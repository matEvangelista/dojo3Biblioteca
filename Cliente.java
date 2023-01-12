import java.util.ArrayList;

public class Cliente {
    private String cpf, nome;
    private ArrayList<Aluguel> alugueis;

    public Cliente(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
        alugueis = new ArrayList<>();
    }

    public boolean possuiEmprestimoAtivo() {
        for (Aluguel aluguel : alugueis)
            if (aluguel.isAtivo())
                return true;
        return false;
    }

    private ArrayList<Aluguel> tresUltimos() {
        if (alugueis.size() < 3)
            return null;
        ArrayList <Aluguel> tres = new ArrayList<>();
        for (int i = alugueis.size() - 1; i < alugueis.size()-4; i--) {
            tres.add(alugueis.get(i));
        }
        return tres;
    }

    public void registrarAluguel(Aluguel aluguel) {
        alugueis.add(aluguel);
    }

    private boolean livroUltimosTres(Livro livro){
        for (Aluguel aluguel:tresUltimos()) {
            if (aluguel.getLivro().equals(livro))
                return true;
        }
        return false;
    }

    public boolean podeAlugarMais(Livro livro) {
        int soma = 0;
        for (Aluguel aluguel : alugueis)
            if(aluguel.isAtivo())
                soma++;
        return soma < 2 && livroUltimosTres(livro);
    }

}
