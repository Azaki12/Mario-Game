/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.HashMap;

/**
 *
 * @author NightGhost12
 */
public class IDPASS {
    HashMap<String, String> login = new HashMap<>();

    public IDPASS() {
        
        login.put("ahmed", "12345");
        login.put("zaki", "345");
        
    }
    
    public void put(String ID, String pass){
        login.put(ID, pass);
    }
    
    public HashMap getLoginInfo(){
        return login;
    }
    
   
}
