package com.jaaziel.irc.crc_tx.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.jaaziel.irc.crc_tx.R;
import com.jaaziel.irc.crc_tx.data.MainMVP;
import com.jaaziel.irc.crc_tx.databinding.ActivityMainBinding;

import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements MainMVP.View {

    ActivityMainBinding binding;

    @Inject
    MainMVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.initLists();
        binding.btnTransmitir.setOnClickListener(view -> presenter.transmitir());
        binding.btnReiniciar.setOnClickListener(view -> presenter.reiniciarValores());
    }

    @Override
    public String getMx() {
        return Objects.requireNonNull(binding.etBitsMx.getText()).toString();
    }

    @Override
    public String getGx() {
        return Objects.requireNonNull(binding.etBitsGx.getText()).toString();
    }

    @Override
    public void reiniciarTodo() {
        binding.etBitsMx.setText("");
        binding.etBitsGx.setText("");
        binding.tvResultado.setText(this.getString(R.string.resultado));
    }

    @Override
    public void ocultarTeclado() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(binding.etBitsMx.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(binding.etBitsGx.getWindowToken(), 0);
    }

    @Override
    public void errorRangoBitsMx() {
        binding.etBitsMx.setError("8 a 16 bits");
    }

    @Override
    public void errorRangoBitsGx() {
        binding.etBitsGx.setError("4 a 8 bits");
    }

    @Override
    public void errorSoloBits() {
        Toast.makeText(this,"Ingresa un número binario válido", Toast.LENGTH_SHORT ).show();
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