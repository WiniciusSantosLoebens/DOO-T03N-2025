# Paradigmas de Programação: Imperativo e Declarativo

Os paradigmas de programação são diferentes abordagens para resolver problemas em computação. Entre os paradigmas mais conhecidos, temos o imperativo e o declarativo, que diferem significativamente na forma como os problemas são abordados.

## Paradigma Imperativo

No paradigma imperativo, o programador descreve explicitamente como o programa deve atingir um objetivo. Ou seja, define-se uma sequência de instruções que alteram o estado do programa até que o resultado esperado seja alcançado. Linguagens como Java, C e Python são exemplos de linguagens imperativas.

### Exemplo de código em Java (Imperativo)

public class Fatorial {
    public static int calcularFatorial(int n) {
        int resultado = 1;
        for (int i = 2; i <= n; i++) {
            resultado *= i;
        }
        return resultado;
    }
    
    public static void main(String[] args) {
        System.out.println(calcularFatorial(5));
    }
}

Como funciona:

O código utiliza um laço for que vai de 2 até n, multiplicando os valores sucessivamente e acumulando o resultado na variável resultado.

A variável resultado é modificada a cada iteração do laço, alterando o estado do sistema a cada passo.

O programador descreve explicitamente as instruções necessárias para calcular o fatorial, passo a passo, mudando o estado do programa durante a execução.

## Paradigma Declarativo

No paradigma declarativo, o programador descreve o que deve ser feito, sem se preocupar com os detalhes de como isso será realizado. O foco está em declarar relações e propriedades do problema. Linguagens como Prolog, SQL e Haskell seguem esse paradigma, onde o sistema é responsável por descobrir como chegar à solução.

### Exemplo de código em Prolog (Declarativo)

fatorial(0, 1).
fatorial(N, F) :-
    N > 0,
    N1 is N - 1,
    fatorial(N1, F1),
    F is N * F1.


Como funciona:

O código em Prolog começa com um caso base para o fatorial de 0, que é igual a 1 (fatorial(0, 1)).

A regra recursiva define que o fatorial de um número N é o produto de N e o fatorial de N-1.

O Prolog utiliza unificação e backtracking para buscar automaticamente a solução quando a consulta fatorial(5, F) é feita, sem necessidade de especificar explicitamente o controle de fluxo.

Em vez de alterar o estado de variáveis como no exemplo imperativo, o Prolog define relações lógicas e deixa que o sistema faça o trabalho de resolução.

## Comparação entre Java (Imperativo) e Prolog (Declarativo)

| Aspecto | Java (Imperativo) | Prolog (Declarativo) | |----------------------|-------------------------------------------------------------|---------------------------------------------------------------| | Como funciona | O programador especifica como a solução deve ser alcançada, com controle explícito sobre o fluxo de execução. | O programador especifica o que deve ser feito, e o sistema encontra a solução automaticamente. | | Controle de Fluxo | A execução é controlada por estruturas como loops (for, while) e condicionais (if). | O controle de fluxo é implícito, com o sistema de inferência do Prolog realizando a busca pela solução. | | Alteração de Estado | O estado do programa é modificado explicitamente a cada iteração de um laço ou execução de uma instrução. | Não há alteração explícita de estados, apenas a definição de relações lógicas. | | Exemplo de Código | Cálculo do fatorial com um laço for e uma variável de resultado que é modificada a cada iteração. | Definição de um caso base e uma regra recursiva que o Prolog resolve usando unificação e backtracking. |

## Conclusão

A principal diferença entre o paradigma imperativo e o declarativo está na maneira como o problema é abordado. No imperativo (como em Java), o programador define explicitamente as etapas a serem seguidas, controlando diretamente o fluxo de execução e alterando o estado do programa. Já no declarativo (como em Prolog), o programador descreve a lógica ou as relações do problema, enquanto o sistema se encarrega de encontrar a solução de maneira automática.

Em resumo, Java (imperativo) exige que o programador detalha as ações que o programa deve executar para chegar ao resultado, enquanto Prolog (declarativo) permite que o programador defina regras e deixe o sistema lidar com a execução.