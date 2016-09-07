package domain.conversores;

public class CelsiusParaFahrenheit implements Conversor {
    public double converter(double celsius) {
        return ((celsius * 9) / 5) + 32;
    }
}
