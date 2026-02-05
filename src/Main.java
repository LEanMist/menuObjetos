import java.lang.reflect.Array;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static ArrayList<Aluno> listaAlunos = new ArrayList<>();
    private static ArrayList<Turma> listaTurmas = new ArrayList<>();
    public static void main(String[] args) {
    }

    public static void menuPrincipal() {
        System.out.println("=== Secretaria ====");
        System.out.println("1 - Alunos");
        System.out.println("2 - Turmas");
        System.out.println("3 - Sair");
        String opcao = Leitura.dados("digite a opcção desejada: ");
        switch (opcao) {
            case "1":
                menuAlunos();
                break;

            case "2":
                menuTurmas();
                break;

            case "3":
            {
                System.out.println("Até breve ...");
                System.exit(0);
                break;
            }
            default:
                System.out.println("Opção Invalida! Tente Novamente");
        }
    }

    private static void menuTurmas() {
        System.out.println("=== Turmas ===");
        System.out.println("1 - Listar Turmas");
        System.out.println("2 - Cadastrar turma");
        System.out.println("3 - Atualizar turma");
        System.out.println("4 - Excluir turma");
        System.out.println("5 - Voltar ao menuPrincipal Principal");
        String opcao = Leitura.dados("digite a opcção desejada: ");
        switch (opcao) {
            case "1":
                listarTurmas();
                break;
            case "2":
                cadastrarTurma();
                break;
            case "3":
                atualizarTurma();
                break;
            case "4":
                excluirTurma();
                break;
            case "5":
                menuPrincipal();
                break;
            default:
                System.out.println("Opção Invalida! Tente Novamente");
        }
    }

    private static void menuAlunos() {
        System.out.println("=== Alunos ===");
        System.out.println("1 - Listar Alunos");
        System.out.println("2 - Cadastrar Aluno");
        System.out.println("3 - Atualizar aluno");
        System.out.println("4 - Excluir Aluno");
        System.out.println("5 - Voltar ao menuPrincipal principal");
        String opcao = Leitura.dados("digite a opcção desejada: ");
        switch (opcao) {
            case "1":
                listarAlunos();
                break;
            case "2":
                cadastrarAluno();
                break;
            case "3":
                atualizarAluno();
                break;
            case "4":
                excluirAluno();
                break;
            case "5":
                menuPrincipal();
                break;
            default:
                System.out.println("Opção Invalida! Tente Novamente");
        }

    }

    private static void excluirTurma() {
    }

    private static void atualizarTurma() {
    }

    private static void cadastrarTurma() {
    }

    private static void listarTurmas() {
        for(Turma t: listaTurmas){
            System.out.println(t);
        }
    }


    private static void excluirAluno() {
    }

    private static void atualizarAluno() {
    }

    private static void cadastrarAluno() {
    }

    private static void listarAlunos() {
        for(Aluno a: listaAlunos){
            System.out.println(a);
        }
    }
}