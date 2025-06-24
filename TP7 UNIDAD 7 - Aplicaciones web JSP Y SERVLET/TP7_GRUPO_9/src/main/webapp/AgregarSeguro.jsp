<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Agregar Seguro</title>
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

    <!-- Proteger contra acceso directo -->
    <c:if test="${empty listaTiposSeguro && empty debugMessage}">
        <c:redirect url="/ServletSeguro"/>
    </c:if>

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
                        <a class="nav-link active" href="ServletSeguro">Agregar Seguro</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="ServletSeguro?Param=1">Lista Seguros</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Contenido principal -->
    <div class="container card shadow p-4 bg-white">
        <h1 class="mb-4 text-primary">Agregar Seguros</h1>

        <!-- Mensaje de depuración -->
        <div class="alert alert-info">
            <strong>Depuración:</strong> ${fn:escapeXml(debugMessage)}<br>
            Tipos de seguro cargados: ${tiposSeguroCount}
            <c:if test="${tiposSeguroCount == 0}">
                <br><strong>Advertencia:</strong> No se encontraron tipos de seguro. Verifique la conexión a la base de datos o la tabla tipoSeguros.
            </c:if>
        </div>

        <form action="ServletSeguro" method="post">
            <div class="mb-3 row">
                <label class="col-sm-3 col-form-label">Id Seguro:</label>
                <div class="col-sm-9 pt-2">
                    <span class="form-control-plaintext">${idSeguro}</span>
                </div>
            </div>

            <div class="mb-3 row">
                <label for="descripcion" class="col-sm-3 col-form-label">Descripción:</label>
                <div class="col-sm-9">
                    <input type="text" name="descripcion" class="form-control" required>
                </div>
            </div>

            <div class="mb-3 row">
                <label for="tipoSeguro" class="col-sm-3 col-form-label">Tipo de Seguro:</label>
                <div class="col-sm-9">
                    <select name="tipoSeguro" class="form-select" required>
                        <option value="">-- Seleccione un tipo --</option>
                        <c:forEach var="tipo" items="${listaTiposSeguro}">
                            <option value="${tipo.id}">${tipo.descripcion}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="mb-3 row">
                <label for="costoContratacion" class="col-sm-3 col-form-label">Costo contratación:</label>
                <div class="col-sm-9">
                    <input type="text" name="costoContratacion" class="form-control" required>
                </div>
            </div>

            <div class="mb-3 row">
                <label for="costoMaximoAsegurado" class="col-sm-3 col-form-label">Costo Máximo Asegurado:</label>
                <div class="col-sm-9">
                    <input type="text" name="costoMaximoAsegurado" class="form-control" required>
                </div>
            </div>

            <div class="text-center">
                <button type="submit" name="btnAceptar" class="btn btn-primary">Aceptar</button>
            </div>
            <br>
            <c:if test="${cantFilas == 1}">
                <div class="alert alert-success text-center">Seguro agregado Exitosamente</div>
            </c:if>
        </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>