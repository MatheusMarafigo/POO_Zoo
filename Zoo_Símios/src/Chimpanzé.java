
public class Chimpanzé extends Símios{
	
	private int quantComida;

	public Chimpanzé(String alimentacao, double peso, double idade, int quantComida) {
		super(alimentacao, peso, idade);
		this.quantComida = quantComida;
	}

	public int getquantComida() {
		return quantComida;
	}

	public void setquantComida(int espécie) {
		this.quantComida = espécie;
	}
	
	public String toString() {
		String retorno = super.toString();
		retorno += "Quantidade de Comida por dia: "     + this.quantComida     + "\n";
		return retorno;
	}

	
	public boolean enviarComida() {
		return false;
	}

}
