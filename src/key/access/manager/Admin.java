/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package key.access.manager;

/**
 *
 * @author Arosha D
 */

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFrame;


public class Admin {
    HttpHandler handler;
    AdminLoginInterface adminLogin = new AdminLoginInterface();
    RegisterEmployeeInterface regEmployee = new RegisterEmployeeInterface();
    
    public Admin(HttpHandler handler){
        this.handler = handler;   
        adminLogin.setAdmin(this);
        adminLogin.setTitle("Administrator Login");
        adminLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminLogin.setSize(960,760);
        adminLogin.setVisible(true);
        
        
        /*Enter the following part in the button action for registering the new employee*/
        regEmployee.setAdmin(this);
        regEmployee.setTitle("Employee Registration");
        regEmployee.setResizable(false);
        regEmployee.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        regEmployee.setSize(1040,555);
        regEmployee.setVisible(true);
    }
    
    
    
    public boolean insertAdmin(String name, String position, String username, String password, String role) {
        return true;
    }

    public boolean adminAuthenticate(String username, String password) throws IOException {
        ArrayList<String> data = new ArrayList<>(Arrays.asList("username="+username, "password="+password));
        String result = handler.connect("http://localhost/Smart-Key-Holder/loginDataPost.php", data);
        if(result.substring(1).equals("Login Successful!")){
            System.out.println("Login Successful!");
        }
        else if(result.substring(1).equals("Invalid Username!")){
            adminLogin.setLabelText("Invalid Username!");
            return false;
        }
        else if(result.substring(1).equals("Wrong password!")){
            adminLogin.setLabelText("Wrong Password!");
            return false;
        }
        return true;
    }
    
    public boolean registerEmployee(String name, String employeeId, String nationalId, String addressPart1, String addressPart2, String city, String phone, File imageFile) throws IOException {
        
        ArrayList<String> data = new ArrayList<>(Arrays.asList
               ("name="+name, 
                "employee_id="+employeeId, 
                "national_id="+employeeId, 
                "address_part_1="+addressPart1, 
                "address_part_2="+addressPart2, 
                "city="+city, 
                "phone="+phone));
        
        String result = handler.connect("http://localhost/Smart-Key-Holder/registerDataPost.php", data);
        boolean imageUploadStatus = handler.sendImage("http://localhost/Smart-Key-Holder/upload.php", employeeId, imageFile);
        if(result.substring(1).equals("Registration successful!")){
            System.out.println("Registration successful!");
        }
        else{
            adminLogin.setLabelText("Error in registration!");
            return false;
        }
        return true;
    }

}

