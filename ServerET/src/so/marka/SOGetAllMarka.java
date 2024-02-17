/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.marka;

import dbb.DBBroker;
import domen.AbstractDomainObject;
import domen.Marka;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Zarko
 */
public class SOGetAllMarka extends AbstractSO{
    
    ArrayList<Marka> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Marka)) {
            throw new Exception("Objekat nije instanca klase Marka");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> marke = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Marka>) (ArrayList<?>) marke;
    }

    public ArrayList<Marka> getLista() {
        return lista;
    }
    
}
