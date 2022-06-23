
public class Chimpanz� extends S�mios{
	
	private int quantComida;

	public Chimpanz�(String alimentacao, double peso, double idade, int quantComida) {
		super(alimentacao, peso, idade);
		this.quantComida = quantComida;
	}

	public int getquantComida() {
		return quantComida;
	}

	public void setquantComida(int esp�cie) {
		this.quantComida = esp�cie;
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
