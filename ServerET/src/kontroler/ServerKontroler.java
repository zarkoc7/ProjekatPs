/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Cenovnik;
import domen.Klijent;
import domen.Marka;
import domen.Korisnik;
import domen.Potvrda;
import domen.StavkaCenovnika;
import domen.Trotinet;
import java.math.MathContext;
import java.util.ArrayList;
import so.cenovnik.SOAddCen;
import so.cenovnik.SOGetAllCen;
import so.cenovnik.SOUpdateCen;
import so.klijent.SOGetAllKlijent;
import so.login.SOLogin;
import so.marka.SOGetAllMarka;
import so.potvrda.SOAddPotvrda;
import so.potvrda.SOGetAllPotvrda;
import so.potvrda.SOUpdatePotvrda;
import so.stavka.SOGetAllStavka;
import so.trotinet.SOAddTrotinet;
import so.trotinet.SODeleteTrotinet;
import so.trotinet.SOGetAllTrotinet;
import so.trotinet.SOUpdateTrotinet;

/**
 *
 * @author Zarko
 */
public class ServerKontroler {
    private static ServerKontroler instance;
    private ArrayList<Korisnik> ulogovani;

    private ServerKontroler() {
        ulogovani = new ArrayList<>();
    }

    public static ServerKontroler getInstance() {
        if (instance == null) {
            instance = new ServerKontroler();
        }
        return instance;
    }

    public Korisnik login(Korisnik p) throws Exception {
        SOLogin so = new SOLogin();
        so.templateExecute(p);
        
        return so.getUlogovani();
    }

    public ArrayList<Korisnik> getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(ArrayList<Korisnik> ulogovani) {
        this.ulogovani = ulogovani;
    }
    

    public void dodajUlogovanog(Korisnik prodavac) {
        ulogovani.add(prodavac);
    }

    public Object getAllMarke() throws Exception {
        SOGetAllMarka so = new SOGetAllMarka();
        so.templateExecute(new Marka());
        return so.getLista();
    }

    public void addTrotinet(Trotinet trotinet) throws Exception {
        SOAddTrotinet so = new SOAddTrotinet();
        so.templateExecute(trotinet);
        
    }

    public ArrayList<Potvrda> getAllPotvrde() throws Exception {
        SOGetAllPotvrda so= new SOGetAllPotvrda();
        so.templateExecute(new Potvrda());
        return so.getLista();
    }

    public Object getAllTrotinet() throws Exception {
        SOGetAllTrotinet so= new SOGetAllTrotinet();
        so.templateExecute(new Trotinet());
        return so.getLista();
        
    }

    public void deleteTrotinet(Trotinet tr) throws Exception {
        SODeleteTrotinet so= new SODeleteTrotinet();
        so.templateExecute(tr);
        
        
    }

    public void updateTrotinet(Trotinet trotinet) throws Exception {
        SOUpdateTrotinet so= new SOUpdateTrotinet();
        so.templateExecute(trotinet);
    }

    public void addCenovnik(Cenovnik cenovnik) throws Exception {
        SOAddCen so= new SOAddCen();
        so.templateExecute(cenovnik);
    }

    public Object getAllCenovnik() throws Exception {
        SOGetAllCen so= new SOGetAllCen();
        so.templateExecute(new Cenovnik());
        return so.getLista();
    }


    public Object getAllStavka(Cenovnik cenovnik) throws Exception {
        SOGetAllStavka so= new SOGetAllStavka();
        StavkaCenovnika sc=new StavkaCenovnika();
        sc.setCenovnik(cenovnik);
        so.templateExecute(sc);
        return so.getLista();
    }

    public void updateCenovnik(Cenovnik cenovnik) throws Exception {
        SOUpdateCen so= new SOUpdateCen();
        so.templateExecute(cenovnik);
    }

    public Object getAllKlijent(Klijent klijent) throws Exception {
        SOGetAllKlijent so= new SOGetAllKlijent();
        so.templateExecute(new Klijent());
        return so.getLista();
    }

    public void addPotvrda(Potvrda potvrda) throws Exception {
        SOAddPotvrda so= new SOAddPotvrda();
        so.templateExecute(potvrda);
        
    }

    public Object getAllPotvrde(Potvrda potvrda) throws Exception {
        SOGetAllPotvrda so= new SOGetAllPotvrda();
        so.templateExecute(new Potvrda());
        return so.getLista();
    }

    public void updatePotvrda(Potvrda potvrda) throws Exception {
        SOUpdatePotvrda so=new SOUpdatePotvrda();
        so.templateExecute(potvrda);
    }

    public void odjavaKorisnika(Korisnik korisnik) {
        for (Korisnik korisnik1 : ulogovani) {
            if(korisnik1.equals(korisnik)){
                ulogovani.remove(korisnik);
                return;
                
            }

                
            
        }
    }
    
   
    
}
