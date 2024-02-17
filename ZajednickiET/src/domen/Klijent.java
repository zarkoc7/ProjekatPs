/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Zarko
 */
public class Klijent  extends AbstractDomainObject {
    private Long klijentID;
    private String jmbg;
    private String ime;
    private String prezime;
    private String kontakt;
    private Korisnik prodavac;

    @Override
    public String toString() {
        return " " + ime + " " + prezime;
    }

  
    
    

    public Klijent() {
    }
    

    public Klijent(Long klijentID, String jmbg, String ime, String prezime, String kontakt, Korisnik prodavac) {
        this.klijentID = klijentID;
        this.jmbg = jmbg;
        this.ime = ime;
        this.prezime = prezime;
        this.kontakt = kontakt;
        this.prodavac = prodavac;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Klijent other = (Klijent) obj;
        if (!Objects.equals(this.jmbg, other.jmbg)) {
            return false;
        }
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        if (!Objects.equals(this.kontakt, other.kontakt)) {
            return false;
        }
        if (!Objects.equals(this.klijentID, other.klijentID)) {
            return false;
        }
        return Objects.equals(this.prodavac, other.prodavac);
    }

    
    public Long getKlijentID() {
        return klijentID;
    }

    public void setKlijentID(Long klijentID) {
        this.klijentID = klijentID;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKontakt() {
        return kontakt;
    }

    public void setKontakt(String kontakt) {
        this.kontakt = kontakt;
    }

    public Korisnik getProdavac() {
        return prodavac;
    }

    public void setProdavac(Korisnik prodavac) {
        this.prodavac = prodavac;
    }
    
    

    @Override
    public String nazivTabele() {
        return " klijent ";
    }

    @Override
    public String alijas() {
        return" k ";
    }

    @Override
    public String join() {
        return " JOIN user u ON (u.userId=k.userId) ";
    }
    

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            Korisnik p = new Korisnik(rs.getLong("userId"), rs.getString("username"), rs.getString("password"),
                    rs.getString("ime"), rs.getString("prezime"));

            Klijent k = new Klijent(rs.getLong("klijentId"), rs.getString("jmbg"),
                    rs.getString("imeK"),
                    rs.getString("prezimeK"),
                    rs.getString("kontakt"), p);

            lista.add(k);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (imeK, prezimeK, jmbg, kontakt, userId) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " klijentId = " + klijentID;
    }

    @Override
    public String vrednostiZaInsert() {
        return " '" + jmbg + "', '" + ime + "', '" + prezime + "', '" + kontakt + "', " + prodavac.getKorisnikId();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " imeK='" + ime + "', prezimeK='" + prezime + "', kontakt='" + kontakt + "' ";
    }

    @Override
    public String uslov() {
        return "";
    }
    
}
