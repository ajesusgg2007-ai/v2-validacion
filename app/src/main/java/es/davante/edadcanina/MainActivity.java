package es.davante.edadcanina;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import es.davante.edadcanina.databinding.ActivityMainBinding;
import modelo.EdadCaninaModelo;

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

        binding.btnCalc.setOnClickListener(view -> calcularEdad());
    }

    private final EdadCaninaModelo modelo = new EdadCaninaModelo();

    private void calcularEdad() {
      modelo.setEdadTexto(binding.ageInput.getText().toString());

      switch (modelo.calcular()) {
          case VACIO:
              mostrarError(getString(R.string.error_vacio));
              break;
          case NO_NUMERO:
              mostrarError(getString(R.string.error_no_numero));
              break;
          case FUERA_DE_RANGO:
              mostrarError(getString(R.string.error_rango, EDAD_MINIMA, EDAD_MAXIMA));
              break;
          case OK:
              binding.ageInput.setError(null);

           binding.resultText.setText(getString(R.string.tu_edad_format, modelo.getEdadHumana()));
           break;
          default:
              break;
      }
    }

    private void mostrarError(String mensaje) {
        binding.ageInput.setError(mensaje);
        binding.ageInput.requestFocus();
        binding.resultText.setText("");
    }

    }

