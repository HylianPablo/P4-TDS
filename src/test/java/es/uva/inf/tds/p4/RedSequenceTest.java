package es.uva.inf.tds.p4;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.easymock.Mock;
import static org.easymock.EasyMock.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class RedSequenceTest {
	@Mock
	private Linea l1;
	@Mock
	private Linea l2;
	@Mock
	private Linea l3;
	@Mock
	private Estacion e;
	@Mock
	private Estacion e2;
	@Mock
	private Estacion e3;
	@Mock
	private Estacion e4;
	
	@Tag("Sequence")
	@Tag("Isolation")
	@Test
	public void Sequence() {
		l1 = createMock(Linea.class);
		l2 = createMock(Linea.class);
		e = createMock(Estacion.class);
		e2= createMock(Estacion.class);
		e3=createMock(Estacion.class);
		e4=createMock(Estacion.class);
		ArrayList<Linea> al = new ArrayList<>();
		al.add(l1);
		al.add(l2);
		
		expect(l1.getNumero()).andReturn(1).anyTimes();
		expect(l2.getNumero()).andReturn(2).anyTimes();
		expect(l1.getColor()).andReturn("Red").anyTimes();
		expect(l2.getColor()).andReturn("Blue").anyTimes();
		expect(l1.contieneEstacion(e)).andReturn(true).anyTimes();
		expect(l1.contieneEstacion(e2)).andReturn(true).anyTimes();
		
		expect(l1.contieneEstacion(e3)).andReturn(true).anyTimes();
		expect(l1.contieneEstacion(e4)).andReturn(false).anyTimes();
		
		expect(l2.contieneEstacion(e3)).andReturn(false).anyTimes();
		expect(l2.contieneEstacion(e4)).andReturn(true).anyTimes();
		
		expect(l2.contieneEstacion(e)).andReturn(true).anyTimes();
		expect(l2.contieneEstacion(e2)).andReturn(false).anyTimes();
		
		replay(l1);
		replay(l2);
		
		Red r = new Red(al);
		int num = 1;
		String color = "Red";
		CoordenadasGPS cgps = new CoordenadasGPS(10.0,-1.0);
		CoordenadasGPS[] argps = new CoordenadasGPS[1];
		argps[0]=cgps;
		
		CoordenadasGPS cgps2 = new CoordenadasGPS(100.0,-1.0);
		CoordenadasGPS[] argps2 = new CoordenadasGPS[1];
		argps2[0]=cgps2;
		
		expect(e.getCoordenadasGPS()).andReturn(argps).anyTimes();
		expect(e2.getCoordenadasGPS()).andReturn(argps).anyTimes();
		expect(e3.getCoordenadasGPS()).andReturn(argps).anyTimes();
		expect(e4.getCoordenadasGPS()).andReturn(argps2).anyTimes();
		
		replay(e);
		replay(e2);
		replay(e3);
		replay(e4);
		
		l3 = createMock(Linea.class);
		expect(l3.getNumero()).andReturn(3).anyTimes();
		expect(l3.getColor()).andReturn("Green").anyTimes();
		replay(l3);
		
		r.addLinea(l3);
		assertSame(3,r.getArrayLineas().length);
		assertEquals(l3,r.getLinea("Green"));
		assertEquals(l2,r.getLinea(2));
		r.removeLinea(l3);
		assertSame(2,r.getArrayLineas().length);
		
		Estacion[] are = new Estacion[1];
		are[0]=e;
		
		assertArrayEquals(are,r.correspondenciaLineas(l1, l2));
		
		ArrayList<String> sal = new ArrayList<>();
		String s1 = "Linea 1, Red";
		String s2 = "Linea 2, Blue";
		sal.add(s1);
		sal.add(s2);
		
		assertEquals(l1,r.conexionSinTransbordo(e, e2));
		
		assertArrayEquals(sal.toArray(),r.getInfoLineasPorEstacion(e).toArray());
		
		Estacion[] res = new Estacion[3];
		res[0]=e3;
		res[1]=e;
		res[2]=e4;
		
		assertEquals(e4,r.getEstacionMasCercana(cgps2));
		
		assertArrayEquals(res,r.conexionConTransbordo(e3, e4));
		
		assertArrayEquals(al.toArray(),r.getLineasPorEstacion(e).toArray());
		
		verify(l1);
		verify(l2);
		verify(e);
		verify(e2);
		verify(e3);
		verify(e4);
	}
	

}
