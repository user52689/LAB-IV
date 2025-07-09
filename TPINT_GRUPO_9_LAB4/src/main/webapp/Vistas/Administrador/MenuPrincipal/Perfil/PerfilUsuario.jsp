<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Modelo.Usuario" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <title>Perfil de Usuario - MiBanco</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet" />
</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="../../../Componentes/header.jspf" %>

<main class="flex-grow-1 bg-light p-4">
    <div class="container w-50 mx-auto">
        <h2 class="mb-4 text-center">Perfil de Usuario</h2>

        <% if (request.getAttribute("mensajeExito") != null) { %>
            <div class="alert alert-success"><%= request.getAttribute("mensajeExito") %></div>
        <% } %>
        <% if (request.getAttribute("mensajeError") != null) { %>
            <div class="alert alert-danger"><%= request.getAttribute("mensajeError") %></div>
        <% } %>

        <%
        Usuario usuario = (Usuario) request.getAttribute("usuario");
        String nombreUs = usuario != null ? usuario.getNombreUsuario() : "";
        String nombre = usuario != null ? usuario.getNombre() : "";
        String apellido = usuario != null ? usuario.getApellido() : "";
        String correo = usuario != null ? usuario.getCorreoElectronico() : "";
        String telefono = usuario != null ? usuario.getTelefono() : "";
        String direccion = usuario != null ? usuario.getDireccion() : "";
        String dni = usuario != null ? usuario.getDni() : "";
        %>

        <!-- Nombre de Usuario -->
        <div class="mb-3 row align-items-center">
            <label class="col-sm-3 col-form-label">Nombre de Usuario</label>
            <div class="col-sm-9">
                <input type="text" class="form-control" value="<%= nombreUs %>" readonly />
            </div>
        </div>

        <!-- DNI -->
        <div class="mb-3 row align-items-center">
            <label class="col-sm-3 col-form-label">DNI</label>
            <div class="col-sm-9">
                <input type="text" class="form-control" value="<%= dni %>" readonly />
            </div>
        </div>

        <!-- Nombre -->
        <form action="<%=request.getContextPath()%>/usuario/modificar" method="post">
            <input type="hidden" name="dni" value="<%= dni %>" />
            <div class="mb-3 row align-items-center">
                <label class="col-sm-3 col-form-label">Nombre</label>
                <div class="col-sm-7">
                    <input type="text" id="nombre" name="nombre" class="form-control" value="<%= nombre %>" disabled required />
                </div>
                <div class="col-sm-2 d-flex gap-1">
                    <button type="button" class="btn btn-outline-primary btn-sm" onclick="toggleEdit('nombre', this, btnSaveNombre, btnCancelNombre)" id="btnEditNombre">
                        <i class="bi bi-pencil"></i>
                    </button>
                    <button type="submit" name="action" value="save" class="btn btn-success btn-sm" style="display:none;" id="btnSaveNombre">
                        <i class="bi bi-check-lg"></i>
                    </button>
                    <button type="button" class="btn btn-danger btn-sm" onclick="cancelEdit('nombre', btnEditNombre, btnSaveNombre, this)" style="display:none;" id="btnCancelNombre">
                        <i class="bi bi-x-lg"></i>
                    </button>
                    <input type="hidden" name="field" value="nombre" />
                </div>
            </div>
        </form>

        <!-- Email -->
        <form action="<%=request.getContextPath()%>/usuario/modificar" method="post">
            <input type="hidden" name="dni" value="<%= dni %>" />
            <div class="mb-3 row align-items-center">
                <label class="col-sm-3 col-form-label">Email</label>
                <div class="col-sm-7">
                    <input type="email" id="correoElectronico" name="correoElectronico" class="form-control" value="<%= correo %>" disabled required />
                </div>
                <div class="col-sm-2 d-flex gap-1">
                    <button type="button" class="btn btn-outline-primary btn-sm" onclick="toggleEdit('correoElectronico', this, btnSaveEmail, btnCancelEmail)" id="btnEditEmail">
                        <i class="bi bi-pencil"></i>
                    </button>
                    <button type="submit" name="action" value="save" class="btn btn-success btn-sm" style="display:none;" id="btnSaveEmail">
                        <i class="bi bi-check-lg"></i>
                    </button>
                    <button type="button" class="btn btn-danger btn-sm" onclick="cancelEdit('correoElectronico', btnEditEmail, btnSaveEmail, this)" style="display:none;" id="btnCancelEmail">
                        <i class="bi bi-x-lg"></i>
                    </button>
                    <input type="hidden" name="field" value="correoElectronico" />
                </div>
            </div>
        </form>

        <!-- Teléfono -->
        <form action="<%=request.getContextPath()%>/usuario/modificar" method="post">
            <input type="hidden" name="dni" value="<%= dni %>" />
            <div class="mb-3 row align-items-center">
                <label class="col-sm-3 col-form-label">Teléfono</label>
                <div class="col-sm-7">
                    <input type="text" id="telefono" name="telefono" class="form-control" value="<%= telefono %>" disabled required />
                </div>
                <div class="col-sm-2 d-flex gap-1">
                    <button type="button" class="btn btn-outline-primary btn-sm" onclick="toggleEdit('telefono', this, btnSaveTel, btnCancelTel)" id="btnEditTel">
                        <i class="bi bi-pencil"></i>
                    </button>
                    <button type="submit" name="action" value="save" class="btn btn-success btn-sm" style="display:none;" id="btnSaveTel">
                        <i class="bi bi-check-lg"></i>
                    </button>
                    <button type="button" class="btn btn-danger btn-sm" onclick="cancelEdit('telefono', btnEditTel, btnSaveTel, this)" style="display:none;" id="btnCancelTel">
                        <i class="bi bi-x-lg"></i>
                    </button>
                    <input type="hidden" name="field" value="telefono" />
                </div>
            </div>
        </form>

        <!-- Dirección -->
        <form action="<%=request.getContextPath()%>/usuario/modificar" method="post">
            <input type="hidden" name="dni" value="<%= dni %>" />
            <div class="mb-3 row align-items-center">
                <label class="col-sm-3 col-form-label">Dirección</label>
                <div class="col-sm-7">
                    <input type="text" id="direccion" name="direccion" class="form-control" value="<%= direccion %>" disabled required />
                </div>
                <div class="col-sm-2 d-flex gap-1">
                    <button type="button" class="btn btn-outline-primary btn-sm" onclick="toggleEdit('direccion', this, btnSaveDir, btnCancelDir)" id="btnEditDir">
                        <i class="bi bi-pencil"></i>
                    </button>
                    <button type="submit" name="action" value="save" class="btn btn-success btn-sm" style="display:none;" id="btnSaveDir">
                        <i class="bi bi-check-lg"></i>
                    </button>
                    <button type="button" class="btn btn-danger btn-sm" onclick="cancelEdit('direccion', btnEditDir, btnSaveDir, this)" style="display:none;" id="btnCancelDir">
                        <i class="bi bi-x-lg"></i>
                    </button>
                    <input type="hidden" name="field" value="direccion" />
                </div>
            </div>
        </form>

        <!-- Contraseña -->
        <form action="<%=request.getContextPath()%>/usuario/modificar" method="post" onsubmit="return validarContrasena()">
            <input type="hidden" name="dni" value="<%= dni %>" />
            <h4 class="mb-3 text-center">Actualizar Contraseña</h4>
            <div class="mb-3 row align-items-center">
                <label class="col-sm-4 col-form-label">Nueva contraseña</label>
                <div class="col-sm-8">
                    <input type="password" name="nuevaContrasena" id="nuevaContrasena" class="form-control" required minlength="6" />
                </div>
            </div>
            <div class="mb-3 row align-items-center">
                <label class="col-sm-4 col-form-label">Confirmar contraseña</label>
                <div class="col-sm-8">
                    <input type="password" name="confirmarContrasena" id="confirmarContrasena" class="form-control" required minlength="6" />
                </div>
            </div>
            <div class="text-center">
                <input type="hidden" name="field" value="contrasena" />
                <button type="submit" name="action" value="updatePassword" class="btn btn-primary">
                    <i class="bi bi-key-fill"></i> Cambiar contraseña
                </button>
            </div>
        </form>

        <!-- Volver -->
        <div class="text-center mt-4">
            <a href="<%=request.getContextPath()%>/Vistas/Administrador/MenuPrincipal/MenuAdministrador.jsp" class="btn btn-secondary">
                <i class="bi bi-arrow-left"></i> Volver
            </a>
        </div>
    </div>
</main>

<%@ include file="../../../Componentes/footer.jspf" %>

<script>
    function toggleEdit(idInput, btnEdit, btnSave, btnCancel) {
        const input = document.getElementById(idInput);
        input.disabled = false;
        input.setAttribute('data-original-value', input.value);
        btnEdit.style.display = 'none';
        btnSave.style.display = 'inline-block';
        btnCancel.style.display = 'inline-block';
        input.focus();
    }

    function cancelEdit(idInput, btnEdit, btnSave, btnCancel) {
        const input = document.getElementById(idInput);
        input.value = input.getAttribute('data-original-value');
        input.disabled = true;
        btnEdit.style.display = 'inline-block';
        btnSave.style.display = 'none';
        btnCancel.style.display = 'none';
    }

    function validarContrasena() {
        const pass1 = document.getElementById('nuevaContrasena').value;
        const pass2 = document.getElementById('confirmarContrasena').value;
        if (pass1 !== pass2) {
            alert("Las contraseñas no coinciden.");
            return false;
        }
        return true;
    }
</script>

</body>
</html>
