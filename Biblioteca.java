import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class Biblioteca {
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Livro> livros = new ArrayList<>();
    private ArrayList<Aluguel> alugueis = new ArrayList<>();

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

    public boolean fazAluguel(Cliente cliente, Livro livro, Date dataInicio) throws Exception {
        if (!clientes.contains(cliente) || !livros.contains(livro) || !cliente.podeAlugarMais() || livro.isAlugado())
            return false;
        int pos = clientes.indexOf(cliente);
        Aluguel al = new Aluguel(clientes.get(pos), livro, dataInicio);
        if (clientes.get(pos).adicionaEmprestimo(al)) {
            livro.fazEmprestimo(al);
            alugueis.add(al);
            return true;
        }
        return false;
    }

    public boolean desfazAluguel(Cliente cliente, Aluguel aluguel, Livro livro, Date dataFim) throws Exception {
        if (!clientes.contains(cliente) || !cliente.possuiAluguelAtivo() || !livros.contains(livro) ||
                !livro.isAlugado() || !alugueis.contains(aluguel) || !aluguel.getLivro().equals(livro) ||
                !aluguel.getCliente().equals(cliente) || encontraAluguel(aluguel) == -1)
            return false;
        cliente.desfazEmprestimo(aluguel, dataFim);
        alugueis.get(encontraAluguel(aluguel)).setDataFim(dataFim);
        livro.desfazEmprestimo(aluguel);
        return true;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public ArrayList<Livro> getLivros() {
        return livros;
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

    public String stringAlugueis() {
        String resultado = "";
        for (Aluguel aluguel : alugueis)
            resultado += aluguel.toString();
        return resultado;
    }

    public int encontraAluguel(Aluguel aluguel) {
        for (int i = 0; i < alugueis.size(); i++)
            if (aluguel.equals(alugueis.get(i)))
                return i;
        return -1;
    }


}
