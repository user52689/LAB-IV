<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, java.util.ArrayList"%>

<%
    // Simulamos un cliente y cuentas para que la vista funcione sin servlet
    class Cliente {
        private String nombre, apellido, dni;
        public Cliente(String nombre, String apellido, String dni) {
            this.nombre = nombre; this.apellido = apellido; this.dni = dni;
        }
        public String getNombre() { return nombre; }
        public String getApellido() { return apellido; }
        public String getDni() { return dni; }
    }

    class Cuenta {
        private String numero, tipo, moneda;
        private double saldo;
        public Cuenta(String numero, String tipo, String moneda, double saldo) {
            this.numero = numero; this.tipo = tipo; this.moneda = moneda; this.saldo = saldo;
        }
        public String getNumero() { return numero; }
        public String getTipo() { return tipo; }
        public String getMoneda() { return moneda; }
        public double getSaldo() { return saldo; }
    }

    Cliente cliente = new Cliente("Juan", "Pérez", "12345678");
    List<Cuenta> cuentas = new ArrayList<>();
    cuentas.add(new Cuenta("001-12345-6", "Caja de Ahorro", "ARS", 10000));
    cuentas.add(new Cuenta("002-54321-9", "Cuenta Corriente", "USD", 5000)); 
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <title>Buscar Cliente - MiBanco</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet" />
</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="../../../Componentes/header.jspf" %>

<main class="flex-grow-1 bg-light p-4">
    <div class="container">

        <h2 class="mb-4">Buscar Cliente por DNI (Simulado!)</h2>

        <div class="card mb-4">
            <div class="card-header">
                <h4>Cliente: <%= cliente.getNombre() %> <%= cliente.getApellido() %></h4>
                <small>DNI: <%= cliente.getDni() %></small>
            </div>
            <div class="card-body">
                <h5>Cuentas (<%= cuentas.size() %>)</h5>

                <%
                    if (!cuentas.isEmpty()) {
                %>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>N° Cuenta</th>
                                <th>Tipo</th>
                                <th>Moneda</th>
                                <th>Saldo</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                for (Cuenta cuenta : cuentas) {
                            %>
                                <tr>
                                    <td><%= cuenta.getNumero() %></td>
                                    <td><%= cuenta.getTipo() %></td>
                                    <td><%= cuenta.getMoneda() %></td>
                                    <td>$<%= cuenta.getSaldo() %></td>
                                </tr>
                            <%
                                }
                            %>
                        </tbody>
                    </table>
                <%
                    } else {
                %>
                    <p>El cliente no tiene cuentas asignadas.</p>
                <%
                    }
                %>

                <a href="AltaCliente.jsp?dniCliente=<%= cliente.getDni() %>" class="btn btn-success mt-3">
                    <i class="bi bi-plus-circle"></i> Agregar Cuenta
                </a>
            </div>
            
	        <div class="mb-3 row">
			    <div class="col-sm-12 text-center">
			        <a href="../MenuPrincipal/MenuPrincipalCliente.jsp" class="btn btn-secondary">
			            <i class="bi-box-arrow-left"></i> Volver
			        </a>
			    </div>
			</div>
        </div>

    </div>
</main>

<%@ include file="../../../Componentes/footer.jspf" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
