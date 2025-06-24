<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <title>Alta de Cuenta - MiBanco</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet" />
</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="../../../Componentes/header.jspf" %>

<main class="flex-grow-1 bg-light p-4">
    <div class="container">
        <h2 class="mb-4">Alta de Cuenta</h2>

        <form action="AltaCuentaServlet" method="post" class="w-50 mx-auto">

            <div class="mb-3">
                <label for="dniCliente" class="form-label">DNI del Cliente</label>
                <input type="text" name="dniCliente" id="dniCliente" class="form-control" placeholder="Ingresá DNI" required />
            </div>

            <div class="mb-3">
                <label for="tipoCuenta" class="form-label">Tipo de Cuenta</label>
                <select name="tipoCuenta" id="tipoCuenta" class="form-select" required>
                    <option value="" disabled selected>Elegí un tipo</option>
                    <option value="Caja de Ahorro">Caja de Ahorro</option>
                    <option value="Cuenta Corriente">Cuenta Corriente</option>
                </select>
            </div>

            <div class="mb-3">
                <label for="saldoInicial" class="form-label">Saldo Inicial</label>
                <input type="text" id="saldoInicial" class="form-control" value="$10.000" disabled />
                <input type="hidden" name="saldoInicial" value="10000" />
                <small class="text-muted">El saldo inicial es fijo y asignado automáticamente.</small>
            </div>

            <button type="submit" class="btn btn-primary w-100">Crear Cuenta</button>
            
	        <div class="mb-3 row">
			    <div class="col-sm-12 text-center">
			        <a href="../MenuPrincipal/MenuPrincipalCliente.jsp" class="btn btn-secondary">
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
