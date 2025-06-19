import java.util.*;

public class BibliotecaApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Livro> livros = new ArrayList<>();
        ArrayList<Emprestimo> emprestimos = new ArrayList<>();

        int op;
        do {
            System.out.println("\n========================");
            System.out.println("[1] Cadastro de Cliente");
            System.out.println("[2] Cadastro de Livro Comum");
            System.out.println("[3] Cadastro de Livro Raro");
            System.out.println("[4] Buscar livro por título");
            System.out.println("[5] Buscar livro por autor");
            System.out.println("[6] Realizar empréstimo");
            System.out.println("[7] Devolver livro");
            System.out.println("[8] Verificar disponibilidade");
            System.out.println("[0] Sair");
            op = sc.nextInt(); sc.nextLine();

            switch (op) {
                case 1 -> {
                    System.out.print("Nome do cliente: ");
                    clientes.add(new Cliente(sc.nextLine()));
                }
                case 2 -> {
                    System.out.print("Título: ");
                    String titulo = sc.nextLine();
                    System.out.print("Autor: ");
                    String autor = sc.nextLine();
                    livros.add(new Livro(titulo, autor, false, ""));
                }
                case 3 -> {
                    System.out.print("Título: ");
                    String titulo = sc.nextLine();
                    System.out.print("Autor: ");
                    String autor = sc.nextLine();
                    System.out.print("Explique por que é raro: ");
                    String explicacao = sc.nextLine();
                    livros.add(new Livro(titulo, autor, true, explicacao));
                }
                case 4 -> {
                    System.out.print("Digite o título: ");
                    String titulo = sc.nextLine();
                    livros.stream().filter(l -> l.getTitulo().equalsIgnoreCase(titulo)).findFirst()
                            .ifPresentOrElse(
                                    l -> System.out.println("Livro encontrado: " + l.getTitulo() + " - " + l.getAutor()),
                                    () -> System.out.println("Livro não encontrado."));
                }
                case 5 -> {
                    System.out.print("Digite o autor: ");
                    String autor = sc.nextLine();
                    livros.stream().filter(l -> l.getAutor().equalsIgnoreCase(autor)).findFirst()
                            .ifPresentOrElse(
                                    l -> System.out.println("Livro encontrado: " + l.getTitulo() + " - " + l.getAutor()),
                                    () -> System.out.println("Livro não encontrado."));
                }
                case 6 -> {
                    System.out.print("Nome do cliente: ");
                    String nome = sc.nextLine();
                    System.out.print("Título do livro: ");
                    String titulo = sc.nextLine();
                    Cliente cli = clientes.stream().filter(c -> c.getNome().equalsIgnoreCase(nome)).findFirst().orElse(null);
                    Livro liv = livros.stream().filter(l -> l.getTitulo().equalsIgnoreCase(titulo)).findFirst().orElse(null);
                    if (cli != null && liv != null) {
                        if (liv.isRaro()) {
                            System.out.println("Livro raro não pode ser emprestado.");
                        } else if (liv.isEmprestado()) {
                            System.out.println("Livro já está emprestado.");
                        } else {
                            liv.setEmprestado(true);
                            emprestimos.add(new Emprestimo(liv, cli));
                            System.out.println("Empréstimo realizado com sucesso!");
                        }
                    } else {
                        System.out.println("Cliente ou livro não encontrado.");
                    }
                }
                case 7 -> {
                    System.out.print("Título do livro: ");
                    String titulo = sc.nextLine();
                    Optional<Emprestimo> emp = emprestimos.stream()
                            .filter(e -> e.getLivro().getTitulo().equalsIgnoreCase(titulo))
                            .findFirst();

                    if (emp.isPresent()) {
                        Livro liv = emp.get().getLivro();
                        liv.setEmprestado(false);
                        double multa = emp.get().calcularMulta();
                        emprestimos.remove(emp.get());
                        System.out.printf("Devolução feita. Multa: R$ %.2f\n", multa);
                    } else {
                        System.out.println("Empréstimo não encontrado.");
                    }
                }
                case 8 -> {
                    System.out.print("Título: ");
                    String titulo = sc.nextLine();
                    Livro liv = livros.stream().filter(l -> l.getTitulo().equalsIgnoreCase(titulo)).findFirst().orElse(null);
                    if (liv != null) {
                        if (liv.isRaro()) {
                            System.out.println("Livro raro - não pode ser emprestado.");
                        } else {
                            System.out.println(liv.isEmprestado() ? "Indisponível." : "Disponível.");
                        }
                    } else {
                        System.out.println("Livro não encontrado.");
                    }
                }
            }
        } while (op != 0);

        sc.close();
    }
}

