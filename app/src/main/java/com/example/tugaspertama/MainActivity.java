package com.example.tugaspertama;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    EditText edtPanjang, edtLebar, edtTinggi;
    Button btnHitung;
    TextView tvHasil;

    private static final String STATE_RESULT = "state_result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtPanjang = findViewById(R.id.edt_panjang);
        edtLebar = findViewById(R.id.edt_lebar);
        edtTinggi = findViewById(R.id.edt_tinggi);
        btnHitung = findViewById(R.id.btn_hitung);
        tvHasil = findViewById(R.id.tv_hasil);

        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btn_hitung) {
                    String inputPanjang = edtPanjang.getText().toString().trim();
                    String inputLebar = edtLebar.getText().toString().trim();
                    String inputTinggi = edtTinggi.getText().toString().trim();

                    boolean isEmptyFields = false;
                    boolean isInvalidDouble = false;

                    if (TextUtils.isEmpty(inputPanjang)) {
                        isEmptyFields = true;
                        edtPanjang.setError("Kolom ini harus diisi");
                    }

                    if (TextUtils.isEmpty(inputLebar)) {
                        isEmptyFields = true;
                        edtLebar.setError("Kolom ini harus diisi");
                    }

                    if (TextUtils.isEmpty(inputTinggi)) {
                        isEmptyFields = true;
                        edtTinggi.setError("Kolom ini harus diisi");
                    }


                    Double panjang = toDouble(inputPanjang);
                    Double lebar = toDouble(inputLebar);
                    Double tinggi = toDouble(inputTinggi);

                    if (panjang == null) {
                        isInvalidDouble = true;
                        edtPanjang.setError("Kolom ini harus berupa angka");
                    }

                    if (lebar == null) {
                        isInvalidDouble = true;
                        edtLebar.setError("Kolom ini harus berupa angka");
                    }

                    if (tinggi == null) {
                        isInvalidDouble = true;
                        edtTinggi.setError("Kolom ini harus berupa angka");
                    }

                    if (!isEmptyFields && !isInvalidDouble) {

                        double total = panjang * lebar * tinggi;

                        tvHasil.setText(String.valueOf(total));
                    }

                }
            }

            private Double toDouble(String str) {
                try {
                    return Double.valueOf(str);
                } catch (NumberFormatException e) {
                    return  null;
                }
            }
        });


        if (savedInstanceState != null) {
            String hasil = savedInstanceState.getString(STATE_RESULT);
            tvHasil.setText(hasil);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, tvHasil.getText().toString());
    }
}
