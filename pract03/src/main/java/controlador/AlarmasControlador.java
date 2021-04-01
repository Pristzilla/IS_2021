package controlador;

import modelo.Alarmas;
import vista.MainWindow;

public class AlarmasControlador {
	
	private Alarmas modelo;
	private MainWindow vista;
	
	public AlarmasControlador(Alarmas m, MainWindow v) {
		modelo = m;
		vista = v;
		
	}

}
