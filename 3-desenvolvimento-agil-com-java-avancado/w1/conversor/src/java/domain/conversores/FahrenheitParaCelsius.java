package domain.conversores;

public class FahrenheitParaCelsius implements Conversor {
    public double converter(double fahrenheit) {
        return ((fahrenheit - 32) * 5) / 9;
    }
}
