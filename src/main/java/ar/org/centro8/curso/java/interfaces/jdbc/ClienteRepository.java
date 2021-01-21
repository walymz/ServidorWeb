package ar.org.centro8.curso.java.interfaces.jdbc;

import ar.org.centro8.curso.java.entities.Cliente;
import ar.org.centro8.curso.java.enumerados.TipoDocumento;
import ar.org.centro8.curso.java.interfaces.I_ClienteRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepository implements I_ClienteRepository{
    private Connection conn;

    public ClienteRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Cliente> getAll() {
       List<Cliente> lista = new ArrayList<Cliente>();
        try (ResultSet rs=conn.createStatement().executeQuery("select * from clientes")) {
            while(rs.next()){
                lista.add(new Cliente(
                              rs.getInt("id"),
                              rs.getString("nombre"),
                              rs.getString("apellido"),
                              TipoDocumento.valueOf(rs.getString("tipoDocumento")),
                              rs.getString("numeroDocumento"),
                              rs.getString("telefono"),
                              rs.getString("email"),
                              rs.getString("direccion")
                           )
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       return lista;
    }

    @Override
    public Cliente save(Cliente cliente) {
      if(cliente!=null){
        try(PreparedStatement ps = conn.prepareStatement(
                "insert into clientes "
                    + "(nombre, apellido, tipoDocumento, numeroDocumento, telefono, email, direccion) "
                    + "values (?,?,?,?,?,?,?)", 
                PreparedStatement.RETURN_GENERATED_KEYS)){
                    ps.setString(1, cliente.getNombre());
                    ps.setString(2, cliente.getApellido());
                    ps.setString(3, cliente.getTipoDocumento().toString());
                    ps.setString(4, cliente.getNumeroDocumento());
                    ps.setString(5, cliente.getTelefono());
                    ps.setString(6, cliente.getEmail());
                    ps.setString(7, cliente.getDireccion());
                    ps.execute();
                    ResultSet rs = ps.getGeneratedKeys();
                    if(rs.next()){ 
                        cliente.setId(rs.getInt(1));
                    }

            }catch(Exception e){
                e.printStackTrace();
                cliente=null;
            }
      }
      return cliente;
    }

    @Override
    public boolean remove(Cliente cliente) {
        boolean result=false;
        if(cliente!=null){ 
            try (PreparedStatement ps = conn.prepareStatement("delete from clientes where id=?")) {
                ps.setInt(1, cliente.getId());
                ps.execute();
                result=true;
            } catch (Exception e) {
               e.printStackTrace();
          }
        }    
     return result;   
    }

    @Override
    public Cliente update(Cliente cliente) {
     //  boolean result=false;
       if(cliente!=null){
            try (PreparedStatement ps = conn.prepareStatement("update clientes set nombre=?, apellido=?, tipoDocumento=?, numeroDocumento=?, telefono=?, email=?, direccion=? where id=?")) {
                ps.setString(1, cliente.getNombre());
                ps.setString(2, cliente.getApellido());
                ps.setString(3, cliente.getTipoDocumento().toString());
                ps.setString(4, cliente.getNumeroDocumento());
                ps.setString(5, cliente.getTelefono());
                ps.setString(6, cliente.getEmail());
                ps.setString(7, cliente.getDireccion());
                ps.setInt(8, cliente.getId());
                ps.execute();
             
            } catch (Exception e) {
                e.printStackTrace();
                cliente=null;
            } 
        }
       return cliente;
    }
}
