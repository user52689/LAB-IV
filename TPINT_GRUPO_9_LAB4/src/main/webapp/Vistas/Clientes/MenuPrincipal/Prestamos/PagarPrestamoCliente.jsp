<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.text.NumberFormat, java.util.*, java.util.Locale" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Pagar Préstamo - MiBanco</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet" />
</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="../../../Componentes/header.jspf" %>

<main class="flex-grow-1 bg-light p-4">
    <div class="container">
        <h2 class="mb-4 text-center">Pagar Préstamo</h2>

        <%
            class PrestamoSimulado {
                int id;
                String descripcion;
                double montoPendiente;

                PrestamoSimulado(int id, String descripcion, double montoPendiente) {
                    this.id = id;
                    this.descripcion = descripcion;
                    this.montoPendiente = montoPendiente;
                }
            }

            class CuentaSimulada {
                int id;
                String numero;
                double saldo;

                CuentaSimulada(int id, String numero, double saldo) {
                    this.id = id;
                    this.numero = numero;
                    this.saldo = saldo;
                }
            }

            List<PrestamoSimulado> prestamos = new ArrayList<>();
            prestamos.add(new PrestamoSimulado(1, "Crédito Personal 2024", 75000.00));
            prestamos.add(new PrestamoSimulado(2, "Préstamo Auto", 45000.00));

            List<CuentaSimulada> cuentas = new ArrayList<>();
            cuentas.add(new CuentaSimulada(1, "123-456789", 100000.00));
            cuentas.add(new CuentaSimulada(2, "123-987654", 25000.00));

            NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(new Locale("es", "AR"));
        %>

        <form action="PagarPrestamoServlet" method="post" class="w-50 mx-auto">

            <div class="mb-3">
                <label for="idPrestamo" class="form-label">Seleccioná el préstamo a pagar</label>
                <select name="idPrestamo" id="idPrestamo" class="form-select" required onchange="actualizarMonto()">
                    <option value="" disabled selected>Elegí un préstamo</option>
                    <%
                        for (PrestamoSimulado p : prestamos) {
                    %>
                        <option value="<%= p.id %>" data-monto="<%= p.montoPendiente %>">
                            <%= p.descripcion %> - Pendiente: <%= formatoMoneda.format(p.montoPendiente) %>
                        </option>
                    <%
                        }
                    %>
                </select>
            </div>

            <div class="mb-3">
                <label class="form-label">Monto a pagar</label>
                <input type="text" class="form-control" id="montoDisplay" value="" disabled />
                <input type="hidden" name="monto" id="montoHidden" value="" />
            </div>

            <div class="mb-3">
                <label for="cuentaOrigen" class="form-label">Cuenta de origen</label>
                <select name="idCuenta" id="cuentaOrigen" class="form-select" required>
                    <option value="" disabled selected>Elegí una cuenta</option>
                    <%
                        for (CuentaSimulada cuenta : cuentas) {
                    %>
                        <option value="<%= cuenta.id %>">
                            <%= cuenta.numero %> - Saldo: <%= formatoMoneda.format(cuenta.saldo) %>
                        </option>
                    <%
                        }
                    %>
                </select>
            </div>

            <button type="submit" class="btn btn-success w-100">
                <i class="bi bi-cash-coin"></i> Pagar Préstamo
            </button>
	        <div class="mb-3 row mt-4">
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

<script>
    function actualizarMonto() {
        const select = document.getElementById("idPrestamo");
        const monto = select.options[select.selectedIndex].getAttribute("data-monto");
        const display = document.getElementById("montoDisplay");
        const hidden = document.getElementById("montoHidden");

        if (monto) {
            display.value = new Intl.NumberFormat("es-AR", { style: "currency", currency: "ARS" }).format(monto);
            hidden.value = monto;
        } else {
            display.value = "";
            hidden.value = "";
        }
    }
</script>

</body>
</html>
