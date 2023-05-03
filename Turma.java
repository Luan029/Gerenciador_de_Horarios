import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Turma {
    private int codTurma;
    private List<Professor> professor;
    private int horario;
    private ComponenteCurricular componente;
	private int semestre;
	private static PostgreSQLConnection db = PostgreSQLConnection.getInstance();
    private static ArrayList<Turma> turmas;
	static {
		turmas = new ArrayList<>();
	}
	public Turma(){
		this.codTurma = 0;
		this.horario = 0;
		this.componente = null;
		this.professor = new ArrayList<>();
	}
    public Turma(int codTurma, List<Professor> professor, int horario, int semestre, ComponenteCurricular componente) {
        this.codTurma = codTurma;
        this.professor = professor;
        this.horario = horario;
        this.componente = componente;
		this.semestre = semestre;
		try{
			String query = "INSERT INTO turmas (codTurma, horario, componente) VALUES(?,?,?)";
			PreparedStatement ps = db.getConnection().prepareStatement(query);
			ps.setInt(1, codTurma);
			ps.setObject(2, professor);
			ps.setInt(3, horario);
			ps.setInt(4, semestre);
			ps.setObject(5, componente);
			
			ps.executeUpdate();
		}catch (SQLException e) {
			System.err.println("Error inserting data into database: " + e.getMessage());
		}
    }
	public Turma(String t) {
    }
	public static Turma buscarTurma(int codigoTurma) {
		try {
			String query = "SELECT * FROM turmas WHERE codTurma = ?";
			PreparedStatement ps = db.getConnection().prepareStatement(query);
			ps.setInt(1, codigoTurma);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int horario = rs.getInt("horario");
				int semestre = rs.getInt("smestre");
				ComponenteCurricular componente = (ComponenteCurricular) rs.getObject("componente");
				List<Professor> professores = new ArrayList<>();
				// busca os professores da turma
				String queryProfessores = "SELECT * FROM turmas_professores WHERE codTurma = ?";
				PreparedStatement psProfessores = db.getConnection().prepareStatement(queryProfessores);
				psProfessores.setInt(1, codigoTurma);
				ResultSet rsProfessores = psProfessores.executeQuery();
				while (rsProfessores.next()) {
					int codProfessor = rsProfessores.getInt("codProfessor");
					Professor professor = Professor.buscarProfessor(codProfessor);
					if (professor != null) {
						professores.add(professor);
					}
				}
				return new Turma(codigoTurma, professores, horario, semestre, componente);
			}
		} catch (SQLException e) {
			System.err.println("Error searching for turma in database: " + e.getMessage());
		}
		return null;
	}
	public static boolean verificarHorario(int codigoTurma, int semestre, int horario) {
		try {
			String query = "SELECT * FROM turmas WHERE semestre = ? AND horario = ? AND codTurma != ?";
			PreparedStatement ps = db.getConnection().prepareStatement(query);
			ps.setInt(1, semestre);
			ps.setInt(2, horario);
			ps.setInt(3, codigoTurma);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				// encontrou outra turma no mesmo semestre com o mesmo horário
				return true;
			} else {
				// não encontrou nenhuma outra turma no mesmo semestre com o mesmo horário
				return false;
			}
		} catch (SQLException e) {
			System.err.println("Error executing SQL query: " + e.getMessage());
			return false;
		}
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

    public int getSemestre() {
		return semestre;
	}
	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}
	public static PostgreSQLConnection getDb() {
		return db;
	}
	public static void setDb(PostgreSQLConnection db) {
		Turma.db = db;
	}
	public static ArrayList<Turma> getTurmas() {
		return turmas;
	}
	public static void setTurmas(ArrayList<Turma> turmas) {
		Turma.turmas = turmas;
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
				System.out.println("4 - Cancelar");
				Scanner ler = new Scanner(System.in);
				int op;
				op = ler.nextInt();
				if(op == 1){
					System.out.println("Informe o novo codigo");
					int novoCod;
					novoCod = ler.nextInt();
					turmas = buscarTurma(novoCod);
					//Vefifica se existe outra turma com o mesmo codigo
					if(turmas == null){
						turmas.setCodTurma(novoCod);
						System.out.println("Codigo Atualizado!");
					}else{
						System.out.println("Ja existe outra turma com esse codigo.");
					}

				}else if(op == 2){
					System.out.println("Digite o identificador do professor");
					int idProf;
					idProf = ler.nextInt();
					Professor professor = Professor.buscarProfessor(idProf);
					if(professor != null){
						boolean ocupado = false;
						for (Turma t : Turma.turmas) {
							if (t.getProfessor() != null && t.getHorario() == turmas.getHorario() && t.getProfessor().contains(professor)) {
								ocupado = true;
								break;
							}
						}
						if (!ocupado) {
							turmas.getProfessor().clear();
							turmas.getProfessor().add(professor);
							System.out.println("Professor atualizado!");
						} else {
							System.out.println("Professor já está ocupado nesse horário!");
						}
					} else {
						System.out.println("Professor não encontrado!");
					}
				}else if(op == 3){
					System.out.println("Digite o novo horário:");
					int novoHorario = ler.nextInt();
					// verifica se existe outra turma no mesmo semestre com o mesmo horário e atualiza o horário da turma no banco de dados
					if (verificarHorario(codigoTurma, turmas.getSemestre(), novoHorario)) {
						System.out.println("Já existe uma turma no mesmo semestre com o mesmo horário.");
						
					} else {
						try {
							String query = "UPDATE turmas SET horario = ? WHERE codTurma = ?";
							PreparedStatement ps = db.getConnection().prepareStatement(query);
							ps.setInt(1, novoHorario);
							ps.setInt(2, codigoTurma);
							ps.executeUpdate();
							turmas.setHorario(novoHorario);
							System.out.println("Horário atualizado com sucesso.");
							return true;
						} catch (SQLException e) {
							System.err.println("Error updating data in database: " + e.getMessage());
						}
						
					}
				}else if(op == 4){
					System.out.println("Cancelado");
				}
				ler.close();
				try{
					String query = "UPDATE turmas SET professor = ?, horario = ? codTurma = ? WHERE codigoTurma = ?";
					PreparedStatement ps = db.getConnection().prepareStatement(query);
					ps.setObject(1, turmas.getProfessor());
					ps.setInt(2, turmas.getHorario());
					ps.setInt(3, turmas.getCodTurma());
					ps.executeUpdate();
					System.out.println("Turma atualizada com sucesso!");
					return true;
				} catch (SQLException e) {
					System.err.println("Error updating data in database: " + e.getMessage());
					return false;
				}
		}else{
			return false;
		}
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
		try{
			String query = "DELETE FROM turmas WHERE codigoTurma = ?";
			PreparedStatement ps = db.getConnection().prepareStatement(query);
			ps.setInt(1, codigoTurma);
			int result = ps.executeUpdate();
			if (result > 0) {
				return true;
			} else {
				System.out.println("Número de identificação não existe");
				return false;
			}
		}catch (SQLException e) {
			System.err.println("Error deleting data from database: " + e.getMessage());
			return false;
		}
	}	
}