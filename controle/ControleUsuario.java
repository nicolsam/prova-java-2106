package controle;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Usuario;

public class ControleUsuario {
    public boolean login(Usuario usuario){
        boolean retorno = false;
        Conexao con = null;
        try {
            con = new Conexao();
            if(con.getCon() != null){
                PreparedStatement ps = con.getCon().prepareStatement("SELECT * FROM  usuario WHERE usuario=? AND senha=?;");
                ps.setString(1,usuario.getUsuario());
                ps.setString(2,usuario.getSenha());
                if(ps.execute()){
                    ResultSet rs = ps.executeQuery();
                    if(rs.next()){
                        con.fecharConexao();
                        retorno = true;
                    }else{
                        throw new SQLException("Usuário não cadastrado");
                    }              
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }catch(Exception e){
            System.out.println("[-] Erro ao logar.");
        } finally{
            return retorno;
        }
    }
}
