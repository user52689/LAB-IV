<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.text.NumberFormat, java.util.Locale, java.time.format.DateTimeFormatter" %>
<%@ page import="Modelo.Prestamo" %>
<%
    Prestamo prestamo = (Prestamo) request.getAttribute("prestamo");
    NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(new Locale("es", "AR"));
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <title>Detalle de Préstamo - MiBanco</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet" />
</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="../../../Componentes/header.jspf" %>

<main class="flex-grow-1 bg-light p-4">
    <div class="container w-50 mx-auto">

        <h2 class="mb-4 text-center">Detalle del Préstamo</h2>

        <table class="table table-bordered">
            <tbody>
                <tr>
                    <th>ID Préstamo</th>
                    <td><%= prestamo.getIdPrestamo() %></td>
                </tr>
                <tr>
                    <th>Descripción</th>
                    <td><%= prestamo.getObservaciones() != null ? prestamo.getObservaciones() : "Sin descripción" %></td>
                </tr>
                <tr>
                    <th>Monto Original</th>
                    <td><%= formatoMoneda.format(prestamo.getImporteSolicitado()) %></td>
                </tr>
                <tr>
                    <th>Monto Total a Devolver</th>
                    <td><%= formatoMoneda.format(prestamo.getImporteTotal()) %></td>
                </tr>
                <tr>
                    <th>Monto Pendiente</th>
                    <td><%= formatoMoneda.format(prestamo.getSaldoPendiente() != 0 ? prestamo.getSaldoPendiente() : prestamo.getImporteTotal()) %></td>
                </tr>
                <tr>
                    <th>Tasa de Interés Mensual</th>
                    <td>
                        <%
                            double solicitado = prestamo.getImporteSolicitado();
                            double total = prestamo.getImporteTotal();
                            int plazo = prestamo.getPlazoMeses();
                            double interesMensual = plazo > 0 ? ((total - solicitado) / solicitado / plazo) * 100 : 0;
                        %>
                        <%= String.format("%.2f", interesMensual) %> %
                    </td>
                </tr>
                <tr>
                    <th>Plazo</th>
                    <td><%= prestamo.getPlazoMeses() %> meses</td>
                </tr>
                <tr>
                    <th>Fecha de Inicio</th>
                    <td><%= prestamo.getFechaSolicitud().format(formatter) %></td>
                </tr>
                <tr>
                    <th>Estado</th>
                    <td><%= prestamo.getEstadoPrestamo().getDescripcion() %></td>
                </tr>
            </tbody>
        </table>

        <div class="text-center mt-4">
            <a href="ListarPrestamosClienteServlet" class="btn btn-secondary">
                <i class="bi bi-arrow-left"></i> Volver al listado
            </a>
        </div>

    </div>
</main>

<%@ include file="../../../Componentes/footer.jspf" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
