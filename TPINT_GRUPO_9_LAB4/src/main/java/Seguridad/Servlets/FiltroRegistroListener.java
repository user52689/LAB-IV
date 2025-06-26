package Seguridad.Servlets;

    import javax.servlet.ServletContextEvent;
    import javax.servlet.ServletContextListener;
    import javax.servlet.annotation.WebListener;

    @WebListener
    public class FiltroRegistroListener implements ServletContextListener {
        @Override
        public void contextInitialized(ServletContextEvent sce) {
            System.out.println("FiltroRegistroListener: Registrando FiltroAcceso");
            sce.getServletContext()
               .addFilter("FiltroAcceso", FiltroAcceso.class)
               .addMappingForUrlPatterns(null, true, "/*");
        }

        @Override
        public void contextDestroyed(ServletContextEvent sce) {
            System.out.println("FiltroRegistroListener: Contexto destruido");
        }
    }