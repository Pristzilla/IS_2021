package es.unican.is2.practica5;

public class Valor {
	
	private String entidad;	
	private int numValores;
	private double cotizacionActual;
	
	public Valor(String entidad, int numValores, double cotizaciónActual) { // WMC +1 Ccog 0
		super();
		this.entidad = entidad;
		this.numValores = numValores;
		this.cotizacionActual = cotizaciónActual;
	}
	
	public int getNumValores() { // WMC +1 Ccog 0
		return numValores;
	}

	public void setNumValores(int numValores) { // WMC +1 Ccog 0
		this.numValores = numValores;
	}

	public double getCotizacionActual() { // WMC +1 Ccog 0
		return cotizacionActual;
	}

	public void setCotizacionActual(double cotizacionActual) { // WMC +1 Ccog 0
		this.cotizacionActual = cotizacionActual;
	}

	public String getEntidad() { // WMC +1 Ccog 0
		return entidad;
	}
}