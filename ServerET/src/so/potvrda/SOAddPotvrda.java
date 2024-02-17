/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.potvrda;

import dbb.DBBroker;
import domen.AbstractDomainObject;
import domen.Potvrda;
import java.util.ArrayList;
import java.util.Date;
import kontroler.ServerKontroler;
import so.AbstractSO;


/**
 *
 * @author Zarko
 */
public class SOAddPotvrda extends AbstractSO{
    
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Potvrda)) {
            throw new Exception("Objekat nije instanca klase Confirmation");
        }
        Potvrda po = (Potvrda) ado;
        ArrayList<Potvrda> potvrde = ServerKontroler.getInstance().getAllPotvrde();
        for (Potvrda potvrda : potvrde) {
            Date vaziOd = po.getDatumOd();
            Date vaziDo = po.getDatumDo();
            Date vaziOd2 = potvrda.getDatumOd();
            Date vaziDo2 = potvrda.getDatumDo();
            if ((po.getTrotinet().getSifraT().equals(potvrda.getTrotinet().getSifraT()))
                    && ((vaziOd.before(vaziDo2) && vaziOd.after(vaziOd2))
                    || (vaziDo.after(vaziOd2) && vaziDo.before(vaziDo2))
                    || (vaziOd.before(vaziOd2) && vaziDo.after(vaziDo2)))) {
                throw new Exception("Trotinet je iznajmljen u tom periodu ");
            }
        }
        
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }
    
}
