package es.unican.is2;

import java.time.LocalDate;

public class Seguro {
	
	private LocalDate fechaUltimoSiniestro;
	private int potenciaCV;
	public enum Cobertura {TODORIESGO,TERCEROSLUNAS,TERCEROS};
	private Cobertura cobertura;
	private Cliente tomadorSeguro;
	
	// Precio base del seguro por tipo de cobertura
	private final static int PRECIOTODORIESGO = 1000;
	private final static int PRECIOTERCEROSLUNAS = 600;
	private final static int PRECIOTERCEROS = 400;
	
	// Excepcion
	@SuppressWarnings("serial")
	public static class DatoIncorrectoException extends RuntimeException {};
	
	public Seguro (int potencia, Cliente cliente, Cobertura cobertura) throws DatoIncorrectoException {
		if (potencia < 0) {
			throw new DatoIncorrectoException();			
		}
		if (cliente == null) {
			throw new NullPointerException();
		}
		this.potenciaCV = potencia;
		this.tomadorSeguro = cliente;
		this.cobertura = cobertura;
	}
	
	public double precio()  {
		
		double precioBase = 0.0;
		double porcentajePotencia = 0.0;
		
		
		// Cobertura
		if (cobertura.equals(Cobertura.TODORIESGO)) {
			precioBase = PRECIOTODORIESGO;
			//System.out.print("El precio base es: "+precioBase);
		} else if (cobertura.equals(Cobertura.TERCEROSLUNAS)) {
			precioBase = PRECIOTERCEROSLUNAS;
		} else {
			precioBase = PRECIOTERCEROS;
		}
		// Potencia
		if (potenciaCV >= 90 && potenciaCV <= 110) {
			porcentajePotencia = 0.05;
		} else if (potenciaCV > 110) {
			porcentajePotencia = 0.2;
		}
		double precio = precioBase + precioBase*porcentajePotencia;
		// Fecha ultimo siniestro
		if (fechaUltimoSiniestro.isAfter(LocalDate.now().minusYears(1).minusDays(1)) && 
				fechaUltimoSiniestro.isBefore(LocalDate.now().plusDays(1))) {
			precio += 200;
			//System.out.print("El precio base es: "+precioBase);
		} else if (fechaUltimoSiniestro.isAfter(LocalDate.now().minusYears(3).minusDays(1)) && 
				fechaUltimoSiniestro.isBefore(LocalDate.now().minusYears(1))) {
			precio += 50;
		}
		// Minusvalia
		if (tomadorSeguro.getMinusvalia()) {
			//System.out.print("El descuento minusvalido es: "+precio);
			return precio - precio*0.25;
		}
		//System.out.print("El precio esperado eran 937,5 y es: "+precio);
		return precio;
	}
	
	public int getPotencia() {
		return potenciaCV;
	}
	
	public Cliente getCliente() {
		return tomadorSeguro;
	}
	
	public Cobertura getCobertura() {
		return cobertura;
	}
	
	public void setFechaUltimoSiniestro(LocalDate fecha) throws DatoIncorrectoException {
		if ( fecha.isAfter(LocalDate.now())) { // TODO fechaIncorrecta
			throw new DatoIncorrectoException();
		}
		this.fechaUltimoSiniestro = fecha;
	}


}
