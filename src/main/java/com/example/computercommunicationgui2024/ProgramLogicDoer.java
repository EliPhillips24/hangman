package com.example.computercommunicationgui2024;

import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class ProgramLogicDoer implements Runnable {

    MyCoolDataStructure inData;
    ObjectOutputStream objOut;
    boolean serverMode;
    ClientController theController;

    static ArrayList<ObjectOutputStream> manyOuts = new ArrayList<ObjectOutputStream>();

    public ProgramLogicDoer(MyCoolDataStructure inData,ObjectOutputStream objOut, ClientController theController, boolean serverMode)  {
        this.inData = inData;
        this.objOut = objOut;
        manyOuts.add(objOut);
        this.theController = theController;
        this.serverMode = serverMode;
    }

    public void run() {
        Object inMessage1 = inData.get();
        while (true) {
            if (inMessage1 != null) {
                if (theController != null) {
                    // add the message to your JavaFX Control that displays many messages
                    theController.allMessages.getItems().add(inMessage1);
                } else {
                    System.out.println(inMessage1);
                }
                try {
                    if (serverMode) {
                        for (ObjectOutputStream eachOut: manyOuts) {
                            eachOut.writeObject("Mr. H overheard: " + inMessage1);
                        }
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
            inMessage1 = inData.get();
        }
    }
}
