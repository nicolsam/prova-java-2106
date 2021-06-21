package controle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private Connection con = null;
    public Connection getCon() {
        return con;
    }
    public void setCon(Connection con) {
        this.con = con;
    }
    public void fecharConexao(){
        try{
            this.getCon().close();
        }catch(SQLException e){
            System.out.println("Erro ao fechar a conexão");
        }
    }  
    public Conexao(){
        try {
            String url = "jdbc:sqlite:db/banco.db";
            this.setCon(DriverManager.getConnection(url));
        } catch(SQLException e){
            System.out.println("[-] Erro ao conectar ao banco.");
            System.out.println(e.getMessage());
        }
    }
}
