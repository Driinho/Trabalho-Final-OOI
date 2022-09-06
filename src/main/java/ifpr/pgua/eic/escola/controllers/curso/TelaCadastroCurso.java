package ifpr.pgua.eic.escola.controllers.curso;

import ifpr.pgua.eic.escola.models.Escola;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class TelaCadastroCurso {
    
    private Escola escola;

    @FXML
    private TextField campoCodigo;

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoDescricao;

    @FXML
    private TextField campoCargaHoraria;

    @FXML
    private TextField campoProfessor;

    public TelaCadastroCurso(Escola escola) {
        this.escola = escola;
    }

    @FXML
    private void cadastrar() {

    }

    @FXML
    private void clear() {
        campoCodigo.clear();
        campoNome.clear();
        campoDescricao.clear();
        campoCargaHoraria.clear();
        campoProfessor.clear();
    }
}
