import java.util.ArrayList;
import java.util.Scanner;
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
				System.out.println("O que você deseja editar?");
				System.out.println("1 - Nome");
				System.out.println("2 - Formação");
				System.out.println("3 - Email");
				System.out.println("4 - Turma");
				System.out.println("5 - Cancelar");
				Scanner ler = new Scanner(System.in);
				int op;
				op = ler.nextInt();
				if(op == 1){
					professor.setNome(professor.getNome());
				}else if(op == 2){
					professor.setFormacao(professor.getFormacao());
				}else if(op == 3){
					professor.setEmail(professor.getEmail());
				}else if(op == 4){
					professor.setTurma(professor.getTurma());
				}else if(op == 5){
					System.out.println("Cancelado");
				}
				ler.close();
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