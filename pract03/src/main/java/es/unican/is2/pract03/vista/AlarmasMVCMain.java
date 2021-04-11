package es.unican.is2.pract03.vista;

import es.unican.is2.pract03.controlador.AlarmasControlador;
import es.unican.is2.pract03.modelo.Alarmas;

public class AlarmasMVCMain {

	public static void main (String[] args) {
		MainWindow vista = new MainWindow();
		Alarmas modelo = new Alarmas();
		@SuppressWarnings("unused")
		AlarmasControlador controlador = new AlarmasControlador(modelo, vista);
		vista.setVisible();
	}
}
