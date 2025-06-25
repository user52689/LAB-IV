<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Home Banking - Inicio</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    
    <!-- Animaciones personalizadas -->
	<style>
	    .wind-animation {
	        display: inline-block;
	        animation: breeze 1.5s ease-in-out infinite;
	        transform-origin: center bottom;
	    }
	
	    @keyframes breeze {
	        0% { transform: rotate(0deg) translateX(0); }
	        20% { transform: rotate(-3deg) translateX(-2px); }
	        40% { transform: rotate(3deg) translateX(2px); }
	        60% { transform: rotate(-2deg) translateX(-1px); }
	        80% { transform: rotate(2deg) translateX(1px); }
	        100% { transform: rotate(0deg) translateX(0); }
	    }
	</style>
</head>
<body class="d-flex flex-column min-vh-100">

<!-- CABECERA -->
<header class="bg-primary text-white text-center py-4 shadow">
    <h1 class="m-0"><i class="bi bi-bank2 me-2"></i>MiBanco</h1>
</header>

<!-- CUERPO PRINCIPAL -->
<main class="flex-grow-1 d-flex align-items-center justify-content-center bg-light">
    <div class="text-center p-4">
        <i class="bi bi-house-door-fill text-primary wind-animation" style="font-size: 4rem;"></i>
        <h2 class="mt-3 mb-4">Bienvenido a tu Home Banking</h2>
        <p class="mb-4 text-muted">Gestioná tus cuentas de forma simple, rápida y segura.</p>
        <a href="Login.jsp" class="btn btn-primary btn-lg">
            <i class="bi bi-box-arrow-in-right me-2"></i>Ingresar
        </a>
    </div>
</main>

<!-- PIE DE PÁGINA -->
<footer class="bg-dark text-white text-center py-3 mt-auto">
    <small>&copy; 2025 MiBanco <i class="bi bi-shield-lock-fill ms-2"></i> Todos los derechos reservados.</small>
</footer>

<!-- Bootstrap JS Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
