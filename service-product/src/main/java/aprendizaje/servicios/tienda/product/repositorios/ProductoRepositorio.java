package aprendizaje.servicios.tienda.product.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import aprendizaje.servicios.tienda.product.modelos.Categoria;
import aprendizaje.servicios.tienda.product.modelos.Producto;

public interface ProductoRepositorio extends JpaRepository<Producto, Long> {

	public List<Producto> findByCategoria(Categoria categoria);
}
