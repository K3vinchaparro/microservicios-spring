package aprendizaje.servicios.tienda.product.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aprendizaje.servicios.tienda.product.modelos.Categoria;
import aprendizaje.servicios.tienda.product.modelos.Producto;
import aprendizaje.servicios.tienda.product.servicios.ProductoService;

@RestController
@RequestMapping(value = "/productos")
public class ProductoControlador {
	
	@Autowired
	private ProductoService productoService;
	
	@GetMapping
	public ResponseEntity<List<Producto>> listarProductos(@RequestParam(value = "categoriaId", required = false) Long categoriaId){
		List<Producto> productos = new ArrayList<>();
		if(categoriaId == null) {
			productos = productoService.listAllProduct();
		}else {
			productos = productoService.findByCategoria(Categoria.builder().id(categoriaId).build());
		}
		
		if(productos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(productos);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Producto> getProducto(@PathVariable("id") Long id){
		Producto producto = productoService.getProducto(id);
		
		if(producto == null) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(producto);
	}
	
	@PostMapping
	public ResponseEntity<Producto> guardarProducto(@RequestBody Producto producto){
		Producto productoCreado = productoService.createProducto(producto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(producto);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Producto> editarProducto(@PathVariable("id") Long id, @RequestBody Producto producto){
		producto.setId(id);
		Producto productoEditado = productoService.updateProducto(producto);
		
		if(productoEditado == null) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(productoEditado);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Producto> eliminarProducto(@PathVariable("id") Long id){
		Producto productoEliminado = productoService.deleteProducto(id);
		
		if(productoEliminado == null) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(productoEliminado);
	}
	
	 @PatchMapping(value = "/{id}/stock")
	 public ResponseEntity<Producto> editarStockProducto(@PathVariable("id") Long id , @RequestParam(name = "cantidad", required = true) int cantidad){
		 Producto producto = productoService.updateStock(id, cantidad);
	   	if (producto == null){
	   		return ResponseEntity.notFound().build();
	    }
	   	
	   	return ResponseEntity.ok(producto);
	 }
}
