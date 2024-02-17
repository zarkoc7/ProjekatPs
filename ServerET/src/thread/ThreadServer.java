/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */ 

package thread;

import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Zarko
 */
public class ThreadServer extends Thread {

    private ServerSocket serverSocket;

    public ThreadServer() {
        try {
            serverSocket = new ServerSocket(9000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("Klijent se povezao!");
                ClientThread ct= new  ClientThread(socket);
                System.out.println("Klijent je povezan!");
                ct.start();
                System.out.println("Klijent je povezan1!");
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

}
