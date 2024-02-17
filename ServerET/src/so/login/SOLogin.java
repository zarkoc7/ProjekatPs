/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.login;

import dbb.DBBroker;
import domen.AbstractDomainObject;
import domen.Korisnik;
import java.util.ArrayList;
import kontroler.ServerKontroler;
import so.AbstractSO;

/**
 *
 * @author Zarko
 */
public class SOLogin extends AbstractSO{
    
    Korisnik ulogovani;
    ArrayList<Korisnik> listaUlogovanih;
    
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Korisnik)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Korisnik");
        }
        listaUlogovanih= ServerKontroler.getInstance().getUlogovani();
        Korisnik prodavac = (Korisnik) ado;
        if (listaUlogovanih != null && listaUlogovanih.contains(prodavac)) {
            throw new Exception("Vec ulogovan.");
        }
        
    }
    
    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        Korisnik p = (Korisnik) ado;
        
        ArrayList<Korisnik> prodavci = (ArrayList<Korisnik>) (ArrayList<?>) DBBroker.getInstance().select(ado);
        
        for (Korisnik pr : prodavci) {
            if (pr.getUsername().equals(p.getUsername()) && pr.getPassword().equals(p.getPassword())) {
                ulogovani = pr;
                ServerKontroler.getInstance().dodajUlogovanog(ulogovani);
                return;
            }
        }
        
        throw new Exception("Ne postoji prodavac sa ovim parametrima!");
        
    }
    
    public Korisnik getUlogovani() {
        return ulogovani;
    }
    
    
}
