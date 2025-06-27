<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.Date, java.util.List, java.util.ArrayList, java.text.NumberFormat, java.text.SimpleDateFormat, java.util.Locale" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <title>Mis Préstamos - MiBanco</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet" />
</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="../../../Componentes/header.jspf" %>

<main class="flex-grow-1 bg-light p-4">
    <div class="container">
        <h2 class="mb-4">Mis Préstamos</h2>

        <%
            // Simulación de préstamos
            class Prestamo {
                int id;
                double montoTotal;
                double saldoPendiente;
                Date fechaInicio;
                String estado; // Activo, Cancelado

                Prestamo(int id, double montoTotal, double saldoPendiente, Date fechaInicio, String estado) {
                    this.id = id;
                    this.montoTotal = montoTotal;
                    this.saldoPendiente = saldoPendiente; 
                    this.fechaInicio = fechaInicio;
                    this.estado = estado;
                }
            }

            List<Prestamo> prestamos = new ArrayList<>();
            prestamos.add(new Prestamo(1, 150000.00, 75000.00, new Date(new java.util.Date().getTime() - 86400000L * 90), "Activo"));
            prestamos.add(new Prestamo(2, 50000.00, 0.00, new Date(new java.util.Date().getTime() - 86400000L * 90), "Cancelado"));
            prestamos.add(new Prestamo(3, 200000.00, 150000.00, new Date(new java.util.Date().getTime() - 86400000L * 90), "Activo"));

            NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("es", "AR"));
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        %>

        <%
            if (prestamos.isEmpty()) {
        %>
            <div class="alert alert-info">No tenés préstamos activos o pasados.</div>
        <%
            } else {
        %>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Monto Total</th>
                        <th>Saldo Pendiente</th>
                        <th>Fecha de Inicio</th>
                        <th>Estado</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                <%
                    for (Prestamo p : prestamos) {
                %>
                    <tr>
                        <td><%= p.id %></td>
                        <td><%= nf.format(p.montoTotal) %></td>
                        <td><%= nf.format(p.saldoPendiente) %></td>
                        <td><%= sdf.format(p.fechaInicio) %></td>
                        <td><%= p.estado %></td>
                        <td>
                            <a href="DetallePrestamo.jsp?idPrestamo=<%= p.id %>" class="btn btn-sm btn-primary">
                                <i class="bi bi-eye"></i> Ver
                            </a>
                            <%
                                if ("Activo".equals(p.estado) && p.saldoPendiente > 0) {
                            %>
                            <a href="PagarPrestamoCliente.jsp?idPrestamo=<%= p.id %>" class="btn btn-sm btn-success ms-1">
                                <i class="bi bi-cash-coin"></i> Pagar
                            </a>
                            <%
                                }
                            %>
                        </td>
                    </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        <%
            }
        %>
        <div class="mb-3 row mt-4">
		    <div class="col-sm-12 text-center">
		        <a href="../MenuPrincipal/MenuPrincipalCliente.jsp" class="btn btn-secondary">
		            <i class="bi-box-arrow-left"></i> Volver
		        </a>
		    </div>
		</div>
    </div>
</main>

<%@ include file="../../../Componentes/footer.jspf" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
