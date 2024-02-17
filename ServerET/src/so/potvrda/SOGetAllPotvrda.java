/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.potvrda;

import dbb.DBBroker;
import domen.AbstractDomainObject;
import domen.Potvrda;
import java.util.ArrayList;
import so.AbstractSO;



/**
 *
 * @author Zarko
 */
public class SOGetAllPotvrda extends AbstractSO{
    
     ArrayList<Potvrda> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Potvrda)) {
            throw new Exception("Objekat nije instanca klase Potvrda");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> potvrde = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Potvrda>) (ArrayList<?>) potvrde;
    }

    public ArrayList<Potvrda> getLista() {
        return lista;
    }
    
}
