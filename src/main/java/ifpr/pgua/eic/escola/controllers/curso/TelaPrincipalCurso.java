package ifpr.pgua.eic.escola.controllers.curso;

import ifpr.pgua.eic.escola.App;
import ifpr.pgua.eic.escola.utils.BorderPaneRegion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class TelaPrincipalCurso extends App {
    
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
        atualizaEstilo();
    }

    @Override
    public void atualizaEstilo() {
        removeArquivoEstilo(getClass().getResource("../../style/style-aux.css").toExternalForm());
        adicionarArquivoEstilo(getClass().getResource("../../style/style-principal.css").toExternalForm());
    }
}
