package aprendizaje.servicios.tienda.user.implementaciones;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aprendizaje.servicios.tienda.user.modelos.Usuario;
import aprendizaje.servicios.tienda.user.repositorios.UsuarioRepositorio;
import aprendizaje.servicios.tienda.user.servicios.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Override
	public List<Usuario> listaUsuarios() {
		return usuarioRepositorio.findAll();
	}

	@Override
	public Usuario crearUsuario(Usuario usuario) {
		usuario.setEstado("CREATED");
		usuario.setCreatedAT(new Date());
		return usuarioRepositorio.save(usuario);
	}

	@Override
	public Usuario obtenerUsuario(Long id) {
		return usuarioRepositorio.findById(id).orElse(null);
	}

	@Override
	public Usuario editarUsuario(Usuario usuario) {
		Usuario usuarioDB = obtenerUsuario(usuario.getId());
        if (null == usuarioDB){
            return null;
        }
        
        usuarioDB.setNombre(usuario.getNombre());
        usuarioDB.setCorreo(usuario.getCorreo());
        
		return usuarioRepositorio.save(usuario);
	}

	@Override
	public Usuario eliminarUsuario(Long id) {
		Usuario usuarioDB = obtenerUsuario(id);
        if (null == usuarioDB){
            return null;
        }
		
        usuarioDB.setEstado("DELETED");
        return usuarioRepositorio.save(usuarioDB);
	}

}
