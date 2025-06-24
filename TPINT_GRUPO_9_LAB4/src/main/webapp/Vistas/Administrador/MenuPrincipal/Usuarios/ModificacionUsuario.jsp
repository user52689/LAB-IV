<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Blanqueo de Contraseña de Usuario - MiBanco</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet" />
</head>
<body class="d-flex flex-column min-vh-100">

<!-- CABECERA -->
<%@ include file="../../../Componentes/header.jspf" %>

<!-- CUERPO -->
<main class="flex-grow-1 bg-light p-4">
    <div class="container">

        <h2 class="mb-4 text-warning">
            <i class="bi bi-pencil-square me-2"></i>Blanqueo de Contraseña de Usuario
        </h2>

        <p>Desde acá podés blanquear la contraseña de un usuario administrador o cliente. Una vez hecho esto, el usuario deberá asignar una nueva contraseña al iniciar sesión.</p>

        <!-- Mensajes de éxito o error -->
        <c:if test="${not empty mensajeExito}">
            <div class="alert alert-success mt-3" role="alert">
                <i class="bi bi-check-circle-fill me-2"></i>${mensajeExito}
            </div>
        </c:if>
        <c:if test="${not empty mensajeError}">
            <div class="alert alert-danger mt-3" role="alert">
                <i class="bi bi-exclamation-triangle-fill me-2"></i>${mensajeError}
            </div>
        </c:if>

        <!-- Formulario para buscar usuario por DNI -->
        <form action="${pageContext.request.contextPath}/usuario/blanquear" method="get" class="mt-4">
            <div class="mb-3">
                <label for="dni" class="form-label">DNI del Usuario</label>
                <input type="text" class="form-control" id="dni" name="dni" value="${param.dni}" required placeholder="Ej: 30123456" pattern="\d{7,8}" />
            </div>

            <button type="submit" class="btn btn-warning text-white">
                <i class="bi bi-search me-2"></i>Buscar Usuario
            </button>
        </form>

		<c:choose>
		    <c:when test="${not empty usuario}">
		        <div class="alert alert-warning mt-4" role="alert">
		            <h5>Usuario encontrado</h5>
		            <p>
		                <strong>DNI:</strong> ${usuario.dni}<br/>
		                <strong>Usuario:</strong> ${usuario.nombreUsuario}<br/>
		                <strong>Rol:</strong> ${usuario.rol}<br/>
		                <strong>Correo:</strong> 
		                <c:choose>
		                    <c:when test="${not empty usuario.correoElectronico}">${usuario.correoElectronico}</c:when>
		                    <c:otherwise>No registrado</c:otherwise>
		                </c:choose>
		            </p>
		            
		            <p><strong>Contraseña que se asignará:</strong> 123456</p>
		
		            <form method="post" action="${pageContext.request.contextPath}/usuario/blanquear" 
		                  onsubmit="return confirm('¿Estás seguro que querés blanquear la contraseña de este usuario?');">
		                <input type="hidden" name="accion" value="blanquear" />
		                <input type="hidden" name="dni" value="${usuario.dni}" />
		                <input type="hidden" name="contrasena" value="123456" />
		                <button type="submit" class="btn btn-warning">
		                    <i class="bi bi-key-fill me-2"></i>Confirmar Blanqueo de Contraseña
		                </button>
		            </form>
		        </div>
		    </c:when>
		
		    <c:when test="${empty usuario and not empty param.dni and empty mensajeExito}">
		        <div class="alert alert-danger mt-4" role="alert">
		            No se encontró ningún usuario con DNI ${param.dni}.
		        </div>
		    </c:when>
		</c:choose>


        <div class="mb-3 row mt-3">
            <div class="col-sm-12 text-center">
                <a href="ABMLUsuarios.jsp" class="btn btn-secondary">
                    <i class="bi bi-box-arrow-left"></i> Volver
                </a>
            </div>
        </div>

    </div>
</main>

<!-- PIE -->
<%@ include file="../../../Componentes/footer.jspf" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
