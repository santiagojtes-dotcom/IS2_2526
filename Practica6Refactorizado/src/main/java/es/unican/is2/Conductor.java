package es.unican.is2;
import java.util.ArrayList;

/**
 * WMC: 15
 * Justificación: El valor de la métrica Weighted Methods per Class (WMC) 
 * se obtiene mediante la suma de la Complejidad Ciclomática de todos los métodos 
 * individuales definidos dentro de la estructura de esta clase:
 * - Constructor Conductor: 4 puntos (1 punto base por el flujo inicial de ejecución 
 * y 3 puntos adicionales por las tres bifurcaciones lógicas introducidas por los operadores '||').
 * - Método sueldo(): 5 puntos (1 punto base de ejecución, +1 por la estructura de bucle 'for', 
 * +2 por las tres alternativas independientes dentro del bloque 'switch', y +1 por la bifurcación 'if').
 * - Métodos getDni(), getNombre(), getApellido1(), apellido2(), getDire() y anhadeTransporte(): 
 * 1 punto cada uno (6 puntos en total), al presentar únicamente un camino lineal y secuencial de ejecución.
 * Total WMC = 4 + 5 + 6 = 15.
 */
public class Conductor {

	private ArrayList<Transporte> transportes = new ArrayList<Transporte>();
	private String dni;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String dire;

	/**
	 * CCog: 2
	 * Desglose CCog:
	 * +1 (Ruptura de flujo lineal provocada por la estructura condicional 'if' - Nivel de anidamiento 0)
	 * +1 (Penalización por la presencia de una secuencia consecutiva de operadores lógicos condicionales '||')
	 */
	public Conductor(String dni, String nombre, String apellido1,
			String apellido2, String direccion) {
		if (dni == null || nombre == null || apellido1 == null || direccion == null) {
			throw new IllegalArgumentException();
		}
		this.dni = dni;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.dire = direccion;
	}

	/**
	 * CCog: 0
	 * Desglose CCog: Flujo puramente secuencial.
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * CCog: 0
	 * Desglose CCog: Flujo puramente secuencial.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * CCog: 0
	 * Desglose CCog: Flujo puramente secuencial.
	 */
	public String getApellido1() {
		return apellido1;
	}

	/**
	 * CCog: 0
	 * Desglose CCog: Flujo puramente secuencial.
	 */
	public String apellido2() {
		return apellido2;
	}

	/**
	 * CCog: 0
	 * Desglose CCog: Flujo puramente secuencial.
	 */
	public String getDire() {
		return dire;
	}

	/**
	 * CCog: 6
	 * Desglose CCog:
	 * +1 (Estructura iterativa 'for' - Nivel de anidamiento 0. Rompe la linealidad base)
	 * +2 (Estructura de selección 'switch' - Nivel de anidamiento 1: +1 punto base por la estructura y +1 punto de penalización por encontrarse confinada dentro del bucle 'for')
	 * +2 (Estructura condicional 'if' - Nivel de anidamiento 1: +1 punto base por la estructura y +1 punto de penalización por anidamiento dentro del bucle 'for'. Académicamente, la estructura 'switch' previa no incrementa de manera acumulativa el nivel de profundidad de anidamiento para los bloques internos)
	 * +1 (Estructura alternativa 'else' - Suma fija de 1 punto por ruptura de linealidad. Por definición, los bloques asociativos 'else' no reciben penalizaciones adicionales por nivel de profundidad)
	 */
	public double sueldo() {
		double sueldoTransportes = 0;
		for (Transporte t : transportes) {
			double sueldoExtraTransporte = 0.0;
			switch (t.categoria()) {
				case Mercancias:
					sueldoExtraTransporte = t.ton() * 2;
					break;
				case MercanciasPeligrosas:
					sueldoExtraTransporte = t.ton() * 2 + 50;
					break;
				case Personas:
					if (t.getPersonas() < 10)
						sueldoExtraTransporte = t.horas() * 0.5;
					else
						sueldoExtraTransporte = t.horas();
					break;
			}
			sueldoTransportes += t.horas() * 5 + sueldoExtraTransporte;
		}
		return 700 + sueldoTransportes;
	}

	/**
	 * CCog: 0
	 * Desglose CCog: Flujo puramente secuencial.
	 */
	public void anhadeTransporte(Transporte t) {
		transportes.add(t);
	}

}