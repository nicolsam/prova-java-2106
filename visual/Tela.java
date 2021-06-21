package visual;
import controle.ControleUsuario;
import java.util.Scanner;
import modelo.Usuario;
public class Tela {
    private Scanner sc = new Scanner(System.in);
    public void login(){
        Usuario usuario = new Usuario();
        ControleUsuario controle = new ControleUsuario();
        System.out.print("Digite o login: ");
        usuario.setUsuario(sc.next());
        System.out.print("Digite a senha: ");
        usuario.setSenha(sc.next());
        if(controle.login(usuario)){
            Lib.limparTela();
            Lib.menuInicial();
        }
    }
}
