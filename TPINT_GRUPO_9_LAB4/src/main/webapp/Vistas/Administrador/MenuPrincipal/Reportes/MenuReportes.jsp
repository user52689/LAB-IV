<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Reportes del Sistema</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet" />
</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="../../../Componentes/header.jspf" %>

<main class="flex-grow-1 bg-light p-4">
    <div class="container">
        <h2 class="text-center mb-5"><i class="bi bi-graph-up text-danger"></i> Reportes del Sistema</h2>
        <div class="row justify-content-center g-4">
            <div class="col-md-4">
                <a href="ReporteFinanciero.jsp" class="text-decoration-none">
                    <div class="card shadow text-center p-3">
                        <i class="bi bi-bar-chart-fill text-primary" style="font-size: 2rem;"></i>
                        <h5 class="mt-3">Ingresos y Egresos por Fecha</h5>
                    </div>
                </a>
            </div>
            <div class="col-md-4">
                <a href="${pageContext.request.contextPath}/ReporteClienteServlet" class="text-decoration-none">
                    <div class="card shadow text-center p-3">
                        <i class="bi bi-person-lines-fill text-success" style="font-size: 2rem;"></i>
                        <h5 class="mt-3">Ingresos y Egresos por Cliente</h5>
                    </div>
                </a>
            </div>
        </div>
    </div>
</main>

<%@ include file="../../../Componentes/footer.jspf" %>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>