package es.davante.edadcanina;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import es.davante.edadcanina.databinding.ActivityMainBinding;

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

    private void calcularEdad() {
        // 1. Leer el texto sin espacios
        String texto = binding.ageInput.getText().toString().trim();

        // 2. ¿Vacío?
        if (texto.isEmpty()) {
            mostrarError(getString(R.string.error_vacio));
            return;
        }

        // 3. Convertir a int con try/catch
        int age;
        try {
            age = Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            mostrarError(getString(R.string.error_no_numero));
            return;
        }

        // 4. ¿En rango?
        if (age < EDAD_MINIMA || age > EDAD_MAXIMA) {
            mostrarError(getString(R.string.error_rango, EDAD_MINIMA, EDAD_MAXIMA));
            return;
        }

        // 5. Todo correcto
        binding.ageInput.setError(null);
        int edadHumana = age * FACTOR_CANINO;
        binding.resultText.setText(getString(R.string.tu_edad_format, edadHumana));
    }

    private void mostrarError(String mensaje) {
        binding.ageInput.setError(mensaje);
        binding.ageInput.requestFocus();
        binding.resultText.setText("");
    }

    }

