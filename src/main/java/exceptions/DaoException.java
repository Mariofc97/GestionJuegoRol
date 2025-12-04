package exceptions;

public class DaoException extends Exception{

	public DaoException(String mensaje) {
		super(mensaje);
	}

//	Para  errores de acceso a datos / Hibernate
//	Posible excepcion mas:
//		ServiceException.java (C)     # errores técnicos en capa servicio (opcional)
	
}
