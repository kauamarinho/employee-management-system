public class Secretary extends Employee {
    private boolean languageBonus;

    public Secretary(String name, double baseSalary, String cpf, java.time.LocalDate hireDate, boolean languageBonus) {
        super(name, baseSalary, cpf, hireDate);
        this.languageBonus = languageBonus;
    }

    public boolean isLanguageBonus() {
        return languageBonus;
    }

    public void setLanguageBonus(boolean languageBonus) {
        this.languageBonus = languageBonus;
    }

    @Override
    public double calculateSalary() {
        double bonus = languageBonus ? 500.0 : 0.0;
        return getBaseSalary() + bonus;
    }
}
