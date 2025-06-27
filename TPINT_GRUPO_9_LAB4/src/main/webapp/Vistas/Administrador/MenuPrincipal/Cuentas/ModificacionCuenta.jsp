<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modificacion Cuenta - Mi Banco</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet" />
</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="../../../Componentes/header.jspf" %>

<main class="flex-grow-1 bg-light p-4">
    <div class="container">
        <form action="${pageContext.request.contextPath}/Cuentas/modificar" method="post" class="w-50 mx-auto">

            <h2 class="mb-4 text-warning">
                <i class="bi bi-pencil-fill me-2"></i>Modificación de Cuenta
            </h2>

            <!-- Campo oculto para enviar el número de cuenta -->
            <input type="hidden" name="numeroCuenta" value="${numeroCuenta}" />

            <div class="mb-3">
                <label for="numeroCuenta" class="form-label">Número de Cuenta</label>
                <input type="text" id="numeroCuenta" class="form-control" value="${numeroCuenta}" disabled />
            </div>

            <div class="mb-3">
                <label for="tipoCuenta" class="form-label">Tipo de Cuenta</label>
                <select name="tipoCuenta" id="tipoCuenta" class="form-select" required>
                    <option value="" disabled selected>Elegí un tipo</option>
                    <c:forEach var="tc" items="${tiposCuenta}">
                        <option value="${tc.idTipoCuenta}"
                            <c:if test="${cuenta.tipoCuenta.idTipoCuenta == tc.idTipoCuenta}">selected</c:if>>
                            ${tc.descripcion}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="mb-3">
                <label for="saldoInicial" class="form-label">Saldo</label>
                <input type="text" id="saldoInicial" class="form-control" value="${saldo}" disabled />
            </div>

            <button type="submit" class="btn btn-warning w-100">Modificar Cuenta</button>

            <div class="mb-3 row mt-4">
                <div class="col-sm-12 text-center">
                    <a href="${pageContext.request.contextPath}/Vistas/Administrador/MenuPrincipal/Cuentas/ABMLCuentas.jsp" class="btn btn-secondary">
                        <i class="bi-box-arrow-left"></i> Volver
                    </a>
                </div>
            </div>

        </form>
    </div>
</main>

<%@ include file="../../../Componentes/footer.jspf" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
