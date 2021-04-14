package es.unican.is2;

import java.time.LocalDate;

public class Seguro {
	
	private LocalDate fechaUltimoSiniestro;
	private int potenciaCV;
	public enum Cobertura {TODORIESGO,TERCEROSLUNAS,TERCEROS};
	private Cobertura cobertura;
	private Cliente tomadorSeguro;
	
	public Seguro (int potencia, Cliente cliente, Cobertura cobertura) {
		this.potenciaCV = potencia;
		this.tomadorSeguro = cliente;
		this.cobertura = cobertura;
	}
	
	public double precio() {
		return 0;
	}

}
