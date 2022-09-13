package ifpr.pgua.eic.escola.controllers.professor;

import ifpr.pgua.eic.escola.models.Escola;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

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

        if (nome.contains(";") || cpf.contains(";") || email.contains(";") || telefone.contains(";")) {
            Alert alert = new Alert(AlertType.WARNING, "NENHUM CAMPO PODE CONTER [ ; ]");
            alert.showAndWait();
        } else {
            try {
                salario = Double.parseDouble(campoSalario.getText());
                if (!validaCamposVazios()) {
                    if (escola.cadastrarProfessor(cpf, nome, email, telefone, salario)) {
                        Alert alert = new Alert(AlertType.INFORMATION, "PROFESSOR CADASTRADO!");
                        alert.showAndWait();
                        clear();
                    } else {
                        Alert alert = new Alert(AlertType.ERROR, "ERRO PROFESSOR NÂO CADASTRADO!");
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(AlertType.ERROR, "PREENCHA TODOS OS CAMPOS CORRETAMENTE!!");
                    alert.showAndWait();
                }

            } catch (NumberFormatException e) {
                campoSalario.getStyleClass().add("erro");
                validaCamposVazios();
                Alert alert = new Alert(AlertType.ERROR, "DIGITE UM NUMERO NO SALARIO!");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void limpaErro(MouseEvent event) {
        campoCpf.getStyleClass().remove("erro");
        campoEmail.getStyleClass().remove("erro");
        campoNome.getStyleClass().remove("erro");
        campoTelefone.getStyleClass().remove("erro");
        campoSalario.getStyleClass().remove("erro");
        campoSalario.getStyleClass().remove("erro");
    }

    private boolean validaCamposVazios() {
        boolean erro = false;
        if (campoCpf.getText().isBlank()) {
            erro = true;
            campoCpf.getStyleClass().add("erro");
        }
        if (campoNome.getText().isBlank()) {
            erro = true;
            campoNome.getStyleClass().add("erro");
        }
        if (campoEmail.getText().isBlank()) {
            erro = true;
            campoEmail.getStyleClass().add("erro");
        }
        if (campoTelefone.getText().isBlank()) {
            erro = true;
            campoTelefone.getStyleClass().add("erro");
        }
        if (campoSalario.getText().isBlank()) {
            erro = true;
            campoSalario.getStyleClass().add("erro");
        }
        return erro;
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
