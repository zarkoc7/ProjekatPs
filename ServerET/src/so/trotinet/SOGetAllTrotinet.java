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
public class SOGetAllTrotinet extends AbstractSO {

    ArrayList<Trotinet> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Trotinet)) {
            throw new Exception("Objekat nije instanca klase trotinet");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> trotineti = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Trotinet>) (ArrayList<?>) trotineti;
    }

    public ArrayList<Trotinet> getLista() {
        return lista;
    }

    
}
