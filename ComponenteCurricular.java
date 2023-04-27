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

    public boolean cadastrarComponente(ComponenteCurricular componente, ArrayList<ComponenteCurricular> listaComponentes) {
        if (componente == null || listaComponentes == null) {
            return false;
        }

        listaComponentes.add(componente);
        return true;
    }

    public boolean editarComponente(ComponenteCurricular componente, ArrayList<ComponenteCurricular> listaComponentes) {
        if (componente == null || listaComponentes == null) {
            return false;
        }

        for (ComponenteCurricular c : listaComponentes) {
            if (c.getNome().equals(componente.getNome())) {
                c.setObrigatorio(componente.isObrigatorio());
                c.setSemestre(componente.getSemestre());
                c.setCargaHoraria(componente.getCargaHoraria());
                c.setTurmas(componente.getTurmas());
                return true;
            }
        }

        return false;
    }

    public ArrayList<String> verDadosDeUmComponente(String nomeComponente, ArrayList<ComponenteCurricular> listaComponentes) {
		for (ComponenteCurricular c : listaComponentes) {
			if (c.getNome().equals(nomeComponente)) {
				ArrayList<String> dadosComponente = new ArrayList<String>();
				dadosComponente.add("Nome: " + c.getNome());
				dadosComponente.add("Obrigatório: " + (c.isObrigatorio() ? "Sim" : "Não"));
				dadosComponente.add("Semestre: " + c.getSemestre());
				dadosComponente.add("Carga Horária: " + c.getCargaHoraria());
	
				StringBuilder turmas = new StringBuilder();
				for (Turma t : c.getTurmas()) {
					turmas.append(t.getCodTurma()).append(", ");
				}
				if (turmas.length() > 0) {
					turmas.setLength(turmas.length() - 2);
				}
				dadosComponente.add("Turmas: " + turmas.toString());
	
				return dadosComponente;
			}
		}
		return null;
	}
	

    public ArrayList<ComponenteCurricular> listarComponentes(ArrayList<ComponenteCurricular> listaComponentes) {
		return listaComponentes;
	}
	
    public boolean excluirComponente(ComponenteCurricular componente, ArrayList<ComponenteCurricular> listaComponentes) {
		if (componente == null || listaComponentes == null) {
			return false;
		}
		
		return listaComponentes.remove(componente);
	}
	
}
