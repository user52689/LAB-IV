<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <title>Gestión de Usuarios - MiBanco</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet" />
</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="../../../Componentes/header.jspf" %>

<!-- CUERPO PRINCIPAL -->
<main class="flex-grow-1 bg-light p-4">
    <div class="container text-center">
        <h2 class="mb-4">Gestión de Usuarios</h2>
        <p class="mb-5">Seleccioná una acción para gestionar los usuarios administradores.</p>

        <div class="row justify-content-center g-3">
            <div class="col-md-3">
                <a href="AltaUsuario.jsp" class="btn btn-success w-100">
                    <i class="bi bi-person-plus-fill me-1"></i> Alta Usuario
                </a>
            </div>
            <div class="col-md-3">
                <a href="BajaUsuario.jsp" class="btn btn-danger w-100">
                    <i class="bi bi-person-dash-fill me-1"></i> Baja Usuario
                </a>
            </div>
            <div class="col-md-3">
                <a href="ModificacionUsuario.jsp" class="btn btn-warning text-white w-100">
                    <i class="bi bi-pencil-square me-1"></i> Modificar Usuario
                </a>
            </div>
            <div class="col-md-3">
                <a href="ListadoUsuario.jsp" class="btn btn-primary w-100">
                    <i class="bi bi-people-fill me-1"></i> Listado de Usuarios
                </a>
            </div>
            <div class="mb-3 row mt-4">
                <div class="col-sm-12 text-center">
                    <a href="${pageContext.request.contextPath}/Vistas/Administrador/MenuPrincipal/MenuAdministrador.jsp" class="btn btn-secondary">
                        <i class="bi bi-box-arrow-left"></i> Volver
                    </a>
                </div>
            </div>
        </div>
    </div>
</main>

<!-- PIE -->
<%@ include file="../../../Componentes/footer.jspf" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
