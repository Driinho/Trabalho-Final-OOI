package ifpr.pgua.eic.escola.models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Curso {

    private int codigo;
    private String nome;
    private String descricao;
    private int cargaHoraria;
    private Professor professor;
    private ArrayList<Aluno> alunos;

    private File arquivoMatricula; 

    public Curso(int codigo, String nome, String descricao, int cargaHoraria, Professor professor) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.cargaHoraria = cargaHoraria;
        this.professor = professor;

        alunos = new ArrayList<>();

        arquivoMatricula = new File("src/main/resources/ifpr/pgua/eic/escola/arquivos/arquivoMatricula.txt");
    }

    public boolean matricula(Aluno aluno) {

        try {
            FileWriter fWriter = new FileWriter(arquivoMatricula, true);
            BufferedWriter bWriter = new BufferedWriter(fWriter);

            bWriter.write(aluno.getCpf());
            bWriter.newLine();

            bWriter.close();
            fWriter.close();

            alunos.add(aluno);
            
            return true;
        } catch(IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean desmatricula(String cpf) {
        for (Aluno aluno : alunos) {
            if (aluno.getCpf().equals(cpf)) {
                alunos.remove(aluno);
                return true;
            }
        }
        return false;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(ArrayList<Aluno> alunos) {
        this.alunos = alunos;
    }

    @Override
    public String toString() {
        return codigo + " - " + nome;
    }

    public String toText() {
        return codigo + ";" + nome + ";" + descricao + ";" + cargaHoraria + ";" + professor.getCpf();
    }
}
