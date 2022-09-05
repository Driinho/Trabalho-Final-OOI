package ifpr.pgua.eic.escola.controllers.professor;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.escola.models.Escola;
import ifpr.pgua.eic.escola.models.Professor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class TelaListaProf implements Initializable {

    private Escola escola;

    @FXML
    private TextField campoFiltro;

    @FXML
    private ListView<Professor> ltvProf;

    @FXML
    private TextArea taDetalhes;

    public TelaListaProf(Escola escola) {
        this.escola = escola;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ltvProf.getItems().clear();
        ltvProf.getItems().addAll(escola.listarProfessores());
    }

    @FXML 
    private void buscarProf(ActionEvent event) {
        String filtro = campoFiltro.getText();
        Professor professor = escola.buscarProfessorCpf(filtro);
        ltvProf.getItems().clear();
        if(professor != null) {
            ltvProf.getItems().add(professor);
        }
    }

    @FXML
    public void mostrarDetalhes(MouseEvent event) {
        Professor professor = ltvProf.getSelectionModel().getSelectedItem();

        if (professor != null) {
            taDetalhes.clear();
            taDetalhes.appendText("Nome: " + professor.getNome());
            taDetalhes.appendText("\nCPF: " + professor.getCpf());
            taDetalhes.appendText("\nEmail: " + professor.getEmail());
            taDetalhes.appendText("\nTelefone: " + professor.getTelefone());
            taDetalhes.appendText("\nSalario: R$" + professor.getSalario());
        }
    }
}
