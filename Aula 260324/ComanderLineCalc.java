public class ComanderLineCalc{
    public static void main(String[] argumentos){

        char operador = argumentos[0].charAt(0);
        int[] vetor = new int[argumentos.length - 1];
        int resultado = 0;

        //if (operador != '-' || operador != '/'){
            //System.out.println("Este programa precisa que o primeiro argumento seja '*' ou '+'.");

            //System.exit(1);
        //}

        for (int i = 1; i < argumentos.length; i++){
            vetor[i - 1] = Integer.parseInt(argumentos[i]);
        }

        if (operador == '+'){
            resultado = 0;

            for (int i = 1; i < argumentos.length; i++){
                resultado = resultado + vetor[i - 1];
            }
        }

        if (operador == '*'){
            resultado = 1;

            for (int i = 1; i < argumentos.length; i++){
                resultado = resultado * vetor[i - 1];
            }
        }

        System.out.println(resultado);
    }
}