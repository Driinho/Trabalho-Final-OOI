package ifpr.pgua.eic.escola.controllers;

import ifpr.pgua.eic.escola.models.Escola;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class TelaCadastroProf {

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
    private TextField campoSalario;

    public TelaCadastroProf(Escola escola) {
        this.escola = escola;
    }

    @FXML
    private void cadastrar(ActionEvent event) {
        String nome = campoNome.getText();
        String cpf = campoCpf.getText();
        String email = campoEmail.getText();
        String telefone = campoTelefone.getText();
        double salario;
        try {

            salario = Double.parseDouble(campoSalario.getText());
            if (escola.cadastrarProfessor(cpf, nome, email, telefone, salario)) {
                Alert alert = new Alert(AlertType.INFORMATION, "PROFESSOR CADASTRADO!");
                alert.showAndWait();
                clear();
            } else {
                Alert alert = new Alert(AlertType.ERROR, "ERRO PROFESSOR NÂO CADASTRADO!");
                alert.showAndWait();
            }

        } catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.ERROR, "DIGITE UM NUMERO NO SALARIO!");
            alert.showAndWait();
        }
    }

    @FXML
    private void clear() {
        campoNome.clear();
        campoCpf.clear();
        campoEmail.clear();
        campoTelefone.clear();
        campoSalario.clear();
    }
}
