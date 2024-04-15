import java.util.Scanner;

public class primos {
    public static int ehPrimo(int num){
        if (num <= 1)
            return 0;
        for (int i = 2; i*i <= num; i++){
            if (num % i == 0)
                return 0;
        }

        return 1;
    }

    public static void main(String[] args){
        Scanner teclado = new Scanner(System.in);

        System.out.print("Digite um numero inteiro: ");
        int num = teclado.nextInt();
        
        if (ehPrimo(num) == 1) {
            System.out.println(num + " é um número primo.");
        } else {
            System.out.println(num + " não é um número primo.");
        }
    }
}
