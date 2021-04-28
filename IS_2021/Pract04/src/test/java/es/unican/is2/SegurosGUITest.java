package es.unican.is2;

import org.junit.Before;
import org.junit.*;

import org.fest.swing.fixture.FrameFixture;


public class SegurosGUITest {
	
	private FrameFixture demo;
	
	@Before
	public void setUp() {
		SegurosGUI gui = new SegurosGUI(); 
		demo = new FrameFixture(gui);
		gui.setVisible(true);
	} 
	
	@After
	public void tearDown() {
	   demo.cleanUp();
	}
	
	@Test
	public void test() {
		
		/* Interfaz tiene el aspecto deseado, estetica(no muy necesario)
		 */
		demo.button("btnCalcular").requireText("CALCULAR");
				
		// Caso de prueba valido
		
		demo.textBox("txtFechaUltimoSiniestro").setText("01/01/2021");
		demo.comboBox("comboBoxCobertura").selectItem("TODO_RIESGO");
		demo.textBox("editorPanePotencia").setText("90");
		demo.radioButton("rdbtnMinusvalia").check(); 
		demo.button("btnCalcular").click();
		demo.textBox("textFieldPrecio").requireText("937.5");
		
		// Sleep para visualizar como se realiza el test
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// Casos de prueba no validos
		demo.textBox("txtFechaUltimoSiniestro").setText("01/01/2021");
		demo.comboBox("comboBoxCobertura").selectItem("TERCEROS_LUNAS");
		demo.textBox("editorPanePotencia").setText("-1");
		demo.radioButton("rdbtnMinusvalia").check(); 
		demo.button("btnCalcular").click();
		demo.textBox("textFieldPrecio").requireText("¡Dato de entrada incorrecto!");
		
		// Sleep para visualizar como se realiza el test
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		demo.textBox("txtFechaUltimoSiniestro").setText("Sobaos el macho");
		demo.comboBox("comboBoxCobertura").selectItem("TERCEROS_SIMPLE");
		demo.textBox("editorPanePotencia").setText("110");
		demo.radioButton("rdbtnMinusvalia").check(); 
		demo.button("btnCalcular").click();
		demo.textBox("textFieldPrecio").requireText("¡Formato de fecha no valido!");
		
		// Sleep para visualizar como se realiza el test
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
}
