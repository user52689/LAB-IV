<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Modelo.Cliente" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <title>Baja de Cliente - MiBanco</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet" />
</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="../../../Componentes/header.jspf" %>

<main class="flex-grow-1 bg-light p-4">
    <div class="container">

        <h2 class="mb-4 text-danger"><i class="bi bi-person-dash-fill me-2"></i>Baja de Cliente</h2>
        <p>Buscá un cliente por DNI para darlo de baja del sistema.</p>

        <form method="get" action="${pageContext.request.contextPath}/cliente/baja" class="row g-3 mb-4">
            <input type="hidden" name="context" value="baja" />
            <div class="col-md-4">
                <input type="text" name="dni" class="form-control" placeholder="Buscar por DNI"
                       value="${dniBuscado != null ? dniBuscado : ''}" />
            </div>
            <div class="col-md-2">
                <button type="submit" class="btn btn-primary">
                    <i class="bi bi-search"></i> Buscar
                </button>
            </div>
            <div class="col-md-2">
                <button type="submit" class="btn btn-outline-danger" name="limpiar" value="true">
                    <i class="bi bi-eraser-fill"></i> Limpiar
                </button>
            </div>
        </form>

        <c:if test="${not empty mensajeExito}">
            <div class="alert alert-success mt-4" role="alert">
                <i class="bi bi-check-circle-fill me-2"></i>
                ${mensajeExito}
            </div>
        </c:if>

        <c:if test="${not empty mensajeError}">
            <div class="alert alert-danger mt-4" role="alert">
                <i class="bi bi-exclamation-triangle-fill me-2"></i>
                ${mensajeError}
            </div>
        </c:if>

        <c:choose>
            <c:when test="${not empty cliente}">
                <div class="alert alert-warning mt-4" role="alert">
                    <h5>Cliente encontrado</h5>
                    <p><strong>Nombre:</strong> ${cliente.nombre} ${cliente.apellido}<br/>
                       <strong>DNI:</strong> ${cliente.dni}<br/>
                       <strong>Email:</strong> ${cliente.correoElectronico}</p>
                    <form method="post" action="${pageContext.request.contextPath}/cliente/baja">
                        <input type="hidden" name="accion" value="borrar" />
                        <input type="hidden" name="dni" value="${cliente.dni}" />
                        <button type="submit" class="btn btn-danger"
                                onclick="return confirm('¿Estás seguro que querés dar de baja a este cliente?');">
                            <i class="bi bi-trash-fill"></i> Confirmar Baja
                        </button>
                    </form>
                </div>
            </c:when>
            <c:when test="${empty cliente and dniBuscado != null and dniBuscado != '' and empty mensajeExito}">
                <div class="alert alert-danger mt-4" role="alert">
                    No se encontró ningún cliente con DNI ${dniBuscado}.
                </div>
            </c:when>
        </c:choose>

        <div class="d-flex justify-content-center mt-5">
            <a href="${pageContext.request.contextPath}/Vistas/Administrador/MenuPrincipal/Clientes/ABMLClientes.jsp" class="btn btn-secondary">
                <i class="bi-box-arrow-left"></i> Volver
            </a>
        </div>

    </div>
</main>

<%@ include file="../../../Componentes/footer.jspf" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
