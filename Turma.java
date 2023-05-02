import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Turma {
    private int codTurma;
    private List<Professor> professor;
    private int horario;
    private ComponenteCurricular componente;
    private static ArrayList<Turma> turmas = new ArrayList<Turma>();

    public Turma(int codTurma, List<Professor> professor, int horario, ComponenteCurricular componente) {
        this.codTurma = codTurma;
        this.professor = professor;
        this.horario = horario;
        this.componente = componente;
		turmas.add(this);
    }
	public Turma(String t) {
    }
    public static Turma buscarTurma(int codigoTurma){
		for(Turma t : turmas){
			if(t.getCodTurma() == codigoTurma){
				return t;
			}else{
				System.out.println("Nummero de identificação nao existe");
			}
		}
		return null;
	}
	
    @Override
	public String toString() {
		return "Turma [codigo Turma: " + codTurma + ", professor: " + professor + ", horario: " + horario + ", componente: "
				+ componente + "]";
	}
	public int getCodTurma() {
        return codTurma;
    }

    public void setCodTurma(int codTurma) {
        this.codTurma = codTurma;
    }

    public List<Professor> getProfessor() {
        return professor;
    }

    public void setProfessor(List<Professor> professor) {
        this.professor = professor;
    }

    public int getHorario() {
        return horario;
    }

    public void setHorario(int horario) {
        this.horario = horario;
    }

    public ComponenteCurricular getComponente() {
        return componente;
    }

    public void setComponente(ComponenteCurricular componente) {
        this.componente = componente;
    }

	public boolean editarTurma(int codigoTurma) {
		Turma turmas = buscarTurma(codigoTurma);
		if(turmas != null){
			System.out.println("O que você deseja editar?");
				System.out.println("1 - Codigo da turma");
				System.out.println("2 - Professor");
				System.out.println("3 - Horario");
				System.out.println("4 - Componente");
				System.out.println("5 - Cancelar");
				Scanner ler = new Scanner(System.in);
				int op;
				op = ler.nextInt();
				if(op == 1){
					turmas.setCodTurma(turmas.getCodTurma());
				}else if(op == 2){
					turmas.setProfessor(turmas.getProfessor());
				}else if(op == 3){
					turmas.setHorario(turmas.getHorario());
				}else if(op == 4){
					turmas.setComponente(turmas.getComponente());
				}else if(op == 5){
					System.out.println("Cancelado");
				}
				ler.close();
				return true;
		}
		return false;
	}

    public void verDadoDeUmaTurma(int codigoTurma) {
		Turma turmas = buscarTurma(codigoTurma);
		if(turmas != null){
			turmas.toString();
		}
	}
	
    public ArrayList<Turma> listarTodasAsTurmas() {
		return turmas;
	}

    public ArrayList<Turma> listarTurmasPorSemestre(int semestre) {
		ArrayList<Turma> turmasDoSemestre = new ArrayList<>();
		for (Turma turma : turmas) {
			if (turma.getComponente().getSemestre() == semestre) {
				turmasDoSemestre.add(turma);
			}
		}
		return turmasDoSemestre;
	}	

    public ArrayList<Turma> listarTurmasPorProfessor(int identificacaoProfessor) {
		ArrayList<Turma> turmasDoProfessor = new ArrayList<>();
		for (Turma turma : turmas) {
			for (Professor prof : turma.getProfessor()) {
				if (prof.getIdentificacao() == identificacaoProfessor) {
					turmasDoProfessor.add(turma);
					break;
				}
			}
		}
		return turmasDoProfessor;
	}
	

    public boolean excluirTurma(int codigoTurma) {
		if (buscarTurma(codigoTurma) != null) {
			for (int i = 0; i < turmas.size(); i++) {
				Turma turma = turmas.get(i);
				if (turma.getCodTurma() == codigoTurma) {
					turmas.remove(i);
					return true;
				}
			}
			return false;
		}
		return false;
	}
}