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
public class Korisnik  extends AbstractDomainObject{
    
    private Long korisnikId;
    private String username;
    private String password;
    private String imeP;
    private String prezimeP;

    public Korisnik() {
    }

    public Korisnik(Long korisnikId, String username, String password, String imeP, String prezimeP) {
        this.korisnikId = korisnikId;
        this.username = username;
        this.password = password;     
        this.imeP = imeP;
        this.prezimeP = prezimeP;
    }

    public Long getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Long prodavacId) {
        this.korisnikId = prodavacId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImeP() {
        return imeP;
    }

    public void setImeP(String imeP) {
        this.imeP = imeP;
    }

    public String getPrezimeP() {
        return prezimeP;
    }

    public void setPrezimeP(String prezimeP) {
        this.prezimeP = prezimeP;
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
        final Korisnik other = (Korisnik) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return Objects.equals(this.password, other.password);
    }

   
  
    
    

    @Override
    public String nazivTabele() {
        return " user ";
    }

    @Override
    public String alijas() {
        return " u ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        
          ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Korisnik p = new Korisnik(rs.getLong("userId"), rs.getString("username"),
                    rs.getString("password"), rs.getString("ime"), rs.getString("prezime"));

            lista.add(p);
        }
        rs.close();
        return lista;
        
    }

    @Override
    public String koloneZaInsert() {
        return " (username, password, ime, prezime) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " userId = " + korisnikId;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + username + "', '" + password +"', '" + imeP + "', '" + prezimeP + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " username='" + username + "', password='" + password +  "', ime='" + imeP+ "', prezime='" + prezimeP+ "' ";
    }

    @Override
    public String uslov() {
        return"";
    }
    
}
