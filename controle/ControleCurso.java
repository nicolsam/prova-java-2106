package controle;

import modelo.Curso;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControleCurso {
    public boolean inserirCurso(Curso c){
        boolean resultado = false;
        Conexao con = new Conexao();
        try{
            PreparedStatement ps = con.getCon().prepareStatement("INSERT INTO curso(nome,coordenador) VALUES (?,?)");
            ps.setString(1,c.getNome());
            ps.setString(2,c.getCoordenador());
            if(!ps.execute()){
                resultado = true;
            }
        }catch(SQLException e){
            System.out.println("[-] Erro ao inserir: " + e.getMessage());
        }finally{
            con.fecharConexao();
            return resultado;
        }
    }
    
    public boolean editarCurso(Curso c, int id){
        boolean resultado = false;
        Conexao con = new Conexao();
        try{
            PreparedStatement ps = con.getCon().prepareStatement("UPDATE curso SET nome=?,coordenador=? WHERE id=?");
            ps.setString(1,c.getNome());
            ps.setString(2,c.getCoordenador());
            ps.setInt(3,id);
            if(!ps.execute()){
                resultado = true;
            }
        }catch(SQLException e){
            System.out.println("[-] Erro ao editar: " + e.getMessage());
        }finally{
            con.fecharConexao();
            return resultado;
        }
    }
    public boolean removerCurso(int id){
        boolean resultado = false;
        Conexao con = new Conexao();
        try{
            PreparedStatement ps = con.getCon().prepareStatement("DELETE FROM curso WHERE id=?");
            ps.setInt(1,id);
            if(!ps.execute()){
                resultado = true;
            }
        }catch(SQLException e){
            System.out.println("[-] Erro ao remover o curso: " + e.getMessage());
        }finally{
            con.fecharConexao();
            return resultado;
        }
    }
    public ArrayList<Curso> selecionarTodos(){
        ArrayList<Curso> lista = null;
        Conexao con = new Conexao();
        try{
            PreparedStatement ps = con.getCon().prepareStatement("SELECT * FROM curso");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                lista = new ArrayList<Curso>();
                while(rs.next()){
                    Curso c = new Curso();
                    c.setId(rs.getInt("id"));
                    c.setNome(rs.getString("nome"));
                    c.setCoordenador(rs.getString("coordenador"));
                    lista.add(c);
                }
            }
        }catch(SQLException e){
            System.out.println("[-] Erro ao obter a lista de Cursos");
        }finally{
            con.fecharConexao();
            return lista;
        }
    } 
}
