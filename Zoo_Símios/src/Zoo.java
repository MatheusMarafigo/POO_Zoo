import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Zoo {

	private ArrayList<S�mios> esp�cime = new ArrayList<S�mios>();
	
	public String[] leValores (String [] dadosIn){
		String [] dadosOut = new String [dadosIn.length];

		for (int i = 0; i < dadosIn.length; i++)
			dadosOut[i] = JOptionPane.showInputDialog  ("Entre com " + dadosIn[i]+ ": ");

		return dadosOut;
	}

	public Chimpanz� leChimpanz� (){

		String [] valores = new String [3];
		String [] nomeVal = {"Alimenta��o","Peso", "Idade", "Quantidade de Comida por dia"};
		valores = leValores (nomeVal);
		

		double Peso = this.retornaDouble(valores[1]);
		double idade = this.retornaDouble(valores[2]);
		int Esp�cie = this.retornaInteiro(valores[3]);

		Chimpanz� floresta = new Chimpanz� (valores[0],Peso,idade,Esp�cie);
		return floresta;
	}

	public Orangotango leOrangotango (){

		String [] valores = new String [3];
		String [] nomeVal = {"Alimenta��o", "Peso", "Idade","tamanho"};
		valores = leValores (nomeVal);

		double peso = this.retornaDouble(valores[1]);
		double idade = this.retornaDouble(valores[2]);
		double quantComida = this.retornaDouble(valores[3]);

		Orangotango arvore = new Orangotango (valores[0],peso,idade,quantComida);
		return arvore;
	}
	
	public Gorila leGorila(){

		String [] valores = new String [3];
		String [] nomeVal = {"Alimenta��o", "Peso", "Idade", "Tipo"};
		valores = leValores (nomeVal);

		double peso = this.retornaDouble(valores[1]);
		double idade = this.retornaDouble(valores[2]);

		Gorila casa = new Gorila (valores[0],peso,idade,valores[3]);
		return casa;
	}

	private boolean intValido(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	public int retornaInteiro(String entrada) { 
		int numInt;

		while (!this.intValido(entrada)) {
			entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um n�mero inteiro.");
		}
		return Integer.parseInt(entrada);
	}
	

	private boolean doubleValido(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	public double retornaDouble(String entrada) {
		float numFloat;

		while (!this.doubleValido(entrada)) {
			entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um n�mero decimal.");
		}
		return Double.parseDouble(entrada);
	}

	public void salva (ArrayList<S�mios> sim){
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream 
					(new FileOutputStream("dados"));
			for (int i=0; i < sim.size(); i++)
				outputStream.writeObject(sim.get(i));
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Imposs�vel criar arquivo!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally { 
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public ArrayList<S�mios> recupera (){
		ArrayList<S�mios> temp = new ArrayList<S�mios>();

		ObjectInputStream inputStream = null;

		try {	
			inputStream = new ObjectInputStream
					(new FileInputStream("dados"));
			Object obj = null;
			while ((obj = inputStream.readObject()) != null) {
				if (obj instanceof S�mios) {
					temp.add((S�mios) obj);
				}   
			}          
		} catch (EOFException ex) {
			System.out.println("Fim de arquivo.");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Arquivo n�o existe!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
			return temp;
		}
	}

	public void menu (){

		String menu = "";
		String entrada;
		int    opc1, opc2;

		do {
			menu = "Controle de S�mios\n" +
					"Op��es:\n" + 
					"1. Entrar Zool�gico\n" +
					"2. Exibir Zool�gico\n" +
					"3. Limpar Zool�gico\n" +
					"4. Gravar Zool�gico\n" +
					"5. Recuperar Zool�gico\n" +
					"9. Sair";
			entrada = JOptionPane.showInputDialog (menu + "\n\n");
			opc1 = this.retornaInteiro(entrada);

			switch (opc1) {
			case 1:
				menu = "Entrada de S�mios\n" +
						"Op��es:\n" + 
						"1. Chimpanz�\n" +
						"2. Orangotango\n" +
						"3. Gorila";

				entrada = JOptionPane.showInputDialog (menu + "\n\n");
				opc2 = this.retornaInteiro(entrada);

				switch (opc2){
				case 1: esp�cime.add((S�mios)leChimpanz�());
				break;
				case 2: esp�cime.add((S�mios)leOrangotango());
				break;
				case 3: esp�cime.add((S�mios)leGorila());
				break;
				default: 
					JOptionPane.showMessageDialog(null,"S�mio para entrada N�O escolhido!");
				}

				break;
				
			case 2:
				if (esp�cime.size() == 0) {
					JOptionPane.showMessageDialog(null,"N�o h� S�mios dispon�veis. Entre com os dados");
					break;
				}
				String dados = "";
				for (int i=0; i < esp�cime.size(); i++)	{
					dados += esp�cime.get(i).toString() + "---------------\n";
				}
				JOptionPane.showMessageDialog(null,dados);
				break;
				
			case 3:
				if (esp�cime.size() == 0) {
					JOptionPane.showMessageDialog(null,"N�o h� S�mios dispon�veis. Entre com os dados");
					break;
				}
				esp�cime.clear();
				JOptionPane.showMessageDialog(null,"Dados LIMPOS com sucesso!");
				break;
				
			case 4:
				if (esp�cime.size() == 0) {
					JOptionPane.showMessageDialog(null,"N�o h� S�mios dispon�veis. Entre com os dados");
					break;
				}
				salva(esp�cime);
				JOptionPane.showMessageDialog(null,"Dados SALVOS com sucesso!");
				break;
				
			case 5:
				esp�cime = recupera();
				if (esp�cime.size() == 0) {
					JOptionPane.showMessageDialog(null,"Sem dados para apresentar.");
					break;
				}
				JOptionPane.showMessageDialog(null,"Dados RECUPERADOS com sucesso!");
				break;
			case 9:
				JOptionPane.showMessageDialog(null,"Fim do aplicativo");
				break;
			}
		} while (opc1 != 9);
	}
	
	public static void main(String[] args) {

		Zoo ce = new Zoo();
		
		ce.menu();
		

	}

}
