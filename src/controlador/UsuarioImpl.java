package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class UsuarioImpl {

    // Debes tener una conexión a la base de datos
    private Connection conexion;

    // Constructor que recibe la conexión a la base de datos
    public UsuarioImpl(Connection conexion) {
        this.conexion = conexion;
    }

    public String generarCode() {
        String TwoFactorCode = String.valueOf(new Random().nextInt(9999) + 1000);
        return TwoFactorCode;
    }

    public String reenviarCode(String id) throws SQLException {
        String sql = "UPDATE USUARIO SET CODE = ?, EXPIRETIME = ? WHERE IDUSU = ?";
        String nuevoCode = generarCode();
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            // Obtienes la hora actual (milisegundos) y le sumas más 30 segundos.
            // Es el tiempo que tendrá para ingresar el código
            ps.setString(1, nuevoCode);
            ps.setLong(2, System.currentTimeMillis() / 1000 + 30);
            ps.setString(3, id);
            ps.executeUpdate();
        }
        return nuevoCode;
    }

    public String verificarCode(String id, String code) throws SQLException {
        String idUsuario = null;
        ResultSet rs;
        String sql = "SELECT IDUSU FROM USUARIO WHERE CODE = ? AND IDUSU = ? AND EXPIRETIME >= ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, code);
            ps.setString(2, id);
            ps.setLong(3, System.currentTimeMillis() / 1000);
            rs = ps.executeQuery();
            if (rs.next()) {
                idUsuario = rs.getString(1);
            }
        }
        return idUsuario;
    }
}
