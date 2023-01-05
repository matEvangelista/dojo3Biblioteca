import javax.swing.*;
import java.util.Scanner;

public class Menu {

    private Scanner scanner = new Scanner(System.in);
    private Biblioteca biblioteca;

    public Menu(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    public void menuDeFato() {
        boolean continua = true;
        do {
            mostraOpcoes();
            int opcao = leOpcao();
            switch (opcao) {
                case 1:
                    listarLivros();
                    break;
                case 2:
                    listarClientes();
                    break;
                case 3:
                    cadastrarUsuario();
                    break;
                case 4:
                    cadastrarLivro();
                    break;
                case 5:
                    removerUsuario();
                    break;
                case 6:
                    removerLivro();
                    break;
                case 7:
                    fazerEmprestimo();
                    break;
                case 8:
                    //desfazerEmprestimo();
                    break;
                case 9:
                    System.out.println("Adeus");
                    continua = false;
                    break;
                case 0:
                    System.out.println("Digite corretamente");
                    break;
            }
        } while (continua);

    }

    public void mostraOpcoes() {
        System.out.println(
                "O que você quer fazer?\n" +
                        "1. Listar todos os livros\n" +
                        "2. Listar todos os clientes\n" +
                        "3. Cadastrar um usuario\n" +
                        "4. Cadastrar um livro\n" +
                        "5. Remover um usuário\n" +
                        "6. Remover um livro\n" +
                        "7. Fazer um empréstimo\n" +
                        "8. Desfazer um empréstimo\n" +
                        "9. Sair\n\n"
        );
    }

    public void listarLivros() {
        System.out.println(biblioteca.stringLivros());
    }

    public void listarClientes() {
        System.out.println(biblioteca.stringClientes());
    }

    public void cadastrarLivro() {
        Livro livro = leLivro();
        if (biblioteca.adicionaLivro(livro))
            System.out.println("Livro cadastrado com sucesso!");
        else
            System.out.println("Este livro já está cadastrado. Tente outro");
    }

    public void cadastrarUsuario() {
        String nome = leString("nome do usuário");
        String cpf = leString("cpf do usuário");
        Cliente cliente = new Cliente(nome, cpf);
        if (biblioteca.adicionaCliente(cliente))
            System.out.printf("Cliente cadastrado com sucesso!");
        else
            System.out.printf("Não foi possível cadastrar esse cliente pois ele já está cadastrado");
    }

    public void removerUsuario() {
        Cliente cliente = leUsuario();
        if (biblioteca.clientes.contains(cliente))
            if (biblioteca.removeCliente(cliente))
                System.out.print("Cliente removido com sucesso!");
            else
                System.out.print("Este cliente não pode ser removido pois tem pendências");
        else
            System.out.println("Cliente não cadastrado");
    }

    public void removerLivro() {
        Livro livro = leLivro();
        if (biblioteca.removeLivro(livro))
            System.out.println("Livro removido com sucesso!");
        else if (!biblioteca.livros.contains(livro))
            System.out.println("Não se pode remover o que não se cadastrou");
        else
            System.out.println("Este livro não pode ser excluído porque está emprestado");
    }

    public void fazerEmprestimo() {
        Cliente cliente = leUsuario();
        Livro livro = leLivro();
        int data = Integer.parseInt(leString("data do emprestimo"));
        if (!biblioteca.livros.contains(livro))
            System.out.println("Não se pode emprestar o que não se cadastrou");
        else if (!biblioteca.clientes.contains(cliente)) {
            System.out.println("Não se pode emprestar a quem não se cadastrou");
        }
        if (biblioteca.fazAluguel(cliente, livro, 12))
            System.out.println("Livro cadastrado com sucesso");
        else
            System.out.println("Este cliente já tem 2 empréstimos ativos");
    }

    public void desfazerEmprestimo() {

    }

    public String leString(String atributo) {
        System.out.printf("Digite o %s:", atributo);
        return scanner.nextLine();
    }

    public Cliente leUsuario() {
        String nome = leString("nome do usuário");
        String cpf = leString("cpf do usuário");
        return new Cliente(nome, cpf);
    }

    public Livro leLivro() {
        String titulo = leString("título do livro");
        String autor = leString("autor do livro");
        String editora = leString("editora");
        String dataDePublicacao = leString("data de publicação");
        return new Livro(titulo, autor, editora, dataDePublicacao);
    }

    public int leOpcao() {
        System.out.printf("Digite uma opção: ");
        int opcao = scanner.nextInt();
        if (opcao < 1 || opcao > 9)
            opcao = 0;
        consomeEnter();
        return opcao;
    }

    private void consomeEnter() {
        scanner.nextLine();
    }


}
