
# Resumo de Paradigmas de Programação

## Paradigmas de Programação: Imperativo e Declarativo

O paradigma imperativo é aquele em que a gente fala exatamente como o computador tem que fazer alguma coisa, passo a passo. Java, C e Python são exemplos de linguagens imperativas.

Já o paradigma declarativo é diferente: a gente fala apenas o que quer que aconteça, sem explicar como fazer. Quem cuida de encontrar o caminho é o próprio sistema. Exemplos de linguagens declarativas são SQL e Prolog.

## Comparando Java (Imperativo) e Prolog (Declarativo)

Vou mostrar dois códigos simples que fazem a mesma coisa: checar se um número é par.

### Código em Java

```java
public class Paridade {
    public static void main(String[] args) {
        int numero = 4;
        if (numero % 2 == 0) {
            System.out.println(numero + " é par.");
        } else {
            System.out.println(numero + " é ímpar.");
        }
    }
}
```


Explicação:
No Java, a gente precisa dizer tudo: pegar o número, fazer a conta (módulo por 2) e depois decidir o que mostrar. Ou seja, controlamos todo o processo.

CÓDIGO EM PROLOG
par(X) :- 0 is X mod 2.

% Consulta:
% ?- par(4).
% true.
Explicação:
No Prolog, a gente só diz a regra: "um número é par se o resto da divisão dele por 2 for zero". Depois é só perguntar se o número é par, e o sistema responde.

Aluno: Beatriz De Araújo Filipini, T03N 