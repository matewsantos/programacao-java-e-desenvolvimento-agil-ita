public class Paciente {
    private double peso;
    private double altura;

    public Paciente(double peso, double altura) {
        this.peso = peso;
        this.altura = altura;
    }

    public double calcularIMC() {
        return peso / (altura * altura);
    }

    public String disgnostico() {
        double imc = calcularIMC();
        String resultado = null;

        if (imc < 16) {
            resultado = "Baixo peso muito grave";
        } else if (imc < 17) {
            resultado = "Baixo peso grave";
        } else if (imc < 18.5) {
            resultado = "Baixo peso";
        } else if (imc < 25) {
            resultado = "Peso normal";
        } else if (imc < 30) {
            resultado = "Sobrepeso";
        } else if (imc < 35) {
            resultado = "Obesidade grau I";
        } else if (imc < 40) {
            resultado = "Obesidade grau II";
        } else {
            resultado = "Obesidade grau III (obesidade mórbida)";
        }

        return resultado;
    }
}
