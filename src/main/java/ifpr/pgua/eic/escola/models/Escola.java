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

        criaFakes();
    }

    public void criaFakes() {
        cadastrarAluno("111-111", "Pedro", "pedro@gmail.com", "111-111", LocalDate.now());
        cadastrarAluno("-222", "Gustavo", "gustavo@gmail.com", "222-222", LocalDate.now());
        cadastrarAluno("333-333", "Andre", "andre@gmail.com", "333-333", LocalDate.now());

        cadastrarProfessor("444-444", "Jonas", "jonas@gmail.com", "444-444", 1200);
        cadastrarProfessor("555-555", "Alberto", "alberto@gmail.com", "555-555", 1200);
        cadastrarProfessor("666-666", "Rodrigo", "rodrigo@gmail.com", "666-666", 1200);
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
        return alunos;
    }

    public ArrayList<Professor> listarProfessores() {
        return professores;
    }

    public ArrayList<Curso> listarCursos() {
        return cursos;
    }

    public ArrayList<Aluno> listarAlunosMatriculados(Curso curso) {
        return curso.getAlunos();
    }

    public Aluno buscarAlunoCpf(String cpf) {
        for(Aluno alunoAtual : alunos) {
            if(alunoAtual.getCpf().equals(cpf)) {
                return alunoAtual;
            }
        }
        return null;
    }

    public Professor buscarProfessorCpf(String cpf) {
        for(Professor professorAtual : professores) {
            if(professorAtual.getCpf().equals(cpf)) {
                return professorAtual;
            }
        }
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

    public void setProfessores(ArrayList<Professor> professores) {
        this.professores = professores;
    }

    public void setAlunos(ArrayList<Aluno> alunos) {
        this.alunos = alunos;
    }

    public void setCursos(ArrayList<Curso> cursos) {
        this.cursos = cursos;
    }

}
