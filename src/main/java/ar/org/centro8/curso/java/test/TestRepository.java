
package ar.org.centro8.curso.java.test;

import ar.org.centro8.curso.java.connectors.Connector;
import ar.org.centro8.curso.java.entities.Articulo;
import ar.org.centro8.curso.java.entities.Cliente;
import ar.org.centro8.curso.java.entities.Detalle;
import ar.org.centro8.curso.java.entities.Factura;
import ar.org.centro8.curso.java.enumerados.Letra;
import ar.org.centro8.curso.java.enumerados.TipoDocumento;
import ar.org.centro8.curso.java.interfaces.I_ArticuloRepository;
import ar.org.centro8.curso.java.interfaces.I_ClienteRepository;
import ar.org.centro8.curso.java.interfaces.I_DetalleRepository;
import ar.org.centro8.curso.java.interfaces.I_FacturaRepository;
import ar.org.centro8.curso.java.interfaces.jdbc.ArticuloRepository;
import ar.org.centro8.curso.java.interfaces.jdbc.ClienteRepository;
import ar.org.centro8.curso.java.interfaces.jdbc.DetalleRepository;
import ar.org.centro8.curso.java.interfaces.jdbc.FacturaRepository;
import java.time.LocalDate;
import java.time.Month;

public class TestRepository {
    public static void main(String[] args) {
        
        System.out.println("////////////////   CLIENTE   //////////////////////////");
        
        I_ClienteRepository cr = new ClienteRepository(Connector.getConnection());
        
        System.out.println("===========  GETALL =================");
        cr.getAll().forEach(System.out::println);
        
        System.out.println("===========  SAVE Cliente =================");
        Cliente cliente = new Cliente("Carolina", "Larez", TipoDocumento.DNI, "66666666", "6666666666","carolina@hotmail.com","Boedo 254");
        cr.save(cliente);
        System.out.println(cliente);
        
        System.out.println("===========  GETALL DESPUÉS DE SAVE  =================");
        cr.getAll().forEach(System.out::println);
        
        System.out.println("===========  UPDATE Cliente id="+cliente.getId()+" Nombre=Carol =================");
        cliente.setNombre("Carol");
        if(cr.update(cliente)) System.out.println("El cliente ha sido modificado");
        cliente = cr.getById(cliente.getId());
        
        System.out.println("===========  GETALL DESPUÉS DEL UPDATE=================");
        cr.getAll().forEach(System.out::println);
        
        System.out.println("===========  REMOVE Cliente id="+cliente.getId()+" =================");
        System.out.println(cliente);
        if (cr.remove(cliente)) System.out.println("El cliente "+cliente.getId()+" ha sido eliminado");
        else System.out.println("El cliente no ha sido eliminado");
        
        System.out.println("===========  GETALL DESPUÉS DEL REMOVE=================");
        cr.getAll().forEach(System.out::println);
        
       
        System.out.println("////////////////   ARTÍCULO   //////////////////////////");
        
        I_ArticuloRepository ar = new ArticuloRepository(Connector.getConnection());
        
        System.out.println("===========  GETALL =================");
        ar.getAll().forEach(System.out::println);
        
        System.out.println("===========  SAVE Artículo =================");
        Articulo articulo = new Articulo("Medias", 150, 100);
        ar.save(articulo);
        System.out.println(articulo);
        
        System.out.println("===========  GETALL DESPUÉS DE SAVE  =================");
        ar.getAll().forEach(System.out::println);
        
        System.out.println("===========  UPDATE Articulo id="+articulo.getId()+" Nombre=Medias =================");
        articulo.setNombre("Pañuelos");
        if(ar.update(articulo)) System.out.println("El artículo ha sido modificado");
        articulo = ar.getById(articulo.getId());
        
        System.out.println("===========  GETALL DESPUÉS DEL UPDATE=================");
        ar.getAll().forEach(System.out::println);
         
        System.out.println("===========  REMOVE Artículo id="+articulo.getId()+" =================");
        System.out.println(articulo);
        if (ar.remove(articulo)) System.out.println("El artículo "+articulo.getId()+" ha sido eliminado");
        else System.out.println("El artículo no ha sido eliminado");
        
        System.out.println("===========  GETALL DESPUÉS DEL REMOVE=================");
        ar.getAll().forEach(System.out::println); 
        
        
        
        System.out.println("////////////////   FACTURA   //////////////////////////");
        
        I_FacturaRepository fr = new FacturaRepository(Connector.getConnection());
        
        System.out.println("=========== FACTURA: GETALL =================");
        fr.getAll().forEach(System.out::println);
        
        System.out.println("=========== FACTURA: SAVE Artículo =================");
        Factura factura = new Factura(Letra.A, 5, LocalDate.now(), 500,1);
        fr.save(factura);
        System.out.println(factura);
        
        System.out.println("=========== FACTURA: GETALL DESPUÉS DE SAVE  =================");
        fr.getAll().forEach(System.out::println);
        
        System.out.println("=========== FACTURA: UPDATE factura id="+factura.getId()+" monto=800 =================");
        factura.setMonto(800);
        if(fr.update(factura)) System.out.println("La factura ha sido modificada");
        factura = fr.getById(factura.getId());
        
        System.out.println("=========== FACTURA: GETALL DESPUÉS DEL UPDATE=================");
        fr.getAll().forEach(System.out::println);
        
        System.out.println("=========== FACTURA: REMOVE Artículo id="+factura.getId()+" =================");
        System.out.println(factura);
        if (fr.remove(factura)) System.out.println("La factura "+factura.getId()+" ha sido eliminada");
        else System.out.println("El artículo no ha sido eliminado");
        
        System.out.println("=========== FACTURA: GETALL DESPUÉS DEL REMOVE=================");
        fr.getAll().forEach(System.out::println);
        
        System.out.println("=========== FACTURA: GETBYLETRAYNUMERO Obtener la factura A-2=================");
        System.out.println(fr.getByLetraYNumero(Letra.A, 2));        
        
        System.out.println("=========== FACTURA: GETBYIDCLIENTE Obtener las facturas del cliente 2 =================");
        fr.getByIdCliente(2).forEach(System.out::println);
        
        System.out.println("=========== FACTURA: GETBYFECHA Obtener la factura con la fecha de hoy=================");
        System.out.println(LocalDate.now());
        fr.getLikeFecha(LocalDate.now()).forEach(System.out::println);
        
        System.out.println("===========  FACTURA: GETBETWEEN Obtener la factura de enero=================");
        fr.getBetweenFecha(LocalDate.of(2021, Month.JANUARY, 1),LocalDate.of(2021, Month.JANUARY, 31)).forEach(System.out::println);
        
        System.out.println("////////////////   DETALLE   //////////////////////////");
        
        I_DetalleRepository dr = new DetalleRepository(Connector.getConnection());
        
        System.out.println("=========== DETALLE: GETALL =================");
        dr.getAll().forEach(System.out::println);
        
        System.out.println("=========== DETALLE: SAVE Detalle =================");
        Detalle detalle = new Detalle(1, 1, 300);
        dr.save(detalle);
        System.out.println(detalle);
        
        System.out.println("=========== DETALLE: GETALL DESPUÉS DE SAVE  =================");
        dr.getAll().forEach(System.out::println);
        
        System.out.println("=========== DETALLE: UPDATE Detalle idFactura="+detalle.getIdFactura()+" idArticulo="+detalle.getIdArticulo()+" Cantidad=500 =================");
        detalle.setCantidad(500);
        if(dr.update(detalle)) System.out.println("El detalle ha sido modificado");
        detalle = dr.getByIdFacturaYIdArticulo(detalle.getIdFactura(), detalle.getIdArticulo());
        
        System.out.println("=========== DETALLE: GETALL DESPUÉS DEL UPDATE=================");
        dr.getAll().forEach(System.out::println);
        
        System.out.println("=========== DETALLE: REMOVE Detalle idFactura="+detalle.getIdFactura()+" idArticulo="+detalle.getIdArticulo()+" =================");
        System.out.println(detalle);
        if (dr.remove(detalle)) System.out.println("El detalle idFactura="+detalle.getIdFactura()+" idArticulo="+detalle.getIdArticulo()+" ha sido eliminado");
        else System.out.println("El cliente no ha sido eliminado");
        
        System.out.println("===========  DETALLE: GETALL DESPUÉS DEL REMOVE=================");
        dr.getAll().forEach(System.out::println);
        
        System.out.println("===========  DETALLE: GETBYFACTURA IdFactura=1=================");
        dr.getByIdFactura(1).forEach(System.out::println);
        
        System.out.println("===========  DETALLE: GETBYARTICULO IdArticulo=3=================");
        dr.getByIdArticulo(3).forEach(System.out::println);
    }
   }
