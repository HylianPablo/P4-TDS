package es.uva.inf.tds.p4;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.easymock.Mock;
import org.json.JSONException;

import static org.easymock.EasyMock.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

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
	public void Sequence() throws JSONException {
		l1 = createMock(Linea.class);
		l2 = createMock(Linea.class);
		e = createMock(Estacion.class);
		e2 = createMock(Estacion.class);
		e3 = createMock(Estacion.class);
		e4 = createMock(Estacion.class);
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

		Estacion[] are = new Estacion[1];
		are[0] = e;

		Estacion[] estl1 = new Estacion[3];
		estl1[0] = e;
		estl1[1] = e2;
		estl1[2] = e3;
		Estacion[] estl2 = new Estacion[3];
		estl2[0] = e;
		estl2[1] = e2;
		estl2[2] = e4;

		expect(l1.hayCorrespondencia(l2)).andReturn(true).anyTimes();
		expect(l1.getCorrespondencias(l2)).andReturn(are).anyTimes();

		expect(l1.getEstaciones(true)).andReturn(estl1).anyTimes();
		expect(l1.getEstaciones(false)).andReturn(estl1).anyTimes();

		expect(l2.getEstaciones(true)).andReturn(estl2).anyTimes();
		expect(l2.getEstaciones(false)).andReturn(estl2).anyTimes();

		replay(l1);
		replay(l2);

		Red r = new Red(al);
		CoordenadasGPS cgps = createMock(CoordenadasGPS.class);
		CoordenadasGPS[] argps = new CoordenadasGPS[1];
		argps[0] = cgps;

		CoordenadasGPS cgps2 = createMock(CoordenadasGPS.class);
		CoordenadasGPS[] argps2 = new CoordenadasGPS[1];
		argps2[0] = cgps2;

		expect(cgps2.getDistanciaA(cgps)).andReturn(100.0).anyTimes();
		expect(cgps2.getDistanciaA(cgps2)).andReturn(1.0).anyTimes();

		replay(cgps2);

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

		ArrayList<Linea> arr2 = new ArrayList<>();
		arr2.add(l1);
		arr2.add(l2);
		Red r2 = new Red(arr2);
		String in = "dummy.json";
		assertEquals(r2.getArrayLineas().length, r.loadFrom(in).getArrayLineas().length);

		r.addLinea(l3);
		JSONAssert.assertEquals(
				"{ \"lineas\" : [{\"linea0\":[{\"num\" : 1}, {\"color\" : \"Red\"}]}, {\"linea1\":[{\"num\" : 2}, {\"color\" : \"Blue\"}]}, {\"linea2\":[{\"num\" : 3}, {\"color\" : \"Green\"}]}]} ] }",
				r.updateTo("out.json"), JSONCompareMode.STRICT);
		assertSame(3, r.getArrayLineas().length);
		assertEquals(l3, r.getLinea("Green"));
		assertEquals(l2, r.getLinea(2));
		r.removeLinea(l3);
		assertSame(2, r.getArrayLineas().length);

		assertArrayEquals(are, r.correspondenciaLineas(l1, l2));

		ArrayList<String> sal = new ArrayList<>();
		String s1 = "Linea 1, Red";
		String s2 = "Linea 2, Blue";
		sal.add(s1);
		sal.add(s2);

		assertEquals(l1, r.conexionSinTransbordo(e, e2));

		assertArrayEquals(sal.toArray(), r.getInfoLineasPorEstacion(e).toArray());

		Estacion[] res = new Estacion[3];
		res[0] = e3;
		res[1] = e;
		res[2] = e4;

		assertEquals(e4, r.getEstacionMasCercana(cgps2));

		assertArrayEquals(res, r.conexionConTransbordo(e3, e4));

		assertArrayEquals(al.toArray(), r.getLineasPorEstacion(e).toArray());

		verify(l1);
		verify(l2);
		verify(e);
		verify(e2);
		verify(e3);
		verify(e4);
		verify(cgps2);
	}
}