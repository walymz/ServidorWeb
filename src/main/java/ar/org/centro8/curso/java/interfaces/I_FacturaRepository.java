package ar.org.centro8.curso.java.interfaces;

import ar.org.centro8.curso.java.entities.Factura;
import ar.org.centro8.curso.java.enumerados.Letra;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public interface I_FacturaRepository {
    boolean save(Factura factura);
    boolean remove(Factura factura);
    boolean update(Factura factura);
    
    default Factura getById(int id){
       return getAll()
                  .stream()
                  .filter(f->f.getId() == id)
                  .findFirst()
                  .orElse(new Factura());
    }
    default Factura getByLetraYNumero(Letra letra, int numero){
        return getAll()
                .stream()
                .filter(f->f.getLetra().equals(letra) && f.getNumero()==numero)
                .findFirst()
                .orElse(new Factura());
    }
    List<Factura>getAll();//Para evitar colapso del sistema, si la bd es grande podría estar codificada la bd para devolver solo los clientes actuales
    default List<Factura> getByIdCliente(int idCliente){
        return 
                getAll()
                .stream()
                .filter(f->f.getIdCliente()==idCliente)
                .collect(Collectors.toList());
    }
    default List<Factura>getLikeFecha(LocalDate fecha){
        return 
                getAll()
                .stream()
                .filter(f->f.getFecha().isEqual(fecha))
                .collect(Collectors.toList());
    }
    
     default List<Factura>getBetweenFecha(LocalDate fechaInicio, LocalDate fechaFin){
        return 
                getAll()
                .stream()
                .filter(f->f.getFecha().isBefore(fechaFin)&&f.getFecha().isAfter(fechaInicio)||f.getFecha().isEqual(fechaInicio)||f.getFecha().isEqual(fechaFin))
                .collect(Collectors.toList());
    }

}
