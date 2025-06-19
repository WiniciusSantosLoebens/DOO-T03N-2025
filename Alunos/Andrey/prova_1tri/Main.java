package ClassePrincipal;

import Objeto.Biblioteca;
import Objeto.Livro;
import Objeto.Cliente;
import java.util.Scanner;

public class Main {

private static Scanner scan = new Scanner(System.in);

public static void Main(String[] args){
    Menu();
}

public static void Menu(){
    Integer resp = 0;
    while(resp != 8){
     System.out.println("Digite   \n"
                     + "1- para cadastrar cliente  \n"
                     + "2- para cadastrar livro\n "
                     + "3- para buscar livro por titulo  \n"
                     + "4- para buscar livro por autor   \n"
                     + "5- ver dispónibilidade do livro  \n"
                     + "6- emprestar um livro  \n"
                     + "7- devolver um livro  \n"
                     + "8- Sair  \n");
    resp = scan.nextInt();
        if(resp == 1){
            Cliente.cadastroCliente();
        } else if(resp == 2){
            Livro.cadastroLivro();
        } else if(resp == 3){
            Livro.buscarLivroNome();
        } else if(resp == 4){
            Livro.buscarLivroAutor();
        } else if(resp == 5){
            Livro.verificarDisponibilidade();
        } else if(resp == 6){
            Biblioteca.emprestarLivro();
        } else if(resp == 7){
            Biblioteca.devolverLivro();
        } else {
            resp = 8;
        }
    }
}
}
