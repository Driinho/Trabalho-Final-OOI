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
import javafx.scene.input.MouseEvent;

public class TelaCadastroCurso implements Initializable {

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
            Professor professor = comboProfessor.getValue();
            if (!validaCamposVazios()) {
                if (nome.contains(";") || descricao.contains(";")) {
                    Alert alert = new Alert(AlertType.WARNING, "NENHUM CAMPO PODE CONTER [ ; ]");
                    alert.showAndWait();
                } else if (escola.cadastrarCurso(codigo, nome, descricao, cargaHoraria, professor)) {
                    clear();
                    Alert alert = new Alert(AlertType.INFORMATION, "Curso Cadastrado!!");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(AlertType.ERROR, "CÓDIGO DE CURSO JA CADASTRADO!!");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(AlertType.ERROR, "PREENCHA TODOS OS CAMPOS CORRETAMENTE!!");
                alert.showAndWait();
            }

        } catch (NumberFormatException e) {
            campoCodigo.getStyleClass().add("erro");
            campoCargaHoraria.getStyleClass().add("erro");
            validaCamposVazios();
            Alert alert = new Alert(AlertType.ERROR, "O Código e a Carga Horária precisam ser números!!");
            alert.showAndWait();
        }
    }

    @FXML
    private void limpaErro(MouseEvent event) {
        campoNome.getStyleClass().remove("erro");
        campoCodigo.getStyleClass().remove("erro");
        campoCodigo.getStyleClass().remove("erro");
        campoDescricao.getStyleClass().remove("erro");
        campoCargaHoraria.getStyleClass().remove("erro");
        campoCargaHoraria.getStyleClass().remove("erro");
        comboProfessor.getStyleClass().remove("erro");
    }

    private boolean validaCamposVazios() {
        boolean erro = false;
        if (campoNome.getText().isBlank()) {
            erro = true;
            campoNome.getStyleClass().add("erro");
        }
        if (campoCodigo.getText().isBlank()) {
            erro = true;
            campoCodigo.getStyleClass().add("erro");
        }
        if (campoDescricao.getText().isBlank()) {
            erro = true;
            campoDescricao.getStyleClass().add("erro");
        }
        if (campoCargaHoraria.getText().isBlank()) {
            erro = true;
            campoCargaHoraria.getStyleClass().add("erro");
        }
        if (comboProfessor.getSelectionModel().getSelectedItem() == null) {
            erro = true;
            comboProfessor.getStyleClass().add("erro");
        }
        return erro;
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
