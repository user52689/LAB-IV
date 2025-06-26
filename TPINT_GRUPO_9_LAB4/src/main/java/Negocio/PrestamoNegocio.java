package Negocio;

import DAO.PrestamoDAO;
import DAO.CuentaDAO;
import Modelo.Prestamo;

import java.sql.SQLException;
import java.util.List;

public class PrestamoNegocio {
    private PrestamoDAO prestamoDAO;
    private CuentaDAO cuentaDAO;

   
    private static final int TIPO_MOVIMIENTO_ALTA_PRESTAMO = 15;

    public PrestamoNegocio() throws SQLException {
        this.prestamoDAO = new PrestamoDAO();
        this.cuentaDAO  = new CuentaDAO();
    }

    public List<Prestamo> listarPendientes() throws SQLException {
        return prestamoDAO.listarPorEstado(1);
    }

    public boolean aprobarPrestamo(int idPrestamo) throws SQLException {
        // 1) Cambio de estado
        boolean ok = prestamoDAO.actualizarEstado(idPrestamo, 2);
        if (!ok) return false;

        // 2) Recupero el préstamo completo
        Prestamo p = prestamoDAO.buscarPorId(idPrestamo);
        if (p == null) return false;

        // 3) Genero las cuotas
        prestamoDAO.generarCuotas(p);

        // 4) Registro movimiento + actualizo saldo
        int idCuenta = p.getCuentaDeposito().getIdCuenta();
        double importe = p.getImporteSolicitado();

        double saldoAnt = cuentaDAO.obtenerSaldo(idCuenta);
        boolean upd = cuentaDAO.actualizarSaldo(idCuenta, importe);
        double saldoPos = saldoAnt + (upd ? importe : 0);

        prestamoDAO.registrarMovimiento(
            idCuenta,
            TIPO_MOVIMIENTO_ALTA_PRESTAMO,
            importe,
            "Desembolso préstamo #" + idPrestamo,
            saldoAnt,
            saldoPos
        );

        return true;
    }

    public boolean rechazarPrestamo(int idPrestamo) throws SQLException {
        return prestamoDAO.actualizarEstado(idPrestamo, 3);
    }
}
