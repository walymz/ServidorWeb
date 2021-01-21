package ar.org.centro8.curso.java.interfaces;

import ar.org.centro8.curso.java.entities.Cliente;
import ar.org.centro8.curso.java.enumerados.TipoDocumento;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface I_ClienteRepository {
    Cliente save(Cliente cliente);
    boolean remove(Cliente cliente);
    Cliente update(Cliente cliente);
    default Cliente getById(int id){
        return getAll()
                .stream()
                .filter(c->c.getId()==id) 
                .findFirst()
                .orElse(new Cliente());
    }
    
    default Cliente getByDocumento(TipoDocumento tipo, String numeroDocumento){
        if(numeroDocumento==null || numeroDocumento.isEmpty()) return new Cliente();
        return getAll()
                .stream()
                .filter(c->c.getTipoDocumento()==tipo && c.getNumeroDocumento()==numeroDocumento)
                .findFirst()
                .orElse(new Cliente());
    }
    List<Cliente>getAll();//Para evitar colapso del sistema, si la bd es grande podría estar codificada la bd para devolver solo los clientes actuales
    
    default List<Cliente>getLikeApellido(String apellido){
        if(apellido==null) return new ArrayList<Cliente>();
        return getAll()
                .stream()
                .filter(p->p.getApellido().toLowerCase()
                .contains(apellido.toLowerCase()))
                .collect(Collectors.toList());
    }
    default List<Cliente>getLikeNombre(String nombre){
        if(nombre==null) return new ArrayList<Cliente>();
        return getAll()
                .stream()
                .filter(p->p.getApellido().toLowerCase()
                .contains(nombre.toLowerCase()))
                .collect(Collectors.toList());
    }
}
