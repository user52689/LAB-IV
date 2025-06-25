<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, java.text.NumberFormat, java.util.Locale" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <title>Préstamos Pendientes - Administración - MiBanco</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet" />
</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="../../../Componentes/header.jspf" %>

<main class="flex-grow-1 bg-light p-4">
    <div class="container">

        <h2 class="mb-4 text-center">Préstamos Pedidos por Clientes</h2>
		
		<!-- Formulario de búsqueda por DNI -->
		<form method="get" action="PrestamosPendientes.jsp" class="row g-3 mb-4">
		    <div class="col-md-4">
		        <input type="text" name="dni" class="form-control" placeholder="Buscar por DNI del cliente"
		               value="<%= request.getParameter("dni") != null ? request.getParameter("dni") : "" %>">
		    </div>
		    <div class="col-md-2">
		        <button type="submit" class="btn btn-primary">
		            <i class="bi bi-search"></i> Buscar
		        </button>
		    </div>
		</form>
		
        <%
            class Prestamo {
                int id;
                String descripcion;
                String estado; // Ej: "Pendiente", "Asignado", "Rechazado"
                double monto;
                String dniCliente;
                String nombreCliente;

                Prestamo(int id, String descripcion, String estado, double monto, String dniCliente, String nombreCliente) {
                    this.id = id;
                    this.descripcion = descripcion;
                    this.estado = estado;
                    this.monto = monto;
                    this.dniCliente = dniCliente;
                    this.nombreCliente = nombreCliente;
                }
            }

            List<Prestamo> prestamos = new ArrayList<>();
            prestamos.add(new Prestamo(1, "Crédito Personal", "Pendiente", 50000.0, "20123456", "Juan Pérez"));
            prestamos.add(new Prestamo(2, "Préstamo Auto", "Pendiente", 75000.0, "30123456", "María Gómez"));
            prestamos.add(new Prestamo(3, "Préstamo Estudio", "Asignado", 30000.0, "20123456", "Juan Pérez"));

            NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(new Locale("es", "AR"));
        %>

        <table class="table table-bordered table-striped align-middle">
            <thead class="table-primary">
                <tr>
                    <th>ID Préstamo</th>
                    <th>Descripción</th>
                    <th>Estado</th>
                    <th>Monto</th>
                    <th>DNI Cliente</th>
                    <th>Nombre Cliente</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    boolean hayPendientes = false;
                    for (Prestamo p : prestamos) {
                %>
                    <tr>
                        <td><%= p.id %></td>
                        <td><%= p.descripcion %></td>
                        <td>
                            <% if ("Pendiente".equalsIgnoreCase(p.estado)) { %>
                                <span class="badge bg-warning text-dark"><%= p.estado %></span>
                            <% } else if ("Asignado".equalsIgnoreCase(p.estado)) { %>
                                <span class="badge bg-success"><%= p.estado %></span>
                            <% } else if ("Rechazado".equalsIgnoreCase(p.estado)) { %>
                                <span class="badge bg-danger"><%= p.estado %></span>
                            <% } else { %>
                                <%= p.estado %>
                            <% } %>
                        </td>
                        <td><%= formatoMoneda.format(p.monto) %></td>
                        <td><%= p.dniCliente %></td>
                        <td><%= p.nombreCliente %></td>
                        <td>
                            <% if ("Pendiente".equalsIgnoreCase(p.estado)) { 
                                hayPendientes = true;
                            %>
                                <div class="d-flex gap-2">
                                    <!-- Formulario para Asignar -->
                                    <form action="GestionarPrestamoServlet" method="post" style="margin:0;">
                                        <input type="hidden" name="idPrestamo" value="<%= p.id %>" />
                                        <input type="hidden" name="accion" value="asignar" />
                                        <button type="submit" class="btn btn-sm btn-primary" title="Asignar Préstamo">
                                            <i class="bi bi-check-circle"></i> Asignar
                                        </button>
                                    </form>

                                    <!-- Formulario para Rechazar -->
                                    <form action="GestionarPrestamoServlet" method="post" style="margin:0;">
                                        <input type="hidden" name="idPrestamo" value="<%= p.id %>" />
                                        <input type="hidden" name="accion" value="rechazar" />
                                        <button type="submit" class="btn btn-sm btn-danger" title="Rechazar Préstamo">
                                            <i class="bi bi-x-circle"></i> Rechazar
                                        </button>
                                    </form>
                                </div>
                            <% } else { %>
                                <button class="btn btn-sm btn-secondary" disabled>
                                    <i class="bi bi-lock-fill"></i> <%= p.estado %>
                                </button>
                            <% } %>
                        </td>
                    </tr>
                <%
                    }
                    if (!hayPendientes) {
                %>
                    <tr>
                        <td colspan="7" class="text-center">No hay préstamos pendientes para gestionar.</td>
                    </tr>
                <%
                    }
                %>
            </tbody>
        </table>

	        <div class="mb-3 row">
			    <div class="col-sm-12 text-center">
			        <a href="../MenuPrincipal/MenuAdministrador.jsp" class="btn btn-secondary">
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
