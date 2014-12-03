package models;

/**
 * Created by Marcos on 03/12/2014.
 */
public enum Prioridade {
    BAIXA(1), MEDIA(2), ALTA(3);

    private final int valor;
    Prioridade(int valorOpcao){
        valor = valorOpcao;
    }
    public int getValor(){
        return valor;
    }
}