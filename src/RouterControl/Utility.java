/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RouterControl;

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
	public boolean exists(String arg) {
	    if(fh.getAccount(arg) == ""){
	    	
	    }
	}

	public boolean routerExists() {
	   
	}
       
}
