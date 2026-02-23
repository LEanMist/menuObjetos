import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static ArrayList<Aluno> listaAlunos = new ArrayList<>();
    private static ArrayList<Turma> listaTurmas = new ArrayList<>();

    public static void main(String[] args) {
        menuPrincipal();
    }

    public static void menuPrincipal() {
        System.out.println("=== Secretaria ====");
        System.out.println("1 - Alunos");
        System.out.println("2 - Turmas");
        System.out.println("3 - Sair");
        String opcao = Leitura.dados("digite a opção desejada: ");
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
                menuPrincipal();
        }
    }

    private static void menuTurmas() {
        System.out.println("=== Turmas ===");
        System.out.println("1 - Listar Turmas");
        System.out.println("2 - Cadastrar turma");
        System.out.println("3 - Atualizar turma");
        System.out.println("4 - Excluir turma");
        System.out.println("5 - Voltar ao menu Principal");
        String opcao = Leitura.dados("digite a opção desejada: ");
        switch (opcao) {
            case "1":
                listarTurmas();
                menuTurmas();
                break;
            case "2":
                cadastrarTurma();
                menuTurmas();
                break;
            case "3":
                atualizarTurma();
                menuTurmas();
                break;
            case "4":
                excluirTurma();
                menuTurmas();
                break;
            case "5":
                menuPrincipal();
                break;
            default:
                System.out.println("Opção Invalida! Tente Novamente");
                menuTurmas();
        }
    }

    private static void menuAlunos() {
        System.out.println("=== Alunos ===");
        System.out.println("1 - Listar Alunos");
        System.out.println("2 - Cadastrar Aluno");
        System.out.println("3 - Atualizar aluno");
        System.out.println("4 - Excluir Aluno");
        System.out.println("5 - Voltar ao menu principal");
        String opcao = Leitura.dados("digite a opção desejada: ");
        switch (opcao) {
            case "1":
                listarAlunos();
                menuAlunos();
                break;
            case "2":
                cadastrarAluno();
                menuAlunos();
                break;
            case "3":
                atualizarAluno();
                menuAlunos();
                break;
            case "4":
                excluirAluno();
                menuAlunos();
                break;
            case "5":
                menuPrincipal();
                break;
            default:
                System.out.println("Opção Invalida! Tente Novamente");
                menuAlunos();
        }

    }

    private static void excluirTurma() {
    }

    private static void atualizarTurma() {
    }

    private static void cadastrarTurma() {
        Periodo periodo = validarPeriodo();

        String curso = Leitura.dados("Digite o curso");
        while(!isCharacter(curso)) {
            System.out.println("Nome de curso invalido! Não use números ou caracteres especiais");
            curso = Leitura.dados("Digite o curso");
        }
        String sigla = Leitura.dados("Digite a sigla");
        while(!validarSigla(sigla)){
            System.out.println("Sigla invalida! precisa conster texto e nao pode ser repetida");
            sigla = Leitura.dados("Digite a sigla");
        }

        Turma turma = new Turma(curso, sigla, periodo);
        listaTurmas.add(turma);
        System.out.println("Turma cadastrada com sucess");
        menuTurmas();

    }

    private static boolean validarSigla(String sigla) {
        if(!sigla.isBlank()) return false;

        for (Turma turma : listaTurmas) {
            if (turma.getSigla().equals(sigla)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isCharacter(String texto) {
        String textoSemNumeros = texto.replaceAll("\\d","");
        return !texto.isBlank() && texto.equals(textoSemNumeros);
    }

    private static Periodo validarPeriodo() {
        String opcaoPeriodo = Leitura.dados("""
                Digite o numero do periodo escolhido:
                1 - Matutino
                2 - Vespertino
                3 - Noturno
                4 - Integral""");
        switch (opcaoPeriodo){
            case "1":
                return Periodo.MATUTINO;
            case "2":
                return Periodo.VERPERTINO;
            case "3":
                return Periodo.NOTURNO;
            case "4":
                return Periodo.INTEGRAL;
            default:
                System.out.println("Opcao invalida, digite novamente");
                return validarPeriodo();
        }
    }

    private static void listarTurmas() {
        if(listaTurmas.isEmpty()) {
            System.out.println("Não ha turmas cadastradas");
            return;
        }
        for(Turma t: listaTurmas){
            System.out.println(t);
        }
    }

    // AREA DE ALUNOS

    private static void excluirAluno() {
    }

    private static void atualizarAluno() {
    }

    private static void cadastrarAluno() {
    }

    private static void listarAlunos() {
        if(listaAlunos.isEmpty()){
            System.out.println("Não ha alunos cadastradas");
            return;
        }
        for(Aluno a: listaAlunos){
            System.out.println(a);
        }
    }
}