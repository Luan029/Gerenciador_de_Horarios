import java.util.ArrayList;

public class Professor {
	private String nome;
	private String formacao;
	private String email;
	private int identificacao;
	private static ArrayList<Professor> professores = new ArrayList<Professor>();
	private ArrayList<Turma> turma;

	public Professor(String nome, String formacao, String email, int identificacao, ArrayList<Turma> turma) {
		this.nome = nome;
		this.formacao = formacao;
		this.email = email;
		this.identificacao = identificacao;
		this.turma = turma;
		professores.add(this);
	}
	public static Professor buscarProfessor(int identificacao) {
		for (Professor p : professores) {
			if (p.getIdentificacao() == identificacao) {
				
				return p;
			}else{
				System.out.println("Nummero de identificação nao existe");
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		return "Professor [nome: " + nome + ", formacao: " + formacao + ", email: " + email + ", identificacao: "
				+ identificacao + ", turma: " + turma + "]";
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
		Professor.professores = professores;
	}

	public ArrayList<Turma> getTurma() {
		return turma;
	}

	public void setTurma(ArrayList<Turma> turma) {
		this.turma = turma;
	}

	public boolean editarProfessor(int identificador) {	
		Professor professor = buscarProfessor(identificador);
			if (professor != null) {
				professor.setNome(professor.getNome());
				professor.setFormacao(professor.getFormacao());
				professor.setEmail(professor.getEmail());
				professor.setTurma(professor.getTurma());
				return true;
			}	
		return false;
	}
	public static void verDados(int identificador) {
		Professor professor = buscarProfessor(identificador);
		if (professor!= null) {
			professor.toString();
		}	
	}
	
	public ArrayList<Professor> listarProfessores() {
		return Professor.professores;
	}
	
	public boolean excluirProfessor(int identificador) {
		if (buscarProfessor(identificador) != null) {
			for (int i = 0; i < professores.size(); i++) {
				Professor professor = professores.get(i);
				if (professor.getIdentificacao() == identificador) {
					professores.remove(i);
					return true;
				}
			}
			return false;
		}
		return false;
	}	

}
