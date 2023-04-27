import java.util.ArrayList;
public class Professor {
	private String nome;
	private String formacao;
	private String email;
	private int identificacao;
	private ArrayList<Turma> turma;
	
	public Professor(String nome, String formacao, String email, int identificacao, ArrayList<Turma> turma) {
		this.nome = nome;
		this.formacao = formacao;
		this.email = email;
		this.identificacao = identificacao;
		this.turma = turma;
	}

	public boolean cadastrarProfessor() {
		return false;
	}

	public boolean editarProfessor(Professor p) {
		return false;
	}

	public void verDados(Professor p) {

	}

	public ArrayList<Professor> listarProfessores() {
		return null;
	}

	public boolean excluirProfessor(Professor p) {
		return false;
	}

}
