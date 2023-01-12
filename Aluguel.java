import java.util.Date;
import java.util.Objects;

public class Aluguel {
    private Livro livro;
    private Cliente cliente;
    private Date dataInicio;
    private Date dataFim;

    public Aluguel(Livro livro, Cliente cliente, Date dataInicio) {
        this.livro = livro;
        this.cliente = cliente;
        this.dataInicio = dataInicio;
        dataFim = null;
        livro.registrarAluguel(this);
        cliente.registrarAluguel(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluguel aluguel = (Aluguel) o;
        return Objects.equals(livro, aluguel.livro) && Objects.equals(cliente, aluguel.cliente) &&
                Objects.equals(dataInicio, aluguel.dataInicio) && Objects.equals(dataFim, aluguel.dataFim);
    }

    @Override
    public int hashCode() {
        return Objects.hash(livro, cliente, dataInicio, dataFim);
    }

    public boolean isAtivo() {
        return dataFim == null;
    }

    public Livro getLivro() {
        return livro;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }
}
