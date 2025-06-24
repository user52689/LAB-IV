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

   <form action="${pageContext.request.contextPath}/cliente/alta" method="post" class="row g-3 needs-validation" novalidate>
  
    <div class="col-md-4">
        <label for="dni" class="form-label">DNI</label>
        <input type="text" class="form-control" id="dni" name="dni" pattern="\d{7,8}" required />
        <div class="invalid-feedback">Por favor, ingresá el DNI.</div>
    </div>

    <div class="col-md-4">
        <label for="cuil" class="form-label">CUIL</label>
        <input type="text" class="form-control" id="cuil" name="cuil" pattern="\d{2}-\d{8}-\d" placeholder="XX-XXXXXXXX-X" required />
        <div class="invalid-feedback">Formato CUIL inválido. Ej: 20-12345678-3</div>
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
        <label for="generos" class="form-label">Género</label>
        <select id="generos" name="generos" class="form-select" required>
            <option value="">Seleccioná género</option>
            <c:forEach var="gen" items="${generos}">
                <option value="${gen.idGenero}">${gen.descripcion}</option>
            </c:forEach>
        </select>
        <div class="invalid-feedback">Por favor, seleccioná un género.</div>
    </div>

    <div class="col-md-4">
        <label for="nacionaidades" class="form-label">Nacionalidad</label>
        <select id="nacionaidades" name="nacionaidades" class="form-select" required>
            <option value="">Seleccioná nacionalidad</option>
            <c:forEach var="nac" items="${pais}">
                <option value="${nac.idPais}">${nac.nombre}</option>
            </c:forEach>
        </select>
        <div class="invalid-feedback">Por favor, seleccioná una nacionalidad.</div>
    </div>

     <div class="col-md-4">
	    <label for="provincias" class="form-label">Provincia</label>
	    <select id="provincias" name="provincias" class="form-select" required>
	        <option value="">Seleccioná provincia</option>
	        <c:forEach var="prov" items="${provincias}">
	            <option value="${prov.idProvincia}">${prov.nombreProvincia}</option>
	        </c:forEach>
	    </select>
	    <div class="invalid-feedback">Por favor, seleccioná una provincia.</div>
	</div>
	
	<div class="col-md-4">
	    <label for="localidades" class="form-label">Localidad</label>
	    <select id="localidades" name="localidades" class="form-select" required>
	        <option value="">Seleccioná localidad</option>
	        <c:forEach var="loc" items="${localidades}">
	            <option value="${loc.idLocalidad}">${loc.nombreLocalidad}</option>
	        </c:forEach>
	    </select>
	    <div class="invalid-feedback">Por favor, seleccioná una localidad.</div>
	</div>

    <div class="col-md-4">
        <label for="fechaNacimiento" class="form-label">Fecha de Nacimiento</label>
        <input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento" required />
        <div class="invalid-feedback">Ingresá la fecha de nacimiento.</div>
    </div>

    <div class="col-md-4">
        <label for="direccion" class="form-label">Dirección</label>
        <input type="text" class="form-control" id="direccion" name="direccion" required />
        <div class="invalid-feedback">Ingresá la dirección.</div>
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

</main>

<!-- PIE -->
<%@ include file="../../../Componentes/footer.jspf" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
