/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import enumi.TipObracuna;
import enumi.Valuta;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Zarko
 */
public class Potvrda  extends AbstractDomainObject{
    
    private Long potvrdaId;
    private Date datumOd;
    private Date datumDo;
    private double cena;
    private Trotinet trotinet;
    private Klijent klijent;
    private StavkaCenovnika stavkaCenovnika;
    private Korisnik prodavac;
    private Cenovnik cenovnik;
    

    public Potvrda() {
    }

    public Potvrda(Long potvrdaId, Date datumOd, Date datumDo, double cena, Trotinet trotinet, Klijent klijent, StavkaCenovnika stavkaCenovnika, Korisnik prodavac, Cenovnik cenovnik) {
        this.potvrdaId = potvrdaId;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.cena = cena;
        this.trotinet = trotinet;
        this.klijent = klijent;
        this.stavkaCenovnika = stavkaCenovnika;
        this.prodavac = prodavac;
        this.cenovnik = cenovnik;
    }

   

    public Long getPotvrdaId() {
        return potvrdaId;
    }

    public void setPotvrdaId(Long potvrdaId) {
        this.potvrdaId = potvrdaId;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Trotinet getTrotinet() {
        return trotinet;
    }

    public void setTrotinet(Trotinet trotinet) {
        this.trotinet = trotinet;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public StavkaCenovnika getStavkaCenovnika() {
        return stavkaCenovnika;
    }

    public void setStavkaCenovnika(StavkaCenovnika stavkaCenovnika) {
        this.stavkaCenovnika = stavkaCenovnika;
    }

    public Korisnik getProdavac() {
        return prodavac;
    }

    public void setProdavac(Korisnik prodavac) {
        this.prodavac = prodavac;
    }

    public Cenovnik getCenovnik() {
        return cenovnik;
    }

    public void setCenovnik(Cenovnik cenovnik) {
        this.cenovnik = cenovnik;
    }
    
    

    @Override
    public String nazivTabele() {
        return " potvrda ";
    }

    @Override
    public String alijas() {
        return " p2 ";
    }

    @Override
    public String join() {
   return " JOIN trotinet t ON (t.sifraT=p2.sifraT) JOIN klijent k ON (k.klijentId=p2.klijentId) JOIN stavkacenovnika sc ON (sc.stavkaCenovnikaId=p2.stavkaCenovnikaId AND sc.cenovnikId=p2.cenovnikId) JOIN cenovnik c ON(c.cenovnikId=sc.cenovnikId) JOIN user u ON (u.userId=p2.userId) JOIN marka m ON (m.markaId=t.markaId)";

    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
             Korisnik p = new Korisnik(rs.getLong("userId"), rs.getString("username"),
                    rs.getString("password"), rs.getString("ime"), rs.getString("prezime"));

             cenovnik = new Cenovnik(rs.getLong("cenovnikId"), rs.getDate("vaziOd"),
                    rs.getDate("vaziDo"), p, null);

            StavkaCenovnika sc = new StavkaCenovnika(rs.getLong("stavkaCenovnikaId"),
                    cenovnik, rs.getDouble("cena"),
                    Valuta.valueOf(rs.getString("valuta")),
                    TipObracuna.valueOf(rs.getString("tipObracuna")));
            
            Marka m = new Marka(rs.getLong("markaId"), rs.getString("naziv"), rs.getString("model"));

            Trotinet et= new Trotinet(rs.getString("sifraT"), rs.getInt("maxSpeed"), rs.getInt("maxRange"), rs.getInt("maxPower"), rs.getInt("maxPayload"), m);

              Klijent k = new Klijent(rs.getLong("klijentId"), rs.getString("jmbg"),
                    rs.getString("imeK"),
                    rs.getString("prezimeK"),
                    rs.getString("kontakt"), p);

            Potvrda p2 = new Potvrda(rs.getLong("potvrdaId"),
                    rs.getDate("datumOd"), rs.getDate("datumDo"),
                    rs.getDouble("cenaP"),               
                    et, k, sc, p, cenovnik);

            lista.add(p2);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (datumOd, datumDo, cenaP, sifraT, klijentId, stavkaCenovnikaId, userId, cenovnikId) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " potvrdaId=" + potvrdaId;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + new java.sql.Date(datumOd.getTime()) + "', '" + new java.sql.Date(datumDo.getTime()) + "', " + cena +", '" + trotinet.getSifraT() + "', " + klijent.getKlijentID() + ", " + stavkaCenovnika.getStavkaCenId() + ", " + prodavac.getKorisnikId()+ ", " + cenovnik.getCenovnikId();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " datumOd='" + new java.sql.Date(datumOd.getTime()) + "', datumDo='" + new java.sql.Date(datumDo.getTime()) + "', cenaP=" + cena+ ", sifraT='"+trotinet.getSifraT()+"', klijentId="+klijent.getKlijentID()+", stavkaCenovnikaId="+stavkaCenovnika.getStavkaCenId()+", cenovnikId="+cenovnik.getCenovnikId();
    }

    @Override
    public String uslov() {
        return "ORDER BY p2.datumOd";
    }
    
}
