import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {

    /// entrada 1: E-> E+E | E*E | (E) | i
    public static void main(String[] args) {
        String entradaRegra = "E-> E+E | E*E | (E) | i";
        String entradaPalavra = "i + i * i";
        Automato automato = new Automato(entradaRegra, entradaPalavra);
        automato.start();



    }

}
