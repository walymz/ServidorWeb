package ar.org.centro8.curso.java.test;

import ar.org.centro8.curso.java.connectors.Connector;
import ar.org.centro8.curso.java.entities.Cliente;
import ar.org.centro8.curso.java.interfaces.I_ClienteRepository;
import ar.org.centro8.curso.java.interfaces.jdbc.ClienteRepository;
import com.google.gson.Gson;

public class TestGson {
    public static void main(String[] args) {
        I_ClienteRepository cr = new ClienteRepository(Connector.getConnection());
        Cliente cliente=cr.getById(2);
        System.out.println(cliente);
        Gson gson = new Gson();
        String stJson = gson.toJson(cliente);
        System.out.println(stJson);
    }
}
