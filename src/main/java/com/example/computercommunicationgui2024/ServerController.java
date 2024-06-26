package com.example.computercommunicationgui2024;

import com.example.CommunicationData;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ServerController extends ClientServerController {

    public TextField serverIP;
    public TextField serverPort;

    public TableView<ClientConnection> clients;
    public TableColumn<ClientConnection, String> clientIPsColumn;
    public TableColumn<ClientConnection, String> nameColumn;

    public TableView<CommunicationData> allMessages;
    public TableColumn<CommunicationData, String> IPColumn;
    public TableColumn<CommunicationData, String> fromColumn;
    public TableColumn<CommunicationData, String> toColumn;
    public TableColumn<CommunicationData, String> messageColumn;

    static ServerSocketConnector connector;
    static Thread connectorThread;

    public PasswordField Word;

    public String FinalWord;

    public  int Score;



    //This is for head body kees and toes

    public TextField Head;
    public TextField Body;
    public TextField LeftArm;
    public TextField RightArm;
    public TextField LeftLeg;
    public TextField RightLeg;

    public TextField lookWord;

    public TextField letter1;
    public TextField letter2;
    public TextField letter3;
    public TextField letter4;
    public TextField letter5;
    public TextField letter6;
    public TextField letter7;
    public TextField letter8;
    public TextField letter9;
    public TextField letter10;





    public  TextField Win;

    public  TextField goodWord;
    public  TextField badWord;

    public String BadChar;
    public String GoodChar;


    public void initialize() {
        ArrayList<String> GoodWord = new ArrayList<String>();
        ArrayList<String> BadWord = new ArrayList<String>();
        GoodChar = " ";
        BadChar = " ";

        Score = 0;

        clientIPsColumn.setCellValueFactory(new PropertyValueFactory<ClientConnection, String>("clientIP"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<ClientConnection, String>("name"));

        IPColumn.setCellValueFactory(new PropertyValueFactory<CommunicationData, String>("fromIPAddress"));
        fromColumn.setCellValueFactory(new PropertyValueFactory<CommunicationData, String>("from"));
        toColumn.setCellValueFactory(new PropertyValueFactory<CommunicationData, String>("to"));
        messageColumn.setCellValueFactory(new PropertyValueFactory<CommunicationData, String>("message"));

        connector = new ServerSocketConnector(this);
        connectorThread = new Thread(connector);
        connectorThread.start();
    }
    public void doHangmanLogic(String lastMessage) {

        char firstCharWord = Word.getText().charAt(0);
        char firstCharLastMessage = lastMessage.charAt(0);

        String firstChar = String.valueOf(firstCharLastMessage);

        if (Word.getText().contains(firstChar)) {

        GoodChar = GoodChar + firstChar + ",";

        goodWord.setText(GoodChar);


            if(Word.getText().equals(lastMessage)){
                Win.setText("You Won");
            }
//this to show the word
//I could use a forlop but whatever

        if (FinalWord.charAt(0) == lastMessage.charAt(0)) {
                letter1.setText(String.valueOf(firstCharLastMessage));
        }
            if (FinalWord.charAt(1) == lastMessage.charAt(0)) {
                letter2.setText(String.valueOf(firstCharLastMessage));
            }
            if (FinalWord.charAt(2) == lastMessage.charAt(0)) {
                letter3.setText(String.valueOf(firstCharLastMessage));
            }
            if (FinalWord.charAt(3) == lastMessage.charAt(0)) {
                letter4.setText(String.valueOf(firstCharLastMessage));
            }
            if (FinalWord.charAt(4) == lastMessage.charAt(0)) {
                letter5.setText(String.valueOf(firstCharLastMessage));
            }
            if (FinalWord.charAt(5) == lastMessage.charAt(0)) {
                letter6.setText(String.valueOf(firstCharLastMessage));
            }
            if (FinalWord.charAt(6) == lastMessage.charAt(0)) {
                letter7.setText(String.valueOf(firstCharLastMessage));
            }
            if (FinalWord.charAt(7) == lastMessage.charAt(0)) {
                letter8.setText(String.valueOf(firstCharLastMessage));
            }
            if (FinalWord.charAt(8) == lastMessage.charAt(0)) {
                letter9.setText(String.valueOf(firstCharLastMessage));
            }



}else{
    System.out.println("Not Message" + lastMessage);
//badword
            BadChar = BadChar + firstChar + ",";

            badWord.setText(BadChar);
            Score = Score +1;

if(Score == 1){
    Head.setText("0");
}
if(Score == 2){
    Body.setText("|");
}
if(Score == 3){
    LeftArm.setText("/");
}
if(Score == 4){
    RightArm.setText("_");
}
if(Score == 5){
    LeftLeg.setText("/");
}
if(Score == 6){
    RightLeg.setText("|");
}
    if(Score == 7){
System.out.println("you Lost");
Win.setText("You lost");
Head.setText("");
Body.setText("");
LeftArm.setText("");
RightArm.setText("");
LeftLeg.setText("");
RightLeg.setText("");
Score = 0;
    }

}
    }
public void setWord() {
    FinalWord =  Word.getText();
System.out.println("FinalWord"+ FinalWord);

}
    public void reset() {
        Head.setText("");
        Body.setText("");
        LeftArm.setText("");
        RightArm.setText("");
        LeftLeg.setText("");
        RightLeg.setText("");
        Score = 0;
badWord.setText("");
goodWord.setText("");
Win.setText("Put In Your New Word");
GoodChar = " ";
BadChar = " ";
letter1.setText("");
        letter2.setText("");
        letter3.setText("");
        letter4.setText("");
        letter5.setText("");
        letter6.setText("");
        letter7.setText("");
        letter8.setText("");
        letter9.setText("");






    }


    }