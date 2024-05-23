package com.example.computercommunicationgui2024;

import com.example.CommunicationData;

import java.net.Socket;

public class Client {
    static MyCoolDataStructure queue;
    public static void main(String[] args) throws Exception {

        // Controller.initialize()
        System.out.println("Connecting to my server");
        Socket newSocket = new Socket("10.37.153.179",3256);

        //my ip is 10.37.153.179
        queue = new MyCoolDataStructure();
        ClientConnection newClient = new ClientConnection(newSocket);

        DataReader myDataReader = new DataReader(newClient, queue);
        ProgramLogicDoer myProgramLogicDoer = new ProgramLogicDoer(queue,null,false);
        Thread dataReadThread = new Thread(myDataReader);
        Thread programLogicThread = new Thread(myProgramLogicDoer);
        dataReadThread.start();
        programLogicThread.start();

        // Controller.sendMessage()  onAction
        CommunicationData data1 = new CommunicationData(null,null,"HIIII", 0);
        newClient.getObjOut().writeObject(data1);
        System.out.println("Client wrote: " + data1);
    }
}
