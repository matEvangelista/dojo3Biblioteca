import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private Biblioteca biblioteca;
    private Scanner input;

    public Menu() {
        biblioteca = new Biblioteca();
        input = new Scanner(System.in);
    }

    public void apresentaMenu() {
        boolean continua = true;
        do {
            mostraOpcoes();
            int opcao = leOpcao();
            switch (opcao) {
                case 1:
                    cadastraCliente();
                    break;
                case 2:
                    cadastrarLivro();
                    break;
                case 3:
                    // listaClientes();
                    break;
                case 4:
                    // listaLivros();
                    break;
                case 5:
                    // fazerEmprestimo();
                    break;
            }

        } while (continua);
    }

    private int leOpcao() {
        System.out.println("Digite um número: ");
        while (true) {
            try {
                int opcao = input.nextInt();
                consomeEnter();
                return opcao;
            } catch (InputMismatchException e) {
                System.out.println("Digite corretamente");
            }
        }
    }

    private void mostraOpcoes() {
        System.out.println("""
                Olá.
                Digite o que você quer fazer:
                1 - Cadastrar cliente
                2 - Cadastrar livro
                3 - Listar clientes
                4 - Listar livros
                5 - Fazer emprestimo
                6 - Devolver livro
                """);
    }

    private void cadastraCliente() {
        Cliente cliente = leCliente();
        if (biblioteca.registrarClientes(cliente))
            System.out.println("Cliente cadastrado com sucesso.");
        else
            System.out.println("Não se cadastra o que já se cadastrou.");
    }

    private Cliente leCliente() {
        String nome = leString("nome do cliente");
        String cpf = leString("cpf do cliente");
        return new Cliente(nome, cpf);
    }

    private Livro leLivro() {
        String titulo = leString("título do livro");
        String autor = leString("autor do livro");
        String editora = leString("editora do livro");
        Date data = null; // TODO: colocar método pra ler data
        return new Livro(titulo, autor, editora, data);
    }

    private void cadastrarLivro() {
        if (biblioteca.adicionaLivro(leLivro()))
            System.out.println("Livro cadastrado com sucesso");
        else
            System.out.println("Livro cadastrado com sucesso");
    }

    private String leString(String impreso) {
        System.out.print("Digite o" + impreso + ": ");
        String s = input.nextLine();
        return s;
    }

    private void consomeEnter() {
        input.nextLine();
    }
}
