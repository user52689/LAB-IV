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

        <!-- FORMULARIO DE BUSQUEDA Y FILTROS -->
        <form method="post" action="${pageContext.request.contextPath}/Cuentas/listar" class="row g-3 mb-3">
        
            <div class="col-md-3">
            	<label class="form-label" for="numeroCuenta">Número de Cuenta:</label>
                <input type="text" id="numeroCuenta" name="numeroCuenta" class="form-control" placeholder="Buscar por Número de Cuenta"
                       value="${numeroCuenta != null ? numeroCuenta  : ''}" />
            </div>
            
            <div class="col-md-3">
            	<label class="form-label" for="nombreCliente">Nombre Cliente:</label>
			    <input type="text" id="nombreCliente" name="nombreCliente" class="form-control" placeholder="Buscar por Nombre del Cliente"
			           value="${nombreCliente != null ? nombreCliente : ''}" />
			</div>
			
			<!-- div class="col-md-3">
			    <label class="form-label" for="dni">DNI Cliente:</label>
			    <input type="text" id="dni" name="dni" class="form-control" placeholder="Buscar por DNI"
			           value="${dni != null ? dni : ''}" />
			</div> -->
            
            <div class="col-md-3">
            	<label class="form-label" for="orden">Orden:</label>
			    <select name="orden" class="form-select">
			        <option value="">Ordenar por...</option>
			        <option value="asc" ${orden == 'asc' ? 'selected' : ''}>Saldo: menor a mayor</option>
			        <option value="desc" ${orden == 'desc' ? 'selected' : ''}>Saldo: mayor a menor</option>
			    </select>
			</div>
			
			<div class="col-md-3">
            	<label class="form-label" for="tipoCuenta">Tipo de Cuenta:</label>
			    <select name="tipoCuenta" class="form-select">
			        <option value="">Todos los tipos</option>
			        <c:forEach var="tc" items="${listaTiposCuentas}">
			        	<option value="${tc.idTipoCuenta}" ${tipoCuenta == tc.idTipoCuenta ? 'selected' : ''} >${tc.descripcion}</option>
			        </c:forEach>
			    </select>
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
        <!-- fin DE FORMULARIO DE BUSQUEDA Y FILTROS -->
        
        <!-- BOTONES DE PAGINACIÓN SUPERIOR -->
		<div class="d-flex justify-content-between align-items-center mb-2">
		    <div>
		        <c:choose>
		            <c:when test="${paginaActual > 1}">
		                <a class="btn btn-secondary" href="?pagina=${paginaActual - 1}${queryParams}">
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
					   href="?pagina=${pagina}${queryParams}">
					   ${pagina}
					</a>
		        </c:forEach>
		    </div>
		
		    <div>
		        <c:choose>
		            <c:when test="${paginaActual < totalPaginas}">
		                <a class="btn btn-secondary" href="?pagina=${paginaActual + 1}${queryParams}">
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
        <!-- fin DE BOTONES DE PAGINACIÓN SUPERIOR -->

		<!-- LISTADO DE CUENTAS -->
        <c:choose>
	        <c:when test="${not empty listaCuentas}">
        	<!-- TABLA DE LISTADO -->
				<div class="table-responsive">
			        <table class="table table-striped table-hover align-middle">
			            <thead class="table-dark">
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
                                    <td>${cuenta.cliente.apellido.toUpperCase()}, ${cuenta.cliente.nombre}</td>
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
        	<!-- fin TABLA DE LISTADO -->
			</c:when>
        	
			<c:otherwise>
			<!-- ERRORES LISTADO -->
				<c:choose>
				    <c:when test="${error != null}">
				        <div class="alert alert-danger">${error}</div>
				    </c:when>
				    <c:otherwise>
				        <div class="alert alert-warning">No se encontraron coincidencias.</div>
				    </c:otherwise>
				</c:choose>
			<!-- fin ERRORES LISTADO -->
			</c:otherwise>
		</c:choose>
		<!-- fin DE LISTADO DE CUENTAS -->
		
        <!-- BOTONES DE PAGINACIÓN INFERIOR -->
		<div class="d-flex justify-content-between align-items-center">
		    <div>
		        <c:choose>
		            <c:when test="${paginaActual > 1}">
		                <a class="btn btn-secondary" href="?pagina=${paginaActual - 1}${queryParams}">
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
					   href="?pagina=${pagina}${queryParams}">
					   ${pagina}
					</a>
		        </c:forEach>
		    </div>
		
		    <div>
		        <c:choose>
		            <c:when test="${paginaActual < totalPaginas}">
		                <a class="btn btn-secondary" href="?pagina=${paginaActual + 1}${queryParams}">
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
        <!-- fin BOTONES DE PAGINACIÓN INFERIOR -->
		
		<!-- BOTÓN VOLVER -->
		<div class="mb-3 row mt-4">
			<div class="col-sm-12 text-center">
			    <a href="${pageContext.request.contextPath}/Vistas/Administrador/MenuPrincipal/Cuentas/ABMLCuentas.jsp" class="btn btn-secondary">
			    	<i class="bi-box-arrow-left"></i> Volver
				</a>
			</div>
		</div>
		<!-- fin BOTÓN VOLVER -->
	</div>
</main>

<%@ include file="../../../Componentes/footer.jspf" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>