package es.davante.edadcanina.vista;

import android.content.Context;
import android.widget.EditText;

import androidx.databinding.BindingAdapter;

import es.davante.edadcanina.R;
import es.davante.edadcanina.modelo.EdadCaninaModelo;
import es.davante.edadcanina.modelo.Validacion;

public class AdaptadoresVista {
    @BindingAdapter("errorValidacion")
    public static void ponerError(EditText campo, Validacion validacion) {
        Context c=campo.getContext();
        if (validacion == null)
        {
            validacion=Validacion.SIN_CALCULAR;
        }

        switch (validacion)
        {
            case  VACIO:
                campo.setError(c.getString(R.string.error_vacio));
                campo.requestFocus(); break;
            case NO_NUMERO:
                campo.setError(c.getString(R.string.error_no_numero));
                campo.requestFocus(); break;
            case FUERA_DE_RANGO:
                campo.setError(c.getString(R.string.error_rango,
                        EdadCaninaModelo.EDAD_MINIMA,
                        EdadCaninaModelo.EDAD_MAXIMA));
                campo.requestFocus(); break;
            default:
                campo.setError(null); // sin error
        }
    }

}

