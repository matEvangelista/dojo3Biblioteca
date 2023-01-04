import java.util.ArrayList;
import java.util.Objects;

public class Cliente {
    private String nome, cpf;
    // Um cliente tem alugueis de livros. Gambiarra? Muito provável :)
    public ArrayList<Aluguel> alugueis = new ArrayList<>();

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    // faz um emprestimo
    public boolean pegaEmprestimo(Livro livro, int dataEmprestimo) {
        if (livro.isAlugado() || !podeAlugarMais())
            return false;
        livro.setAlugado(true);
        // exemplo de como ambos construtores foram utilizados
        alugueis.add(new Aluguel(nome, livro, dataEmprestimo));
        return true;
    }

    // devolve livro
    public boolean desfazEmprestimo(Livro livro) {
         for (Aluguel aluguel : alugueis)
             if (aluguel.getLivro().equals(livro)) {
                 // passagem por parametro
                 aluguel.setAtivo(false);
                 return true;
             }
         return false;
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

    // para arraylist
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cliente cliente = (Cliente) o;

        if (!Objects.equals(nome, cliente.nome)) return false;
        if (!Objects.equals(cpf, cliente.cpf)) return false;
        return Objects.equals(alugueis, cliente.alugueis);
    }

    @Override
    public String toString() {
        StringBuilder alugueisAtivos = new StringBuilder();
        for (Aluguel aluguel : alugueisAtivos())
            alugueisAtivos.append("\n").append(aluguel.toString());
        return "Nome: " + nome + "\nCPF: " + cpf + "\nAlugueis ativos: " + alugueisAtivos;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }
}
