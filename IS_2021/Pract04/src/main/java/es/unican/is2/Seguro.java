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
	
	public Seguro (int potencia, Cliente cliente, Cobertura cobertura) {
		this.potenciaCV = potencia;
		this.tomadorSeguro = cliente;
		this.cobertura = cobertura;
	}
	
	public double precio() throws DatoIncorrectoException {
		
		double precioBase = 0.0;
		double porcentajePotencia = 0.0;
		
		if (potenciaCV < 0 || fechaUltimoSiniestro.isAfter(LocalDate.now())) { // TODO fechaIncorrecta
			throw new DatoIncorrectoException();
		}
		// Cobertura
		if (cobertura.equals(Cobertura.TODORIESGO)) {
			precioBase = PRECIOTODORIESGO;
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
		// Fecha ultimo siniestro
		if (fechaUltimoSiniestro.isAfter(LocalDate.now().minusYears(1)) && fechaUltimoSiniestro.isBefore(LocalDate.now())) {
			precioBase += 200;
		} else if (fechaUltimoSiniestro.isAfter(LocalDate.now().minusYears(3)) && 
				fechaUltimoSiniestro.isBefore(LocalDate.now().minusYears(1))) {
			precioBase += 50;
		}
		// Minusvalia
		double precio = precioBase + precioBase*porcentajePotencia;
		if (tomadorSeguro.getMinusvalia()) {
			return precio - precio*0.25;
		}
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
	
	public void setFechaUltimoSiniestro(LocalDate fecha) {
		this.fechaUltimoSiniestro = fecha;
	}
	
	public LocalDate getFechaUltimoSiniestro() {
		return fechaUltimoSiniestro;
	}

}
