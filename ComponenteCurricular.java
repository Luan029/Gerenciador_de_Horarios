import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ComponenteCurricular {
    private boolean obrigatorio;
    private int semestre;
    private int cargaHoraria;
    private String nome;
    private int codComponente;
    private List<Turma> turmas;
    private int turma;
    private static PostgreSQLConnection db = PostgreSQLConnection.getInstance();
    private static ArrayList<ComponenteCurricular> componente;

    static{
        componente = new ArrayList<>();
    }
    public ComponenteCurricular(){
        this.obrigatorio = false;
        this.cargaHoraria = 0;
        this.semestre = 0;
        this.nome = "";
        this.codComponente = 0;
        this.turmas = null;
    }
    public ComponenteCurricular(boolean obrigatorio, int semestre, int cargaHoraria, String nome, int codComponente,
            List<Turma> turmas) {
        this.obrigatorio = obrigatorio;
        this.semestre = semestre;
        this.cargaHoraria = cargaHoraria;
        this.nome = nome;
        this.turmas = turmas;
        this.codComponente = codComponente;
        try{
            String query = "INSERT INTO componente(obrigatorio, semestre, cargaHoraria, nome, codComponente) VALUES(?,?,?,?,?)";
            PreparedStatement ps = db.getConnection().prepareStatement(query);
            ps.setInt(2, semestre);
            ps.setInt(3, cargaHoraria);
            ps.setBoolean(1, obrigatorio);
            ps.setString(4, nome);
            ps.setInt(5, codComponente);
            ps.executeUpdate();
        }catch(SQLException e){
            System.err.println("Error inserting data into database: " + e.getMessage());
        }
    }

    public static ComponenteCurricular buscarComponente(int codigoComponente) {
        try{
            String query = "SELECT * FFROM componente WHERE codigoComponente = ?";
            PreparedStatement ps = db.getConnection().prepareStatement(query);
            ps.setInt(1, codigoComponente);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String nome = rs.getString("nome");
                Boolean obrigatorio = rs.getBoolean("obrigatorio");
                int semestre = rs.getInt("semestre");
                int cargaHoraria = rs.getInt("cargaHoraria");
                ArrayList<Turma> turma = new ArrayList<Turma>();
                ComponenteCurricular componente = new ComponenteCurricular(obrigatorio, semestre, cargaHoraria, nome, codigoComponente, turma);

                return componente;
            }else{
                System.out.println("Número de identificação não existe");
				return null;
            }
        }catch (SQLException e) {
            System.err.println("Error querying database: " + e.getMessage());
            return null;
        }
       
    }

    @Override
    public String toString() {
        return "ComponenteCurricular [obrigatorio: " + obrigatorio + ", semestre: " + semestre + ", carga Horaria: "
                + cargaHoraria + ", nome: " + nome + ", codigo do Componente: " + codComponente + ", turmas: " + turmas
                + "]";
    }
    
    public int getCodComponente() {
        return codComponente;
    }

    public void setCodComponente(int codComponente) {
        this.codComponente = codComponente;
    }

    public static ArrayList<ComponenteCurricular> getComponente() {
        return componente;
    }

    public static void setComponente(ArrayList<ComponenteCurricular> componente) {
        ComponenteCurricular.componente = componente;
    }

    public boolean isObrigatorio() {
        return obrigatorio;
    }

    public void setObrigatorio(boolean obrigatorio) {
        this.obrigatorio = obrigatorio;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public int getHorasAulaPorSemana() {
        return cargaHoraria / 30 * 2;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }
    public int getTurmaValue() {
		return turma;
	}
	
	public void setTurmaValue(int i) {
		this.turma = i;
	}
    public static PostgreSQLConnection getDb() {
        return db;
    }
    public static void setDb(PostgreSQLConnection db) {
        ComponenteCurricular.db = db;
    }

    public boolean editarComponente(int codigoComponente) {
        ComponenteCurricular componente = buscarComponente(codigoComponente);
        if (componente != null) {
            System.out.println("O que você deseja editar?");
            System.out.println("1 - Nome");
            System.out.println("2 - Se é obrigatorio ou não");
            System.out.println("3 - Semestre");
            System.out.println("4 - Carga horaria");
            System.out.println("5 - Turmas");
            System.out.println("6 - Cancelar");
            Scanner ler = new Scanner(System.in);
            int op;
            op = ler.nextInt();
            if (op == 1) {
                System.out.println("Digite o novo nome:");
                String nome = ler.next();
                componente.setNome(nome);
            } else if (op == 2) {
                System.out.println("O componente curricular é obrigatório? (s/n)");
                String resposta = ler.next();
                boolean obrigatorio = resposta.equalsIgnoreCase("s");
                componente.setObrigatorio(obrigatorio);
            } else if (op == 3) {
                System.out.println("Digite o novo semestre:");
                int semestre = ler.nextInt();
                componente.setSemestre(semestre);
            } else if (op == 4) {
                System.out.println("Digite a nova carga horária (em horas):");
                int cargaHoraria = ler.nextInt();
                componente.setCargaHoraria(cargaHoraria);
            } else if (op == 5) {
                System.out.println("Digite o identificador da turma:");
				int idTurma = ler.nextInt();
				Turma turma = Turma.buscarTurma(idTurma);
				if (turma != null) {
					componente.setTurmaValue(turma.getCodTurma());
				} else {
					System.out.println("Turma não encontrada");
				}
            } else if (op == 6) {
                System.out.println("Cancelado");
            }ler.close();
            try{
                String query = "UPDATE componente SET nome = ?, obrigatorio = ?, semestre = ?, cargaHoraria = ?, turma = ? WHERE codigoComponente = ?";
                PreparedStatement ps = db.getConnection().prepareStatement(query);
                ps.setString(1,  componente.getNome());
                ps.setBoolean(2, componente.isObrigatorio());
                ps.setInt(3, componente.getSemestre());
                ps.setInt(4, componente.getCargaHoraria());
                ps.setInt(5, componente.getTurmaValue());
                ps.setInt(6, componente.getCodComponente());
                ps.executeUpdate();
                System.out.println("Componente atualizado com sucesso!");
				return true;
            }catch (SQLException e) {
				System.err.println("Error updating data in database: " + e.getMessage());
			}
        }
        return false;
    }

    public static void verDadosDeUmComponente(int codigoComponente) {
        ComponenteCurricular componente = buscarComponente(codigoComponente);
        if (componente != null) {
            componente.toString();
        }
    }
    public ArrayList<ComponenteCurricular> listarComponentes() {
        return ComponenteCurricular.componente;
    }

    public boolean excluirComponente(int codigoComponente) {
        try{
            String query = "DELETE FROM componente WHERE codigoComponente = ?";
            PreparedStatement ps = db.getConnection().prepareStatement(query);
            ps.setInt(1, codigoComponente);
            int result = ps.executeUpdate();
            if(result > 0 ){
                return true;
            }else{
                System.out.println("Número de identificação não existe");
				return false;
            }
        }catch (SQLException e) {
			System.err.println("Error deleting data from database: " + e.getMessage());
			return false;
		}
    }
    
}