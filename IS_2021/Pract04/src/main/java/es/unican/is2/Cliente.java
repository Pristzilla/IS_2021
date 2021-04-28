package es.unican.is2;

public class Cliente {
	
	private String nombre;
	private String dni;
	private boolean minusvalia;
	
	public Cliente(String nombre, String dni, boolean minusvalia) {
		this.nombre = nombre;
		this.dni = dni;
		this.minusvalia = minusvalia;
	}
	
	public boolean getMinusvalia() {
		return this.minusvalia;
	}

}
