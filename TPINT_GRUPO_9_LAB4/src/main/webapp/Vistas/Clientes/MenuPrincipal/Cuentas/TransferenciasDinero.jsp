<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <title>Transferencia de Dinero - MiBanco</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet" />
    <script>
        function toggleDestino() {
            const esPropia = document.getElementById('tipoDestinoPropia').checked;
            document.getElementById('destinoPropia').style.display = esPropia ? 'block' : 'none';
            document.getElementById('destinoExterna').style.display = esPropia ? 'none' : 'block';
            
            // Limpiar valores al cambiar
            if(esPropia) {
                document.getElementById('cuentaDestinoExterna').value = '';
            } else {
                document.getElementById('cuentaDestinoPropia').value = '';
            }
        }

        window.onload = function() {
            toggleDestino();
        }
    </script>
</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="../../../Componentes/header.jspf" %>

<main class="flex-grow-1 bg-light p-4">
    <div class="container w-50 mx-auto">
        <h2 class="mb-4">Transferir Dinero</h2> 

        <%
            class Cuenta {
                int id;
                String numero;
                String tipo;
                double saldo;

                Cuenta(int id, String numero, String tipo, double saldo) {
                    this.id = id;
                    this.numero = numero;
                    this.tipo = tipo;
                    this.saldo = saldo;
                }
            }

            List<Cuenta> cuentasPropias = new ArrayList<>();
            cuentasPropias.add(new Cuenta(1, "123-456789", "Caja de Ahorro", 50000.00));
            cuentasPropias.add(new Cuenta(2, "987-654321", "Cuenta Corriente", 20000.00));
        %>

        <form action="TransferenciaServlet" method="post">

            <div class="mb-3">
                <label for="cuentaOrigen" class="form-label">Cuenta de Origen</label>
                <select id="cuentaOrigen" name="idCuentaOrigen" class="form-select" required>
                    <option value="" disabled selected>Elegí una cuenta</option>
                    <%
                        for (Cuenta c : cuentasPropias) {
                    %>
                    <option value="<%= c.id %>">
                        <%= c.numero %> - <%= c.tipo %> - Saldo: $<%= String.format("%,.2f", c.saldo) %>
                    </option>
                    <%
                        }
                    %>
                </select>
            </div>

            <div class="mb-3">
                <label class="form-label">Destino de la transferencia</label>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="tipoDestino" id="tipoDestinoPropia" value="propia" checked onchange="toggleDestino()" />
                    <label class="form-check-label" for="tipoDestinoPropia">
                        Cuenta propia
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="tipoDestino" id="tipoDestinoExterna" value="externa" onchange="toggleDestino()" />
                    <label class="form-check-label" for="tipoDestinoExterna">
                        Cuenta externa
                    </label>
                </div>
            </div>

            <div id="destinoPropia" class="mb-3">
                <label for="cuentaDestinoPropia" class="form-label">Cuenta Destino (propia)</label>
                <select id="cuentaDestinoPropia" name="idCuentaDestinoPropia" class="form-select">
                    <option value="" disabled selected>Elegí una cuenta propia</option>
                    <%
                        for (Cuenta c : cuentasPropias) {
                    %>
                    <option value="<%= c.id %>">
                        <%= c.numero %> - <%= c.tipo %>
                    </option>
                    <%
                        }
                    %>
                </select>
            </div>

            <div id="destinoExterna" class="mb-3" style="display:none;">
                <label for="cuentaDestinoExterna" class="form-label">Número de Cuenta Destino (externa)</label>
                <input type="text" id="cuentaDestinoExterna" name="numeroCuentaDestinoExterna" class="form-control" placeholder="Ej: 456-123456" />
            </div>

            <div class="mb-3">
                <label for="monto" class="form-label">Monto a Transferir</label>
                <input type="number" step="0.01" min="0.01" id="monto" name="monto" class="form-control" required placeholder="Ej: 15000.00" />
            </div>

            <button type="submit" class="btn btn-primary w-100">
                <i class="bi bi-arrow-left-right"></i> Transferir
            </button>

                    <div class="mb-3 row mt-4">
					    <div class="col-sm-12 text-center">
					        <a href="../MenuPrincipal/MenuCliente.jsp" class="btn btn-secondary">
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
