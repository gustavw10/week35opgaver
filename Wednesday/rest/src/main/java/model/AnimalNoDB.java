/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Gustav
 */
public class AnimalNoDB {
    
    String type;
    String sound;
    public AnimalNoDB() {
    }
    
    public AnimalNoDB(String type, String sound){
        this.type = type;
        this.sound = sound;
    }

    @Override
    public String toString() {
        return "AnimalNoDB{" + "type=" + type + ", sound=" + sound + '}';
    }
    
}
