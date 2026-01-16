package service.impl;

import java.util.ArrayList;
import java.util.List;

import dao.CriaturaDao;
import dao.PersonajeDao;
import dao.impl.CriaturaDaoImpl;
import dao.impl.PersonajeDaoImpl;
import dto.CriaturaDto;
import entities.Personaje;
import entities.criatura.Conejo;
import entities.criatura.Criatura;
import entities.criatura.Gusano;
import entities.criatura.Jabali;
import entities.criatura.Lobo;
import entities.criatura.Mosquito;
import entities.criatura.PezPrehistoricoGigante;
import entities.criatura.Raton;
import entities.criatura.Siluro;
import exceptions.ReglaJuegoException;
import service.CriaturaService;

public class CriaturaServiceImpl implements CriaturaService {

    private final CriaturaDao criaturaDao = new CriaturaDaoImpl();
    private final PersonajeDao personajeDao = new PersonajeDaoImpl();

    private CriaturaDto mapToDto(Criatura c) {
        if (c == null) return null;

        String tipo = c.getClass().getSimpleName().toUpperCase();
        Long personajeId = (c.getPersonaje() != null) ? c.getPersonaje().getId() : null;

        return new CriaturaDto(
                c.getId(),
                tipo,
                c.getNombre(),
                c.getAlias(),
                c.getNivel(),
                c.getExperiencia(),
                c.getPuntosVida(),
                c.getPuntosAtaque(),
                c.getTipoAtaque(),
                personajeId
        );
    }

    private Criatura construirCriatura(String tipoCriatura) throws ReglaJuegoException {
        if (tipoCriatura == null || tipoCriatura.isBlank()) {
            throw new ReglaJuegoException("Tipo de criatura obligatorio.");
        }

        String t = tipoCriatura.trim().toUpperCase();

        if ("GUSANO".equals(t)) return new Gusano();
        if ("CONEJO".equals(t)) return new Conejo();
        if ("MOSQUITO".equals(t)) return new Mosquito();
        if ("RATON".equals(t)) return new Raton();
        if ("JABALI".equals(t)) return new Jabali();
        if ("LOBO".equals(t)) return new Lobo();
        if ("PEZ PREHISTORICO GIGANTE".equals(t)) return new PezPrehistoricoGigante();
        if ("SILURO".equals(t)) return new Siluro();

        throw new ReglaJuegoException("Tipo inválido: " + tipoCriatura + " (usa GUSANO / CONEJO / MOSQUITO / RATON / LOBO / PEZ PREHISTORICO GIGANTE/ SILURO)");
    }

    @Override
    public CriaturaDto crearYAsignar(Long personajeId, String tipoCriatura, String alias) throws ReglaJuegoException {

        if (personajeId == null) throw new ReglaJuegoException("personajeId obligatorio.");

        // 1) Validar que existe el personaje (regla de juego)
        Personaje p = personajeDao.findById(personajeId);
        if (p == null) throw new ReglaJuegoException("No existe el personaje con id " + personajeId);

        // 2) Validar máximo criaturas
        long total = criaturaDao.countByPersonajeId(personajeId);
        if (total >= 5) throw new ReglaJuegoException("No puedes tener más de 5 criaturas aliadas.");

        // 3) Construir criatura concreta
        Criatura c = construirCriatura(tipoCriatura);

        // 4) Alias (si no ponen, le ponemos el nombre por defecto)
        if (alias == null || alias.isBlank()) {
            c.setAlias(c.getNombre());
        } else {
            c.setAlias(alias.trim());
        }

        // 5) Persistir y asociar en BD (crea fila en TB_CRIATURA con personaje_id)
        Criatura guardada = criaturaDao.saveToPersonaje(personajeId, c);

        return mapToDto(guardada);
    }

    @Override
    public List<CriaturaDto> listarPorPersonaje(Long personajeId) throws ReglaJuegoException {
        if (personajeId == null) throw new ReglaJuegoException("personajeId obligatorio.");

        Personaje p = personajeDao.findById(personajeId);
        if (p == null) throw new ReglaJuegoException("No existe el personaje con id " + personajeId);

        List<Criatura> lista = criaturaDao.findByPersonajeId(personajeId);
        List<CriaturaDto> dto = new ArrayList<>();
        for (Criatura c : lista) {
            dto.add(mapToDto(c));
        }
        return dto;
    }
}

