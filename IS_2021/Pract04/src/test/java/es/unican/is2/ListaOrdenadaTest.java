package es.unican.is2;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

public class ListaOrdenadaTest<E> {

	@Test
	public void get() {
		ListaOrdenada<Integer> sut = new ListaOrdenada<Integer>();
		
		// Caso de prueba no valido, lista vacia
		try {
			sut.get(2);
			fail();
		} catch (IndexOutOfBoundsException e) {}
		
		// Caso de prueba valido, lista con un elemento
		sut.add(1);
		
		try {			
			assertTrue(sut.get(0)==1);
		} catch (IndexOutOfBoundsException e) {
			fail();
		}
		
		sut.add(2);
		sut.add(3);
		sut.add(4);
		// Casos de prueba validos, lista con varios elementos
		try {			
			assertTrue(sut.get(0)==1);
		} catch (IndexOutOfBoundsException e) {
			fail();
		}
		
		try {			
			assertTrue(sut.get(3)==4);
		} catch (IndexOutOfBoundsException e) {
			fail();
		}	
		
		try {			
			assertTrue(sut.get(2)==3);
		} catch (IndexOutOfBoundsException e) {
			fail();
		}
		
		// Casos de prueba no validos, lista con elementos
		try {
			sut.get(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {}
		
		try {
			sut.get(4);
			fail();
		} catch (IndexOutOfBoundsException e) {}
	}
	
	@Test
	public void add() {
		ListaOrdenada<Integer> sut = new ListaOrdenada<Integer>();
		
		// Caso de prueba valido, lista vacia
		try {
			sut.add(1);
			assertTrue(sut.get(0)==1);
			assertTrue(sut.size()==1);
		} catch (IndexOutOfBoundsException e) {
			fail();
		}
		
		// Caso de prueba no valido, lista con un elemento
		try {
			sut.add(null);
			fail();
		} catch (NullPointerException e) {}
		
		// Caso de prueba valido, lista con un elemento
		try {
			sut.add(2);
			assertTrue(sut.get(1)==2);
			assertTrue(sut.size()==2);
		} catch (IndexOutOfBoundsException e) {
			fail();
		}
		
		sut.add(3);
		sut.add(4);
		
		// Caso de prueba no valido, lista con varios elementos
		try {
			sut.add(null);
			fail();
		} catch (NullPointerException e) {}
		
		// Caso de prueba valido, lista con varios elementos
		try {
			sut.add(3);
			assertTrue(sut.get(2)==3);
			assertTrue(sut.get(3)==3);
			assertTrue(sut.size()==5);
		} catch (IndexOutOfBoundsException e) {
			fail();
		}
		
		// Caso de prueba valido, lista con varios elementos
		try {
			sut.add(5);
			assertTrue(sut.get(5)==5);
			assertTrue(sut.size()==6);
		} catch (IndexOutOfBoundsException e) {
			fail();
		}		
	}
	
	@Test
	public void remove() {
		ListaOrdenada<Integer> sut = new ListaOrdenada<Integer>();
		
		// Caso de prueba no valido, lista vacia
		try {
			sut.remove(2);
			fail();
		} catch (IndexOutOfBoundsException e) {}
		
		// Caso de prueba valido, lista con un elemento AQUI DA ERROR
		sut.add(1);
		//System.out.println(sut.get(0).toString());
		try {
			assertTrue(sut.remove(0)==1);
			assertTrue(sut.size()==0);
		} catch (IndexOutOfBoundsException e) {
			//System.out.println(sut.get(0).toString());
			fail(); // AQUI EXACTAMENTE
		}
		
		sut.add(1);
		sut.add(2);
		sut.add(3);
		sut.add(4); 
		
		// Casos de prueba no validos, lista con elementos
		try {
			sut.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {}
		
		try {
			sut.remove(4);
			fail();
		} catch (IndexOutOfBoundsException e) {}
		
		// Casos de prueba validos, lista con varios elementos
		try {
			assertTrue(sut.remove(3)==4);
			assertTrue(sut.size()==3);
		} catch (IndexOutOfBoundsException e) {
			fail();
		}
		
		try {			
			assertTrue(sut.remove(1)==2);
			assertTrue(sut.size()==2);
		} catch (IndexOutOfBoundsException e) {
			fail();
		}	
	}
	
	@Test
	public void size() {
		ListaOrdenada<Integer> sut = new ListaOrdenada<Integer>();
		
		// Casos de prueba validos
		assertTrue(sut.size()==0);
		sut.add(1);
		assertTrue(sut.size()==1);
		sut.add(2);
		sut.add(3);
		sut.add(4);
		assertTrue(sut.size()==4);
	}
	
	@Test
	public void clear() {
		ListaOrdenada<Integer> sut = new ListaOrdenada<Integer>();
		
		// Casos de prueba validos
		sut.clear();
		assertTrue(sut.size()==0);
		sut.add(1);
		//System.out.println(sut.get(0).toString());
		sut.clear();
		//System.out.println(sut.get(0).toString());
		assertTrue(sut.size()==0); // AQUI DA EL ERROR
		sut.add(1);
		sut.add(2);
		sut.add(3);
		sut.add(4);
		sut.clear();
		assertTrue(sut.size()==0);		
	}
}
