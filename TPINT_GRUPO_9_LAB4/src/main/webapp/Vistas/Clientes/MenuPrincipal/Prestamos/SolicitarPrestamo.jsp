<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <title>Solicitar Préstamo - MiBanco</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet" />
</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="../../../Componentes/header.jspf" %>

<main class="flex-grow-1 bg-light p-4">
    <div class="container">
        <h2 class="mb-4">Solicitar Préstamo</h2>

        <form action="<%=request.getContextPath()%>/SolicitarPrestamoServlet" method="post" class="w-50 mx-auto">

            <!-- Observaciones -->
            <div class="mb-3">
                <label for="observaciones" class="form-label">Descripción del Préstamo</label>
                <input type="text" name="observaciones" id="observaciones" class="form-control" required
                       placeholder="Ej: Préstamo personal, préstamo para auto" maxlength="100" />
            </div>

            <!-- Monto -->
            <div class="mb-3">
                <label for="importeSolicitado" class="form-label">Monto Solicitado</label>
                <input type="number" name="importeSolicitado" id="importeSolicitado" class="form-control" required min="1000" step="100"
                       placeholder="Ej: 50000" />
                <small class="text-muted">Mínimo $1.000. Ingresá el monto en pesos.</small>
            </div>

            <!-- Cuotas -->
            <div class="mb-3">
                <label for="plazoMeses" class="form-label">Cantidad de Cuotas</label> 
                <input type="number" name="plazoMeses" id="plazoMeses" class="form-control" required min="1" max="60"
                       placeholder="Ej: 12" />
                <small class="text-muted">Máximo 60 cuotas. Elegí en cuántos pagos querés devolverlo.</small>
            </div>

            <!-- Selección de cuenta para depósito -->
            <div class="mb-3">
                <label for="idCuentaDeposito" class="form-label">Cuenta para Depósito</label>
                <select name="idCuentaDeposito" id="idCuentaDeposito" class="form-select" required>
                    <option value="" disabled selected>Seleccioná una cuenta</option>
                    <option value="101">Caja de Ahorro - 0112345678901234567890</option>
                    <option value="102">Cuenta Corriente - 0112345678901234567891</option>
                    <!-- Reemplazar con IDs reales de cuentas del cliente -->
                </select>
            </div>

            <!-- Botón de envío -->
            <button type="submit" class="btn btn-primary w-100">Enviar Solicitud</button>

            <div class="mb-3 row mt-4">
                <div class="col-sm-12 text-center">
                    <a href="MenuPrestamos.jsp" class="btn btn-secondary">
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

