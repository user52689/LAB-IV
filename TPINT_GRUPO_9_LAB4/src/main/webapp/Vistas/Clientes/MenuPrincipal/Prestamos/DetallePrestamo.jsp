<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.text.NumberFormat, java.util.Locale" %>
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

        <%
            // Simulamos datos del préstamo
            int idPrestamo = 12345;
            String descripcion = "Préstamo Personal para Auto";
            double montoOriginal = 150000.00;
            double montoPendiente = 65000.75;
            double tasaInteres = 0.025; // 2.5% mensual
            int plazoMeses = 36;
            String fechaInicio = "15/03/2023";
            String estado = "Activo";

            NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(new Locale("es", "AR"));
        %>

        <h2 class="mb-4 text-center">Detalle del Préstamo</h2>

        <table class="table table-bordered">
            <tbody>
                <tr>
                    <th>ID Préstamo</th>
                    <td><%= idPrestamo %></td>
                </tr>
                <tr>
                    <th>Descripción</th>
                    <td><%= descripcion %></td>
                </tr>
                <tr>
                    <th>Monto Original</th>
                    <td><%= formatoMoneda.format(montoOriginal) %></td>
                </tr>
                <tr>
                    <th>Monto Pendiente</th>
                    <td><%= formatoMoneda.format(montoPendiente) %></td>
                </tr>
                <tr>
                    <th>Tasa de Interés Mensual</th>
                    <td><%= (tasaInteres * 100) %> %</td>
                </tr>
                <tr>
                    <th>Plazo</th>
                    <td><%= plazoMeses %> meses</td>
                </tr>
                <tr>
                    <th>Fecha de Inicio</th>
                    <td><%= fechaInicio %></td>
                </tr>
                <tr>
                    <th>Estado</th>
                    <td><%= estado %></td>
                </tr>
            </tbody>
        </table>

        <div class="text-center mt-4">
            <a href="ListarPrestamos.jsp" class="btn btn-secondary">
                <i class="bi bi-arrow-left"></i> Volver al listado
            </a>
        </div>

    </div>
</main>

<%@ include file="../../../Componentes/footer.jspf" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
