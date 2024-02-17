/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transfer;

import java.io.Serializable;

/**
 *
 * @author Zarko
 */
public class KlijentskiZahtev implements Serializable{
    
    private Object argument;
    private int operacija;

    public KlijentskiZahtev() {
    }

    public KlijentskiZahtev(Object argument, int operacija) {
        this.argument = argument;
        this.operacija = operacija;
    }

    public Object getArgument() {
        return argument;
    }

    public void setArgument(Object argument) {
        this.argument = argument;
    }

    public int getOperacija() {
        return operacija;
    }

    public void setOperacija(int operacija) {
        this.operacija = operacija;
    }
    
    
}
