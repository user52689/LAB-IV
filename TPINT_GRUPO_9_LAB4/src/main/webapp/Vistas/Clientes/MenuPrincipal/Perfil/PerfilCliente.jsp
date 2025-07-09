<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Modelo.Cliente, java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <title>Perfil de Cliente - MiBanco</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet" />
    
</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="../../../Componentes/header.jspf" %>

<main class="flex-grow-1 bg-light p-4">
    <div class="container w-50 mx-auto">
        <h2 class="mb-4 text-center">Perfil de Cliente</h2> 

        <!-- Mostrar mensajes -->
        <% if (request.getAttribute("mensajeExito") != null) { %>
            <div class="alert alert-success" role="alert">
                <%= request.getAttribute("mensajeExito") %>
            </div>
        <% } %>
        <% if (request.getAttribute("mensajeError") != null) { %>
            <div class="alert alert-danger" role="alert">
                <%= request.getAttribute("mensajeError") %>
            </div>
        <% } %>

        <%
            Cliente cliente = (Cliente) request.getAttribute("cliente");
            String nombreCompleto = cliente != null ? cliente.getNombre() + " " + cliente.getApellido() : "";
            String dni = cliente != null ? cliente.getDni() : "";
            String correoElectronico = cliente != null ? cliente.getCorreoElectronico() : "";
            String telefono = cliente != null ? cliente.getTelefono() : "";
            String direccion = cliente != null ? cliente.getDireccion() : "";
        %>

        <div class="mb-3 row align-items-center">
            <label for="nombre" class="col-sm-3 col-form-label">Nombre completo</label>
            <div class="col-sm-9">
                <input type="text" id="nombre" name="nombre" class="form-control" value="<%= nombreCompleto %>" readonly />
            </div>
        </div>

        <div class="mb-3 row align-items-center">
            <label for="dni" class="col-sm-3 col-form-label">DNI</label>
            <div class="col-sm-9">
                <input type="text" id="dni" name="dni" class="form-control" value="<%= dni %>" readonly />
            </div>
        </div>

        <form action="<%=request.getContextPath()%>/ActualizarPerfilServlet" method="post">
            <div class="mb-3 row align-items-center">
                <label for="correoElectronico" class="col-sm-3 col-form-label">Email</label>
                <div class="col-sm-7">
                    <input type="email" id="correoElectronico" name="correoElectronico" class="form-control" value="<%= correoElectronico %>" disabled required />
                </div>
                <div class="col-sm-2 d-flex gap-1">
                    <button type="button" id="btnEditEmail" class="btn btn-outline-primary btn-sm"
                        onclick="toggleEdit('correoElectronico', this, document.getElementById('btnSaveEmail'), document.getElementById('btnCancelEmail'))">
                        <i class="bi bi-pencil"></i>
                    </button>
                    <button type="submit" id="btnSaveEmail" name="action" value="save" class="btn btn-success btn-sm" style="display:none;">
                        <i class="bi bi-check-lg"></i>
                    </button>
                    <button type="button" id="btnCancelEmail" class="btn btn-danger btn-sm" style="display:none;"
                        onclick="cancelEdit('correoElectronico', document.getElementById('btnEditEmail'), document.getElementById('btnSaveEmail'), this)">
                        <i class="bi bi-x-lg"></i>
                    </button>
                    <input type="hidden" name="field" value="correoElectronico" />
                </div>
            </div>
        </form>

        <form action="<%=request.getContextPath()%>/ActualizarPerfilServlet" method="post">
            <div class="mb-3 row align-items-center">
                <label for="telefono" class="col-sm-3 col-form-label">Teléfono</label>
                <div class="col-sm-7">
                    <input type="text" id="telefono" name="telefono" class="form-control" value="<%= telefono %>" disabled required />
                </div>
                <div class="col-sm-2 d-flex gap-1">
                    <button type="button" id="btnEditTelefono" class="btn btn-outline-primary btn-sm"
                        onclick="toggleEdit('telefono', this, document.getElementById('btnSaveTelefono'), document.getElementById('btnCancelTelefono'))">
                        <i class="bi bi-pencil"></i>
                    </button>
                    <button type="submit" id="btnSaveTelefono" name="action" value="save" class="btn btn-success btn-sm" style="display:none;">
                        <i class="bi bi-check-lg"></i>
                    </button>
                    <button type="button" id="btnCancelTelefono" class="btn btn-danger btn-sm" style="display:none;"
                        onclick="cancelEdit('telefono', document.getElementById('btnEditTelefono'), document.getElementById('btnSaveTelefono'), this)">
                        <i class="bi bi-x-lg"></i>
                    </button>
                    <input type="hidden" name="field" value="telefono" />
                </div>
            </div>
        </form>

        <form action="<%=request.getContextPath()%>/ActualizarPerfilServlet" method="post">
            <div class="mb-3 row align-items-center">
                <label for="direccion" class="col-sm-3 col-form-label">Dirección</label>
                <div class="col-sm-7">
                    <input type="text" id="direccion" name="direccion" class="form-control" value="<%= direccion %>" disabled required />
                </div>
                <div class="col-sm-2 d-flex gap-1">
                    <button type="button" id="btnEditDireccion" class="btn btn-outline-primary btn-sm"
                        onclick="toggleEdit('direccion', this, document.getElementById('btnSaveDireccion'), document.getElementById('btnCancelDireccion'))">
                        <i class="bi bi-pencil"></i>
                    </button>
                    <button type="submit" id="btnSaveDireccion" name="action" value="save" class="btn btn-success btn-sm" style="display:none;">
                        <i class="bi bi-check-lg"></i>
                    </button>
                    <button type="button" id="btnCancelDireccion" class="btn btn-danger btn-sm" style="display:none;"
                         onclick="cancelEdit('direccion', document.getElementById('btnEditDireccion'), document.getElementById('btnSaveDireccion'), this)">
                        <i class="bi bi-x-lg"></i>
                    </button>
                    <input type="hidden" name="field" value="direccion" />
                </div>
            </div>
        </form>

        <div class="mb-3 row">
            <div class="col-sm-12 text-center">
                <a href="<%=request.getContextPath()%>/Vistas/Clientes/MenuPrincipal/MenuCliente.jsp" class="btn btn-secondary">
                    <i class="bi-box-arrow-left"></i> Volver
                </a>
            </div>
        </div>
    </div>
</main>

<%@ include file="../../../Componentes/footer.jspf" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<script>
        function toggleEdit(idInput, btnEdit, btnSave, btnCancel) {
            const input = document.getElementById(idInput);
            input.disabled = !input.disabled;
            if (!input.disabled) {
                // Guardar el valor original para poder restaurarlo
                input.setAttribute('data-original-value', input.value);
                input.focus();
                btnEdit.style.display = 'none';
                btnSave.style.display = 'inline-block';
                btnCancel.style.display = 'inline-block';
            } else {
                btnEdit.style.display = 'inline-block';
                btnSave.style.display = 'none';
                btnCancel.style.display = 'none';
            }
        }

        function cancelEdit(idInput, btnEdit, btnSave, btnCancel) {
            const input = document.getElementById(idInput);
            // Restaurar el valor original
            input.value = input.getAttribute('data-original-value');
            input.disabled = true;
            btnEdit.style.display = 'inline-block';
            btnSave.style.display = 'none';
            btnCancel.style.display = 'none';
        }
    </script>
</body>
</html>