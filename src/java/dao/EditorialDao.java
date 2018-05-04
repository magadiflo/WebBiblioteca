package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Editorial;

public class EditorialDao {

    public static boolean registrar(Editorial editorial) {
        try {
            String sql = "INSERT INTO Editorial(nit, nombre, telefono, direccion,"
                    + "email, sitioWeb) "
                    + "VALUES(?,?,?,?,?,?)";
            Connection con = Conexion.connectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, editorial.getNit());
            ps.setString(2, editorial.getNombre());
            ps.setString(3, editorial.getTelefono());
            ps.setString(4, editorial.getDireccion());
            ps.setString(5, editorial.getEmail());
            ps.setString(6, editorial.getSitioWeb());

            return ps.executeUpdate() > 0;
            
        } catch (SQLException ex) {
            return false;
        }
    }

    public static ArrayList<Editorial> listar() {
        try {
            String sql = "SELECT * FROM Editorial";
            Connection con = Conexion.connectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ArrayList<Editorial> lista = new ArrayList<>();
            Editorial editorial;
            while (rs.next()) {
                editorial = new Editorial();
                editorial.setNit(rs.getString(1));
                editorial.setNombre(rs.getString(2));
                editorial.setTelefono(rs.getString(3));
                editorial.setDireccion(rs.getString(4));
                editorial.setEmail(rs.getString(5));
                editorial.setSitioWeb(rs.getString(6));

                lista.add(editorial);
            }
            return lista;
        } catch (SQLException ex) {
            return null;
        }
    }

    public static String getEditorial(String nit) {
        try {
            String sql = "SELECT nombre FROM Editorial WHERE nit = ?";
            Connection con = Conexion.connectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nit);
            ResultSet rs = ps.executeQuery();

            return rs.next() ? rs.getString(1) : "--";

        } catch (SQLException ex) {
            return "--";
        }
    }
}
