import java.time.LocalDate;

public class Vendedor extends Funcionario {
    public Vendedor(String nome, double salarioBase, String cpf, LocalDate dataAdmissao) {
        super(nome, salarioBase, cpf, dataAdmissao);
    }

    @Override
    public double calcularSalario() {
        return getSalarioBase() + 2000.0;
    }
}
