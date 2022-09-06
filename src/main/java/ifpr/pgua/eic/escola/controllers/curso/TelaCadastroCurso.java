package ifpr.pgua.eic.escola.controllers.curso;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.escola.models.Escola;
import ifpr.pgua.eic.escola.models.Professor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class TelaCadastroCurso implements Initializable{
    
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
    private ComboBox<Professor> comboProfessor;

    public TelaCadastroCurso(Escola escola) {
        this.escola = escola;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        comboProfessor.getItems().clear();
        comboProfessor.getItems().addAll(escola.listarProfessores());
    }

    @FXML
    private void cadastrar() {
        
        try {
            int codigo = Integer.parseInt(campoCodigo.getText());
            String nome = campoNome.getText();
            String descricao = campoDescricao.getText();
            int cargaHoraria = Integer.parseInt(campoCargaHoraria.getText());
            Professor professor = comboProfessor.getSelectionModel().getSelectedItem();
    
            escola.cadastrarCurso(codigo, nome, descricao, cargaHoraria, professor);
            clear();
            Alert alert = new Alert(AlertType.INFORMATION, "Curso Cadastrado!!");
            alert.showAndWait();

        } catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.ERROR, "O Código precisa ser um número!!");
            alert.showAndWait();
        }
    }

    @FXML
    private void clear() {
        campoCodigo.clear();
        campoNome.clear();
        campoDescricao.clear();
        campoCargaHoraria.clear();
        comboProfessor.getSelectionModel().clearSelection();
    }
}
