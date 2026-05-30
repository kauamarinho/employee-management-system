import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private List<Funcionario> funcionarios;

    public Empresa() {
        this.funcionarios = new ArrayList<>();
    }

    public void adicionarFuncionario(Funcionario f) throws CpfInvalidoException {
        for (Funcionario existente : funcionarios) {
            if (existente.getCpf().equals(f.getCpf())) {
                throw new CpfInvalidoException("CPF já cadastrado: " + f.getCpf());
            }
        }
        funcionarios.add(f);
    }

    public void removerFuncionario(String cpf) {
        funcionarios.removeIf(f -> f.getCpf().equals(cpf));
    }

    public Funcionario buscarFuncionarioPorCPF(String cpf) throws FuncionarioNaoEncontradoException {
        for (Funcionario f : funcionarios) {
            if (f.getCpf().equals(cpf)) {
                return f;
            }
        }
        throw new FuncionarioNaoEncontradoException("Funcionário com CPF " + cpf + " não encontrado.");
    }

    public void listarFuncionarios() {
        for (Funcionario f : funcionarios) {
            System.out.println(f.getNome() + " - CPF: " + f.getCpf() + " - Salário: " + f.calcularSalario());
        }
    }

    public double calcularFolhaMensal() {
        double total = 0.0;
        for (Funcionario f : funcionarios) {
            total += f.calcularSalario();
        }
        return total;
    }

    public void listarFuncionariosPorPeriodo(LocalDate inicio, LocalDate fim) throws DataInvalidaException {
        if (inicio.isAfter(fim)) {
            throw new DataInvalidaException("Data inicial não pode ser depois da data final.");
        }
        for (Funcionario f : funcionarios) {
            if (!f.getDataAdmissao().isBefore(inicio) && !f.getDataAdmissao().isAfter(fim)) {
                System.out.println(f.getNome() + " - Admissão: " + f.getDataAdmissao());
            }
        }
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }
}
