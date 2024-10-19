package aprendizaje.servicios.tienda.product.implementaciones;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aprendizaje.servicios.tienda.product.modelos.Categoria;
import aprendizaje.servicios.tienda.product.modelos.Producto;
import aprendizaje.servicios.tienda.product.repositorios.ProductoRepositorio;
import aprendizaje.servicios.tienda.product.servicios.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService{
	
	@Autowired
	private ProductoRepositorio productoRepositorio;

	@Override
	public List<Producto> listAllProduct() {
		return productoRepositorio.findAll();
	}

	@Override
	public Producto getProducto(Long id) {
		return productoRepositorio.findById(id).orElse(null);
	}

	@Override
	public Producto createProducto(Producto producto) {
		producto.setEstado("CREATED");
		producto.setCreateAt(new Date());
		return productoRepositorio.save(producto);
	}

	@Override
	public Producto updateProducto(Producto producto) {
		Producto productoDB = getProducto(producto.getId());
        if (null == productoDB){
            return null;
        }
        productoDB.setNombre(producto.getNombre());
        productoDB.setDescripcion(producto.getDescripcion());
        productoDB.setCategoria(producto.getCategoria());
        productoDB.setPrecio(producto.getPrecio());
        return productoRepositorio.save(productoDB);
	}

	@Override
	public Producto deleteProducto(Long id) {
		Producto productoDB = getProducto(id);
        if (null == productoDB){
            return null;
        }
        
        productoDB.setEstado("DELETED");
        return productoRepositorio.save(productoDB);
	}

	@Override
	public List<Producto> findByCategoria(Categoria categoria) {
		return productoRepositorio.findByCategoria(categoria);
	}

	@Override
	public Producto updateStock(Long id, int cantidad) {
		Producto productoDB = getProducto(id);
        if (null == productoDB){
            return null;
        }
        
        productoDB.setStock(cantidad);
        return productoRepositorio.save(productoDB);
	}

}
