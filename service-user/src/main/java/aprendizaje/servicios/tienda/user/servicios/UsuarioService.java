package aprendizaje.servicios.tienda.user.servicios;

import java.util.List;

import aprendizaje.servicios.tienda.user.modelos.Usuario;

public interface UsuarioService {
	
	public List<Usuario> listaUsuarios();
	public Usuario crearUsuario(Usuario usuario);
	public Usuario obtenerUsuario(Long id);
	public Usuario editarUsuario(Usuario usuario);
	public Usuario eliminarUsuario(Long id);
}
