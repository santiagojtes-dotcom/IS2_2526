package es.unican.is2;

import java.time.LocalDate;

/**
 * Clase que representa un seguro de coche.
 */
public class Seguro {
	
	private long id;

    private String matricula;

	private int potencia;

    private Cobertura cobertura;
    
    private LocalDate fechaInicio;

	private String conductorAdicional;
	
	// constructor vacio para que otras clases no fallen
	public Seguro() {
	}
	
	public Seguro(String matricula, Cobertura cobertura, LocalDate fechaInicio, int potencia) {
	    this.matricula = matricula;
	    this.cobertura = cobertura;
	    this.fechaInicio = fechaInicio;
	    this.potencia = potencia;
	}

	/**
	 * Retorna el identificador del seguro
	 */
	public long getId() {
		return id;
	}

	/**
	 *  Asigna el valor del identificador del seguro
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Retorna la matricula del coche 
	 * asociado al seguro
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 *  Asigna el valor de la matrícula del seguro
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	/**
	 * Retorna la fecha de contratacion del seguro
	 */
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * Asigna la fecha de inicio del seguro
	 * @param fechaInicio La fecha de inicio del seguro
	 */
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * Retorna el tipo de cobertura del seguro
	 */
	public Cobertura getCobertura() {
		return cobertura;
	}

	/**
	 * Asigna el tipo de cobertura del seguro
	 * @param cobertura El tipo de cobertura del seguro
	 */	
	public void setCobertura(Cobertura cobertura) {
		this.cobertura = cobertura;		
}

	/**
     * Retorna la potencia del coche asociado 
     * al seguro. 
     */
    public int getPotencia() {
        return potencia;
    }

	/**
	 *  Asigna el valor del identificador del seguro
	 */
	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}

	/**
	 * Retorna el conductor adicional del seguro, si lo hay
	 * @return El conductor adicional si lo hay
	 * 		null en caso contrario
	 */
	public String getConductorAdicional() {
		return conductorAdicional;
	}

	/**
	 * Asigna el conductor adicional del seguro
	 * @param conductorAdicional
	 */
	public void setConductorAdicional(String conductorAdicional) {
		this.conductorAdicional = conductorAdicional;
	}
    
    /**
     * Retorna el precio del seguro. 
	 * El precio se calcula a partir de la cobertura, la potencia del coche y el tiempo que lleva contratado el seguro
	 * @return El precio del seguro
	 *         0 si el seguro todavía no está en vigor (no se ha alcanzado su fecha de inicio)
     */
	public double precio() {
	    double precioBase = 0;
	    
	    // Determinar precio base por cobertura
	    switch (cobertura) {
	        case TODO_RIESGO: precioBase = 600; break;
	        case TERCEROS: precioBase = 400; break;
	        case TERCEROS_LUNAS: precioBase = 450; break; 
	    }

	    // Aplicar recargos por potencia
	    if (potencia >= 90 && potencia <= 110) {
	        precioBase *= 1.05; // +5% recargo
	    } else if (potencia > 110) {
	        precioBase *= 1.08; // +8% recargo
	    }

	    // Aplicar descuento por antiguedad
	    // si ha pasado mas de 1 mes aplica el 10%
	    if (fechaInicio.isBefore(LocalDate.now().minusMonths(1))) {
	        precioBase *= 0.9; // -10% descuento
	    }

	    // retornar 0 si no está en vigor
	    if (fechaInicio.isAfter(LocalDate.now())) {
	        return 0;
	    }

	    return precioBase;
	}
	
}
