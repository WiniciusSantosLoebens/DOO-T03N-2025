import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import objetos.Leitor;
import objetos.Livros;

public class Avaliacao {
    static Scanner scan = new Scanner(System.in);
    static ArrayList<Leitor> leitores = new ArrayList<>();
    static ArrayList<Livros> livros = new ArrayList<>();

    public static void main(String[] args) {
        carregarDadosTeste();
        while (true) {
            menuPrincipal();
        }
    }

    static void carregarDadosTeste() {
        livros.add(new Livros("Dom Casmurro", "L001", "Machado de Assis", "1899", false, ""));
        livros.add(new Livros("Primeira Edição de Os Lusíadas", "L002", "Luís de Camões", "1572", true, "Edição original do século XVI"));
        leitores.add(new Leitor("João Silva", "LEI001", "11 9999-8888"));
        leitores.add(new Leitor("Maria Souza", "LEI002", "11 9777-6666"));
    }

    static void menuPrincipal() {
        System.out.println("\n=== SISTEMA BIBLIOTECA ===");
        System.out.println("1. Acesso Leitor");
        System.out.println("2. Acesso Bibliotecário");
        System.out.println("3. Sair");
        System.out.print("Escolha: ");
        
        switch (lerInteiro(1, 3)) {
            case 1 -> menuLeitor();
            case 2 -> menuBibliotecario();
            case 3 -> {
                System.out.println("Sistema encerrado!");
                System.exit(0);
            }
        }
    }

    static void menuLeitor() {
        System.out.print("\nDigite seu ID de leitor: ");
        String leitorID = scan.nextLine();
        Leitor leitor = buscarLeitor(leitorID);
        
        if (leitor == null) {
            System.out.println("Leitor não encontrado!");
            return;
        }

        while (true) {
            System.out.println("\n--- MENU LEITOR ---");
            System.out.println("1. Buscar Livro por Título");
            System.out.println("2. Buscar Livro por Autor");
            System.out.println("3. Verificar Disponibilidade");
            System.out.println("4. Realizar Empréstimo");
            System.out.println("5. Devolver Livro");
            System.out.println("6. Pagar Multa");
            System.out.println("7. Voltar");
            System.out.print("Escolha: ");
            
            switch (lerInteiro(1, 7)) {
                case 1 -> buscarPorTitulo();
                case 2 -> buscarPorAutor();
                case 3 -> verificarDisponibilidade();
                case 4 -> realizarEmprestimo(leitor);
                case 5 -> realizarDevolucao(leitor);
                case 6 -> pagarMulta(leitor);
                case 7 -> { return; }
            }
        }
    }

    static void menuBibliotecario() {
        while (true) {
            System.out.println("\n--- MENU BIBLIOTECÁRIO ---");
            System.out.println("1. Cadastrar Leitor");
            System.out.println("2. Cadastrar Livro Comum");
            System.out.println("3. Cadastrar Livro Raro");
            System.out.println("4. Buscar Livro por Título");
            System.out.println("5. Buscar Livro por Autor");
            System.out.println("6. Listar Todos os Livros");
            System.out.println("7. Listar Todos os Leitores");
            System.out.println("8. Relatório de Empréstimos Ativos");
            System.out.println("9. Histórico Completo");
            System.out.println("10. Voltar");
            System.out.print("Escolha: ");
            
            switch (lerInteiro(1, 10)) {
                case 1 -> cadastrarLeitor();
                case 2 -> cadastrarLivro(false);
                case 3 -> cadastrarLivroRaro();
                case 4 -> buscarPorTitulo();
                case 5 -> buscarPorAutor();
                case 6 -> listarTodosLivros();
                case 7 -> listarTodosLeitores();
                case 8 -> relatorioEmprestimosAtivos();
                case 9 -> historicoCompleto();
                case 10 -> { return; }
            }
        }
    }

    // Métodos de operações
    static void realizarEmprestimo(Leitor leitor) {
        if (!leitor.getLivroEmprestado().equals("Nenhum")) {
            System.out.println("Você já tem um livro emprestado!");
            return;
        }

        System.out.print("Digite o ID do livro: ");
        String livroID = scan.nextLine();
        Livros livro = buscarLivro(livroID);
        
        if (livro == null) {
            System.out.println("Livro não encontrado!");
            return;
        }
        
        if (livro.isRaro()) {
            System.out.println("Este livro é raro e não pode ser emprestado!");
            System.out.println("Motivo: " + livro.getMotivoRaridade());
            return;
        }
        
        if (!livro.isDisponivel()) {
            System.out.println("Livro já emprestado!");
            return;
        }

        LocalDate dataEmprestimo = lerData("Data do empréstimo");
        livro.emprestar(leitor.getID(), dataEmprestimo);
        leitor.setLivroEmprestado(livro.getID(), dataEmprestimo);
        System.out.println("Empréstimo realizado com sucesso!");
    }

    static void realizarDevolucao(Leitor leitor) {
        if (leitor.getLivroEmprestado().equals("Nenhum")) {
            System.out.println("Você não tem livros para devolver!");
            return;
        }

        LocalDate dataDevolucao = lerData("Data da devolução");
        if (dataDevolucao.isBefore(leitor.getDataEmprestimo())) {
            System.out.println("Data de devolução não pode ser anterior à data de empréstimo!");
            return;
        }

        Livros livro = buscarLivro(leitor.getLivroEmprestado());
        if (livro != null) {
            double multa = leitor.finalizarEmprestimo(dataDevolucao);
            livro.devolver();
            
            if (multa > 0) {
                System.out.printf("Multa por atraso: R$ %.2f%n", multa);
            }
            System.out.println("Devolução realizada com sucesso!");
        }
    }

    // Métodos auxiliares
    static LocalDate lerData(String mensagem) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            try {
                System.out.print(mensagem + " (dd/MM/yyyy): ");
                return LocalDate.parse(scan.nextLine(), formatter);
            } catch (Exception e) {
                System.out.println("Formato inválido! Use dd/MM/yyyy");
            }
        }
    }

    static int lerInteiro(int min, int max) {
        while (true) {
            try {
                int valor = Integer.parseInt(scan.nextLine());
                if (valor >= min && valor <= max) return valor;
                System.out.print("Valor inválido! Tente novamente: ");
            } catch (NumberFormatException e) {
                System.out.print("Digite um número válido: ");
            }
        }
    }

    static double lerDouble() {
        while (true) {
            try {
                return Double.parseDouble(scan.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Digite um valor válido: ");
            }
        }
    }

    // ... (outros métodos mantidos conforme versão anterior)
    static Livros buscarLivro(String id) {
        return livros.stream()
            .filter(l -> l.getID().equalsIgnoreCase(id))
            .findFirst()
            .orElse(null);
    }

    static Leitor buscarLeitor(String id) {
        return leitores.stream()
            .filter(l -> l.getID().equalsIgnoreCase(id))
            .findFirst()
            .orElse(null);
    }

    static void buscarPorTitulo() {
        System.out.print("\nDigite o título para busca: ");
        String titulo = scan.nextLine();
        
        livros.stream()
            .filter(l -> l.getNome().equalsIgnoreCase(titulo))
            .findFirst()
            .ifPresentOrElse(
                System.out::println,
                () -> System.out.println("Livro não encontrado!")
            );
    }

    static void buscarPorAutor() {
        System.out.print("\nDigite o autor para busca: ");
        String autor = scan.nextLine();
        
        livros.stream()
            .filter(l -> l.getAutor().equalsIgnoreCase(autor))
            .findFirst()
            .ifPresentOrElse(
                System.out::println,
                () -> System.out.println("Nenhum livro deste autor encontrado!")
            );
    }

    static void verificarDisponibilidade() {
        System.out.print("\nDigite o título do livro: ");
        String titulo = scan.nextLine();
        
        livros.stream()
            .filter(l -> l.getNome().equalsIgnoreCase(titulo))
            .findFirst()
            .ifPresentOrElse(
                l -> System.out.println(l.isDisponivel() ? 
                    "Disponível para empréstimo" : "Indisponível"),
                () -> System.out.println("Livro não encontrado!")
            );
    }

    static void pagarMulta(Leitor leitor) {
        if (leitor.getMultaPendente() <= 0) {
            System.out.println("Você não tem multas pendentes!");
            return;
        }

        System.out.printf("Multa pendente: R$ %.2f%n", leitor.getMultaPendente());
        System.out.print("Digite o valor a pagar: ");
        double valor = lerDouble();
        
        if (valor > 0 && valor <= leitor.getMultaPendente()) {
            leitor.pagarMulta(valor);
            System.out.printf("Pagamento de R$ %.2f realizado!%n", valor);
        } else {
            System.out.println("Valor inválido!");
        }
    }

    static void cadastrarLeitor() {
        System.out.println("\nNovo Leitor:");
        System.out.print("Nome: ");
        String nome = scan.nextLine();
        
        String id;
        do {
            System.out.print("ID (único): ");
            id = scan.nextLine().toUpperCase();
        } while (buscarLeitor(id) != null);
        
        System.out.print("Telefone: ");
        String telefone = scan.nextLine();
        
        leitores.add(new Leitor(nome, id, telefone));
        System.out.println("Leitor cadastrado com sucesso!");
    }

    static void cadastrarLivro(boolean raro) {
        System.out.println("\nNovo Livro " + (raro ? "Raro" : "Comum") + ":");
        System.out.print("Título: ");
        String titulo = scan.nextLine();
        
        String id;
        do {
            System.out.print("ID (único): ");
            id = scan.nextLine().toUpperCase();
        } while (buscarLivro(id) != null);
        
        System.out.print("Autor: ");
        String autor = scan.nextLine();
        
        System.out.print("Ano: ");
        String ano = scan.nextLine();
        
        String motivo = "";
        if (raro) {
            System.out.print("Motivo da raridade: ");
            motivo = scan.nextLine();
        }
        
        livros.add(new Livros(titulo, id, autor, ano, raro, motivo));
        System.out.println("Livro " + (raro ? "raro" : "comum") + " cadastrado com sucesso!");
    }

    static void cadastrarLivroRaro() {
        cadastrarLivro(true);
    }

    static void listarTodosLivros() {
        System.out.println("\n=== TODOS OS LIVROS ===");
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }
        livros.forEach(System.out::println);
    }

    static void listarTodosLeitores() {
        System.out.println("\n=== TODOS OS LEITORES ===");
        if (leitores.isEmpty()) {
            System.out.println("Nenhum leitor cadastrado.");
            return;
        }
        leitores.forEach(System.out::println);
    }

    static void relatorioEmprestimosAtivos() {
        System.out.println("\n=== EMPRÉSTIMOS ATIVOS ===");
        long ativos = livros.stream()
            .filter(l -> !l.isDisponivel() && !l.isRaro())
            .peek(l -> {
                System.out.println(l);
                Leitor leitor = buscarLeitor(l.getLeitorAtual());
                if (leitor != null) {
                    System.out.println("Detalhes do Leitor:");
                    System.out.println(leitor);
                }
            })
            .count();
        
        if (ativos == 0) {
            System.out.println("Nenhum empréstimo ativo no momento.");
        }
    }

    static void historicoCompleto() {
        System.out.println("\n=== HISTÓRICO COMPLETO ===");
        System.out.println("Total de Livros: " + livros.size());
        System.out.println("Livros Comuns: " + livros.stream().filter(l -> !l.isRaro()).count());
        System.out.println("Livros Raros: " + livros.stream().filter(Livros::isRaro).count());
        System.out.println("Livros Emprestados: " + 
            livros.stream().filter(l -> !l.isDisponivel() && !l.isRaro()).count());
        System.out.println("Total de Leitores: " + leitores.size());
        System.out.printf("Total em Multas: R$ %.2f%n", 
            leitores.stream().mapToDouble(Leitor::getMultaPendente).sum());
    }
}