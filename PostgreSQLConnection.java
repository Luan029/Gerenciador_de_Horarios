import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLConnection {
    private static PostgreSQLConnection instance; // Instancia de Banco de Dados
    private Connection connection; // Conectar o Banco

    private final String url = "jdbc:postgresql://projetopoo.cp2q0ntxu3y8.us-east-1.rds.amazonaws.com:5432/dbpoo"; // Url do Banco
    private final String username = "ProjetoPOO"; // Username do Banco
    private final String password = "projeto123"; // Senha do Banco

    // Cria uma conexão com o Banco de Dados
    PostgreSQLConnection() {
        try { // Tenta Conectar
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException e) { // Se não conseguir da erro
            System.err.println("Error connecting to database: " + e.getMessage());
        }
    }

    public static synchronized PostgreSQLConnection getInstance() {
        if (instance == null) {
            instance = new PostgreSQLConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Conexão fechada com sucesso.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }
}
/*create table componente_curricular
	(id_comp serial,
	nome varchar(50) not null,
	carga_horaria int,
	semestre int,
	codigo_componente char(10) not null,
	obrigatorio boolean,
	turmas int,
	primary key(id_comp))
	
create table professor
	(id_prof serial,
	nome varchar(50) not null,
	formação varchar(50),
	email varchar(50),
	codigo_identificação char(10) not null,
	primary key(id_prof)) */