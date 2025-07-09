<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Listado de Clientes - MiBanco</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet" />
</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="../../../Componentes/header.jspf" %>

<main class="flex-grow-1 bg-light p-4">
    <div class="container">

        <h2 class="mb-4 text-primary">
            <i class="bi bi-people-fill me-2"></i>Listado de Clientes
        </h2>

        <!-- FORMULARIO DE BUSQUEDA Y FILTROS -->
        <form method="post" action="${pageContext.request.contextPath}/cliente/listar" class="row g-3 mb-4">
        
        	<div class="row">
        	
	            <div class="col-md-3">
	            	<label class="form-label" for="dni">DNI:</label>
	                <input type="text" id="dni" name="dni" class="form-control" placeholder="Buscar por DNI"
	                       value="${dni != null ? dni : ''}" />
	            </div>
	            
	            <div class="col-md-3">
	            	<label class="form-label" for="nombreApellido">Nombre:</label>
	                <input type="text" id="nombreApellido" name="nombreApellido" class="form-control" placeholder="Buscar por Nombre Completo"
	                       value="${nombreApellido != null ? nombreApellido : ''}" />
	            </div>
	            
	            <div class="col-md-3">
	            	<label class="form-label" for="provincia">Provincia:</label>
				    <select id="provincia" name="provincia" class="form-select">
				        <option value="">Todas las provincias</option>
				        <c:forEach var="p" items="${listaProvincias}">
				        	<option value="${p.idProvincia}" ${provincia == p.idProvincia ? 'selected' : ''} >${p.nombreProvincia}</option>
				        </c:forEach>
				    </select>
				</div>
	            
	            <div class="col-md-3">
	            	<label class="form-label" for="orden">Orden:</label>
				    <select id="orden" name="orden" class="form-select">
				        <option value="">Ordenar por...</option>
				        <option value="asc" ${orden == 'asc' ? 'selected' : ''}>Apellido: A-Z</option>
				        <option value="desc" ${orden == 'desc' ? 'selected' : ''}>Apellido: Z-A</option>
				    </select>
				</div>
				
			</div>
			<div class="row">
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
            </div>
            
        </form>
        <!-- fin DE FORMULARIO DE BUSQUEDA Y FILTROS -->
		<hr>
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
        <!-- fin BOTONES DE PAGINACIÓN SUPERIOR -->
		
        <!-- LISTADO DE CLIENTES -->
        <c:choose>
            <c:when test="${not empty listaClientes}">
                <div class="table-responsive">
                    <table class="table table-striped table-hover align-middle">
                        <thead class="table-dark">
                            <tr>
                                <th>ID</th>
                                <th>Nombre y Apellido</th>
                                <th>DNI</th>
                                <th>CUIL</th>
                                <th>Email</th>
                                <th>Teléfono</th>
                                <th>Usuario</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="cliente" items="${listaClientes}">
                                <tr>
                                    <td>${cliente.idCliente}</td>
                                    <td>${cliente.apellido.toUpperCase()}, ${cliente.nombre}</td>
                                    <td>${cliente.dni}</td>
                                    <td>${cliente.cuil}</td>
                                    <td>${cliente.correoElectronico}</td>
                                    <td>${cliente.telefono}</td>
                                    <td>${cliente.usuario}</td>
									<td>
									    <a href="${pageContext.request.contextPath}/cliente/modificar?context=baja&dni=${cliente.dni}" 
									       class="btn btn-sm btn-warning" title="Modificar">
									        <i class="bi bi-pencil-square"></i>
									    </a>
									    
									    <a href="${pageContext.request.contextPath}/cliente/baja?context=baja&dni=${cliente.dni}" 
									       class="btn btn-sm btn-danger" title="Dar de baja">
									        <i class="bi bi-person-dash-fill"></i>
									    </a>									    
										<a href="${pageContext.request.contextPath}/cliente/blanqueo?dni=${cliente.dni}" 
										   class="btn btn-sm btn-info text-white" title="Blanquear contraseña">
										    <i class="bi bi-key-fill"></i>
										</a>

									</td>


                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div> 
            </c:when>
            <c:otherwise>
				<c:choose>
				    <c:when test="${error != null}">
				        <div class="alert alert-danger">${error}</div>
				    </c:when>
				    <c:otherwise>
				        <div class="alert alert-warning">No se encontraron coincidencias.</div>
				    </c:otherwise>
				</c:choose>
            </c:otherwise>
            
        </c:choose>
        <!-- fin LISTADO DE CLIENTES -->
		
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
		
        <div class="mb-3 row mt-4">
            <div class="col-sm-12 text-center">
                <a href="${pageContext.request.contextPath}/Vistas/Administrador/MenuPrincipal/Clientes/ABMLClientes.jsp" class="btn btn-secondary">
                    <i class="bi bi-box-arrow-left"></i> Volver
                </a>
            </div>
        </div>
    </div>
</main>

<%@ include file="../../../Componentes/footer.jspf" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>