
public class Orangotango extends Símios {
	
	private double tamanho;

	public Orangotango(String alimentacao, double peso, double idade, double tamanho) {
		super(alimentacao, peso, idade);
		this.tamanho = tamanho;
	}

	public double getCor() {
		return tamanho;
	}

	public void setCor(double tamanho) {
		this.tamanho = tamanho;
	}
	
	public String toString() {
		String retorno = super.toString();
		retorno += "A altura do Orangotango é de: "     + this.tamanho     + "\n";
		return retorno;
	}
	public boolean enviarComida() {
		return false;
	}

}
