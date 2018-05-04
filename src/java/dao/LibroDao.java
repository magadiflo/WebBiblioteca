package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Libro;

public class LibroDao {

    public static boolean registrar(Libro libro) {
        try {
            String sql = "INSERT INTO Libro(isbn, titulo, descripcion, "
                    + "nombreAutor, publicacion, fechaRegistro, "
                    + "codigoCategoria, nitEditorial) "
                    + "VALUES(?,?,?,?,?,(SELECT NOW()),?,?)";
            
            Connection con = Conexion.connectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, libro.getIsbn());
            ps.setString(2, libro.getTitulo());
            ps.setString(3, libro.getDescripcion());
            ps.setString(4, libro.getNombreAutor());
            ps.setString(5, libro.getPublicacion());
            ps.setInt(6, libro.getCodigoCategoria());
            ps.setString(7, libro.getNitEditorial());

            return ps.executeUpdate() > 0;//Si todo salió bien, retornará TRUE
            
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public static boolean actualizar(Libro libro) {
        try {
            String sql = "UPDATE Libro SET "
                            + "titulo = ?, "
                            + "descripcion = ?, "
                            + "nombreAutor = ?, "
                            + "publicacion = ?, "
                            + "codigoCategoria = ?, "
                            + "nitEditorial = ? "
                            + "WHERE isbn = ?";
            Connection con = Conexion.connectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getDescripcion());
            ps.setString(3, libro.getNombreAutor());
            ps.setString(4, libro.getPublicacion());
            ps.setInt(5, libro.getCodigoCategoria());
            ps.setString(6, libro.getNitEditorial());
            ps.setString(7, libro.getIsbn());

            return ps.executeUpdate() > 0;//Si todo salió bien, retornará TRUE
            
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public static boolean eliminar(Libro libro) {
        try {
            String sql = "DELETE FROM Libro WHERE isbn = ?";
            Connection con = Conexion.connectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, libro.getIsbn());

            return ps.executeUpdate() > 0;//Si todo salió bien, retornará TRUE
            
        } catch (SQLException ex) {
            return false;
        }
    }

    public static ArrayList<Libro> listar(){
        try {
            String sql = "SELECT * FROM Libro";
            Connection con = Conexion.connectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ArrayList<Libro> lista = new ArrayList<>();
            Libro libro;
            while(rs.next()){
                libro = new Libro();
                libro.setIsbn(rs.getString(1));
                libro.setTitulo(rs.getString(2));
                libro.setDescripcion(rs.getString(3));
                libro.setNombreAutor(rs.getString(4));
                libro.setPublicacion(rs.getString(5));
                libro.setFechaRegistro(rs.getString(6));
                libro.setCodigoCategoria(rs.getInt(7));
                libro.setNitEditorial(rs.getString(8));
                
                lista.add(libro);
            }
            return lista;
        } catch (SQLException ex) {
            return null;            
        }  
    }
}
