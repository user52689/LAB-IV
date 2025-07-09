<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Iniciar Sesión - MiBanco</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body class="d-flex flex-column min-vh-100">

<!-- CABECERA -->
<header class="bg-primary text-white text-center py-4 shadow">
    <h1 class="m-0"><i class="bi bi-bank2 me-2"></i>MiBanco</h1>
</header>

<!-- FORMULARIO DE LOGIN -->
<main class="flex-grow-1 d-flex align-items-center justify-content-center bg-light">
    <div class="card shadow p-4" style="min-width: 300px; max-width: 400px; width: 100%;">
        <h2 class="text-center mb-4">Iniciar Sesión</h2>
        <% if (request.getAttribute("mensajeError") != null) { %>
            <div class="alert alert-danger" role="alert">
                <%= request.getAttribute("mensajeError") %>
            </div>
        <% } %>
        <form action="${pageContext.request.contextPath}/LoginServlet" method="post">
            <div class="mb-3"> 
                <label for="nombreUsuario" class="form-label">Usuario</label>
                <div class="input-group">
                    <span class="input-group-text"><i class="bi bi-person-fill"></i></span>
                    <input type="text" class="form-control" id="nombreUsuario" name="nombreUsuario" required>
                </div>
            </div>
            <div class="mb-3">
                <label for="contrasena" class="form-label">Contraseña</label>
                <div class="input-group">
                    <span class="input-group-text"><i class="bi bi-lock-fill"></i></span>
                    <input type="password" class="form-control" id="contrasena" name="contrasena" required>
                </div>
            </div>
            <div class="d-grid">
                <button type="submit" class="btn btn-primary">
                    <i class="bi bi-box-arrow-in-right me-1"></i> Ingresar
                </button>
            </div>
        </form>
        <div class="mt-4 text-center">
            <a href="${pageContext.request.contextPath}/Vistas/Inicio/Inicio.jsp" class="btn btn-secondary">
                <i class="bi-box-arrow-left"></i> Volver
            </a>
        </div>
    </div>
</main>

<!-- PIE DE PÁGINA -->
<footer class="bg-dark text-white text-center py-3 mt-auto">
    <small>© 2025 MiBanco <i class="bi bi-shield-lock-fill ms-2"></i> Todos los derechos reservados.</small>
</footer>

<!-- Bootstrap JS Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>