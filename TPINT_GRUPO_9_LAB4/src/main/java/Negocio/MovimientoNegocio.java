package Negocio;

import DAO.Conexion;
import DAO.CuentaDAO;
import DAO.MovimientoDAO;
import Modelo.Cuenta;
import Modelo.Movimiento;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


   public class MovimientoNegocio {
       private MovimientoDAO movimientoDAO;
       private CuentaDAO cuentaDAO;

       public MovimientoNegocio() throws SQLException {
           this.movimientoDAO = new MovimientoDAO();
           this.cuentaDAO = new CuentaDAO();
       }

       public boolean registrarTransferencia(int idCuentaOrigen, int idCuentaDestino, double monto,
                                            String detalle, String numeroReferencia) throws SQLException {
           // Validar que las cuentas no sean la misma
           if (idCuentaOrigen == idCuentaDestino) {
               throw new IllegalArgumentException("La cuenta de origen y destino no pueden ser la misma.");
           }

           // Iniciar transacción
           Connection conn = null;
           try {
               conn = Conexion.obtenerConexion();
               conn.setAutoCommit(false);

               // Obtener cuentas
               Cuenta cuentaOrigen = cuentaDAO.buscarCuentaPorId(idCuentaOrigen);
               Cuenta cuentaDestino = cuentaDAO.buscarCuentaPorId(idCuentaDestino);
               if (cuentaOrigen == null || cuentaDestino == null) {
                   throw new SQLException("Cuenta origen o destino no encontrada.");
               }

               // Calcular nuevos saldos
               double saldoAnteriorOrigen = cuentaOrigen.getSaldo();
               double saldoPosteriorOrigen = saldoAnteriorOrigen - monto;
               double saldoAnteriorDestino = cuentaDestino.getSaldo();
               double saldoPosteriorDestino = saldoAnteriorDestino + monto;

               // Registrar movimiento de salida (id_tipo_movimiento = 9 para "Transferencia Enviada")
               boolean exitoSalida = movimientoDAO.registrarMovimiento(
                   idCuentaOrigen,
                   9, // Transferencia Enviada
                   -monto,
                   detalle,
                   saldoAnteriorOrigen,
                   saldoPosteriorOrigen,
                   idCuentaDestino,
                   numeroReferencia
               );

               // Registrar movimiento de entrada (id_tipo_movimiento = 8 para "Transferencia Recibida")
               boolean exitoEntrada = movimientoDAO.registrarMovimiento(
                   idCuentaDestino,
                   8, // Transferencia Recibida
                   monto,
                   detalle,
                   saldoAnteriorDestino,
                   saldoPosteriorDestino,
                   idCuentaOrigen,
                   numeroReferencia
               );

               // Actualizar saldos
               boolean exitoSaldoOrigen = cuentaDAO.actualizarSaldo(idCuentaOrigen, -monto);
               boolean exitoSaldoDestino = cuentaDAO.actualizarSaldo(idCuentaDestino, monto);

               if (exitoSalida && exitoEntrada && exitoSaldoOrigen && exitoSaldoDestino) {
                   conn.commit();
                   return true;
               } else {
                   conn.rollback();
                   return false;
               }
           } catch (SQLException e) {
               if (conn != null) {
                   conn.rollback();
               }
               throw e;
           } finally {
               if (conn != null) {
                   try {
                       conn.setAutoCommit(true);
                       conn.close();
                   } catch (SQLException e) {
                       System.err.println("Error al cerrar la conexión: " + e.getMessage());
                   }
               }
           }
       }
       
       public List<Movimiento> listarMovimientosPorCuenta(int idCuenta) throws SQLException {
           return movimientoDAO.listarMovimientosPorCuenta(idCuenta);
       }
   }