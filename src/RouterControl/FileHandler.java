/*license TODO*/

package RouterControl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adam
 */
public class FileHandler {
    private String dirPath = System.getProperty("user.home") + "/.config/ISPSwitch";
    private File dir = new File(dirPath);
    private String routerPath = dir + "/router.conf"; 
    private String accountsPath = dir + "/account.conf";
    private String tempPath = dir + "/temp.conf";
    private File tempConfig = new File(tempPath);
    private File routerConfig = new File(routerPath);
    private File accountsConfig = new File(accountsPath);
    private Encryption crypt = new Encryption();
    
    public FileHandler(){
        
    }

    public boolean routerCheck(){return routerConfig.exists();}
    public boolean accountsCheck(){return accountsConfig.exists();}
    
    private void configCreate(){
    	if(routerCheck()){
    		if(!accountsCheck()){    			
    			accountsCreate();
    		}
    	}else{    		
    		routerCreate();
    		if(!accountsCheck()){
    			accountsCreate();
    		}
    	}
    }

    private void routerCreate(){      
        try{
            routerConfig.createNewFile();
            }catch(IOException E){
                E.printStackTrace();
         }
    }

    private void accountsCreate(){
        try{
            accountsConfig.createNewFile();
            }catch(IOException E){
                E.printStackTrace();
            }
    }

    private void tempCreate(){
        try{
            tempConfig.createNewFile();
        }catch(IOException E){
            E.printStackTrace();
        }
    }

    private void directoryCreate(){
    	if(!dir.exists()){dir.mkdir();}
    }

    private boolean writeISP(String header, String username, String password){
        boolean gate = false;
        String encryptedUsername = crypt.encrypt(username);
        String encryptedPassword = crypt.encrypt(password);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(accountsConfig, true));
            writer.write(header + " " + encryptedUsername + " " + encryptedPassword);
            writer.newLine();
            writer.close();
            gate = true;
        } catch (IOException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return gate;        
    }

    private boolean writeRouter(String ip, String username, String password){
        boolean gate = false;
        String encryptedUsername = crypt.encrypt(username);
        String encryptedPassword = crypt.encrypt(password);
        try {
            if(routerConfig.exists()){
                routerConfig.delete();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(routerConfig, true));
            writer.write(ip + " " + encryptedUsername + " " + encryptedPassword);           
            writer.close();
            gate = true;
        } catch (IOException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return gate;
    }
    
    public boolean addRouter(String ip, String username, String password){
         directoryCreate();
         configCreate();
         if(writeRouter(ip, username,password)){
            return true;
         }else{
            return false;
         }
    }

    public boolean removeISP(String accountLine){
        tempCreate();
        boolean gate = false;
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempConfig));
            Scanner sc = new Scanner(new FileReader(accountsConfig));
            String line = "";
            while(sc.hasNextLine()){
                line = sc.nextLine();
                if(!line.equals(accountLine)){
                    bw.write(line);
                    bw.newLine();
                }else{
                    gate = true;
                }   
            }
            accountsConfig.delete();
            tempConfig.renameTo(accountsConfig);
            bw.close();
            sc.close();            
        }catch(IOException E){
             E.printStackTrace();
        }
        return gate;
    }

    public boolean addISP(String header, String username, String password){
        directoryCreate();
        configCreate();
        if(writeISP(header, username, password)){
            return true;
        }else{
            return false;
        }
    }
    
    public String getAccount(String header){
        String line = "";
        try{
            Scanner sc = new Scanner(new FileReader(accountsConfig));
            while(sc.hasNextLine()){
                line = sc.nextLine();
                String[] parts = line.split(" ");
                if(parts[0].equals(header)){
                    return line;
                }
                line = "";
            }
        }catch(IOException E){
            E.printStackTrace();
        }
        return line;
    }

    public String getRouter(){
        String line = "";
        try{
            Scanner sc = new Scanner(new FileReader(routerConfig));
            if(sc.hasNextLine()){
                line = sc.nextLine();                         
                return line;
            }
        }catch(IOException E){
            E.printStackTrace();
        }
        return line;
    }
    
}
