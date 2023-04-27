import java.util.ArrayList;

public class Professor {
	private String nome;
	private String formacao;
	private String email;
	private int identificacao;
	private ArrayList<Professor> professores;
	private ArrayList<Turma> turma;

	public Professor(String nome, String formacao, String email, int identificacao, ArrayList<Turma> turma) {
		this.nome = nome;
		this.formacao = formacao;
		this.email = email;
		this.identificacao = identificacao;
		this.turma = turma;
		this.professores = new ArrayList<Professor>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFormacao() {
		return formacao;
	}

	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(int identificacao) {
		this.identificacao = identificacao;
	}

	public ArrayList<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(ArrayList<Professor> professores) {
		this.professores = professores;
	}

	public ArrayList<Turma> getTurma() {
		return turma;
	}

	public void setTurma(ArrayList<Turma> turma) {
		this.turma = turma;
	}

	public boolean cadastrarProfessor(Professor professor) {
		if (professor != null) {
			professores.add(professor);
			return true;
		}
		return false;
	}

	public boolean editarProfessor(Professor p) {
		for (int i = 0; i < professores.size(); i++) {
			Professor professor = professores.get(i);
			if (professor.getIdentificacao() == p.getIdentificacao()) {
				professor.setNome(p.getNome());
				professor.setFormacao(p.getFormacao());
				professor.setEmail(p.getEmail());
				professor.setTurma(p.getTurma());
				return true;
			}
		}
		return false;
	}
	public void verDados(Professor p) {
		System.out.println("Nome: " + p.getNome());
		System.out.println("Formação: " + p.getFormacao());
		System.out.println("Email: " + p.getEmail());
		System.out.println("Identificação: " + p.getIdentificacao());
		System.out.println("Turmas:");
		for (Turma t : p.getTurma()) {
			System.out.println("- " + t.getNome());
		}
	}
	
	public ArrayList<Professor> listarProfessores() {
		return this.professores;
	}
	
	public boolean excluirProfessor(Professor p) {
		for (int i = 0; i < professores.size(); i++) {
			Professor professor = professores.get(i);
			if (professor.getIdentificacao() == p.getIdentificacao()) {
				professores.remove(i);
				return true;
			}
		}
		return false;
	}	

}
