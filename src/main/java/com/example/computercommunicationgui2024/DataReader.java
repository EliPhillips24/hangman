package com.example.computercommunicationgui2024;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;

public class DataReader implements Runnable {
    MyCoolDataStructure inData;
    ClientConnection client;

    public DataReader(ClientConnection client, MyCoolDataStructure inData) throws Exception {
        this.inData = inData;
        this.client = client;
    }

    public void run()  {
        while (true) {
            try {
                CommunicationData inMessage1 = (CommunicationData)client.getObjIn().readObject();

                InetAddress fromIPAddress = client.getActualSocket().getInetAddress();
                inMessage1.setFromIPAddress(fromIPAddress);
                inData.put(inMessage1);
                System.out.println("DataReader read: " + inMessage1);
            } catch (IOException ioex) {
                // its ok to get IOException when there is no object to read in from ObjectInputStream
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
