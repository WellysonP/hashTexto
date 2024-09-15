import java.io.*;
import java.util.*;

public class IndiceRemissivo {
    public static void main(String[] args) {
        String arquivoPalavrasChave = "palavrasChave.txt";
        String arquivoTexto = "texto.txt";
        Map<String, Set<Integer>> indiceRemissivo = new TreeMap<>();

        try {
            BufferedReader brPalavrasChave = new BufferedReader(new FileReader(arquivoPalavrasChave));
            String linhaPalavraChave;
            while ((linhaPalavraChave = brPalavrasChave.readLine()) != null) {
                String[] palavrasChave = linhaPalavraChave.toLowerCase().split(",\\s*");
                for (String palavra : palavrasChave) {
                    indiceRemissivo.put(palavra.trim(), new TreeSet<>());
                }
            }
            brPalavrasChave.close();            BufferedReader brTexto = new BufferedReader(new FileReader(arquivoTexto));
            String linhaTexto;
            int numeroLinha = 1;

            while ((linhaTexto = brTexto.readLine()) != null) {
                String[] palavras = linhaTexto.split("[\\s,.;!?]+");
                for (String palavra : palavras) {
                    String palavraLower = palavra.toLowerCase();
                    if (indiceRemissivo.containsKey(palavraLower)) {
                        indiceRemissivo.get(palavraLower).add(numeroLinha);
                    }
                }
                numeroLinha++;
            }
            brTexto.close();

            BufferedWriter bw = new BufferedWriter(new FileWriter("resposta.txt"));
            for (Map.Entry<String, Set<Integer>> entry : indiceRemissivo.entrySet()) {
                bw.write(entry.getKey());
                for (Integer linha : entry.getValue()) {
                    bw.write(" " + linha);
                }
                bw.newLine();
            }
            bw.close();

            System.out.println("Índice remissivo criado com sucesso em 'resposta.txt'.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
