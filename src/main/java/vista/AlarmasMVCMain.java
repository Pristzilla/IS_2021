package vista;

import controlador.AlarmasControlador;
import modelo.Alarmas;

public class AlarmasMVCMain {

	public static void main (String[] args) {
		MainWindow vista = new MainWindow();
		Alarmas modelo = new Alarmas();
		@SuppressWarnings("unused")
		AlarmasControlador controlador = new AlarmasControlador(modelo, vista);
		vista.setVisible();
	}
}
