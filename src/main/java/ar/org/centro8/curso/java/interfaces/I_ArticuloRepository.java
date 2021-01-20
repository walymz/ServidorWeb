package ar.org.centro8.curso.java.interfaces;

import ar.org.centro8.curso.java.entities.Articulo;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface I_ArticuloRepository {
    boolean save(Articulo articulo);
    boolean remove(Articulo articulo);
    boolean update(Articulo articulo);
    default Articulo getById(int id){
        return getAll()
                  .stream()
                  .filter(a->a.getId()==id) 
                  .findFirst()
                  .orElse(new Articulo());
    }
    List<Articulo>getAll();//Para evitar colapso del sistema, si la bd es grande podría estar codificada la bd para devolver solo los clientes actuales
    default List<Articulo>getLikeNombre(String nombre){
        if(nombre==null || nombre.isEmpty()) return new ArrayList<Articulo>();
        return getAll()
                .stream()
                .filter(a->a.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    default List<Articulo> getArticulosConStock(){
        return getAll()
                .stream()
                .filter(a->a.getStock()>0)
                .collect(Collectors.toList());
    }
    
    default List<Articulo> getArticulosSinStock(){
        return getAll()
                .stream()
                .filter(a->a.getStock()==0)
                .collect(Collectors.toList());
    }
}
