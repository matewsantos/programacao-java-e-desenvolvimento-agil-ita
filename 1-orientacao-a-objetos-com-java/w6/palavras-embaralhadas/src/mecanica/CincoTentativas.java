package mecanica;

import banco.BancoDePalavras;
import mecanica.embaralhador.FabricaEmbaralhadores;

public class CincoTentativas extends MecanicaDoJogo {
    private String palavraSorteada;
    private int tentativas;

    public CincoTentativas(FabricaEmbaralhadores fabricaEmbaralhadores, BancoDePalavras bancoDePalavras) {
        super(fabricaEmbaralhadores, bancoDePalavras);
    }

    @Override
    public String descricao() {
        return "Jogador possui 5 vidas. Cada acerto dá 2 pontos.";
    }

    @Override
    public String sorteiaPalavra() {
        palavraSorteada = getNovaPalavra();

        return getFabricaEmbaralhadores().getEmbaralhadorAleatorio().embaralhar(palavraSorteada);
    }

    @Override
    public boolean acabou() {
        return tentativas == 5;
    }

    @Override
    public String chutar(String chute) {
        tentativas++;
        boolean acertou = chute.equals(palavraSorteada);

        if (acertou) {
            pontuacao = pontuacao + 2;
            return descricaoChuteSucesso(palavraSorteada);
        } else {
            return descricaoChuteErrado(palavraSorteada);
        }
    }
}
