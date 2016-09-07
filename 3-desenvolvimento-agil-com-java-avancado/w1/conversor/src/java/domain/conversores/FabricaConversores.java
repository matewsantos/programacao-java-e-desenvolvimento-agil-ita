package domain.conversores;

public class FabricaConversores {
    public Conversor getConversor(String conversor) {
        switch(conversor) {
            case "celsiusParaFahrenheit": {
                return new CelsiusParaFahrenheit();
            }
            
            case "fahrenheitParaCelsius": {
                return new FahrenheitParaCelsius();
            }
            
            default: {
                throw new ConversorNaoEnconstradoException();
            }
        }
    }
}
