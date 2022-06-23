import java.io.Serializable;

public abstract class Símios implements Serializable{

	private String alimentacao;
	private double peso;
	private double idade;
	
	public Símios(String tamanho, double peso, double idade) {
		this.alimentacao = tamanho;
		this.peso = peso;
		this.idade = idade;
	}
	
	public String toString() {
		String retorno = "";
		retorno += "Tipo de Comida: "     + this.alimentacao     + "\n";
		retorno += "Peso: "    + this.peso    + "kg" + "\n";
		retorno += "Idade: "    + this.idade +"\n";
		return retorno;
	}
	
	public abstract boolean enviarComida();

	public String getalimentacao() {
		return alimentacao;
	}

	public void setalimentacao(String tamanho) {
		this.alimentacao = tamanho;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getIdade() {
		return idade;
	}

	public void setIdade(double idade) {
		this.idade = idade;
	}
	
	
}
