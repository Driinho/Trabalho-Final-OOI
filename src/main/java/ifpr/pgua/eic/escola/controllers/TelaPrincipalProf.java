package ifpr.pgua.eic.escola.controllers;

import ifpr.pgua.eic.escola.App;
import ifpr.pgua.eic.escola.utils.BorderPaneRegion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class TelaPrincipalProf {

    @FXML
    private void mudarTelaCadastro(ActionEvent event) {
        App.changeScreenRegion("CADASTRO_PROF", BorderPaneRegion.CENTER);
    }

    @FXML
    private void mudarTelaLista(ActionEvent event) {
        App.changeScreenRegion("LISTA_PROF", BorderPaneRegion.CENTER);
    }

    @FXML
    private void mudarTelaInicial(ActionEvent event) {
        App.popScreen();
    }
}
