import java.util.Objects;

public class Livro {
    private String titulo, autor, editora, dataPublicacao; // não sei trabalhar com datas
    private boolean alugado = false; // vai ser útil na hora de listar o livro.
    // naturalmente, um livro criado não é automaticamente adicionado

    public Livro(String titulo, String autor, String editora, String dataPublicacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.dataPublicacao = dataPublicacao;
    }

    // equals para usar com arraylist
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Livro livro = (Livro) o;

        if (alugado != livro.alugado) return false;
        if (!Objects.equals(titulo, livro.titulo)) return false;
        if (!Objects.equals(autor, livro.autor)) return false;
        if (!Objects.equals(editora, livro.editora)) return false;
        return Objects.equals(dataPublicacao, livro.dataPublicacao);
    }

    // toString com os dados do livro
    public String toString() {
        return "Título: " + titulo + "\nAutor: " + autor + "\nEditora: " + editora + "\nData de publicação: " +
                dataPublicacao + "\nAlugado: " + (alugado ? "sim" : "não") + "\n";
    }

    // "setter", outros dados não podem ser alterados
    public void setAlugado(boolean alugado) {
        this.alugado = alugado;
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

    public String getDataPublicacao() {
        return dataPublicacao;
    }

    public boolean isAlugado() {
        return alugado;
    }
}
