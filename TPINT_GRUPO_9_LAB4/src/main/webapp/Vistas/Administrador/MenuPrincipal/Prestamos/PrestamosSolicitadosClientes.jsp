<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, java.text.NumberFormat, java.util.Locale, Modelo.Prestamo" %>
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
        <h2 class="mb-4 text-center">Préstamos Pendientes</h2>

        <%-- Mostrar mensaje de operación --%>
        <%
            String msg = request.getParameter("msg");
            if (msg != null && !msg.isEmpty()) {
        %>
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                <%= msg %>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Cerrar"></button>
            </div>
        <%
            }
        %>

        <%
            @SuppressWarnings("unchecked")
            List<Prestamo> prestamos = (List<Prestamo>) request.getAttribute("listaPrestamos");
            if (prestamos == null) {
                prestamos = new java.util.ArrayList<>();
            }
            NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("es","AR"));
            boolean hayPendientes = false;
        %>

        <table class="table table-bordered table-striped align-middle">
            <thead class="table-primary">
                <tr>
                    <th>ID</th>
                    <th>Observaciones</th>
                    <th>Estado</th>
                    <th>Importe Solicitado</th>
                    <th>DNI Cliente</th>
                    <th>Nombre Cliente</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Prestamo p : prestamos) {
                        int estadoId = p.getEstadoPrestamo().getIdEstadoPrestamo();
                        String estadoDesc = p.getEstadoPrestamo().getDescripcion();
                        if (estadoId == 1) {
                            hayPendientes = true;
                        }
                %>
                <tr>
                    <td><%= p.getIdPrestamo() %></td>
                    <td><%= p.getObservaciones() %></td>
                    <td>
                        <% if (estadoId == 1) { %>
                            <span class="badge bg-warning text-dark"><%= estadoDesc %></span>
                        <% } else if (estadoId == 2) { %>
                            <span class="badge bg-success text-dark"><%= estadoDesc %></span>
                        <% } else { %>
                            <span class="badge bg-danger text-light"><%= estadoDesc %></span>
                        <% } %>
                    </td>
                    <td><%= formato.format(p.getImporteSolicitado()) %></td>
                    <td><%= p.getCliente().getDni() %></td>
                    <td><%= p.getCliente().getNombre() + " " + p.getCliente().getApellido() %></td>
                    <td>
                        <% if (estadoId == 1) { %>
                            <div class="d-flex gap-2">
                                <form action="<%= request.getContextPath() %>/Prestamos/asignar" method="post" style="margin:0;">
                                    <input type="hidden" name="idPrestamo" value="<%= p.getIdPrestamo() %>" />
                                    <button type="submit" class="btn btn-sm btn-primary" title="Asignar">
                                        <i class="bi bi-check-circle"></i>
                                    </button>
                                </form>
                                <form action="<%= request.getContextPath() %>/Prestamos/rechazar" method="post" style="margin:0;">
                                    <input type="hidden" name="idPrestamo" value="<%= p.getIdPrestamo() %>" />
                                    <button type="submit" class="btn btn-sm btn-danger" title="Rechazar">
                                        <i class="bi bi-x-circle"></i>
                                    </button>
                                </form>
                            </div>
                        <% } else { %>
                            <button class="btn btn-sm btn-secondary" disabled>
                                <i class="bi bi-lock-fill"></i> <%= estadoDesc %>
                            </button>
                        <% } %>
                    </td>
                </tr>
                <% } %>
                <% if (!hayPendientes) { %>
                    <tr>
                        <td colspan="7" class="text-center">No hay préstamos pendientes.</td>
                    </tr>
                <% } %>
            </tbody>
        </table>

        <div class="mb-3 row">
            <div class="col text-center">
                <a href="<%= request.getContextPath() %>/Vistas/Administrador/MenuPrincipal/MenuAdministrador.jsp" class="btn btn-secondary">
                    <i class="bi bi-box-arrow-left"></i> Volver
                </a>
            </div>
        </div>
    </div>
</main>

<%@ include file="../../../Componentes/footer.jspf" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
