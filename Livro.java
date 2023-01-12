import java.util.ArrayList;
import java.util.Date;

public class Livro {
    private String titulo;
    private String autor;
    private String editora;
    private Date dataPublicacao;
    private ArrayList<Aluguel> alugueis;


    public Livro(String titulo, String autor, String editora, Date dataPublicacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.dataPublicacao = dataPublicacao;
        alugueis = new ArrayList<>();
    }

    public boolean isAlugado() {
        return alugueis.get(alugueis.size() - 1).isAtivo();
    }

    public void registrarAluguel(Aluguel aluguel) {
        alugueis.add(aluguel);
    }
}
