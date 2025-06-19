import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Menu {
    public static Cliente persona;
    public static Livros_raros bookRaro;
    public static Livros livro_comuns;
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

while(true){
    System.out.println("O que você gostaria: "
            + "1 - Cadastrar livro\n"
            + "2 - Buscar livro por título\n"
            + "3 - Buscar livro por autor\n"
            + "4 - Realizar empréstimo\n"
            + "5 - Realizar devolução\n"
            + "6 - Verificar atraso de livro\n"
            + "7 - Sair\n"
            + "8 - Adicionar cliente\n"
            + "9 - Listar clientes\n"
            + "10 - Buscar autor (livro raro)\n"
            + "11 - Buscar título (livro raro)\n"
            +"escolha"
            );
    int opcao = scanner.nextInt();
    switch (opcao){
    case 1:
        Cadastrar_livro();
        break;
    case 2:
        Buscar_Titulo(livro_comuns);
        break;
    case 3:
        Buscar_Autor(livro_comuns);
        break;
    case 4:
        Realizar_Emprestimo(livro_comuns);
        break;
    case 5:
        Realizar_Devolucao(livro_comuns);
        break;
    case 6:
        Verificar_atraso();
        break;
    case 7:
        System.out.println("Você saiu");
        return;
        case 8:
            Addclie(persona);
            break;
        case 9:
            lista_clientes(persona);
            break;
        case 10:
            Buscar_Autor_raro(bookRaro);
            break;
        case 11:
            Buscar_Titulo_raros(bookRaro);
            break;
        default:
        System.out.println("Erro");
    }



}

    }
    public static void Cadastrar_livro(){
        System.out.println("Escreva o titulo do livro");
        String titulo = scanner.nextLine();

        scanner.next();
        System.out.println("Escreva o autor do livro");
        String autor = scanner.nextLine();
        scanner.next();
        System.out.println("Escreva que tipo de livro é (comum ou raro)");
        String tipo = scanner.nextLine();
        scanner.next();
        System.out.println("Quantidade deste livro");
        int quantidade = scanner.nextInt();

        System.out.println("O livro esta disponivel ou ocupado");
        String disponibilidade = scanner.next();

        livro_comuns = new Livros(titulo,autor,tipo,quantidade,disponibilidade);
        livro_comuns.AddLivros_comuns(livro_comuns);
        if(tipo.equalsIgnoreCase("raro")){
            System.out.println("Escreva uma explicação por que o livro não deve ser pego");
            String explicacao = scanner.next();
            scanner.next();
            bookRaro = new Livros_raros(titulo,autor,tipo,quantidade, explicacao,livro_comuns.getDisponivel());
            bookRaro.AddLivros_raros(bookRaro);
        }
    }
    public static void  Buscar_Titulo(Livros livro_comuns){
        System.out.println("--------Livros Comuns---------");
        livro_comuns.Buscar_T();


    }
    public static void  Buscar_Titulo_raros( Livros_raros  bookRaro ) {
        System.out.println("--------Livros Raros---------");
        bookRaro.Buscar_T_R();
    }
    public static void  Buscar_Autor(Livros livro_comuns){
        System.out.println("--------Livros Comuns---------");
livro_comuns.Buscar_Autor();
    }
    public static void  Buscar_Autor_raro(Livros_raros  bookRaro) {
        System.out.println("--------Livros Raros---------");
        bookRaro.Buscar_Autor_();
    }
    public static void Realizar_Emprestimo(Livros livro_comuns){
        livro_comuns.Emprestimo_comuns();
    }
    public static void Verificar_atraso() {
        System.out.println("Quantos dias ficou de atraso o livro?");
        int atraso = scanner.nextInt();
        if(atraso == 0){
            System.out.println("Não teve atraso");
        }else if(atraso > 0){
            System.out.println("Tera que pagar:" + atraso * 3.50);
        }else{
            System.out.println("Erro");
        }
    }
    public static void lista_clientes(Cliente persona){
        persona.lista_clientes();
    }
    public static void Realizar_Devolucao(Livros livro_comuns){
        livro_comuns.Devoluc_comuns();
    }
    public static void Addclie(Cliente persona){
        persona.Addclie();
    }
}
/*
Cadastro de clientes.
Cadastro de Livros (devem ser incluídos dois tipos de livros, comuns e raros,
livros raros tem uma explicação de porque são raros e não podem ser emprestados).
Buscar livro por titulo, retornando o primeiro registro encontrado. (ex: Cracking the code interview)
Buscar livro por autor, retornando o primeiro registro encontrado. (ex: Gayle Laakmann)
Realizar empréstimo.
Realizar devolução.
Verificar a disponibilidade de empréstimo de um título (Há apenas uma cópia de cada livro na biblioteca).
Na devolução, em caso de atraso após 7 dias, gerar uma multa de 3,50 por dia de atraso.
 */