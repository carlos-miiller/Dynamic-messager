module br.com.cpaps.systemmanager {
    requires javafx.controls;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires javafx.fxml;
    requires javafx.web;

    opens br.com.cpaps.systemmanager to javafx.fxml;
    exports br.com.cpaps.systemmanager;
    exports br.com.cpaps.systemmanager.controllers;
    opens br.com.cpaps.systemmanager.controllers to javafx.fxml;
}
