<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Modelo.ReporteFinanciero" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Reporte Financiero</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet" />
</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="../../../Componentes/header.jspf" %>

<main class="flex-grow-1 bg-light p-4">
    <div class="container">
        <h2 class="text-center mb-5"><i class="bi bi-bar-chart-line-fill text-primary"></i> Reporte Financiero</h2>

        <form action="${pageContext.request.contextPath}/ReporteFinancieroServlet" method="post" class="row g-3">
            <div class="col-md-4">
                <label class="form-label">Fecha Desde</label>
                <input type="date" class="form-control" name="fechaDesde" value="${fechaDesde}" required>
            </div>
            <div class="col-md-4">
                <label class="form-label">Fecha Hasta</label>
                <input type="date" class="form-control" name="fechaHasta" value="${fechaHasta}" required>
            </div>
            <div class="col-md-4 d-flex align-items-end">
                <button type="submit" class="btn btn-primary w-100"><i class="bi bi-bar-chart me-1"></i> Generar Reporte</button>
            </div>
        </form>

        <hr class="my-4" />

        <%
            ReporteFinanciero reporte = (ReporteFinanciero) request.getAttribute("reporte");
            if (reporte != null) {
        %>
        <div class="card shadow-sm">
            <div class="card-body">
                <h5 class="card-title">Resultado:</h5>
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
                            <td>$<%= String.format("%.2f", reporte.getTotalIngresos()) %></td>
                            <td>$<%= String.format("%.2f", reporte.getTotalEgresos()) %></td>
                            <td>$<%= String.format("%.2f", reporte.getSaldo()) %></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <% } else if (request.getAttribute("error") != null) { %>
        <div class="alert alert-danger mt-4">
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


