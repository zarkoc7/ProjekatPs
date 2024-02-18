/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.cenovnik;

import dbb.DBBroker;
import domen.AbstractDomainObject;
import domen.Cenovnik;
import domen.StavkaCenovnika;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import so.AbstractSO;

/**
 *
 * @author Zarko
 */
public class SOAddCen extends AbstractSO{
    
    
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Cenovnik)) {
            throw new Exception("Objekat nije instanca klase cenovnik");
        }
        Cenovnik cenovnik1 = (Cenovnik) ado;
        if (cenovnik1.getStavkeC().isEmpty()) {
            throw new Exception("Cenovnik mora da ima bar jednu stavku!");
        }
        ArrayList<Cenovnik> cenovnici = (ArrayList<Cenovnik>) (ArrayList<?>) DBBroker.getInstance().select(ado);
        for (Cenovnik cenovnik : cenovnici) {
            Date startDate1 = cenovnik1.getVaziOd();
            Date endDate1 = cenovnik1.getVaziDo();
            Date startDate2 = cenovnik.getVaziOd();
            Date endDate2 = cenovnik.getVaziDo();

            if ((startDate1.before(endDate2) && startDate1.after(startDate2))
                    || (endDate1.after(startDate2) && endDate1.before(endDate2))
                    || (startDate1.before(startDate2) && endDate1.after(endDate2))) {
                throw new Exception("Vec postoji cenovnik za ovaj period!");
            }
        }
    }
    
    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        //Long rb=0L;
        PreparedStatement preparedStatement = DBBroker.getInstance().insert(ado);
        Cenovnik cenovnik = (Cenovnik) ado;
        
        ResultSet rs = preparedStatement.getGeneratedKeys();
        if (rs.next()) {
            Long cenovnikId = rs.getLong(1);
            cenovnik.setCenovnikId(cenovnikId);
            
        }

        for (StavkaCenovnika stavka : cenovnik.getStavkeC()) {
            stavka.setCenovnik(cenovnik);
           // stavka.setStavkaCenId(++rb);
            DBBroker.getInstance().insert(stavka);
        }
        
    }
    
}
