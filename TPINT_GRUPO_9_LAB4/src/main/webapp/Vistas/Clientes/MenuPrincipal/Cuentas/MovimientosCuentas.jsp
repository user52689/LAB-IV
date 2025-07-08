<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <title>Movimientos de Cuentas - MiBanco</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet" />
    <style>
        .credito { color: green; }
        .debito { color: red; }
    </style>
</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="../../../Componentes/header.jspf" %>

<main class="flex-grow-1 bg-light p-4">
    <div class="container w-75 mx-auto">
        <h2 class="mb-4 text-center">Movimientos de todas las cuentas</h2>

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

        <!-- Mostrar movimientos por cuenta -->
        <c:choose>
            <c:when test="${empty cuentasConMovimientos}">
                <p class="text-center">No hay movimientos para mostrar.</p>
            </c:when>
            <c:otherwise>
                <c:forEach var="entry" items="${cuentasConMovimientos}">
                    <c:if test="${not empty entry.value}">
                        <div class="card mb-4 shadow-sm">
                            <div class="card-header bg-primary text-white">
                                <h5 class="mb-0">Cuenta: ${entry.key.numeroCuenta} (${entry.key.tipoCuenta.descripcion})</h5>
                            </div>
                            <div class="card-body p-0">
                                <table class="table table-striped mb-0">
                                    <thead>
                                        <tr>
                                            <th>Fecha</th>
                                            <th>Descripción</th>
                                            <th>Tipo</th>
                                            <th>Monto</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="movimiento" items="${entry.value}">
                                            <c:set var="esCredito" value="${movimiento.tipoMovimiento.descripcion == 'Crédito' || movimiento.tipoMovimiento.descripcion == 'Transferencia Recibida'}" />
                                            <tr>
                                                <td>
                                                    <fmt:parseDate value="${movimiento.fechaMovimiento}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDate" type="both" />
                                                    <fmt:formatDate value="${parsedDate}" pattern="dd/MM/yyyy" />
                                                </td>
                                                <td>${movimiento.detalle}</td>
                                                <td class="${esCredito ? 'credito' : 'debito'}">
                                                    ${movimiento.tipoMovimiento.descripcion}
                                                </td>
                                                <td class="${esCredito ? 'credito' : 'debito'}">
                                                    <fmt:formatNumber value="${movimiento.importe}" type="currency" currencySymbol="$" />
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
            </c:otherwise>
        </c:choose>

        <!-- Botón Volver -->
        <div class="mb-3 row">
            <div class="col-sm-12 text-center">
                <a href="../MenuPrincipal/MenuCliente.jsp" class="btn btn-secondary">
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
