<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    
    <c:if test="${msg == 'ok'}">
        <div class="alert alert-success d-flex justify-content-between align-items-center" role="alert">
            <span>Usuario guardado con éxito.</span>
            <a href="ABMLUsuarios.jsp" class="btn btn-secondary btn-sm">
                <i class="bi bi-box-arrow-left"></i> Volver
            </a>
        </div>
    </c:if>

    <c:if test="${not empty error}">
        <div class="alert alert-danger" role="alert">${error}</div>
    </c:if>

    <!-- Formulario sólo si NO hubo éxito -->
    <c:if test="${msg != 'ok'}">
        <form action="${pageContext.request.contextPath}/usuario/alta" method="post" class="row g-3 needs-validation" novalidate autocomplete="off">

            <div class="col-md-4">
                <label for="dni" class="form-label">DNI</label>
                <input type="text" class="form-control" id="dni" name="dni" pattern="\d{6,20}" placeholder="Ej: 30123456" required />
                <div class="invalid-feedback">Ingresá un DNI válido (solo números).</div>
            </div>

            <div class="col-md-4">
                <label for="nombre_usuario" class="form-label">Nombre de Usuario</label>
                <input type="text" class="form-control" id="nombre_usuario" name="nombre_usuario" minlength="3" required autocomplete="off" />
                <div class="invalid-feedback">Ingresá un nombre de usuario válido (mínimo 3 caracteres).</div>
            </div>

            <div class="col-md-4">
                <label for="contrasena" class="form-label">Contraseña por defecto</label>
                <input type="text" class="form-control" id="contrasena" name="contrasena" value="123456" readonly autocomplete="off" />
                <div class="form-text">Esta será la contraseña inicial del cliente. Luego podrá cambiarla.</div>
            </div>

            <div class="col-md-4">
                <label for="correo_electronico" class="form-label">Correo Electrónico</label>
                <input type="email" class="form-control" id="correo_electronico" name="correo_electronico" required />
                <div class="invalid-feedback">Ingresá un correo válido.</div>
            </div>

            <div class="col-md-4">
                <label for="rol" class="form-label">Rol</label>
                <select class="form-select" id="rol" name="rol" required>
                    <option value="" selected disabled>Seleccione un rol</option>
                    <option value="admin">Administrador</option>
                    <option value="cliente">Cliente</option>
                </select>
                <div class="invalid-feedback">Elegí un rol válido.</div>
            </div>

            <div class="col-12">
                <button class="btn btn-primary" type="submit">Guardar Usuario</button>
            </div>

        </form>


        <div class="mb-3 row mt-3">
            <div class="col-sm-12 text-center">
                <a href="${pageContext.request.contextPath}/Vistas/Administrador/MenuPrincipal/Usuarios/ABMLUsuarios.jsp" class="btn btn-secondary">
                    <i class="bi bi-box-arrow-left"></i> Volver
                </a>
            </div>
        </div>
    </c:if>
</main>

<%@ include file="../../../Componentes/footer.jspf" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<script>
(() => {
  'use strict'
  const forms = document.querySelectorAll('.needs-validation')
  Array.from(forms).forEach(form => {
    form.addEventListener('submit', event => {
      if (!form.checkValidity()) {
        event.preventDefault()
        event.stopPropagation()
      }
      form.classList.add('was-validated')
    }, false)
  })
})()
</script>

</body>
</html>
