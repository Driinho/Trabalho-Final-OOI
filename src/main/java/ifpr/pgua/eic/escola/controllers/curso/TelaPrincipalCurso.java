package ifpr.pgua.eic.escola.controllers.curso;

import ifpr.pgua.eic.escola.App;
import ifpr.pgua.eic.escola.utils.BorderPaneRegion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class TelaPrincipalCurso {
    
    @FXML
    private void mudarTelaCadastro(ActionEvent event) {
        App.changeScreenRegion("CADASTRO_CURSO", BorderPaneRegion.CENTER);
    }

    @FXML
    private void mudarTelaLista(ActionEvent event) {
        App.changeScreenRegion("LISTA_CURSO", BorderPaneRegion.CENTER);
    }

    @FXML
    private void mudarTelaInicial(ActionEvent event) {
        App.popScreen();
    }
}
