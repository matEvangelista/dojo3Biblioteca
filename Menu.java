import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.InputMismatchException;
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
            int opcao = leOpcao(1, 10);
            switch (opcao) {
                case 1:
                    listarLivros();
                    break;
                case 2:
                    listarClientes();
                    break;
                case 3:
                    listarEmprestimos();
                    break;
                case 4:
                    cadastrarUsuario();
                    break;
                case 5:
                    cadastrarLivro();
                    break;
                case 6:
                    removerUsuario();
                    break;
                case 7:
                    removerLivro();
                    break;
                case 8:
                    try {
                        fazerEmprestimo();
                    } catch (Exception exception) {
                        System.out.println(exception);
                    }
                    break;
                case 9:
                    try {
                        desfazerEmprestimo();
                    } catch (Exception exception) {
                        System.out.println(exception);
                    }
                    break;
                case 10:
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
        System.out.println("""
                O que voc?? quer fazer?
                1. Listar todos os livros
                2. Listar todos os clientes
                3. Listar empr??stimos
                4. Cadastrar um usuario
                5. Cadastrar um livro
                6. Remover um usu??rio
                7. Remover um livro
                8. Fazer um empr??stimo
                9. Desfazer um empr??stimo
                10. Sair

                """);
    }

    public void listarLivros() {
        System.out.println(biblioteca.stringLivros());
    }

    public void listarClientes() {
        System.out.println(biblioteca.stringClientes());
    }

    public void listarEmprestimos() {
        System.out.println(biblioteca.stringAlugueis());
    }

    public void cadastrarLivro() {
        Livro livro = leLivro();
        if (biblioteca.adicionaLivro(livro)) System.out.println("Livro cadastrado com sucesso!");
        else System.out.println("Este livro j?? est?? cadastrado. Tente outro");
    }

    public void cadastrarUsuario() {
        String nome = leString("nome do usu??rio");
        String cpf = leString("cpf do usu??rio");
        Cliente cliente = new Cliente(nome, cpf);
        if (biblioteca.adicionaCliente(cliente)) System.out.print("Cliente cadastrado com sucesso!");
        else System.out.print("N??o foi poss??vel cadastrar esse cliente pois ele j?? est?? cadastrado");
    }

    public void removerUsuario() {
        Cliente cliente = leUsuario();
        if (biblioteca.getClientes().contains(cliente))
            if (biblioteca.removeCliente(cliente)) System.out.print("Cliente removido com sucesso!");
            else System.out.print("Este cliente n??o pode ser removido pois tem pend??ncias");
        else System.out.println("Cliente n??o cadastrado");
    }

    public void removerLivro() {
        Livro livro = leLivro();
        if (biblioteca.removeLivro(livro)) System.out.println("Livro removido com sucesso!");
        else if (!biblioteca.getLivros().contains(livro))
            System.out.println("N??o se pode remover o que n??o se cadastrou");
        else System.out.println("Este livro n??o pode ser exclu??do porque est?? emprestado");
    }

    public void fazerEmprestimo() throws Exception {
        Cliente cliente = leUsuario();
        Livro livro = leLivro();
        int data = Integer.parseInt(leString("data do emprestimo"));
        if (!biblioteca.getLivros().contains(livro)) System.out.println("N??o se pode emprestar o que n??o se cadastrou");
        else if (!biblioteca.getClientes().contains(cliente)) {
            System.out.println("N??o se pode emprestar a quem n??o se cadastrou");
        }
        if (biblioteca.fazAluguel(cliente, livro, new Date())) System.out.println("Livro cadastrado com sucesso");
        else System.out.println("Este cliente j?? tem 2 empr??stimos ativos");
    }

    public void desfazerEmprestimo() throws Exception {
        Cliente cliente = leUsuario();
        Livro livro = leLivro();
        Aluguel aluguel = leAluguel(cliente);
        Date dataFim = new Date();
        livro.desfazEmprestimo(aluguel);
        cliente.desfazEmprestimo(aluguel, dataFim);
    }

    public String leString(String atributo) {
        System.out.printf("Digite o %s:", atributo);
        return scanner.nextLine();
    }

    public Cliente leUsuario() {
        String nome = leString("nome do usu??rio");
        String cpf = leString("cpf do usu??rio");
        return new Cliente(nome, cpf);
    }

    public Livro leLivro() {
        String titulo = leString("t??tulo do livro");
        String autor = leString("autor do livro");
        String editora = leString("editora");
        Date dataDePublicacao = new Date();
        return new Livro(titulo, autor, editora, dataDePublicacao);
    }

    public int leOpcao(int min, int max) {
        System.out.print("Digite uma op????o: ");
        int opcao = scanner.nextInt();
        if (opcao < min || opcao > max) opcao = 0;
        consomeEnter();
        return opcao;
    }

    public Aluguel leAluguel (Cliente cliente) throws Exception, InputMismatchException {
        int tam = cliente.alugueisAtivos().size();
        if (tam == 0)
            throw new Exception("N??o tem alugueis ativos");
        System.out.println("Digite qual aluguel voc?? quer dezfazer: ");
        for (int i = 0; i < tam; i++)
            System.out.println(i + 1 + ":" + cliente.alugueisAtivos().get(i));
        int opcao = leOpcao(1, tam + 1) - 1;
        return cliente.alugueisAtivos().get(opcao);
    }

    private void consomeEnter() {
        scanner.nextLine();
    }


}
