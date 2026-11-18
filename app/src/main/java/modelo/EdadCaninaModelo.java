package modelo;

public class EdadCaninaModelo {
    public static final int FACTOR_CANINO = 7;
    public static final int EDAD_MINIMA = 1;
    public static final int EDAD_MAXIMA = 30;

    private String edadTexto = "";
    private int edadHumana;
    private Validacion validacion = Validacion.SIN_CALCULAR;

    public String getEdadTexto()
    {
        return edadTexto;
    }
    public int getEdadHumana()
    {
        return edadHumana;
    }
    public Validacion getValidacion()
    {
        return validacion;
    }

    public void setEdadTexto(String nuevo)
    {
        if (nuevo == null) nuevo = "";
        if (nuevo.equals(edadTexto)) return;
        edadTexto = nuevo;

        validacion = Validacion.SIN_CALCULAR;
    }


    public Validacion calcular() {
        String texto = edadTexto.trim();
        if (texto.isEmpty()) { validacion = Validacion.VACIO; return validacion; }
        int edad;
        try {
            edad = Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            validacion = Validacion.NO_NUMERO; return validacion;
        }
        if (edad < EDAD_MINIMA || edad > EDAD_MAXIMA) {
            validacion = Validacion.FUERA_DE_RANGO; return validacion;
        }
        edadHumana = edad * FACTOR_CANINO;
        validacion = Validacion.OK;
        return validacion;
    }
}
