/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author tuan
 */
public class Role {
    private int Role_id;
    private String name;
    
    private static final Role instance = new Role();
    
    public static Role getInstance() {
        return instance;
    }
   
    
    public Role(int Role_id ,String name) {
        this.name = name;
        this.Role_id = Role_id;
    }

    public int getRole_id() {
        return Role_id;
    }

    public void setRole_id(int Role_id) {
        this.Role_id = Role_id;
    }

    public Role() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
