<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Modificar Cliente - MiBanco</title>
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
            <i class="bi bi-pencil-square me-2"></i>Blanqueo de Contraseña de Cliente
        </h2>

        <p>Desde acá podés blanquear la contraseña de un cliente. Una vez hecho esto, el cliente deberá asignar una nueva contraseña al iniciar sesión.</p>

        <form action="${pageContext.request.contextPath}/cliente/modificar" method="post" class="mt-4">
            <div class="mb-3">
                <label for="dni" class="form-label">DNI del Cliente</label>
                <input type="text" class="form-control" id="dni" name="dni" value="${param.dni}" required pattern="\\d+" placeholder="Ej: 30123456">

            </div>

            <button type="submit" class="btn btn-warning text-white">
                <i class="bi bi-key-fill me-2"></i>Blanquear Contraseña
            </button>
            	        <div class="mb-3 row">
			    <div class="col-sm-12 text-center">
			        <a href="ABMLClientes.jsp" class="btn btn-secondary">
			            <i class="bi-box-arrow-left"></i> Volver
			        </a>
			    </div>
			</div>
        </form>

    </div>
</main>

<!-- PIE -->
<%@ include file="../../../Componentes/footer.jspf" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
