import java.util.Date;
import java.util.Objects;

public class Aluguel {
    private Cliente cliente;
    private Livro livro;
    private Date dataIni, dataFim;

    public Aluguel(Cliente cliente, Livro livro, Date dataIni) {
        this.cliente = cliente;
        this.livro = livro;
        this.dataIni = dataIni;
        this.dataFim = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Aluguel aluguel = (Aluguel) o;

        if (!Objects.equals(cliente, aluguel.cliente)) return false;
        if (!Objects.equals(livro, aluguel.livro)) return false;
        if (!Objects.equals(dataIni, aluguel.dataIni)) return false;
        return Objects.equals(dataFim, aluguel.dataFim);
    }

    // empréstimo está ativo
    public boolean isAtivo() {
        return dataFim == null;
    }

    @Override
    public String toString() {
        return "Cliente: " + cliente + "\nLivro: " + livro.getTitulo() + "\nData do empréstimo: " + dataIni +
                "\nAtivo: " + (isAtivo() ? "sim" : "não");
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Livro getLivro() {
        return livro;
    }

    public Date getDataIni() {
        return dataIni;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }
}
