/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.trotinet;

import dbb.DBBroker;
import domen.AbstractDomainObject;
import domen.Trotinet;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Zarko
 */
public class SOAddTrotinet extends AbstractSO{
    
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Trotinet)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Trotinet");
        }
        Trotinet tr = (Trotinet) ado;

        ArrayList<Trotinet> lista = (ArrayList<Trotinet>) (ArrayList<?>) DBBroker.getInstance().select(ado);
        for (Trotinet trotinet : lista) {
            if (tr.getSifraT().equals(trotinet.getSifraT())) {
                throw new Exception("Ovakav trotinet je vec unet.");
            }
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }
}
