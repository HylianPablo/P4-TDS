package es.uva.inf.tds.p4;

/**
 * Implementación de un tipo de dato que representa coordenadas GPS.
 * Las coordenadas GPS localizan una posición de un 
 * objeto en toda la Tierra. La localización se realiza caracterizando 
 * la latitud y la longitud de la posición.
 *
 */
public class CoordenadasGPS {
	
	/**
	 * Inicialización de las coordenadas GPS indicando la longitud y la latitud en GMS. 
	 * @param latitudGMS latitud en Grados, Minutos y Segundos hexagesimales con indicación de hemisferio Norte o Sur 
	 * @param longitudGMS longitud en Grados, Minutos y Segundos hexagesimales con indicación de Oeste o Este
	 * @throws IllegalArgumentException cuando latitudeGMS es null, o es una cadena vacía
	 * o es una cadena cuyo contenido no coincide con el formato indicado en el método {@link #getLatitudGMS()}  
	 * @throws IllegalArgumentException cuando longitudeGMS es null, o es una cadena vacía
	 * o es una cadena cuyo contenido no coincide con el formato indicado en el método {@link #getLongitudGMS()}
	 * @see #getLatitudGMS()
	 * @see #getLongitudGMS()
	 */
	public CoordenadasGPS(String latitudGMS, String longitudGMS) {
		//TODO. Auto-generated constructor stub
	}

	/**
	 * Inicialización de las coordenadas GPS indicando la longitud y la latitud en GD.
	 * @param latitudGD  longitud en Grados Decimales
	 * @param longitudGD latitud en Grados Decimales
	 * @throws IllegalArgumentException cuando {@code longitudGD>-180 && longitudGD<180}
	 * @throws IllegalArgumentException cuando {@code latitudGD>-90 && latitudGD<90}
	 * @see #getLatitudGD()
	 * @see #getLongitudGD()
	 */
	public CoordenadasGPS(double latitudGD, double longitudGD) {
		//TODO. Auto-generated constructor stub
	}
	
	/**
	 * Consulta la latitud en GMS.
	 * La latitud es la distancia angular que hay desde un punto de la superficie de la
	 * Tierra hasta el paralelo del Ecuador (paralelo 0º); se  mide  en  
	 * grados,  minutos  y segundos  sexagesimales  (GMS)  sobre  los meridianos.
	 * Hay que especificar si se encuentra en latitud Norte o en latitud Sur.
	 * @return Cadena que representa la latitud en GMS con el siguiente formato: <br>
	 * GGG°MM'SS"H <br>
	 * La cadena tiene exactamente 11 caracteres. <br>
	 * El cuarto caracter es º, el séptimo carácter es ' y el décimo es ". <br>
	 * GGG son tres dígitos que representan los grados sexagesimales, <br>
	 * MM son dos dígitos que representan los minutos sexagesimales, <br>
	 * SS son dos dígitos que representan los segundos sexagesimales. <br>
	 * H puede ser o bien el carácter 'N' o el carácter 'S' según sea <br>
	 * una latitud al Norte o al Sur o al sur del Ecuador. <br>
	 * Ejemplos: 041°38'06"N o 056°45'43"S o 007°28'35"N o 000º00'00"N
	 */
	public String getLatitudGMS() {
		//TODO. Auto-generated method stub
		return "";
	}

	/**
	 * Consulta la longitud en GMS. 
	 * La longitud es la distancia angular entre un punto dado de la superficie de la 
	 * Tierra y el meridiano de Greenwich que se toma como meridiano 0º; se mide en 
	 * grados, minutos y segundos sexagesimales (GMS). 
	 * Hay que especificar si se encuentra en longitud Este o longitud Oeste.
	 * @return Cadena que representa la longitud en GMS con el siguiente formato: <br>
	 * GGG°MM'SS"H <br>
	 * La cadena tiene exactamente 11 caracteres. <br>
	 * El cuarto caracter es º, el séptimo carácter es ' y el décimo es ". <br>
	 * GGG son tres dígitos que representan los grados sexagesimales, <br>
	 * MM son dos dígitos que representan los minutos sexagesimales, <br>
	 * SS son dos dígitos que representan los segundos sexagesimales. <br>
	 * H puede ser o bien el carácter 'E' o el carácter 'W' según sea <br>
	 * una longitud al Este del meridiano de Greenwich (meridiano 0º) o al Oeste del meridiano 0º. <br>
	 * Ejemplos: 135°05'59"E o 059°45'00"W o 007°28'35"E o 000º00'00"E
	 */
	public String getLongitudGMS() {
		//TODO. Auto-generated method stub
		return "";
	}
	
	/**
	 * Consulta la latitud en GD.
	 * Se expresa la latitud en Grados Decimales. Los grados minutos y segundos se expresan con un único
	 * número decimal. Si se tiene que la posición de latitud está dada en GMS por GGGºMM'SS''H ({@link #getLatitudGMS()}), 
	 * la latitud en GD sería (signo)(GGG + MM/60+ SS/3600). El signo 
	 * tendría en cuenta si la latitud es en el hemisferio norte (H es N), en cuyo caso sería positivo, 
	 * o sur (H es S), en cuyo caso sería negativo.
	 * @return un número decimal {@code result} tal que {@code result>-90 && result<90} 
	 */
	public double getLatitudGD() {
		//TODO. Auto-generated method stub
		return 0;
	}
	
	/**
	 * Consulta la longitud en GD.
	 * Se expresa la longitud en Grados Decimales. Los grados minutos y segundos se expresan con un único
	 * número decimal. Si se tiene que la posición de longitud está dada en GMS por GGGºMM'SS''H ({@link #getLongitudGMS()}), 
	 * la longitud en GD sería (signo)(GG + MM/60+ SS/3600). El signo 
	 * tendría en cuenta si la latitud es al este del meridiano 0º (H es E), en cuyo caso sería positivo, 
	 * o al oeste del meridiano 0º (H es W), en cuyo caso sería negativo.
	 * @return un número decimal {@code result} tal que {@code result>-180 && result<180} 
	 */
	public double getLongitudGD() {
		//TODO. Auto-generated method stub
		return 0;
	}		
	
	/**
	 * Consulta la distancia de la coordenada {@code this} a la coordenada {@code otra}
	 * @param otra no puede ser null
	 * @throws IllegalArgumentException cuando {@code otra==null}
	 * @return distancia en kilómetros entre las coordenadas {@code this} y {@code otra}, garantiza
	 * que el resultado siempre es mayor o igual que cero. Es cero cuando {@code this.equals(otra)}.
	 * Debe garantizar simetría, es decir, {@code a.distance(b)==b.distance(a)}
	 */
	public double getDistanciaA(CoordenadasGPS otra) {
		//TODO. Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "";
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return 0;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return false;
	}
	
}
