
public class Gorila extends S�mios {
	
	public String tipo;

	public Gorila(String alimentacao, double peso, double idade, String tipo) {
		super(alimentacao, peso, idade);
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String toString() {
		String retorno = super.toString();
		retorno += "Caracter�sticas do esp�cime: "     + this.tipo     + "\n";
		return retorno;
	}
	public boolean enviarComida() {
		return false;
	}

}
