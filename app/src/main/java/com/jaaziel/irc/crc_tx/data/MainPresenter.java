package com.jaaziel.irc.crc_tx.data;

import com.jaaziel.irc.crc_tx.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainPresenter implements MainMVP.Presenter {
    private final MainMVP.Model model;
    private final MainMVP.View view;
    private List<Integer> tmp, resultado, listMx, listGx;

    @Inject
    public MainPresenter(MainActivity view) {
        this.view = view;
        model = new MainModel(this);
    }

    @Override
    public void initLists() {
        tmp = new ArrayList<>();
        resultado = new ArrayList<>();
        listGx = new ArrayList<>();
        listMx = new ArrayList<>();
    }

    @Override
    public void notifyErrorRangoBitsMx() {
        view.errorRangoBitsMx();
    }

    @Override
    public void notifyErrorRangoBitsGx() {
        view.errorRangoBitsGx();
    }

    @Override
    public void notifyErrorSoloBits() {
        view.errorSoloBits();
    }

    @Override
    public void notifyErrorBitsInicioFinalGx() {
        view.errorBitsInicioFinalGx();
    }

    @Override
    public void doDivision() {
        tmp.clear();
        tmp.addAll(resultado);
        resultado.clear();

        for(int j = 0; j < tmp.size(); j++) {
            if( listGx.get(j).equals(tmp.get(j) )){
                resultado.add(0);
            } else {
                resultado.add(1);
            }
        }
    }

    @Override
    public void doProceso(String stringMx, String stringGx) {
        listGx.clear();
        listMx.clear();

        if(model.isMxValido(stringMx) && model.isGxValido(stringGx)){
            for( int i=0; i<stringGx.length(); i++ ){
                if( stringGx.toCharArray()[i] == '1' ){
                    listGx.add(1);
                } else {
                    listGx.add(0);
                }
            }

            for( int i=0; i<stringMx.length(); i++ ){
                if( stringMx.toCharArray ()[i] == '1' ){
                    listMx.add(1);
                } else {
                    listMx.add(0);
                }
            }

            int rx = listGx.size();

            for(int i = 0; i < rx - 1; i++) {
                listMx.add(0);
            }

            for(int i = 0; i < rx; i++) {
                if(listGx.get(i).equals( listMx.get(i)) ) {
                    resultado.add(0);
                } else {
                    resultado.add(1);
                }
            }

            for(int current = rx; current < listMx.size(); current++) {
                if(resultado.get(0) == 0) {
                    resultado.remove(0);
                    resultado.add( listMx.get(current) );
                    if( current == (listMx.size() - 1) && resultado.get(0) == 1 ) {
                        doDivision();
                        break;
                    }
                } else {
                    doDivision();
                    current--;
                }
            }

            for(int i = 0; i < rx - 1; i++) {
                listMx.remove(listMx.size() - 1);
            }

            for(int i = 1; i < resultado.size(); i++) {
                listMx.add( resultado.get(i) );
            }
            StringBuilder listResult = new StringBuilder();
            for(Integer s : listMx){
                listResult.append(s).append(" ");
            }
            view.mostrarResultado(listResult.toString());
        }
        tmp.clear();
        resultado.clear();
        listMx.clear();
        listGx.clear();
    }

    @Override
    public void reiniciarValores() {
        view.reiniciarTodo();
    }

    @Override
    public void transmitir() {
        view.ocultarTeclado();
        doProceso(view.getMx(), view.getGx());
    }
}
