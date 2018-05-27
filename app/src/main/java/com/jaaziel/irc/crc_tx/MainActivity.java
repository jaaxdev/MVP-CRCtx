package com.jaaziel.irc.crc_tx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button transmitir, reiniciar;
    EditText mx, gx;
    TextView resultado;
    boolean tmx=false, tgx=false;
    char[] array, arraye;
    int rx;
    String m1, g1;
    private ArrayList <Integer> Mx = new ArrayList <> ( );
    private ArrayList <Integer> Gx = new ArrayList <> ( );
    private ArrayList <Integer> res = new ArrayList <> ( );
    private ArrayList <Integer> tmp = new ArrayList <> ( );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultado = (TextView)findViewById(R.id.resultado);
        mx = (EditText)findViewById(R.id.enter_mx);
        gx = (EditText)findViewById(R.id.enter_gx);
        transmitir = (Button)findViewById(R.id.transmitir);
        reiniciar = (Button)findViewById(R.id.reiniciar);

        reiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tmx=false; tgx=false;
                m1=""; g1="";
                mx.setText(""); gx.setText("");
                resultado.setText("");
            }
        });
        //------------------------------------------------------------------------
        transmitir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
    public void verMx( String str ){
        Toast toast = Toast.makeText(MainActivity.this,"M(x):\nMínimo 8 bits, máximo 16", Toast.LENGTH_SHORT );
        Toast toaste = Toast.makeText(MainActivity.this,"M(x):\nIngresa un número binario válido", Toast.LENGTH_SHORT );

        arraye = str.toCharArray();

        if( arraye.length!=0 ){
            if( (arraye.length < 8)||(arraye.length > 16) ) {
                toast.show();
                return;
            } else {
                for( int i=0; i<arraye.length; i++ ) {
                    if ( !((arraye[i] == '0') || (arraye[i] == '1')) ) {
                        toaste.show ( );
                        return;
                    }
                }
                tmx=true;
            }
        }
    }
    public void verGx( String st ){
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
                return;
            } else {
                for( int i=0; i<array.length; i++ ) {
                    if( !((array[i] == '0') || (array[i] == '1')) ) {
                        toaste.show ( );
                        return;
                    }
                }
                tgx=true;
            }
        }
    }
    //-------------------------------------------------------------------------
    public void proceso( String sm, String sg ) {
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
            if (Gx.get (i) == Mx.get (i)) res.add (0);
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

    public void division() {
        tmp.clear ( );
        for (int i = 0; i < res.size ( ); i++)
            tmp.add (res.get (i) );
        res.clear ( );
        for (int j = 0; j < tmp.size ( ); j++) {
            if ( Gx.get (j) == tmp.get (j) ) res.add (0);
            else  res.add (1);
        }
    }
}