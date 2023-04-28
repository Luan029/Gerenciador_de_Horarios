import java.util.ArrayList;
import java.util.List;

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

    // public boolean cadastrarTurma(int codTurma, List<Professor> professor, int horario, ComponenteCurricular componente) {
    //     Turma turma = new Turma(codTurma, professor, horario, componente);
    //     return turmas.add(turma);
    // }

	public boolean editarTurma(Turma p) {
		for (Turma t : turmas) {
			if (t.getCodTurma() == p.getCodTurma()) {
				t.setProfessor(p.getProfessor());
				t.setHorario(p.getHorario());
				t.setComponente(p.getComponente());
				return true;
			}
		}
		return false;
	}

    public void verDadoDeUmaTurma(Turma p) {
		System.out.println("Código da turma: " + p.getCodTurma());
		System.out.println("Horário da turma: " + p.getHorario());
		System.out.println("Componente curricular: " + p.getComponente().getNome());
		System.out.println("Professores:");
		for (Professor prof : p.getProfessor()) {
			System.out.println("- " + prof.getNome());
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
	

    public boolean excluirTurma(Turma p) {
		for (Turma t : turmas) {
			if (t.equals(p)) {
				turmas.remove(t);
				return true;
			}
		}
		return false;
	}
	
    public String getNome() {
        return null;
    }
}
