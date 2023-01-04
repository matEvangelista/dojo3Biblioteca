public class Aluguel {
    private String clienteNome;
    private Livro livro;
    private int dataIni;
    private boolean ativo = true; // assumi que, se um aluguel for feito, ele automaticamente estará ativo.
    // o trabalho de organizar se um aluguel será feito ou não será da Biblioteca

    public Aluguel(String clienteNome, Livro livro, int dataIni) {
        this.livro = livro;
        this.dataIni = dataIni;
        this.clienteNome = clienteNome;
    }

    @Override
    public String toString() {
        return "Cliente: " + clienteNome + "\nLivro: " + livro.getTitulo() + "\nData do empréstimo: " + dataIni +
                "\nAtivo: " + (ativo ? "sim" : "não");
    }

    // setter
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    // getter
    public String getClienteNome() {
        return clienteNome;
    }

    public Livro getLivro() {
        return livro;
    }

    public int getDataIni() {
        return dataIni;
    }

    public boolean isAtivo() {
        return ativo;
    }
}
