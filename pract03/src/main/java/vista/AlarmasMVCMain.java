package vista;

import controlador.AlarmasControlador;
import modelo.Alarmas;

public class AlarmasMVCMain {

	public static void main (String[] args) {
		MainWindow vista1 = new MainWindow();
		Alarmas modelo = new Alarmas();
		AlarmasControlador controlador = new AlarmasControlador(modelo, vista1);
		vista1.setVisible();
	}
}
