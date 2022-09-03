package ifpr.pgua.eic.escola;

import ifpr.pgua.eic.escola.controllers.TelaInicial;
import ifpr.pgua.eic.escola.utils.BaseAppNavigator;
import ifpr.pgua.eic.escola.utils.ScreenRegistryFXML;

public class App extends BaseAppNavigator{

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public String getHome() {
        // TODO Auto-generated method stub
        return "PRINCIPAL";
    }

    @Override
    public String getAppTitle() {
        // TODO Auto-generated method stub
        return "Escola";
    }

    @Override
    public void registrarTelas() {
        registraTela("PRINCIPAL", new ScreenRegistryFXML(App.class, "view/telaInicial.fxml", o -> new TelaInicial()));
    }

    @Override
    public void atualizaEstilo() {
    }
}