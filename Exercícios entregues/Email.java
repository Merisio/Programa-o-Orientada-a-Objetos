import java.io.Serializable;

public class Email implements Serializable{
    private String de, para, assunto, msg;
    
    public Email(String remet, String dest, String a, String m){
        de = remet;
        para = dest;
        assunto = a;
        msg = m;
    }
    
    public String getDestinatario(){
        return para;
    }
    
    public String getRemetente(){
        return de;
    }
    
    public String getAssunto(){
        return assunto;
    }
    
    public String getMensagem(){
        return msg;
    }
    
    public void setDestinatario(String str){
        para = str;
    }
    
    public void setRemetente(String str){
        de = str;
    }
    
    public void setAssunto(String str){
        assunto = str;
    }
    
    public void setMensagem(String str){
        msg = str;
    }
    
    public String toString(){
        return "Remetente: "+de+" \nDestinatario: "+para+"\nAssunto: "+assunto+"\nMensagem: "+msg;   
    }
}