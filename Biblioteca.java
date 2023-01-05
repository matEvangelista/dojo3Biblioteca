import java.util.ArrayList;

public class Biblioteca {
    public ArrayList<Cliente> clientes = new ArrayList<>();
    public ArrayList<Livro> livros = new ArrayList<>(1000);

    public Biblioteca() {
    }

    public boolean adicionaCliente(Cliente cliente) {
        if (clientes.contains(cliente)) return false;
        clientes.add(cliente);
        return true;
    }

    public boolean removeCliente(Cliente cliente) {
        if (clientes.contains(cliente) && !cliente.possuiAluguelAtivo()) {
            clientes.remove(cliente);
            return true;
        }
        return false;
    }

    public boolean adicionaLivro(Livro livro) {
        if (livros.contains(livro) || livros.size() >= 1000) return false;
        livros.add(livro);
        return true;
    }

    public boolean removeLivro(Livro livro) {
        if (livros.contains(livro) && !livro.isAlugado()) {
            livros.remove(livro);
            return true;
        }
        return false;
    }

    public boolean fazAluguel(Cliente cliente, Livro livro, int dataInicio) {
        if (!clientes.contains(cliente) || !livros.contains(livro) || !cliente.podeAlugarMais() || livro.isAlugado())
            return false;
        int pos = clientes.indexOf(cliente);
        if (clientes.get(pos).pegaEmprestimo(livro, dataInicio)) {
            livro.setAlugado(true);
            return true;
        }
        return false;
    }

    public boolean desfazAluguel(Cliente cliente, Livro livro) {
        if (!clientes.contains(cliente) || !cliente.possuiAluguelAtivo() || !livros.contains(livro) ||
                !livro.isAlugado()) {
            return false;
        }
        int pos = clientes.indexOf(cliente);
        if (clientes.get(pos).desfazEmprestimo(livro)) {
            livro.setAlugado(false);
            return true;
        }
        return false;
    }

    public Cliente buscaClientePorPosicao(int p) {
        if (p < clientes.size())
            return clientes.get(p);
        return null;
    }

    public Livro buscaLivroPorPosicao(int p) {
        if (p < livros.size())
            return livros.get(p);
        return null;
    }

    public String stringLivros() {
        String resultado = "";
        for (Livro livro : livros)
            resultado += livro.toString();
        return resultado;
    }

    public String stringClientes() {
        String resultado = "";
        for (Cliente cliente : clientes)
            resultado += cliente.toString();
        return resultado;
    }


}
