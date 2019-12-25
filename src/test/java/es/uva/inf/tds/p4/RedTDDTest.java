package es.uva.inf.tds.p4;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.easymock.Mock;
import static org.easymock.EasyMock.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class RedTDDTest {

	@Mock
	private Linea l1;
	@Mock
	private Linea l2;
	
	@Mock
	private Estacion e;
	
	private Red r;
	private int num;
	private String color;
	@Mock
	private CoordenadasGPS cgps;
	private CoordenadasGPS[] argps;
	
	@BeforeEach
	public void setUp() {
		l1=createMock(Linea.class);
		l2=createMock(Linea.class);
		e=createMock(Estacion.class);
		
		ArrayList<Linea> al = new ArrayList<>();
		al.add(l1);
		al.add(l2);
		
		r = new Red(al);
		num = 1;
		color = "Red";
		cgps= createMock(CoordenadasGPS.class);
				//new CoordenadasGPS(10.0,-1.0);
		argps= new CoordenadasGPS[1];
		argps[0]=cgps;
	}
	
	@Tag("TDD")
	@Test
	public void constructor() {
		ArrayList<Linea> al = new ArrayList<>();
		al.add(l1);
		al.add(l2);
		Red r = new Red(al);
		assertNotNull(r);
	}
	
	@Tag("TDD")
	@Test
	public void constructorNull() {
		ArrayList<Linea> al = null;
		assertThrows(IllegalArgumentException.class, () -> {
			 @SuppressWarnings("unused")
			Red r= new Red(al);
		});
	}
	
	@Tag("TDD")
	@Tag("Isolation")
	@Test
	public void getLineaNumber() {
		expect(l1.getNumero()).andReturn(1).anyTimes();
		replay(l1);
		assertEquals(l1, r.getLinea(num));
		verify(l1);
	}
	
	@Tag("TDD")
	@Tag("Isolation")
	@Test
	public void getLineaColor() {
		expect(l1.getColor()).andReturn("Red").anyTimes();
		replay(l1);
		assertEquals(l1, r.getLinea(color));
		verify(l1);
	}
	
	@Tag("TDD")
	@Tag("Isolation")
	@Test
	public void addLineaRemoveLinea() {
		Linea l3 = createMock(Linea.class);
		expect(l1.getNumero()).andReturn(1).anyTimes();
		expect(l1.getColor()).andReturn("Red").anyTimes();
		expect(l2.getNumero()).andReturn(2).anyTimes();
		expect(l2.getColor()).andReturn("Blue").anyTimes();
		expect(l3.getNumero()).andReturn(3).anyTimes();
		expect(l3.getColor()).andReturn("Green").anyTimes();
		replay(l1);
		replay(l2);
		replay(l3);
		r.addLinea(l3);
		assertSame(3,r.getArrayLineas().length);
		r.removeLinea(l3);
		assertSame(2, r.getArrayLineas().length);
		verify(l1);
		verify(l2);
		verify(l3);
	}
	
	@Tag("TDD")
	@Tag("Isolation")
	@Test
	public void addLineasNull() {
		Linea l3 = null;
		assertThrows(IllegalArgumentException.class, () -> {
			r.addLinea(l3);
		});
	}
	
	@Tag("TDD")
	@Tag("Isolation")
	@Test
	public void removeLineasMenor2() {
		assertThrows(IllegalStateException.class, () -> {
			r.removeLinea(l2);
		});
	}
	
	@Tag("TDD")
	@Tag("Isolation")
	@Test
	public void getInfoLineasEstacion(){
		expect(l1.contieneEstacion(e)).andReturn(true).anyTimes();
		expect(l2.contieneEstacion(e)).andReturn(false).anyTimes();
		expect(l1.getNumero()).andReturn(1).anyTimes();
		expect(l1.getColor()).andReturn("Red").anyTimes();
		expect(l2.getNumero()).andReturn(2).anyTimes();
		expect(l2.getColor()).andReturn("Blue").anyTimes();
		replay(l1);
		replay(l2);
		ArrayList<Linea> eal = new ArrayList<>();
		eal.add(l1);
		assertArrayEquals(eal.toArray(), r.getLineasPorEstacion(e).toArray());
		ArrayList<String> sal = new ArrayList<>();
		String s = "Linea 1, Red";
		sal.add(s);
		assertArrayEquals(sal.toArray(), r.getInfoLineasPorEstacion(e).toArray());
		verify(l1);
		verify(l2);
	}
	
	@Tag("TDD")
	@Tag("Isolation")
	@Test
	public void getLineasEstacionNull() {
		e=null;
		assertThrows(IllegalArgumentException.class, () -> {
			r.getLineasPorEstacion(e);
		});
	}
	
	@Tag("TDD")
	@Tag("Isolation")
	@Test
	public void getInfoLineasEstacionNull() {
		e=null;
		assertThrows(IllegalArgumentException.class, () -> {
			r.getInfoLineasPorEstacion(e);
		});
	}
	
	@Tag("TDD")
	@Tag("Isolation")
	@Test
	public void correspondenciaLineas() {
		Estacion[] mockRes = new Estacion[1];
		mockRes[0]=e;
		expect(l1.getCorrespondencias(l2)).andReturn(mockRes).anyTimes();
		replay(l1);
		
		assertArrayEquals(mockRes,r.correspondenciaLineas(l1,l2));
	}
	
	@Tag("TDD")
	@Tag("Isolation")
	@Test
	public void correspondenciaLineasNull() {
		l1=null;
		assertThrows(IllegalArgumentException.class, () -> {
			r.correspondenciaLineas(l1,l2);
		});
		
	}
	
	@Tag("TDD")
	@Tag("Isolation")
	@Test
	public void conexionSinTransbordo() {
		Estacion e2 = createMock(Estacion.class);
		expect(l1.contieneEstacion(e)).andReturn(true).anyTimes();
		expect(l1.contieneEstacion(e2)).andReturn(true).anyTimes();
		replay(l1);
		assertEquals(l1, r.conexionSinTransbordo(e,e2));
		verify(l1);
	}
	
	@Tag("TDD")
	@Tag("Isolation")
	@Test
	public void conexionSinTransbordoNull() {
		Estacion e2=null;
		assertThrows(IllegalArgumentException.class, () -> {
			r.conexionSinTransbordo(e,e2);
		});
	}
	
	@Tag("TDD")
	@Tag("Isolation")
	@Test
	public void conexionConTransbordo() {
		Estacion e2 = createMock(Estacion.class);
		Estacion e3 = createMock(Estacion.class);
		Estacion[] ear = new Estacion[1];
		ear[0]=e2;
		expect(l1.contieneEstacion(e)).andReturn(true).anyTimes();
		expect(l1.contieneEstacion(e2)).andReturn(true).anyTimes();
		expect(l1.contieneEstacion(e3)).andReturn(false).anyTimes();
		expect(l1.hayCorrespondencia(l2)).andReturn(true).anyTimes();
		expect(l1.getCorrespondencias(l2)).andReturn(ear).anyTimes();
		expect(l2.contieneEstacion(e)).andReturn(false).anyTimes();
		expect(l2.contieneEstacion(e2)).andReturn(true).anyTimes();
		expect(l2.contieneEstacion(e3)).andReturn(true).anyTimes();
		
		replay(l1);
		replay(l2);
		
		ArrayList<Estacion> lal = new ArrayList<>();
		lal.add(e);
		lal.add(e2);
		lal.add(e3);
		
		assertArrayEquals(lal.toArray(), r.conexionConTransbordo(e,e3));
		verify(l1);
		verify(l2);
	}
	
	@Tag("TDD")
	@Tag("Isolation")
	@Test
	public void conexionConTransbordoNull() {
		Estacion e3=null;
		assertThrows(IllegalArgumentException.class, () -> {
			r.conexionConTransbordo(e,e3);
		});
	}
	
	@Tag("TDD")
	@Tag("Isolation")
	@Test
	public void estacionMasCercana() {
		Estacion e0=createMock(Estacion.class);
		Estacion[] est0 = new Estacion[1];
		est0[0]=e0;
		
		CoordenadasGPS cgps0= createMock(CoordenadasGPS.class);
		CoordenadasGPS[] argps0= new CoordenadasGPS[1];
		argps0[0]=cgps0;
		
		Estacion[] est = new Estacion[1];
		est[0]=e;
		Estacion e2=createMock(Estacion.class);
		Estacion[] est2 = new Estacion[1];
		est2[0]=e2;
		
		CoordenadasGPS cgps2= createMock(CoordenadasGPS.class);
		CoordenadasGPS[] argps2= new CoordenadasGPS[1];
		argps2[0]=cgps2;
		
		CoordenadasGPS gps1 = createMock(CoordenadasGPS.class);
		expect(gps1.getDistanciaA(cgps)).andReturn(1.0).anyTimes();
		expect(gps1.getDistanciaA(cgps0)).andReturn(10.0).anyTimes();
		expect(gps1.getDistanciaA(cgps2)).andReturn(100.0).anyTimes();
		
		expect(e0.getCoordenadasGPS()).andReturn(argps0).anyTimes();
		expect(e.getCoordenadasGPS()).andReturn(argps).anyTimes();
		expect(e2.getCoordenadasGPS()).andReturn(argps2).anyTimes();
		expect(l1.getEstaciones(false)).andReturn(est0).anyTimes();
		expect(l1.getEstaciones(true)).andReturn(est).anyTimes();
		expect(l2.getEstaciones(true)).andReturn(est2).anyTimes();
		expect(l2.getEstaciones(false)).andReturn(est2).anyTimes();
		
		replay(e);
		replay(e2);
		replay(e0);
		replay(l1);
		replay(l2);
		
				//new CoordenadasGPS(5.0,-1.0);
		replay(cgps);
		replay(cgps2);
		replay(cgps0);
		replay(gps1);
		assertEquals(e,r.getEstacionMasCercana(gps1));
		verify(e);
		verify(e2);
		verify(e0);
		verify(l1);
		verify(l2);
		verify(cgps);
		verify(cgps2);
		verify(cgps0);
		verify(gps1);
	}
	
	@AfterEach
	public void tearDown() {
		l1=null;
		l2=null;
		e=null;
	}

}
