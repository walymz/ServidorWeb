package ar.org.centro8.curso.java.test;

import ar.org.centro8.curso.java.connectors.Connector;
import ar.org.centro8.curso.java.enumerados.TipoDocumento;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;


public class TestConnector {
     public static void main(String[] args) throws SQLException, ParseException {
         Connector
                 .getConnection()
                 .createStatement()
                 .execute("delete from clientes where numeroDocumento='11111111'");
       
         Connector
               .getConnection()
               .createStatement()
               .execute("insert into clientes "
                       + "(nombre,apellido,tipoDocumento, numeroDocumento, telefono, email, direccion)"
                       + " values "
                       + "('juan','perez','DNI','11111111','1111111111', 'juancito@gmail.com','peru 323')");
       
            
       
       //Probando consultas select
       ResultSet rs = Connector.getConnection()
                               .createStatement()
                               .executeQuery("select * from clientes"); //executeQuery devuelve un rs
       
       while(rs.next()){
           System.out.println(
                   rs.getInt("id")+ " " +
                   TipoDocumento.valueOf(rs.getString("tipoDocumento"))+ ' ' +
                   rs.getString("numeroDocumento") + ' ' +
                   rs.getString("nombre") + ' ' +
                   rs.getString("apellido") + ' ' +
                   rs.getString("telefono") + ' ' +
                   rs.getString("email") + ' ' +
                   rs.getString("direccion") + ' ' 
           );
           
       }
    } 

       
}
