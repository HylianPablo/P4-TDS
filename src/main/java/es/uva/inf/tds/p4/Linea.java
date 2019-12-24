package es.uva.inf.tds.p4;

/**
 * Clase que representa los servicios de una línea en una red de metro.
 * En una línea no hay estaciones repetidas. Se cuenta con al menos 2 estaciones, inicial y final,
 * el resto de estaciones se consideran estaciones intermedias. 
 * @Invariant {@code getNumero()>0 && getColor() != null && getCantidadEstaciones() >= 2}
 */

public class Linea{
	
	public Linea(int numero, String color, Estacion... estaciones) {
		//TODO. Auto-generated constructor stub
	}

	public String getColor() {
		//TODO. Auto-generated method stub
		return "";
	}

	public int getNumero() {
		//TODO. Auto-generated method stub
		return 0;
	}

	public int getCantidadEstaciones() {
		//TODO. Auto-generated method stub
		return 0;
	}

	public boolean contieneEstacion(Estacion estacion) {
		//TODO. Auto-generated method stub
		return false;
	}

	/**
	 * @Requires({ estacion no está añadida ya; estacion.getNombre() no es igual
	 *             al de otra estacion })
	 * @param estacion
	 */
	public void añadeEstacion(Estacion estacion) {
		//TODO. Auto-generated method stub
	}
	
	/**
	 * Elimina una estación de la línea
	 * @Requires ({getCantidadDeEstaciones>2}, estacion )
	 * @Ensure ({getCantidadEstaciones().size>=2})
	 * @param estacion
	 */
	public void eliminarEstacion(Estacion estacion) {
		//TODO. Auto-generated method stub
	}

	/**
	 * Devuelve la lista de estaciones por las que pasa esta línea en el sentido
	 * de ida o de vuelta (sentido inverso)
	 * @return el array de estaciones siempre es distinto de null y contiene al menos 
	 * dos estaciones, la inicial y la final
	 */
	public Estacion[] getEstaciones(boolean enSentidoDirecto) {
		//TODO. Auto-generated method stub
		return new Estacion[0];
	}

	/**
	 * Comprueba si esta linea pasa por las dos estaciones dadas
	 * @param estacion1
	 * @param estacion2
	 * @return true si la línea conecta ambas estaciones, false en otro caso
	 */
	public boolean estaConectada(Estacion estacion1, Estacion estacion2) {
		//TODO. Auto-generated method stub
		return false;
	}

	/**
	 * Comprueba si hay alguna estacion de esta linea por la que pase la otra
	 * linea. Busca la correspondencia por el nombre de estaciones. Es posible
	 * que entre una línea y otra haya más de una estación en la que coincidan
	 * @param otraLinea
	 * @return
	 */
	public boolean hayCorrespondencia(Linea otraLinea) {
		//TODO. Auto-generated method stub
		return false;
	}

	/**
	 * Indica las estaciones en las que hay correspondencia de esta linea con la otra
	 * linea que se recibe como parámetro. Busca la correspondencia por el nombre de estaciones. Es posible
	 * que entre una línea y otra haya más de una estación en la que coincidan 
	 * o ninguna si no hay correspondencia. 
	 * @param otraLinea
	 * @return En ese caso se devuelve un array vacío.
	 */
	public Estacion[] getCorrespondencias(Linea otraLinea) {
		//TODO. Auto-generated method stub
		return new Estacion[0];
	}

	/**
	 * Calcula el numero de estaciones que separan a las dos estaciones que
	 * recibe
	 * @param estacion1
	 * @param estacion2
	 * @return
	 */
	public int getNumeroParadas(Estacion estacion1, Estacion estacion2) {
		//TODO. Auto-generated method stub
		return 0;
	}

}
