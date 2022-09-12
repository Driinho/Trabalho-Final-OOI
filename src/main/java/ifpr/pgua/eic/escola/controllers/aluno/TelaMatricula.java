package ifpr.pgua.eic.escola.controllers.aluno;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import ifpr.pgua.eic.escola.models.Aluno;
import ifpr.pgua.eic.escola.models.Curso;
import ifpr.pgua.eic.escola.models.Escola;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class TelaMatricula implements Initializable {

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

    @FXML
    private Button btnDesmatricula;

    public TelaMatricula(Escola escola) {
        this.escola = escola;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        cbAlunos.getItems().clear();
        if (escola.listarAlunos() != null) {
            cbAlunos.getItems().addAll(escola.listarAlunos());
        }

        cbCursos.getItems().clear();
        if (escola.listarCursos() != null) {
            cbCursos.getItems().addAll(escola.listarCursos());
        }

        codigoCurso.setCellValueFactory(new PropertyValueFactory<Curso, String>("codigo"));
        nomeCurso.setCellValueFactory(new PropertyValueFactory<Curso, String>("nome"));
        descricaoCurso.setCellValueFactory(new PropertyValueFactory<Curso, String>("descricao"));
        cargaHorariaCurso.setCellValueFactory(new PropertyValueFactory<Curso, String>("cargaHoraria"));
        professorCurso.setCellValueFactory(new PropertyValueFactory<Curso, String>("professor"));
    }

    @FXML
    private void populaTabela(ActionEvent event) {
        ArrayList<Curso> cursosMatriculados = new ArrayList<>();
        ArrayList<Curso> cursosNaoMatriculados = new ArrayList<>();

        Aluno alunoSelecionado = cbAlunos.getValue();

        btnDesmatricula.setOpacity(0);

        for (Curso curso : escola.listarCursos()) {
            boolean matriculado = false;
            if (escola.listarAlunosMatriculados(curso) != null) {
                for (Aluno aluno : escola.listarAlunosMatriculados(curso)) {
                    if (aluno.getCpf().equals(alunoSelecionado != null ? alunoSelecionado.getCpf() : "")) {
                        cursosMatriculados.add(curso);
                        matriculado = true;
                    }
                }
            }
            if (!matriculado) {
                cursosNaoMatriculados.add(curso);
            }
        }

        cbCursos.getItems().clear();
        cbCursos.getItems().addAll(cursosNaoMatriculados);
        tvCursosMatriculados.getItems().setAll(cursosMatriculados);
    }

    @FXML
    private void matricularAluno(ActionEvent event) {
        Aluno aluno = cbAlunos.getValue();
        Curso curso = cbCursos.getValue();
        if (aluno != null && curso != null) {
            if (escola.matricularAluno(aluno, curso)) {
                Alert alert = new Alert(AlertType.INFORMATION, "ALUNO MATRICULADO!");
                alert.showAndWait();
                clear();
            } else {
                Alert alert = new Alert(AlertType.ERROR, "ERRO ALUNO NÃO MATRICULADO!");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void desmatricula(ActionEvent event) {
        Aluno aluno = cbAlunos.getValue();
        Curso curso = tvCursosMatriculados.getSelectionModel().getSelectedItem();

        if (curso != null) {
            if (escola.desmatricular(aluno, curso)) {
                Alert alert = new Alert(AlertType.INFORMATION, "ALUNO DESMATRICULADO!");
                alert.showAndWait();
                clear();
            } else {
                Alert alert = new Alert(AlertType.ERROR, "ERRO ALUNO NÃO DESMATRICULADO!");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void apareceBotao(MouseEvent event) {
        if (tvCursosMatriculados.getSelectionModel().getSelectedItem() != null) {
            btnDesmatricula.setOpacity(1);
        }
    }

    @FXML
    private void clear() {
        cbAlunos.getSelectionModel().clearSelection();
        cbCursos.getSelectionModel().clearSelection();
        tvCursosMatriculados.getItems().clear();
        btnDesmatricula.setOpacity(0);
    }
}
