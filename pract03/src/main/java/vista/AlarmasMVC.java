package vista;

import controlador.AlarmasControlador;
import vista.MainWindow;
import modelo.Alarmas;

public class AlarmasMVC {

	public static void main (String[] args) {
		MainWindow vista = new MainWindow();
		Alarmas modelo = new Alarmas();
		AlarmasControlador controlador = new AlarmasControlador(modelo, vista);
		vista.setVisible();
	
		
		
		
		
	}
}
