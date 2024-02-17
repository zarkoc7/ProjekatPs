/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tabele;

import domen.Trotinet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import kontroler.ClientKontroler;

/**
 *
 * @author Zarko
 */
public class TableModelTrotinet extends AbstractTableModel implements Runnable{
    
    private ArrayList<Trotinet> lista;
    private String[] kolone = {"sifraT", "maxSpeed", "maxRange", "maxPower", "maxPayload","marka"};
    private String parametar = "";

    public TableModelTrotinet() {
        try {
            lista = ClientKontroler.getInstance().getAllTrotinet();
        } catch (Exception ex) {
            Logger.getLogger(TableModelTrotinet.class.getName()).log(Level.SEVERE, null, ex);
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
    public String getColumnName(int i) {
        return kolone[i];
    }

    @Override
    public Object getValueAt(int row, int column) {
        Trotinet o = lista.get(row);

        switch (column) {
            case 0:
                return o.getSifraT();
            case 1:
                return o.getMaxSpeed();
            case 2:
                return o.getMaxRange();
            case 3:
                return o.getMaxPower();
            case 4:
                return o.getMaxPayload();
            case 5:
                return o.getMarka();

            default:
                return null;
        }
    }

    public Trotinet vratiTrotinet(int row) {
        return lista.get(row);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(10000);
                refreshTable();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(TableModelTrotinet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    public void refreshTable() {
        try {
            lista = ClientKontroler.getInstance().getAllTrotinet();
            if (!parametar.equals("")) {
                ArrayList<Trotinet> trotineti = new ArrayList<>();
                for (Trotinet t : lista) {
                    if (t.getSifraT().toLowerCase().contains(parametar.toLowerCase())) {
                        trotineti.add(t);
                    }
                }
                lista = trotineti;
            }

            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void obrisiTrotinet(int row) {
        lista.remove(row);
        fireTableDataChanged();
    }
    
}
