/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tabele;

import domen.Cenovnik;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import kontroler.ClientKontroler;

/**
 *
 * @author Zarko
 */
public class TableModelCenovnik extends AbstractTableModel {

    ArrayList<Cenovnik> lista;
    String kolone[] = {"cenovnikId", "vaziOd", "vaziDo"};
    private String parametar = "";

    public TableModelCenovnik() {
        try {
            lista = new ArrayList<>();
        } catch (Exception ex) {
            Logger.getLogger(TableModelCenovnik.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cenovnik cen = lista.get(rowIndex);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        switch (columnIndex) {
            case 0:
                return cen.getCenovnikId();
            case 1:
                return sdf.format(cen.getVaziOd());
            case 2:
                return sdf.format(cen.getVaziDo());
            default:
                return "return!";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void dodajListu(ArrayList<Cenovnik> cenovnici) {
        lista = cenovnici;
        fireTableDataChanged();
    }

    public void obrisiRed(int row) {
        lista.remove(row);
        fireTableDataChanged();
    }

    public Cenovnik vratiCenovnik(int row) {
        return lista.get(row);
    }

    public void setParametar(String param) {
        this.parametar = param;
        refreshTable();
    }

    private void refreshTable() {
         try {
            lista = ClientKontroler.getInstance().getAllCenovnik();
            if (!parametar.equals("")) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                Date datumPoc=sdf.parse(parametar);              
                ArrayList<Cenovnik> cenovnici= new ArrayList<>();
                for (Cenovnik c : lista) {
                    if (c.getVaziOd().after(datumPoc) || c.getVaziOd().equals(datumPoc)) {
                        cenovnici.add(c);
                    }
                }
                lista = cenovnici;
            }

            fireTableDataChanged();

        } catch (Exception ex) {
            if(ex instanceof ParseException){
             
            }
            
        }
        
    }

}
