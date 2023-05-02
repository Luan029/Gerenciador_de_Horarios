import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Professor {
	private String nome;
	private String formacao;
	private String email;
	private int identificacao;
	private ArrayList<Turma> turmas; // lista de turmas do professor
	private int turma;
	private static PostgreSQLConnection db = PostgreSQLConnection.getInstance();
	private static ArrayList<Professor> professores;
	
	static {
		professores = new ArrayList<>();
	}
	public Professor() {
		this.nome = "";
		this.formacao = "";
		this.email = "";
		this.identificacao = 0;
		this.turmas = new ArrayList<Turma>();
	}

	public Professor(String nome, String formacao, String email, int identificacao, ArrayList<Turma> turmas) {
		this.nome = nome;
		this.formacao = formacao;
		this.email = email;
		this.identificacao = identificacao;
		this.turmas = turmas; // inicializa a lista de turmas
		try {
			String query = "INSERT INTO professores (nome, formacao, email, identificacao) VALUES (?, ?, ?, ?)";
			PreparedStatement ps = db.getConnection().prepareStatement(query);
			ps.setString(1, nome);
			ps.setString(2, formacao);
			ps.setString(3, email);
			ps.setInt(4, identificacao);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error inserting data into database: " + e.getMessage());
		}
	}

	public static Professor buscarProfessor(int identificacao) {
		try {
			String query = "SELECT * FROM professores WHERE identificacao = ?";
			PreparedStatement ps = db.getConnection().prepareStatement(query);
			ps.setInt(1, identificacao);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String nome = rs.getString("nome");
				String formacao = rs.getString("formacao");
				String email = rs.getString("email");
				ArrayList<Turma> turma = new ArrayList<Turma>();
				// buscar turmas relacionadas ao professor
				Professor professor = new Professor(nome, formacao, email, identificacao, turma);
				return professor;
			} else {
				System.out.println("Número de identificação não existe");
				return null;
			}
		} catch (SQLException e) {
			System.err.println("Error querying database: " + e.getMessage());
			return null;
		}
	}

	@Override
	public String toString() {
		return "Professor [nome: " + nome + ", formacao: " + formacao + ", email: " + email + ", identificacao: "
				+ identificacao + ", turma: " + getTurmaValue() + "]";
	}

	public String getNome() {
		return nome;
	}
	
	public List<Turma> getTurmas() {
		return turmas;
	}
	
	public void setTurmas(String string) {
		this.turmas = new ArrayList<Turma>();
		String[] turmas = string.split(",");
		for (String t : turmas) {
			this.turmas.add(new Turma(t));
		}
	}
	
	
	public static PostgreSQLConnection getDb() {
		return db;
	}
	
	public static void setDb(PostgreSQLConnection db) {
		Professor.db = db;
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
	
	public static List<Professor> getProfessores() {
		return professores;
	}
	
	public void setProfessores(List<Professor> professores) {
		Professor.professores = (ArrayList<Professor>) professores;
	}
	
	public int getTurmaValue() {
		return turma;
	}
	
	public void setTurmaValue(int i) {
		this.turma = i;
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
			int op = ler.nextInt();
			String novoValor = null;
			if (op == 1) {
				System.out.println("Digite o novo nome:");
				novoValor = ler.next();
				professor.setNome(novoValor);
			} else if (op == 2) {
				System.out.println("Digite a nova formação:");
				novoValor = ler.next();
				professor.setFormacao(novoValor);
			} else if (op == 3) {
				System.out.println("Digite o novo email:");
				novoValor = ler.next();
				professor.setEmail(novoValor);
			} else if (op == 4) {
				System.out.println("Digite o identificador da turma:");
				int idTurma = ler.nextInt();
				Turma turma = Turma.buscarTurma(idTurma);
				if (turma != null) {
					professor.setTurmaValue(turma.getCodTurma());
				} else {
					System.out.println("Turma não encontrada");
				}
			} else {
				ler.close();
				return false;
			}
			try {
				String query = "UPDATE professores SET nome = ?, formacao = ?, email = ?, turma = ? WHERE identificacao = ?";
				PreparedStatement ps = db.getConnection().prepareStatement(query);
				ps.setString(1, professor.getNome());
				ps.setString(2, professor.getFormacao());
				ps.setString(3, professor.getEmail());
				ps.setInt(4, professor.getTurmaValue());
				ps.setInt(5, professor.getIdentificacao());
				ps.executeUpdate();
				System.out.println("Professor atualizado com sucesso!");
				return true;
			} catch (SQLException e) {
				System.err.println("Error updating data in database: " + e.getMessage());
				return false;
			}
		} else {
			return false;
		}
	}

	public static void verDados(int identificador) {
		Professor professor = buscarProfessor(identificador);
		if (professor != null) {
			professor.toString();
		}
	}

	public ArrayList<Professor> listarProfessores() {
		return Professor.professores;
	}

	public boolean excluirProfessor(int identificador) {
		try {
			String query = "DELETE FROM professores WHERE identificacao = ?";
			PreparedStatement ps = db.getConnection().prepareStatement(query);
			ps.setInt(1, identificador);
			int result = ps.executeUpdate();
			if (result > 0) {
				return true;
			} else {
				System.out.println("Número de identificação não existe");
				return false;
			}
		} catch (SQLException e) {
			System.err.println("Error deleting data from database: " + e.getMessage());
			return false;
		}
	}
}