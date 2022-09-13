package ifpr.pgua.eic.escola.controllers;

import ifpr.pgua.eic.escola.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class TelaInicial extends App {

    @FXML
    private void mudarTelaProf(ActionEvent event) {
        App.pushScreen("PRINCIPAL_PROF");
        atualizaEstilo();
    }

    @Override
    public void atualizaEstilo() {
        // removeArquivoEstilo(getClass().getResource("../style/style-principal.css").toExternalForm());
        // adicionarArquivoEstilo(getClass().getResource("../style/style-aux.css").toExternalForm());
    }

    @FXML
    private void mudarTelaCurso(ActionEvent event) {
        App.pushScreen("PRINCIPAL_CURSO");
        atualizaEstilo();
    }

    @FXML
    private void mudarTelaAluno(ActionEvent event) {
        App.pushScreen("PRINCIPAL_ALUNO");
        atualizaEstilo();
    }
}
