<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <title>Perfil de Cliente - MiBanco</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet" />
    <script>
        function toggleEdit(idInput, btnEdit, btnSave) {
            const input = document.getElementById(idInput);
            input.disabled = !input.disabled;
            if (!input.disabled) {
                input.focus();
                btnEdit.style.display = 'none';
                btnSave.style.display = 'inline-block';
            } else {
                btnEdit.style.display = 'inline-block';
                btnSave.style.display = 'none';
            }
        }
    </script>
</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="../../../Componentes/header.jspf" %>

<main class="flex-grow-1 bg-light p-4">
    <div class="container w-50 mx-auto">
        <h2 class="mb-4 text-center">Perfil de Cliente</h2>

        <%
            // Simulamos datos del cliente
            String nombreCliente = "Oscar Príncipi";
            String dni = "30123456";
            String email = "oscar@ejemplo.com";
            String telefono = "1167891234";
            String direccion = "Av. Siempreviva 123, CABA";
            Date fechaNacimiento = new GregorianCalendar(1990, Calendar.MARCH, 15).getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        %>

        <form action="ActualizarPerfilServlet" method="post">
            <div class="mb-3 row align-items-center">
                <label for="nombre" class="col-sm-3 col-form-label">Nombre completo</label>
                <div class="col-sm-9">
                    <input type="text" id="nombre" name="nombre" class="form-control" value="<%= nombre %>" readonly />
                </div>
            </div>

            <div class="mb-3 row align-items-center">
                <label for="dni" class="col-sm-3 col-form-label">DNI</label>
                <div class="col-sm-9">
                    <input type="text" id="dni" name="dni" class="form-control" value="<%= dni %>" readonly />
                </div>
            </div>

            <div class="mb-3 row align-items-center">
                <label for="email" class="col-sm-3 col-form-label">Email</label>
                <div class="col-sm-8">
                    <input type="email" id="email" name="email" class="form-control" value="<%= email %>" disabled required />
                </div>
                <div class="col-sm-1">
                    <button type="button" id="btnEditEmail" class="btn btn-outline-primary btn-sm"
                        onclick="toggleEdit('email', this, document.getElementById('btnSaveEmail'))">
                        <i class="bi bi-pencil"></i>
                    </button>
                    <button type="submit" id="btnSaveEmail" class="btn btn-success btn-sm" style="display:none;">
                        <i class="bi bi-check-lg"></i>
                    </button>
                </div>
            </div>

            <div class="mb-3 row align-items-center">
                <label for="telefono" class="col-sm-3 col-form-label">Teléfono</label>
                <div class="col-sm-8">
                    <input type="text" id="telefono" name="telefono" class="form-control" value="<%= telefono %>" disabled required />
                </div>
                <div class="col-sm-1">
                    <button type="button" id="btnEditTelefono" class="btn btn-outline-primary btn-sm"
                        onclick="toggleEdit('telefono', this, document.getElementById('btnSaveTelefono'))">
                        <i class="bi bi-pencil"></i>
                    </button>
                    <button type="submit" id="btnSaveTelefono" class="btn btn-success btn-sm" style="display:none;">
                        <i class="bi bi-check-lg"></i>
                    </button>
                </div>
            </div>

            <div class="mb-3 row align-items-center">
                <label for="direccion" class="col-sm-3 col-form-label">Dirección</label>
                <div class="col-sm-8">
                    <input type="text" id="direccion" name="direccion" class="form-control" value="<%= direccion %>" disabled required />
                </div>
                <div class="col-sm-1">
                    <button type="button" id="btnEditDireccion" class="btn btn-outline-primary btn-sm"
                        onclick="toggleEdit('direccion', this, document.getElementById('btnSaveDireccion'))">
                        <i class="bi bi-pencil"></i>
                    </button>
                    <button type="submit" id="btnSaveDireccion" class="btn btn-success btn-sm" style="display:none;">
                        <i class="bi bi-check-lg"></i>
                    </button>
                </div>
            </div>

	        <div class="mb-3 row">
			    <div class="col-sm-12 text-center">
			        <a href="../MenuPrincipal/MenuPrincipalCliente.jsp" class="btn btn-secondary">
			            <i class="bi-box-arrow-left"></i> Volver
			        </a>
			    </div>
			</div>
        </form>

    </div>
</main>

<%@ include file="../../../Componentes/footer.jspf" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
