package com.example.computercommunicationgui2024;

import com.example.CommunicationData;
import javafx.application.Platform;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketConnector implements Runnable {
    MyCoolDataStructure queue;
    Socket actualSocket;
    ServerController theController;
    int port = 3256;

    public ServerSocketConnector(ServerController theController) {
        this.theController = theController;
    }

    public void run() {
        ServerSocket mySocket;
        try {
            mySocket = new ServerSocket(3256);
        } catch (Exception ex) {
            System.out.println("Server failed: " + ex);
            return;
        }

        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress("google.com", 80));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Server socket at IP " + socket.getLocalAddress() + " port 3256");
        System.out.println("... waiting for connectionSSS");

        if (theController != null) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    theController.serverIP.setText(socket.getLocalAddress().getHostAddress());
                    theController.serverPort.setText(String.valueOf(port));
                }
            });
        }

        queue = new MyCoolDataStructure();

        ProgramLogicDoer myProgramLogicDoer = new ProgramLogicDoer(queue, theController, true);
        Thread programLogicThread = new Thread(myProgramLogicDoer);
        programLogicThread.start();
        
        while (true) {
            try {
                actualSocket = mySocket.accept();
                System.out.println("Connected to " + actualSocket.getInetAddress());

                ClientConnection newClient = new ClientConnection(actualSocket);
                myProgramLogicDoer.addSocket(newClient);
                if (theController != null) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            theController.clients.getItems().add(newClient);
                        }
                    });
                }

                DataReader myDataReader = new DataReader(newClient, queue);
                Thread dataReadThread = new Thread(myDataReader);
                dataReadThread.start();

                CommunicationData data1 = new CommunicationData(null,null,"HIIII", 0);
                newClient.getObjOut().writeObject(data1);
                System.out.println("ServerSocketConnector wrote: " + data1);
            } catch (Exception ex) {
                System.out.println("Server connection failed: "+ ex);
            }
        }
    }

}
