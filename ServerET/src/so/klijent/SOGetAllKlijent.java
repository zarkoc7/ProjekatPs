/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.klijent;

import dbb.DBBroker;
import domen.AbstractDomainObject;
import domen.Klijent;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Zarko
 */
public class SOGetAllKlijent extends AbstractSO {
    ArrayList<Klijent> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Klijent)) {
            throw new Exception("Objekat nije instanca klase Klijent");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> clients = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Klijent>) (ArrayList<?>) clients;
    }

    public ArrayList<Klijent> getLista() {
        return lista;
    }
    
}
