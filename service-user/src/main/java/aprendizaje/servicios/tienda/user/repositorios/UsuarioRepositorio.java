package aprendizaje.servicios.tienda.user.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import aprendizaje.servicios.tienda.user.modelos.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{

}
