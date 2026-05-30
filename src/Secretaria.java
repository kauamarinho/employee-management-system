public class Secretaria extends Funcionario {
    private boolean adicionalIdioma;

    public Secretaria(String nome, double salarioBase, String cpf, java.time.LocalDate dataAdmissao, boolean adicionalIdioma) {
        super(nome, salarioBase, cpf, dataAdmissao);
        this.adicionalIdioma = adicionalIdioma;
    }

    public boolean isAdicionalIdioma() {
        return adicionalIdioma;
    }

    public void setAdicionalIdioma(boolean adicionalIdioma) {
        this.adicionalIdioma = adicionalIdioma;
    }

    @Override
    public double calcularSalario() {
        double adicional = adicionalIdioma ? 500.0 : 0.0;
        return getSalarioBase() + adicional;
    }
}
