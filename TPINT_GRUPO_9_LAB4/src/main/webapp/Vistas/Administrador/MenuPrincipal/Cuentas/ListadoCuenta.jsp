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

        <h2 class="mb-4 text-primary">
            <i class="bi bi-list-ul me-2"></i>Listado de Cuentas Bancarias
        </h2>

        <!-- Formulario de búsqueda por DNI -->
        <form method="post" action="${pageContext.request.contextPath}/Cuentas/listar" class="row g-3 mb-4">
            <div class="col-md-4">
                <input type="text" id="numeroCuenta" name="numeroCuenta" class="form-control" placeholder="Buscar por Número de Cuenta"
                       value="${nroCuenta != null ? nroCuenta : ''}" />
            </div>
            <div class="col-md-2">
                <button type="submit" class="btn btn-primary" name="accion" value="buscar">
                    <i class="bi bi-search"></i> Buscar
                </button>
            </div>
            <div class="col-md-2">
                <button type="submit" class="btn btn-secondary" name="accion" value="todos">
                    <i class="bi bi-arrow-clockwise"></i> Mostrar Todos
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
                                        <a href="${pageContext.request.contextPath}/Cuentas/modificar?numeroCuenta=${cuenta.numeroCuenta}" 
                                           class="btn btn-sm btn-warning text-white" title="Modificar">
                                            <i class="bi bi-pencil-square"></i>
                                        </a>
                                        <a href="${pageContext.request.contextPath}/Cuentas/baja?dni=${cuenta.cliente.dni}" 
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
			<c:otherwise>
			    <div class="alert alert-warning mt-4" role="alert">
                    <i class="bi bi-exclamation-triangle-fill me-2"></i>
                    No se encontró ningún DNI que coincida con '${dni}'.
                </div>
			</c:otherwise>
		</c:choose>
		<div class="d-flex justify-content-between align-items-center">
	
		    <div>
		        <c:choose>
		            <c:when test="${paginaActual > 1}">
		                <a class="btn btn-secondary" href="?pagina=${paginaActual - 1}&dni=${dni}">
		                    <i class="bi bi-arrow-left"></i> Anterior
		                </a>
		            </c:when>
		            <c:otherwise>
		                <button class="btn btn-secondary invisible">
		                    <i class="bi bi-arrow-left"></i> Anterior
		                </button>
		            </c:otherwise>
		        </c:choose>
		    </div>
		
		    <div class="d-flex gap-1">
		        <c:forEach var="pagina" begin="1" end="${totalPaginas}">
		            <a class="btn btn-sm ${pagina == paginaActual ? 'btn-secondary' : 'btn-outline-secondary'}"
		               href="?pagina=${pagina}&dni=${dni}">
		                ${pagina}
		            </a>
		        </c:forEach>
		    </div>
		
		    <div>
		        <c:choose>
		            <c:when test="${paginaActual < totalPaginas}">
		                <a class="btn btn-secondary" href="?pagina=${paginaActual + 1}&dni=${dni}">
		                    Siguiente <i class="bi bi-arrow-right"></i>
		                </a>
		            </c:when>
		            <c:otherwise>
		                <button class="btn btn-secondary invisible">
		                    Siguiente <i class="bi bi-arrow-right"></i>
		                </button>
		            </c:otherwise>
		        </c:choose>
		    </div>
		
		</div>
		<div class="mb-3 row mt-4">
			<div class="col-sm-12 text-center">
			    <a href="${pageContext.request.contextPath}/Vistas/Administrador/MenuPrincipal/Cuentas/ABMLCuentas.jsp" class="btn btn-secondary">
			    	<i class="bi-box-arrow-left"></i> Volver
				</a>
			</div>
		</div>
	</div>
</main>

<%@ include file="../../../Componentes/footer.jspf" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
