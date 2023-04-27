import java.util.ArrayList;
import java.util.List;

public class Turma {

	private int codTurma;
	private List<Professor> professor;
	private int horario;
	private ComponenteCurricular componente;
	
	public Turma(int codTurma, List<Professor> professor, int horario, ComponenteCurricular componente) {
		this.codTurma = codTurma;
		this.professor = professor;
		this.horario = horario;
		this.componente = componente;
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

	public boolean cadastrarTurma() {
		return false;
	}

	public boolean editarTurna(Turma p) {
		return false;
	}

	public void verDadoDeUmaTurma(Turma p) {

	}

	public ArrayList<Turma> listarTodasAsTurmas() {
		return null;
	}

	public ArrayList<Turma> listarTurmasPorSemestre(int semestre) {
		return null;
	}

	public ArrayList<Turma> listarTurmasPorProfessor(int identificacaoProfessor) {
		return null;
	}

	public boolean excluirTurma(Turma p) {
		return false;
	}

    public String getNome() {
        return null;
    }

}
