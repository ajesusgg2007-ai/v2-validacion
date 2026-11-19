package es.davante.edadcanina;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import es.davante.edadcanina.controlador.EdadCaninaControlador;
import es.davante.edadcanina.databinding.ActivityMainBinding;
import es.davante.edadcanina.modelo.EdadCaninaModelo;
import es.davante.edadcanina.modelo.Validacion;

public class MainActivity extends AppCompatActivity {

    private static final int FACTOR_CANINO = 7;
    private static final int EDAD_MINIMA = 1;
    private static final int EDAD_MAXIMA = 30; // ningún perro supera esta edad

    private static final String CLAVE_TEXTO = "edad_texto";
    private static final String CLAVE_CALCULADO = "calculado";
    private EditText ageEdit;
    private TextView resultText;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        modelo = new EdadCaninaModelo();

        if (savedInstanceState != null) {
            modelo.setEdadTexto(savedInstanceState.getString(CLAVE_TEXTO, ""));
            if (savedInstanceState.getBoolean(CLAVE_CALCULADO, false)) {
                modelo.calcular();
            }
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setModelo(modelo);
        binding.setControlador(new EdadCaninaControlador(modelo));

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(CLAVE_TEXTO, modelo.getEdadTexto());
        outState.putBoolean(CLAVE_CALCULADO, modelo.getValidacion() !=
                Validacion.SIN_CALCULAR);
    }

    private EdadCaninaModelo modelo = new EdadCaninaModelo();


    }

