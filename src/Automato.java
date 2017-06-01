import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class Automato {
    private String regraProduacao;
    private String palavra;
    private String nTerminal;
    private List<String> regrasDeProducao;
    private List<String> fita;
    private List<String> pilha;
    private boolean stop;
    private int posicaoFita;
    public Automato(String regraDeProducao, String palavra) {
        this.regraProduacao = regraDeProducao;
        this.palavra = palavra;
        this.regrasDeProducao = new ArrayList<>();
        this.fita = new ArrayList<>();
        this.pilha = new ArrayList<>();
        this.stop = false;
        this.posicaoFita = 0;
    }

    public void start(){
        this.palavra += " $";
//        System.out.println(palavra);
        constroiFita();
        ajustaRegrasDeProducao();
        inicializaPilha();
        encontraTerminal();

        /*
        *
        * Looping principal que só termina quando a palavra é aceita ou rejeitada pelo automato.
        *
        */
        boolean firstRun = true;

        while(true){
            if (this.stop){
                break;
            }
            if (firstRun){
                empilhaInicial();
                firstRun = false;
                continue;
            }

//            System.out.println("PILHA EM 0 : " + pilha.get(0));
            if (this.pilha.get(0) == "$" && this.fita.get(posicaoFita) == "$"){
                aceita();
                break;
            }
            else if (this.pilha.get(0) == "$" && this.fita.get(posicaoFita) != "$"){
                recusa();
                break;
            }
            if (this.pilha.get(0) != this.nTerminal && this.fita.get(posicaoFita) != nTerminal){
                reduz();
            }

            if (!this.pilha.get(0).equals("$")){
                reduz();
                empilha();
            }

        }

    }

    private void reduz(){
        String aux = "";
        boolean encontrado = false;
        for (String s: this.pilha) {
            if (!s.equals("$")){
                aux = s.trim() + aux;
            }
//            System.out.println("sAUX = " + aux);
            for (String regra: this.regrasDeProducao) {
//                System.out.println("Comparando: " + aux + " com regra de producao: " + regra);
                if (regra.equals(aux)){
//                    System.out.println("encontrado");
                    if (aux.length() > 1){
                        /*
                        * Se a string aux que é onde desempinhamos a pilha tiver mais de um caracter e para ele for encontrada uma regra
                        * então deve desempilhar todos os caracteres que estão contidos nessa String.
                        *
                        * Caso contrario desempilhamos apenas o topo
                        * */
//                        System.out.println("LENGTH AUX: " + aux.length());
                        for (int i = 0; i < aux.length()  ; i++) {
//                            System.out.println("REMOVE : " + pilha.get(0));
                            pilha.remove(0);
                        }
                    }
                    else
                        this.pilha.remove(0);
                    this.pilha.add(0, this.nTerminal);
                    System.out.println("        REDUZINDO: " + regra + " para " + this.nTerminal);
                    encontrado = true;
                    break;
                }
            }
            if (encontrado){
                mostraRelacaoPilhaFita();
                break;
            }
        }
    }

    private void mostraRelacaoPilhaFita(){
        String pl = "";
        String ft = "";
        Formatter fmt = new Formatter();
        fmt.format("%s   %30s\n", "PILHA", "FILA");
        for (String p : this.pilha)
            pl += p + " ";
        for (int i = this.posicaoFita; i <= this.fita.size() - 1 ; i++) {
            ft += this.fita.get(i) + " ";
        }
        pl = new StringBuffer(pl).reverse().toString();
        fmt.format("%s   %30s\n", pl, ft);
        System.out.println(fmt);

    }

    private void finaliza(){
        pilha.remove(0);
        if (pilha.get(0).equals("$"))
            aceita();
        else
            recusa();
    }

    private void empilha(){
        System.out.println("              EMPILHANDO: " + fita.get(posicaoFita));
        if (this.fita.get(posicaoFita).equals("$")){
            finaliza();
        }
        this.pilha.add(0, this.fita.get(posicaoFita));
        this.posicaoFita++;
    }

    private void empilhaInicial(){
        this.pilha.add(0, this.fita.get(posicaoFita));
        this.posicaoFita++;
    }

    private void aceita(){
        System.out.println("PALAVRA ACEITA\n---------------------------------------------------------\n---------------------------------------------------------\n\n");
        this.stop = true;
    }
    private void recusa(){
        System.out.println("PALAVRA RECUSADA\n---------------------------------------------------------\n---------------------------------------------------------\n\n");
        this.stop = true;
    }

    private void constroiFita(){
        /*
        * Faz a construção da fita adicionando todas as produções
        *
        */

        for (String simbolo: this.palavra.split(" ")){
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
        for (String regra: this.regraProduacao.split(" ")) {
            regra = regra.trim();
            if (!regra.contains("->"))
                if (!regra.contains("|"))
                    this.regrasDeProducao.add(regra.trim());
        }
    }

    private void inicializaPilha(){
        this.pilha.add("$");
    }


    private void encontraTerminal(){
        this.nTerminal = this.regraProduacao.split(" ")[0];
        this.nTerminal = this.nTerminal.replace("->", "");
        this.nTerminal= this.nTerminal.trim();
    }

}
