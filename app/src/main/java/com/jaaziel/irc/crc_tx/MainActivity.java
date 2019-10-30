package com.jaaziel.irc.crc_tx;

/*
 *
 * Autor: Jaaziel Isai Rebollar Calzada
 *
 */

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button transmitir, reiniciar;
    EditText mx, gx;
    TextView resultado;
    boolean tmx, tgx;
    char[] array, arraye;
    int rx;
    String m1, g1;
    private ArrayList <Integer> Mx, Gx, res, tmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultado = findViewById(R.id.resultado);
        mx = findViewById(R.id.enter_mx);
        gx = findViewById(R.id.enter_gx);
        transmitir = findViewById(R.id.transmitir);
        reiniciar = findViewById(R.id.reiniciar);
        Mx = new ArrayList <> (0);
        Gx = new ArrayList <> (0);
        res = new ArrayList <> (0);
        tmp = new ArrayList <> (0);

        onClickButton();
    }
    private void onClickButton(){
        reiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tmx=false; tgx=false;
                m1=""; g1="";
                mx.setText(""); gx.setText("");
                resultado.setText("");
                mx.requestFocus();
            }
        });
        //------------------------------------------------------------------------
        transmitir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //oculta el teclado si se queda visible
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(gx.getWindowToken(), 0);

                Gx.clear ( );
                res.clear ( );
                Mx.clear ( );
                tmx=false; tgx=false;

                m1 = mx.getText().toString();
                g1 = gx.getText().toString();

                verMx(m1);
                verGx(g1);

                if( tmx&&tgx ) {
                    proceso(m1, g1);
                }
            }
        });
    }
    private void verMx( String str ){
        Toast toast = Toast.makeText(MainActivity.this,"M(x):\nMínimo 8 bits, máximo 16", Toast.LENGTH_SHORT );
        Toast toaste = Toast.makeText(MainActivity.this,"M(x):\nIngresa un número binario válido", Toast.LENGTH_SHORT );

        arraye = str.toCharArray();

        if( arraye.length!=0 ){
            if( (arraye.length < 8)||(arraye.length > 16) ) {
                toast.show();
            } else {
                for (char c : arraye) {
                    if (!((c == '0') || (c == '1'))) {
                        toaste.show();
                        return;
                    }
                }
                tmx=true;
            }
        }
    }
    private void verGx( String st ){
        Toast toast = Toast.makeText(MainActivity.this,"G(x):\nMínimo 4 bits, máximo 8", Toast.LENGTH_SHORT );
        Toast toaste = Toast.makeText(MainActivity.this,"G(x):\nIngresa un número binario válido", Toast.LENGTH_SHORT );
        array = st.toCharArray();
        if( array.length!=0 ){
            if( array[0]=='0'||array[array.length-1]=='0' ){
                Toast u = Toast.makeText(MainActivity.this, "G(x):\nObligatorio número 1 al inicio y al final", Toast.LENGTH_SHORT);
                u.show();
                return;
            }
            if( (array.length < 4)||(array.length > 8) ) {
                toast.show();
            } else {
                for (char c : array) {
                    if (!((c == '0') || (c == '1'))) {
                        toaste.show();
                        return;
                    }
                }
                tgx=true;
            }
        }
    }
    //-------------------------------------------------------------------------
    private void proceso( String sm, String sg ) {
        Mx.clear();
        Gx.clear();
        for( int i=0; i<sm.length(); i++ ){
            if( sm.toCharArray ( )[i] == '1' ){
                Mx.add(1);
            } else {
                Mx.add(0);
            }
        }

        for( int i=0; i<sg.length(); i++ ){
            if( sg.toCharArray ()[i] == '1' ){
                Gx.add(1);
            } else {
                Gx.add(0);
            }
        }

        rx = Gx.size( );
        for (int i = 0; i < rx - 1; i++)
            Mx.add ( 0 );

        for (int i = 0; i < rx; i++) {
            if (Gx.get (i).equals(Mx.get (i))) res.add (0);
            else res.add (1);
        }

        for (int current = rx; current < Mx.size ( ); current++) {
            if (res.get(0) == 0) {
                res.remove (0);
                res.add ( Mx.get ( current ) );
                if ( current == (Mx.size ( )  - 1) && res.get (0) == 1 ) {
                    division ( );
                    break;
                }
            } else {
                division ( );
                current--;
            }
        }

        for (int i = 0; i < rx - 1; i++) {
            Mx.remove (Mx.size ( ) - 1);
        }

        for (int i = 1; i < res.size ( ); i++) {
            Mx.add ( res.get (i) );
        }
        resultado.setText("");
        String ls="";
        for (Integer s : Mx){
            ls += s+" ";
        }
        resultado.setText(ls);
    }

    private void division() {
        tmp.clear ( );
        for (int i = 0; i < res.size ( ); i++)
            tmp.add (res.get (i) );
        res.clear ( );
        for (int j = 0; j < tmp.size ( ); j++) {
            if ( Gx.get (j).equals(tmp.get (j) )) res.add (0);
            else  res.add (1);
        }
    }
}