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
		cgps= new CoordenadasGPS(10.0,-1.0);
		argps= new CoordenadasGPS[0];
		argps[0]=cgps;
	}
	
	@Test
	public void constructor() {
		ArrayList<Linea> al = new ArrayList<>();
		al.add(l1);
		al.add(l2);
		Red r = new Red(al);
		assertNotNull(r);
		fail();
	}
	
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
	public void addLinea() {
		Linea l3 = createMock(Linea.class);
		r.addLinea(l3);
		assertSame(3,r.getArrayLineas().length);
		r.removeLinea(l3);
		assertSame(2, r.getArrayLineas().length);
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
		replay(l1);
		replay(l2);
		ArrayList<Linea> eal = new ArrayList<>();
		eal.add(l1);
		assertArrayEquals(eal.toArray(), r.getLineasPorEstacion(e).toArray());
		ArrayList<String> sal = new ArrayList<>();
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
		expect(l1.contieneEstacion(e)).andReturn(true).anyTimes();
		expect(l2.contieneEstacion(e)).andReturn(true).anyTimes();
		replay(l1);
		replay(l2);
		ArrayList<Estacion> eal = new ArrayList<>();
		eal.add(e);
		assertArrayEquals(eal.toArray(),r.correspondenciaLineas(l1,l2).toArray());
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
		expect(l1.contieneEstacion(e)).andReturn(true).anyTimes();
		expect(l1.contieneEstacion(e2)).andReturn(true).anyTimes();
		expect(l1.contieneEstacion(e3)).andReturn(false).anyTimes();
		expect(l2.contieneEstacion(e)).andReturn(false).anyTimes();
		expect(l2.contieneEstacion(e2)).andReturn(true).anyTimes();
		expect(l2.contieneEstacion(e3)).andReturn(true).anyTimes();
		
		replay(l1);
		replay(l2);
		
		ArrayList<Estacion> lal = new ArrayList<>();
		lal.add(e);
		lal.add(e2);
		lal.add(e3);
		
		assertArrayEquals(lal.toArray(), r.conexionConTransbordo(e,e3).toArray());
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
		CoordenadasGPS gps1 = new CoordenadasGPS(5.0,-1.0);
		assertEquals(e,r.getEstacionMasCercana(gps1));
	}
	
	@AfterEach
	public void tearDown() {
		l1=null;
		l2=null;
		e=null;
	}

}
