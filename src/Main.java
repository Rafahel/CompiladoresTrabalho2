import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {
    /*
    *
    *
    * Escolher a entradeRegra (deve ser separarado de uma regra para outra por espaço)
    *
    * Ao entrar com a palavra ela deve ser também toda separada por espaços.
    *
    *
    */
    public static void main(String[] args) {
        String entradaRegra = "E-> E+E | E*E | (E) | i";
//        String entradaPalavra = "i + i * i";
//        String entradaPalavra = "( i + i ) +";
//        String entradaPalavra = "( i + i ) * i";
//        String entradaPalavra = " i + i )";
        String entradaPalavra = "i * ( ( i + i ) * i )";
//        String entradaPalavra = "( i + i )";
        Automato automato = new Automato(entradaRegra, entradaPalavra);
        automato.start();
    }
}

