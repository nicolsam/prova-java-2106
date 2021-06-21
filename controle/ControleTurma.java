package controle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Turma;

public class ControleTurma {
	public boolean inserirTurma(Turma t){
        boolean resultado = false;
        Conexao con = new Conexao();
        
        try {
            PreparedStatement ps = con.getCon().prepareStatement("INSERT INTO turma(serie) VALUES (?)");
            ps.setInt(1,t.getSerie());
            
            if(!ps.execute()){
                resultado = true;
            }
            
        }catch(SQLException e){
            System.out.println("[-] Erro ao inserir a turma: " + e.getMessage());
        }finally{
            con.fecharConexao();
            return resultado;
        }
    }
    
    public boolean editarTurma(Turma t, int id){
        boolean resultado = false;
        Conexao con = new Conexao();
        try {
            PreparedStatement ps = con.getCon().prepareStatement("UPDATE turma SET serie=? WHERE id=?");
            ps.setInt(1, t.getSerie());
            ps.setInt(2, id);
            
            if(!ps.execute()){
                resultado = true;
            }
        } catch(SQLException e){
            System.out.println("[-] Erro ao editar o perfil do turma: " + e.getMessage());
        } finally {
            con.fecharConexao();
            return resultado;
        }
    }
    public boolean removerTurma(int id){
        boolean resultado = false;
        Conexao con = new Conexao();
        try {
            PreparedStatement ps = con.getCon().prepareStatement("DELETE FROM turma WHERE id=?");
            ps.setInt(1, id);
            
            if(!ps.execute()){
                resultado = true;
            }
        } catch(SQLException e){
            System.out.println("[-] Erro ao remover a turma: " + e.getMessage());
        } finally {
            con.fecharConexao();
            return resultado;
        }
    }
    public ArrayList<Turma> selecionarTodos(){
        ArrayList<Turma> lista = null;
        Conexao con = new Conexao();
        try {
            PreparedStatement ps = con.getCon().prepareStatement("SELECT * FROM turma");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                lista = new ArrayList<Turma>();
                while(rs.next()){
                    Turma t = new Turma();
                    t.setId(rs.getInt("id"));
                    t.setSerie(rs.getInt("serie"));
                    lista.add(t);
                }
            }
        } catch(SQLException e){
            System.out.println("[-] Erro ao obter a lista de Turmas");
        } finally {
            con.fecharConexao();
            return lista;
        }
    }
}
