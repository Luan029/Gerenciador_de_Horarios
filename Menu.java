import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {

        Scanner ler = new Scanner(System.in);
        Professor novoProfessor = new Professor(null, null, null, 0, null);
        int opcao = 0;

        do {
            System.out.println("Selecione uma opção:");
            System.out.println("1 - Cadastrar professor");
            System.out.println("2 - Editar professor");
            System.out.println("3 - Ver dados do professor");
            System.out.println("4 - Listar professores");
            System.out.println("5 - Excluir professor");
            System.out.println("0 - Sair");

            opcao = ler.nextInt();
            ler.nextLine(); // Consumir a quebra de linha deixada pelo nextInt()

            switch (opcao) {
                case 1:
                    System.out.print("\nDigite o nome do professor: ");
                    String nome = ler.nextLine();
                    System.out.print("Digite a formação do professor: ");
                    String formacao = ler.nextLine();
                    System.out.print("Digite o email do professor: ");
                    String email = ler.nextLine();
                    System.out.print("Digite a identificação do professor: ");
                    int identificacao = ler.nextInt();
                    ler.nextLine(); // Consumir a quebra de linha deixada pelo nextInt()
                    ArrayList<Turma> turma = new ArrayList<Turma>();
                    novoProfessor = new Professor(nome, formacao, email, identificacao, turma);
                    System.out.println("Professor cadastrado com sucesso.");

                    break;
                case 2:
                    System.out.print("\nDigite a identificação do professor que deseja editar: ");
                    identificacao = ler.nextInt();
                    if (novoProfessor.editarProfessor(identificacao)) {
                        System.out.println("Professor editado com sucesso.");
                    } else {
                        System.out.println("Professor não encontrado.");
                    }
                    break;
                case 3:
                    System.out.print("\nDigite a identificação do professor que deseja ver os dados: ");
                    identificacao = ler.nextInt();
                    ler.nextLine(); // Consumir a quebra de linha deixada pelo nextInt()
                    Professor.verDados(identificacao);
                    break;
                case 4:
                    System.out.println("\n-- Lista de professores --");
                    for (Professor p : novoProfessor.listarProfessores()) {
                        System.out.println(p);
                    }
                    break;
                case 5:
                    System.out.print("\nDigite a identificação do professor que deseja excluir: ");
                    identificacao = ler.nextInt();
                    ler.nextLine(); // Consumir a quebra de linha deixada pelo nextInt()
                    if (novoProfessor.excluirProfessor(identificacao)) {
                        System.out.println("Professor excluído com sucesso.");
                    } else {
                        System.out.println("Professor não encontrado.");
                    }
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        } while (opcao != 0);

        ler.close();
    }

}
