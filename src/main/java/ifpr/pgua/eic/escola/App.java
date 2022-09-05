package ifpr.pgua.eic.escola;

import ifpr.pgua.eic.escola.controllers.TelaCadastroAluno;
import ifpr.pgua.eic.escola.controllers.TelaCadastroProf;
import ifpr.pgua.eic.escola.controllers.TelaInicial;
import ifpr.pgua.eic.escola.controllers.TelaListaAluno;
import ifpr.pgua.eic.escola.controllers.TelaListaProf;
import ifpr.pgua.eic.escola.controllers.TelaPrincipalAluno;
import ifpr.pgua.eic.escola.controllers.TelaPrincipalProf;
import ifpr.pgua.eic.escola.models.Escola;
import ifpr.pgua.eic.escola.utils.BaseAppNavigator;
import ifpr.pgua.eic.escola.utils.ScreenRegistryFXML;

public class App extends BaseAppNavigator {

    private Escola escola;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void init() throws Exception {
        super.init();

        escola = new Escola("IFPR - CAMPUS PARANAGUÁ", "4002-8922");
    }

    @Override
    public String getHome() {
        return "INICIAL";
    }

    @Override
    public String getAppTitle() {
        return "Escola";
    }

    @Override
    public void registrarTelas() {
        registraTela("INICIAL", new ScreenRegistryFXML(App.class, "view/telaInicial.fxml", o -> new TelaInicial()));
        registraTela("PRINCIPAL_PROF",
                new ScreenRegistryFXML(App.class, "view/telaPrincipalProf.fxml", o -> new TelaPrincipalProf()));
        registraTela("CADASTRO_PROF",
                new ScreenRegistryFXML(App.class, "view/telaCadastroProf.fxml", o -> new TelaCadastroProf(escola)));
        registraTela("LISTA_PROF",
                new ScreenRegistryFXML(App.class, "view/telaListaProf.fxml", o -> new TelaListaProf(escola)));
        registraTela("PRINCIPAL_ALUNO",
                new ScreenRegistryFXML(App.class, "view/telaPrincipalAluno.fxml", o -> new TelaPrincipalAluno()));
        registraTela("CADASTRO_ALUNO",
                new ScreenRegistryFXML(App.class, "view/telaCadastroAluno.fxml", o -> new TelaCadastroAluno(escola)));
        registraTela("LISTA_ALUNO",
                new ScreenRegistryFXML(App.class, "view/telaListaAluno.fxml", o -> new TelaListaAluno(escola)));
    }

    @Override
    public void atualizaEstilo() {
    }
}