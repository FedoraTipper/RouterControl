/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RouterControl;

import java.io.IOException;

/**
 *
 * @author adam
 */
public class RouterControl {
    
    public static void main(String[] args) throws IOException{
         /* 
            (Switch) will handle all operations to the router
            (Utility) serves as a general helper to all classes
            (FileHandlers) handles all saving of data entered by the user
        */
        Switch switcher = new Switch();
        Utility util = new Utility();
        FileHandler fh = new FileHandler();
        if(args[0].toLowerCase().equals("--add") || args[0].toLowerCase().equals("--a")){
            if(util.exists(args[1])){
                System.out.println("Account has already been added");
            }else{
                if(fh.addISP(args[1], args[2], args[3])){
                    System.out.println(args[1] + " has been added to the config");
                }
            }
        }else if(args[0].toLowerCase().equals("-s") || args[0].toLowerCase().equals("--switch")){
             String account = fh.getAccount(args[1]);
             if(account != ""){
                  if(util.routerExists()){
                      System.out.println("Switching to " + args[1]);
                      switcher.switchISP(account);
                      System.out.println("Operation complete! Please wait up to 3 mins");
                      account = "";
                  }else{
                      System.out.println("Could not connect to your router. Please reapply your config.");
                      System.out.println("ISPSwitch --config [ip of router] [username of router] [password of router]");
                  }
             }else{
                 System.out.println("ISP account does not exist");
             }
        }else if(args[0].toLowerCase().equals("-r") || args[0].equals("--restart")){
                if(util.routerExists()){
                    System.out.println("Restarting DSL Connection");
                    switcher.restartDSL();
                    System.out.println("Restarted! Please wait 5 mins");
                }else{
                    System.out.println("Could not connect to your router. Please reapply your config.");
                    System.out.println("ISPSwitch --config [ip of router] [username of router] [password of router]");
                }            
        }else if(args[0].toLowerCase().equals("-h") ||args[0].toLowerCase().equals("--help")){
          printHelp();                      //stop the clutter
        }else{            
            System.out.println("Incorrect input. Type ISPSwitch -h for commands");            
        }
    }
    
    private static void printHelp(){
            System.out.println("Usage");
            System.out.println("ISPSwitch --config [ip of router] [username of router] [password of router]");
            System.out.println("E.g. ISPSwitch --config 192.168.1.1 admin password123");
            System.out.println("ISPSwitch [--add] [Header name] [username of ISP account] [password of ISP account]");
            System.out.println("E.g. ISPSwitch --add AH1 example@afrihost.co.za password123\n");
            System.out.println("ISPSwitch [-s or --switch] [ISP linked header]");
            System.out.println("E.g. ISPSwitch -s [vox or 1]\n");
            System.out.println("ISPSwitch [-r or --restart]\n");          
    }
}
