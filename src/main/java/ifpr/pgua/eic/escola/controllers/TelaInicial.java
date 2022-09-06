package ifpr.pgua.eic.escola.controllers;

import ifpr.pgua.eic.escola.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class TelaInicial {

    @FXML
    private void mudarTelaProf(ActionEvent event) {
        App.pushScreen("PRINCIPAL_PROF");
    }

    @FXML
    private void mudarTelaCurso(ActionEvent event) {
        App.pushScreen("PRINCIPAL_CURSO");
    }

    @FXML
    private void mudarTelaAluno(ActionEvent event) {
        App.pushScreen("PRINCIPAL_ALUNO");
    }
}
