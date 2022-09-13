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
    }

    public boolean cadastrarAluno(String cpf, String nome, String email, String telefone, LocalDate dataMatricula) {
        boolean existe = false;
        for (Aluno aluno : alunos) {
            if (aluno.getCpf().equals(cpf)) {
                existe = true;
            }
        }
        if (existe == false) {
            alunos.add(new Aluno(cpf, nome, email, telefone, dataMatricula));
            return true;
        }
        return false;
    }

    public boolean cadastrarProfessor(String cpf, String nome, String email, String telefone, double salario) {
        boolean existe = false;
        for (Professor professor : professores) {
            if (professor.getCpf().equals(cpf)) {
                existe = true;
            }
        }
        if (existe == false) {
            professores.add(new Professor(cpf, nome, email, telefone, salario));
            return true;
        }
        return false;

    }

    public boolean cadastrarCurso(int codigo, String nome, String descricao, int cargaHoraria, Professor professor) {
        boolean existe = false;
        for (Curso curso : cursos) {
            if (curso.getCodigo() == codigo) {
                existe = true;
            }
        }
        if (existe == false) {
            cursos.add(new Curso(codigo, nome, descricao, cargaHoraria, professor));
            return true;
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
        for (Aluno aluno : alunos) {
            if (aluno.getCpf().equals(cpf)) {
                return aluno;
            }
        }
        return null;
    }

    public Professor buscarProfessorCpf(String cpf) {
        for (Professor professor : professores) {
            if (professor.getCpf().equals(cpf)) {
                return professor;
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

    public void carregaArquivoTexto(String nomeArquivo) {

        File arquivoProfessor = new File("src/main/resources/ifpr/pgua/eic/escola/arquivos/arquivoProfessor.txt");
        File arquivoAluno = new File("src/main/resources/ifpr/pgua/eic/escola/arquivos/arquivoAluno.txt");
        File arquivoCurso = new File("src/main/resources/ifpr/pgua/eic/escola/arquivos/arquivoCurso.txt");
        File arquivoMatricula = new File("src/main/resources/ifpr/pgua/eic/escola/arquivos/arquivoMatricula.txt");

        if (arquivoProfessor.exists()) {
            try {
                Scanner leitor = new Scanner(arquivoProfessor);
                while (leitor.hasNextLine()) {
                    String linha = leitor.nextLine();
                    String[] tokens = linha.split(";");

                    String cpfProfessor = tokens[0];
                    String nomeProfessor = tokens[1];
                    String emailProfessor = tokens[2];
                    String telefoneProfessor = tokens[3];
                    double salarioProfessor = Double.parseDouble(tokens[4]);

                    professores.add(new Professor(cpfProfessor, nomeProfessor, emailProfessor, telefoneProfessor,
                            salarioProfessor));
                }
                leitor.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (arquivoAluno.exists()) {
            try {
                Scanner leitor = new Scanner(arquivoAluno);
                while (leitor.hasNextLine()) {
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
        if (arquivoCurso.exists()) {
            try {
                Scanner leitor = new Scanner(arquivoCurso);
                while (leitor.hasNextLine()) {
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

        if (arquivoMatricula.exists()) {
            try {
                Scanner leitor = new Scanner(arquivoMatricula);

                while (leitor.hasNextLine()) {
                    String linha = leitor.nextLine();
                    String[] tokens = linha.split(";");

                    String nomeCurso = tokens[0];
                    String cpfAluno = tokens[1];

                    Curso curso = buscarCurso(nomeCurso);
                    Aluno aluno = buscarAlunoCpf(cpfAluno);
                    curso.matricula(aluno);
                }
                leitor.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void salvarAquivoTexto(String nomeAquivo) {
        File arquivoProfessor = new File("src/main/resources/ifpr/pgua/eic/escola/arquivos/arquivoProfessor.txt");
        File arquivoAluno = new File("src/main/resources/ifpr/pgua/eic/escola/arquivos/arquivoAluno.txt");
        File arquivoCurso = new File("src/main/resources/ifpr/pgua/eic/escola/arquivos/arquivoCurso.txt");
        File arquivoMatricula = new File("src/main/resources/ifpr/pgua/eic/escola/arquivos/arquivoMatricula.txt");

        try {
            FileWriter fWriter = new FileWriter(arquivoProfessor);
            BufferedWriter bWriter = new BufferedWriter(fWriter);

            for (Professor professor : professores) {
                bWriter.write(professor.toText());
                bWriter.newLine();
            }
            bWriter.close();
            fWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter fWriter = new FileWriter(arquivoAluno);
            BufferedWriter bWriter = new BufferedWriter(fWriter);

            for (Aluno aluno : alunos) {
                bWriter.write(aluno.toText());
                bWriter.newLine();
            }
            bWriter.close();
            fWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter fWriter = new FileWriter(arquivoCurso);
            BufferedWriter bWriter = new BufferedWriter(fWriter);

            for (Curso curso : cursos) {
                bWriter.write(curso.toText());
                bWriter.newLine();
            }
            bWriter.close();
            fWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter fwriter = new FileWriter(arquivoMatricula);
            BufferedWriter bwriter = new BufferedWriter(fwriter);

            for (Curso curso : cursos) {
                for (Aluno aluno : curso.getAlunos()) {
                    bwriter.write(curso.getNome() + ";" + aluno.getCpf());
                }
            }

            bwriter.close();
            fwriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
