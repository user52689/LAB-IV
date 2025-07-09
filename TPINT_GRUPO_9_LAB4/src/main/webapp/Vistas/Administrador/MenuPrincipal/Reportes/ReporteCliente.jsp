<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Modelo.Cliente" %>
<%@ page import="Modelo.ReporteCliente" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Reporte por Cliente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body class="bg-light p-4">

<div class="container">
    <h2 class="mb-4"><i class="bi bi-person-lines-fill text-purple"></i> Reporte de Ingresos y Egresos por Cliente</h2>
<%
    Object prueba = request.getAttribute("listaClientes");
    if (prueba == null) {
%>
        <div class="alert alert-danger">⚠️ listaClientes es NULL</div>
<%
    } else {
        List<Cliente> testList = (List<Cliente>) prueba;
%>
        <div class="alert alert-info">🔍 listaClientes contiene <%= testList.size() %> cliente(s)</div>
<%
        for (Cliente c : testList) {
%>
            <div><%= c.getIdCliente() %> - <%= c.getNombre() %> <%= c.getApellido() %></div>
<%
        }
    }
%>
    <form action="${pageContext.request.contextPath}/ReporteClienteServlet" method="post" class="row g-3">
        <div class="col-md-4">
            <label for="idCliente" class="form-label">Cliente:</label>
            <select name="idCliente" id="idCliente" class="form-select" required>
                <option value="">Seleccione un cliente</option>
                <%
                    List<Cliente> clientes = (List<Cliente>) request.getAttribute("listaClientes");
                    Integer seleccionado = (Integer) request.getAttribute("idCliente");

                    if (clientes != null) {
                        for (Cliente c : clientes) {
                            String selected = (seleccionado != null && seleccionado == c.getIdCliente()) ? "selected" : "";
                %>
                    <option value="<%= c.getIdCliente() %>" <%= selected %>>
                        <%= c.getNombre() %> <%= c.getApellido() %> - DNI: <%= c.getDni() %>
                    </option>
                <%
                        }
                    }
                %>
            </select>
        </div>

        <div class="col-md-4">
            <label for="fechaDesde" class="form-label">Fecha Desde:</label>
            <input type="date" class="form-control" id="fechaDesde" name="fechaDesde" value="${fechaDesde}" required>
        </div>
        <div class="col-md-4">
            <label for="fechaHasta" class="form-label">Fecha Hasta:</label>
            <input type="date" class="form-control" id="fechaHasta" name="fechaHasta" value="${fechaHasta}" required>
        </div>

        <div class="col-12 d-flex justify-content-end">
            <button type="submit" class="btn btn-primary">Generar Reporte</button>
        </div>
    </form>

    <hr class="my-4" />

    <%
        ReporteCliente reporte = (ReporteCliente) request.getAttribute("reporte");
        if (reporte != null) {
    %>
        <div class="card shadow-sm">
            <div class="card-body">
                <h5 class="card-title">Cliente: <%= reporte.getNombreCliente() %></h5>
                <table class="table table-bordered text-center mt-3">
                    <thead class="table-light">
                        <tr>
                            <th>Total Ingresos</th>
                            <th>Total Egresos</th>
                            <th>Saldo Neto</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>$<%= String.format("%.2f", reporte.getIngresos()) %></td>
                            <td>$<%= String.format("%.2f", reporte.getEgresos()) %></td>
                            <td>$<%= String.format("%.2f", reporte.getSaldo()) %></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    <%
        } else if (request.getAttribute("error") != null) {
    %>
        <div class="alert alert-danger mt-3">
            <%= request.getAttribute("error") %>
        </div>
    <%
        }
    %>
</div>

</body>
</html>
