/*license TODO*/

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
	public boolean exists(String header) {
		String account = fh.getAccount(header);
		if(account.equals("")){
			return false;
		}else{
			return true;
		}
	}

	public boolean routerExists(String ip) throws UnknownHostException, IOException {
	   InetAddress inet = InetAddress.getByName(ip);
	   if(inet.isReachable(250)){
	   	return true;
	   }
	   return false;
	}
       
}
