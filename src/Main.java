import java.util.List;

public class Main {
    /*
    *
    *                           COMO UTILIZAR
    *  Dentro da pasta src existe um arquivo chamado entrada.txt nele temos as regras de produção e as entradas que
    *  devem ser testadas, para modificar a entrada apenas siga as regras abaixo.
    *
    *  1) Primeira linha deve conter a regra de produção em UMA linha ex: "E-> E+E | E*E | (E) | i";
    *  2) Cada parte da regra deve ser separada por um "|";
    *  3) Cada regra deve estar separada com espaços assim como no exemplo;
    *  4) Na segunda linha até a ultima devem conter as entradas que o teste devera ser realizado;
    *  5) Cada caracter deste teste deve ser separado por espaço (a não ser que a regra produza dois caracteres grudados)
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

