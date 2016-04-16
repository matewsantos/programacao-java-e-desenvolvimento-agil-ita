public class Respeitoso implements FormatadorNome {

    public enum Genero {
        MASCULINO("Sr."),
        FEMININO("Sra.");

        private String tratamento;

        Genero(String tratamento) {
            this.tratamento = tratamento;
        }

        public String getTratamento() {
            return this.tratamento;
        }
    }

    Genero genero;

    public Respeitoso(Genero genero) {
        this.genero = genero;
    }

    @Override
    public String formatarNome(String nome, String sobrenome) {
        return this.genero.getTratamento() + " " + sobrenome;
    }
}
