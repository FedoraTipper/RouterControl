/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RouterControl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author adam
 */
public class Utility {
   
 	FileHandler fh = new FileHandler();

	public Utility(){
	    
	}
	/*
	*See if the ISP account exists in the config
	*/
	public String account(String arg) {

	}

	public boolean routerExists(String ip) throws UnknownHostException, IOException {
	   InetAddress inet = InetAddress.getByName(ip);
	   if(inet.isReachable(250)){
	   	return true;
	   }
	   return false;
	}
       
}
