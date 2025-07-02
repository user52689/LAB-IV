<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.List, java.text.NumberFormat, java.text.SimpleDateFormat, java.util.Locale"
    import="Modelo.Prestamo" %>
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
            List<Prestamo> prestamos = (List<Prestamo>) request.getAttribute("prestamos");

            NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("es", "AR"));
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        %>

        <%
            if (prestamos == null || prestamos.isEmpty()) {
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
                        <td><%= p.getIdPrestamo() %></td>
                        <td><%= nf.format(p.getImporteTotal()) %></td>
                        <td><%= nf.format(p.getSaldoPendiente()) %></td>
                        <td><%= sdf.format(java.sql.Timestamp.valueOf(p.getFechaSolicitud())) %></td>
                        <td><%= p.getEstadoPrestamo().getDescripcion() %></td>
                        <td>
                            <a href="DetallePrestamo.jsp?idPrestamo=<%= p.getIdPrestamo() %>" class="btn btn-sm btn-primary">
                                <i class="bi bi-eye"></i> Ver
                            </a>
                            <%
                                if ("Activo".equalsIgnoreCase(p.getEstadoPrestamo().getDescripcion()) &&
                                    p.getSaldoPendiente() > 0) {
                            %>
                            <a href="PagarPrestamoCliente.jsp?idPrestamo=<%= p.getIdPrestamo() %>" class="btn btn-sm btn-success ms-1">
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
                <a href="MenuPrestamos.jsp" class="btn btn-secondary">
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
