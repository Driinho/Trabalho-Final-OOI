package ifpr.pgua.eic.escola.controllers.aluno;

import ifpr.pgua.eic.escola.App;
import ifpr.pgua.eic.escola.utils.BorderPaneRegion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class TelaPrincipalAluno extends App {

    @FXML
    private void mudarTelaCadastro(ActionEvent event) {
        App.changeScreenRegion("CADASTRO_ALUNO", BorderPaneRegion.CENTER);
    }

    @FXML
    private void mudarTelaLista(ActionEvent event) {
        App.changeScreenRegion("LISTA_ALUNO", BorderPaneRegion.CENTER);
    }

    @FXML
    private void mudarTelaMatricula(ActionEvent event) {
        App.changeScreenRegion("MATRICULA_ALUNO", BorderPaneRegion.CENTER);
    }

    @FXML
    private void mudarTelaInicio(ActionEvent event) {
        App.popScreen();
        atualizaEstilo();
    }

    @Override
    public void atualizaEstilo() {
        removeArquivoEstilo(getClass().getResource("../../style/style-aux.css").toExternalForm());
        adicionarArquivoEstilo(getClass().getResource("../../style/style-principal.css").toExternalForm());
    }
}
