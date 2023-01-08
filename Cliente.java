import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Cliente {
    private String nome, cpf;
    // Um cliente tem alugueis de livros. Gambiarra? Muito provável :)
    private ArrayList<Aluguel> alugueis;

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        alugueis = new ArrayList<>();
    }

    /*
     * Quem, de fato, empresta é a classe biblioteca. Esta apenas adiciona ou remove emprestimos,
     * portanto não haverá nenhum objeto da classe Livro aqui.
     */
    public boolean adicionaEmprestimo(Aluguel aluguel) throws Exception {
        if (!podeAlugarMais())
            return false;
        if (alugueis.contains(aluguel))
            throw new Exception("Aluguel já feito.");
        alugueis.add(aluguel);
        return true;
    }

    // desfaz emprestimo
    public boolean desfazEmprestimo(Aluguel aluguel, Date dataFim) throws Exception {
        if (!alugueis.contains(aluguel))
            throw new Exception("Empréstimo não registrado!");
        alugueis.get(encontraAluguel(aluguel)).setDataFim(dataFim);
        return true;
    }

    // possui aluguel ativo
    public boolean possuiAluguelAtivo() {
        for (Aluguel aluguel : alugueis) {
            if (aluguel.isAtivo())
                return true;
        }
        return false;
    }

    // lista alugueis ativos
    public ArrayList<Aluguel> alugueisAtivos() {
        ArrayList<Aluguel> retorno = new ArrayList<>();
        for (Aluguel aluguel : alugueis) {
            if (aluguel.isAtivo())
                retorno.add(aluguel);
        }
        return retorno.size() > 0 ? retorno : null;
    }

    // o cliente pode fazer mais alugueis?
    public boolean podeAlugarMais() {
        int i = 0;
        for (Aluguel aluguel : alugueis)
            if (aluguel.isAtivo())
                i++;
        return i < 2; // dois alugueis ativos no maximo
    }

    // encontra na lista de alugueis
    private int encontraAluguel(Aluguel aluguel) {
        for (int i = 0; i < alugueis.size(); i++)
            if (aluguel.equals(alugueis.get(i)))
                return i;
        return -1;
    }

    @Override
    public int hashCode() {
        int result = nome != null ? nome.hashCode() : 0;
        result = 31 * result + (cpf != null ? cpf.hashCode() : 0);
        result = 31 * result + (alugueis != null ? alugueis.hashCode() : 0);
        return result;
    }

    // para arraylist
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cliente cliente = (Cliente) o;

        return cpf.equals(cliente.cpf);
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ". CPF: " + cpf + ". Possui alugueis ativos?: "
                + (possuiAluguelAtivo() ? "sim\n" : "não\n");
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }
}
