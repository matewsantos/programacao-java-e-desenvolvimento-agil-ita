import mecanica.FabricaMecanicaDoJogo;
import mecanica.MecanicaDoJogo;

import java.io.IOException;
import java.util.*;

public class Principal {
    public static void main(String[] args) throws IOException {

        boolean novoJogo = true;
        while(novoJogo) {
            FabricaMecanicaDoJogo fabricaMecanicaDoJogo = new FabricaMecanicaDoJogo();
            MecanicaDoJogo mecanicaDoJogo = fabricaMecanicaDoJogo.getMecanicaDoJogoAleatorio();
            System.out.println(mecanicaDoJogo.descricao());

            Scanner sc = new Scanner(System.in);

            while (!mecanicaDoJogo.acabou()) {
                System.out.println("Adivinhe a palavra desembaralhando as letras: " + mecanicaDoJogo.sorteiaPalavra());
                System.out.println("Tente acertar a palavra digitando-a abaixo:");

                String chute = sc.nextLine();
                String descricaoJogada = mecanicaDoJogo.chutar(chute);
                System.out.println(descricaoJogada);
            }

            System.out.println("Você conseguiu " + mecanicaDoJogo.getPontuacao() + " pontos.");
            System.out.println("Gostaria de jogar um novo jogo? Digite 's' para sim, ou qualquer outro caracter para sair.\n");

            novoJogo = sc.nextLine().equals("s");
            System.out.println();
        }
    }
}
