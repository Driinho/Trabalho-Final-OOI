package ifpr.pgua.eic.escola;

import java.io.File;

import ifpr.pgua.eic.escola.controllers.TelaInicial;
import ifpr.pgua.eic.escola.controllers.aluno.TelaCadastroAluno;
import ifpr.pgua.eic.escola.controllers.aluno.TelaListaAluno;
import ifpr.pgua.eic.escola.controllers.aluno.TelaMatricula;
import ifpr.pgua.eic.escola.controllers.aluno.TelaPrincipalAluno;
import ifpr.pgua.eic.escola.controllers.curso.TelaCadastroCurso;
import ifpr.pgua.eic.escola.controllers.curso.TelaListaCurso;
import ifpr.pgua.eic.escola.controllers.curso.TelaPrincipalCurso;
import ifpr.pgua.eic.escola.controllers.professor.TelaCadastroProf;
import ifpr.pgua.eic.escola.controllers.professor.TelaListaProf;
import ifpr.pgua.eic.escola.controllers.professor.TelaPrincipalProf;
import ifpr.pgua.eic.escola.models.Escola;
import ifpr.pgua.eic.escola.utils.BaseAppNavigator;
import ifpr.pgua.eic.escola.utils.ScreenRegistryFXML;

public class App extends BaseAppNavigator {

    private Escola escola;
    File pasta;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void init() throws Exception {
        super.init();
        pasta = new File("src/main/resources/ifpr/pgua/eic/escola/arquivos");
        if(!pasta.exists()) {
            pasta.mkdir();
        }
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
                new ScreenRegistryFXML(App.class, "view/professor/telaPrincipalProf.fxml", o -> new TelaPrincipalProf()));
        registraTela("CADASTRO_PROF",
                new ScreenRegistryFXML(App.class, "view/professor/telaCadastroProf.fxml", o -> new TelaCadastroProf(escola)));
        registraTela("LISTA_PROF",
                new ScreenRegistryFXML(App.class, "view/professor/telaListaProf.fxml", o -> new TelaListaProf(escola)));
        registraTela("PRINCIPAL_ALUNO",
                new ScreenRegistryFXML(App.class, "view/aluno/telaPrincipalAluno.fxml", o -> new TelaPrincipalAluno()));
        registraTela("CADASTRO_ALUNO",
                new ScreenRegistryFXML(App.class, "view/aluno/telaCadastroAluno.fxml", o -> new TelaCadastroAluno(escola)));
        registraTela("MATRICULA_ALUNO",
                new ScreenRegistryFXML(App.class, "view/aluno/telaMatricula.fxml", o -> new TelaMatricula(escola)));
        registraTela("LISTA_ALUNO",
                new ScreenRegistryFXML(App.class, "view/aluno/telaListaAluno.fxml", o -> new TelaListaAluno(escola)));
        registraTela("PRINCIPAL_CURSO", 
                new ScreenRegistryFXML(App.class, "view/curso/telaPrincipalCurso.fxml", o -> new TelaPrincipalCurso()));
        registraTela("CADASTRO_CURSO", 
                new ScreenRegistryFXML(App.class, "view/curso/telaCadastroCurso.fxml", o -> new TelaCadastroCurso(escola)));
        registraTela("LISTA_CURSO", 
                new ScreenRegistryFXML(App.class, "view/curso/telaListaCurso.fxml", o -> new TelaListaCurso(escola)));
    }

    @Override
    public void atualizaEstilo() {
        adicionarArquivoEstilo(getClass().getResource("style/estilo1.css").toExternalForm());
    }
}