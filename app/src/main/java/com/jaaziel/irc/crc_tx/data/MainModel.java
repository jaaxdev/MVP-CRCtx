package com.jaaziel.irc.crc_tx.data;

import android.util.Log;

public class MainModel implements MainMVP.Model {
    private final MainMVP.Presenter presenter;

    public MainModel(MainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public boolean isMxValido(String mx) {
        char[] arrayMx = mx.toCharArray();
        Log.i( "CRC-", ""+arrayMx.length );
        if( arrayMx.length != 0 ){
            if( (arrayMx.length < 8) || (arrayMx.length > 16) ) {
                presenter.notifyErrorRangoBitsMx();
                return false;
            } else {
                for(char c : arrayMx) {
                    if(!((c == '0') || (c == '1'))) {
                        presenter.notifyErrorSoloBits();
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isGxValido(String gx) {
        char[] arrayGx = gx.toCharArray();
        if( arrayGx.length!=0 ){
            if( arrayGx[0]=='0' || arrayGx[arrayGx.length-1]=='0' ){
                presenter.notifyErrorBitsInicioFinalGx();
                return false;
            }
            if( (arrayGx.length < 4) || (arrayGx.length > 8) ) {
                presenter.notifyErrorRangoBitsGx();
                return false;
            } else {
                for(char c : arrayGx) {
                    if(!((c == '0') || (c == '1'))) {
                        presenter.notifyErrorSoloBits();
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
}
