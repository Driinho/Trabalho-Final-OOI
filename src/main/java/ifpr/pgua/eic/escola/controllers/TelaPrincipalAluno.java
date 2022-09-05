package ifpr.pgua.eic.escola.controllers;

import ifpr.pgua.eic.escola.App;
import ifpr.pgua.eic.escola.utils.BorderPaneRegion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class TelaPrincipalAluno {

    @FXML
    private void mudarTelaCadastro(ActionEvent event) {
        App.changeScreenRegion("CADASTRO_ALUNO", BorderPaneRegion.CENTER);
    }

    @FXML
    private void mudarTelaLista(ActionEvent event) {
        App.changeScreenRegion("LISTA_ALUNO", BorderPaneRegion.CENTER);
    }

    @FXML
    private void mudarTelaInicio(ActionEvent event) {
        App.popScreen();
    }
}
