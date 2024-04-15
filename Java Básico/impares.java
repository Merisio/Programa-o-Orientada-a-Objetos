import java.util.Scanner;

public class impares {
    public static void imprimeImpar(int num){
        if (num == 0)
            System.out.println("nao existem numeros impares nesse intervalo.");
        else if (num == 1)
            System.out.println("1");
        else{
            for (int i = 1; i < num; i = i + 2)
                System.out.println(i);
        }
    }
    public static void main(String[] args){
        Scanner teclado = new Scanner(System.in);

        System.out.print("Digite um numero inteiro: ");
        int num = teclado.nextInt();

        imprimeImpar(num);
    }
}

