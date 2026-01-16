package service;

import java.util.List;

import dto.CriaturaDto;
import exceptions.ReglaJuegoException;

public interface CriaturaService {

    CriaturaDto crearYAsignar(Long personajeId, String tipoCriatura, String alias) throws ReglaJuegoException;

    List<CriaturaDto> listarPorPersonaje(Long personajeId) throws ReglaJuegoException;
}

