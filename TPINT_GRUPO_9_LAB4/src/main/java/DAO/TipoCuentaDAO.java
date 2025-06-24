package DAO;

import java.sql.*;
import Modelo.TipoCuenta;

public class TipoCuentaDAO {
	
	public TipoCuenta mapearTipoCuenta(ResultSet rs) throws SQLException {
    	TipoCuenta t = new TipoCuenta();
        t.setIdTipoCuenta(rs.getInt("id_tipo_cuenta"));
        t.setDescripcion(rs.getString("descripcion"));
        t.setActivo(rs.getBoolean("activo"));
        return t;
    }

}
