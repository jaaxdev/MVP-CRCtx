package com.jaaziel.irc.crc_tx.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.jaaziel.irc.crc_tx.data.MainMVP;
import com.jaaziel.irc.crc_tx.data.MainPresenter;
import com.jaaziel.irc.crc_tx.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements MainMVP.View {

    ActivityMainBinding binding;
    MainMVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new MainPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.btnTransmitir.setOnClickListener(view -> presenter.transmitir());
        binding.btnReiniciar.setOnClickListener(view -> presenter.reiniciarVariables());
    }

    @Override
    public String getMx() {
        return binding.etBitsMx.getText().toString();
    }

    @Override
    public String getGx() {
        return binding.etBitsGx.getText().toString();
    }

    @Override
    public void reiniciarTodo() {
        binding.etBitsMx.setText("");
        binding.etBitsGx.setText("");
        binding.tvMessageTx.setText("");
        binding.tvResultado.setText("");
    }

    @Override
    public void ocultarTeclado() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(binding.etBitsMx.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(binding.etBitsGx.getWindowToken(), 0);
    }

    @Override
    public void errorRangoBitsMx() {
        Toast.makeText(MainActivity.this,"M(x):\nMínimo 8 bits, máximo 16", Toast.LENGTH_SHORT ).show();
        binding.etBitsMx.setError("8 a 16 bits");
    }

    @Override
    public void errorRangoBitsGx() {
        Toast.makeText(this,"G(x):\nMínimo 4 bits, máximo 8", Toast.LENGTH_SHORT ).show();
        binding.etBitsGx.setError("4 a 8 bits");
    }

    @Override
    public void errorSoloBits() {
        Toast.makeText(MainActivity.this,"Ingresa un número binario válido", Toast.LENGTH_SHORT ).show();
    }

    @Override
    public void errorBitsInicioFinalGx() {
        Toast.makeText(this, "G(x):\nObligatorio número 1 al inicio y al final", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mostrarResultado(String listResult) {
        binding.tvResultado.setText("");
        binding.tvResultado.setText(listResult);
    }
}