package mecanica;

import banco.BancoDePalavras;
import mecanica.embaralhador.FabricaEmbaralhadores;

public class MorteSubita extends MecanicaDoJogo {
    private String palavraSorteada;
    private boolean acabou;

    public MorteSubita(FabricaEmbaralhadores fabricaEmbaralhadores, BancoDePalavras bancoDePalavras) {
        super(fabricaEmbaralhadores, bancoDePalavras);
        palavraSorteada = getNovaPalavra();
    }

    @Override
    public String descricao() {
        return "Morte súbita!!! Jogo termina após primeiro erro. Cada acerto vale 1 ponto.";
    }

    @Override
    public String sorteiaPalavra() {
        palavraSorteada = getNovaPalavra();

        return getFabricaEmbaralhadores().getEmbaralhadorAleatorio().embaralhar(palavraSorteada);
    }

    @Override
    public boolean acabou() {
        return acabou;
    }

    @Override
    public String chutar(String chute) {
        boolean acertou = chute.equals(palavraSorteada);
        acabou = !acertou;

        if (acertou) {
            pontuacao++;
            return descricaoChuteSucesso(palavraSorteada);
        } else {
            return descricaoChuteErrado(palavraSorteada);
        }
    }
}