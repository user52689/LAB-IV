<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, java.text.SimpleDateFormat" %>
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

        <%
            // Simulo estructura de cuentas y movimientos
            class Movimiento {
                Date fecha;
                String descripcion;
                String tipo; // "credito" o "debito"
                double monto;
                Movimiento(Date fecha, String descripcion, String tipo, double monto) {
                    this.fecha = fecha;
                    this.descripcion = descripcion;
                    this.tipo = tipo;
                    this.monto = monto;
                }
            }

            class CuentaConMovimientos {
                String numeroCuenta;
                String tipoCuenta;
                List<Movimiento> movimientos;
                CuentaConMovimientos(String numeroCuenta, String tipoCuenta, List<Movimiento> movimientos) {
                    this.numeroCuenta = numeroCuenta;
                    this.tipoCuenta = tipoCuenta;
                    this.movimientos = movimientos;
                }
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            List<CuentaConMovimientos> cuentas = new ArrayList<>();

            List<Movimiento> movs1 = Arrays.asList(
                new Movimiento(new GregorianCalendar(2025, Calendar.JANUARY, 5).getTime(), "Depósito sueldo", "credito", 50000.00),
                new Movimiento(new GregorianCalendar(2025, Calendar.JANUARY, 10).getTime(), "Pago supermercado", "debito", 15000.00),
                new Movimiento(new GregorianCalendar(2025, Calendar.JANUARY, 15).getTime(), "Transferencia recibida", "credito", 12000.00)
            );

            List<Movimiento> movs2 = Arrays.asList(
                new Movimiento(new GregorianCalendar(2025, Calendar.FEBRUARY, 1).getTime(), "Pago tarjeta", "debito", 20000.00),
                new Movimiento(new GregorianCalendar(2025, Calendar.FEBRUARY, 10).getTime(), "Intereses ganados", "credito", 3000.00)
            );

            cuentas.add(new CuentaConMovimientos("123-456789", "Caja de Ahorro", movs1));
            cuentas.add(new CuentaConMovimientos("987-654321", "Cuenta Corriente", movs2));

            if (cuentas.isEmpty()) {
        %>
            <p class="text-center">No hay movimientos para mostrar.</p>
        <%
            } else {
                for (CuentaConMovimientos cuenta : cuentas) {
        %>
            <div class="card mb-4 shadow-sm">
                <div class="card-header bg-primary text-white">
                    <h5 class="mb-0">Cuenta: <%= cuenta.numeroCuenta %> (<%= cuenta.tipoCuenta %>)</h5>
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
                            <%
                                for (Movimiento mov : cuenta.movimientos) {
                            %>
                                <tr>
                                    <td><%= sdf.format(mov.fecha) %></td>
                                    <td><%= mov.descripcion %></td>
                                    <td class="<%= mov.tipo.equals("credito") ? "credito" : "debito" %>">
                                        <%= mov.tipo.equals("credito") ? "Crédito" : "Débito" %>
                                    </td>
                                    <td class="<%= mov.tipo.equals("credito") ? "credito" : "debito" %>">
                                        $<%= String.format("%,.2f", mov.monto) %>
                                    </td>
                                </tr>
                            <%
                                }
                            %>
                        </tbody>
                    </table>
                </div>
            </div>
        <%
                }
            }
        %>

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
