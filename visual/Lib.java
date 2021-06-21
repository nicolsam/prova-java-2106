package visual;
import java.util.Scanner;
import java.util.ArrayList;

import controle.ControleCurso;
import modelo.Curso;

import controle.ControleAluno;
import modelo.Aluno;

import controle.ControleTurma;
import modelo.Turma;

import controle.ControleRelacionamento;
import modelo.Relacionamento;

public class Lib {
    private static Scanner sc = new Scanner(System.in);
    // Apaga as informações da tela.
    // Usar quando achar que precisa
    public static void limparTela(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }
    // Usado para voltar diretamente ao menu principal quando um erro acontecer
    // Passar o tempo em segundos como argumento
    public static void erro(int tempo){
        System.out.println("Opção inválida. Voltando ao menu principal.");
        tempo = tempo * 1000;
        try {
            Thread.sleep(tempo);
        } catch (InterruptedException e){
            e.getMessage();
        }finally{
            limparTela();
            menuInicial();
        }
    }
    // Os métodos a seguir usam entradas de dado do tipo Scanner com números
    // inteiros.
    // Usar somente o método sc.nextInt() pra evitar erros no código

    // Menu Principal
    // Chama o menu principal da aplicação
    // Recebe uma entrada de dados referente a opção desejada
    public static void menuInicial(){
        System.out.println("--------------------------------------");
        System.out.println("Menu Principal");
        System.out.println("1 - Menu Curso");
        System.out.println("2 - Menu Turma");
        System.out.println("3 - Menu Aluno");
        System.out.println("4 - Relacionamento");
        System.out.println("0 - Sair");
        System.out.println("--------------------------------------");
        System.out.print("Escolha a opção: ");
        try{
            switch(sc.nextInt()){
                case 1:
                    limparTela();
                    menuCurso();
                    break;
                case 2:
                    menuTurma();
                    break;
                case 3:
                    menuAluno();
                    break;
                case 4:
                    menuRelacao();
                    break;
                case 0:
                    System.out.println("Saindo da aplicação.");
                    break;
                default:
                    erro(2);
            }       
        }catch(Exception e){
            System.out.println("[-] Digite uma das opções mostradas na tela");
        }
    }

    // Menu Curso
    // Menu do CRUD de CURSO
    public static void menuCurso(){
        System.out.println("--------------------------------------");
        System.out.println("Menu Curso");
        System.out.println("1 - Listar");
        System.out.println("2 - Cadastrar");
        System.out.println("3 - Editar");
        System.out.println("4 - Apagar");
        System.out.println("0 - Voltar");
        System.out.println("--------------------------------------");
        System.out.print("Escolha a opção: ");
        try{
            switch(sc.nextInt()){
                case 1:
                    try{
                        ControleCurso con = new ControleCurso();
                        ArrayList<Curso> lista = con.selecionarTodos();
                        if(lista != null){
                            System.out.println("| ID | Nome | Coordenador |");
                            for(int i = 0; i < lista.size() ; i++){
                                System.out.print("| " + lista.get(i).getId());
                                System.out.print(" | " + lista.get(i).getNome());
                                System.out.print(" | " + lista.get(i).getCoordenador());
                                System.out.println(" | ");
                            }
                        }
                    }catch(Exception e){
                        System.out.println("Erro de execução");
                    }finally{
                        menuCurso();
                    }
                    break;
                case 2:
                    try{
                        ControleCurso con = new ControleCurso();
                        Curso c = new Curso();
                        System.out.print("Digite o nome do curso: ");
                        c.setNome(sc.next());
                        System.out.print("Digite o nome do Coordenador: ");
                        c.setCoordenador(sc.next());
                        if(con.inserirCurso(c)){
                            System.out.println("Curso Cadastrado");
                        }
                    }catch(Exception e){
                        System.out.print("Erro ao inserir.");
                    }finally{
                        menuCurso();
                    }
                    break;
                case 3:
                    try {
                        System.out.print("Digite o id do curso que deseja editar: ");
                        int id = sc.nextInt();
                        Curso c = new Curso();
                        c.setId(id);
                        System.out.print("Digite o novo nome do curso: ");
                        c.setNome(sc.next());
                        System.out.print("Digite o novo nome do coordenador: ");
                        c.setCoordenador(sc.next());
                        if(new ControleCurso().editarCurso(c,id)){
                            System.out.println("Curso editado com sucesso");
                        }
                    }catch(Exception e){
                        System.out.println("Erro ao editar.");
                    }finally{
                        menuCurso();
                    }
                    break;
                case 4:
                    try {
                        System.out.print("Digite o id do curso que deseja apagar: ");
                        if(new ControleCurso().removerCurso(sc.nextInt())){
                            System.out.println("Curso removido com sucesso.");
                        }
                    } catch(Exception e){
                        System.out.println("Erro ao deletar.");
                    }finally{
                        menuCurso();
                    }
                    break;
                case 0:
                    limparTela();
                    menuInicial();
                    break;
                default:
                    erro(2);
            }
        }catch(Exception e){
            System.out.println("[-] Digite uma das opções mostradas na tela");
        }
    }

    // Menu Aluno
    // Menu do CRUD de Aluno
    public static void menuAluno(){
        System.out.println("--------------------------------------");
        System.out.println("Menu Aluno");
        System.out.println("1 - Listar");
        System.out.println("2 - Cadastrar");
        System.out.println("3 - Editar");
        System.out.println("4 - Apagar");
        System.out.println("0 - Voltar");
        System.out.println("--------------------------------------");
        System.out.print("Escolha a opção: ");
        try{
            switch(sc.nextInt()){
                case 1:
                	try{
                        ControleAluno con = new ControleAluno();
                        ArrayList<Aluno> lista = con.selecionarTodos();
                        if(lista != null){
                            System.out.println("| MATRICULA | NOME | EMAIL |");
                            for(int i = 0; i < lista.size() ; i++){
                                System.out.print("| " + lista.get(i).getMatricula());
                                System.out.print(" | " + lista.get(i).getNome());
                                System.out.print(" | " + lista.get(i).getEmail());
                                System.out.println(" | ");
                            }
                        }
                    }catch(Exception e){
                        System.out.println("Erro de execucao");
                    }finally{
                        menuAluno();
                    }
                    break;
                case 2:
                	try	{
                        ControleAluno con = new ControleAluno();
                        Aluno a = new Aluno();
                        System.out.print("Digite a matricula do aluno: ");
                        a.setMatricula(sc.nextInt());
                        System.out.print("Digite o nome do aluno: ");
                        a.setNome(sc.next());
                        System.out.print("Digite o email: ");
                        a.setEmail(sc.next());
                        if(con.inserirAluno(a)){
                            System.out.println("Aluno Cadastrado");
                        }
                    } catch(Exception e){
                        System.out.print("Erro ao inserir.");
                    } finally {
                        menuAluno();
                    }
                    break;
                case 3:
                	try {
                        System.out.print("Digite a matricula do aluno que deseja editar: ");
                        int matricula = sc.nextInt();
                        Aluno a = new Aluno();
                        a.setMatricula(matricula);
                        System.out.print("Digite o novo nome do aluno: ");
                        a.setNome(sc.next());
                        System.out.print("Digite o novo email do aluno: ");
                        a.setEmail(sc.next());
                        if(new ControleAluno().editarAluno(a, matricula)){
                            System.out.println("Aluno editado com sucesso");
                        }
                    }catch(Exception e){
                        System.out.println("Erro ao editar.");
                    }finally{
                        menuAluno();
                    }
                    break;
                case 4:
                	try {
                        System.out.print("Digite a matricula do aluno que deseja apagar: ");
                        if(new ControleAluno().removerAluno(sc.nextInt())){
                            System.out.println("Aluno removido com sucesso.");
                        }
                    } catch(Exception e){
                        System.out.println("Erro ao deletar o perfil do aluno.");
                    }finally{
                        menuAluno();
                    }
                    break;
                case 0:
                    limparTela();
                    menuInicial();
                    break;
                default:
                    erro(2);
            }       
        }catch(Exception e){
            System.out.println("[-] Digite uma das opções mostradas na tela");
        }
    }
    
    // Menu Turma
    // Menu do CRUD de Turma
    public static void menuTurma(){
        System.out.println("--------------------------------------");
        System.out.println("Menu Turma");
        System.out.println("1 - Listar");
        System.out.println("2 - Cadastrar");
        System.out.println("3 - Editar");
        System.out.println("4 - Apagar");
        System.out.println("0 - Voltar");
        System.out.println("--------------------------------------");
        System.out.print("Escolha a opcao: ");
        try{
            switch(sc.nextInt()){
                case 1:
                	try {
                        ControleTurma con = new ControleTurma();
                        ArrayList<Turma> lista = con.selecionarTodos();
                        if(lista != null){
                            System.out.println("| ID | SERIE |");
                            for(int i = 0; i < lista.size() ; i++){
                                System.out.print("| " + lista.get(i).getId());
                                System.out.print(" | " + lista.get(i).getSerie());
                                System.out.println(" | ");
                            }
                        }
                    } catch(Exception e){
                        System.out.println("Erro de execucao");
                    } finally {
                        menuTurma();
                    }
                    break;
                case 2:
                	try	{
                        ControleTurma con = new ControleTurma();
                        Turma t = new Turma();
                        System.out.print("Digite a serie da turma: ");
                        t.setSerie(sc.nextInt());
                        if(con.inserirTurma(t)){
                            System.out.println("Turma Cadastrado");
                        }
                    } catch(Exception e){
                        System.out.print("Erro ao inserir a turma.");
                    } finally {
                        menuTurma();
                    }
                    break;
                case 3:
                	try {
                        System.out.print("Digite o id da turma que deseja editar: ");
                        int id = sc.nextInt();
                        Turma t = new Turma();
                        t.setId(id);
                        System.out.print("Digite o novo numero da serie da turma: ");
                        t.setSerie(sc.nextInt());
                        if(new ControleTurma().editarTurma(t, id)){
                            System.out.println("Turma editado com sucesso");
                        }
                    }catch(Exception e){
                        System.out.println("Erro ao editar.");
                    }finally{
                        menuTurma();
                    }
                    break;
                case 4:
                	try {
                        System.out.print("Digite o id da turma que deseja apagar: ");
                        if(new ControleTurma().removerTurma(sc.nextInt())){
                            System.out.println("Turma removida com sucesso.");
                        }
                    } catch(Exception e){
                        System.out.println("Erro ao deletar a turma.");
                    }finally{
                        menuTurma();
                    }
                    break;
                case 0:
                    limparTela();
                    menuInicial();
                    break;
                default:
                    erro(2);
            }       
        }catch(Exception e){
            System.out.println("[-] Digite uma das opções mostradas na tela");
        }
    }

    public static void menuRelacao(){
        System.out.println("--------------------------------------");
        System.out.println("Menu Relação");
        System.out.println("1 - Listar");
        System.out.println("2 - Cadastrar");
        System.out.println("3 - Editar");
        System.out.println("4 - Apagar");
        System.out.println("0 - Voltar");
        System.out.println("--------------------------------------");
        System.out.print("Escolha a opção: ");
        try{
            switch(sc.nextInt()){
                case 1:
                	try{
                        ControleRelacionamento con = new ControleRelacionamento();
                        ArrayList<Relacionamento> lista = con.selecionarTodos();
                        System.out.println(lista.size());
                        System.out.println(lista);
                        if(lista != null){
                            System.out.println("| ID | ALUNOS | CURSO | TURMA |");
                            for(int i = 0; i < lista.size() ; i++){
                                System.out.print("| " + lista.get(i).getId());
                                System.out.print(" | " + lista.get(i).getAluno());
                                System.out.print(" | " + lista.get(i).getCurso());
                                System.out.print(" | " + lista.get(i).getTurma());
                                System.out.println(" | ");
                            }
                        }
                    }catch(Exception e){
                        System.out.println("Erro de execucao");
                    }finally{
                        menuRelacao();
                    }
                    break;
                case 2:
                	try	{
                        ControleRelacionamento con = new ControleRelacionamento();
                        Relacionamento r = new Relacionamento();
                        System.out.print("Digite o id do aluno: ");
                        r.setAluno(sc.nextInt());
                        System.out.print("Digite o id do curso: ");
                        r.setCurso(sc.nextInt());
                        System.out.print("Digite o id da turma do relacionamento: ");
                        r.setTurma(sc.nextInt());
                        if(con.inserirRelacionamento(r)){
                            System.out.println("Relacionamento Cadastrado");
                        }
                    } catch(Exception e){
                        System.out.print("Erro ao inserir.");
                    } finally {
                        menuRelacao();
                    }
                    break;
                case 3:
                	try {
                        System.out.print("Digite o id do relacionamento que deseja editar: ");
                        int id = sc.nextInt();
                        Relacionamento r = new Relacionamento();
                        r.setId(id);
                        System.out.print("Digite o id do aluno que ira substituir: ");
                        r.setAluno(sc.nextInt());
                        System.out.print("Digite o id do curso que ira substituir: ");
                        r.setCurso(sc.nextInt());
                        System.out.print("Digite o id do turma que ira substituir: ");
                        r.setTurma(sc.nextInt());
                        if(new ControleRelacionamento().editarRelacionamento(r, id)){
                            System.out.println("Relacionamento editado com sucesso");
                        }
                    }catch(Exception e){
                        System.out.println("Erro ao editar o relacionamento.");
                    }finally{
                        menuRelacao();
                    }
                    break;
                case 4:
                	try {
                        System.out.print("Digite o id do relacionamento que deseja apagar: ");
                        if(new ControleRelacionamento().removerRelacionamento(sc.nextInt())){
                            System.out.println("Relacionamento removido com sucesso.");
                        }
                    } catch(Exception e){
                        System.out.println("Erro ao deletar.");
                    }finally{
                        menuRelacao();
                    }
                    break;
                case 0:
                    limparTela();
                    menuInicial();
                    break;
                default:
                    erro(2);
            }       
        }catch(Exception e){
            System.out.println("[-] Digite uma das opções mostradas na tela");
        }
    }
}
