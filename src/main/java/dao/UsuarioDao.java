package dao;

import entities.Usuario;

public interface UsuarioDao extends GenericDao<Usuario, Long> {

    Usuario findByUsername(String username);
    
    boolean existsByUsername(String username);
    
    boolean existsByEmail(String email);
}
