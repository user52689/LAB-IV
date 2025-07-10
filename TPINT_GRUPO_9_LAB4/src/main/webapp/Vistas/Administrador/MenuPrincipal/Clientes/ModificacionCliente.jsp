<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <title>Modificar Perfil de Cliente - MiBanco</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet" />
</head>
<body class="d-flex flex-column min-vh-100">

<%@ include file="../../../Componentes/header.jspf" %>

<main class="flex-grow-1 bg-light p-4">
    <div class="container w-50 mx-auto">
        <h2 class="mb-4 text-center">Modificar Perfil de Cliente</h2>

        <c:if test="${mensajeExito != null && !mensajeExito.trim().isEmpty()}">
          <div class="alert alert-success" role="alert">${mensajeExito}</div>
        </c:if>
        
        <c:if test="${mensajeError != null && !mensajeError.trim().isEmpty()}">
          <div class="alert alert-danger" role="alert">${mensajeError}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/cliente/modificar" method="get" class="row g-3 needs-validation" novalidate>
            <div class="col-md-8">
                <label for="dniBuscar" class="form-label">Buscar cliente por DNI</label>
                <input type="text" id="dniBuscar" name="dni" class="form-control" pattern="\d{6,20}" placeholder="Ej: 30123456" required />
                <div class="invalid-feedback">Ingresá un DNI válido (solo números).</div>
            </div>
            <div class="col-md-4 d-flex align-items-end">
                <button type="submit" class="btn btn-primary w-100">Buscar</button>
            </div>
        </form>

        <hr />

        <c:if test="${not empty cliente}">
            <form action="${pageContext.request.contextPath}/cliente/modificar" method="post" class="needs-validation" novalidate>
                <!-- DNI no editable -->
                <div class="mb-3 row">
                    <label for="dni" class="col-sm-3 col-form-label">DNI</label>
                    <div class="col-sm-9">
                        <input type="text" id="dni" name="dni" class="form-control" value="${cliente.dni}" readonly />
                    </div>
                </div>

                <div class="mb-3 row">
                    <label for="nombre" class="col-sm-3 col-form-label">Nombre</label>
                    <div class="col-sm-9">
                        <input type="text" id="nombre" name="nombre" class="form-control" value="${cliente.nombre}" required />
                        <div class="invalid-feedback">Ingrese un nombre válido.</div>
                    </div>
                </div>

                <div class="mb-3 row">
                    <label for="apellido" class="col-sm-3 col-form-label">Apellido</label>
                    <div class="col-sm-9">
                        <input type="text" id="apellido" name="apellido" class="form-control" value="${cliente.apellido}" required />
                        <div class="invalid-feedback">Ingrese un apellido válido.</div>
                    </div>
                </div>

                <div class="mb-3 row">
                    <label for="correoElectronico" class="col-sm-3 col-form-label">Correo Electrónico</label>
                    <div class="col-sm-9">
                        <input type="email" id="correoElectronico" name="correoElectronico" class="form-control" value="${cliente.correoElectronico}" required />
                        <div class="invalid-feedback">Ingrese un correo válido.</div>
                    </div>
                </div>

                <div class="mb-3 row">
                    <label for="telefono" class="col-sm-3 col-form-label">Teléfono</label>
                    <div class="col-sm-9">
                        <input type="tel" id="telefono" name="telefono" class="form-control" value="${cliente.telefono}" />
                    </div>
                </div>

                <div class="mb-3 row">
                    <label for="direccion" class="col-sm-3 col-form-label">Dirección</label>
                    <div class="col-sm-9">
                        <input type="text" id="direccion" name="direccion" class="form-control" value="${cliente.direccion}" />
                    </div>
                </div>

                <div class="mb-3 row">
                    <div class="col-sm-12 text-center">
                        <button type="submit" class="btn btn-primary">
                            <i class="bi bi-save me-2"></i>Guardar Cambios
                        </button>
                    </div>
                </div>
            </form>
        </c:if>

        <div class="mb-3 row mt-3">
            <div class="col-sm-12 text-center">
                <a href="${pageContext.request.contextPath}/Vistas/Administrador/MenuPrincipal/Clientes/ABMLClientes.jsp" class="btn btn-secondary">
                    <i class="bi bi-box-arrow-left"></i> Volver
                </a>
            </div>
        </div>

    </div>
</main>

<%@ include file="../../../Componentes/footer.jspf" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<script>
(() => {
  'use strict'
  const forms = document.querySelectorAll('.needs-validation')
  Array.from(forms).forEach(form => {
    form.addEventListener('submit', event => {
      if (!form.checkValidity()) {
        event.preventDefault()
        event.stopPropagation()
      }
      form.classList.add('was-validated')
    }, false)
  })
})()
</script>

</body>
</html>

















<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" --%>
<%--     pageEncoding="UTF-8" %> --%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<!-- <!DOCTYPE html> -->
<!-- <html lang="es"> -->
<!-- <head> -->
<!--     <meta charset="UTF-8" /> -->
<!--     <title>Modificar Perfil de Cliente - MiBanco</title> -->
<!--     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" /> -->
<!--     <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet" /> -->
<!-- </head> -->
<!-- <body class="d-flex flex-column min-vh-100"> -->

<%-- <%@ include file="../../../Componentes/header.jspf" %> --%>

<!-- <main class="flex-grow-1 bg-light p-4"> -->
<!--     <div class="container w-50 mx-auto"> -->
<!--         <h2 class="mb-4 text-center">Modificar Perfil de Cliente</h2> -->

<%--         <c:if test="${mensajeExito != null && !mensajeExito.trim().isEmpty()}"> --%>
<%--           <div class="alert alert-success" role="alert">${mensajeExito}</div> --%>
<%--         </c:if>  --%>
        
<%--         <c:if test="${mensajeError != null && !mensajeError.trim().isEmpty()}"> --%>
<%--           <div class="alert alert-danger" role="alert">${mensajeError}</div> --%>
<%--         </c:if> --%>

<%--         <form action="${pageContext.request.contextPath}/cliente/modificar" method="get" class="row g-3 needs-validation" novalidate> --%>
<!--             <div class="col-md-8"> -->
<!--                 <label for="dniBuscar" class="form-label">Buscar cliente por DNI</label> -->
<!--                 <input type="text" id="dniBuscar" name="dni" class="form-control" pattern="\d{6,20}" placeholder="Ej: 30123456" required /> -->
<!--                 <div class="invalid-feedback">Ingresá un DNI válido (solo números).</div> -->
<!--             </div> -->
<!--             <div class="col-md-4 d-flex align-items-end"> -->
<!--                 <button type="submit" class="btn btn-primary w-100">Buscar</button> -->
<!--             </div> -->
<!--         </form> -->

<!--         <hr /> -->

<%--        <c:if test="${not empty cliente}"> --%>

<!-- 		    Formulario: Correo Electrónico -->
<%-- 		    <form action="${pageContext.request.contextPath}/cliente/modificar" method="post" class="needs-validation mb-4" novalidate> --%>
<%-- 		        <input type="hidden" name="dni" value="${cliente.dni}" /> --%>
<!-- 		        <input type="hidden" name="field" value="correoElectronico" /> -->
<!-- 		        <div class="mb-3 row"> -->
<!-- 		            <label for="correoElectronico" class="col-sm-3 col-form-label">Correo Electrónico</label> -->
<!-- 		            <div class="col-sm-9"> -->
<%-- 		                <input type="email" id="correoElectronico" name="correoElectronico" class="form-control" value="${cliente.correoElectronico}" required /> --%>
<!-- 		                <div class="invalid-feedback">Ingrese un correo válido.</div> -->
<!-- 		            </div> -->
<!-- 		        </div> -->
<!-- 		        <div class="mb-3 row"> -->
<!-- 		            <div class="col-sm-12 text-center"> -->
<!-- 		                <button type="submit" class="btn btn-primary"> -->
<!-- 		                    <i class="bi bi-envelope-check me-2"></i>Actualizar Correo -->
<!-- 		                </button> -->
<!-- 		            </div> -->
<!-- 		        </div> -->
<!-- 		    </form> -->
		
<!-- 		    Formulario: Teléfono -->
<%-- 		    <form action="${pageContext.request.contextPath}/cliente/modificar" method="post" class="needs-validation mb-4" novalidate> --%>
<%-- 		        <input type="hidden" name="dni" value="${cliente.dni}" /> --%>
<!-- 		        <input type="hidden" name="field" value="telefono" /> -->
<!-- 		        <div class="mb-3 row"> -->
<!-- 		            <label for="telefono" class="col-sm-3 col-form-label">Teléfono</label> -->
<!-- 		            <div class="col-sm-9"> -->
<%-- 		                <input type="tel" id="telefono" name="telefono" class="form-control" value="${cliente.telefono}" /> --%>
<!-- 		            </div> -->
<!-- 		        </div> -->
<!-- 		        <div class="mb-3 row"> -->
<!-- 		            <div class="col-sm-12 text-center"> -->
<!-- 		                <button type="submit" class="btn btn-primary"> -->
<!-- 		                    <i class="bi bi-telephone-outbound me-2"></i>Actualizar Teléfono -->
<!-- 		                </button> -->
<!-- 		            </div> -->
<!-- 		        </div> -->
<!-- 		    </form> -->
		
<!-- 		    Formulario: Dirección -->
<%-- 		    <form action="${pageContext.request.contextPath}/cliente/modificar" method="post" class="needs-validation mb-4" novalidate> --%>
<%-- 		        <input type="hidden" name="dni" value="${cliente.dni}" /> --%>
<!-- 		        <input type="hidden" name="field" value="direccion" /> -->
<!-- 		        <div class="mb-3 row"> -->
<!-- 		            <label for="direccion" class="col-sm-3 col-form-label">Dirección</label> -->
<!-- 		            <div class="col-sm-9"> -->
<%-- 		                <input type="text" id="direccion" name="direccion" class="form-control" value="${cliente.direccion}" /> --%>
<!-- 		            </div> -->
<!-- 		        </div> -->
<!-- 		        <div class="mb-3 row"> -->
<!-- 		            <div class="col-sm-12 text-center"> -->
<!-- 		                <button type="submit" class="btn btn-primary"> -->
<!-- 		                    <i class="bi bi-geo-alt me-2"></i>Actualizar Dirección -->
<!-- 		                </button> -->
<!-- 		            </div> -->
<!-- 		        </div> -->
<!-- 		    </form> -->
		
<%-- 		</c:if> --%>


<!--         <div class="mb-3 row mt-3"> -->
<!--             <div class="col-sm-12 text-center"> -->
<%--                 <a href="${pageContext.request.contextPath}/Vistas/Administrador/MenuPrincipal/Clientes/ABMLClientes.jsp" class="btn btn-secondary"> --%>
<!--                     <i class="bi bi-box-arrow-left"></i> Volver -->
<!--                 </a> -->
<!--             </div> -->
<!--         </div> -->

<!--     </div> -->
<!-- </main> -->

<%-- <%@ include file="../../../Componentes/footer.jspf" %> --%>

<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script> -->

<!-- <script> -->
<!-- // (() => { -->
<!-- //   'use strict' -->
<!-- //   const forms = document.querySelectorAll('.needs-validation') -->
<!-- //   Array.from(forms).forEach(form => { -->
<!-- //     form.addEventListener('submit', event => { -->
<!-- //       if (!form.checkValidity()) { -->
<!-- //         event.preventDefault() -->
<!-- //         event.stopPropagation() -->
<!-- //       } -->
<!-- //       form.classList.add('was-validated') -->
<!-- //     }, false) -->
<!-- //   }) -->
<!-- // })() -->
<!-- </script> -->

<!-- </body> -->
<!-- </html> -->
