import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Professor professor = new Professor();
        Turma turmas = new Turma();
        List<Professor> professoresDisponiveis = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        int opcao;

        do {
            System.out.println("      GERENCIADOR DE HORARIOS");
            System.out.println("|============== MENU ==============|");
            System.out.println("|1 - Cadastrar professor           |");
            System.out.println("|2 - Editar professor              |");
            System.out.println("|3 - Ver dados de um professor     |");
            System.out.println("|4 - Listar Professores            |");
            System.out.println("|5 - Exluir Professor              |");
            System.out.println("|11- Cadastrar Turma               |");
            System.out.println("|12- Editar Turma                  |");
            System.out.println("|0 - Sair                          |");
            System.out.println("|__________________________________|");

            System.out.print("Opcao: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            int idProf, idTur;
            switch (opcao) {
                case 1:
                    cadastrarProfessor(scanner, professor);
                    break;
                case 2:
                    System.out.print("Informe a Identificacao do professor: ");
                    idProf = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        professor.editarProfessor(idProf);
                    } catch (IOException e) {
                        System.out.println("Erro ao editar professor: " + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.print("Informe a Identificacao do professor: ");
                    idProf = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        professor.verDadosDeUmProfessor(idProf);
                    } catch (IOException e) {
                        System.out.println("Erro ao editar professor: " + e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        professor.listarProfessores();
                    } catch (IOException e) {
                        System.out.println("Erro ao listar os professores: " + e.getMessage());
                    }

                    break;
                case 5:
                    System.out.print("Informe a Identificacao do professor: ");
                    idProf = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        professor.excluirProfessor(idProf);
                        System.out.println("Professor excluido com sucesso.");
                    } catch (IOException e) {
                        System.out.println("Ocorreu um erro ao excluir o professor: " + e.getMessage());
                    }
                    break;
                case 11:
                    try {
                        Turma turma = new Turma(professoresDisponiveis);
                        turma.salvarTurma();
                    } catch (IOException e) {
                        System.out.println("Erro ao criar turma: " + e.getMessage());
                    }
                    break;
                case 12:
                    System.out.print("Informe a Identificacao da turma: ");
                    idTur = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        turmas.editarTurma(idTur);
                    } catch (IOException e) {
                        System.out.println("Erro ao editar turma: " + e.getMessage());
                    }
                    break;
                case 0:
                    System.out.println("Encerrando o programa...");
                    scanner.nextLine();
                    break;
                default:
                    System.out.println("Opção invalida.");
                    scanner.nextLine();
                    break;
            }

            System.out.println();
        } while (opcao != 0);

        scanner.close();
    }

    public static void cadastrarProfessor(Scanner scanner, Professor professor) {
        String nome, email, formacao;
        int identificacao;

        System.out.println("CADASTRO DE PROFESSOR");
        System.out.println("=====================");

        System.out.print("Nome: ");
        nome = scanner.nextLine();

        System.out.print("Identificacao: ");
        identificacao = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Email: ");
        email = scanner.nextLine();

        System.out.print("Formacao: ");
        formacao = scanner.nextLine();

        professor.setNome(nome);
        professor.setIdentificacao(identificacao);
        professor.setEmail(email);
        professor.setFormacao(formacao);

        try {
            professor.salvar();
            System.out.println("Professor cadastrado com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar professor: " + e.getMessage());
        }
    }
}
