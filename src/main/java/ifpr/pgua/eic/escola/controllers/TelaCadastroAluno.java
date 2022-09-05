package ifpr.pgua.eic.escola.controllers;

import java.time.LocalDate;

import ifpr.pgua.eic.escola.models.Escola;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class TelaCadastroAluno {

    private Escola escola;

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoCpf;

    @FXML
    private TextField campoEmail;

    @FXML
    private TextField campoTelefone;

    @FXML
    private DatePicker campoDataMatricula;

    public TelaCadastroAluno(Escola escola) {
        this.escola = escola;
    }

    @FXML
    private void cadastrar() {
        String nome = campoNome.getText();
        String cpf = campoCpf.getText();
        String email = campoEmail.getText();
        String telefone = campoTelefone.getText();
        LocalDate dataMatricula = campoDataMatricula.getValue();

        if (escola.cadastrarAluno(cpf, nome, email, telefone, dataMatricula)) {
            Alert alert = new Alert(AlertType.INFORMATION, "ALUNO CADASTRADO!");
            alert.showAndWait();
            clear();
        } else {
            Alert alert = new Alert(AlertType.ERROR, "ERRO PROFESSOR NÂO CADASTRADO!");
            alert.showAndWait();
        }
    }

    @FXML
    private void clear() {
        campoNome.clear();
        campoCpf.clear();
        campoEmail.clear();
        campoTelefone.clear();
        campoDataMatricula.getEditor().clear();
    }
}