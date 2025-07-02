<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <title>Préstamos - Cliente - MiBanco</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet" />
    <style>
        .menu-card {
            transition: transform 0.2s ease;
        }

        .menu-card:hover {
            transform: translateY(-5px);
        }
    </style>
</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="../../../Componentes/header.jspf" %>

<main class="flex-grow-1 bg-light p-4">
    <div class="container">
        <h2 class="text-center mb-5">Gestión de Préstamos</h2>
        <div class="row g-4 justify-content-center">

            <!-- Pedir Préstamo -->
            <div class="col-md-4 menu-card">
                <a href="SolicitarPrestamo.jsp" class="text-decoration-none">
                    <div class="card shadow h-100 text-center p-3">
                        <i class="bi bi-plus-circle text-primary" style="font-size: 2.5rem;"></i>
                        <h5 class="mt-3">Pedir Préstamo</h5>
                        <p class="text-muted">Solicitá un nuevo préstamo</p>
                    </div>
                </a> 
            </div>

            <!-- Listar Préstamos del Cliente -->
            <div class="col-md-4 menu-card">
                <a href="../../../../ListarPrestamosClienteServlet" class="text-decoration-none">
                    <div class="card shadow h-100 text-center p-3">
                        <i class="bi bi-file-text text-success" style="font-size: 2.5rem;"></i>
                        <h5 class="mt-3">Mis Préstamos</h5>
                        <p class="text-muted">Ver el estado de tus préstamos</p>
                    </div>
                </a>
            </div>

        </div>

        <div class="mt-5 text-center">
            <a href="../MenuPrincipal/MenuCliente.jsp" class="btn btn-secondary">
                <i class="bi bi-box-arrow-left"></i> Volver al menú principal
            </a>
        </div>
    </div>
</main>

<%@ include file="../../../Componentes/footer.jspf" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
