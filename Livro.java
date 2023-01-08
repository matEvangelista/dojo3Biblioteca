import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Livro {
    private String titulo, autor, editora;
    private Date dataPublicacao;
    private Aluguel aluguel;


    public Livro(String titulo, String autor, String editora, Date dataPublicacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.dataPublicacao = dataPublicacao;
        aluguel = null;
    }

    // equals para usar com arraylist


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Livro livro = (Livro) o;

        if (!Objects.equals(titulo, livro.titulo)) return false;
        if (!Objects.equals(autor, livro.autor)) return false;
        if (!Objects.equals(editora, livro.editora)) return false;
        if (!Objects.equals(dataPublicacao, livro.dataPublicacao))
            return false;
        return Objects.equals(aluguel, livro.aluguel);
    }

    // adiciona emprestimo (mesma lógica da classe cliente)
    public boolean fazEmprestimo(Aluguel aluguel) throws Exception {
        if (isAlugado())
            return false;
        this.aluguel = aluguel;
        return true;
    }

    // desfaz emprestimo
    public boolean desfazEmprestimo(Aluguel aluguel) {
        if (!aluguel.equals(this.aluguel))
            return false;
        aluguel = null;
        return true;
    }


    // is alugado
    public boolean isAlugado() {
        return aluguel != null;
    }

    // toString com os dados do livro
    public String toString() {
        return "Título: " + titulo + "\nAutor: " + autor + "\nEditora: " + editora + "\nData de publicação: " +
                dataPublicacao + "\nAlugado: " + (isAlugado() ? "sim" : "não") + "\n";
    }

    // getter
    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getEditora() {
        return editora;
    }

}
