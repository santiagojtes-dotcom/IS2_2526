package es.unican.is2;

/**
 * WMC: 8
 * Justificación: La métrica WMC se computa sumando la Complejidad Ciclomática 
 * de la totalidad de los métodos de la clase:
 * - Constructor Transporte: 4 puntos (1 punto base de ejecución lineal, +2 puntos por los 
 * dos operadores de decisión '||' presentes en la primera instrucción condicional, 
 * y +1 punto por la bifurcación del segundo bloque 'if').
 * - Métodos horas(), categoria(), ton() y getPersonas(): 1 punto cada uno (4 puntos en total), 
 * dado que carecen de estructuras de control internas y su ejecución es directa.
 * Total WMC = 4 + 4 = 8.
 */
public class Transporte {
	
	private double horas;
	private int ton;
	private int personas;
	private CategoriaTransporte cat;

	/**
	 * CCog: 4
	 * Desglose CCog:
	 * +1 (Primera estructura condicional 'if' - Nivel de anidamiento 0)
	 * +1 (Penalización por la secuencia homogénea de operadores lógicos '||')
	 * +1 (Segunda estructura condicional 'if' - Nivel de anidamiento 0, dispuesta en secuencia plana respecto a la anterior)
	 * +1 (Estructura asociativa 'else' - Suma fija de 1 punto por el desvío del flujo lineal, sin penalización por profundidad)
	 */
	public Transporte(double horas, CategoriaTransporte cat, int valor) throws IllegalArgumentException {
		if (horas <= 0 || valor <= 0 || cat == null) {
			throw new IllegalArgumentException();
		}
		this.horas = horas;
		this.cat = cat;
		if (cat.equals(CategoriaTransporte.Personas)) {
			this.personas = valor;
		} else  {
			this.ton = valor;
		}
	}
	
	/**
	 * CCog: 0
	 * Desglose CCog: Flujo puramente secuencial.
	 */
	public double horas() {
		return horas;
	}

	/**
	 * CCog: 0
	 * Desglose CCog: Flujo puramente secuencial.
	 */
	public CategoriaTransporte categoria() {
		return cat;
	}

	/**
	 * CCog: 0
	 * Desglose CCog: Flujo puramente secuencial.
	 */
	public int ton() {
		return ton;
	}

	/**
	 * CCog: 0
	 * Desglose CCog: Flujo puramente secuencial.
	 */
	public int getPersonas() {
		return personas;
	}
	
}