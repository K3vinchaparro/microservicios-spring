package aprendizaje.servicios.tienda.product.servicios;

import java.util.List;

import aprendizaje.servicios.tienda.product.modelos.Categoria;
import aprendizaje.servicios.tienda.product.modelos.Producto;

public interface ProductoService {
	public List<Producto> listAllProduct();
    public Producto getProducto(Long id);

    public Producto createProducto(Producto product);
    public Producto updateProducto(Producto product);
    public  Producto deleteProducto(Long id);
    public List<Producto> findByCategoria(Categoria categoria);
    public Producto updateStock(Long id, int quantity);

}
