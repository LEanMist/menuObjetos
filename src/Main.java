import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.Period;
import java.time.format.DateTimeParseException;


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
        if(isVazio(listaTurmas)) {
            System.out.println("Não ha turmas cadastradas");
            return;
        }
        listaTurmasIndiceSigla();

        int idExcluir = validaIdTurma();

        if (confirmaExclusao()){
//            listaTurmas.remove(opcaoUsuario);

            listaTurmas.get(idExcluir).setAtivo(false);
            System.out.println(listaTurmas.get(idExcluir).getCurso() + " excluida com sucesso!");
        }
        menuTurmas();
    }

    private static boolean isVazio(ArrayList<Turma> listaTurmas) {
        if (listaTurmas.isEmpty()) return true;
        for(Turma turma : listaTurmas){
            if(turma.isAtivo()) return  false;
        }
        return true;
    }

    private static boolean confirmaExclusao() {
        while (true){
            String confirma = Leitura.dados("Você tem certeza? (S/N): ").toUpperCase();
            switch (confirma){
                case "S":
                    return true;
                case "N":
                    return false;
                default:
                    System.out.println("Opção invalida, digite S para sim ou N para não!");
                    break;
            }
        }
    }

    private static int validaitemLista(String opcao) {
        if (opcao.isBlank()) return -1;

        int opcaoNumero = -1;

        try{
            opcaoNumero = Integer.parseInt(opcao);
        } catch (NumberFormatException e) {
            return -1;
        }

        int indiceLista = opcaoNumero-1;
        return indiceLista >= 0 && listaTurmas.size() > indiceLista ? indiceLista : -1;
    }

    private static void listaTurmasIndiceSigla() {
        System.out.println("\nLista das Turmas: ");
        for (int i=0;i<listaTurmas.size();i++){
            if (listaTurmas.get(i).isAtivo())
                System.out.printf("\n%d - %s",i+1, listaTurmas.get(i).getSigla());
        }
    }

    private static void atualizarTurma() {
        if(isVazio(listaTurmas)) {
            System.out.println("Não ha turmas cadastradas");
            return;
        }
        listaTurmasIndiceSigla();

        int idAtualizar = validaIdTurma();

        System.out.printf("O periodo atual é: "+ listaTurmas.get(idAtualizar).getPeriodo());
        atualizarParcial("periodo", idAtualizar);

        System.out.println("O curso atual é: "+ listaTurmas.get(idAtualizar).getCurso());
        atualizarParcial("curso", idAtualizar);

        System.out.println("A sigla atual é: "+ listaTurmas.get(idAtualizar).getSigla());
        atualizarParcial("sigla", idAtualizar);
    }

    private static void atualizarParcial(String atributo, int idAtualizar) {
        //1 - Periodo, 2 - Curso, 3 - Sigla
        boolean rodarNovamente = true;
        while (rodarNovamente) {
            String opcao = Leitura.dados("\nDeseja modificar "+ atributo +" ? (S/N): ").toUpperCase();
            switch (opcao) {
                case "S":
                    switch (atributo){
                        case "periodo":
                            Periodo periodo = validarPeriodo();
                            listaTurmas.get(idAtualizar).setPeriodo(periodo);
                            break;
                        case "curso":
                            String curso = validarCurso();
                            listaTurmas.get(idAtualizar).setCurso(curso);
                            break;
                        case "sigla":
                            String sigla = validarSigla();
                            listaTurmas.get(idAtualizar).setSigla(sigla);
                            break;
                    }

                    System.out.println(atributo + " atualizado com sucesso");
                    rodarNovamente = false;
                    break;
                case "N":
                    System.out.println("adeus");
                    rodarNovamente = false;
                    break;
                default:
                    System.out.println("Opçao invalida! Escolha S para SIM ou N para NÃO");
            }
        }

    }

    private static String validarSigla() {
        String sigla = Leitura.dados("Digite a sigla: ");
        while(!validarSigla(sigla)){
            System.out.println("Sigla inválida! Precisa conter texto e não pode ser repetida");
            sigla = Leitura.dados("Digite a sigla: ");
        }
        return sigla;
    }

    private static String validarCurso() {
        String curso = Leitura.dados("Digite o nome do curso: ");
        while(!isCharacter(curso)) {
            System.out.println("Nome de curso inválido! Não use números ou caracteres especiais, por favor");
            curso = Leitura.dados("Digite o nome do curso: ");
        }
        return curso;
    }

    private static int validaIdTurma() {
        String opcao = Leitura.dados("\nDigite o numero da turma desejada: ");
        int opcaoValida = -1;
        int opcaoUsuario = -1;
        while(opcaoValida==-1) {
            opcaoUsuario = validaitemLista(opcao);

            if (opcaoUsuario == -1) {
                System.out.println("opção inválida! Digite novamente");
                opcao = Leitura.dados("Digite o número da turma desejada");
            } else {
                opcaoValida = opcaoUsuario;
            }
        }
        return opcaoValida;
    }

    private static void cadastrarTurma() {
        Periodo periodo = validarPeriodo();
        String curso = validarCurso();
        String sigla = validarSigla();

        Turma turma = new Turma(curso, sigla, periodo);
        listaTurmas.add(turma);
        System.out.println("Turma cadastrada com sucesso");
        menuTurmas();
    }

    private static boolean validarSigla(String sigla) {
        if(sigla.isBlank()) return false;

        for (Turma turma : listaTurmas) {
            if (turma.getSigla().equals(sigla)) return false;

        }
        return true;
    }

    private static boolean isCharacter(String texto) {
        return texto != null && !texto.isBlank() && texto.matches("[a-zA-ZÀ-ÿ ]+");
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
                return Periodo.VESPERTINO;
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
        if(isVazio(listaTurmas)) {
            System.out.println("Não ha turmas cadastradas");
            return;
        }
        for(Turma t: listaTurmas){
            if (t.isAtivo())
                System.out.println(t);
        }
    }

    //AREA DE ALUNOS
    private static void listarAlunos() {
        if(isvazioAluno(listaAlunos)) {
            System.out.println("Não ha turmas cadastradas");
            return;
        }
        for(Aluno a: listaAlunos){
            if (a.isAtivo())
                System.out.println(a);
        }
    }

    private static boolean isvazioAluno(ArrayList<Aluno> listaAlunos) {
        if (listaAlunos.isEmpty()) return true;
        for(Aluno aluno : listaAlunos){
            if(aluno.isAtivo()) return  false;
        }
        return true;
    }

    private static void cadastrarAluno() {
        String nome = validarNome();
        LocalDate dataNascimento = validarDataNascimento();
        Turma turma = validarTurma();

        Aluno aluno = new Aluno(nome, dataNascimento, turma);
        listaAlunos.add(aluno);
    }

    private static String validarNome() {
        String nome = Leitura.dados("Digite o nome do aluno: ");
        while(!isCharacter(nome)) {
            System.out.println("Nome do aluno inválido! Não deixe em branco, não use números ou caracteres especiais, por favor");
            nome = Leitura.dados("Digite o nome do aluno: ");
        }
        return nome;
    }

    private static LocalDate validarDataNascimento() {
        DateTimeFormatter formatarData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true){
            String dataTexto = Leitura.dados("Digite a data de nascimento (dd/MM/yyyy): ");

            if (dataTexto.isBlank()) {
                System.out.println("A data não pode ser vazia!");
                continue;
            }

            try {

                LocalDate dataNascimento = LocalDate.parse(dataTexto, formatarData);

                int idade = Period.between(dataNascimento, LocalDate.now()).getYears();

                if (idade < 14) {
                    System.out.println("O aluno precisa ter pelo menos 14 anos.");
                    menuAlunos();
                    return null;
                }

                if (idade > 130) {
                    System.out.println("Idade inválida. Não é permitido cadastrar pessoas com mais de 130 anos.");
                    menuAlunos();
                    return null;
                }

                return dataNascimento;

            } catch (DateTimeParseException e) {
                System.out.println("Data inválida! Digite novamente no formato dd/MM/yyyy.");
            }

        }
    }

    private static Turma validarTurma() {
        if (isVazio(listaTurmas)) {
            System.out.println("Não há turmas cadastradas. Cadastre uma turma primeiro.");
            menuAlunos();
            return null;
        }

        listaTurmasIndiceSigla();

        int idTurma = validaIdTurma();

        return listaTurmas.get(idTurma);
    }

    private static void atualizarAluno() {
        if(isvazioAluno(listaAlunos)) {
            System.out.println("Não ha alunos cadastrados");
            return;
        }
        listaAlunosIndiceNome();

        int idAtualizar = validaIdAluno();

        System.out.println("O nome atual é: "+ listaAlunos.get(idAtualizar).getNome());
        atualizarAlunoParcial("Nome", idAtualizar);

        System.out.println("A data de nascimeto atual é: "+ listaAlunos.get(idAtualizar).getDataNascimento());
        atualizarAlunoParcial("Data de Nascimento", idAtualizar);

        System.out.println("A turma do aluno é: "+ listaAlunos.get(idAtualizar).getTurma());
        atualizarAlunoParcial("Turma", idAtualizar);

    }

    private static void atualizarAlunoParcial(String atributo, int idAtualizar) {
        boolean rodarNovamente = true;
        while (rodarNovamente) {
            String opcao = Leitura.dados("\nDeseja modificar "+ atributo +" ? (S/N): ").toUpperCase();
            switch (opcao) {
                case "S":
                    switch (atributo){
                        case "Nome":
                            String nome = validarNome();
                            listaAlunos.get(idAtualizar).setNome(nome);
                            break;
                        case "Data de Nascimento":
                            LocalDate dataDeNascimento = validarDataNascimento();
                            listaAlunos.get(idAtualizar).setDataNascimento(dataDeNascimento);
                            break;
                        case "Turma":
                            Turma turma = validarTurma();
                            listaAlunos.get(idAtualizar).setTurma(turma);
                            break;
                    }

                    System.out.println(atributo + " atualizado com sucesso");
                    rodarNovamente = false;
                    break;
                case "N":
                    System.out.println("adeus");
                    rodarNovamente = false;
                    break;
                default:
                    System.out.println("Opçao invalida! Escolha S para SIM ou N para NÃO");
            }
        }
    }

    private static int validaIdAluno() {
        String opcao = Leitura.dados("\nDigite o numero do aluno desejado: ");
        int opcaoValida = -1;
        int opcaoUsuario = -1;
        while(opcaoValida==-1) {
            opcaoUsuario = validaAlunoLista(opcao);

            if (opcaoUsuario == -1) {
                System.out.println("opção inválida! Digite novamente");
                opcao = Leitura.dados("Digite o número do aluno desejado");
            } else {
                opcaoValida = opcaoUsuario;
            }
        }
        return opcaoValida;
    }

    private static int validaAlunoLista(String opcao) {
        if (opcao.isBlank()) return -1;

        int opcaoNumero = -1;

        try{
            opcaoNumero = Integer.parseInt(opcao);
        } catch (NumberFormatException e) {
            return -1;
        }

        int indiceLista = opcaoNumero-1;
        return indiceLista >= 0 && listaAlunos.size() > indiceLista ? indiceLista : -1;
    }

    private static void listaAlunosIndiceNome() {
        System.out.println("\nLista dos Alunos: ");
        for (int i=0;i<listaAlunos.size();i++){
            if (listaAlunos.get(i).isAtivo())
                System.out.printf("\n%d - %s",i+1, listaAlunos.get(i).getNome());
        }
    }

    private static void excluirAluno() {
        if(isvazioAluno(listaAlunos)) {
            System.out.println("Não ha Alunos cadastrados");
            return;
        }
        listaAlunosIndiceNome();

        int idExcluir = validaIdAluno();

        if (confirmaExclusao()){
//            listaTurmas.remove(opcaoUsuario);

            listaAlunos.get(idExcluir).setAtivo(false);
            System.out.println(listaAlunos.get(idExcluir).getNome() + " excluido com sucesso");
        }
        menuAlunos();
    }
}