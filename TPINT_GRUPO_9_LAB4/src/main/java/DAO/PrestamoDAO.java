package DAO;

import Modelo.Prestamo;
import Modelo.EstadoPrestamo;
import Modelo.Cliente;
import Modelo.Cuenta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO {
    private Connection conexion;

    public PrestamoDAO() throws SQLException {
        this.conexion = Conexion.obtenerConexion();
    }

    /** Lista préstamos por estado */
    public List<Prestamo> listarPorEstado(int idEstado) throws SQLException {
        String sql = """
            SELECT p.id_prestamo,
                   p.id_cliente,
                   p.id_cuenta_deposito,
                   p.id_estado_prestamo,
                   p.importe_solicitado,
                   p.importe_total,
                   p.plazo_meses,
                   p.monto_cuota,
                   p.fecha_solicitud,
                   p.fecha_resolucion,
                   p.observaciones,
                   p.activo      AS prestamo_activo,
                   e.descripcion AS estado_desc,
                   e.activo      AS estado_activo,
                   c.dni,
                   c.nombre,
                   c.apellido
              FROM prestamos p
              JOIN estados_prestamo e ON p.id_estado_prestamo = e.id_estado_prestamo
              JOIN clientes c          ON p.id_cliente          = c.id_cliente
             WHERE p.id_estado_prestamo = ?
               AND p.activo = TRUE
        """;
        List<Prestamo> lista = new ArrayList<>();
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idEstado);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Prestamo p = new Prestamo();
                    p.setIdPrestamo(rs.getInt("id_prestamo"));
                    p.setImporteSolicitado(rs.getDouble("importe_solicitado"));
                    p.setImporteTotal(rs.getDouble("importe_total"));
                    p.setPlazoMeses(rs.getInt("plazo_meses"));
                    p.setMontoCuota(rs.getDouble("monto_cuota"));
                    p.setFechaSolicitud(rs.getTimestamp("fecha_solicitud").toLocalDateTime());
                    Timestamp tr = rs.getTimestamp("fecha_resolucion");
                    if (tr != null) p.setFechaResolucion(tr.toLocalDateTime());
                    p.setObservaciones(rs.getString("observaciones"));
                    p.setActivo(rs.getBoolean("prestamo_activo"));

                    Cliente cli = new Cliente();
                    cli.setIdCliente(rs.getInt("id_cliente"));
                    cli.setDni(rs.getString("dni"));
                    cli.setNombre(rs.getString("nombre"));
                    cli.setApellido(rs.getString("apellido"));
                    p.setCliente(cli);

                    Cuenta cu = new Cuenta();
                    cu.setIdCuenta(rs.getInt("id_cuenta_deposito"));
                    p.setCuentaDeposito(cu);

                    EstadoPrestamo est = new EstadoPrestamo();
                    est.setIdEstadoPrestamo(rs.getInt("id_estado_prestamo"));
                    est.setDescripcion(rs.getString("estado_desc"));
                    est.setActivo(rs.getBoolean("estado_activo"));
                    p.setEstadoPrestamo(est);

                    lista.add(p);
                }
            }
        }
        return lista;
    }

    /** Actualiza el estado de un préstamo */
    public boolean actualizarEstado(int idPrestamo, int nuevoEstado) throws SQLException {
        String sql = """
            UPDATE prestamos
               SET id_estado_prestamo = ?,
                   fecha_resolucion   = NOW()
             WHERE id_prestamo = ?
        """;
        System.out.println("[DEBUG] Ejecutando UPDATE estado para préstamo ID: " + idPrestamo);

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, nuevoEstado);
            ps.setInt(2, idPrestamo);
            return ps.executeUpdate() > 0;
        }
    }

    /** Recupera un préstamo completo por su ID */
   /* public Prestamo buscarPorId(int idPrestamo) throws SQLException {
        String sql = """
            SELECT p.id_prestamo,
                   p.id_cliente,
                   p.id_cuenta_deposito,
                   p.id_estado_prestamo,
                   p.importe_solicitado,
                   p.importe_total,
                   p.plazo_meses,
                   p.monto_cuota,
                   p.fecha_solicitud,
                   p.fecha_resolucion,
                   p.observaciones,
                   p.activo,
                   e.descripcion AS estado_desc,
                   e.activo      AS estado_activo,
                   c.dni, c.nombre, c.apellido
              FROM prestamos p
              JOIN estados_prestamo e ON p.id_estado_prestamo = e.id_estado_prestamo
              JOIN clientes c          ON p.id_cliente          = c.id_cliente
             WHERE p.id_prestamo = ?
        """;
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idPrestamo);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;
                Prestamo p = new Prestamo();
                p.setIdPrestamo(rs.getInt("id_prestamo"));
                p.setImporteSolicitado(rs.getDouble("importe_solicitado"));
                p.setImporteTotal(rs.getDouble("importe_total"));
                p.setPlazoMeses(rs.getInt("plazo_meses"));
                p.setMontoCuota(rs.getDouble("monto_cuota"));
                p.setFechaSolicitud(rs.getTimestamp("fecha_solicitud").toLocalDateTime());
                Timestamp tr = rs.getTimestamp("fecha_resolucion");
                if (tr != null) p.setFechaResolucion(tr.toLocalDateTime());
                p.setObservaciones(rs.getString("observaciones"));
                p.setActivo(rs.getBoolean("activo"));

                Cliente cli = new Cliente();
                cli.setIdCliente(rs.getInt("id_cliente"));
                cli.setDni(rs.getString("dni"));
                cli.setNombre(rs.getString("nombre"));
                cli.setApellido(rs.getString("apellido"));
                p.setCliente(cli);

                Cuenta cu = new Cuenta();
                cu.setIdCuenta(rs.getInt("id_cuenta_deposito"));
                p.setCuentaDeposito(cu);

                EstadoPrestamo est = new EstadoPrestamo();
                est.setIdEstadoPrestamo(rs.getInt("id_estado_prestamo"));
                est.setDescripcion(rs.getString("estado_desc"));
                est.setActivo(rs.getBoolean("estado_activo"));
                p.setEstadoPrestamo(est);

                return p;
            }
        }
    }*/

    /** Genera las cuotas (una por mes), sólo si no existen aún */
    public void generarCuotas(Prestamo p) throws SQLException {
        // ¿Ya hay cuotas para este préstamo?
        String countSql = "SELECT COUNT(*) FROM cuotas WHERE id_prestamo = ?";
        try (PreparedStatement psCount = conexion.prepareStatement(countSql)) {
            psCount.setInt(1, p.getIdPrestamo());
            try (ResultSet rs = psCount.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) return; // ya creadas
            }
        }
        // Si no, las inserto
        String insertSql = """
            INSERT INTO cuotas (
              id_prestamo,
              numero_cuota,
              monto_cuota,
              fecha_vencimiento,
              activo
            ) VALUES (?, ?, ?, ?, TRUE)
        """;
        try (PreparedStatement ps = conexion.prepareStatement(insertSql)) {
            for (int i = 1; i <= p.getPlazoMeses(); i++) {
                ps.setInt(1, p.getIdPrestamo());
                ps.setInt(2, i);
                ps.setDouble(3, p.getMontoCuota());
                java.sql.Date venc = java.sql.Date.valueOf(
                    p.getFechaSolicitud().toLocalDate().plusMonths(i)
                );
                ps.setDate(4, venc);
                ps.executeUpdate();
            }
        }
    }

    /** Registra un movimiento con saldo anterior y posterior */
    public void registrarMovimiento(
            int idCuenta,
            int idTipoMovimiento,
            double importe,
            String detalle,
            double saldoAnterior,
            double saldoPosterior
        ) throws SQLException {
        String sql = """
            INSERT INTO movimientos (
              id_cuenta,
              id_tipo_movimiento,
              importe,
              detalle,
              fecha_movimiento,
              saldo_anterior,
              saldo_posterior,
              activo
            ) VALUES (?, ?, ?, ?, NOW(), ?, ?, TRUE)
        """;
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idCuenta);
            ps.setInt(2, idTipoMovimiento);
            ps.setDouble(3, importe);
            ps.setString(4, detalle);
            ps.setDouble(5, saldoAnterior);
            ps.setDouble(6, saldoPosterior);
            ps.executeUpdate();
        }
    }
    
    public int agregar(Prestamo p) throws SQLException {
        String sql = """
            INSERT INTO prestamos (
              id_cliente,
              id_cuenta_deposito,
              id_estado_prestamo,
              importe_solicitado,
              importe_total,
              plazo_meses,
              monto_cuota,
              fecha_solicitud,
              observaciones,
              activo
            ) VALUES (?, ?, ?, ?, ?, ?, ?, NOW(), ?, TRUE)
        """;
        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, p.getCliente().getIdCliente());
            ps.setInt(2, p.getCuentaDeposito().getIdCuenta());
            ps.setInt(3, p.getEstadoPrestamo().getIdEstadoPrestamo()); // Por ejemplo: 1 = Pendiente
            ps.setDouble(4, p.getImporteSolicitado());
            ps.setDouble(5, p.getImporteTotal());
            ps.setInt(6, p.getPlazoMeses());
            ps.setDouble(7, p.getMontoCuota());
            ps.setString(8, p.getObservaciones());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return -1;
    }
    
    public List<Prestamo> listarPorCliente(int idCliente) throws SQLException {
        String sql = """
            SELECT p.id_prestamo,
                   p.importe_total,
                   p.fecha_solicitud,
                   p.id_estado_prestamo,
                   e.descripcion AS estado
              FROM prestamos p
              JOIN estados_prestamo e ON p.id_estado_prestamo = e.id_estado_prestamo
             WHERE p.id_cliente = ? AND p.activo = TRUE
        """;

        List<Prestamo> lista = new ArrayList<>();
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Prestamo p = new Prestamo();
                    p.setIdPrestamo(rs.getInt("id_prestamo"));
                    p.setImporteTotal(rs.getDouble("importe_total"));
                    p.setFechaSolicitud(rs.getTimestamp("fecha_solicitud").toLocalDateTime());

                    EstadoPrestamo estado = new EstadoPrestamo();
                    estado.setIdEstadoPrestamo(rs.getInt("id_estado_prestamo"));
                    estado.setDescripcion(rs.getString("estado"));
                    p.setEstadoPrestamo(estado);

                    p.setSaldoPendiente(p.getImporteTotal());

                    lista.add(p);
                }
            }
        }
        System.out.println("[DEBUG] listarPorCliente idCliente=" + idCliente + ", préstamos encontrados: " + lista.size());
        for (Prestamo p : lista) {
            System.out.println("[DEBUG] Préstamo ID: " + p.getIdPrestamo() + ", Importe: " + p.getImporteTotal() + ", Saldo: " + p.getSaldoPendiente() + ", Estado: " + p.getEstadoPrestamo().getDescripcion());
        }
        return lista;
    }


    
    public Prestamo buscarPorId(int idPrestamo) throws SQLException {
        String sql = """
            SELECT p.id_prestamo, p.importe_solicitado, p.importe_total, 
                   p.plazo_meses, p.monto_cuota, p.fecha_solicitud, 
                   p.fecha_resolucion, p.observaciones, p.activo,
                   p.id_cliente, p.id_cuenta_deposito, 
                   ep.id_estado_prestamo, ep.descripcion AS estado_desc
            FROM prestamos p
            JOIN estados_prestamo ep ON p.id_estado_prestamo = ep.id_estado_prestamo
            WHERE p.id_prestamo = ?
        """;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idPrestamo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Prestamo p = new Prestamo();

                    p.setIdPrestamo(rs.getInt("id_prestamo"));
                    p.setImporteSolicitado(rs.getDouble("importe_solicitado"));
                    p.setImporteTotal(rs.getDouble("importe_total"));
                    p.setPlazoMeses(rs.getInt("plazo_meses"));
                    p.setMontoCuota(rs.getDouble("monto_cuota"));
                    p.setFechaSolicitud(rs.getTimestamp("fecha_solicitud").toLocalDateTime());
                    Timestamp res = rs.getTimestamp("fecha_resolucion");
                    if (res != null) p.setFechaResolucion(res.toLocalDateTime());
                    p.setObservaciones(rs.getString("observaciones"));
                    p.setActivo(rs.getBoolean("activo"));

                    // Estado
                    EstadoPrestamo estado = new EstadoPrestamo();
                    estado.setIdEstadoPrestamo(rs.getInt("id_estado_prestamo"));
                    estado.setDescripcion(rs.getString("estado_desc"));
                    p.setEstadoPrestamo(estado);

                    // Cliente
                    Cliente cliente = new Cliente();
                    cliente.setIdCliente(rs.getInt("id_cliente"));
                    p.setCliente(cliente);

                    // Cuenta
                    Cuenta cuenta = new Cuenta();
                    cuenta.setIdCuenta(rs.getInt("id_cuenta_deposito"));
                    p.setCuentaDeposito(cuenta);

                    return p;
                }
            }
        }
        return null;
    }
    
    /** Lista préstamos por cliente con saldo pendiente calculado desde cuotas */
    public List<Prestamo> listarPorClienteConSaldo(int idCliente) throws SQLException {
        String sql = """
            SELECT p.id_prestamo,
                   p.importe_total,
                   p.fecha_solicitud,
                   p.id_estado_prestamo,
                   e.descripcion AS estado,
                   COALESCE((
                       SELECT SUM(c.monto_cuota)
                       FROM cuotas c
                       WHERE c.id_prestamo = p.id_prestamo
                       AND c.fecha_pago IS NULL
                       AND c.activo = TRUE
                   ), p.importe_total) AS saldo_pendiente
              FROM prestamos p
              JOIN estados_prestamo e ON p.id_estado_prestamo = e.id_estado_prestamo
             WHERE p.id_cliente = ? AND p.activo = TRUE
        """;

        List<Prestamo> lista = new ArrayList<>();
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Prestamo p = new Prestamo();
                    p.setIdPrestamo(rs.getInt("id_prestamo"));
                    p.setImporteTotal(rs.getDouble("importe_total"));
                    p.setSaldoPendiente(rs.getDouble("saldo_pendiente"));
                    p.setFechaSolicitud(rs.getTimestamp("fecha_solicitud").toLocalDateTime());

                    EstadoPrestamo estado = new EstadoPrestamo();
                    estado.setIdEstadoPrestamo(rs.getInt("id_estado_prestamo"));
                    estado.setDescripcion(rs.getString("estado"));
                    p.setEstadoPrestamo(estado);

                    lista.add(p);
                }
            }
        }
        System.out.println("[DEBUG] listarPorClienteConSaldo idCliente=" + idCliente + ", préstamos encontrados: " + lista.size());
        for (Prestamo p : lista) {
            System.out.println("[DEBUG] Préstamo ID: " + p.getIdPrestamo() + ", Importe: " + p.getImporteTotal() + ", Saldo: " + p.getSaldoPendiente() + ", Estado: " + p.getEstadoPrestamo().getDescripcion());
        }
        return lista;
    }


}
