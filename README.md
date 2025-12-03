# Proyecto Juego de Rol (Java + Hibernate)

Proyecto de juego de rol por consola desarrollado en Java, orientado a practicar:

- Programación Orientada a Objetos (POO)
- Diseño por capas (presentación, servicio, acceso a datos)
- Uso de Hibernate como ORM
- Operaciones con base de datos relacional (CRUD, consultas, etc.)

El juego permite crear personajes, gestionar inventario y equipo, combatir criaturas, tomar decisiones en episodios y persistir el estado de la partida en base de datos.

---

## Tecnologías

- Java (versión a concretar, por ejemplo 17)
- Hibernate (ORM)
- Base de datos relacional (por ejemplo, MySQL / MariaDB / H2)
- Maven o Gradle como gestor de dependencias (según configuración del proyecto)

---

## Arquitectura por capas

El proyecto sigue una arquitectura en capas:

- **Capa de presentación**: interacción con el usuario (consola).
- **Capa de servicios (service)**: lógica de juego / reglas del dominio.
- **Capa de acceso a datos (dao)**: comunicación con la base de datos mediante Hibernate.
- **Capa de entidades (entities)**: clases de dominio mapeadas a tablas de BD.
- **Capa de utilidades y configuración**: clases auxiliares y configuración técnica.

---

## Estructura de paquetes

> Nota: `(I)` = interfaz, `(C)` = clase

```text
src/main/java
└─ es.cursojava.juegorol
   ├─ app                           # Capa de presentación
   │   └─ AppJuegoRol.java (C)      # main: arranca el juego y menú principal
   │
   ├─ entities                      # Entidades mapeadas con Hibernate (@Entity)
   │   ├─ Personaje.java (C)
   │   ├─ raza
   │   │   ├─ Raza.java (C)         # clase base de raza
   │   │   ├─ Mongol.java (C)
   │   │   ├─ RapaNui.java (C)
   │   │   └─ Troglodita.java (C)
   │   ├─ criatura
   │   │   ├─ Criatura.java (C)
   │   │   ├─ Gusano.java (C)
   │   │   ├─ Conejo.java (C)
   │   │   ├─ Mosquito.java (C)
   │   │   └─ Raton.java (C)
   │   ├─ equipo
   │   │   ├─ Equipamiento.java (C)
   │   │   ├─ Armas.java (C)
   │   │   ├─ Escudos.java (C)
   │   │   ├─ Pocion.java (C)
   │   │   └─ HojaParaLimpiar.java (C)
   │   ├─ episodio
   │   │   ├─ Episodio.java (I/C)   # interfaz o clase abstracta común
   │   │   ├─ Episodio1.java (C)
   │   │   └─ Episodio2.java (C)
   │   └─ inventario
   │       └─ Inventario.java (C)   # entidad para inventario (si se persiste)
   │
   ├─ core                          # Lógica de dominio no mapeada
   │   ├─ Atacable.java (I)
   │   ├─ Defendible.java (I)
   │   └─ RasgoRacial.java (I)
   │
   ├─ dao                           # Capa DAO: interfaces
   │   ├─ GenericDao.java (I)       # CRUD genérico
   │   ├─ PersonajeDao.java (I)
   │   ├─ CriaturaDao.java (I)
   │   ├─ EquipamientoDao.java (I)
   │   └─ InventarioDao.java (I)
   │
   ├─ dao.impl                      # Implementaciones DAO (Hibernate)
   │   ├─ GenericDaoHibernate.java (C)
   │   ├─ PersonajeDaoImpl.java (C)
   │   ├─ CriaturaDaoImpl.java (C)
   │   ├─ EquipamientoDaoImpl.java (C)
   │   └─ InventarioDaoImpl.java (C)
   │
   ├─ dto                           # Data Transfer Objects (comunicación entre capas)
   │   ├─ PersonajeDto.java (C)
   │   ├─ CriaturaDto.java (C)
   │   ├─ EquipamientoDto.java (C)
   │   └─ InventarioDto.java (C)
   │
   ├─ service                       # Capa de servicios: interfaces
   │   ├─ PersonajeService.java (I)
   │   ├─ EpisodioService.java (I)
   │   ├─ CombateService.java (I)
   │   └─ InventarioService.java (I)
   │
   ├─ service.impl                  # Implementaciones de servicios
   │   ├─ PersonajeServiceImpl.java (C)
   │   ├─ EpisodioServiceImpl.java (C)
   │   ├─ CombateServiceImpl.java (C)
   │   └─ InventarioServiceImpl.java (C)
   │
   ├─ exceptions                    # Excepciones propias del proyecto
   │   ├─ ReglaJuegoException.java (C)  # violación de reglas del juego (nivel, peso, etc.)
   │   ├─ DaoException.java (C)         # errores de acceso a datos / Hibernate
   │   └─ ServiceException.java (C)     # errores técnicos en capa servicio (opcional)
   │
   ├─ util                          # Utilidades generales
   │   ├─ Utils.java (C)            # dados, combate, menús, inventario...
   │   └─ HibernateUtil.java (C)    # gestión de SessionFactory / EntityManager
   │
   └─ config                        # Configuración (opcional)
       └─ JuegoConfig.java (C)      # constantes de XP, daño, etc.
       
   Ejemplo de excepciones con ReglaJuegoException.java:
  	-Intentar equipar un arma que requiere nivel 10 y el personaje es nivel 3.

	-Intentar coger un objeto que haría que supere el peso máximo permitido.
	
	-Intentar usar una poción cuando ya tienes la vida al máximo.
	
	-Intentar entrar a un episodio que requiere haber completado otro antes.
	
	-Intentar invocar una criatura cuando ya tienes el máximo de criaturas aliadas.
	
	Uso de Generic Dao:
	Para no tener que reecribir el CRUD en 10 clases, tener una estructura mas limpia y reutilizada
	
	public interface GenericDao<T> {

	    void save(T entity);
	
	    void update(T entity);
	
	    void delete(T entity);
	
	    T findById(Long id);
	
	    List<T> findAll();
	}
	Esto sirve para cualquier entidad: Personaje, Criatura, Episorio, Inventario...
   
