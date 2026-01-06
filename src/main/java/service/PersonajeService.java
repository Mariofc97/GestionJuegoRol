package service;

import java.util.List;

import entities.Personaje;

public interface PersonajeService {

	Personaje crearYGuardar(Long usuarioId, String nombre, String razaTipo);
	Personaje buscarPorId(Long id);
	List<Personaje> listarPorUsuario(Long usuarioId);
	public Personaje actualizar(Personaje p);
}
