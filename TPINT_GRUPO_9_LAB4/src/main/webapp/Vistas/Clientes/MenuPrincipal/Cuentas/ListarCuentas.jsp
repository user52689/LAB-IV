<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, java.text.SimpleDateFormat" %>
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

        <%
            // Simulo lista de cuentas del cliente
            class CuentaSimulada {
                String numeroCuenta;
                String tipoCuenta;
                double saldo;
                Date fechaCreacion;
                
                CuentaSimulada(String numeroCuenta, String tipoCuenta, double saldo, Date fechaCreacion) {
                    this.numeroCuenta = numeroCuenta;
                    this.tipoCuenta = tipoCuenta;
                    this.saldo = saldo;
                    this.fechaCreacion = fechaCreacion; 
                }
            }

            List<CuentaSimulada> cuentas = new ArrayList<>();
            cuentas.add(new CuentaSimulada("123-456789", "Caja de Ahorro", 25000.50, new GregorianCalendar(2023, Calendar.JANUARY, 15).getTime()));
            cuentas.add(new CuentaSimulada("987-654321", "Cuenta Corriente", 5000.00, new GregorianCalendar(2024, Calendar.MARCH, 20).getTime()));

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        %>

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
                <%
                    if (cuentas.isEmpty()) {
                %>
                    <tr>
                        <td colspan="4">No tenés cuentas registradas.</td>
                    </tr>
                <%
                    } else {
                        for (CuentaSimulada cuenta : cuentas) {
                %>
                    <tr>
                        <td><%= cuenta.numeroCuenta %></td>
                        <td><%= cuenta.tipoCuenta %></td>
                        <td>$<%= String.format("%,.2f", cuenta.saldo) %></td>
                        <td><%= sdf.format(cuenta.fechaCreacion) %></td>
                    </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>


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
