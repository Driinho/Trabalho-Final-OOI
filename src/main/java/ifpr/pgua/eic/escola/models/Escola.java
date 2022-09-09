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
    private File arquivoProfessor;
    private File arquivoAluno;
    private File arquivoCurso;

    public Escola(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;

        professores = new ArrayList<>();
        alunos = new ArrayList<>();
        cursos = new ArrayList<>();

        arquivoProfessor = new File("src/main/resources/ifpr/pgua/eic/escola/arquivos/arquivoProfessor.txt");
        arquivoAluno = new File("src/main/resources/ifpr/pgua/eic/escola/arquivos/arquivoAluno.txt");
        arquivoCurso = new File("src/main/resources/ifpr/pgua/eic/escola/arquivos/arquivoCurso.txt");

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
        if(buscarAlunoCpf(cpf) == null) {
            Aluno aluno = new Aluno(cpf, nome, email, telefone, dataMatricula);
            try {
                FileWriter fwriter = new FileWriter(arquivoAluno,true);
                BufferedWriter bwriter = new BufferedWriter(fwriter);
    
                bwriter.write(aluno.toText());
                bwriter.newLine();
    
                bwriter.close();
                fwriter.close();
                return alunos.add(aluno);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } 
        
        return false;
    }

    public boolean cadastrarProfessor(String cpf, String nome, String email, String telefone, double salario) {
        if(buscarAlunoCpf(cpf) == null) {
            Professor professor = new Professor(cpf, nome, email, telefone, salario);
            try {
                FileWriter fWriter = new FileWriter(arquivoProfessor, true);
                BufferedWriter bWriter = new BufferedWriter(fWriter);
    
                bWriter.write(professor.toText());
                bWriter.newLine();
    
                bWriter.close();
                fWriter.close();
    
            } catch (IOException e) {
                e.printStackTrace();
            }
    
            return professores.add(professor);
        } else {
            return false;
        }
    }

    public boolean cadastrarCurso(int codigo, String nome, String descricao, int cargaHoraria, Professor professor) {
        if(buscarCurso(nome) == null) {
            Curso curso = new Curso(codigo, nome, descricao, cargaHoraria, professor);
            try {
                FileWriter fWriter = new FileWriter(arquivoCurso, true);
                BufferedWriter bWriter = new BufferedWriter(fWriter);
    
                bWriter.write(curso.toText());
                bWriter.newLine();
    
                bWriter.close();
                fWriter.close();
            } catch(IOException e) {
                e.printStackTrace();
            } 
            return cursos.add(curso);
        } else {
            return false;
        }
    }

    public boolean matricularAluno(Aluno aluno, Curso curso) {
        return curso.matricula(aluno);
    }

    public boolean desmatricular(Aluno aluno, Curso curso) {
        return curso.desmatricula(aluno.getCpf());
    }

    public ArrayList<Aluno> listarAlunos() {
        alunos.clear();
        if(arquivoAluno.exists()) {
            try {
                Scanner leitor = new Scanner(arquivoAluno);
            
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

                    Aluno aluno = new Aluno(cpfAluno, nomeAluno, emailAluno, telefoneAluno, dataMatriculaAluno);

                    alunos.add(aluno);
                }
                leitor.close();
                return alunos;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public ArrayList<Professor> listarProfessores() {
        professores.clear();
        if(arquivoProfessor.exists()) {
            try {
                Scanner leitor = new Scanner(arquivoProfessor);

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
                return professores;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public ArrayList<Curso> listarCursos() {
        return cursos;
    }

    public ArrayList<Aluno> listarAlunosMatriculados(Curso curso) {
        return curso.getAlunos();
    }

    public Aluno buscarAlunoCpf(String cpf) {
        if(arquivoAluno.exists()) {
            try {
                Scanner leitor = new Scanner(arquivoAluno);
            
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
                    if(cpf.equals(cpfAluno)) {
                        leitor.close();
                        return new Aluno(cpfAluno, nomeAluno, emailAluno, telefoneAluno, dataMatriculaAluno);
                    }
                }
                leitor.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Professor buscarProfessorCpf(String cpf) {
        if(arquivoProfessor.exists()) {
            try {
                Scanner leitor = new Scanner(arquivoProfessor);

                while(leitor.hasNextLine()) {
                    String linha = leitor.nextLine();
                    String[] tokens = linha.split(";");

                    String cpfProfessor = tokens[0];
                    String nomeProfessor = tokens[1];
                    String emailProfessor = tokens[2];
                    String telefoneProfessor = tokens[3];
                    double salarioProfessor = Double.parseDouble(tokens[4]);

                    if(cpf.equals(cpfProfessor)) {
                        leitor.close();
                        return new Professor(cpfProfessor, nomeProfessor, emailProfessor, telefoneProfessor, salarioProfessor);
                    }
                }

                leitor.close();
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Curso buscarCurso(String nome) {
        for (Curso curso : cursos) {
            if (curso.getNome().equals(nome)) {
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
