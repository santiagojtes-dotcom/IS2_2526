package es.unican.is2;

import java.time.LocalDate;

public class Seguro {

    private long id;
    private String matricula;
    private int potencia;
    private Cobertura cobertura;
    private LocalDate fechaInicio;
    private String conductorAdicional;

    public Seguro() {}

    public Seguro(String matricula, Cobertura cobertura, LocalDate fechaInicio, int potencia) {
        this.matricula = matricula;
        this.cobertura = cobertura;
        this.fechaInicio = fechaInicio;
        this.potencia = potencia;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    public Cobertura getCobertura() { return cobertura; }
    public void setCobertura(Cobertura cobertura) { this.cobertura = cobertura; }

    public int getPotencia() { return potencia; }
    public void setPotencia(int potencia) { this.potencia = potencia; }

    public String getConductorAdicional() { return conductorAdicional; }
    public void setConductorAdicional(String conductorAdicional) { this.conductorAdicional = conductorAdicional; }

    public double precio() {
        double precioBase = 0;
        switch (cobertura) {
            case TODO_RIESGO: precioBase = 600; break;
            case TERCEROS: precioBase = 400; break;
            case TERCEROS_LUNAS: precioBase = 450; break;
        }
        if (potencia > 90 && potencia <= 110) precioBase *= 1.05;
        else if (potencia > 110) precioBase *= 1.08;
        if (fechaInicio.isBefore(LocalDate.now().minusMonths(1))) precioBase *= 0.9;
        if (fechaInicio.isAfter(LocalDate.now())) return 0;
        return precioBase;
    }
}