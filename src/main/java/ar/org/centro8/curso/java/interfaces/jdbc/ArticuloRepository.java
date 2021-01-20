package ar.org.centro8.curso.java.interfaces.jdbc;

import ar.org.centro8.curso.java.entities.Articulo;
import ar.org.centro8.curso.java.interfaces.I_ArticuloRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ArticuloRepository implements I_ArticuloRepository {
    private Connection conn;

    public ArticuloRepository(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public boolean save(Articulo articulo) {
        boolean result=false;
        if(articulo!=null){
            try (PreparedStatement ps = conn.prepareStatement("insert into articulos (nombre, precio, stock) values (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, articulo.getNombre());
                ps.setFloat(2, articulo.getPrecio());
                ps.setInt(3, articulo.getStock());
                ps.execute();
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()) articulo.setId(rs.getInt(1));
                result=true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }  
        return result;        
    }

    @Override
    public boolean remove(Articulo articulo) {
        boolean result=false;
        if(articulo!=null){
            try (PreparedStatement ps = conn.prepareStatement("delete from articulos where id=?")){
                ps.setInt(1, articulo.getId());
                ps.execute();
                result=true;
            } catch (Exception e) {
                e.printStackTrace();
            } 
        }  
        return result;
    }

    @Override
    public boolean update(Articulo articulo) {
       boolean result=false;
       if(articulo!=null){
            try (PreparedStatement ps = conn.prepareStatement("update articulos set nombre=?, precio=?, stock=? where id=?")){
                ps.setString(1, articulo.getNombre());
                ps.setFloat(2, articulo.getPrecio());
                ps.setInt(3, articulo.getStock());
                ps.setInt(4, articulo.getId());
                ps.execute();
                result=true;
            } catch (Exception e) {
                e.printStackTrace();
            }
       }   
       return result;
    }

    @Override
    public List<Articulo> getAll() {
       List<Articulo> lista = new ArrayList<Articulo>();
        try (ResultSet rs = conn.createStatement().executeQuery("select * from articulos")){
            while(rs.next()){
                lista.add(new Articulo(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getFloat("precio"),
                        rs.getInt("stock")
                     )
                );
            }
        } catch (Exception e) {
        }
 
       return lista;
    }
    
}
