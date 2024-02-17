/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tabele;

import domen.Potvrda;
import domen.Trotinet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import kontroler.ClientKontroler;

/**
 *
 * @author Zarko
 */
public class TableModelPotvrda extends AbstractTableModel implements Runnable{
    
    ArrayList<Potvrda> lista;
    String kolone[] = {"PovrdaId", "DatumOd", "DatumDo", "cena", "sifraT", "Klijent", "stavkaCenovnika",};
    private String parametar = "";

    public TableModelPotvrda() {
        try {
            lista = ClientKontroler.getInstance().getAllPotvrde();
        } catch (Exception ex) {
            Logger.getLogger(TableModelPotvrda.class.getName()).log(Level.SEVERE, null, ex);
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
        Potvrda p = lista.get(rowIndex);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        switch (columnIndex) {
            case 0:
                return p.getPotvrdaId();
            case 1:
                return sdf.format(p.getDatumOd());
            case 2:
                return sdf.format(p.getDatumDo());
            case 3:
                return p.getCena();          
            case 4:
                return p.getTrotinet().getSifraT();
            case 5:
                return p.getKlijent();
            case 6:
                return p.getStavkaCenovnika().getCena()+", "+p.getStavkaCenovnika().getValuta();
            default:
                return "return!";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void ubaciListu(ArrayList<Potvrda> potvrde) {
        lista = potvrde;
        fireTableDataChanged();
    }

    public void obrisiRed(int row) {
        lista.remove(row);
        fireTableDataChanged();
    }

    public Potvrda vratiPotvrdu(int row) {
        return lista.get(row);
    }

    @Override
    public void run() {
         try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(4000);
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
            lista = ClientKontroler.getInstance().getAllPotvrde();
            if (!parametar.equals("")) {
                ArrayList<Potvrda> potvrde= new ArrayList<>();
                for (Potvrda t : lista) {
                    if (t.getTrotinet().getSifraT().toLowerCase().contains(parametar.toLowerCase())) {
                        potvrde.add(t);
                    }
                }
                lista = potvrde;
            }

            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
    
}
