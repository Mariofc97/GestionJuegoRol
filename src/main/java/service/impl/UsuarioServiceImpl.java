package service.impl;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import dao.UsuarioDao;
import dao.impl.UsuarioDaoImpl;
import dto.UsuarioDto;
import entities.Usuario;
import service.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService {
	
	private final UsuarioDao usuarioDao = new UsuarioDaoImpl();
	
	private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	
	private UsuarioDto mapToDto(Usuario u) {
		if (u == null) return null;
		String fecha = (u.getFechaAlta() == null) ? null : u.getFechaAlta().format(FMT);
		
		return new UsuarioDto(
				u.getId(),
				u.getUsername(),
				u.getEmail(),
				u.getRol(),
				fecha,
				u.getActivo()
				);
	}
	
	// En este caso seria para crear usuarios pasando todos los parametros
	private Usuario mapToEntity(String username, String email, String password, String rol) {
		return new Usuario(null,username, email, password, rol);
	}

	@Override
	public UsuarioDto registrar(String username, String email, String password, String rol) {
		// TODO Auto-generated method stub
		if(username == null || username.isBlank()) throw new RuntimeException("Username obligatorio");
		if(email == null || username.isBlank()) throw new RuntimeException("Email obligatorio");
		if(password == null || username.isBlank()) throw new RuntimeException("Password obligatorio");
		if(rol == null || username.isBlank()) throw new RuntimeException("Rol obligatorio");
		
		if(usuarioDao.existsByUsername(username)) {
			throw new RuntimeException("El username ya existe: " + username);
		}
		if(usuarioDao.existsByEmail(email)) {
			throw new RuntimeException("El email ya existe: " + email);
		}
		
		rol = rol.trim().toUpperCase();

		if (!rol.equals("JUGADOR") && !rol.equals("ADMINISTRADOR")) {
		    throw new RuntimeException("Rol inválido. Usa JUGADOR o ADMINISTRADOR");
		}
		
		Usuario u = mapToEntity(username, email, password, rol);
		usuarioDao.save(u);
		
		return mapToDto(u);
	}

	@Override
	public UsuarioDto login(String username, String password) {
		// TODO Auto-generated method stub
		if(username == null || username.isBlank() || password == null || password.isBlank()) {
			throw new RuntimeException("Username y password son obligatorios para logearse");
		}
		
		Usuario u = usuarioDao.findByUsername(username);
		if (u == null) {
			throw new RuntimeException("No existe el usuario " + username);
		}
		if (!u.getPassword().equals(password)) {
			throw new RuntimeException("Password incorrecta");
		}
		
		return mapToDto(u);
	}

	@Override
	public List<UsuarioDto> listar() {
		// TODO Auto-generated method stub
		List<Usuario> usuarios = usuarioDao.findAll();
		List<UsuarioDto> dtos = new ArrayList<>();
		
		for (Usuario u : usuarios) {
			dtos.add(mapToDto(u));
		}
		
		return dtos;
				
	}

}
