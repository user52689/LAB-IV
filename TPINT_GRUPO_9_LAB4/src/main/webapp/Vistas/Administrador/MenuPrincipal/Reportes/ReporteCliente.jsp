
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Modelo.Cliente" %>
<%@ page import="Modelo.ReporteCliente" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Reporte por Cliente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet" />
</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="../../../Componentes/header.jspf" %>

<main class="flex-grow-1 bg-light p-4">
    <div class="container">
        <h2 class="text-center mb-5"><i class="bi bi-person-lines-fill text-success"></i> Reporte por Cliente</h2>

        <form action="${pageContext.request.contextPath}/ReporteClienteServlet" method="post" class="row g-3">
            <div class="col-md-4">
                <label class="form-label">Cliente</label>
                <select name="idCliente" class="form-select" required>
                    <option value="">Seleccione un cliente</option>
                    <%
                        List<Cliente> clientes = (List<Cliente>) request.getAttribute("listaClientes");
                        Integer seleccionado = (Integer) request.getAttribute("idCliente");
                        if (clientes != null) {
                            for (Cliente c : clientes) {
                                String selected = (seleccionado != null && seleccionado == c.getIdCliente()) ? "selected" : "";
                    %>
                        <option value="<%= c.getIdCliente() %>" <%= selected %>>
                            <%= c.getNombre() %> <%= c.getApellido() %> - DNI: <%= c.getDni() %>
                        </option>
                    <%
                            }
                        }
                    %>
                </select>
            </div>

            <div class="col-md-4">
                <label class="form-label">Fecha Desde</label>
                <input type="date" class="form-control" name="fechaDesde" value="${fechaDesde}" required>
            </div>
            <div class="col-md-4">
                <label class="form-label">Fecha Hasta</label>
                <input type="date" class="form-control" name="fechaHasta" value="${fechaHasta}" required>
            </div>

            <div class="col-12 d-flex justify-content-end">
                <button type="submit" class="btn btn-primary"><i class="bi bi-bar-chart-fill me-1"></i> Generar Reporte</button>
            </div>
        </form>

        <hr class="my-4"/>

        <%
            ReporteCliente reporte = (ReporteCliente) request.getAttribute("reporte");
            if (reporte != null) {
        %>
        <div class="card shadow-sm">
            <div class="card-body">
                <h5 class="card-title">Cliente: <%= reporte.getNombreCliente() %></h5>
                <table class="table table-bordered text-center mt-3">
                    <thead class="table-light">
                        <tr>
                            <th>Total Ingresos</th>
                            <th>Total Egresos</th>
                            <th>Saldo Neto</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>$<%= String.format("%.2f", reporte.getIngresos()) %></td>
                            <td>$<%= String.format("%.2f", reporte.getEgresos()) %></td>
                            <td>$<%= String.format("%.2f", reporte.getSaldo()) %></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <%
            } else if (request.getAttribute("error") != null) {
        %>
        <div class="alert alert-danger mt-3">
            <%= request.getAttribute("error") %>
        </div>
        <% } %>
    </div>
    
    <div class="d-flex justify-content-center mt-5">
    <a href="${pageContext.request.contextPath}/Vistas/Administrador/MenuPrincipal/Reportes/MenuReportes.jsp" class="btn btn-secondary">
        <i class="bi bi-box-arrow-left"></i> Volver
    </a>
</div>

</main>

<%@ include file="../../../Componentes/footer.jspf" %>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
