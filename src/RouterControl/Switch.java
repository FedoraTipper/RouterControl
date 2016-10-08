/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RouterControl;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptEngine;
import java.io.IOException;
import java.util.logging.Level;

/**
 *
 * @author Adam
 */
public class Switch {

        Encryption en = new Encryption();
         private WebClient webClient = new WebClient(BrowserVersion.CHROME);
         private HtmlPage page;
         private ScriptResult result;
         private HtmlPasswordInput textField;
         private HtmlInput textField1;
         private String JSCode;
         private String JSPVC = "chg_pvc(0);";
         
         public Switch(){}
         
         private void startPage() throws IOException{
                 System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
                 java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF); 
                 page = webClient.getPage("http://192.168.1.1/Advanced_DSL_Content.asp");
                 JavaScriptEngine engine = new JavaScriptEngine(webClient);
                 webClient.setJavaScriptEngine(engine);
                 
                 textField1 = page.getHtmlElementById("login_username");       
                 textField1.setValueAttribute(RouterUser);	
                 textField = page.getElementByName("login_passwd");
                 textField.setValueAttribute(RouterPass); 

                 JSCode = "login()";
                 result = page.executeJavaScript(JSCode);
                 Object jsResult = result.getJavaScriptResult();
                 page = (HtmlPage) result.getNewPage();
                 page = webClient.getPage("http://192.168.1.1/Advanced_DSL_Content.asp"); //Redirect, as the program as been authed
                 result = page.executeJavaScript(JSPVC); //Always switch to PVC first, all it will be deleted on the change
                 result.getNewPage();
                 result.getJavaScriptResult();
         }
         
//         public void test() throws IOException{
//                 System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
//                 java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);                  
//                 page = webClient.getPage("http://192.168.1.1/Advanced_DSL_Content.asp");
//                 JavaScriptEngine engine = new JavaScriptEngine(webClient);
//                 webClient.setJavaScriptEngine(engine);
//                 
//                 textField1 = page.getHtmlElementById("login_username");       
//                 textField1.setValueAttribute(RouterUser);	
//                 textField = page.getElementByName("login_passwd");
//                 textField.setValueAttribute(RouterPass); 
//
//                 JSCode = "login()";
//                 result = page.executeJavaScript(JSCode);
//                 Object jsResult = result.getJavaScriptResult();
//                 page = (HtmlPage) result.getNewPage();
//                 page = webClient.getPage("http://192.168.1.1/Advanced_DSL_Content.asp"); //Redirect, as the program as been authed
//                 
//                 JSCode = "logout();";
//                 result = page.executeJavaScript(JSCode);
////                 page = (HtmlPage) result.getNewPage(); //Fix confirm box
////                 ConfirmHandler okHandler = new ConfirmHandler(){
////                          public boolean handleConfirm(Page page, String message) {
////                                  return true;
////                          }
////                 };
////                  webClient.setConfirmHandler(okHandler);
////                  page = webClient.getPage("http://192.168.1.1/index.asp");
//                  System.out.println(page.getBaseURL());                  
//         }
         
         private void logoutPage(){
                 JSCode = "showMAC();";
                 result = page.executeJavaScript(JSCode);
                 //result.getNewPage();
                 JSCode = "applyRule();";
                 result = page.executeJavaScript(JSCode);
                 result.getNewPage();
                 JSCode = "logout();";
                 result = page.executeJavaScript(JSCode);
                 result.getNewPage(); //Fix confirm box
//                 ConfirmHandler okHandler = new ConfirmHandler(){
//                          public boolean handleConfirm(Page page, String message) {
//                                  return true;
//                          }
//                 };
//                  webClient.setConfirmHandler(okHandler);
         }
         
    public void restartDSL() throws IOException{ //dslx_modulation
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF); 
        page = webClient.getPage("http://192.168.1.1/Advanced_ADSL_Content.asp");
        JavaScriptEngine engine = new JavaScriptEngine(webClient);
        webClient.setJavaScriptEngine(engine);

        textField1 = page.getHtmlElementById("login_username");       
        textField1.setValueAttribute(RouterUser);	
        textField = page.getElementByName("login_passwd");
        textField.setValueAttribute(RouterPass); 

        JSCode = "login()";
        result = page.executeJavaScript(JSCode);
        Object jsResult = result.getJavaScriptResult();
        page = (HtmlPage) result.getNewPage();
        page = webClient.getPage("http://192.168.1.1/Advanced_ADSL_Content.asp"); //Redirect, as the program as it has been authed            


        HtmlSelect select = (HtmlSelect) page.getElementByName("dslx_modulation");
        int newOption = 4;
        if(select.getSelectedIndex() ==  4){
            newOption = 6;
        }
        select.setSelectedIndex(newOption);
        JSCode = "applyRule();";
        result = page.executeJavaScript(JSCode);
        result.getNewPage();
        JSCode = "logout();";
        result = page.executeJavaScript(JSCode);
        result.getNewPage(); //Fix confirm box
    }

    public void switchISP(String account) throws IOException{
        String[] parts = account.split(" ");
        String username = en.decrypt(parts[1]);
        String password = en.decrypt(parts[2]);
        textField1 = page.getElementByName("dslx_pppoe_username");
        textField1.setValueAttribute(username);
        textField = page.getElementByName("dslx_pppoe_passwd");
        textField.setValueAttribute(password);
        logoutPage();
        username = null; password = null;
    }
    
}
