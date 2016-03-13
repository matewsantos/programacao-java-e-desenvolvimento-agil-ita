public class Principal {
    public static void main (String args[]) {
        Paciente regis = new Paciente(57, 1.7);
        Paciente bianca = new Paciente(47, 1.62);
        Paciente miguel = new Paciente(120, 1.8);

        System.out.println(regis.calcularIMC());
        System.out.println(regis.disgnostico());

        System.out.println(bianca.calcularIMC());
        System.out.println(bianca.disgnostico());

        System.out.println(miguel.disgnostico());
        System.out.println(miguel.calcularIMC());
    }
}
