package ar.org.centro8.curso.java.interfaces;

import ar.org.centro8.curso.java.entities.Detalle;
import java.util.List;
import java.util.stream.Collectors;

public interface I_DetalleRepository {
    boolean save(Detalle detalle);
    boolean remove(Detalle detalle);
    boolean update(Detalle detalle);
    default Detalle getByIdFacturaYIdArticulo(int idFactura, int idArticulo){
        return getAll()
                  .stream()
                  .filter(d->d.getIdFactura()==idFactura && d.getIdArticulo()==idArticulo)
                  .findFirst()
                  .orElse(new Detalle());
    }
    default List<Detalle> getByIdFactura(int idFactura){
        return getAll()
                .stream()
                .filter(d->d.getIdFactura()==idFactura)
                .collect(Collectors.toList());
    }
    
    default List<Detalle> getByIdArticulo(int idArticulo){
        return getAll()
                .stream()
                .filter(d->d.getIdArticulo()==idArticulo)
                .collect(Collectors.toList());
    }
    List<Detalle>getAll();//Para evitar colapso del sistema, si la bd es grande podría estar codificada la bd para devolver solo los clientes actuales
}
