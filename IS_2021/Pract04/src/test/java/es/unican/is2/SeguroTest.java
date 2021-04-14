package es.unican.is2;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class SeguroTest {
	
	private Seguro seguro;
	private Cliente clienteMinus;
	private Cliente clienteNoMinus;
	
	@Before
	public void setUp() throws Exception {
		clienteMinus = new Cliente("Pepe", "12345678A", true);
		clienteNoMinus = new Cliente("Pepe", "12345678A", false);
		//seguro = new Seguro(90, cliente, Seguro.Cobertura.TODORIESGO);
	}
	
	@Test
	public void testConstructor() {
		//assertTrue(seguro.precio()==0);
	}

}
