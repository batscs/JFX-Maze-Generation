package gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import logic.GUIConnector;

public class JavaFXGUI implements GUIConnector {

    private final Button[][] field;

    private final BorderPane controls;
    private final VBox configuration;

    private Label lblAlgo, lblSeed;

    public JavaFXGUI(Button[][] field, BorderPane controls, VBox configuration, Label lblAlgo, Label lblSeed) {
        this.field = field;
        this.controls = controls;
        this.configuration = configuration;
        this.lblAlgo = lblAlgo;
        this.lblSeed = lblSeed;
    }

    @Override
    public void setCell(int x, int y, String color) {
        if (x <= field.length && y < field[0].length) {
            field[x][y].setStyle("-fx-background-color: " + color);
        }
    }

    @Override
    public void lockControls(boolean disable) {
        controls.setDisable(disable);
        configuration.setDisable(disable);
    }

    @Override
    public void setAlgorithm(String name) {
        lblAlgo.setText(name);
    }

    @Override
    public void setSeed(long seed) {
        lblSeed.setText("" + seed);
    }

}
