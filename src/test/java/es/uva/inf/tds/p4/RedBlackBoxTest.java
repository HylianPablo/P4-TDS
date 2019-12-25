package es.uva.inf.tds.p4;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.easymock.Mock;
import static org.easymock.EasyMock.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;



public class RedBlackBoxTest {
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
	
	@Tag("BlackBoxTestFirst")
	@Test
	public void constructorConMenosDosLineas() {
		ArrayList<Linea> dummy = new ArrayList<>();
		dummy.add(l1);
		assertThrows(IllegalArgumentException.class, () -> {
			@SuppressWarnings("unused")
			Red r2 = new Red(dummy);
		});
	}
	
	@Tag("BlackBoxTestFirst")
	@Tag("Isolation")
	@Test
	public void getLineaNumberNoExiste() {
		expect(l1.getNumero()).andReturn(1).anyTimes();
		expect(l2.getNumero()).andReturn(2).anyTimes();
		replay(l1);
		replay(l2);
		assertNull(r.getLinea(3));
		fail("UNTIL GREEN PHASE");
		verify(l1);
		verify(l2);
	}
	
	@Tag("BlackBoxTestFirst")
	@Tag("Isolation")
	@Test
	public void getLineaColorNoExiste() {
		expect(l1.getColor()).andReturn("Red").anyTimes();
		expect(l2.getColor()).andReturn("Blue").anyTimes();
		replay(l1);
		replay(l2);
		assertNull(r.getLinea("Green"));
		fail("UNTIL GREEN PHASE");
		verify(l1);
		verify(l2);
	}
	
	@Tag("BlackBoxTestFirst")
	@Tag("Isolation")
	@Test
	public void addLineaNumRepetido() {
		Linea l3 = createMock(Linea.class);
		
		expect(l1.getNumero()).andReturn(1).anyTimes();
		expect(l2.getNumero()).andReturn(2).anyTimes();
		expect(l3.getNumero()).andReturn(2).anyTimes();
		expect(l1.getColor()).andReturn("Red").anyTimes();
		expect(l2.getColor()).andReturn("Blue").anyTimes();
		expect(l3.getColor()).andReturn("Green").anyTimes();
		
		replay(l1);
		replay(l2);
		replay(l3);
		
		assertThrows(IllegalArgumentException.class, () -> {
			r.addLinea(l3);
		});
		
		verify(l1);
		verify(l2);
		verify(l3);
	}
	
	@Tag("BlackBoxTestFirst")
	@Tag("Isolation")
	@Test
	public void addLineaColorRepetido() {
		Linea l3 = createMock(Linea.class);
		
		expect(l1.getNumero()).andReturn(1).anyTimes();
		expect(l2.getNumero()).andReturn(2).anyTimes();
		expect(l3.getNumero()).andReturn(3).anyTimes();
		expect(l1.getColor()).andReturn("Red").anyTimes();
		expect(l2.getColor()).andReturn("Blue").anyTimes();
		expect(l3.getColor()).andReturn("Blue").anyTimes();
		
		replay(l1);
		replay(l2);
		replay(l3);
		
		assertThrows(IllegalArgumentException.class, () -> {
			r.addLinea(l3);
		});
		
		verify(l1);
		verify(l2);
		verify(l3);
	}
	
	@Tag("BlackBoxTestFirst")
	@Tag("Isolation")
	@Test
	public void eliminarLineaInexistente() {
		Linea l3 = createMock(Linea.class);
		assertThrows(IllegalArgumentException.class, () -> {
			r.removeLinea(l3);
		});
	}
	
	@Tag("BlackBoxTestFirst")
	@Tag("Isolation")
	@Test
	public void conexionSinTransbordoInexistente() {
		Estacion e2 = createMock(Estacion.class);
		expect(l1.contieneEstacion(e)).andReturn(true).anyTimes();
		expect(l1.contieneEstacion(e2)).andReturn(false).anyTimes();
		replay(l1);
		
		assertNull(r.conexionSinTransbordo(e, e2));
		fail("UNTIL GREEN PHASE");
		verify(l1);
	}
	
	@Tag("BlackBoxTestFirst")
	@Tag("Isolation")
	@Test
	public void conexionConTransbordoInexistente() {
		Estacion e2 = createMock(Estacion.class);
		expect(l1.contieneEstacion(e)).andReturn(true).anyTimes();
		expect(l2.contieneEstacion(e2)).andReturn(true).anyTimes();
		replay(l1);
		replay(l2);
		
		assertNull(r.conexionConTransbordo(e, e2));
		fail("UNTIL GREEN PHASE");
		verify(l1);
		verify(l2);
	}
	
	
	@AfterEach
	public void tearDown() {
		l1=null;
		l2=null;
		e=null;
	}

}
