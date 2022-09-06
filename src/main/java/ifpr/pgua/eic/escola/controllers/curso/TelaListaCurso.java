package ifpr.pgua.eic.escola.controllers.curso;

import ifpr.pgua.eic.escola.models.Curso;
import ifpr.pgua.eic.escola.models.Escola;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class TelaListaCurso {

    private Escola escola;

    @FXML
    private TextField campoFiltro;

    @FXML
    private ListView<Curso> ltvCurso;

    @FXML
    private TextArea taDetalhes;

    public TelaListaCurso(Escola escola) {
        this.escola = escola;
    }

    @FXML
    private void buscarCurso(ActionEvent event) {

    }

    @FXML
    private void mostrarDetalhes(MouseEvent event) {

    }
}
