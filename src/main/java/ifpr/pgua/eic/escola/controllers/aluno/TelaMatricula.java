package ifpr.pgua.eic.escola.controllers.aluno;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.escola.models.Aluno;
import ifpr.pgua.eic.escola.models.Curso;
import ifpr.pgua.eic.escola.models.Escola;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TelaMatricula implements Initializable{
    
    private Escola escola;

    @FXML
    private ComboBox<Aluno> cbAlunos;

    @FXML
    private ComboBox<Curso> cbCursos;

    @FXML
    private TableView<Curso> tvCursosMatriculados;

    @FXML
    private TableColumn<Curso, String> codigoCurso;

    @FXML
    private TableColumn<Curso, String> nomeCurso;

    @FXML
    private TableColumn<Curso, String> descricaoCurso;

    @FXML
    private TableColumn<Curso, String> cargaHorariaCurso;

    @FXML
    private TableColumn<Curso, String> professorCurso;

    public TelaMatricula(Escola escola) {
        this.escola = escola;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        cbAlunos.getItems().clear();
        cbAlunos.getItems().addAll(escola.listarAlunos());

        cbCursos.getItems().clear();
        cbCursos.getItems().addAll(escola.listarCursos());
        
        // for(Curso curso : escola.listarCursos()) {
        //     if(curso.getAlunos().get(i).getNome().equals())
        // }

        codigoCurso.setCellValueFactory(new PropertyValueFactory<Curso, String>("codigo"));
        nomeCurso.setCellValueFactory(new PropertyValueFactory<Curso, String>("nome"));
        descricaoCurso.setCellValueFactory(new PropertyValueFactory<Curso, String>("descricao"));
        cargaHorariaCurso.setCellValueFactory(new PropertyValueFactory<Curso, String>("cargaHoraria"));
        professorCurso.setCellValueFactory(new PropertyValueFactory<Curso, String>("professor"));
    
        tvCursosMatriculados.getItems().setAll(escola.listarCursos());
    }

    @FXML
    private void matricularAluno(ActionEvent event) {
        Aluno aluno = cbAlunos.getSelectionModel().getSelectedItem();
        Curso curso = cbCursos.getSelectionModel().getSelectedItem();

        escola.matricularAluno(aluno, curso);
    }
}
