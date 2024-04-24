import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class EmailSystem{
    public static void main(String[] args){
        Scanner teclado = new Scanner(System.in);
        
        Email[] caixaPostal = new Email[10];
        
        int opcao = 0;
        int contador = 0;
        
        while (opcao != 2){
            System.out.println("==== Email System ====");
            System.out.println("1 - Fazer login;");
            System.out.println("2 - Sair do programa.");
            
            System.out.printf("Entre com uma opção: ");
            opcao = teclado.nextInt();
            teclado.nextLine();
            
            String usuario;
            
            switch(opcao){
                case 1:
                    System.out.printf("\nDigite o nome do usuario: ");
                    usuario = teclado.nextLine();
                    usuario = usuario.trim();
                    
                    for (int i = 0; i < 10; i++){
                        if (caixaPostal[i] != null)
                            contador++;
                    }
                    
                    System.out.println("Usuario "+usuario+" logado.");
                    caixaPostal = submenu(usuario, caixaPostal, contador);
                    
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
    
    private static Email[] submenu(String usuario, Email[] caixaPostal, int contador){
        Scanner teclado = new Scanner(System.in);
        
        int opcao = 0;
        int pos;
        
        while(opcao != 5){
            System.out.printf("\n");
            System.out.println("==== MENU DE AÇÕES ====");
            System.out.println("1 - Escrever um email;");
            System.out.println("2 - Listar os emails;");
            System.out.println("3 - Ler um email;");
            System.out.println("4 - Excluir um email;");
            System.out.println("5 - Restaurar backup;");
            System.out.println("6 - Fazer logout.");
            
            System.out.printf("Entre com uma opção: ");
            opcao = teclado.nextInt();
            
            switch(opcao){
                case 1:
                    System.out.printf("\n");
                    
                    if (contador == 10){
                        System.out.println("A caixa postal esta cheia. Exclua emails para poder escrever outros.");
                        break;
                    }
                    
                    caixaPostal = Escrever(usuario, caixaPostal);
                    contador++;
                    
                    System.out.printf("\n");
                    System.out.println("Sua caixa postal foi atualizada.");
                    
                    break;
                case 2:
                    System.out.printf("\n");
                    
                    contador = 0;
                    for (int i = 0; i < 10; i++){
                        if (caixaPostal[i] != null)
                            contador++;
                    }
                    
                    Listar(usuario, caixaPostal, contador);
                    break;
                case 3:
                    if (contador != 0){
                        System.out.printf("\nQual email voce deseja ler? ");
                        pos = teclado.nextInt();
                        teclado.nextLine();
                        
                        System.out.printf("\n");
                        
                        if (caixaPostal != null)
                            Ler(usuario, caixaPostal, contador, pos);
                        else
                            System.out.println("Este email nao existe.");
                    }
                    else
                        System.out.printf("\nSua caixa postal não possui nenhum email.\n ");
                        
                    break;
                case 4:
                    if (contador != 0){
                        System.out.printf("\nQual email voce deseja excluir? ");
                        pos = teclado.nextInt();
                        teclado.nextLine();
                        
                        caixaPostal = Excluir(usuario, caixaPostal, contador, pos);
                        
                        contador = 0;
                        for (int i = 0; i < 10; i++){
                            if (caixaPostal[i] != null)
                                contador++;
                        }
                    }
                    else
                        System.out.printf("\nSua caixa postal não possui nenhum email.\n ");
                    
                    break;
                case 5:
                    System.out.printf("\nImportando arquivo...\n\n");
                    
                    caixaPostal = lerArquivo(caixaPostal);
                    
                    for (int i = 0; i < 10; i++){
                        if (caixaPostal[i] != null)
                            contador++;
                    }
                    
                    break;
                case 6:
                    System.out.printf("\nSaindo...\n\n");
                    break;
                default:
                    System.out.printf("\n");
                    System.out.println("Opcao invalida. Tente novamente.");
                    break;
            }
        }
        return caixaPostal;
    }
    
    private static Email[] Escrever(String usuario, Email[] caixaPostal){
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
        
        return caixaPostal;
    }
    
    private static void Listar(String usuario, Email[] caixaPostal, int contador){
        int contD = 0;
        int contR = 0;
        
        for (int i = 0; i < contador; i++){
            if (usuario.equals(caixaPostal[i].getDestinatario()))
                contD++;
                    
            if (usuario.equals(caixaPostal[i].getRemetente()))
                contR++;
        }
        
        System.out.println(usuario+" possui "+contD+" email(s) na sua caixa postal e enviou "+contR+" email(s)");
    }
    
    private static void Ler(String usuario, Email[] caixaPostal, int contador, int posicao){
        if (posicao + 1 > contador){
            System.out.println("Este email nao existe.");
        }
        else{
            if (caixaPostal != null)
                if (usuario.equals(caixaPostal[posicao].getRemetente()) || usuario.equals(caixaPostal[posicao].getDestinatario()))
                    System.out.println(caixaPostal[posicao]);
                else
                     System.out.println("Este email nao e destinado a voce.");
        }
    }
    
    private static Email[] Excluir(String usuario, Email[] caixaPostal, int contador, int posicao){
        if (caixaPostal[posicao] != null){
            if (usuario.equals(caixaPostal[posicao].getRemetente())){
                if (posicao >= 0 && posicao < contador){
                    for (int i = posicao; i < contador - 1; i++){
                        caixaPostal[i] = caixaPostal[i + 1];
                    }
                    caixaPostal[contador - 1] = null;
                    
                    System.out.printf("\n");
                    System.out.println("Email excluido com sucesso.");
                    
                    return caixaPostal;
                }
            }
            else{
                System.out.printf("\n");
                System.out.println("Voce nao tem autorizacao para excluir esse email.");
                
                return caixaPostal;
            }
        }
        else{
            System.out.printf("\n");
            System.out.println("Este email nao existe.");
            
            return caixaPostal;
        }
        return null;
    }
    
    private static Email[] lerArquivo(Email[] caixaPostal){
        File arquivo = new File("backup.dat");
        
        try{
            FileInputStream fin = new FileInputStream(arquivo);
            ObjectInputStream oin = new ObjectInputStream(fin);
            
            caixaPostal = (Email[]) oin.readObject();
            oin.close();
            fin.close();
            
            for (Email p : caixaPostal){
                if (p != null){
                    System.out.println(p);
                }
            }
        }
        catch (Exception ex){
            System.err.println("erro: "+ex.toString());
        }
        System.out.println("\n\nArquivos importados com sucesso!");
        
        return caixaPostal;
    
    }
}