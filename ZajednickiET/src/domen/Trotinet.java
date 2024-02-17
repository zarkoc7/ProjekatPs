/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Zarko
 */
public class Trotinet  extends AbstractDomainObject{
    private String sifraT;
    private int maxSpeed;
    private int maxRange;
    private int maxPower;
    private int maxPayload;
    private Marka marka;

    @Override
    public String toString() {
        return sifraT+", "+marka.getModel()+", "+marka.getNaziv();
    }
    
    

    public Trotinet() {
    }

    public Trotinet(String sifraT, int maxSpeed, int maxRange, int maxPower, int maxPayload, Marka markaId) {
        this.sifraT = sifraT;
        this.maxSpeed = maxSpeed;
        this.maxRange = maxRange;
        this.maxPower = maxPower;
        this.maxPayload = maxPayload;
        this.marka = markaId;
    }
    
   

    @Override
    public String nazivTabele() {
        return " trotinet ";
    }

    @Override
    public String alijas() {
        return " t ";
    }

    @Override
    public String join() {
        return " JOIN marka m ON (m.markaId=t.markaId) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
         ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Marka m = new Marka(rs.getLong("markaId"), rs.getString("naziv"), rs.getString("model"));

            Trotinet et= new Trotinet(rs.getString("sifraT"), rs.getInt("maxSpeed"), rs.getInt("maxRange"), rs.getInt("maxPower"), rs.getInt("maxPayload"), m);

            lista.add(et);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (sifraT, maxSpeed, maxRange, maxPower, maxPayload, markaId) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " sifraT ='"+ sifraT+"'";
    }

    @Override
    public String vrednostiZaInsert() {
        return " '" + sifraT + "', " + +maxSpeed + ", " + maxRange + ", " + maxPower+ ", " + maxPayload+ ", " + marka.getMarkaId() + " ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " maxSpeed=" + maxSpeed + ", maxRange=" + maxRange + ", maxRange=" + maxRange+", maxPower="+maxPower+", maxPayload= "+ maxPayload+" "; }

    @Override
    public String uslov() {
        return "";
    }

    public int getMaxRange() {
        return maxRange;
    }

    public void setMaxRange(int maxRange) {
        this.maxRange = maxRange;
    }

    public int getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(int maxPower) {
        this.maxPower = maxPower;
    }

    public int getMaxPayload() {
        return maxPayload;
    }

    public void setMaxPayload(int maxPayload) {
        this.maxPayload = maxPayload;
    }

    public Marka getMarka() {
        return marka;
    }

    public void setMarka(Marka markaId) {
        this.marka = markaId;
    }

    public String getSifraT() {
        return sifraT;
    }

    public void setSifraT(String sifraT) {
        this.sifraT = sifraT;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
    
    
}
