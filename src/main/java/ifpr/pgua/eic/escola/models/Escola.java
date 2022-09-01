package ifpr.pgua.eic.escola.models;

import java.time.LocalDate;
import java.util.ArrayList;

public class Escola {

    private String nome;
    private String telefone;
    private ArrayList<Professor> professores;
    private ArrayList<Aluno> alunos;
    private ArrayList<Curso> cursos;

    public Escola(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;

        professores = new ArrayList<>();
        alunos = new ArrayList<>();
        cursos = new ArrayList<>();
    }

    public boolean cadastrarAluno(String cpf, String nome, String email, String telefone, LocalDate dataMatricula) {
        return alunos.add(new Aluno(cpf, nome, email, telefone, dataMatricula));
    }

    public boolean cadastrarProfessor(String cpf, String nome, String email, String telefone, double salario) {
        return professores.add(new Professor(cpf, nome, email, telefone, salario));
    }

    public boolean cadastrarCurso(int codigo, String nome, String descricao, int cargaHoraria, Professor professor) {
        return cursos.add(new Curso(codigo, nome, descricao, cargaHoraria, professor));
    }

    public boolean matricularAluno(Aluno aluno, Curso curso) {
        return curso.matricula(aluno);
    }

    public boolean desmatricular(Aluno aluno, Curso curso) {
        return curso.desmatricula(aluno.getCpf());
    }

    public ArrayList<Aluno> listarAlunos() {
        return getAlunos();
    }

    public ArrayList<Professor> listarProfessores() {
        return getProfessores();
    }

    public ArrayList<Curso> listarCursos() {
        return getCursos();
    }

    public ArrayList<Aluno> listarAlunosMatriculados(Curso curso) {
        return curso.getAlunos();
    }

    public Aluno buscarAlunoCpf(String cpf) {
        return null;
    }

    public Professor buscarProfessorCpf(String cpf) {
        return null;
    }

    public Curso buscarCurso(String nome) {
        return null;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public ArrayList<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(ArrayList<Professor> professores) {
        this.professores = professores;
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(ArrayList<Aluno> alunos) {
        this.alunos = alunos;
    }

    public ArrayList<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(ArrayList<Curso> cursos) {
        this.cursos = cursos;
    }

}
