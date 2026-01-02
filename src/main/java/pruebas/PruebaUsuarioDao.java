package pruebas;

import java.util.List;

import dao.UsuarioDao;
import dao.impl.UsuarioDaoImpl;
import entities.Usuario;
import utilidades.HibernateUtil;

public class PruebaUsuarioDao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HibernateUtil.crearConexion();
		
		UsuarioDao dao = new UsuarioDaoImpl();
		
		//CREATE
		Usuario u = new Usuario(null, "mario4","mario4@gmail.com","58745","JUGADOR");
		dao.save(u);
		System.out.println("Usuario creado: " + u);
		Usuario u2 = new Usuario(null, "mario5","mario5@gmail.com","12335","JUGADOR");
		dao.save(u2);
		System.out.println("Usuario creado: " + u2);
		//READ
		Usuario leido = dao.findById(u.getId());
		System.out.println("Usuario encontrado: " + leido);
		//UPDATE
		leido.setPassword("1234");
		Usuario actualizado = dao.update(leido);
		System.out.println("Usuario actualizado: " + actualizado);
		//DELETE
		dao.delete(u2);
		System.out.println("Borrado OK");
		//LIST
		List<Usuario> listaUsuarios = dao.findAll();
		System.out.println("LISTA (" + listaUsuarios.size());
		//ENCONTRAR POR NOMBRE DE USUARIO
		Usuario encontrado = dao.findByUsername("mario1");
		System.out.println("ENCONTRADO CON USERNAME: " + encontrado);
		
		HibernateUtil.cerrarSessionFactory();
	}

}
