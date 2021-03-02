package com.example.doviakovskis_percentcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText inputText;
    TextView procentai;
    SeekBar seekBar;
    TextView tips;
    TextView total;
    TextView Result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.inputText);
        procentai = findViewById(R.id.procentai);
        seekBar = findViewById(R.id.seekbar);
        tips = findViewById(R.id.tips);
        total = findViewById(R.id.total);
        Result = findViewById(R.id.Result);

        // procentu juostos funkcionalumas: kesiciasiprocentu skaicius jei juostos elementas stumdomas
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int proc = progress;
                procentai.setText(String.valueOf(proc) + "%");
                calculate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // padaro taip kad gali ivesti teksta, siuncia duomenis i funkcija calculate()
        inputText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                calculate();
            }
        });

        //rezultato isvados paspaudus migtuka result // veikia tik paskutinis :)
        Result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Result.setText("Įvesta suma: " + inputText.getText());
                Result.setText("Pritaikyta nuolaida: " + tips.getText());
                Result.setText("Suma su nuolaida: " + total.getText());
            }
        });

    }

    // skaiciavimo funkcija
    private void calculate() {
        if (inputText.length() == 0){ // jeigu ivesties ilgis ligus nului
            inputText.requestFocus(); // vyksta patikra ar is tikru ligu 0
            inputText.setError("Enter amount again"); // jei taip praso vel ivesti skaiciu
        }else {
            double apimtis = Double.parseDouble(inputText.getText().toString()); // ivedamas skaicius paverciamas israiska
            int procent = seekBar.getProgress(); // tikrinama kiek buvo pastumta procentu juosta
            double tipsai = (apimtis * procent) / 100.0; // apskaiciuojama nuolaida
            double tot = apimtis - tipsai; // apskaiciuojama galutine kaina
            tips.setText(String.valueOf(tipsai));
            total.setText(String.valueOf(tot));
        }
    }
}