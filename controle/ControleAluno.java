package controle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Aluno;

public class ControleAluno {
	public boolean inserirAluno(Aluno a){
        boolean resultado = false;
        Conexao con = new Conexao();
        try{
            PreparedStatement ps = con.getCon().prepareStatement("INSERT INTO aluno(matricula, nome, email) VALUES (?,?,?)");
            ps.setInt(1,a.getMatricula());
            ps.setString(2,a.getNome());
            ps.setString(3,a.getEmail());
            
            if(!ps.execute()){
                resultado = true;
            }
        }catch(SQLException e){
            System.out.println("[-] Erro ao inserir o aluno: " + e.getMessage());
        }finally{
            con.fecharConexao();
            return resultado;
        }
    }
    
    public boolean editarAluno(Aluno a, int matricula){
        boolean resultado = false;
        Conexao con = new Conexao();
        try {
            PreparedStatement ps = con.getCon().prepareStatement("UPDATE aluno SET nome=?,email=? WHERE matricula=?");
            ps.setString(1, a.getNome());
            ps.setString(2, a.getEmail());
            ps.setInt(3, matricula);
            
            if(!ps.execute()){
                resultado = true;
            }
        } catch(SQLException e){
            System.out.println("[-] Erro ao editar o perfil do aluno: " + e.getMessage());
        } finally {
            con.fecharConexao();
            return resultado;
        }
    }
    public boolean removerAluno(int matricula){
        boolean resultado = false;
        Conexao con = new Conexao();
        try {
            PreparedStatement ps = con.getCon().prepareStatement("DELETE FROM aluno WHERE matricula=?");
            ps.setInt(1, matricula);
            
            if(!ps.execute()){
                resultado = true;
            }
        } catch(SQLException e){
            System.out.println("[-] Erro ao remover o perfil do aluno: " + e.getMessage());
        } finally {
            con.fecharConexao();
            return resultado;
        }
    }
    public ArrayList<Aluno> selecionarTodos(){
        ArrayList<Aluno> lista = null;
        Conexao con = new Conexao();
        try {
            PreparedStatement ps = con.getCon().prepareStatement("SELECT * FROM aluno");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                lista = new ArrayList<Aluno>();
                while(rs.next()){
                    Aluno a = new Aluno();
                    a.setMatricula(rs.getInt("matricula"));
                    a.setNome(rs.getString("nome"));
                    a.setEmail(rs.getString("email"));
                    lista.add(a);
                }
            }
        } catch(SQLException e){
            System.out.println("[-] Erro ao obter a lista de Cursos");
        } finally {
            con.fecharConexao();
            return lista;
        }
    }
}
