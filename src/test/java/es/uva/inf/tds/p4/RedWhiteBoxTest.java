package es.uva.inf.tds.p4;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.easymock.Mock;
import static org.easymock.EasyMock.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class RedWhiteBoxTest {
	@Mock
	private Linea l1;
	@Mock
	private Linea l2;

	@Mock
	private Estacion e;
	@Mock
	private Estacion e2;

	private Red r;

	@BeforeEach
	public void setUp() {
		l1 = createMock(Linea.class);
		l2 = createMock(Linea.class);
		e = null;
		e2 = null;

		ArrayList<Linea> al = new ArrayList<>();
		al.add(l1);
		al.add(l2);

		r = new Red(al);
	}

	@Tag("WhiteBox")
	@Test
	public void correspondenciaLineasAmbasNulas() {
		l1 = null;
		l2 = null;
		assertThrows(IllegalArgumentException.class, () -> {
			r.correspondenciaLineas(l1, l2);
		});
	}

	@Tag("WhiteBox")
	@Test
	public void conexionSinTransbordoAmbasNulas() {
		assertThrows(IllegalArgumentException.class, () -> {
			r.conexionSinTransbordo(e2, e2);
		});
	}

	@Tag("WhiteBox")
	@Test
	public void conexionConTransbordoAmbasNulas() {
		assertThrows(IllegalArgumentException.class, () -> {
			r.conexionConTransbordo(e2, e2);
		});
	}

	@AfterEach
	public void tearDown() {
		l1 = null;
		l2 = null;
	}

}
