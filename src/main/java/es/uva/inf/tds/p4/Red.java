package es.uva.inf.tds.p4;

import java.util.ArrayList;
import java.util.List;

public class Red {

	private ArrayList<Linea> lineas;

	/**
	 * Crea una red de líneas de metro a través de un conjunto inicial de líneas
	 * cuyo número mínimo debe ser dos.
	 * 
	 * @param al {@code ArrayList} de {@code Linea} que representan las distintas
	 *           lineas de metro.
	 * @throws {@code IllegalArgumentException} si el parámetro introducido es nulo.
	 * @throws {@code IllegalArgumentException} si el número de líneas iniciales es
	 *                menor que dos.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Red(List<Linea> al) {
		if (al == null) {
			throw new IllegalArgumentException();
		}
		if (al.size() < 2) {
			throw new IllegalArgumentException();
		}
		lineas = (ArrayList) al;
	}

	/**
	 * Devuelve una línea de metro a partir de su número.
	 * 
	 * @param i Número entero que representa el número de una línea.
	 * @return {@code Linea} correspondiente al número introducido. De no existir
	 *         una línea con ese número, devuelve nulo.
	 * @throws {@code IllegalArgumentException} en caso de introducir un número
	 *                nulo.
	 */
	public Linea getLinea(int i) {
		for (int j = 0; j < lineas.size(); j++) {
			if (lineas.get(j).getNumero() == i)
				return lineas.get(j);
		}
		return null;
	}

	/**
	 * Devuelve una línea de metro a partir de su color.
	 * 
	 * @param color Cadena de caracteres que representa el color de la línea de
	 *              metro. De no existir una línea con ese color, devuelve nulo.
	 * @return {@code Linea} correspondiente al color introducido.
	 * @throws {@code IllegalArgumentException} en caso de introducir un color nulo.
	 */
	public Linea getLinea(String color) {
		for (int i = 0; i < lineas.size(); i++) {
			if (lineas.get(i).getColor().equals(color))
				return lineas.get(i);
		}
		return null;
	}

	/**
	 * Añade una nueva línea de metro a la red. Dicha línea debe tener un nombre y
	 * color únicos en la red.
	 * 
	 * @param l {@code Linea} a añadir a la red.
	 * @throws {@code IllegalArgumentException} en el caso de introducir una línea
	 *                nula.
	 * @throws {@code IllegalArgumentException} en el caso de introducir una linea
	 *                con número o color ya existentes en la red.
	 */
	public void addLinea(Linea l) {
		if (l == null) {
			throw new IllegalArgumentException();
		}
		if (isRepetida(l))
			throw new IllegalArgumentException();
		lineas.add(l);
	}

	private boolean isRepetida(Linea l) {
		for (int i = 0; i < lineas.size(); i++) {
			if (l.getNumero() == lineas.get(i).getNumero() || l.getColor().equals(lineas.get(i).getColor()))
				return true;
		}
		return false;
	}

	/**
	 * Obtiene el conjunto de líneas de metro que conforman el array.
	 * 
	 * @return {@code Array} de {@code Linea} que representa el conjunto de líneas
	 *         de la red.
	 */
	public Linea[] getArrayLineas() {
		return lineas.toArray(new Linea[0]);
	}

	/**
	 * Elimina una línea específica de la red.
	 * 
	 * @param l {@code Linea} concreta a eliminar de la red.
	 * @throws {@code IllegalStateException} en el caso de que al eliminar una
	 *                línea, el total de líneas de la red sea menor que cero.
	 * @throws {@code IllegalArgumentException} en el caso de que la línea a borrar
	 *                no exista en la red o la línea a borrar sea nula.
	 */
	public void removeLinea(Linea l) {
		if (l == null)
			throw new IllegalArgumentException();
		if (!lineas.contains(l))
			throw new IllegalArgumentException();
		if (lineas.size() == 2)
			throw new IllegalStateException();
		lineas.remove(l);
	}

	/**
	 * Devuelve el total de líneas que pasan por una estación concreta.
	 * 
	 * @param e {@code Estacion} de la que se quiere saber las líneas que pasan por
	 *          ella.
	 * @return Lista de lineas que pasan por la estación.
	 * @throws {@code IllegalArgumentException} si la estación introducida es nula.
	 */
	public List<Linea> getLineasPorEstacion(Estacion e) {
		if (e == null)
			throw new IllegalArgumentException();
		ArrayList<Linea> resultado = new ArrayList<>();
		for (int i = 0; i < lineas.size(); i++) {
			if (lineas.get(i).contieneEstacion(e))
				resultado.add(lineas.get(i));
		}
		return resultado;
	}

	/**
	 * Devuelve información sobre el total de líneas que pasan por una estación
	 * concreta.
	 * 
	 * @param e {@code Estacion} de la que se quiere saber las líneas que pasan por
	 *          ella.
	 * @return Lista de información asociada a las líneas que pasan por la estación.
	 * @throws {@code IllegalArgumentException} si la estación introducida es nula.
	 */
	public List<String> getInfoLineasPorEstacion(Estacion e) {
		if (e == null)
			throw new IllegalArgumentException();
		String data;
		List<Linea> lin = getLineasPorEstacion(e);
		ArrayList<String> resultado = new ArrayList<>();
		for (int i = 0; i < lin.size(); i++) {
			data = "Linea " + lin.get(i).getNumero() + ", " + lin.get(i).getColor();
			resultado.add(data);
		}
		return resultado;
	}

	/**
	 * Devuelve las estaciones donde existe correspondencia para las líneas
	 * introducidas por parámetro.
	 * 
	 * @param l1 {@code Linea} a comprobar si existe correspondencia.
	 * @param l2 {@code Linea} a comprobar si existe correspondencia.
	 * @return Lista de estaciones donde existe correspondecia entre las líneas. De
	 *         no existir correspondencia en ningín punto, se devolverá un array
	 *         vacío.
	 * @throws {@code IllegalArgumentException} si una o ambas líneas introducidas
	 *                es nula.
	 */
	public Estacion[] correspondenciaLineas(Linea l1, Linea l2) {
		if (l1 == null || l2 == null)
			throw new IllegalArgumentException();
		return l1.getCorrespondencias(l2);
	}

	/**
	 * Determina si existe una línea directa entre las dos estaciones introducidas
	 * por parámetro.
	 * 
	 * @param e  {@code Estacion} a comprobar si existe línea directa.
	 * @param e2 {@code Estacion} a comprobar si existe línea directa.
	 * @return {@code Linea} que une las dos estaciones en caso de haber conexión
	 *         directa. {@code Null} en caso contrario.
	 * @throws {@code IllegalArgumentException} si una o ambas estaciones
	 *                introducidas es nula.
	 */
	public Linea conexionSinTransbordo(Estacion e, Estacion e2) {
		if (e == null || e2 == null)
			throw new IllegalArgumentException();
		for (int i = 0; i < lineas.size(); i++) {
			if (lineas.get(i).contieneEstacion(e) && lineas.get(i).contieneEstacion(e2))
				return lineas.get(i);
		}
		return null;
	}

	/**
	 * Determina si existe una conexión indirecta entre las dos estaciones
	 * introducidas por parámetro.
	 * 
	 * @param e  {@code Estacion} a comprobar si existe conexión indirecta.
	 * @param e2 {@code Estacion} a comprobar si existe conexión indirecta.
	 * @return Lista de estaciones, incluidas inicial y final, que conectan las dos
	 *         estaciones introducidas por parámetro. Devolverá array vacío en caso
	 *         de no existir conexión indirecta.
	 * @throws {@code IllegalArgumentException} si una o ambas estaciones
	 *                introducidas son nulas.
	 */
	public Estacion[] conexionConTransbordo(Estacion e, Estacion e2) {
		if (e == null || e2 == null)
			throw new IllegalArgumentException();
		Estacion[] resultado = new Estacion[3];
		ArrayList<Linea> l1 = new ArrayList<>();
		ArrayList<Linea> l2 = new ArrayList<>();
		for (int i = 0; i < lineas.size(); i++) {
			if (lineas.get(i).contieneEstacion(e)) {
				l1.add(lineas.get(i));
			}
		}
		for (int j = 0; j < lineas.size(); j++) {
			if (lineas.get(j).contieneEstacion(e2)) {
				l2.add(lineas.get(j));
			}
		}
		for (int i = 0; i < l1.size(); i++) {
			for (int j = 0; j < l2.size(); j++) {
				if (l1.get(i).hayCorrespondencia(l2.get(j))) {
					resultado[0] = e;
					resultado[1] = l1.get(i).getCorrespondencias(l2.get(j))[0];
					resultado[2] = e2;
					return resultado;
				}

			}
		}
		return resultado;
	}

	/**
	 * Determina la estación más cercana en base a las coordenadas introducidas por
	 * parámetros.
	 * 
	 * @param gps Coordenadas que representan la posición actual desde donde se
	 *            realiza la llamada.
	 * @return {@code Estacion} más próxima a las coordenadas introducidas.
	 */
	public Estacion getEstacionMasCercana(CoordenadasGPS gps) {
		Estacion[] res = lineas.get(0).getEstaciones(true);
		Estacion resultado = res[0];
		for (int i = 0; i < lineas.size(); i++) {
			Estacion[] estacionesE = lineas.get(i).getEstaciones(true);
			Estacion[] estacionesS = lineas.get(i).getEstaciones(false);
			for (int j = 0; j < estacionesE.length; j++) {
				if (gps.getDistanciaA(estacionesE[j].getCoordenadasGPS()[0]) < gps
						.getDistanciaA(resultado.getCoordenadasGPS()[0])) {
					resultado = estacionesE[j];
				}
				if (gps.getDistanciaA(estacionesS[j].getCoordenadasGPS()[0]) < gps
						.getDistanciaA(resultado.getCoordenadasGPS()[0])) {
					resultado = estacionesS[j];
				}
			}
		}
		return resultado;
	}

}
