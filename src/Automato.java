import java.util.ArrayList;
import java.util.List;

public class Automato {
    private String regraProduacao;
    private String palavra;
    private String nTerminal;
    private List<String> regrasDeProducao;
    private List<String> fita;
    private List<String> pilha;
    private List<String> auxiliar;
    boolean stop;
    public Automato(String regraDeProducao, String palavra) {
        this.regraProduacao = regraDeProducao;
        this.palavra = palavra;
        this.regrasDeProducao = new ArrayList<>();
        this.fita = new ArrayList<>();
        this.pilha = new ArrayList<>();
        this.auxiliar = new ArrayList<>();
        this.stop = false;
    }

    public void start(){
        this.palavra += " $";
        System.out.println(palavra);
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
            if (this.pilha.get(0) == "$" && this.fita.get(0) == "$"){
                aceita();
                break;
            }
            else if (this.pilha.get(0) == "$" && this.fita.get(0) != "$"){
                recusa();
                break;
            }
            if (this.pilha.get(0) != this.nTerminal && this.fita.get(0) != nTerminal){
                reduz();
            }

            if (!this.pilha.get(0).equals("$")){
                reduz();
                empilha();
            }




        }

    }


    private void reduz(){
//        System.out.println("REDUZ");
        String aux = "";
        boolean encontrado = false;
        for (String s: this.pilha) {
            if (!s.equals("$")){
                aux = s.trim() + aux;
//                aux = new StringBuffer(aux).reverse().toString();
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
                        for (int i = 0; i < aux.length() -1 ; i++) {
//                            System.out.println("REM : " + pilha.get(0));
                            pilha.remove(pilha.remove(0));
                        }
                    }
                    else
                        this.pilha.remove(0);
                    this.pilha.add(0, this.nTerminal);
//                    System.out.println(" ------------------------------------------------------------------------------------ AUX substituido por: " + nTerminal);
                    encontrado = true;
                    break;
                }
            }
            if (encontrado)
                break;


        }
//        System.out.println("PILHA APÓS REDUÇAO: ");
//        for (String p : pilha)
//            System.out.println(p);
    }

    private void empilha(){
//        System.out.println("EMPILHANDO: " + fita.get(0));
        if (this.fita.get(0).equals("$") && this.pilha.get(0).equals(this.nTerminal))
            aceita();
        else
            recusa();
        this.pilha.add(0, this.fita.get(0));
        this.fita.remove(0);

    }

    private void empilhaInicial(){
//        System.out.println("EMPILHA -> " +  this.fita.get(0));
        this.pilha.add(0, this.fita.get(0));
        this.fita.remove(0);
    }

    private void aceita(){
        System.out.println("ACEITA");
        this.stop = true;
    }
    private void recusa(){
        System.out.println("RECUSA");
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
//        for (String regra: regrasDeProducao) {
//            System.out.print(regra + " ");
//        }
    }

    private void inicializaPilha(){
        this.pilha.add("$");
    }



    private void encontraTerminal(){
        this.nTerminal = this.regraProduacao.split(" ")[0];
        this.nTerminal = this.nTerminal.replace("->", "");
        this.nTerminal= this.nTerminal.trim();
//        System.out.println(this.nTerminal);
    }




}
