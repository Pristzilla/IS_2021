package es.unican.is2;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.time.Period;

import org.junit.Before;
import org.junit.Test;

import es.unican.is2.Seguro.DatoIncorrectoException;

public class SeguroTest {
	
	private Seguro sut;
	private Cliente clienteMinus;
	private Cliente clienteNoMinus;
	
	@Before
	public void setUp() throws Exception {
		clienteMinus = new Cliente("Pepe", "12345678A", true);
		clienteNoMinus = new Cliente("Pepe", "12345678A", false);
	}
	
	@Test
	public void testConstructor() {
		// Casos de prueba validos
		try {
			sut = new Seguro(90, clienteMinus, Seguro.Cobertura.TODORIESGO);
			assertTrue(sut.getPotencia()==90);
			assertTrue(sut.getCliente().equals(clienteMinus));
			assertTrue(sut.getCobertura()==Seguro.Cobertura.TODORIESGO);	
		} catch (DatoIncorrectoException e) {
			fail();
		}

		try {
			sut = new Seguro(100, clienteMinus, Seguro.Cobertura.TERCEROSLUNAS);
			assertTrue(sut.getPotencia()==100);
			assertTrue(sut.getCliente().equals(clienteMinus));
			assertTrue(sut.getCobertura()==Seguro.Cobertura.TERCEROSLUNAS);	
		} catch (DatoIncorrectoException e) {
			fail();
		}
		
		try {
			sut = new Seguro(110, clienteMinus, Seguro.Cobertura.TERCEROS);
			assertTrue(sut.getPotencia()==110);
			assertTrue(sut.getCliente().equals(clienteMinus));
			assertTrue(sut.getCobertura()==Seguro.Cobertura.TERCEROS);	
		} catch (DatoIncorrectoException e) {
			fail();
		}
		
		try {
			sut = new Seguro(200, clienteMinus, Seguro.Cobertura.TODORIESGO);
			assertTrue(sut.getPotencia()==200);
			assertTrue(sut.getCliente().equals(clienteMinus));
			assertTrue(sut.getCobertura()==Seguro.Cobertura.TODORIESGO);	
		} catch (DatoIncorrectoException e) {
			fail();
		}
		
		// Casos de prueba no validos
		try {
			sut = new Seguro(-1, clienteMinus, Seguro.Cobertura.TODORIESGO);
			fail();
		} catch (DatoIncorrectoException e) {}
		
		try {
			Cliente c = null;
			sut = new Seguro(90, c, Seguro.Cobertura.TERCEROSLUNAS);
			fail();
		} catch (NullPointerException e) {}
	}
	
	@Test
	public void testPrecio() {
		
		// Casos de prueba validos
		try {
			sut = new Seguro(90, clienteMinus, Seguro.Cobertura.TODORIESGO);
			LocalDate fecha = LocalDate.now();
			sut.setFechaUltimoSiniestro(fecha.minusYears(1));
			System.out.print("El precio esperado eran 937,5 y es: "+sut.precio());
			assertTrue(sut.precio()==937.5);
		} catch (DatoIncorrectoException e) {
			fail();
		}
		
		try {
			sut = new Seguro(100, clienteNoMinus, Seguro.Cobertura.TERCEROSLUNAS);
			LocalDate fecha = LocalDate.now();
			sut.setFechaUltimoSiniestro(fecha.minusMonths(6));
			assertTrue(sut.precio()==830);
		} catch (DatoIncorrectoException e) {
			fail();
		}
		
		try {
			sut = new Seguro(110, clienteMinus, Seguro.Cobertura.TERCEROS);
			LocalDate fecha = LocalDate.now();
			sut.setFechaUltimoSiniestro(fecha);
			System.out.print("El precio esperado eran 465 y es: "+sut.precio());
			assertTrue(sut.precio()==465);
		} catch (DatoIncorrectoException e) {
			fail();
		}
		
		try {
			sut = new Seguro(200, clienteNoMinus, Seguro.Cobertura.TODORIESGO);
			LocalDate fecha = LocalDate.now();
			sut.setFechaUltimoSiniestro(fecha.minusYears(3));
			assertTrue(sut.precio()==1250);
		} catch (DatoIncorrectoException e) {
			fail();
		}
		
		try {
			sut = new Seguro(200, clienteNoMinus, Seguro.Cobertura.TERCEROSLUNAS);
			LocalDate fecha = LocalDate.now();
			sut.setFechaUltimoSiniestro(fecha.minusYears(2));
			assertTrue(sut.precio()==770);
		} catch (DatoIncorrectoException e) {
			fail();
		}
		
		try {
			sut = new Seguro(90, clienteNoMinus, Seguro.Cobertura.TERCEROS);
			LocalDate fecha = LocalDate.now();
			sut.setFechaUltimoSiniestro(fecha.minusYears(5));
			assertTrue(sut.precio()==420);
		} catch (DatoIncorrectoException e) {
			fail();
		}
		
		// Casos de prueba no validos
		/* El caso de prueba no valido con cobertura "terceros ampliados" no se puede implementar
		 * debido al enumerado, por eso ya no lo probamos.
		 * El caso de prueba no valido con potencia "AAA" no se puede implementar
		 * porque Java controla que el campo sea un entero.
		 * El caso de prueba no valido con fecha invalida (2/13/2021) ya lo gestiona la
		 * clase LocalDate.
		*/
		try {
			sut = new Seguro(-1, clienteNoMinus, Seguro.Cobertura.TODORIESGO);
			LocalDate fecha = LocalDate.now();
			sut.setFechaUltimoSiniestro(fecha.minusYears(1));
			sut.precio();
			fail();
		} catch (DatoIncorrectoException e) {}
		
		try {
			sut = new Seguro(110, clienteNoMinus, Seguro.Cobertura.TODORIESGO);
			LocalDate fecha = LocalDate.now();
			sut.setFechaUltimoSiniestro(fecha.plusDays(1));
			sut.precio();
			fail();
		} catch (DatoIncorrectoException e) {}
	}
}
