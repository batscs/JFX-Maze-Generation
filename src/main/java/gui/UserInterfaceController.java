package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import logic.MazeLogic;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Main class for the user interface.
 *
 * @author github.com/batscs
 */
public class UserInterfaceController implements Initializable {

    /**
     * Label that displays the welcome text. Should be deleted when creating an
     * actual project.
     */

    private JavaFXGUI gui;

    private MazeLogic game;

    @FXML
    private Button btnRecursive;
    @FXML
    private BorderPane controls;
    @FXML
    private Button btnRandomBranches;
    @FXML
    private Button btnKruskal;

    private String mode;
    @FXML
    private VBox vbxConfiguration;
    @FXML
    private TextField inptDelay;
    @FXML
    private Button btnFinish;
    @FXML
    private Button btnStones;
    @FXML
    private Button btnBinaryTree;

    @FXML
    private Label lblSeed;
    @FXML
    private Label lblAlgorithm;
    @FXML
    private Canvas cnvGame;
    @FXML
    private TextField inptSize;




    private void fullReset() {
        GraphicsContext gc = cnvGame.getGraphicsContext2D();
        gc.setFill(new Color((float) 234 / 255, (float) 234 / 255, (float) 234 / 255, 1));
        gc.fillRect(0, 0, cnvGame.getWidth(), cnvGame.getHeight());

        double formFactor = cnvGame.getHeight() / cnvGame.getWidth();

        int sizeX = Integer.parseInt(inptSize.getText());
        int sizeY = (int) Math.floor(sizeX * formFactor);

        this.gui = new JavaFXGUI(cnvGame, sizeX, sizeY, controls, vbxConfiguration, lblAlgorithm, lblSeed, game);
        this.game = new MazeLogic(gui);

        this.gui.setAlgorithm("None");
        this.gui.setSeed(0);

        overdrawCanvas();

        cnvGame.setOnMouseDragged(event -> {
            double coordX = event.getX();
            double coordY = event.getY();
            double cellSize = gui.getCellSize();

            int cellX = (int) (coordX / cellSize);
            int cellY = (int) (coordY / cellSize);

            if (mode == "stone") {
                game.setStone(cellX, cellY);
            }

        });
    }

    private void overdrawCanvas() {
        // Um alle subpixel "Abschnitte" auch zu übermalen
        // Sonst bei Reset & Size Change gibt es noch Überreste
        GraphicsContext gc = cnvGame.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, cnvGame.getWidth(), cnvGame.getHeight());
        gc.save();
    }

    /**
     * This is where you need to add code that should happen during
     * initialization and then change the java doc comment.
     *
     * @param location  probably not used
     * @param resources probably not used
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        mode = "default";
        btnFinish.setVisible(false);
        inptDelay.setText("30");
        inptSize.setText("32");

        fullReset();
    }

    @FXML
    private void handleBtnRecursiveMaze(ActionEvent actionEvent) throws InterruptedException {
        if (this.game.countNeighbours(MazeLogic.Cell.NODE) > 0) {
            this.game.generateMazeEmpty();
            fullReset();
        }

        this.game.generateMazeRecursively(Integer.parseInt(inptDelay.getText()));
    }

    @FXML
    private void handleBtnResetMaze(ActionEvent actionEvent) {
        //fullReset();

        overdrawCanvas();
        this.game.generateMazeEmpty();
    }

    @FXML
    private void handleBtnKruskalMaze(ActionEvent actionEvent) {
        if (this.game.countNeighbours(MazeLogic.Cell.NODE) > 0) {
            this.game.generateMazeEmpty();
            fullReset();
        }
        this.game.generateMazeKruskal(Integer.parseInt(inptDelay.getText()));
    }

    @FXML
    private void handleBtnRandomBranchesMaze(ActionEvent actionEvent) {
        if (this.game.countNeighbours(MazeLogic.Cell.NODE) > 0) {
            this.game.generateMazeEmpty();
            fullReset();
        }
        this.game.generateMazeRandomBranches(Integer.parseInt(inptDelay.getText()));
    }

    @FXML
    private void handleBtnBinaryTreeMaze(ActionEvent actionEvent) {
        if (this.game.countNeighbours(MazeLogic.Cell.NODE) > 0) {
            this.game.generateMazeEmpty();
            fullReset();
        }
        this.game.generateMazeBinaryTree(Integer.parseInt(inptDelay.getText()));
    }

    @FXML
    private void handleBtnFieldInteraction(ActionEvent actionEvent) {
        Button btn = (Button) actionEvent.getSource();
        String[] id = btn.getId().substring(3).split("x");

        int y = Integer.parseInt(id[0]);
        int x = Integer.parseInt(id[1]);

        if (mode.equals("stone")) {
            this.game.setStone(x, y);
        }
    }

    @FXML
    private void handleBtnPlaceStone(ActionEvent actionEvent) {
        mode = "stone";
        btnFinish.setVisible(true);
        btnStones.setDisable(true);
        controls.setDisable(true);
    }

    @FXML
    private void handleBtnFinishInteraction(ActionEvent actionEvent) {
        mode = "default";
        btnFinish.setVisible(false);
        btnStones.setDisable(false);
        controls.setDisable(false);
    }

    @FXML
    private void handleBtnChangeSize(ActionEvent actionEvent) {
        fullReset();
    }
}