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
   
	<c:if test="${empty mensajeExito}">
        <form action="${pageContext.request.contextPath}/usuario/alta" method="post" class="row g-3 needs-validation" novalidate autocomplete="off">

            <div class="col-md-4">
                <label for="dni" class="form-label">DNI</label>
                <input type="text" class="form-control" id="dni" name="dni" pattern="\d{6,20}" placeholder="Ej: 30123456" required />
                <div class="invalid-feedback">Ingresá un DNI válido (solo números).</div>
            </div>

            <div class="col-md-4">
                <label for="nombre_usuario" class="form-label">Nombre de Usuario</label>
                <input type="text" class="form-control" id="nombre_usuario" name="nombre_usuario" required autocomplete="off" />
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
			    <label for="id_genero" class="form-label">Género</label>
				<select class="form-select" id="id_genero" name="id_genero" required>
				    <option value="" selected disabled>Seleccione un género</option>
				    <c:forEach var="g" items="${generos}">
				        <option value="${g.idGenero}">${g.descripcion}</option>
				    </c:forEach>
				</select>
			    <div class="invalid-feedback">Seleccioná un género.</div>
			</div>
			
			<div class="col-md-4">
			    <label for="id_pais" class="form-label">Nacionalidad</label>
				<select class="form-select" id="id_pais" name="id_pais" required>
				    <option value="" selected disabled>Seleccione un país</option>
				    <c:forEach var="p" items="${paises}">
				        <option value="${p.idPais}">${p.nombre}</option>
				    </c:forEach>
				</select>
			    <div class="invalid-feedback">Seleccioná un país.</div>
			</div>
			
			<div class="col-md-4">
			    <label for="id_provincia" class="form-label">Provincia</label>
				<select class="form-select" id="id_provincia" name="id_provincia" required>
				    <option value="" selected disabled>Seleccione una provincia</option>
				    <c:forEach var="prov" items="${provincias}">
				        <option value="${prov.idProvincia}">${prov.nombreProvincia}</option>
				    </c:forEach>
				</select>
			    <div class="invalid-feedback">Seleccioná una provincia.</div>
			</div>
			
			<div class="col-md-4">
			    <label for="id_localidad" class="form-label">Localidad</label>
				<select class="form-select" id="id_localidad" name="id_localidad" required>
				    <option value="" selected disabled>Seleccione una localidad</option>
				    <c:forEach var="loc" items="${localidades}">
				        <option value="${loc.idLocalidad}">${loc.nombreLocalidad}</option>
				    </c:forEach>
				</select>
			    <div class="invalid-feedback">Seleccioná una localidad.</div>
			</div>
			
			<div class="col-md-4">
			    <label for="fecha_nacimiento" class="form-label">Fecha de nacimiento</label>
			    <input type="date" class="form-control" id="fecha_nacimiento" name="fecha_nacimiento" required />
			    <div class="invalid-feedback">Ingresá una fecha válida.</div>
			</div>
			
			<div class="col-md-4">
			    <label for="direccion" class="form-label">Dirección</label>
			    <input type="text" class="form-control" id="direccion" name="direccion" required />
			    <div class="invalid-feedback">Ingresá una dirección válida.</div>
			</div>
            

            <div class="col-md-4">
                <label for="rol" class="form-label">Rol</label>
                <select class="form-select" id="rol" name="rol" required>
                    <option value="admin">Administrador</option>
                </select>
                <div class="invalid-feedback">Elegí un rol válido.</div>
            </div>            

            <div class="col-12">
                <button class="btn btn-primary" type="submit">Guardar Usuario</button>
            </div>

        </form>

    </c:if>

        <div class="mb-3 row mt-3">
            <div class="col-sm-12 text-center">
                <a href="${pageContext.request.contextPath}/Vistas/Administrador/MenuPrincipal/Usuarios/ABMLUsuarios.jsp" class="btn btn-secondary">
                    <i class="bi bi-box-arrow-left"></i> Volver
                </a>
            </div>
        </div>
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
