<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Error - MiBanco</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body class="d-flex flex-column min-vh-100 bg-light">

<!-- CABECERA -->
<%@ include file="../Componentes/header.jspf" %>

<!-- CUERPO PRINCIPAL -->
<main class="flex-grow-1 d-flex flex-column align-items-center justify-content-center text-center px-4">
    <div class="text-danger mb-4">
        <i class="bi bi-bug-fill display-1"></i>
        <h2 class="mt-3">Algo salió mal</h2>
    </div>
    <p class="lead mb-3">Ocurrió un problema inesperado.</p>

    <c:if test="${not empty error}">
        <div class="alert alert-danger w-75 mx-auto">
            <i class="bi bi-info-circle-fill me-2"></i>${error}
        </div>
    </c:if>

    <div class="col-sm-12 text-center">
        <a href="${pageContext.request.contextPath}/Vistas/Administrador/MenuPrincipal/Usuarios/ABMLUsuarios.jsp" class="btn btn-secondary">
            <i class="bi bi-box-arrow-left"></i> Volver
        </a>
    </div>
</main>

<!-- PIE -->

<%@ include file="../Componentes/footer.jspf" %>


</body>
</html>
