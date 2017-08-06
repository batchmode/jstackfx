package io.twasyl.jstackfx.controllers;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import io.twasyl.jstackfx.factory.PidFactory;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SelectJvmController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> jvmList;

    @FXML
    void close() {
        ((Stage) jvmList.getParent().getScene().getWindow()).close();
    }

    private Long pid = null;

    @FXML
    void clickedOnJvmList(MouseEvent event) {
        if (event.getClickCount() == 2) {
            String selectedItem = jvmList.getSelectionModel().getSelectedItem();
            pid = Long.valueOf(selectedItem.split(" ")[0]);
            close();
        }
    }

    public Optional<Long> selectedPid() {
        return Optional.ofNullable(pid);
    }

    @FXML
    void initialize() {
        assert jvmList != null : "fx:id=\"jvmList\" was not injected: check your FXML file 'jvmList.fxml'.";

        loadJvms();

    }

    private void loadJvms() {
        jvmList.getItems().addAll(PidFactory.listPids());
    }
}
