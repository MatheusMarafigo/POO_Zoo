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

	private ArrayList<Símios> espécime = new ArrayList<Símios>();
	
	public String[] leValores (String [] dadosIn){
		String [] dadosOut = new String [dadosIn.length];

		for (int i = 0; i < dadosIn.length; i++)
			dadosOut[i] = JOptionPane.showInputDialog  ("Entre com " + dadosIn[i]+ ": ");

		return dadosOut;
	}

	public Chimpanzé leChimpanzé (){

		String [] valores = new String [3];
		String [] nomeVal = {"Alimentação","Peso", "Idade", "Quantidade de Comida por dia"};
		valores = leValores (nomeVal);
		

		double Peso = this.retornaDouble(valores[1]);
		double idade = this.retornaDouble(valores[2]);
		int Espécie = this.retornaInteiro(valores[3]);

		Chimpanzé floresta = new Chimpanzé (valores[0],Peso,idade,Espécie);
		return floresta;
	}

	public Orangotango leOrangotango (){

		String [] valores = new String [3];
		String [] nomeVal = {"Alimentação", "Peso", "Idade","tamanho"};
		valores = leValores (nomeVal);

		double peso = this.retornaDouble(valores[1]);
		double idade = this.retornaDouble(valores[2]);
		double quantComida = this.retornaDouble(valores[3]);

		Orangotango arvore = new Orangotango (valores[0],peso,idade,quantComida);
		return arvore;
	}
	
	public Gorila leGorila(){

		String [] valores = new String [3];
		String [] nomeVal = {"Alimentação", "Peso", "Idade", "Tipo"};
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
			entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um número inteiro.");
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
			entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um número decimal.");
		}
		return Double.parseDouble(entrada);
	}

	public void salva (ArrayList<Símios> sim){
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream 
					(new FileOutputStream("dados"));
			for (int i=0; i < sim.size(); i++)
				outputStream.writeObject(sim.get(i));
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Impossível criar arquivo!");
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

	public ArrayList<Símios> recupera (){
		ArrayList<Símios> temp = new ArrayList<Símios>();

		ObjectInputStream inputStream = null;

		try {	
			inputStream = new ObjectInputStream
					(new FileInputStream("dados"));
			Object obj = null;
			while ((obj = inputStream.readObject()) != null) {
				if (obj instanceof Símios) {
					temp.add((Símios) obj);
				}   
			}          
		} catch (EOFException ex) {
			System.out.println("Fim de arquivo.");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Arquivo não existe!");
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
			menu = "Controle de Símios\n" +
					"Opções:\n" + 
					"1. Entrar Zoológico\n" +
					"2. Exibir Zoológico\n" +
					"3. Limpar Zoológico\n" +
					"4. Gravar Zoológico\n" +
					"5. Recuperar Zoológico\n" +
					"9. Sair";
			entrada = JOptionPane.showInputDialog (menu + "\n\n");
			opc1 = this.retornaInteiro(entrada);

			switch (opc1) {
			case 1:
				menu = "Entrada de Símios\n" +
						"Opções:\n" + 
						"1. Chimpanzé\n" +
						"2. Orangotango\n" +
						"3. Gorila";

				entrada = JOptionPane.showInputDialog (menu + "\n\n");
				opc2 = this.retornaInteiro(entrada);

				switch (opc2){
				case 1: espécime.add((Símios)leChimpanzé());
				break;
				case 2: espécime.add((Símios)leOrangotango());
				break;
				case 3: espécime.add((Símios)leGorila());
				break;
				default: 
					JOptionPane.showMessageDialog(null,"Símio para entrada NÃO escolhido!");
				}

				break;
				
			case 2:
				if (espécime.size() == 0) {
					JOptionPane.showMessageDialog(null,"Não há Símios disponíveis. Entre com os dados");
					break;
				}
				String dados = "";
				for (int i=0; i < espécime.size(); i++)	{
					dados += espécime.get(i).toString() + "---------------\n";
				}
				JOptionPane.showMessageDialog(null,dados);
				break;
				
			case 3:
				if (espécime.size() == 0) {
					JOptionPane.showMessageDialog(null,"Não há Símios disponíveis. Entre com os dados");
					break;
				}
				espécime.clear();
				JOptionPane.showMessageDialog(null,"Dados LIMPOS com sucesso!");
				break;
				
			case 4:
				if (espécime.size() == 0) {
					JOptionPane.showMessageDialog(null,"Não há Símios disponíveis. Entre com os dados");
					break;
				}
				salva(espécime);
				JOptionPane.showMessageDialog(null,"Dados SALVOS com sucesso!");
				break;
				
			case 5:
				espécime = recupera();
				if (espécime.size() == 0) {
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
