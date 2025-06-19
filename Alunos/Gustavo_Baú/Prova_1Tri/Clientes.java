package Alunos.Gustavo_Baú.Prova_1Tri;

public class Clientes {
        private String nome;
        private int cpf;

        public Clientes (String nome, int cpf) {
            this.nome = nome;
            this.cpf = cpf;
        }
        public void Clientes2 (String nome2, int cpf2) {
            this.nome = nome2;
            this.cpf = cpf2;
        }
        public String getNome() {
            return nome;
        }
        public void setNome(String nome) {
            this.nome = nome;
        }
        public int getCpf() {
            return cpf;
        }
        public void setCpf(int cpf) {
            this.cpf = cpf;
        }
        
        @Override
        public String toString() {
            return "Nome: " + nome + ", CPF: " + cpf;
        }
}
