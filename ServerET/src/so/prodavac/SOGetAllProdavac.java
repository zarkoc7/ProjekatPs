/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.prodavac;

import dbb.DBBroker;
import domen.AbstractDomainObject;
import domen.Korisnik;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Zarko
 */
public class SOGetAllProdavac extends AbstractSO{
    
    private ArrayList<Korisnik> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Korisnik)) {
            throw new Exception("Ovaj objekat nije instanca klase Korisnik");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
           ArrayList<AbstractDomainObject> users = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Korisnik>) (ArrayList<?>) users;
    }

    public ArrayList<Korisnik> getLista() {
        return lista;
    }
    
    
}
