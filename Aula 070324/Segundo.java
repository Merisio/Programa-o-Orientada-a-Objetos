import java.util.Scanner;
import java.util.Random;
import java.io.Console;

 public class Segundo{

	public static void main(String[] args){

	Scanner teclado = new Scanner(System.in);
	Console console = System.console();

	System.out.print("Digite um numero inteiro: ");
	int i = teclado.nextInt(); // lendo inteiro
	System.out.print("Digite um numero real: ");
	double r = teclado.nextDouble(); // lendo real
	//System.out.print("Digite uma frase: ");
	String s = teclado.nextLine(); // lendo cadeia de caracteres
	s = teclado.nextLine(); // lendo cadeia de caracteres

	System.out.println("\n\ninteiro: " + i + ", real: " + r);
	System.out.println("Frase: " + s);
	
	// obtendo numeros pseudo-aleatorios de 0 a 9
    Random x = new Random( ) ;
    int j = x.nextInt (10) ;
	System.out.println("\nUm numero inteiro pseudo-aleatoria: " + j);

	}
}
