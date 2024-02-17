/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tabele;

import domen.Cenovnik;
import domen.StavkaCenovnika;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import kontroler.ClientKontroler;

/**
 *
 * @author Zarko
 */
public class TableModelStavka extends AbstractTableModel{
    
    ArrayList<StavkaCenovnika> lista;
    String kolone[] = {"Cena", "Valuta", "TipObracuna"};
    Long br = 0l;

    public TableModelStavka() {
        try {
            lista = new ArrayList<>();
        } catch (Exception ex) {
            Logger.getLogger(TableModelStavka.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public TableModelStavka(Cenovnik cen) {
        try {
            lista = ClientKontroler.getInstance().getAllStavke(cen);
        } catch (Exception ex) {
            Logger.getLogger(TableModelStavka.class.getName()).log(Level.SEVERE, null, ex);
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
        StavkaCenovnika stavka = lista.get(rowIndex);
        switch (columnIndex) {
          
            case 0:
                return stavka.getCena();
            case 1:
                return stavka.getValuta();
            case 2:
                return stavka.getPlacanje();
           
            default:
                return "return!";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void dodajListuStavke(ArrayList<StavkaCenovnika> stavke) {
        lista = stavke;
        fireTableDataChanged();
    }

    public void obrisiRed(int row) {

        
        lista.remove(row);
        
       
        fireTableDataChanged();
    }

    public StavkaCenovnika vratiStavkuCen(int row) {
        return lista.get(row);
    }

    public void dodajStavku(StavkaCenovnika stavka) {
        br = (long) lista.size() + 1;
        stavka.setStavkaCenId(br);
        lista.add(stavka);
        fireTableDataChanged();
    }

    public ArrayList<StavkaCenovnika> vratiListu() {
        return lista;
    }

   
    
}
