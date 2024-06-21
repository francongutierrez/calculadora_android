package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etNumber1, etNumber2;
    private Spinner spinnerOperations;
    private Button btnCalculate;
    private TextView tvResult;
    private String selectedOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumber1 = findViewById(R.id.etNumber1);
        etNumber2 = findViewById(R.id.etNumber2);
        spinnerOperations = findViewById(R.id.spinnerOperations);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.operations_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOperations.setAdapter(adapter);

        spinnerOperations.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedOperation = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedOperation = "Suma";
            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResult();
            }
        });
    }

    private void calculateResult() {
        String num1Str = etNumber1.getText().toString();
        String num2Str = etNumber2.getText().toString();

        if (num1Str.isEmpty() || num2Str.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese ambos números", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double num1 = Double.parseDouble(num1Str);
            double num2 = Double.parseDouble(num2Str);
            double result = 0;

            switch (selectedOperation) {
                case "Suma":
                    result = num1 + num2;
                    break;
                case "Resta":
                    result = num1 - num2;
                    break;
                case "Multiplicación":
                    result = num1 * num2;
                    break;
                case "División":
                    if (num2 == 0) {
                        Toast.makeText(this, "No se puede dividir por cero", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    result = num1 / num2;
                    break;
                case "Módulo":
                    if (num2 == 0) {
                        Toast.makeText(this, "No se puede dividir por cero", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    result = num1 % num2;
                    break;
            }

            tvResult.setText("Resultado: " + result);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Por favor ingrese números válidos", Toast.LENGTH_SHORT).show();
        }
    }
}