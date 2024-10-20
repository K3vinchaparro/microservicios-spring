package aprendizaje.servicios.tienda.user.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import aprendizaje.servicios.tienda.user.modelos.Usuario;
import aprendizaje.servicios.tienda.user.servicios.UsuarioService;

@Controller
@RequestMapping(value = "/usuarios")
public class UsuarioControlador {
	
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<Usuario>> listarProductos(){
		List<Usuario> usuarios = usuarioService.listaUsuarios();		
		
		if(usuarios.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(usuarios);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Usuario> getProducto(@PathVariable("id") Long id){
		Usuario usuario = usuarioService.obtenerUsuario(id);
		
		if(usuario == null) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(usuario);
	}
	
	@PostMapping
	public ResponseEntity<Usuario> guardarProducto(@RequestBody Usuario Usuario){
		Usuario usuarioCreado = usuarioService.crearUsuario(Usuario);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCreado);
	}
}
