/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Zarko
 */
public class Marka extends AbstractDomainObject{
    private Long markaId;
    private String naziv;
    private String model;

    @Override
    public String toString() {
        return  naziv + ", " + model;
    }

    
    public Marka() {
    }

    public Marka(Long markaId, String naziv, String model) {
        this.markaId = markaId;
        this.naziv = naziv;
        this.model = model;
    }

    public Long getMarkaId() {
        return markaId;
    }

    public void setMarkaId(Long markaId) {
        this.markaId = markaId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
    

    @Override
    public String nazivTabele() {
        return" marka ";
    }

    @Override
    public String alijas() {
        return" m ";
    }

    @Override
    public String join() {
        return"";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        
         ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Marka m = new Marka(rs.getLong("markaId"), rs.getString("naziv"), rs.getString("model"));

            lista.add(m);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (naziv, model) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " markaId = " + markaId;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + naziv + "', '" + model + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " naziv='" + naziv + "', model='" + model + "' ";
    }

    @Override
    public String uslov() {
        return " ORDER BY m.naziv ASC";
    }
    
}
