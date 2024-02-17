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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import session.Session;
import transfer.KlijentskiZahtev;
import transfer.Operacije;
import transfer.ResponseStatus;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Zarko
 */
public class ClientKontroler {
    
    private static ClientKontroler instance;
    

    private ClientKontroler() {
    }

    public static ClientKontroler getInstance() {
        if (instance == null) {
            instance = new ClientKontroler();
        }
        return instance;
    
}
       public Korisnik login(Korisnik prodavac) throws Exception {
        return (Korisnik) posaljiKZ(Operacije.LOGIN, prodavac);
    }

    public void addTrotinet(Trotinet trotinet) throws Exception {
        posaljiKZ(Operacije.ADD_TROTINET, trotinet);
    }

    public void deleteTrotinet(Trotinet trotinet) throws Exception {
        posaljiKZ(Operacije.DELETE_TROTINET, trotinet);
        
    }
    
      public void updateTrotinet(Trotinet trotinet) throws Exception {
        posaljiKZ(Operacije.UPDATE_TROTINET, trotinet);
    }




    private Object posaljiKZ(int operation, Object data) throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev(data, operation);

        ObjectOutputStream out = new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(kz);

        ObjectInputStream in = new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        ServerskiOdgovor so = (ServerskiOdgovor) in.readObject();

        if (so.getResponseStatus().equals(ResponseStatus.ERROR)) {
            throw so.getException();
        } else {
            return so.getOdgovor();
        }

    }

    public ArrayList<Marka> getAllMarka() throws Exception {
        return (ArrayList<Marka>) posaljiKZ(Operacije.GET_ALL_MARKE, null);
    }

    public void addET(Trotinet tr) throws Exception {
        posaljiKZ(Operacije.ADD_TROTINET, tr);
    }

    public ArrayList<Trotinet> getAllTrotinet() throws Exception {
       return (ArrayList<Trotinet>) posaljiKZ(Operacije.GETALL_TROTINET, null);
    }

    public void obrisiETrotinet(Trotinet t) throws Exception {
        posaljiKZ(Operacije.DELETE_TROTINET, t);
    }

    public void updateET(Trotinet ovaj) throws Exception {
        posaljiKZ(Operacije.UPDATE_TROTINET, ovaj);
    }

    public void addCenovnik(Cenovnik cenovnik) throws Exception {
         posaljiKZ(Operacije.ADD_CENOVNIK, cenovnik);
    }

    public ArrayList<Cenovnik> getAllCenovnik() throws Exception {
        return (ArrayList<Cenovnik>) posaljiKZ(Operacije.GETALL_CENOVNIK, null);
    }

    public ArrayList<StavkaCenovnika> getAllStavke(Cenovnik cenovnik) throws Exception {
        return (ArrayList<StavkaCenovnika>) posaljiKZ(Operacije.GET_ALL_STAVKA, cenovnik);
    }

    public void UpdateCenovnik(Cenovnik cenovnik) throws Exception {
        posaljiKZ(Operacije.UPDATE_CENOVNIK, cenovnik);
    }

    public ArrayList<Klijent> getAllKlijent() throws Exception {
        return (ArrayList<Klijent>) posaljiKZ(Operacije.GET_ALL_KLIJENT, null);
    }

    public void addPotvrda(Potvrda potvrda) throws Exception {
         posaljiKZ(Operacije.ADD_POTVRDA, potvrda);
    }

    public ArrayList<Potvrda> getAllPotvrde() throws Exception {
        return (ArrayList<Potvrda>) posaljiKZ(Operacije.GET_ALL_POTVRDE, null);
    }

    public void UpdatePotvrda(Potvrda potvrda) throws Exception {
        posaljiKZ(Operacije.UPDATE_POTVRDA, potvrda);
    }

    public void Odjava(Korisnik ulogovani) throws Exception {
        posaljiKZ(Operacije.ODJAVA, ulogovani);
    }
}
