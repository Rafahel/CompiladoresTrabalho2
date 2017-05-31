import java.util.ArrayList;
import java.util.List;
/**
 * Created by Rafahel on 24/05/2017.
 */
public class Automato {
    String regraProduacao;
    String palavra;
    List<String> regrasDeProducao;
    List<String> fita;
    List<String> pilha;
    List<String> auxiliar;

    public Automato(String regraDeProducao, String palavra) {
        this.regraProduacao = regraDeProducao;
        this.palavra = palavra;
        this.regrasDeProducao = new ArrayList<>();
        this.fita = new ArrayList<>();
        this.pilha = new ArrayList<>();
        this.auxiliar = new ArrayList<>();

    }

    public void start(){
        palavra += " $";
        System.out.println(palavra);
        constroiFita();
        ajustaRegrasDeProducao();

        /*
        *
        * Looping principal que só termina quando a palavra é aceita ou rejeitada pelo automato.
        *
        */

        while(true){

        }
    }

    private void constroiFita(){
        /*
        * Faz a construção da fita adicionando todas as produções
        *
        */

        for (String simbolo: palavra.split(" ")){
            this.fita.add(simbolo.trim());
        }
    }

    private void ajustaRegrasDeProducao(){
        /*
        *
        * Cria uma lista com as regras de produção para fazer a comparação mais tarde.
        *
        * São removidos os espaços, inicio da regra de produção e separador "|"
        *
        * */
        for (String regra: regraProduacao.split(" ")) {
            regra = regra.trim();
            if (!regra.contains("->"))
                if (!regra.contains("|"))
                    regrasDeProducao.add(regra.trim());
        }
//        for (String regra: regrasDeProducao) {
//            System.out.print(regra + " ");
//        }
    }

    private void inicializaPilha(){
        pilha.add("$");
    }




}
