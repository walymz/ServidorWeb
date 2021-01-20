package ar.org.centro8.curso.java.interfaces.jdbc;

import ar.org.centro8.curso.java.entities.Detalle;
import ar.org.centro8.curso.java.interfaces.I_DetalleRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DetalleRepository implements I_DetalleRepository{
    private Connection conn;

    public DetalleRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean save(Detalle detalle) {
       boolean result=false;
       if(detalle!=null){
            try (PreparedStatement ps = conn.prepareStatement("insert into detalles (idFactura, idArticulo, cantidad) values (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS)){
                ps.setInt(1, detalle.getIdFactura());
                ps.setInt(2, detalle.getIdArticulo());
                ps.setInt(3, detalle.getCantidad());
                ps.execute();
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next())
                { detalle.setIdFactura(rs.getInt(1));
                  detalle.setIdArticulo(rs.getInt(2));
                  result=true;
                 }
            } catch (Exception e) {
                e.printStackTrace();
            }
       }  
       return result;
    }

    @Override
    public boolean remove(Detalle detalle) {
       boolean result=false;
       if(detalle!=null){
            try (PreparedStatement ps = conn.prepareStatement("delete from detalles where idFactura=? and idArticulo=?")){
                ps.setInt(1, detalle.getIdFactura());
                ps.setInt(2, detalle.getIdArticulo());
                ps.execute();
                result=true;
            } catch (Exception e) {
                e.printStackTrace();
            }
       }  
       return result;
    }

    @Override
    public boolean update(Detalle detalle) {
       boolean result=false;
       if(detalle!=null){
            try (PreparedStatement ps = conn.prepareStatement("update detalles set idFactura=?, idArticulo=?, cantidad=? where idFactura=? and idArticulo=?")){
                ps.setInt(1, detalle.getIdFactura());
                ps.setInt(2, detalle.getIdArticulo());
                ps.setInt(3, detalle.getCantidad());
                ps.setInt(4, detalle.getIdFactura());
                ps.setInt(5, detalle.getIdArticulo());
                ps.execute();
                result=true;
            } catch (Exception e) {
                e.printStackTrace();
            }
       }  
       return result;
    }

    @Override
    public List<Detalle> getAll() {
      List<Detalle> lista = new ArrayList<Detalle>();
        try (ResultSet rs = conn.createStatement().executeQuery("select * from detalles")){
            while(rs.next()){
                lista.add(new Detalle(
                        rs.getInt("idFactura"),
                        rs.getInt("idArticulo"),
                        rs.getInt("cantidad")
                       ));
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
      return lista;
    }
    
}
