package es.uva.inf.tds.p4;

/**
 * Clase que representa una estación en una línea de metro. 
 * Cada estación tiene asociada varias coordenadas GPS correspondientes 
 * a las entradas y salidas al exterior de la estación.
 * Tienen la particularidad de estar todas en un radio máximo de 300m. 
 */

public class Estacion {

	public Estacion(String nombre, CoordenadasGPS... coordenadasGPS) {
		//TODO. Auto-generated constructor stub
	}

	public void agregaCoordenadaGPS(CoordenadasGPS coordenada) {
		//TODO. Auto-generated method stub
	}

	public void eliminaCoordenadaGPS(CoordenadasGPS coordenada) {
		//TODO. Auto-generated method stub
	}

	/**
	 * @Requires({nombre!=null; nombre!=""})
	 * @param nombre
	 */
	public String getNombre() {
		//TODO. Auto-generated method stub
		return "";
	}

	public CoordenadasGPS[] getCoordenadasGPS() {
		//TODO. Auto-generated method stub
		return new CoordenadasGPS[0];
	}
}
