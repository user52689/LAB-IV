<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <title>Alta de Usuario - MiBanco</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet" />
</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="../../../Componentes/header.jspf" %>

<main class="flex-grow-1 container my-5">
    <h2 class="mb-4 text-primary">
        <i class="bi bi-person-plus-fill me-2"></i>Alta de Usuario
    </h2>

    <form action="${pageContext.request.contextPath}/usuario/alta" method="post" class="row g-3 needs-validation" novalidate>
    
        <div class="col-md-4">
            <label for="usuario" class="form-label">Usuario</label>
            <input type="text" class="form-control" id="usuario" name="usuario" required />
            <div class="invalid-feedback">Ingresá un usuario.</div>
        </div>

        <div class="col-md-4">
            <label for="contrasena" class="form-label">Contraseña</label>
            <input type="password" class="form-control" id="contrasena" name="contrasena" required />
            <div class="invalid-feedback">La contraseña debe tener al menos 6 caracteres.</div>
        </div>

        <div class="col-md-4">
            <label for="nombre" class="form-label">Nombre</label>
            <input type="text" class="form-control" id="nombre" name="nombre" required />
            <div class="invalid-feedback">Por favor, ingresá el nombre.</div>
        </div>

        <div class="col-md-4">
            <label for="apellido" class="form-label">Apellido</label>
            <input type="text" class="form-control" id="apellido" name="apellido" required />
            <div class="invalid-feedback">Por favor, ingresá el apellido.</div>
        </div>

        <div class="col-md-4">
            <label for="email" class="form-label">Correo Electrónico</label>
            <input type="email" class="form-control" id="email" name="correoElectronico" required />
            <div class="invalid-feedback">Ingresá un correo válido.</div>
        </div>

        <div class="col-md-4">
            <label for="telefono" class="form-label">Teléfono</label>
            <input type="tel" class="form-control" id="telefono" name="telefono" pattern="\d{7,15}" placeholder="Ej: 1123456789" required />
            <div class="invalid-feedback">Ingresá un teléfono válido.</div>
        </div>

        <div class="col-12">
            <button class="btn btn-primary" type="submit">Guardar Usuario</button>
            <div class="mb-3 row mt-3">
                <div class="col-sm-12 text-center">
                    <a href="ABMLUsuarios.jsp" class="btn btn-secondary">
                        <i class="bi bi-box-arrow-left"></i> Volver
                    </a>
                </div>
            </div>
        </div>
    
    </form>
</main>

<%@ include file="../../../Componentes/footer.jspf" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>


</body>
</html>
