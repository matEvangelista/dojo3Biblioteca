import java.lang.ref.Cleaner;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Biblioteca {
    private ArrayList<Livro> livros;
    private ArrayList<Cliente> clientes;
    private ArrayList<Aluguel> alugueis;

    public Biblioteca() {
        this.livros = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.alugueis = new ArrayList<>();
    }

    public boolean adicionaLivro(Livro livro) {
        if (livros.contains(livro))
            return false;
        livros.add(livro);
        return true;
    }

    // TODO adicionar exceptions
    public boolean registrarAluguel(Livro livro, Cliente cliente) {
        if (livro.isAlugado() || !cliente.podeAlugarMais(livro) || !clientes.contains(cliente) || !livros.contains(livro))
            return false;
        Date hoje = Calendar.getInstance().getTime();
        Aluguel aluguel = new Aluguel(livro, cliente, hoje);
        return alugueis.add(aluguel);
    }

    public boolean registrarClientes(Cliente cliente) {
        if (clientes.contains(cliente))
            return false;
        return clientes.add(cliente);
    }

    public boolean removerCliente(Cliente cliente) throws Exception {
        if (clientes.contains(cliente) && !cliente.possuiEmprestimoAtivo())
            return clientes.remove(cliente);
        if (cliente.possuiEmprestimoAtivo())
            throw new Exception("Cliente tem empréstimo ativo");
        if (!clientes.contains(cliente))
            throw new Exception("Cliente não cadastrado");
        return false;
    }

    public boolean registrarLivro(Livro livro) {
        if (livros.contains(livro))
            return false;
        return livros.add(livro);
    }

    public boolean removeLivro(Livro livro) throws Exception {
        if (!livros.contains(livro)) {
            throw new Exception("Este livro não está cadastrado!");
        }
        if (livro.isAlugado()) {
            throw new Exception("Livro já alugado.");
        }
        livros.remove(livro);
        return true;
    }

}
