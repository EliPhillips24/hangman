module com.example.computercommunicationgui2024 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.computercommunicationgui2024 to javafx.fxml;
    exports com.example.computercommunicationgui2024;
}