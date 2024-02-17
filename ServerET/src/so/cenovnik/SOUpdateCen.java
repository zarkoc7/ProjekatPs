/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.cenovnik;

import com.mysql.cj.admin.ServerController;
import dbb.DBBroker;
import domen.AbstractDomainObject;
import domen.Cenovnik;
import domen.StavkaCenovnika;
import java.util.ArrayList;
import java.util.Date;
import kontroler.ServerKontroler;
import so.AbstractSO;

/**
 *
 * @author Zarko
 */
public class SOUpdateCen extends AbstractSO{
    
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
if (!cenovnik.getCenovnikId().equals(cenovnik1.getCenovnikId())){ //provera za ostale datume
            if ((startDate1.before(endDate2) && startDate1.after(startDate2))
                    || (endDate1.after(startDate2) && endDate1.before(endDate2))
                    || (startDate1.before(startDate2) && endDate1.after(endDate2))) {
                throw new Exception("Vec postoji cenovnik u ovom periodu");
            }
        }}
    }
    
    
     @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        
        DBBroker.getInstance().update(ado);
         
         Cenovnik cenovnik = (Cenovnik) ado;
         
        ArrayList<StavkaCenovnika> stavke= (ArrayList<StavkaCenovnika>) ServerKontroler.getInstance().getAllStavka(cenovnik);
         for (StavkaCenovnika stavkaCenovnika : stavke) {
             DBBroker.getInstance().delete(stavkaCenovnika);
             
         }

       
           ArrayList<StavkaCenovnika> izmena=cenovnik.getStavkeC();
           for (StavkaCenovnika stavkaCenovnika1 : izmena) {
               DBBroker.getInstance().insert(stavkaCenovnika1);
             
         }
           
        
             
             //stavka.setStavkaCenId(++rb);
            
        }
    }

    

