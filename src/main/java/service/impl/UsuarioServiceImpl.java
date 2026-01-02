package service.impl;

import java.util.List;

import dao.UsuarioDao;
import dao.impl.UsuarioDaoImpl;
import dto.UsuarioDto;
import service.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService {
	
	private final UsuarioDao usuarioDao = new UsuarioDaoImpl();

	@Override
	public UsuarioDto registrar(String username, String email, String password, String rol) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuarioDto login(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UsuarioDto> listar() {
		// TODO Auto-generated method stub
		return null;
	}

}
