import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Main {

    private static final Biblioteca biblioteca = new Biblioteca();
    private static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));


    public static void main(String[] args) {
        menu();
    }

    private static void menu() {
        while (true) {
            try {
                printMenu();
                int userChoice = readInt();

                switch (userChoice) {
                    case 1:
                        cadastrarCliente();
                        break;
                    case 2:
                        cadastrarLivro();
                        break;
                    case 3:
                        pesquisarLivro();
                        break;
                    case 4:
                        realizarEmprestimo();
                        break;
                    case 5:
                        realizarDevolucao();
                        break;
                    case 6:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Não é uma opção válida!");
                        break;
                }
            } catch (Exception ignored) {
            }
        }
    }

    private static void cadastrarCliente() {
        outer:
        while (true) {
            try {
                System.out.println("Digite o id: ");
                int id = readInt();

                for (Cliente cliente : biblioteca.getClientes()) {
                    if (cliente.getId() == id) {
                        System.out.println("O id ja existe, digite outro!");
                        break outer;
                    }
                }

                System.out.println("Digite o nome: ");
                String nome = input.readLine();

                System.out.println("Digite a idade: ");
                int idade = readInt();

                biblioteca.addCliente(new Cliente(id, nome, idade, new ArrayList<>()));

                System.out.println("Cliente cadastrado com sucesso!");
                return;

            } catch (Exception e) {
                System.out.println("Digitou errado!");
            }
        }
    }

    private static void cadastrarLivro() {
        while (true) {
            try {
                System.out.println("Digite o titulo do livro: ");
                String tituloLivro = input.readLine();

                for (Livro livro : biblioteca.getLivros()) {
                    if (livro.getTituloLivro().equalsIgnoreCase(tituloLivro)) {
                        System.out.println("O titulo ja existe, digite outro!");
                        return;
                    }
                }

                System.out.println("Digite o nome do autor: ");
                String nomeAutor = input.readLine();

                System.out.println("Digite a descrição do livro: ");
                String descricao = input.readLine();

                System.out.println("Esse livro é raro? y/n");
                String userChoice = input.readLine();

                if (userChoice.equalsIgnoreCase("y")) {
                    System.out.println("Digite a explicação o porque é raro");
                    String explicacao = input.readLine();
                    biblioteca.addLivro(new LivroRaro(tituloLivro, nomeAutor, descricao, true, explicacao));
                    return;
                }

                biblioteca.addLivro(new Livro(tituloLivro, nomeAutor, descricao,true));

                System.out.println("Livro cadastrado com sucesso!");
                return;

            } catch (Exception e) {
                System.out.println("Digitou errado!");
            }
        }
    }

    private static void pesquisarLivro() {
        while (true) {
            try {
                System.out.println("Pesquisar por\n[1] Título\n[2] Autor\n[3] Sair?");
                int userChoice = readInt();

                switch (userChoice) {
                    case 1:
                        pesquisarPorTitulo();
                        break;
                    case 2:
                        pesquisarPorAutor();
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Escolha errada!");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Digitou errado!");
            }
        }
    }

    private static void pesquisarPorTitulo() {
        while (true) {
            try {
                System.out.println("Digite o titulo:");
                String titulo = input.readLine();

                for (Livro livro : biblioteca.getLivros()) {
                    if (livro.getTituloLivro().equalsIgnoreCase(titulo)) {
                        System.out.println("Livro encontrado: ");
                        System.out.println(livro);
                        return;
                    }
                }
                System.out.println("Livro não encontrado");
                return;

            } catch (Exception e) {
                System.out.println("Digitou errado!");
            }
        }
    }

    private static void pesquisarPorAutor() {
        while (true) {
            try {
                System.out.println("Digite o autor:");
                String autor = input.readLine();

                for (Livro livro : biblioteca.getLivros()) {
                    if (livro.getNomeAutor().equalsIgnoreCase(autor)) {
                        System.out.println("Livro encontrado: ");
                        System.out.println(livro);
                        return;
                    }
                }
                System.out.println("Livro não encontrado");
                return;

            } catch (Exception e) {
                System.out.println("Digitou errado!");
            }
        }
    }
    private static void realizarEmprestimo(){
        while (true){
            try {
                System.out.println("Escolha o livro: ");
                int cont = 1;
                for(Livro livro : biblioteca.getLivros()){
                    System.out.println("----------------[" + cont + "]----------------");
                    System.out.println(livro);
                    System.out.println("-----------------------------------");
                    cont++;
                }
                int userChoice = readInt();

                Livro livroEscolhido = biblioteca.getLivros().get(userChoice - 1);

                if(!livroEscolhido.isDisponivel()){
                    System.out.println("Livro não disponivel!");
                    return;
                }
                if(livroEscolhido instanceof LivroRaro){
                    System.out.println("Livro não pode ser emprestado pois é raro");
                    return;
                }

                System.out.println("Para qual Cliente?");

                cont = 1;
                for(Cliente cliente : biblioteca.getClientes()){
                    System.out.println("[" + cont + "] " + cliente.getNome());
                    cont++;
                }
                userChoice = readInt();

                Cliente clienteEscolhido = biblioteca.getClientes().get(userChoice - 1);

                livroEscolhido.setDisponibilidade(false);
                livroEscolhido.setUltimaDataEmprestado(LocalDate.now());
                clienteEscolhido.addLivro(livroEscolhido);

                System.out.println("Emprestimo realizado com sucesso");
                return;

            }catch (Exception e){
                System.out.println("Digitou errado!");
            }
        }
    }
    private static void realizarDevolucao(){
        while (true){
            try {
                System.out.println("Voce é qual cliente?");
                int cont = 1;
                for(Cliente cliente : biblioteca.getClientes()){
                    System.out.println("[" + cont + "] " + cliente.getNome());
                    cont++;
                }
                int userChoice = readInt();

                Cliente clienteEscolhido = biblioteca.getClientes().get(userChoice - 1);

                if(clienteEscolhido.getInventarioDeLivro().isEmpty()){
                    System.out.println("Esse cliente não tem livro para devolver");
                }

                System.out.println("Qual livro voce quer devolver?");
                cont = 1;
                for(Livro livro : clienteEscolhido.getInventarioDeLivro()){
                    System.out.println("----------------[" + cont + "]----------------");
                    System.out.println(livro);
                    System.out.println("-----------------------------------");
                    cont++;
                }
                userChoice = readInt();

                Livro livroEscolhido = clienteEscolhido.getInventarioDeLivro().get(userChoice - 1);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                System.out.println("Qual é a data de hoje? yyyy-MM-dd");
                String data = input.readLine();

                LocalDate dataToLocalData = LocalDate.parse(data, formatter);

                long diasDesde =  ChronoUnit.DAYS.between(livroEscolhido.getUltimaDataEmprestado(), dataToLocalData);
                if(diasDesde > 7){
                    diasDesde = diasDesde - 7;
                    double multa = diasDesde * 3.50;

                    System.out.println("Multa de R$" + multa + " por entrega de " + diasDesde + " dias atrasado" );
                }

                livroEscolhido.setDisponibilidade(true);
                clienteEscolhido.removeLivro(livroEscolhido);

                System.out.println("Devolucao realizada com sucesso");
                return;

            }catch (Exception e){
                System.out.println("Digitou errado!");
            }
        }
    }


    private static void printMenu() {
        System.out.println
                (
                        """
                        [1] - Cadastrar Cliente
                        [2] - Cadastrar Livro
                        [3] - Pesquisar por Livros
                        [4] - Realizar Empréstimo
                        [5] - Realizar Devolução
                        [6] - Sair\
                        """
                );
    }
    private static int readInt() throws IOException {
        return Integer.parseInt(input.readLine());
    }

}