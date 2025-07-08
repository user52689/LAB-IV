<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Modelo.Prestamo" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
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
            List<Prestamo> prestamos = (List<Prestamo>) request.getAttribute("prestamosCliente");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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
                        int estadoId = p.getEstadoPrestamo().getIdEstadoPrestamo();
                        String estadoDesc = p.getEstadoPrestamo().getDescripcion();
                        String badgeClass = "secondary"; // Por defecto

                        switch (estadoDesc.toLowerCase()) {
                            case "en evaluación":
                                badgeClass = "primary"; // Azul
                                break;
                            case "aprobado":
                                badgeClass = "success"; // Verde
                                break;
                            case "cancelado":
                                badgeClass = "danger"; // Rojo
                                break;
                            case "en proceso":
                                badgeClass = "warning text-dark"; // Amarillo con texto oscuro
                                break;
                        }
                %>
                    <tr>
                        <td><%= p.getIdPrestamo() %></td>
                        <td>$<%= String.format("%.2f", p.getImporteTotal()) %></td>
                        <td>$<%= String.format("%.2f", p.getSaldoPendiente()) %></td>
                        <td><%= p.getFechaSolicitud().format(formatter) %></td>
                        <td>
                            <span class="badge bg-<%= badgeClass %>"><%= estadoDesc %></span>
                        </td>
                        <td>
                            <a href="DetallePrestamoServlet?id=<%= p.getIdPrestamo() %>" class="btn btn-sm btn-primary">
                                <i class="bi bi-eye"></i> Ver
                            </a>
                            <%
                                if ("aprobado".equals(estadoDesc.toLowerCase()) && p.getSaldoPendiente() > 0) {
                            %>
                            <a href="${pageContext.request.contextPath}/Prestamos/pagar?idPrestamo=${p.idPrestamo}" class="btn btn-sm btn-success ms-1">
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
                <a href="<%=request.getContextPath()%>/Vistas/Clientes/MenuPrincipal/Prestamos/MenuPrestamos.jsp" class="btn btn-secondary">
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