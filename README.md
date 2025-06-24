RBAC Funcional falta solucionar algunos problemas 
Mensaje de error en login.jsp: No se muestra al ingresar credenciales incorrectas; redirige a /TPINT_GRUPO_8_LAB4/LoginServlet y arroja un HTTP 404 para /Vistas/Inicio/login.jsp.
Acceso denegado no funciona: FiltroAccesable no bloquea accesos no autorizados (por ejemplo, ADMIN accediendo a rutas de CLIENTE o viceversa).
Botón de logout: Redirige a /TPINT_GRUPO_8_LAB4/login.jsp (incorrecto) en lugar de /Vistas/Inicio/login.jsp, causando un HTTP 404.

para ejecutar la añadir la libreria el .jar en el Buil path y en el Deployment Assembly que se encuentra en el proyecto
