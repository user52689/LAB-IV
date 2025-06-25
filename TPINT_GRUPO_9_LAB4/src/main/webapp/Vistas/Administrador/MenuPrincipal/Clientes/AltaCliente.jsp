<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <title>Alta de Cliente - MiBanco</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    
</head>
<body class="d-flex flex-column min-vh-100">
<%@ include file="../../../Componentes/header.jspf" %>

<main class="flex-grow-1 container my-5">
    <h2 class="mb-4 text-success">
	    <i class="bi bi-person-plus-fill me-2"></i>Alta de Cliente
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
		<form action="${pageContext.request.contextPath}/cliente/alta" method="post" class="row g-3 needs-validation" novalidate autocomplete="off">
		  
		    <!-- DNI -->
		    <div class="col-md-4">
		        <label for="dni" class="form-label">DNI</label>
		        <input type="text" class="form-control" id="dni" name="dni" pattern="^[0-9]{7,8}$" required />
		        <div class="invalid-feedback">Ingresá un DNI válido de 7 u 8 dígitos numéricos.</div>
		    </div>
		
		    <!-- CUIL -->
		    <div class="col-md-4">
		        <label for="cuil" class="form-label">CUIL</label>
		        <input type="text" class="form-control" id="cuil" name="cuil" pattern="^\d{2}-\d{8}-\d{1}$" placeholder="20-12345678-3" required />
		        <div class="invalid-feedback">Formato CUIL inválido. Ejemplo: 20-12345678-3</div>
		    </div>
		
		    <!-- Nombre -->
		    <div class="col-md-4">
		        <label for="nombre" class="form-label">Nombre</label>
		        <input type="text" class="form-control" id="nombre" name="nombre" pattern="[A-Za-zÁÉÍÓÚÑáéíóúñ\s]{2,50}" required />
		        <div class="invalid-feedback">El nombre debe tener solo letras y al menos 2 caracteres.</div>
		    </div>
		
		    <!-- Apellido -->
		    <div class="col-md-4">
		        <label for="apellido" class="form-label">Apellido</label>
		        <input type="text" class="form-control" id="apellido" name="apellido" pattern="[A-Za-zÁÉÍÓÚÑáéíóúñ\s]{2,50}" required />
		        <div class="invalid-feedback">El apellido debe tener solo letras y al menos 2 caracteres.</div>
		    </div>
		
		    <!-- Género -->
		    <div class="col-md-4">
		        <label for="generos" class="form-label">Género</label>
		        <select id="generos" name="generos" class="form-select" required>
		            <option value="">Seleccioná género</option>
		            <c:forEach var="gen" items="${generos}">
		                <option value="${gen.idGenero}">${gen.descripcion}</option>
		            </c:forEach>
		        </select>
		        <div class="invalid-feedback">Seleccioná una opción de género.</div>
		    </div>
		
		    <!-- Nacionalidad -->
		    <div class="col-md-4">
		        <label for="nacionalidades" class="form-label">Nacionalidad</label>
		        <select id="nacionalidades" name="nacionalidades" class="form-select" required>
		            <option value="">Seleccioná nacionalidad</option>
		            <c:forEach var="nacionalidades" items="${nacionalidades}">
		                <option value="${nacionalidades.idPais}">${nacionalidades.nombre}</option>
		            </c:forEach>
		        </select>
		        <div class="invalid-feedback">Seleccioná una nacionalidad.</div>
		    </div>
		
		    <!-- Provincia -->
		    <div class="col-md-4">
		        <label for="provincias" class="form-label">Provincia</label>
		        <select id="provincias" name="provincias" class="form-select" required>
		            <option value="">Seleccioná provincia</option>
		            <c:forEach var="prov" items="${provincias}">
		                <option value="${prov.idProvincia}">${prov.nombreProvincia}</option>
		            </c:forEach>
		        </select>
		        <div class="invalid-feedback">Seleccioná una provincia.</div>
		    </div>
		
		    <!-- Localidad -->
		    <div class="col-md-4">
		        <label for="localidades" class="form-label">Localidad</label>
		        <select id="localidades" name="localidades" class="form-select" required>
		            <option value="">Seleccioná localidad</option>
		            <c:forEach var="loc" items="${localidades}">
		                <option value="${loc.idLocalidad}">${loc.nombreLocalidad}</option>
		            </c:forEach>
		        </select>
		        <div class="invalid-feedback">Seleccioná una localidad.</div>
		    </div>
		
		    <!-- Fecha Nacimiento -->
		    <div class="col-md-4">
		        <label for="fechaNacimiento" class="form-label">Fecha de Nacimiento</label>
		        <input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento" required />
		        <div class="invalid-feedback">Seleccioná una fecha de nacimiento válida.</div>
		    </div>
		
		    <!-- Dirección -->
		    <div class="col-md-4">
		        <label for="direccion" class="form-label">Dirección</label>
		        <input type="text" class="form-control" id="direccion" name="direccion" required />
		        <div class="invalid-feedback">Ingresá la dirección completa.</div>
		    </div>
		
		    <!-- Email -->
		    <div class="col-md-4">
		        <label for="email_cliente" class="form-label">Correo Electrónico</label>
		        <input type="email" class="form-control" id="email_cliente" name="correoElectronico" required />
		        <div class="invalid-feedback">Ingresá un correo válido.</div>
		    </div>
		
		    <!-- Teléfono -->
		    <div class="col-md-4">
		        <label for="telefono" class="form-label">Teléfono</label>
		        <input type="tel" class="form-control" id="telefono" name="telefono" pattern="^[0-9]{7,15}$" required />
		        <div class="invalid-feedback">Ingresá un teléfono numérico válido (mínimo 7 dígitos).</div>
		    </div>
		
		    <!-- Usuario -->
		    <div class="col-md-4">
		        <label for="usuario" class="form-label">Usuario</label>
		        <input type="text" class="form-control" id="usuario" name="usuario" required pattern="^[a-zA-Z0-9]{4,20}$" />
		        <div class="invalid-feedback">Usuario requerido (entre 4 y 20 caracteres alfanuméricos).</div>
		    </div>
		
		    <!-- Contraseña por defecto -->
		    <div class="col-md-4">
		        <label for="contrasena" class="form-label">Contraseña por defecto</label>
		        <input type="text" class="form-control" id="contrasena" name="contrasena" value="123456" readonly />
		        <div class="form-text">Esta será la contraseña inicial del cliente.</div>
		    </div>

		
		     <div class="col-12">
			    <button class="btn btn-primary" type="submit">Guardar Cliente</button>
			    <div class="mb-3 row mt-3">
			      <div class="col-sm-12 text-center">
			        <a href="${pageContext.request.contextPath}/Vistas/Administrador/MenuPrincipal/Clientes/ABMLClientes.jsp" class="btn btn-secondary">
			          <i class="bi-box-arrow-left"></i> Volver
			        </a>
			      </div>
			    </div>
			 </div>
		
		</form>
	</c:if>

</main>

<!-- PIE -->
<%@ include file="../../../Componentes/footer.jspf" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
    // Bootstrap 5 form validation
    (() => {
        'use strict';
        const forms = document.querySelectorAll('.needs-validation');
        Array.from(forms).forEach(form => {
            form.addEventListener('submit', event => {
                if (!form.checkValidity()) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
            }, false);
        });
    })();
</script>

</body>
</html>
