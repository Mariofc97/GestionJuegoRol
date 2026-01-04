package pruebas;

import dto.UsuarioDto;
import service.UsuarioService;
import service.impl.UsuarioServiceImpl;
import utilidades.HibernateUtil;
import utilidades.Utils;

public class PruebaUsuarioDao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		HibernateUtil.crearConexion();
//		
//		UsuarioDao dao = new UsuarioDaoImpl();
//		
//		//CREATE
//		Usuario u = new Usuario(null, "mario4","mario4@gmail.com","58745","JUGADOR");
//		dao.save(u);
//		System.out.println("Usuario creado: " + u);
//		Usuario u2 = new Usuario(null, "mario5","mario5@gmail.com","12335","JUGADOR");
//		dao.save(u2);
//		System.out.println("Usuario creado: " + u2);
//		//READ
//		Usuario leido = dao.findById(u.getId());
//		System.out.println("Usuario encontrado: " + leido);
//		//UPDATE
//		leido.setPassword("1234");
//		Usuario actualizado = dao.update(leido);
//		System.out.println("Usuario actualizado: " + actualizado);
//		//DELETE
//		dao.delete(u2);
//		System.out.println("Borrado OK");
//		//LIST
//		List<Usuario> listaUsuarios = dao.findAll();
//		System.out.println("LISTA (" + listaUsuarios.size());
//		//ENCONTRAR POR NOMBRE DE USUARIO
//		Usuario encontrado = dao.findByUsername("mario1");
//		System.out.println("ENCONTRADO CON USERNAME: " + encontrado);
//		
//		HibernateUtil.cerrarSessionFactory();
//		
		HibernateUtil.crearConexion();
		
		UsuarioService usuarioService = new UsuarioServiceImpl();
		
		boolean salir = false;
		UsuarioDto usuarioLogueado = null;
			while(!salir) {
	            System.out.println("\n--- MENU ---");
	            System.out.println("1) Registrar");
	            System.out.println("2) Login");
	            System.out.println("3) Listar usuarios");
	            System.out.println("4) Salir");
	            int op = Utils.pideDatoNumerico("Opcion: ");
	            
	            try {
					switch (op) {
					case 1:
						String u = Utils.pideDatoCadena("Username: ");
						String e = Utils.pideDatoCadena("Email: ");
						String p = Utils.pideDatoCadena("Password: ");
						String r = Utils.pideDatoCadena("Rol: ");
						
						UsuarioDto registrado = usuarioService.registrar(u, e, p, r);
						System.out.println("Usuario registrado OK -> " + registrado);
						break;
					case 2:
						String ul = Utils.pideDatoCadena("Username: ");
						String pl = Utils.pideDatoCadena("Password: ");
						
						UsuarioDto logeado = usuarioService.login(ul, pl);
						System.out.println("Usuario logueado OK -> " + logeado);
						//Aqui despues del login, ya podria crear personaje y empezar episodio 1
						break;
					case 3:
						System.out.println("Usuarios: " + usuarioService.listar());
						break;
					case 4:
						salir = true;
						break;

					default:
						System.out.println("Opcion invalida");
						break;
					}
				} catch (RuntimeException e) {
					// TODO: handle exception
					System.out.println("Error general " + e.getMessage());
				}
			}
		HibernateUtil.cerrarSessionFactory();
	}

}
