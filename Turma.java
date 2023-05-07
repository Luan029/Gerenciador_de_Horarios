import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Turma {
    private int codTurma;
    private int semestre;
    private int diasAulaSemana;
    private List<String> horarios;
    private List<Professor> professores = new ArrayList<>();

    public Turma(List<Professor> professoresDisponiveis) throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean validacaoHorario = false;
        while (!validacaoHorario) {
            System.out.println("Digite o código da turma: ");
            codTurma = scanner.nextInt();
            System.out.println("Digite o semestre: ");
            semestre = scanner.nextInt();
            System.out.println("Digite o número de dias de aula na semana: ");
            diasAulaSemana = scanner.nextInt();
            horarios = new ArrayList<>();
            scanner.nextLine(); // consumir a quebra de linha pendente
            for (int i = 1; i <= diasAulaSemana; i++) {
                System.out.println("Digite o horário para o dia " + i + " (no formato HH:MM): ");
                String horario = scanner.nextLine();
                if (horarios.contains(horario)) {
                    System.out.println("Horário já cadastrado para outro dia da semana. Por favor, tente novamente.");
                    validacaoHorario = false;
                    break;
                }
                horarios.add(horario);
                validacaoHorario = true;
            }
        }

        Professor professorSelecionado = null;
        boolean validacaoProfessores = false;
        while (!validacaoProfessores) {
            System.out.println("Lista de professores disponíveis:");
            Professor professorLista = new Professor();
            professorLista.listarProfessores();
            System.out.println("Digite o código do professor que deseja cadastrar na turma: ");
            int codigoProfessor = scanner.nextInt();

            professorSelecionado = professorLista.buscarProfessor(codigoProfessor);

            if (professorSelecionado == null) {
                System.out.println("Código de professor inválido. Por favor, tente novamente.");
                validacaoProfessores = false;
            } else {
                professores.add(professorSelecionado);
                validacaoProfessores = true;
            }
        }
        scanner.close();
    }

    public Turma() {
    }

    public int getCodTurma() {
        return codTurma;
    }

    public void setCodTurma(int codTurma) {
        this.codTurma = codTurma;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public int getDiasAulaSemana() {
        return diasAulaSemana;
    }

    public void setDiasAulaSemana(int diasAulaSemana) {
        this.diasAulaSemana = diasAulaSemana;
    }

    public List<String> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<String> horarios) {
        this.horarios = horarios;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }

    public void salvarTurma() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("turmas.txt", true))) {
            writer.write("Código da turma: " + codTurma + ";");
            writer.write("Semestre: " + semestre + ";");
            writer.write("Dias de aula na semana: " + diasAulaSemana + ";");
            writer.write("Horários:");
            for (String horario : horarios) {
                writer.write(horario + " ");
            }
            writer.write("\n");
            writer.write("Professores: ");
            for (Professor professor : professores) {
                writer.write(professor.toString() + ";");
            }
            writer.write("\n\n");
            System.out.println("Turma salva com sucesso no arquivo turmas.txt");
        } catch (IOException e) {
            System.out.println("Erro ao salvar turma no arquivo turmas.txt");
        }
    }

    public static Turma buscarTurma(int codTurma) {
        try (BufferedReader reader = new BufferedReader(new FileReader("turmas.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // extrai o código da turma da linha
                int codigoInicio = line.indexOf(": ") + 2;
                int codigoFim = line.indexOf(";");
                if (codigoFim > codigoInicio) {
                    int codigo = Integer.parseInt(line.substring(codigoInicio, codigoFim));
    
                    if (codigo == codTurma) {
                        // extrai as informações da turma da linha
                        int semestre = Integer.parseInt(line.substring(line.indexOf("Semestre: ") + 10, line.indexOf(";")));
                        int diasAulaSemana = Integer.parseInt(line.substring(line.indexOf("Dias de aula na semana: ") + 23, line.indexOf("; Horários")));
                        List<String> horarios = Arrays.asList(line.substring(line.indexOf("Horários: ") + 10, line.indexOf("; Professores: ")).split(" "));
                        List<Professor> professores = new ArrayList<>();
                        String[] codigosProfessores = line.substring(line.indexOf("Professores: ") + 13, line.length() - 1).split(";");
                        Professor professorLista = new Professor();
                        for (String codigoProfessor : codigosProfessores) {
                            Professor professor = professorLista.buscarProfessor(Integer.parseInt(codigoProfessor));
                            if (professor != null) {
                                professores.add(professor);
                            }
                        }
                        Turma turma = new Turma();
                        turma.setCodTurma(codTurma);
                        turma.setSemestre(semestre);
                        turma.setDiasAulaSemana(diasAulaSemana);
                        turma.setHorarios(horarios);
                        turma.setProfessores(professores);
                        return turma;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao buscar turma no arquivo turmas.txt");
        }
        return null;
    }
    

    public boolean editarTurma(int codTurma) throws IOException {
        Turma turma = buscarTurma(codTurma);
        if (turma == null) {
            System.out.println("Turma não encontrada.");
            return false;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Turma encontrada:");
        System.out.println("Escolha o atributo que deseja editar:");
        System.out.println("1 - Semestre");
        System.out.println("2 - Número de dias de aula na semana");
        System.out.println("3 - Horários");
        System.out.println("4 - Professores");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // consumir a quebra de linha pendente
        switch (opcao) {
            case 1:
                System.out.println("Digite o novo semestre:");
                int semestre = scanner.nextInt();
                turma.setSemestre(semestre);
                break;
            case 2:
                System.out.println("Digite o novo número de dias de aula na semana:");
                int diasAulaSemana = scanner.nextInt();
                turma.setDiasAulaSemana(diasAulaSemana);
                turma.setHorarios(new ArrayList<String>());
                for (int i = 1; i <= diasAulaSemana; i++) {
                    System.out.println("Digite o novo horário para o dia " + i + " (no formato HH:MM):");
                    String horario = scanner.nextLine();
                    if (turma.getHorarios().contains(horario)) {
                        System.out
                                .println("Horário já cadastrado para outro dia da semana. Por favor, tente novamente.");
                        i--;
                        continue;
                    }
                    turma.getHorarios().add(horario);
                }
                break;
            case 3:
                turma.setHorarios(new ArrayList<String>());
                int diasAulaSemana2 = turma.getDiasAulaSemana();
                for (int i = 1; i <= diasAulaSemana2; i++) {
                    System.out.println("Digite o novo horário para o dia " + i + " (no formato HH:MM):");
                    String horario = scanner.nextLine();
                    if (turma.getHorarios().contains(horario)) {
                        System.out
                                .println("Horário já cadastrado para outro dia da semana. Por favor, tente novamente.");
                        i--;
                        continue;
                    }
                    turma.getHorarios().add(horario);
                }
                break;
            case 4:
                boolean validacaoProfessores = false;
                while (!validacaoProfessores) {
                    System.out.println("Lista de professores disponíveis:");
                    Professor professorLista = new Professor();
                    professorLista.listarProfessores();
                    System.out.println("Digite o código do novo professor para a turma: ");
                    int codigoProfessor = scanner.nextInt();

                    Professor novoProfessor = professorLista.buscarProfessor(codigoProfessor);

                    if (novoProfessor == null) {
                        System.out.println("Código de professor inválido. Por favor, tente novamente.");
                        validacaoProfessores = false;
                    } else {
                        turma.getProfessores().clear();
                        turma.getProfessores().add(novoProfessor);
                        validacaoProfessores = true;
                    }
                }
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
        scanner.close();
        System.out.println("Turma editada com sucesso.");
        // atualiza o arquivo turmas.txt
        // Lê todo o conteúdo do arquivo para a memória
        BufferedReader reader2 = new BufferedReader(new FileReader("turmas.txt"));
        StringBuilder sb = new StringBuilder();
        String linha;
        while ((linha = reader2.readLine()) != null) {
            String[] campos = linha.split(";");
            if (Integer.parseInt(campos[1]) == codTurma) {
                // Atualiza a linha do professor que foi editado
                linha = turma.getCodTurma() + ";" + turma.getSemestre() + ";" + turma.getDiasAulaSemana() + ";"
                        + turma.getHorarios() + ";" + turma.getProfessores();
            }
            sb.append(linha).append("\n");
        }
        reader2.close();

        // Sobrescreve o arquivo original com o conteúdo atualizado
        BufferedWriter writer = new BufferedWriter(new FileWriter("turmas.txt"));
        writer.write(sb.toString());
        writer.close();

        return true;
    }

}
