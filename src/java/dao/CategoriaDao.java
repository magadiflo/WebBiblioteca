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
            if(ps.executeUpdate() > 0){
                return true;
            }else{
                return false;
            }
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

}
