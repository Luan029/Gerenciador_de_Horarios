import java.util.ArrayList;

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

}
