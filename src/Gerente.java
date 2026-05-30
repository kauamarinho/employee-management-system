import java.time.LocalDate;

public class Gerente extends Funcionario implements Autenticavel {
    private String senha;

    public Gerente(String nome, double salarioBase, String cpf, LocalDate dataAdmissao, String senha) {
        super(nome, salarioBase, cpf, dataAdmissao);
        this.senha = senha;
    }

    @Override
    public double calcularSalario() {
        return getSalarioBase() + 8000.0;
    }

    @Override
    public boolean autenticar(String senha) {
        return this.senha.equals(senha);
    }
}
