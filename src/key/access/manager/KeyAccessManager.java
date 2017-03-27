 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package key.access.manager;

import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author Arosha D
 */
public class KeyAccessManager {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        HttpHandler handler = new HttpHandler();
        //handler.sendImage("http://localhost/Smart-Key-Holder/upload.php", "C:\\Users\\Arosha\\Documents\\NetBeansProjects\\Key Access Manager\\src\\resources\\landscape.jpg");
        Admin admin = new Admin(handler);
        
        
        /*
        adminLogin.setTitle("Administrator Login");
        adminLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminLogin.setSize(960,760);
        adminLogin.setVisible(true);*/
        
        
    }
    
}
