import java.util.ArrayList;
import java.util.List;

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
