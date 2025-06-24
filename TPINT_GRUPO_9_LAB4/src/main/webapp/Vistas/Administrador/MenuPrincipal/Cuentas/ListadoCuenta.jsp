<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.text.NumberFormat, java.util.*, java.util.Locale" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <title>Listado de Cuentas - Administrador - MiBanco</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet" />
</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="../../../Componentes/header.jspf" %>

<main class="flex-grow-1 bg-light p-4">
    <div class="container">

        <h2 class="mb-4 text-center">Listado de Cuentas Bancarias</h2>

        <!-- Formulario de búsqueda por DNI -->
        <form method="get" action="${pageContext.request.contextPath}/CuentaListarServlet" class="row g-3 mb-4">
            <div class="input-group w-50 mx-auto">
                <input type="text" name="dni" class="form-control" placeholder="Buscar por DNI" value="<%= request.getParameter("dni") != null ? request.getParameter("dni") : "" %>" />
                <button type="submit" class="btn btn-primary">
                    <i class="bi bi-search"></i> Buscar
                </button>
            </div>
        </form>

        <c:choose>
	        <c:when test="${not empty listaCuentas}">
				<div class="table-responsive">
			        <table class="table table-striped table-bordered">
			            <thead class="table-primary">
			                <tr>
			                    <th>ID Cuenta</th>
			                    <th>Número de Cuenta</th>
			                    <th>CBU</th>
			                    <th>Tipo de Cuenta</th>
			                    <th>Saldo</th>
			                    <th>DNI Cliente</th>
			                    <th>Nombre Cliente</th>
			                    <th>Acciones</th>
			                </tr>
			            </thead>
			            <tbody>
			            	<c:forEach var="cuenta" items="${listaCuentas}">
                                <tr>
                                    <td>${cuenta.idCuenta}</td>
                                    <td>${cuenta.numeroCuenta}</td>
                                    <td>${cuenta.cbu}</td>
                                    <td>${cuenta.tipoCuenta.descripcion}</td>
                                    <td>${cuenta.saldo}</td>
                                    <td>${cuenta.cliente.dni}</td>
                                    <td>${cuenta.cliente.nombre}</td>
                                    <td>
                                        <a href="" 
                                           class="btn btn-sm btn-warning" title="Modificar">
                                            <i class="bi bi-pencil-square"></i>
                                        </a>
                                        <a href="" 
                                           class="btn btn-sm btn-danger" title="Dar de baja">
                                            <i class="bi bi-person-dash-fill"></i>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
			            </tbody>
			        </table>
				</div>
			</c:when>
		</c:choose>
		<div class="mb-3 row">
			<div class="d-flex justify-content-between mb-3">
				<div>
					<c:if test="${paginaActual > 1}">
						<a class="btn btn-secondary" href="CuentaListarServlet?pagina=${paginaActual - 1}"><i class="bi bi-arrow-left"></i> Anterior</a>
					</c:if>
				</div>
				
				<div>
					<c:if test="${paginaActual < totalPaginas}">
						<a class="btn btn-secondary" href="CuentaListarServlet?pagina=${paginaActual + 1}">Siguiente <i class="bi bi-arrow-right"></i></a>
					</c:if>
				</div>
				
			</div>
		
	        <div class="mb-3 row">
			    <div class="col-sm-12 text-center">
			        <a href="${pageContext.request.contextPath}/Vistas/Administrador/MenuPrincipal/Cuentas/ABMLCuentas.jsp" class="btn btn-secondary">
			            <i class="bi-box-arrow-left"></i> Volver
			        </a>
			    </div>
			</div>
    	</div>
    	
    </div>
</main>

<%@ include file="../../../Componentes/footer.jspf" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
