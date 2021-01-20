package ar.org.centro8.curso.java.rest;

import ar.org.centro8.curso.java.connectors.Connector;
import ar.org.centro8.curso.java.entities.Cliente;
import ar.org.centro8.curso.java.enumerados.TipoDocumento;
import ar.org.centro8.curso.java.interfaces.jdbc.ClienteRepository;
import com.google.gson.Gson;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("clientes/v1")
public class ClienteServicio {
    //Método por defacto
   private final ClienteRepository cr = new ClienteRepository(Connector.getConnection());
   @GET
   public String info(){
       return "Servicio de clientes activo";
   }
   /*
   http://localhost:8081/SistemaDeVentasWeb/clientes/v1/alta?nombre=Karol&apellido=Mor%C3%B3n&tipoDocumento=DNI&numeroDocumento=77777777&telefono=7777777777&email=karilina@gmail.com&direccion=CABA
   */
   @GET
   @Path("alta")
   //@Produces(MediaType.APPLICATION_JSON) 
   public String alta(
           @QueryParam("nombre")String nombre,
           @QueryParam("apellido")String apellido,
           @QueryParam("tipoDocumento")String tipoDocumento,
           @QueryParam("numeroDocumento")String numeroDocumento,
           @QueryParam("telefono")String telefono,
           @QueryParam("email")String email,
           @QueryParam("direccion")String direccion
           ){
    
    Cliente cliente = new Cliente(
          nombre,
          apellido,
          TipoDocumento.valueOf(tipoDocumento),
          numeroDocumento,
          telefono,
          email,
          direccion
    );
    cr.save(cliente);
   // return new Gson().toJson(cliente);  
   return cliente.getId()+"";  
}
   @GET
   @Path("baja")
   public boolean baja(@QueryParam("id")String id){
       try {
           ClienteRepository cr = new ClienteRepository(Connector.getConnection());
           return cr.remove(cr.getById(Integer.parseInt(id)));
       } catch (Exception e){
           e.printStackTrace();
       }
       return cr.remove(cr.getById(Integer.parseInt(id)));
   }
   
   @GET
   @Path("all")
   @Produces(MediaType.APPLICATION_JSON)  //Tipo de datos que se está produciendo en el resultado
   public String getAll(){
       return new Gson().toJson(cr.getAll());
   }
   
   
   @GET
   @Path("likeApellido")
   @Produces(MediaType.APPLICATION_JSON)
   public String getLikeApellido(@QueryParam("apellido")String apellido){
       ClienteRepository cr = new ClienteRepository(Connector.getConnection());
       return new Gson().toJson(cr.getLikeApellido(apellido));
   }
   
   @GET
   @Path("update")
   @Produces(MediaType.APPLICATION_JSON)
   public String update(
           @QueryParam("id")String id,
           @QueryParam("nombre")String nombre,
           @QueryParam("apellido")String apellido,
           @QueryParam("tipoDocumento")String tipoDocumento,
           @QueryParam("numeroDocumento")String numeroDocumento,
           @QueryParam("telefono")String telefono,
           @QueryParam("email")String email,
           @QueryParam("direccion")String direccion
           ){
    ClienteRepository cr=new ClienteRepository(Connector.getConnection());
    Cliente cliente = new Cliente(
          Integer.parseInt(id),  
          nombre,
          apellido,
          TipoDocumento.valueOf(tipoDocumento),
          numeroDocumento,
          telefono,
          email,
          direccion
    );
    return new Gson().toJson(cr.update(cliente));  
  }
}
