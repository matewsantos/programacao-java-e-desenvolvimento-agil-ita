package domain.conversores;

public class ConversorGenerico implements Conversor{
    private String conversor;
    private FabricaConversores fabrica;
    
    public ConversorGenerico(String conversor) {
        this.conversor = conversor;
        this.fabrica = new FabricaConversores();
    }
    
    @Override
    public double converter(double temperatura) {
        return getConversor().converter(temperatura);
    }
    
    private Conversor getConversor() {
        return fabrica.getConversor(this.conversor);
    }
}
