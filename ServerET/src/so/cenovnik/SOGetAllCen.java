/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.cenovnik;

import dbb.DBBroker;
import domen.AbstractDomainObject;
import domen.Cenovnik;
import domen.StavkaCenovnika;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Zarko
 */
public class SOGetAllCen extends AbstractSO{
    
    
    ArrayList<Cenovnik> lista;
    
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Cenovnik)) {
            throw new Exception("Objekat nije instanca klase Cenovnik");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> cenovnici = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Cenovnik>) (ArrayList<?>) cenovnici;

    }

    public ArrayList<Cenovnik> getLista() {
        return lista;
    }

    
    
}
