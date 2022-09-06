package ifpr.pgua.eic.escola.controllers.curso;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.escola.models.Curso;
import ifpr.pgua.eic.escola.models.Escola;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class TelaListaCurso implements Initializable{

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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ltvCurso.getItems().clear();
        ltvCurso.getItems().addAll(escola.listarCursos());        
    }

    @FXML
    private void buscarCurso(ActionEvent event) {
        String filtro = campoFiltro.getText();
        if(!filtro.equals("")) {
            Curso curso = escola.buscarCurso(filtro);
            ltvCurso.getItems().clear();
            if(curso != null) {
                ltvCurso.getItems().add(curso);
            } 
        } else {
            ltvCurso.getItems().clear();
            ltvCurso.getItems().addAll(escola.listarCursos());
        }
    }

    @FXML
    private void mostrarDetalhes(MouseEvent event) {
        Curso curso = ltvCurso.getSelectionModel().getSelectedItem();
        
        if(curso != null) {
            taDetalhes.clear();
            taDetalhes.appendText("Código: " + curso.getCodigo());
            taDetalhes.appendText("\nNome: " + curso.getNome());
            taDetalhes.appendText("\nDescrição: " + curso.getDescricao());
            taDetalhes.appendText("\nCarga Horária: " + curso.getCargaHoraria());
            taDetalhes.appendText("\nProfessor: " + curso.getProfessor().getNome());
        }
    }
}
