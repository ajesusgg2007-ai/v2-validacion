package es.davante.edadcanina;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import es.davante.edadcanina.databinding.ActivityMainBinding;
import es.davante.edadcanina.modelo.EdadCaninaModelo;

public class MainActivity extends AppCompatActivity {

    private static final int FACTOR_CANINO = 7;
    private static final int EDAD_MINIMA = 1;
    private static final int EDAD_MAXIMA = 30; // ningún perro supera esta edad

    private EditText ageEdit;
    private TextView resultText;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // DataBindingUtil infla el layout y crea el binding en un solo paso,
        // por eso ya no hace falta setContentView ni findViewById
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.setModelo(modelo);


        binding.btnCalc.setOnClickListener(view -> {
            switch (modelo.calcular()){
                case VACIO:          binding.ageInput.setError(getString(R.string.error_vacio)); break;
                case NO_NUMERO:      binding.ageInput.setError(getString(R.string.error_no_numero)); break;
                case FUERA_DE_RANGO: binding.ageInput.setError(getString(R.string.error_rango,
                        EdadCaninaModelo.EDAD_MINIMA, EdadCaninaModelo.EDAD_MAXIMA)); break;
                default:             binding.ageInput.setError(null);
            }
        });
    }

    private final EdadCaninaModelo modelo = new EdadCaninaModelo();


    }

