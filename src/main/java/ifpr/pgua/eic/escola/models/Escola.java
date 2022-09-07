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

        cadastrarProfessor("444-444", "Hugo", "hugo@gmail.com", "444-444", 1000000);
        cadastrarProfessor("555-555", "Gil", "gil@gmail.com", "555-555", 1200);
        cadastrarProfessor("666-666", "Valério", "valerio@gmail.com", "666-666", 1200);

        Professor professor1 = new Professor("444-444", "Hugo", "hugo@gmail.com", "444-444", 1000000);
        Professor professor2 = new Professor("555-555", "Gil", "gil@gmail.com", "555-555", 1200);
        Professor professor3 = new Professor("666-666", "Valério", "valerio@gmail.com", "666-666", 1200);
        cadastrarCurso(1, "Orientação a Objetos", "Melhor aula", 100, professor1);
        cadastrarCurso(2, "Desenvolvimento Web", "Aula", 100, professor2);
        cadastrarCurso(3, "Engenharia de Software", "Aula", 100, professor3);
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
        for(Curso curso : cursos) {
            if(curso.getNome().equals(nome)) {
                return curso;
            }
        }
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
