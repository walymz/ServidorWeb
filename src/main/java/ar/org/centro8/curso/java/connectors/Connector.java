package ar.org.centro8.curso.java.connectors;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Connector {
 // private static String driver = "com.mysql.jdbc.Driver"; //driver mysql 5

  private static String driver = "com.mysql.cj.jdbc.Driver";  //driver mysql 6.0 o superior
  private static String vendor="mysql";
  private static String port="3306";
  
  //Conexión local

  private static String server="localhost";
  private static String db="dbventas";
  private static String user="root";
  private static String pass="";
  private static String params="?serverTimezone=UTC";
 
/*
  //Conexión remota
  
  private static String server="db4free.net";
  private static String db="bdturnos";
  private static String user="bdturnos";
  private static String pass="centro8java";
  private static String params="";
  
  */
  
  private static String url = "jdbc:"+vendor+"://"+server+":"+port+"/"+db+params;
       
/* 
Esta cadena funcina:
 jdbc:mysql://localhost:3306/bdturnos?serverTimezone=UTC
  */

  /**
   * Si se desea utilizar varias BD quitar el modificador static
   */
  private static Connection conn=null;
  
  /*
   *Se declara el constructor privado para evitar que 
   * se generen instancias de la clase desde otros puntos, de manera tal que 
   * la única manera de generar una conexión sea a través de un método público 
   * diseñado para tal fin
   */
  private Connector(){ } 
  public synchronized static Connection getConnection(){
      /**
       * conn es closeable, pero se evita colocarlo en el try justo para que se mantenga abierto
       * durante toda la ejecución del programa
       */
      try {
          if(conn==null || conn.isClosed()){
              Class.forName(driver); //Se registra el driver en memoria sin crear una nueva conexión
              conn = DriverManager.getConnection(url, user, pass);
              /*
              Se obtiene una nueva conexión con los datos especificados
              */
          }
      } catch (Exception e) { 
          JOptionPane.showMessageDialog(null, "Error: "+e, "Error de conexión:"+url, 0, null);
      }
  
    return conn;
  }
}
