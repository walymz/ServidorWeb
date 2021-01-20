package ar.org.centro8.curso.java.interfaces.jdbc;
import ar.org.centro8.curso.java.entities.Factura;
import ar.org.centro8.curso.java.enumerados.Letra;
import ar.org.centro8.curso.java.interfaces.I_FacturaRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FacturaRepository implements I_FacturaRepository{
   private Connection conn; 

    public FacturaRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean save(Factura factura) {
      boolean result = false;
        try (PreparedStatement ps = conn.prepareStatement("insert into facturas (letra, numero, fecha, monto, idCliente) values (?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
           ps.setString(1, factura.getLetra().toString());
           ps.setInt   (2, factura.getNumero());
           ps.setDate  (3, java.sql.Date.valueOf(factura.getFecha()));
           ps.setFloat (4, factura.getMonto());
           ps.setInt   (5, factura.getIdCliente());
           ps.execute();
           ResultSet rs = ps.getGeneratedKeys();
           if(rs.next()){ 
               factura.setId(rs.getInt(1));
               result=true;
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
      return result;
    }

    @Override
    public boolean remove(Factura factura) {
       boolean result=false;
        try (PreparedStatement ps = conn.prepareStatement("delete from facturas where id=?")){
            ps.setInt(1, factura.getId());
            ps.execute();
            result=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
       return result; 
    }

    @Override
    public boolean update(Factura factura) {
      boolean result=false;
        try (PreparedStatement ps = conn.prepareStatement("update facturas set letra=?, numero=?, fecha=?, monto=?, idCliente=? where id=?")){
            ps.setString(1, factura.getLetra().toString());
            ps.setInt   (2, factura.getNumero());
            ps.setDate  (3, java.sql.Date.valueOf(factura.getFecha()));
            ps.setFloat (4, factura.getMonto());
            ps.setInt   (5, factura.getIdCliente());
            ps.setInt   (6, factura.getId());
            ps.execute();
            result=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
      return result;
    }

    @Override
    public List<Factura> getAll() {
        List<Factura> lista = new ArrayList<Factura>();
        try (ResultSet rs = conn.createStatement().executeQuery("select * from facturas")) {
            while(rs.next()){
                lista.add(new Factura(
                        rs.getInt("id"),
                        Letra.valueOf(rs.getString("letra")),
                        rs.getInt("numero"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getFloat("monto"),
                        rs.getInt("idCliente")
                        ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       return lista;
    }
   
}
