package es.uva.inf.tds.p4;

import java.util.ArrayList;

public class Red {

	/**
	 * Crea una red de líneas de metro a través de un conjunto inicial de líneas cuyo número mínimo debe ser dos.
	 * @param al {@code ArrayList} de {@code Linea} que representan las distintas lineas de metro.
	 * @throws {@code IllegalArgumentException} si el parámetro introducido es nulo.
	 * @throws {@code IllegalArgumentException} si el número de líneas iniciales es menor que dos.
	 */
	public Red(ArrayList<Linea> al) {
		// Constructor a través de un arraylist
	}

	/**
	 * Devuelve una línea de metro a partir de su número.
	 * @param i Número entero que representa el número de una línea.
	 * @return {@code Linea} correspondiente al número introducido. De no existir una línea con ese número, devuelve nulo.
	 * @throws {@code IllegalArgumentException} en caso de introducir un número nulo.
	 */
	public Linea getLinea(int i) {
		// Recorrer arraylist buscando numero
		return null;
	}

	/**
	 * Devuelve una línea de metro a partir de su color.
	 * @param color Cadena de caracteres que representa el color de la línea de metro. De no existir una línea con ese color, devuelve nulo.
	 * @return {@code Linea} correspondiente al color introducido.
	 * @throws {@code IllegalArgumentException} en caso de introducir un color nulo.
	 */
	public Linea getLinea(String color) {
		// Recorrer arraylist buscando color
		return null;
	}

	/**
	 * Añade una nueva línea de metro a la red. Dicha línea debe tener un nombre y color únicos en la red.
	 * @param l {@code Linea} a añadir a la red.
	 * @throws {@code IllegalArgumentException} en el caso de introducir una línea nula.
	 * @throws {@code IllegalArgumentException} en el caso de introducir una linea con número o color ya existentes en la red.
	 */
	public void addLinea(Linea l) {
		// añadir con add
		
	}

	/**
	 * Obtiene el conjunto de líneas de metro que conforman el array.
	 * @return {@code Array} de {@code Linea} que representa el conjunto de líneas de la red.
	 */
	public Linea[] getArrayLineas() {
		// devolver toArray
		return null;
	}

	/**
	 * Elimina una línea específica de la red.
	 * @param l {@code Linea} concreta a eliminar de la red.
	 * @throws {@code IllegalStateException} en el caso de que al eliminar una línea, el total de líneas de la red sea menor que cero.
	 * @throws {@code IllegalArgumentException} en el caso de que la línea a borrar no exista en la red.
	 */
	public void removeLinea(Linea l) {
		// eliminar con remove
		
	}

	/**
	 * Devuelve el total de líneas que pasan por una estación concreta.
	 * @param e {@code Estacion} de la que se quiere saber las líneas que pasan por ella.
	 * @return Lista de lineas que pasan por la estación.
	 * @throws {@code IllegalArgumentException} si la estación introducida es nula.
	 */
	public ArrayList<Linea> getLineasPorEstacion(Estacion e) {
		// Recorrer lineas y añadir al resultado las que tengan a la estacion
		return null;
	}

	/**
	 * Devuelve información sobre el total de líneas que pasan por una estación concreta.
	 * @param e {@code Estacion} de la que se quiere saber las líneas que pasan por ella.
	 * @return Lista de información asociada a las líneas que pasan por la estación.
	 * @throws {@code IllegalArgumentException} si la estación introducida es nula.
	 */
	public ArrayList<String> getInfoLineasPorEstacion(Estacion e) {
		// TODO Recorrer lineas y añadir al resultado un string compuesto por nombre y color por cada linea que tenga a la estacion
		// Utilizar primero metodo anterior
		return null;
	}

	/**
	 * Devuelve las estaciones donde existe correspondencia para las líneas introducidas por parámetro.
	 * @param l1 {@code Linea} a comprobar si existe correspondencia.
	 * @param l2 {@code Linea} a comprobar si existe correspondencia.
	 * @return Lista de estaciones donde existe correspondecia entre las líneas. De no existir correspondencia en ningín punto, se devolverá una lista vacía.
	 * @throws {@code IllegalArgumentException} si una o ambas líneas introducidas es nula.
	 */
	public ArrayList<Estacion> correspondenciaLineas(Linea l1, Linea l2) {
		// TODO Comprobar con dos bucles for
		return null;
	}

	/**
	 * Determina si existe una línea directa entre las dos estaciones introducidas por parámetro.
	 * @param e {@code Estacion} a comprobar si existe línea directa.
	 * @param e2 {@code Estacion} a comprobar si existe línea directa.
	 * @return {@code Linea} que une las dos estaciones en caso de haber conexión directa. {@code Null} en caso contrario.
	 * @throws {@code IllegalArgumentException} si una o ambas estaciones introducidas es nula.
	 */
	public Linea conexionSinTransbordo(Estacion e, Estacion e2) {
		// TODO Comprobar con un bucle for sobre las lineas si existe una linea con las dos estaciones.
		return null;
	}

	/**
	 * Determina si existe una conexión indirecta entre las dos estaciones introducidas por parámetro.
	 * @param e {@code Estacion} a comprobar si existe conexión indirecta.
	 * @param e2 {@code Estacion} a comprobar si existe conexión indirecta.
	 * @return Lista de estaciones, incluidas inicial y final, que conectan las dos estaciones introducidas por parámetro. Devolverá array nulo en caso de no existir conexión indirecta.
	 * @throws {@code IllegalArgumentException} si una o ambas estaciones introducidas son nulas.
	 */
	public Estacion[] conexionConTransbordo(Estacion e, Estacion e2) {
		// TODO Comprueba que no hay conexion directa. Buscar lineas con estacion e y otras con estacion e2.
		// Utilizar funcion recursiva aparte para obtener el array.
		return null;
	}

	/**
	 * Determina la estación más cercana en base a las coordenadas introducidas por parámetros.
	 * @param gps Coordenadas que representan la posición actual desde donde se realiza la llamada.
	 * @return {@code Estacion} más próxima a las coordenadas introducidas.
	 */
	public Estacion getEstacionMasCercana(CoordenadasGPS gps) {
		// Por cada linea obtener la más cercana. De esas elegir la más cercana. Utilizar funcion extra para producto cartesiano.
		return null;
	}

}
