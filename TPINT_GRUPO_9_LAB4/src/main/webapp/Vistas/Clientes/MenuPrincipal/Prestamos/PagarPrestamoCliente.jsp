<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Pagar Cuota - MiBanco</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet" />
</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="../../../Componentes/header.jspf" %>

<main class="flex-grow-1 bg-light p-4">
    <div class="container">
        <h2 class="mb-4 text-center">Pagar Cuota</h2>

        <!-- Mostrar mensajes de éxito o error -->
        <c:if test="${not empty mensajeExito}">
            <div class="alert alert-success" role="alert">
                <i class="bi bi-check-circle-fill me-2"></i>${mensajeExito}
            </div>
        </c:if>
        <c:if test="${not empty mensajeError}">
            <div class="alert alert-danger" role="alert">
                <i class="bi bi-exclamation-triangle-fill me-2"></i>${mensajeError}
            </c:if>

        <c:choose>
            <c:when test="${empty cuota}">
                <div class="alert alert-info">No hay cuotas pendientes para este préstamo.</div>
                <div class="mb-3 row">
                    <div class="col-sm-12 text-center">
                        <a href="<%=request.getContextPath()%>/Prestamos/listar" class="btn btn-secondary">
                            <i class="bi-box-arrow-left"></i> Volver
                        </a>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <form action="<%=request.getContextPath()%>/Prestamos/pagar" method="post" class="w-50 mx-auto">
                    <input type="hidden" name="idCuota" value="${cuota.idCuota}" />
                    <input type="hidden" name="idPrestamo" value="${cuota.prestamo.idPrestamo}" />

                    <div class="mb-3">
                        <label for="idPrestamo" class="form-label">Préstamo</label>
                        <select name="idPrestamoDisplay" id="idPrestamo" class="form-select" disabled>
                            <option value="${cuota.prestamo.idPrestamo}" 
                                    data-monto="${cuota.montoCuota}" 
                                    data-cuota="${cuota.numeroCuota}" 
                                    data-total="${totalCuotas}">
                                Préstamo ${cuota.prestamo.idPrestamo} - Pendiente: 
                                <fmt:formatNumber value="${cuota.prestamo.saldoPendiente}" type="currency" currencySymbol="$" />
                            </option>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Monto cuota a pagar</label>
                        <div class="position-relative">
                            <input type="text" class="form-control" id="montoDisplay" 
                                   value="<fmt:formatNumber value='${cuota.montoCuota}' type='currency' currencySymbol='$' />" disabled />
                            <span class="position-absolute top-50 end-0 translate-middle-y me-3 text-muted small opacity-75" id="cuotaInfo">
                                Cuota ${cuota.numeroCuota} de ${totalCuotas}
                            </span>
                            <input type="hidden" name="monto" id="montoHidden" value="${cuota.montoCuota}" />
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="cuentaOrigen" class="form-label">Cuenta de origen</label>
                        <select name="idCuenta" id="cuentaOrigen" class="form-select" required>
                            <option value="" disabled selected>Elegí una cuenta</option>
                            <c:forEach var="cuenta" items="${cuentas}">
                                <option value="${cuenta.idCuenta}">
                                    ${cuenta.numeroCuenta} - Saldo: 
                                    <fmt:formatNumber value="${cuenta.saldo}" type="currency" currencySymbol="$" />
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-success w-100">
                        <i class="bi bi-cash-coin"></i> Pagar Cuota
                    </button>
                </form>
            </c:otherwise>
        </c:choose>

        <div class="mb-3 row mt-4">
            <div class="col-sm-12 text-center">
                <a href="<%=request.getContextPath()%>/Prestamos/listar" class="btn btn-secondary">
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