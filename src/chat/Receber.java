package chat;

import java.io.DataInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anonymous
 */
public class Receber extends Thread {
    
    public DataInputStream chega;
    
    public Receber(DataInputStream par){
        
        chega=par;
    }
    
    @Override
    public void run(){
            
            while (true) {
                
                try {
                    String strMensagem = "";
                    String idMensagem = "";
                    
                    strMensagem = chega.readUTF();
                    
                    if(strMensagem.contains("-")){
                        idMensagem = strMensagem.substring(0, strMensagem.indexOf("-"));
                        strMensagem = strMensagem.substring(strMensagem.indexOf("-")+1,strMensagem.length());
                    }
        
                    switch(idMensagem) {
                                                                           
                        case "#participantes":

                            Chat.cbxParticipantes.removeAllItems();
                            String strParticipante;
                             
                            while(!strMensagem.equals("")){
                                 
                                if(strMensagem.contains("-")){                                   
                                  
                                    strParticipante = strMensagem.substring(0, strMensagem.indexOf("-"));
                                    Chat.cbxParticipantes.addItem(strParticipante);
                                    strMensagem = strMensagem.substring(strMensagem.indexOf("-")+1,strMensagem.length());
                                 
                                 }else{
                                  
                                    strParticipante = strMensagem;
                                    Chat.cbxParticipantes.addItem(strParticipante);
                                    strMensagem = "";
                                 }
                            }
                            break; 
                            
                        default:                              
                          
                            Chat.txtRecebimento.append(strMensagem+"\n\n");
                            break;
                    }
                } catch (Exception ex) {
                    Logger.getLogger(Receber.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }
}

