import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafahel on 01/06/2017.
 */
public class Leitor {


    public static List<String> lerArquivo(){
        List<String> linhas = new ArrayList<>();
        String caminho = System.getProperty("user.dir") + "\\src\\entrada.txt".replaceAll("\\\\", "/");
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linhas.add(linha.trim());
//                System.out.println(linha.trim());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return linhas;


    }

}