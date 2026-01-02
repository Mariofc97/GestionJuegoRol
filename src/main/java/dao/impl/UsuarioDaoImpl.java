package dao.impl;

import dao.UsuarioDao;
import entities.Usuario;

public class UsuarioDaoImpl extends GenericDaoHibernate<Usuario, Long> implements UsuarioDao {

	public UsuarioDaoImpl() {
		super(Usuario.class);
	}

	@Override
	public Usuario findByUsername(String username) {
		// TODO Auto-generated method stub
		try {
			return session()
					.createQuery("from Usuario u where u.username = :username", Usuario.class)
					.setParameter("username", username)
					.getResultStream()
					.findFirst()
					.orElse(null);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("Error buscando Usuario por username: " + username + e.getMessage());
		}
	}

	@Override
	public boolean existsByUsername(String username) {
		// TODO Auto-generated method stub
        try {
            Long count = session()
                .createQuery("select count(u.id) from Usuario u where u.username = :username", Long.class)
                .setParameter("username", username)
                .uniqueResult();

            return count != null && count > 0;
        } catch (Exception e) {
            throw new RuntimeException("Error comprobando existencia de username: " + username, e);
        }
	}

	@Override
	public boolean existsByEmail(String email) {
		// TODO Auto-generated method stub
        try {
            Long count = session()
                .createQuery("select count(u.id) from Usuario u where u.email = :email", Long.class)
                .setParameter("email", email)
                .uniqueResult();

            return count != null && count > 0;
        } catch (Exception e) {
            throw new RuntimeException("Error comprobando existencia de email: " + email, e);
        }
	}

	
}
