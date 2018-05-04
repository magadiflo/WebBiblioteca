package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Categoria;

public class CategoriaDao {
    
    public static boolean registrar(Categoria categoria){
        try {
            String sql = "INSERT INTO Categoria(nombre) VALUES(?)";
            Connection con = Conexion.connectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, categoria.getNombre());
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException ex) {
            return false;
        }  
    }
    
    public static ArrayList<Categoria> listar(){
        try {
            String sql = "SELECT * FROM Categoria";
            Connection con = Conexion.connectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ArrayList<Categoria> lista = new ArrayList<>();
            Categoria categoria;
            while(rs.next()){
                categoria = new Categoria();
                categoria.setCodigo(rs.getInt("codigo"));
                categoria.setNombre(rs.getString("nombre")); 
                lista.add(categoria);
            }
            
            return lista;
            
        } catch (SQLException ex) {
            return null;            
        }  
    }
    
    public static String getCategoria(int codigo) {
        try {
            String sql = "SELECT nombre FROM Categoria WHERE codigo = ?";
            Connection con = Conexion.connectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            
            return rs.next() ? rs.getString(1) : "--";

        } catch (SQLException ex) {
            return "--";
        }
    }

}
