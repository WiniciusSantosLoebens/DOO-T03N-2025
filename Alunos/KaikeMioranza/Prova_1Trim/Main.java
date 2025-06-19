import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Livro> listarLivros = new ArrayList<>();
        boolean continuar = true;

        do {
            try { 
                System.out.println("\n---- Biblioteca ----");
                System.out.println("[1] - Cadastrar Cliente");
                System.out.println("[2] - Cadastrar Livro");
                System.out.println("[3] - Mostrar/Buscar Livros");
                System.out.println("[4] - Emprestar Livro");
                System.out.println("[5] - Devolver Livro");
                System.out.println("[6] - Verificar Disponibilidade");
                System.out.println("[0] - Sair");
                System.out.print("Escolha: ");
                int option = sc.nextInt();

                switch (option) {
                    case 0:
                        continuar = false;
                        System.out.println("Saindo...");
                        break;

                    case 1:
                        sc.nextLine();
                        System.out.println("--- Cadastro de Cliente ---");
                        System.out.print("Nome: ");
                        String nome = sc.nextLine();
                        System.out.print("CPF: ");
                        String cpf = sc.nextLine();
                        System.out.print("Telefone: ");
                        String tel = sc.nextLine();
                        Cliente cliente = new Cliente(nome, cpf, tel);
                        System.out.println("Cliente cadastrado com sucesso!");
                        break;

                    case 2:
                        sc.nextLine();
                        System.out.println("---Cadastro de Livro---");
                        System.out.print("Título: ");
                        String titulo = sc.nextLine();
                        System.out.print("Autor: ");
                        String autor = sc.nextLine();
                        System.out.println("Tipo: [1] Comum [2] Raro");
                        int tipo = sc.nextInt();
                        sc.nextLine();
                        String tipoLivro;
                        if (tipo == 2) {
                            System.out.print("Motivo da raridade: ");
                            tipoLivro = sc.nextLine();
                        } else {
                            tipoLivro = "Comum";
                        }
                        Livro livro = new Livro(titulo, autor, tipoLivro, false);
                        listarLivros.add(livro);
                        System.out.println("Livro cadastrado com sucesso!");
                        break;

                    case 3:
                        sc.nextLine();
                        System.out.println("--- Livros ---");
                        System.out.println("[1] Buscar por Título");
                        System.out.println("[2] Buscar por Autor");
                        System.out.println("[3] Mostrar Todos");
                        int buscar = sc.nextInt();
                        sc.nextLine();
                        if (buscar == 1) {
                            System.out.print("Título: ");
                            String buscaTitulo = sc.nextLine();
                            Livro l = listarLivros.stream().filter(b -> b.getTitulo().equalsIgnoreCase(buscaTitulo)).findFirst().orElse(null);
                            System.out.println(l != null ? l.mostrarLivros() : "Livro não encontrado.");
                        } else if (buscar == 2) {
                            System.out.print("Autor: ");
                            String buscaAutor = sc.nextLine();
                            Livro l = listarLivros.stream().filter(b -> b.getAutor().equalsIgnoreCase(buscaAutor)).findFirst().orElse(null);
                            System.out.println(l != null ? l.mostrarLivros() : "Livro não encontrado.");
                        } else {
                            for (Livro l : listarLivros) {
                                System.out.println(l.mostrarLivros());
                            }
                        }
                        break;

                    case 4:
                        sc.nextLine();
                        System.out.print("Título do livro para empréstimo: ");
                        String tituloEmprestimo = sc.nextLine();
                        Livro livroEmprestar = listarLivros.stream().filter(l -> l.getTitulo().equalsIgnoreCase(tituloEmprestimo)).findFirst().orElse(null);
                        if (livroEmprestar != null && !livroEmprestar.isEmprestado()) {
                            livroEmprestar.emprestar();
                            System.out.println("Livro emprestado com sucesso!");
                        } else {
                            System.out.println("Livro não encontrado ou já emprestado.");
                        }
                        break;

                    case 5:
                        sc.nextLine();
                        System.out.print("Título do livro para devolução: ");
                        String tituloDevolucao = sc.nextLine();
                        Livro livroDevolver = listarLivros.stream().filter(l -> l.getTitulo().equalsIgnoreCase(tituloDevolucao)).findFirst().orElse(null);
                        if (livroDevolver != null && livroDevolver.isEmprestado()) {
                            double multa = livroDevolver.devolver();
                            if (multa > 0) {
                                System.out.printf("Livro devolvido com multa de R$ %.2f%n", multa);
                            } else {
                                System.out.println("Livro devolvido sem multa.");
                            }
                        } else {
                            System.out.println("Livro não encontrado ou não está emprestado.");
                        }
                        break;

                    case 6:
                        sc.nextLine();
                        System.out.print("Informe o título: ");
                        String tituloDisponivel = sc.nextLine();
                        Livro lDisp = listarLivros.stream().filter(l -> l.getTitulo().equalsIgnoreCase(tituloDisponivel)).findFirst().orElse(null);
                        if (lDisp != null) {
                            System.out.println(lDisp.isEmprestado() ? "Livro emprestado." : "Livro disponível.");
                        } else {
                            System.out.println("Livro não encontrado.");
                        }
                        break;

                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            } catch (Exception e){
                System.err.println("Opção incorreta");
            }
        } while (continuar);

        sc.close();
    }
}
