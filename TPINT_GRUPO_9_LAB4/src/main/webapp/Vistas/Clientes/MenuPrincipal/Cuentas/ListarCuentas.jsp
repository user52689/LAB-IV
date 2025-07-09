<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <title>Mis Cuentas - MiBanco</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet" />
</head>
<body class="d-flex flex-column min-vh-100">
    <%@ include file="../../../Componentes/header.jspf" %>

    <main class="flex-grow-1 bg-light p-4">
        <div class="container w-75 mx-auto">
            <h2 class="mb-4 text-center">Mis Cuentas</h2>

            <!-- Mostrar mensajes de éxito o error -->
            <c:if test="${not empty mensajeExito}">
                <div class="alert alert-success" role="alert">
                    <i class="bi bi-check-circle-fill me-2"></i>${mensajeExito}
                </div>
            </c:if>
            <c:if test="${not empty mensajeError}">
                <div class="alert alert-danger" role="alert">
                    <i class="bi bi-exclamation-triangle-fill me-2"></i>${mensajeError}
                </div>
            </c:if>

            <!-- Tabla de cuentas -->
            <table class="table table-striped table-hover text-center align-middle">
                <thead class="table-dark">
                    <tr>
                        <th>Número de Cuenta</th>
                        <th>Tipo</th>
                        <th>Saldo</th>
                        <th>Fecha de Creación</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${empty cuentas}">
                            <tr>
                                <td colspan="4">No tenés cuentas registradas.</td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="cuenta" items="${cuentas}">
                                <tr>
                                    <td>${cuenta.numeroCuenta}</td>
                                    <td>${cuenta.tipoCuenta.descripcion}</td>
                                    <td>
                                        <fmt:formatNumber value="${cuenta.saldo}" type="currency" currencySymbol="$" />
                                    </td>
                                    <td>
                                        <fmt:parseDate value="${cuenta.fechaCreacion}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDate" type="both" />
                                        <fmt:formatDate value="${parsedDate}" pattern="dd/MM/yyyy" />
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>

            <!-- Botón Volver -->
            <div class="mb-3 row">
                <div class="col-sm-12 text-center">
                    <a href="<%=request.getContextPath()%>/Vistas/Clientes/MenuPrincipal/Cuentas/MenuMovimientosCuentas.jsp" class="btn btn-secondary">
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