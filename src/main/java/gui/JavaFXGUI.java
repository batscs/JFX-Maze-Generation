package gui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import logic.GUIConnector;
import javafx.scene.canvas.Canvas;
import logic.MazeLogic;

public class JavaFXGUI implements GUIConnector {

    private final Canvas field;

    private final BorderPane controls;
    private final VBox configuration;

    int sizeX;
    int sizeY;

    private final Label lblAlgo;
    private final Label lblSeed;

    GraphicsContext gc;

    double cellSize;

    private MazeLogic game;

    public JavaFXGUI(Canvas field, int sizeX, int sizeY, BorderPane controls, VBox configuration, Label lblAlgo, Label lblSeed, MazeLogic game) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.field = field;
        this.controls = controls;
        this.configuration = configuration;
        this.lblAlgo = lblAlgo;
        this.lblSeed = lblSeed;

        this.gc = field.getGraphicsContext2D();
        cellSize = field.getWidth() / sizeX;;

    }

    public double getCellSize() {
        return cellSize;
    }

    @Override
    public synchronized void setCell(int x, int y, Color color) {

        if (x <= sizeX && y < sizeY && x >= 0 && y >= 0) {
            gc.setFill(color);
            gc.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
            gc.save();
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

    @Override
    public int getSizeX() {
        return sizeX;
    }

    @Override
    public int getSizeY() {
        return sizeY;
    }

}
