# Comparação entre os Paradigmas Imperativo e Declarativo

Os paradigmas de programação são abordagens que determinam como os programas são escritos e como a lógica do problema é expressa. Dois dos paradigmas mais comuns são o **imperativo** e o **declarativo**.

## Paradigma Imperativo

No paradigma **imperativo**, o foco está em **como** o programa deve ser executado. Em outras palavras, o programador descreve as etapas e a sequência de operações que o computador deve seguir para atingir um objetivo. Este paradigma é baseado em comandos que alteram o estado do sistema e frequentemente envolve estruturas como laços de repetição e condições.

**Exemplo em Java**:
``` java
public class Fatorial {
    public static void main(String[] args) {
        int n = 5;
        int resultado = 1;
        for (int i = 1; i <= n; i++) {
            resultado *= i;
        }
        System.out.println(resultado);
    }
}
```

**Exemplo em prolog**:
```prolog
fatorial(0, 1).
fatorial(N, F) :-
    N > 0,
    N1 is N - 1,
    fatorial(N1, F1),
    F is N * F1.
```

No exemplo acima, o código define duas regras para calcular o fatorial de um número N em Prolog. A primeira regra é a base, indicando que o fatorial de 0 é 1. A segunda regra define recursivamente o fatorial de N, chamando a função fatorial com o valor N-1 e multiplicando o resultado pela variável N. Aqui, o programador declara as relações e condições que devem ser atendidas, sem se preocupar com a ordem ou o fluxo de execução.

# Comparação
- A principal diferença entre os dois paradigmas está no controle de execução. No código Java, o programador precisa especificar como a solução deve ser alcançada, usando loops e operações explícitas. Já no código Prolog, o foco está em declarar as relações e condições, deixando que o sistema determine a ordem de execução e a resolução das equações.

    - *Imperativo (Java):* O programador tem controle total sobre o fluxo de execução e a manipulação dos dados. O programa é executado sequencialmente, e cada etapa é explicitamente definida.

    - *Declarativo (Prolog):* O programador define o problema de maneira declarativa, descrevendo as relações entre as variáveis e permitindo que o sistema (neste caso, o mecanismo de inferência do Prolog) descubra a sequência de operações para resolver o problema.

Em resumo, enquanto o paradigma imperativo é mais comum em linguagens como Java, onde a implementação detalhada dos passos é crucial, o paradigma declarativo encontrado em linguagens como Prolog permite que o foco seja colocado na descrição do problema, com a execução sendo tratada de maneira mais abstrata pelo sistema.
