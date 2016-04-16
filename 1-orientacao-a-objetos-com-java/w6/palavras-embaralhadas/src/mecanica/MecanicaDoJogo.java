package mecanica;

import banco.BancoDePalavras;
import mecanica.embaralhador.FabricaEmbaralhadores;

public abstract class MecanicaDoJogo {
    private FabricaEmbaralhadores fabricaEmbaralhadores;
    private BancoDePalavras bancoDePalavras;
    protected int pontuacao;

    public MecanicaDoJogo(FabricaEmbaralhadores fabricaEmbaralhadores, BancoDePalavras bancoDePalavras) {
        this.fabricaEmbaralhadores = fabricaEmbaralhadores;
        this.bancoDePalavras = bancoDePalavras;
    }

    public abstract String descricao();
    public abstract String sorteiaPalavra();
    public abstract boolean acabou();
    public abstract String chutar(String chute);

    public int getPontuacao() {
        return pontuacao;
    }

    protected String descricaoChuteSucesso(String palavra) {
        return "Você acertou a palavra " + palavra + "!";
    }

    protected String descricaoChuteErrado(String palavra) {
        return "Você errou. A palavra correta era: " + palavra + ".";
    }

    protected FabricaEmbaralhadores getFabricaEmbaralhadores() {
        return fabricaEmbaralhadores;
    }

    protected String getNovaPalavra() {
        return bancoDePalavras.getPalavraAleatoria();
    }
}
