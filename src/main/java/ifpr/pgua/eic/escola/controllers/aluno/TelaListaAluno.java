package ifpr.pgua.eic.escola.controllers.aluno;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.escola.models.Aluno;
import ifpr.pgua.eic.escola.models.Escola;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class TelaListaAluno implements Initializable {

    private Escola escola;

    @FXML
    private TextField campoFiltro;

    @FXML
    private ListView<Aluno> ltvAluno;

    @FXML
    private TextArea taDetalhes;

    public TelaListaAluno(Escola escola) {
        this.escola = escola;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ltvAluno.getItems().clear();
        ltvAluno.getItems().addAll(escola.listarAlunos());
    }

    @FXML
    private void buscarAluno(ActionEvent event) {
        String filtro = campoFiltro.getText();
        if(!filtro.equals("")) {
            Aluno aluno = escola.buscarAlunoCpf(filtro);
            ltvAluno.getItems().clear();
            if(aluno != null) {
                ltvAluno.getItems().add(aluno);
            }
        } else {
            ltvAluno.getItems().clear();
            ltvAluno.getItems().addAll(escola.listarAlunos());
        }
    }

    @FXML
    public void mostrarDetalhes(MouseEvent event) {
        Aluno aluno = ltvAluno.getSelectionModel().getSelectedItem();

        if (aluno != null) {
            taDetalhes.clear();
            taDetalhes.appendText("Nome: " + aluno.getNome());
            taDetalhes.appendText("\nCPF: " + aluno.getCpf());
            taDetalhes.appendText("\nEmail: " + aluno.getEmail());
            taDetalhes.appendText("\nTelefone: " + aluno.getTelefone());
            taDetalhes.appendText("\nData de Matricula: " + aluno.getDataMatricula());
        }
    }

}
