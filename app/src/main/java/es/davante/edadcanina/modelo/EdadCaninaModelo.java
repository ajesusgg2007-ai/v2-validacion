package es.davante.edadcanina.modelo;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import es.davante.edadcanina.BR;

public class EdadCaninaModelo extends BaseObservable {
    public static final int FACTOR_CANINO = 7;
    public static final int EDAD_MINIMA = 1;
    public static final int EDAD_MAXIMA = 30;

    private String edadTexto = "";
    private int edadHumana;
    private Validacion validacion = Validacion.SIN_CALCULAR;

   @Bindable public String getEdadTexto()
    {
        return edadTexto;
    }
   @Bindable public int getEdadHumana()
    {
        return edadHumana;
    }
   @Bindable public Validacion getValidacion()
    {
        return validacion;
    }

   @Bindable public boolean isValidacionOk()
    {
        return validacion == Validacion.OK;
    }

    public void setEdadTexto(String nuevo)
    {
        if (nuevo == null)
        {
            nuevo = "";
        }
        if (nuevo.equals(edadTexto)) {
            return;
        }
        edadTexto = nuevo;
        notifyPropertyChanged(BR.edadTexto);
        validacion = Validacion.SIN_CALCULAR;
        notifyPropertyChanged(BR.validacion);
    }


    public Validacion calcular() {
        String texto = edadTexto.trim();
        if (texto.isEmpty()) {
            validacion = Validacion.VACIO;
            notifyChange();
            return validacion;
        }
        int edad;
        try {
            edad = Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            validacion = Validacion.NO_NUMERO;
            notifyChange();
            return validacion;
        }
        if (edad < EDAD_MINIMA || edad > EDAD_MAXIMA) {
            validacion = Validacion.FUERA_DE_RANGO;
            notifyChange();
            return validacion;
        }
        edadHumana = edad * FACTOR_CANINO;
        validacion = Validacion.OK;
        notifyChange();
        return validacion;
    }
}
