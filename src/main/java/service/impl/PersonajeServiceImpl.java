package service.impl;

import java.util.List;

import dao.PersonajeDao;
import dao.UsuarioDao;
import dao.impl.PersonajeDaoImpl;
import dao.impl.UsuarioDaoImpl;
import entities.Personaje;
import entities.Usuario;
import entities.raza.Mongol;
import entities.raza.RapaNui;
import entities.raza.Raza;
import entities.raza.Troglodita;
import exceptions.ReglaJuegoException;
import service.PersonajeService;

public class PersonajeServiceImpl implements PersonajeService{

	private final PersonajeDao personajeDao = new PersonajeDaoImpl();
	private final UsuarioDao usuarioDao = new UsuarioDaoImpl();
	
	
	@Override
	public Personaje crearYGuardar(Long usuarioId, String nombre, String razaTipo) {
		// TODO Auto-generated method stub
		if(usuarioId == null) {
			throw new RuntimeException("El id del usuario es obligatorio");
		}
		if(nombre == null || nombre.isBlank()) {
			throw new RuntimeException("El nombre es obligatorio");
		}
		if(razaTipo == null || razaTipo.isBlank()) {
			throw new RuntimeException("La raza es obligatoria");
		}
		
		Usuario u = usuarioDao.findById(usuarioId);
		
		if (u == null) throw new RuntimeException("No existe usuario con id= " + usuarioId);
		try {
			
			Personaje p = new Personaje(nombre.trim(), razaTipo.trim());
			
			p.setUsuario(u);
			
			// Construimos la raza a partir del texto (LA NO PERSISTENTE)
			
			Raza raza = construirRaza(razaTipo);
			
			// creamos personaje con stats predefinidos en este service y con los atributos base de la raza (fuerza/inteligencia/suerte)
			
			inicializarStatsBase(p);			
			aplicarStatsBaseDeRaza(p, raza);
			
			personajeDao.save(p);
			
			return p;
		} catch (ReglaJuegoException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public Personaje buscarPorId(Long id) throws ReglaJuegoException {
		if(id == null) throw new ReglaJuegoException("El ID es obligatorio");
		
		Personaje p = personajeDao.findById(id);
		
		if(p == null) throw new ReglaJuegoException("No existe personaje con ID= " + id);
		
		return p;
	}
	
	@Override
	public List<Personaje> listarPorUsuario(Long usuarioId) {
	    return personajeDao.findByUsuarioId(usuarioId);
	}
	
	private Raza construirRaza(String razaTipo) throws ReglaJuegoException {
		String rt = razaTipo.trim().toUpperCase();
		
		switch (rt) {
		case "MONGOL":
			return new Mongol();

		case "RAPA NUI":
			return new RapaNui();

		case "TROGLODITA":
			return new Troglodita();

		default:
				throw new ReglaJuegoException("Raza invalida: " + razaTipo + " (usa MONGOL / RAPA NUI / TROGLODITA");
		}
	}
	
	private void inicializarStatsBase(Personaje p) {
		p.setNivel(1);
		p.setExperiencia(0);
		p.setPuntosVidaMax(100);
		p.setPuntosVida(100);
		p.setPuntosAtaque(5);
		p.setInteligencia(2);
		p.setSuerte(2);
		
	}
	
	private void aplicarStatsBaseDeRaza(Personaje p, Raza raza) {
		p.setPuntosAtaque(p.getPuntosAtaque() + raza.getFuerza());
		p.setInteligencia(p.getInteligencia() + raza.getInteligencia());
		p.setSuerte(p.getSuerte() + raza.getSuerte());
		
		//No llamo a raza.aplicarBonos(p) porque eso son pasivas situacionales en funcion de la historia
	}

	@Override
	public Personaje actualizar(Personaje p) {
		// TODO Auto-generated method stub
		return personajeDao.update(p);
	}

	@Override
	public Personaje cargarParaJuego(Long personajeId) throws ReglaJuegoException {
		// TODO Auto-generated method stub
		if(personajeId == null) throw new ReglaJuegoException("El ID del personaje es obligatorio");
		
		//Cargamaos el personaje 
		Personaje p = personajeDao.findByIdFetchAll(personajeId);
		if(p == null) throw new ReglaJuegoException("No existe personaje con ID: " + personajeId);
		
		// Nos aseguramos de que el objeto que devolvemos tenga la lista de criaturas, no tocamos nada si p2 viene null
		if(p !=null) p.setCriaturas(p.getCriaturas());
		
	    // Si estás usando raza transient, aquí es buen sitio para reconstruirla si la necesitas en juego
	    // p.setRaza(construirRaza(p.getRazaTipo()));  // opcional
		
		return p;
	}
	
	

	
	

}
