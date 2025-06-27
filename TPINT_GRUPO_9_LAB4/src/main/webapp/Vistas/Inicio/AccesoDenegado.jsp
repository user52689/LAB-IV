<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Acceso Denegado - MiBanco</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body class="d-flex flex-column min-vh-100 bg-light">

<!-- CABECERA -->
<header class="bg-primary text-white text-center py-3 shadow">
    <h1><i class="bi bi-bank2 me-2"></i>MiBanco</h1>
</header>

<!-- CUERPO PRINCIPAL -->
<main class="flex-grow-1 d-flex flex-column align-items-center justify-content-center text-center px-4">
    <div class="text-danger mb-4">
        <i class="bi bi-shield-exclamation display-1"></i>
        <h2 class="mt-3">Acceso Denegado</h2>
    </div>
    <p class="lead">No tenés permisos para acceder.</p>
    <div class="d-flex gap-2">
        <a href="${pageContext.request.contextPath}/Vistas/Inicio/Login.jsp" class="btn btn-secondary">
            <i class="bi bi-box-arrow-left"></i> Volver al Login
        </a>
        <a href="${pageContext.request.contextPath}/LogoutServlet" class="btn btn-danger">
            <i class="bi bi-box-arrow-right"></i> Cerrar Sesión
        </a>
    </div>
</main>

<!-- PIE -->
<footer class="bg-dark text-white text-center py-3 mt-auto">
    <small>© 2025 MiBanco. Todos los derechos reservados.</small>
</footer>

</body>
</html> 