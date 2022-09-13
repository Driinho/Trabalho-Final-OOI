package ifpr.pgua.eic.escola.models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

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
        cadastrarAluno("222-222", "Gustavo", "gustavo@gmail.com", "222-222", LocalDate.now());
        cadastrarAluno("333-333", "Andre", "andre@gmail.com", "333-333", LocalDate.now());

        cadastrarProfessor("444-444", "Hugo", "hugo@gmail.com", "444-444", 1000000);
        cadastrarProfessor("555-555", "Gil", "gil@gmail.com", "555-555", 1200);
        cadastrarProfessor("666-666", "Valério", "valerio@gmail.com", "666-666", 1200);

        Professor professor1 = new Professor("444-444", "Jorge", "hugo@gmail.com", "444-444", 1000000);
        Professor professor2 = new Professor("555-555", "Gil", "gil@gmail.com", "555-555", 1200);
        Professor professor3 = new Professor("666-666", "Valério", "valerio@gmail.com", "666-666", 1200);
        cadastrarCurso(1, "Orientação a Objetos", "Melhor aula", 100, professor1);
        cadastrarCurso(2, "Desenvolvimento Web", "Aula", 100, professor2);
        cadastrarCurso(3, "Engenharia de Software", "Aula", 100, professor3);
    }

    public boolean cadastrarAluno(String cpf, String nome, String email, String telefone, LocalDate dataMatricula) {
        for(Aluno aluno : alunos) {
            if(!aluno.getCpf().equals(cpf)) {
                alunos.add(new Aluno(cpf, nome, email, telefone, dataMatricula));
                return true;
            }
        }
        return false;
    }

    public boolean cadastrarProfessor(String cpf, String nome, String email, String telefone, double salario) {
        boolean existe = false;
        for(Professor professor : professores) {
            if(professor.getCpf().equals(cpf)) {
                existe = true;
            }
        }
        if(existe == false) {
            professores.add(new Professor(cpf, nome, email, telefone, salario));
            return true;
        }

        return false;
        
    }

    public boolean cadastrarCurso(int codigo, String nome, String descricao, int cargaHoraria, Professor professor) {
        for(Curso curso : cursos) {
            if(curso.getCodigo() == codigo) {
                cursos.add(new Curso(codigo, nome, descricao, cargaHoraria, professor));
            }
        }
        return false;
    }

    public boolean matricularAluno(Aluno aluno, Curso curso) {
        return curso.matricula(aluno);
    }

    public boolean desmatricular(Aluno aluno, Curso curso) {
        return curso.desmatricula(aluno.getCpf());
    }

    public ArrayList<Aluno> listarAlunos() {
        return null;
    }

    public ArrayList<Professor> listarProfessores() {
        return null;
    }

    public ArrayList<Curso> listarCursos() {
        return null;
    }

    public ArrayList<Aluno> listarAlunosMatriculados(Curso curso) {
        alunos.clear();
        if(curso.getArquivoMatricula().exists()) {
            try {
                Scanner leitor = new Scanner(curso.getArquivoMatricula());

                while(leitor.hasNextLine()) {
                    String linha = leitor.nextLine();
                    String[] tokens = linha.split(";");

                    String nomeCurso = tokens[0];
                    String cpfAluno = tokens[1];

                    if(nomeCurso.equals(curso.getNome())) {
                        alunos.add(buscarAlunoCpf(cpfAluno));
                    }
                }
                leitor.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return alunos;
        }
        return null;
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

    public void carregaArquivoTexto(String nomeArquivo) {
        
        File arquivoProfessor = new File("src/main/resources/ifpr/pgua/eic/escola/arquivos/arquivoProfessor.txt");
        File arquivoAluno = new File("src/main/resources/ifpr/pgua/eic/escola/arquivos/arquivoAluno.txt");
        File arquivoCurso = new File("src/main/resources/ifpr/pgua/eic/escola/arquivos/arquivoCurso.txt");

        if(arquivoProfessor.exists()) {
            try {
                Scanner leitor = new Scanner(arquivoProfessor);
                professores.clear();
                while(leitor.hasNextLine()) {
                    String linha = leitor.nextLine();
                    String[] tokens = linha.split(";");

                    String cpfProfessor = tokens[0];
                    String nomeProfessor = tokens[1];
                    String emailProfessor = tokens[2];
                    String telefoneProfessor = tokens[3];
                    double salarioProfessor = Double.parseDouble(tokens[4]);

                    professores.add(new Professor(cpfProfessor, nomeProfessor, emailProfessor, telefoneProfessor, salarioProfessor));
                }
                leitor.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(arquivoAluno.exists()) {
            try {
                Scanner leitor = new Scanner(arquivoAluno);
                alunos.clear();
                while(leitor.hasNextLine()) {
                    String linha = leitor.nextLine();
                    String[] tokens = linha.split(";");

                    String cpfAluno = tokens[0];
                    String nomeAluno = tokens[1];
                    String emailAluno = tokens[2];
                    String telefoneAluno = tokens[3];
                    String data = tokens[4];
                    String[] dataMatricula = data.split("-");
                    int ano = Integer.parseInt(dataMatricula[0]);
                    int mes = Integer.parseInt(dataMatricula[1]);
                    int dia = Integer.parseInt(dataMatricula[2]);

                    LocalDate dataMatriculaAluno = LocalDate.of(ano, mes, dia);

                    alunos.add(new Aluno(cpfAluno, nomeAluno, emailAluno, telefoneAluno, dataMatriculaAluno));
                }
                leitor.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(arquivoCurso.exists()) {
            try {
                Scanner leitor = new Scanner(arquivoCurso);
                cursos.clear();
                while(leitor.hasNextLine()) {
                    String linha = leitor.nextLine();
                    String[] tokens = linha.split(";");

                    int codigoCurso = Integer.parseInt(tokens[0]);
                    String nomeCurso = tokens[1];
                    String descricaoCurso = tokens[2];
                    int cargaHorariaCurso = Integer.parseInt(tokens[3]);
                    String cpfProfessor = tokens[4];

                    Professor professor = buscarProfessorCpf(cpfProfessor);

                    cursos.add(new Curso(codigoCurso, nomeCurso, descricaoCurso, cargaHorariaCurso, professor));
                }
                leitor.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void salvarAquivoTexto(String nomeAquivo) {
        
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
