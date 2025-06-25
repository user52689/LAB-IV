<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Listado de Usuarios - MiBanco</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet" />
</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="../../../Componentes/header.jspf" %>

<main class="flex-grow-1 bg-light p-4">
    <div class="container">

        <h2 class="mb-4 text-primary">
            <i class="bi bi-people-fill me-2"></i>Listado de Usuarios
        </h2>

        <!-- Formulario de búsqueda -->
        <form method="get" action="${pageContext.request.contextPath}/usuario/listar" class="row g-3 mb-4">
			<div class="col-md-4">
			    <input type="text" id="dniInput" name="dni" class="form-control" placeholder="Buscar por DNI"
			           value="${dni != null ? dni : ''}" />
			</div>

            <div class="col-md-2">
                <button type="submit" class="btn btn-primary">
                    <i class="bi bi-search"></i> Buscar
                </button>
            </div>
            <div class="col-md-2">
                <button type="submit" class="btn btn-secondary" name="mostrarTodos" value="true">
                    <i class="bi bi-arrow-clockwise"></i> Mostrar Todos
                </button>
            </div>
            <div class="col-md-2">
                <button type="submit" class="btn btn-outline-danger" name="limpiar" value="true">
                    <i class="bi bi-eraser-fill"></i> Limpiar
                </button>
            </div>
        </form>

        <!-- Mostrar tabla o mensaje -->
        <c:choose>
            <c:when test="${not empty listaUsuarios}">
                <div class="table-responsive">
                    <table class="table table-striped table-hover align-middle">
                        <thead class="table-dark">
						    <tr>
						        <th>ID</th>
						        <th>DNI</th>
						        <th>Usuario</th>
						        <th>Rol</th>
						        <th>Email<th>
						        <th>Fecha Creación</th>
						        <th>Último Acceso</th>
						        <th>Activo</th>
						        <th>Acciones</th>
						    </tr>
						</thead>
						<tbody>
						    <c:forEach var="usuario" items="${listaUsuarios}">
						        <tr>
						            <td>${usuario.idUsuario}</td>
						            <td>${usuario.dni}</td> 
						            <td>${usuario.nombreUsuario}</td>
						            <td>${usuario.rol}</td>
						            <td>${usuario.correoElectronico}</td>
						            <td>${usuario.fechaCreacion}</td>
						            <td><c:out value="${usuario.ultimoAcceso != null ? usuario.ultimoAcceso : 'Nunca'}"/></td>
						            <td>
						                <c:choose>
						                    <c:when test="${usuario.activo}">
						                        <span class="badge bg-success">Sí</span>
						                    </c:when>
						                    <c:otherwise>
						                        <span class="badge bg-danger">No</span>
						                    </c:otherwise>
						                </c:choose>
						            </td>
						            <td>
						                <a href="${pageContext.request.contextPath}/Vistas/Administrador/Usuarios/ModificacionUsuario.jsp?id=${usuario.idUsuario}" 
						                   class="btn btn-sm btn-warning" title="Modificar">
						                    <i class="bi bi-pencil-square"></i>
						                </a>
										<a href="${pageContext.request.contextPath}/usuario/baja?dni=${usuario.dni}" 
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
            <c:when test="${empty listaUsuarios and nombreUsuario != null and nombreUsuario != ''}">
                <div class="alert alert-warning mt-4" role="alert">
                    <i class="bi bi-exclamation-triangle-fill me-2"></i>
                    No se encontró ningún usuario con nombre '${nombreUsuario}'.
                </div>
            </c:when>
        </c:choose>

        <div class="mb-3 row">
            <div class="col-sm-12 text-center">
                <a href="${pageContext.request.contextPath}/Vistas/Administrador/MenuPrincipal/Usuarios/ABMLUsuarios.jsp" class="btn btn-secondary">
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
