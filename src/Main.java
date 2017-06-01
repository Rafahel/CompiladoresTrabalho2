import java.util.List;

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
        List<String> linhas = Leitor.lerArquivo();
        String entradaRegra = linhas.get(0);
        linhas.remove(0);
        for (int i = 0; i < linhas.size() ; i++) {
            String entradaPalavra = linhas.get(i);
            System.out.println("INICIANDO TESTE PARA A ENTRADA: " + linhas.get(i));
            Automato automato = new Automato(entradaRegra, entradaPalavra);
            automato.start();
        }
    }
}

