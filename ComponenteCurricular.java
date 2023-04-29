import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ComponenteCurricular {

    private boolean obrigatorio;
    private int semestre;
    private int cargaHoraria;
    private String nome;
    private int codComponente;
    private List<Turma> turmas;

    private static ArrayList<ComponenteCurricular> componente = new ArrayList<ComponenteCurricular>();

    public ComponenteCurricular(boolean obrigatorio, int semestre, int cargaHoraria, String nome, int codComponente,
            List<Turma> turmas) {
        this.obrigatorio = obrigatorio;
        this.semestre = semestre;
        this.cargaHoraria = cargaHoraria;
        this.nome = nome;
        this.turmas = turmas;
        this.codComponente = codComponente;
        componente.add(this);
    }

    public static ComponenteCurricular buscarComponente(int codigoComponente) {
        for (ComponenteCurricular c : componente) {
            if (c.getCodComponente() == codigoComponente) {

                return c;
            } else {
                System.out.println("Nummero de identificação nao existe");
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "ComponenteCurricular [obrigatorio: " + obrigatorio + ", semestre: " + semestre + ", carga Horaria: "
                + cargaHoraria + ", nome: " + nome + ", codigo do Componente: " + codComponente + ", turmas: " + turmas
                + "]";
    }

    public int getCodComponente() {
        return codComponente;
    }

    public void setCodComponente(int codComponente) {
        this.codComponente = codComponente;
    }

    public static ArrayList<ComponenteCurricular> getComponente() {
        return componente;
    }

    public static void setComponente(ArrayList<ComponenteCurricular> componente) {
        ComponenteCurricular.componente = componente;
    }

    public boolean isObrigatorio() {
        return obrigatorio;
    }

    public void setObrigatorio(boolean obrigatorio) {
        this.obrigatorio = obrigatorio;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    // public boolean cadastrarComponente(ComponenteCurricular componente,
    // ArrayList<ComponenteCurricular> listaComponentes) {
    // if (componente == null || listaComponentes == null) {
    // return false;
    // }

    // listaComponentes.add(componente);
    // return true;
    // }

    public boolean editarComponente(int codigoComponente) {
        ComponenteCurricular componente = buscarComponente(codigoComponente);
        if (componente != null) {
            System.out.println("O que você deseja editar?");
            System.out.println("1 - Nome");
            System.out.println("2 - Se é obrigatorio ou não");
            System.out.println("3 - Semestre");
            System.out.println("4 - Carga horaria");
            System.out.println("5 - Turmas");
            System.out.println("6 - Cancelar");
            Scanner ler = new Scanner(System.in);
            int op;
            op = ler.nextInt();
            if (op == 1) {
                componente.setNome(componente.getNome());
            } else if (op == 2) {
                componente.setObrigatorio(componente.isObrigatorio());
            } else if (op == 3) {
                componente.setSemestre(componente.getSemestre());
            } else if (op == 4) {
                componente.setCargaHoraria(componente.getCargaHoraria());
            } else if (op == 5) {
                componente.setTurmas(componente.getTurmas());
            } else if (op == 6) {
                System.out.println("Cancelado");
            }
            ler.close();
            return true;
        }
        return false;
    }

    public static void verDadosDeUmComponente(int codigoComponente) {
        ComponenteCurricular componente = buscarComponente(codigoComponente);
        if (componente != null) {
            componente.toString();
        }
    }
    // public ArrayList<String> verDadosDeUmComponente(String nomeComponente,
    // ArrayList<ComponenteCurricular> listaComponentes) {
    // for (ComponenteCurricular c : listaComponentes) {
    // if (c.getNome().equals(nomeComponente)) {
    // ArrayList<String> dadosComponente = new ArrayList<String>();
    // dadosComponente.add("Nome: " + c.getNome());
    // dadosComponente.add("Obrigatório: " + (c.isObrigatorio() ? "Sim" : "Não"));
    // dadosComponente.add("Semestre: " + c.getSemestre());
    // dadosComponente.add("Carga Horária: " + c.getCargaHoraria());

    // StringBuilder turmas = new StringBuilder();
    // for (Turma t : c.getTurmas()) {
    // turmas.append(t.getCodTurma()).append(", ");
    // }
    // if (turmas.length() > 0) {
    // turmas.setLength(turmas.length() - 2);
    // }
    // dadosComponente.add("Turmas: " + turmas.toString());

    // return dadosComponente;
    // }
    // }
    // return null;
    // }

    public ArrayList<ComponenteCurricular> listarComponentes(ArrayList<ComponenteCurricular> listaComponentes) {
        return listaComponentes;
    }

    public boolean excluirComponente(int codigoComponente) {
        if (buscarComponente(codigoComponente) != null) {
            for (int i = 0; i < componente.size(); i++) {
                ComponenteCurricular componentecCurricular = componente.get(i);
                if (componentecCurricular.getCodComponente() == codigoComponente) {
                    componente.remove(i);
                    return true;
                }
            }
            return false;
        }
        return false;

        // ComponenteCurricular componente = buscarComponente(codigoComponente);
        // if (componente != null) {
        // componente.remove();

        // }
        // if (componente == null || listaComponentes == null) {
        // return false;
        // }

        // return listaComponentes.remove(componente);
    }
    // lucas teste

}
