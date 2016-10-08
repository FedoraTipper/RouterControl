/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    private void directoryCreate(){
    	if(!dir.exists()){dir.mkdir();}
    }

    private void writeISP(String header, String username, String password){
        String encryptedUsername = crypt.encrypt(username);
        String encryptedPassword = crypt.decrypt(password);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(accountsConfig, true));
            writer.write(header + " " + encryptedUsername + " " + encryptedPassword);
            writer.newLine();
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public boolean addISP(String header, String username, String password){
        directoryCreate();
        configCreate();
        writeISP(header, username, password);
        return true;
    }
    
    public String ISPAccount(String header){
        try{
            Scanner sc = new Scanner(new FileReader(accountsConfig));
            
        }catch(IOException E){
            E.printStackTrace();
        }
    }
    
}
