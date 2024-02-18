/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.stavka;

import dbb.DBBroker;
import domen.AbstractDomainObject;
import domen.StavkaCenovnika;
import java.util.ArrayList;
import so.AbstractSO;



/**
 *
 * @author Zarko
 */
public class SOGetAllStavka extends AbstractSO{
    

    ArrayList<StavkaCenovnika> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof StavkaCenovnika)) {
            throw new Exception("Objekat nije instanca klase stavkaCenovnika");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> stavke = DBBroker.getInstance().select(ado);
        lista = (ArrayList<StavkaCenovnika>) (ArrayList<?>) stavke;
    }

    public ArrayList<StavkaCenovnika> getLista() {
        return lista;
    }

    
}
