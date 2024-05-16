package com.example.computercommunicationgui2024;

public class Server {
    static ServerSocketConnector connector;
    static Thread connectorThread;

    public static void main(String[] args) throws Exception {
        connector = new ServerSocketConnector(null);
        connectorThread = new Thread(connector);
        connectorThread.start();
    }
}