/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import domen.Cenovnik;
import domen.Klijent;
import domen.Korisnik;
import domen.Potvrda;
import domen.Trotinet;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import kontroler.ServerKontroler;
import transfer.KlijentskiZahtev;
import transfer.Operacije;
import transfer.ResponseStatus;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Zarko
 */
public class ClientThread extends Thread {

    private Socket socket;

    ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!socket.isClosed()) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                KlijentskiZahtev kz = (KlijentskiZahtev) in.readObject();
                ServerskiOdgovor so = ObradaKZ(kz);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(so);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ServerskiOdgovor ObradaKZ(KlijentskiZahtev kz) {
        ServerskiOdgovor so = new ServerskiOdgovor(null, null, ResponseStatus.SUCCESS);
        try {
            switch (kz.getOperacija()) {
                case Operacije.LOGIN:
                    Korisnik p = (Korisnik) kz.getArgument();
                    Korisnik ulogovani = ServerKontroler.getInstance().login(p);
                    so.setOdgovor(ulogovani);
                    break;
                case Operacije.GET_ALL_MARKE:
                    so.setOdgovor(ServerKontroler.getInstance().getAllMarke());
                    break;
                case Operacije.ADD_TROTINET:
                    ServerKontroler.getInstance().addTrotinet((Trotinet) kz.getArgument());
                    break;
                case Operacije.GETALL_TROTINET:
                    so.setOdgovor(ServerKontroler.getInstance().getAllTrotinet());
                    break;
                case Operacije.DELETE_TROTINET:
                    ServerKontroler.getInstance().deleteTrotinet((Trotinet) kz.getArgument());
                    break;
                case Operacije.UPDATE_TROTINET:
                    ServerKontroler.getInstance().updateTrotinet((Trotinet) kz.getArgument());
                    break;
                case Operacije.ADD_CENOVNIK:
                    ServerKontroler.getInstance().addCenovnik((Cenovnik) kz.getArgument());
                    break;
                case Operacije.GETALL_CENOVNIK:
                    so.setOdgovor(ServerKontroler.getInstance().getAllCenovnik());
                    break;
                case Operacije.GET_ALL_STAVKA:
                    so.setOdgovor(ServerKontroler.getInstance().getAllStavka((Cenovnik) kz.getArgument()));
                    break;
                case Operacije.UPDATE_CENOVNIK:
                    ServerKontroler.getInstance().updateCenovnik((Cenovnik) kz.getArgument());
                    break;
                case Operacije.GET_ALL_KLIJENT:
                    so.setOdgovor(ServerKontroler.getInstance().getAllKlijent((Klijent) kz.getArgument()));
                    break;
                case Operacije.ADD_POTVRDA:
                    ServerKontroler.getInstance().addPotvrda((Potvrda) kz.getArgument());
                    break;
                case Operacije.GET_ALL_POTVRDE:
                    so.setOdgovor(ServerKontroler.getInstance().getAllPotvrde());
                    break;
                case Operacije.UPDATE_POTVRDA:
                    ServerKontroler.getInstance().updatePotvrda((Potvrda) kz.getArgument());
                    break;
                case Operacije.ODJAVA:
                    ServerKontroler.getInstance().odjavaKorisnika((Korisnik) kz.getArgument());
                    break;

                default:
                    return null;
            }
        } catch (Exception e) {
            so.setResponseStatus(ResponseStatus.ERROR);
            so.setException(e);
        }
        return so;
    }

}
