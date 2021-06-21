package controle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Relacionamento;

public class ControleRelacionamento {
	public boolean inserirRelacionamento(Relacionamento r){
        boolean resultado = false;
        Conexao con = new Conexao();
        try{
            PreparedStatement ps = con.getCon().prepareStatement("INSERT INTO relacionamento(aluno, curso, turma) VALUES (?,?,?)");
            ps.setInt(1,r.getAluno());
            ps.setInt(2,r.getCurso());
            ps.setInt(3,r.getTurma());
            
            if(!ps.execute()){
                resultado = true;
            }
        }catch(SQLException e){
            System.out.println("[-] Erro ao inserir o relacionamento: " + e.getMessage());
        }finally{
            con.fecharConexao();
            return resultado;
        }
    }
    
    public boolean editarRelacionamento(Relacionamento r, int id){
        boolean resultado = false;
        Conexao con = new Conexao();
        try {
            PreparedStatement ps = con.getCon().prepareStatement("UPDATE relacionamento SET aluno=?, curso=?, turma=? WHERE id=?");
            ps.setInt(1, r.getAluno());
            ps.setInt(2, r.getCurso());
            ps.setInt(3, r.getTurma());
            ps.setInt(4, id);
            
            if(!ps.execute()){
                resultado = true;
            }
        } catch(SQLException e){
            System.out.println("[-] Erro ao editar o relacionamento: " + e.getMessage());
        } finally {
            con.fecharConexao();
            return resultado;
        }
    }
    public boolean removerRelacionamento(int id){
        boolean resultado = false;
        Conexao con = new Conexao();
        try {
            PreparedStatement ps = con.getCon().prepareStatement("DELETE FROM relacionamento WHERE id=?");
            ps.setInt(1, id);
            
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
    public ArrayList<Relacionamento> selecionarTodos(){
        ArrayList<Relacionamento> lista = null;
        Conexao con = new Conexao();
        try {
            PreparedStatement ps = con.getCon().prepareStatement("SELECT * FROM relacionamento");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                lista = new ArrayList<Relacionamento>();
                while(rs.next()){
                    Relacionamento r = new Relacionamento();
                    r.setId(rs.getInt(1));
                    r.setAluno(rs.getInt(2));
                    r.setCurso(rs.getInt(3));
                    r.setTurma(rs.getInt(4));
                    lista.add(r);
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
