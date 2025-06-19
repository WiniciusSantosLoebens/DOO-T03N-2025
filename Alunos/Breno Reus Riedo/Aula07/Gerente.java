import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Gerente extends Sujeito {
    private Loja loja;
    private BigDecimal salarioBase;
    private List<BigDecimal> salarioRecebido;


    public Gerente(String nome, int idade, Loja loja, Endereco endereco, BigDecimal salarioBase, List<BigDecimal> salarioRecebido) {
        super(nome, idade, endereco);
        this.loja = loja;
        this.salarioBase = salarioBase;
        this.salarioRecebido = salarioRecebido;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public BigDecimal getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(BigDecimal salarioBase) {
        this.salarioBase = salarioBase;
    }

    public List<BigDecimal> getSalarioRecebido() {
        return salarioRecebido;
    }

    public void setSalarioRecebido(List<BigDecimal> salarioRecebido) {
        this.salarioRecebido = salarioRecebido;
    }

    public BigDecimal calcularMedia(){
        BigDecimal media = BigDecimal.ZERO;

        for (BigDecimal valor : salarioRecebido){
            media = media.add(valor);
        }

        return media.divide(new BigDecimal(salarioRecebido.size()), RoundingMode.HALF_UP);
    }

    public BigDecimal calcularBonus(){
        return salarioBase.multiply(new BigDecimal("0.2"));
    }

    @Override
    public void apresentarSe(){
        System.out.println("Nome: " + super.getNome() + " \n" + "Idade: " + super.getIdade() +" \n" + "Gerente da Loja: " + loja.getRazaoSocial());
    }
}
