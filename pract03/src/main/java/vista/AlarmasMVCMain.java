package vista;

import controlador.AlarmasControlador;
import modelo.Alarmas;

public class AlarmasMVCMain {

	public static void main (String[] args) {
		MainWindow vista1 = new MainWindow();
		PopUpApagarAlarma vista2 = new PopUpApagarAlarma();
		Alarmas modelo = new Alarmas();
		AlarmasControlador controlador = new AlarmasControlador(modelo, vista1, vista2);
		vista1.setVisible();
	}
}
