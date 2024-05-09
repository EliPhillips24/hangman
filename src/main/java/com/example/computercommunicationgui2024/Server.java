package com.example.computercommunicationgui2024;

public class Server {

    public static void main(String[] args) throws Exception {
        ServerSocketConnector connector = new ServerSocketConnector();
        Thread connectorThread = new Thread(connector);
        connectorThread.start();
    }
}