/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Zarko
 */
public class Cenovnik  extends AbstractDomainObject{
    
    private Long cenovnikId;
    private Date vaziOd;
    private Date vaziDo;
    private Korisnik prodavac;

    private ArrayList<StavkaCenovnika> stavkeC;

    public Cenovnik() {
    }

    public Cenovnik(Long cenovnikId, Date vaziOd, Date vaziDo, Korisnik prodavac, ArrayList<StavkaCenovnika> stavkeC) {
        this.cenovnikId = cenovnikId;
        this.vaziOd = vaziOd;
        this.vaziDo = vaziDo;
        this.prodavac = prodavac;
        this.stavkeC = stavkeC;
    }
    
    

    @Override
    public String nazivTabele() {
        return" cenovnik ";
    }

    @Override
    public String alijas() {
        return" c ";
    }

    @Override
    public String join() {
        return "JOIN user u ON (u.userId=c.userId) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
             Korisnik p = new Korisnik(rs.getLong("userId"), rs.getString("username"),
                    rs.getString("password"), rs.getString("ime"), rs.getString("prezime"));
             
            Cenovnik cen = new Cenovnik(rs.getLong("cenovnikId"), rs.getDate("vaziOd"),
                    rs.getDate("vaziDo"), p, null);

            lista.add(cen);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (vaziOd, vaziDo, userId) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " cenovnikId = " + cenovnikId;
    }

    @Override
    public String vrednostiZaInsert() {
        return " '" + new java.sql.Date(vaziOd.getTime()) + "', '" + new java.sql.Date(vaziDo.getTime()) + "', " + prodavac.getKorisnikId() + " ";
    }

    @Override
    public String vrednostiZaUpdate() {
         return " vaziOd='" + new java.sql.Date(vaziOd.getTime()) + "', vaziDo='" + new java.sql.Date(vaziDo.getTime()) + "' ";
    }

    @Override
    public String uslov() {
        return"";
    }

    public Long getCenovnikId() {
        return cenovnikId;
    }

    public void setCenovnikId(Long cenovnikId) {
        this.cenovnikId = cenovnikId;
    }

    public Date getVaziOd() {
        return vaziOd;
    }

    public void setVaziOd(Date vaziOd) {
        this.vaziOd = vaziOd;
    }

    public Date getVaziDo() {
        return vaziDo;
    }

    public void setVaziDo(Date vaziDo) {
        this.vaziDo = vaziDo;
    }

    public Korisnik getProdavac() {
        return prodavac;
    }

    public void setProdavac(Korisnik prodavac) {
        this.prodavac = prodavac;
    }

    public ArrayList<StavkaCenovnika> getStavkeC() {
        return stavkeC;
    }

    public void setStavkeC(ArrayList<StavkaCenovnika> stavkeC) {
        this.stavkeC = stavkeC;
    }
    
}
