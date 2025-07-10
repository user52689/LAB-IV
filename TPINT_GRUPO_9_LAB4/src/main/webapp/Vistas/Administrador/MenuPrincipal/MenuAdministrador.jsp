<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <title>Panel de Administración - MiBanco</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet" />
    <style>
    	.menu-card {
    		transition: transform .2s ease;
    	}
    
    	.menu-card:hover {
    		transform: translateY(-5px);
    	}
    </style>
</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="../../Componentes/header.jspf" %>

<main class="flex-grow-1 bg-light p-4">
    <div class="container">
        <h2 class="text-center mb-5">Gestión del Sistema</h2>
        <div class="row g-4 justify-content-center">
        
			<!-- Usuarios (Administradores) -->
			<div class="col-md-3 menu-card">
			    <a href="Usuarios/ABMLUsuarios.jsp" class="text-decoration-none">
			        <div class="card shadow h-100 text-center p-3"> 
			            <i class="bi bi-person-badge-fill text-primary" style="font-size: 2.5rem;"></i>
			            <h5 class="mt-3">Usuarios</h5>
			            <p class="text-muted">Altas, Bajas, Modificaciones y Listados</p>
			        </div>
			    </a>
			</div>
			
			<!-- Clientes -->
			<div class="col-md-3 menu-card">
			    <a href="Clientes/ABMLClientes.jsp" class="text-decoration-none">
			        <div class="card shadow h-100 text-center p-3">
			            <i class="bi bi-people text-success" style="font-size: 2.5rem;"></i>
			            <h5 class="mt-3">Clientes</h5>
			            <p class="text-muted">Altas, Bajas, Modificaciones y Listados</p>
			        </div>
			    </a>
			</div>
			
			 <!-- Perfiles
			<div class="col-md-3 menu-card">
			<a href="Perfil/PerfilUsuario.jsp" class="text-decoration-none">
			<div class="card shadow h-100 text-center p-3">
			<i class="bi bi-person-circle text-success" style="font-size: 2.5rem;"></i>
			<h5 class="mt-3">Perfil</h5>
			<p class="text-muted">Administrar tu perfil y datos personales</p>
			</div>
			</a>
			</div>
			-->

            <!-- Cuentas -->
            <div class="col-md-3 menu-card">
                <a href="Cuentas/ABMLCuentas.jsp" class="text-decoration-none">
                    <div class="card shadow h-100 text-center p-3">
                        <i class="bi bi-credit-card-2-back-fill text-success" style="font-size: 2.5rem;"></i>
                        <h5 class="mt-3">Cuentas</h5>
                        <p class="text-muted">Administrar cuentas bancarias</p>
                    </div>
                </a>
            </div>

          <!-- Préstamos -->
<div class="col-md-3 menu-card">
    <a href="<%= request.getContextPath() %>/Prestamos/pendientes" class="text-decoration-none">
        <div class="card shadow h-100 text-center p-3">
            <i class="bi bi-cash-stack text-warning" style="font-size: 2.5rem;"></i>
            <h5 class="mt-3">Préstamos</h5>
            <p class="text-muted">Gestión de préstamos pendientes</p>
        </div>
    </a>
</div>

            <!-- Reportes -->
            <div class="col-md-3 menu-card">
                <a href="Reportes/MenuReportes.jsp" class="text-decoration-none">
                    <div class="card shadow h-100 text-center p-3">
                        <i class="bi bi-graph-up text-danger" style="font-size: 2.5rem;"></i>
                        <h5 class="mt-3">Reportes</h5>
                        <p class="text-muted">Estadísticas y reportes del sistema</p>
                    </div>
                </a>
            </div>
        </div>
    </div>
</main>

<%@ include file="../../Componentes/footer.jspf" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
