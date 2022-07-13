package com.jaaziel.irc.crc_tx.data;

public interface MainMVP {
    interface Model {
        boolean isMxValido(String mx);
        boolean isGxValido(String gx);
    }

    interface Presenter {
        void notifyErrorRangoBitsMx();
        void notifyErrorRangoBitsGx();
        void notifyErrorSoloBits();
        void notifyErrorBitsInicioFinalGx();
        void doDivision();
        void doProceso(String stringMx, String stringGx);
        void transmitir();
        void reiniciarVariables();
    }

    interface View {
        String getMx();
        String getGx();
        void reiniciarTodo();
        void ocultarTeclado();
        void errorRangoBitsMx();
        void errorRangoBitsGx();
        void errorSoloBits();
        void errorBitsInicioFinalGx();
        void mostrarResultado(String listResult);
    }
}
