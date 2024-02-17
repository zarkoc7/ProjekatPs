/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.trotinet;

import com.mysql.cj.admin.ServerController;
import dbb.DBBroker;
import domen.AbstractDomainObject;
import domen.Potvrda;
import domen.Trotinet;
import java.util.ArrayList;
import java.util.Date;
import kontroler.ServerKontroler;
import so.AbstractSO;

/**
 *
 * @author Zarko
 */
public class SODeleteTrotinet extends AbstractSO{
    
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Trotinet)) {
            throw new Exception("Objekat nije instanca klase Trotinet");
        }
        Trotinet tr = (Trotinet) ado;
        ArrayList<Potvrda> potvrde = ServerKontroler.getInstance().getAllPotvrde();
        for (Potvrda confirmation : potvrde) {
            if (confirmation.getTrotinet().getSifraT().equals(tr.getSifraT())) {
                Date today = new Date();
                if (confirmation.getDatumDo().after(today)) {
                    throw new Exception("Trotinet je rentiran");
                }
            }
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }
    
}
