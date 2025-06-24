<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listar Seguros</title>
    <!-- Bootstrap 5 CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
    body {
        background-image: url('img/fondo-seguros.jpg');
        background-size: cover;
        background-repeat: no-repeat;
        background-attachment: fixed;
        background-position: center;
    }
    </style>
</head>
<body class="bg-light">

    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary mb-4">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">SegurosApp</a>
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="Inicio.jsp">Inicio</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="AgregarSeguro.jsp">Agregar Seguro</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="ServletSeguro?Param=1">Lista Seguros</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Contenido principal -->
    <div class="container card shadow p-4 bg-white">
        <h1 class="text-primary mb-4">Tipo de seguros</h1>

        <!-- Mensaje de depuración -->
        <c:if test="${empty listaTiposSeguro}">
            <div class="alert alert-warning">No se encontraron tipos de seguro en la base de datos.</div>
        </c:if>

        <!-- Filtro por tipo -->
        <form method="post" action="ServletSeguro" class="row g-3 mb-4">
            <div class="col-auto">
                <label for="tipos" class="form-label">Búsqueda por tipo de seguros:</label>
            </div>
            <div class="col-md-4">
                <select id="tipos" name="tipoSeleccionado" class="form-select">
                    <option value="">-- Seleccione un tipo --</option>
                    <c:forEach var="tipo" items="${listaTiposSeguro}">
                        <option value="${tipo.id}" <c:if test="${tipo.id == tipoSeleccionado}">selected</c:if>>${tipo.descripcion}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-auto">
                <button type="submit" class="btn btn-primary">Filtrar</button>
            </div>
        </form>

        <!-- Tabla de resultados -->
        <div class="table-responsive">
            <table class="table table-bordered table-striped table-hover">
                <thead class="table-primary text-center">
                    <tr>
                        <th>ID Seguro</th>
                        <th>Descripción Seguro</th>
                        <th>Descripción Tipo Seguro</th>
                        <th>Costo Contratación</th>
                        <th>Costo Máximo Asegurado</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="seguro" items="${listaSeguros}">
                        <tr>
                            <td>${seguro.id}</td>
                            <td>${seguro.descripcion}</td>
                            <td>${seguro.descripcionTipo}</td>
                            <td>${seguro.costoContratacion}</td>
                            <td>${seguro.costoAsegurado}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>