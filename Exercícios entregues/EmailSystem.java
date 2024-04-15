import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class EmailSystem{
    public static void main(String[] args){
        Scanner teclado = new Scanner(System.in);
        
        Email[] caixaPostal = new Email[10];
        
        int opcao = 0;
        int contador = 0; //conta a quantidade de emails 
        String usuario;
        
        while (opcao != 2){
            System.out.println("==== Email System ====");
            System.out.println("1 - Fazer login;");
            System.out.println("2 - Sair do programa.");
            
            System.out.printf("Entre com uma opcao: ");
            opcao = teclado.nextInt();
            
            switch(opcao){
                case 1:
                    System.out.printf("\nDigite o nome do usuario: ");
                    usuario = teclado.nextLine();
                    usuario = teclado.nextLine();
                    
                    for(int i = 0; i < 10; i++){
                        if (caixaPostal[i] != null)
                            contador++;
                    }

                    System.out.println("Usuario "+usuario+" logado.");
                    submenu(usuario, caixaPostal, contador);
                    
                    break;
                case 2:
                    System.out.println("Fazendo logout...");
                    System.out.println("Obrigado.");
                    break;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
            }
        }
    }
    
    private static void submenu(String usuario, Email[] caixaPostal, int contador){
        Scanner teclado = new Scanner(System.in);
        
        int opcao = 0;
        int pos;

        while(opcao != 5){
            System.out.printf("\n");
            System.out.println("==== O que deseja fazer? ====");
            System.out.println("1 - Escrever um email;");
            System.out.println("2 - Listar os emails;");
            System.out.println("3 - Ler um email;");
            System.out.println("4 - Excluir um email;");
            System.out.println("5 - Arquivar caixa postal.");
            System.out.println("6 - Restaurar caixa postal.");
            System.out.println("7 - Fazer logout.");
            
            System.out.printf("Entre com uma opcao: ");
            opcao = teclado.nextInt();
            
            switch(opcao){
                case 1:
                    System.out.printf("\n");
                    
                    if (contador == 10){
                        System.out.println("A caixa postal esta cheia. Exclua emails para poder escrever outros.");
                        break;
                    }
                    
                    Escrever(usuario, caixaPostal);
                    contador++;

                    System.out.printf("\n");
                    System.out.println("Sua caixa postal foi atualizada.");

                    break;
                case 2:
                    System.out.printf("\n");
                    contador = 0;

                    for(int i = 0; i < 10; i++){
                        if (caixaPostal[i] != null)
                            contador++;
                    }

                    Listar(usuario, caixaPostal, contador);
                    break;
                case 3:
                    System.out.printf("\nQual email voce deseja ler? ");
                    pos = teclado.nextInt();
                    teclado.nextLine();

                    System.out.printf("\n");
                    Ler(usuario, caixaPostal, contador, pos);
                    break;
                case 4:
                    System.out.printf("\nQual email voce deseja excluir? ");
                    pos = teclado.nextInt();
                    teclado.nextLine();

                    Excluir(usuario, caixaPostal, contador, pos);

                    contador = 0;
                    for(int i = 0; i < 10; i++){
                        if (caixaPostal[i] != null)
                            contador++;
                    }
                    break;
                case 5:
                    System.out.printf("\nGerando arquivo...\n\n");
                    gravarArquivo(caixaPostal);
                    break;
                case 6:
                    caixaPostal = lerArquivo(caixaPostal, contador);

                    contador = 0;
                    for(int i = 0; i < 10; i++){
                        if (caixaPostal[i] != null)
                            contador++;
                    }

                    break;
                case 7:
                    System.out.printf("\nSaindo...\n\n");
                    break;
                default:
                    System.out.printf("\n");
                    System.out.println("Opcao invalida. Tente novamente.");
                    break;
            }
        }
    }
    
    private static void Escrever(String usuario, Email[] caixaPostal){
        Scanner teclado = new Scanner(System.in);
        
        String assunto, mensagem, destinatario;
        
        System.out.printf("Destinatario: ");
        destinatario = teclado.nextLine();
        System.out.printf("Assunto: ");
        assunto = teclado.nextLine();
        System.out.printf("Mensagem: ");
        mensagem = teclado.nextLine();
        
        for (int i = 0; i < 10; i++){
            if (caixaPostal[i] == null){
                caixaPostal[i] = new Email(usuario, destinatario, assunto, mensagem);
                break;
            }
        }
    }
    
    private static void Listar(String usuario, Email[] caixaPostal, int contador){
        int contD = 0;
        int contR = 0;

        for (int i = 0; i < contador; i++){
            if (caixaPostal[i] != null){
                if (usuario.equals(caixaPostal[i].getDestinatario()))
                    contD++;
                    
                if (usuario.equals(caixaPostal[i].getRemetente()))
                    contR++;
            }
        }
        
        System.out.println("Voce possui "+contD+" email(s) na sua caixa postal e enviou "+contR+" email(s)");
    }
    
    private static void Ler(String usuario, Email[] caixaPostal, int contador, int posicao){
        if (posicao > contador || caixaPostal[posicao] == null)
            System.out.println("Este email não existe.");

        if (caixaPostal[posicao] != null)
            if (usuario.equals(caixaPostal[posicao].getRemetente()) || usuario.equals(caixaPostal[posicao].getDestinatario()))
                System.out.println(caixaPostal[posicao]);
            else
                System.out.println("Este email nao e destinado a voce.");
    }

    private static void Excluir(String usuario, Email[] caixaPostal, int contador, int posicao){
        if (caixaPostal[posicao] != null){
            if (usuario.equals(caixaPostal[posicao].getRemetente())){
                if (posicao >= 0 && posicao < contador){
                    for (int i = posicao; i < contador - 1; i++)
                        caixaPostal[i] = caixaPostal[i + 1];
                    
                    caixaPostal[contador - 1] = null;

                    System.out.printf("\n");
                    System.out.println("Email excluido com sucesso.");
                } 
            }
            else{
                System.out.printf("\n");
                System.out.println("Voce nao tem autorizacao para excluir este email.");
            }
        }
        else{
            System.out.printf("\n");
            System.out.println("Este email nao existe.");
        }
    }

    private static void gravarArquivo(Email[] caixaPostal){
        File arquivo = new File("backup.dat");

        try{
            FileOutputStream fout = new FileOutputStream(arquivo);
            ObjectOutputStream oos = new ObjectOutputStream(fout);

            oos.writeObject(caixaPostal);
            oos.flush();
            oos.close();
            fout.close();
        }
        catch (Exception ex){
            System.out.println("erro: "+ ex.toString());
        }
    }

    private static Email[] lerArquivo(Email[] caixaPostal, int contador){
        File arquivo = new File("backup.dat");
        
        try{
            FileInputStream fin = new FileInputStream(arquivo);
            ObjectInputStream oin = new ObjectInputStream(fin);

            caixaPostal = (Email[]) oin.readObject();
            oin.close();
            fin.close();

            contador = 0;

            for (Email p : caixaPostal){
                if (p != null){
                    contador++;
                    System.out.println(p);
                }
            }
        }
        catch (Exception ex){
            System.err.println("erro: "+ex.toString());
        }
        System.out.printf("\nArquivos importados com sucesso!\n\n");

        return caixaPostal;
    }
}