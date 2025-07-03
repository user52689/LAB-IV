<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Modelo.Prestamo" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mis Préstamos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet" />
</head>
<body class="bg-light">

<%@ include file="../../../Componentes/header.jspf" %>

<div class="container mt-5">
    <h2 class="mb-4">Mis Préstamos</h2>

    <%
        List<Prestamo> prestamos = (List<Prestamo>) request.getAttribute("prestamosCliente");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if (prestamos != null && !prestamos.isEmpty()) {
    %>
        <table class="table table-bordered table-striped">
            <thead class="table-dark">
                <tr>
                    <th>#</th>
                    <th>Fecha Solicitud</th>
                    <th>Importe Total</th>
                    <th>Saldo Pendiente</th>
                    <th>Estado</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int i = 1;
                    for (Prestamo p : prestamos) {
                %>
                <tr>
                    <td><%= i++ %></td>
                    <td><%= p.getFechaSolicitud().format(formatter) %></td>
                    <td>$<%= String.format("%.2f", p.getImporteTotal()) %></td>
                    <td>$<%= String.format("%.2f", p.getSaldoPendiente()) %></td>
                    <td><%= p.getEstadoPrestamo().getDescripcion() %></td>
                    <td>
                        <a href="DetallePrestamoServlet?id=<%= p.getIdPrestamo() %>" class="btn btn-info btn-sm">
                            <i class="bi bi-eye"></i> Ver Detalle
                        </a>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    <%
        } else {
    %>
        <div class="alert alert-info">No tenés préstamos registrados aún.</div>
    <%
        }
    %>

    <div class="mt-4">
        <a href="<%=request.getContextPath()%>/Vistas/Clientes/MenuPrincipal/Prestamos/MenuPrestamos.jsp" class="btn btn-secondary">
    ← Volver
</a>
    </div>
</div>

<%@ include file="../../../Componentes/footer.jspf" %>
</body>
</html>
