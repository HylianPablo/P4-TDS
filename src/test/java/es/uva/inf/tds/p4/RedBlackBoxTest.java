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
	private Linea l3;

	@Mock
	private Estacion e;
	@Mock
	private Estacion e2;

	private Red r;

	@BeforeEach
	public void setUp() {
		l1 = createMock(Linea.class);
		l2 = createMock(Linea.class);
		l3 = createMock(Linea.class);
		e = createMock(Estacion.class);
		e2 = createMock(Estacion.class);

		expect(l1.getNumero()).andReturn(1).anyTimes();
		expect(l2.getNumero()).andReturn(2).anyTimes();
		expect(l1.getColor()).andReturn("Red").anyTimes();
		expect(l2.getColor()).andReturn("Blue").anyTimes();
		expect(l1.contieneEstacion(e)).andReturn(true).anyTimes();
		expect(l2.contieneEstacion(e2)).andReturn(true).anyTimes();
		expect(l1.contieneEstacion(e2)).andReturn(false).anyTimes();
		expect(l2.contieneEstacion(e)).andReturn(false).anyTimes();
		expect(l1.hayCorrespondencia(l2)).andReturn(false).anyTimes();
		replay(l1);
		replay(l2);

		ArrayList<Linea> al = new ArrayList<>();
		al.add(l1);
		al.add(l2);

		r = new Red(al);
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
	@Test
	public void constructorNoEnOrden() {
		expect(l3.getNumero()).andReturn(200).anyTimes();
		expect(l3.getColor()).andReturn("Purple").anyTimes();
		replay(l3);
		ArrayList<Linea> arrl = new ArrayList<>();
		arrl.add(l3);
		assertThrows(IllegalArgumentException.class, () -> {
			@SuppressWarnings("unused")
			Red r2 = new Red(arrl);
		});
	}

	@Tag("BlackBoxTestFirst")
	@Tag("Isolation")
	@Test
	public void getLineaNumberNoExiste() {

		assertNull(r.getLinea(3));
		verify(l1);
		verify(l2);
	}

	@Tag("BlackBoxTestFirst")
	@Tag("Isolation")
	@Test
	public void getLineaColorNoExiste() {

		assertNull(r.getLinea("Green"));
		verify(l1);
		verify(l2);
	}

	@Tag("BlackBoxTestFirst")
	@Tag("Isolation")
	@Test
	public void addLineaNumRepetido() {
		expect(l3.getNumero()).andReturn(2).anyTimes();
		expect(l3.getColor()).andReturn("Green").anyTimes();

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
	public void addLineaNumNOSeguido() {
		expect(l3.getNumero()).andReturn(4).anyTimes();
		expect(l3.getColor()).andReturn("Green").anyTimes();

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
		expect(l3.getNumero()).andReturn(3).anyTimes();
		expect(l3.getColor()).andReturn("Blue").anyTimes();

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
	public void eliminarLineaNull() {
		Linea l3 = null;
		assertThrows(IllegalArgumentException.class, () -> {
			r.removeLinea(l3);
		});
	}

	@Tag("BlackBoxTestFirst")
	@Tag("Isolation")
	@Test
	public void correspondenciaLineasSegundaNula() {
		Linea l2 = null;
		assertThrows(IllegalArgumentException.class, () -> {
			r.correspondenciaLineas(l1, l2);
		});
	}

	@Tag("BlackBoxTestFirst")
	@Tag("Isolation")
	@Test
	public void conexionSinTransbordoInexistente() {
		assertNull(r.conexionSinTransbordo(e, e2));
		verify(l1);
	}

	@Tag("BlackBoxTestFirst")
	@Tag("Isolation")
	@Test
	public void conexionConTransbordoInexistente() {
		assertNull(r.conexionConTransbordo(e, e2)[2]);
		verify(l1);
		verify(l2);
	}

	@AfterEach
	public void tearDown() {
		l1 = null;
		l2 = null;
		l3 = null;
		e = null;
		e2 = null;
	}

}