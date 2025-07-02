<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <title>Menú Cliente - MiBanco</title>
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

<!-- CUERPO -->
<main class="flex-grow-1 bg-light p-4">
    <div class="container">
        <h2 class="text-center mb-5">Panel de Cliente</h2>
        <div class="row g-4 justify-content-center">

            <!-- Cuentas -->
            <div class="col-md-3 menu-card">
                <a href="../Cuentas/ListarCuentas.jsp" class="text-decoration-none">
                    <div class="card shadow h-100 text-center p-3">
                        <i class="bi bi-wallet2 text-primary" style="font-size: 2.5rem;"></i>
                        <h5 class="mt-3">Cuentas</h5>
                        <p class="text-muted">Ver y administrar tus cuentas bancarias</p>
                    </div> 
                </a>
            </div>

            <!-- Perfiles -->
            <div class="col-md-3 menu-card">
                <a href="../Perfil/PerfilCliente.jsp" class="text-decoration-none">
                    <div class="card shadow h-100 text-center p-3">
                        <i class="bi bi-person-circle text-success" style="font-size: 2.5rem;"></i>
                        <h5 class="mt-3">Perfiles</h5>
                        <p class="text-muted">Administrar tu perfil y datos personales</p>
                    </div>
                </a>
            </div>
            
            <!-- Transferencias -->
			<div class="col-md-3 menu-card">
			    <a href="../Cuentas/TransferenciasDinero.jsp" class="text-decoration-none">
			        <div class="card shadow h-100 text-center p-3">
			            <i class="bi bi-arrow-left-right text-info" style="font-size: 2.5rem;"></i>
			            <h5 class="mt-3">Transferencias</h5>
			            <p class="text-muted">Gestionar transferencias y movimientos</p>
			        </div>
			    </a>
			</div>
            
            <!-- Préstamos -->
            <div class="col-md-3 menu-card">
                <a href="<%=request.getContextPath()%>/Vistas/Clientes/MenuPrincipal/Prestamos/MenuPrestamos.jsp" class="text-decoration-none">
                    <div class="card shadow h-100 text-center p-3">
                        <i class="bi bi-cash-stack text-warning" style="font-size: 2.5rem;"></i>
                        <h5 class="mt-3">Préstamos</h5>
                        <p class="text-muted">Gestionar préstamos y solicitudes</p>
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
