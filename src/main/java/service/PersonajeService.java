package service;

import entities.Personaje;

public interface PersonajeService {

	Personaje crearYGuardar(String nombre, String razaTipo);
	Personaje buscarPorId(Long id);
}
