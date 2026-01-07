package pruebas;

import java.util.List;

import dto.UsuarioDto;
import entities.Personaje;
import entities.criatura.Conejo;
import service.PersonajeService;
import service.UsuarioService;
import service.impl.PersonajeServiceImpl;
import service.impl.UsuarioServiceImpl;
import utilidades.HibernateUtil;
import utilidades.Utils;

public class PruebaCapas {

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
		PersonajeService personajeService = new PersonajeServiceImpl();
		
		boolean salir = false;
		UsuarioDto usuarioLogueado = null;
		Personaje personajeCreado = null;
			while(!salir) {
	            System.out.println("\n--- MENU ---");
	            System.out.println("1) Registrar");
	            System.out.println("2) Login");
	            System.out.println("3) Listar usuarios");
	            System.out.println("4) Salir");
	            System.out.println("5) Crear personaje");
	            //System.out.println("6) Jugar Episodio 1");
	            System.out.println("7) Cerrar sesión");
	            System.out.println("8) Listar personajes por usuario");
	            System.out.println("9) TEST: añadir Cuerda y guardar");
	            System.out.println("10) TEST: AÑADIR CRIATURA A PERSONAJE CREADO");
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

					    usuarioLogueado = usuarioService.login(ul, pl);
					    System.out.println("Usuario logueado OK -> " + usuarioLogueado);

					    List<Personaje> personajes = personajeService.listarPorUsuario(usuarioLogueado.getId());

					    if (personajes.isEmpty()) {
					        personajeCreado = null;
					        System.out.println("No tienes personajes todavía. Crea uno con opción 5.");
					        break;
					    } else {
					        System.out.println("Elige personaje:");
					        for (int i = 0; i < personajes.size(); i++) {
					            System.out.println((i + 1) + ") " + personajes.get(i).getNombre()
					                    + " [" + personajes.get(i).getRazaTipo() + "]"
					                    + " (id=" + personajes.get(i).getId() + ")");
					        }

					        int idx = Utils.pideDatoNumerico("Opción: ") - 1;
					        if (idx < 0 || idx >= personajes.size()) {
					            personajeCreado = null;
					            System.out.println("Opción inválida.");
					            break;
					        }
					        personajeCreado = personajes.get(idx);
					    }

					    System.out.println("Personaje activo: " + personajeCreado);
					 
					    break;
					case 3:
						System.out.println("Usuarios: " + usuarioService.listar());
						break;
					case 4:
						salir = true;
						break;
					case 5:
						if (usuarioLogueado == null) {
							System.out.println("Para crear un personaje debes de hacer login primero");
							break;
						}
						String name = Utils.pideDatoCadena("Nombre de personaje: ");
						String raza = Utils.pideDatoCadena("Raza (MONGOL, RAPA NUI, TROGLODITA): ");
						personajeCreado = personajeService.crearYGuardar(usuarioLogueado.getId(), name, raza);
						
						// MARIO: NO SE PORQUE EN EL CASE 8, NO SE LISTAN LOS PERSONAJES DEL USUARIO.
						System.out.println("A que usuario pertenece ese personaje: f.k " 
							    + (personajeCreado.getUsuario() == null ? "NULL" : personajeCreado.getUsuario().getId()));
						break;
					case 6:
					    if (usuarioLogueado == null) {
					        System.out.println("Debes hacer login primero.");
					        break;
					    }
					    // aquí empiezas el episodio (más adelante lo moveremos a service)
					    //Epsodio1.episodio1(personajeCreado)
						break;
					case 7:
						usuarioLogueado = null;
						System.out.println("Sesion de usuario " + usuarioLogueado.getUsername() + " cerrada");
						break;
					case 8:
					    if (usuarioLogueado == null) {
					        System.out.println("Debes hacer login primero.");
					        break;
					    }

					    System.out.println("Personajes del usuario " + usuarioLogueado.getUsername() + ":");
					    System.out.println(personajeService.listarPorUsuario(usuarioLogueado.getId()));
					    break;
					case 9:
					    if (usuarioLogueado == null) {
					        System.out.println("Debes hacer login primero.");
					        break;
					    }
					    if (personajeCreado == null) {
					        System.out.println("Primero crea un personaje (opción 5).");
					        break;
					    }

					    // Creamos la cuerda
					    entities.equipo.objetos.Cuerda cuerda = new entities.equipo.objetos.Cuerda();
					    cuerda.setNombre("Cuerda"); // si tu constructor ya lo pone, esto no haría falta

					    // IMPORTANTE: enlazar ambos lados (FK)
					    cuerda.setPersonaje(personajeCreado);
					    personajeCreado.getEquipo().add(cuerda);

					    // Guardamos/actualizamos el personaje -> por cascade debe insertar EQUIPAMIENTO
					    personajeCreado = personajeService.actualizar(personajeCreado);

					    System.out.println("OK: añadida Cuerda y guardado personaje. Personaje = " + personajeCreado.getId());
					    break;
					case 10:
						if( usuarioLogueado == null) {
							System.out.println("Debes hacer login primero.");
							break;
						}
						
						if(personajeCreado == null) {
							System.out.println("Primero debes crea un personaje (opción 5).");
							break;
						}
						
						Conejo conejo1 = new Conejo();
						
						//añadir un Conejo al personajeCreado, setear personaje en la criatura, guardarlo, y luego recargar.
						
//						y siempre que añadas criatura:
//
//							criatura.setPersonaje(personaje);
//							personaje.getCriaturas().add(criatura);
						
						//ENLAZAR F.K. con personaje
						
						conejo1.setPersonaje(personajeCreado);
						personajeCreado.getCriaturas().add(conejo1);
						
						personajeCreado = personajeService.actualizar(personajeCreado);
						
						System.out.println("OK: criatura añadida y guardado. Criaturas actuales del personaje: " + personajeCreado.getCriaturas().size());
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
