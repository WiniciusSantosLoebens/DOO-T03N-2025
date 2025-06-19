import java.util.ArrayList;

    public class Loja {
        String nomeFantasia;
        String razaoSocial;
        String cnpj;
        Endereco endereco;

        public Loja(String nomeFantasia, String razaoSocial, String cnpj, Endereco endereco) {
            this.nomeFantasia = nomeFantasia;
            this.razaoSocial = razaoSocial;
            this.cnpj = cnpj;
            this.endereco = endereco;
        }

        public void apresentarse() {
            System.out.println("Nome Fantasia: " + nomeFantasia);
            System.out.println("CNPJ: " + cnpj);
            System.out.println("RazaoSocial: " + razaoSocial);
            endereco.apresentarLogradouro();
            System.out.println("---------------------");
        }
    }