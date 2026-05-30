import java.time.LocalDate;

public abstract class Funcionario {
    private String nome;
    private double salarioBase;
    private String cpf;
    private LocalDate dataAdmissao;

    public Funcionario(String nome, double salarioBase, String cpf, LocalDate dataAdmissao) {
        this.nome = nome;
        this.salarioBase = salarioBase;
        this.cpf = cpf;
        this.dataAdmissao = dataAdmissao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public final String getCpf() {
        return cpf;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public abstract double calcularSalario();
}
