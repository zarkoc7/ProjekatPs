/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import enumi.TipObracuna;
import enumi.Valuta;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import javax.swing.JOptionPane;

/**
 *
 * @author Zarko
 */
public class StavkaCenovnika  extends AbstractDomainObject{
    
    private Long stavkaCenId;
    private Cenovnik cenovnik;
    private double cena;
    private Valuta valuta;
    private TipObracuna placanje;

    @Override
    public String toString() {
         SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");         
        
            String datumOd = sdf.format(cenovnik.getVaziOd());               
            String datumDo = sdf.format(cenovnik.getVaziDo());
        return  cena + ", " + valuta + ", " + placanje+", vazi od "+datumOd+" do "+datumDo;
    }
    
    

    public StavkaCenovnika() {
    }

    public StavkaCenovnika(Long stavkaCenId, Cenovnik cenovnik, double cena, Valuta valuta, TipObracuna placanje) {
        this.stavkaCenId = stavkaCenId;
        this.cenovnik = cenovnik;
        this.cena = cena;
        this.valuta = valuta;
        this.placanje = placanje;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final StavkaCenovnika other = (StavkaCenovnika) obj;
        if (Double.doubleToLongBits(this.cena) != Double.doubleToLongBits(other.cena)) {
            return false;
        }
        if (!Objects.equals(this.stavkaCenId, other.stavkaCenId)) {
            return false;
        }
        if (!Objects.equals(this.cenovnik, other.cenovnik)) {
            return false;
        }
        if (this.valuta != other.valuta) {
            return false;
        }
        return this.placanje == other.placanje;
    }

 

    public Long getStavkaCenId() {
        return stavkaCenId;
    }

    public void setStavkaCenId(Long stavkaCenId) {
        this.stavkaCenId = stavkaCenId;
    }

    public Cenovnik getCenovnik() {
        return cenovnik;
    }

    public void setCenovnik(Cenovnik cenovnik) {
        this.cenovnik = cenovnik;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Valuta getValuta() {
        return valuta;
    }

    public void setValuta(Valuta valuta) {
        this.valuta = valuta;
    }

    public TipObracuna getPlacanje() {
        return placanje;
    }

    public void setPlacanje(TipObracuna placanje) {
        this.placanje = placanje;
    }
    
    
   
   
    @Override
    public String nazivTabele() {
        return " stavkacenovnika ";
    }

    @Override
    public String alijas() {
        return " sc ";
    }

    @Override
    public String join() {
        return " JOIN cenovnik c ON (c.cenovnikId=sc.cenovnikId) JOIN user u ON (c.userId=u.userId) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Korisnik p = new Korisnik(rs.getLong("userId"), rs.getString("username"),
                    rs.getString("password"), rs.getString("ime"), rs.getString("prezime"));


            Cenovnik cen = new Cenovnik(rs.getLong("cenovnikId"), rs.getDate("vaziOd"),
                    rs.getDate("vaziDo"), p, null);

            StavkaCenovnika sc = new StavkaCenovnika(rs.getLong("stavkaCenovnikaId"),
                    cen, rs.getDouble("cena"),
                    Valuta.valueOf(rs.getString("valuta")),
                    TipObracuna.valueOf(rs.getString("tipObracuna")));

            lista.add(sc);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (stavkaCenovnikaId, cenovnikId, cena, valuta, tipObracuna) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " CenovnikId = " + cenovnik.getCenovnikId() + " AND stavkaCenovnikaId=" + stavkaCenId;
    }

    @Override
    public String vrednostiZaInsert() {
    return " "+ stavkaCenId   +", "+ cenovnik.getCenovnikId() + ", " + cena + ", '" + String.valueOf(valuta) + "', '" + String.valueOf(placanje) + "' ";

    }

    @Override
    public String vrednostiZaUpdate() {
        return " cena=" + cena + ", valuta='" + valuta + "' ";
    }

    @Override
    public String uslov() {
        return " WHERE sc.cenovnikId=" + cenovnik.getCenovnikId();
    }
    
}
