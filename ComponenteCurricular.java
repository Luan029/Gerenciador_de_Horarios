import java.util.ArrayList;

public class ComponenteCurricular {

	private boolean obrigatorio;
	private int semestre;
	private int cargaHoraria;
	private String nome;
	private List<Turma> turmas;
	
	public ComponenteCurricular(boolean obrigatorio, int semestre, int cargaHoraria, String nome, List<Turma> turmas) {
		this.obrigatorio = obrigatorio;
		this.semestre = semestre;
		this.cargaHoraria = cargaHoraria;
		this.nome = nome;
		this.turmas = turmas;
	}

	public boolean CadastrarComponente() {
		return false;
	}

	public boolean editarComponente(ComponenteCurricular p) {
		return false;
	}

	public ArrayList<ComponenteCurricular> verDadosDeUmComponente() {
		return null;
	}

	public ArrayList<ComponenteCurricular> listarComponente() {
		return null;
	}

	public boolean ExcluirComponente(ComponenteCurricular p) {
		return false;
	}

}
