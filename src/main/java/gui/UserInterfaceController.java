package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import logic.MazeLogic;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Main class for the user interface.
 *
 * @author mjo, cei
 */
public class UserInterfaceController implements Initializable {

    /**
     * Label that displays the welcome text. Should be deleted when creating an
     * actual project.
     */

    private JavaFXGUI gui;

    private MazeLogic game;

    @FXML
    private Button btn0x0, btn0x1, btn0x2, btn0x3, btn0x4, btn0x5, btn0x6, btn0x7, btn0x8, btn0x9, btn0x10, btn0x11, btn0x12;

    @FXML
    private Button btn1x0, btn1x1, btn1x2, btn1x3, btn1x4, btn1x5, btn1x6, btn1x7, btn1x8, btn1x9, btn1x10, btn1x11, btn1x12;

    @FXML
    private Button btn2x0, btn2x1, btn2x2, btn2x3, btn2x4, btn2x5, btn2x6, btn2x7, btn2x8, btn2x9, btn2x10, btn2x11, btn2x12;

    @FXML
    private Button btn3x0, btn3x1, btn3x2, btn3x3, btn3x4, btn3x5, btn3x6, btn3x7, btn3x8, btn3x9, btn3x10, btn3x11, btn3x12;

    @FXML
    private Button btn4x0, btn4x1, btn4x2, btn4x3, btn4x4, btn4x5, btn4x6, btn4x7, btn4x8, btn4x9, btn4x10, btn4x11, btn4x12;

    @FXML
    private Button btn5x0, btn5x1, btn5x2, btn5x3, btn5x4, btn5x5, btn5x6, btn5x7, btn5x8, btn5x9, btn5x10, btn5x11, btn5x12;

    @FXML
    private Button btn6x0, btn6x1, btn6x2, btn6x3, btn6x4, btn6x5, btn6x6, btn6x7, btn6x8, btn6x9, btn6x10, btn6x11, btn6x12;

    @FXML
    private Button btn7x0, btn7x1, btn7x2, btn7x3, btn7x4, btn7x5, btn7x6, btn7x7, btn7x8, btn7x9, btn7x10, btn7x11, btn7x12;

    @FXML
    private Button btn8x0, btn8x1, btn8x2, btn8x3, btn8x4, btn8x5, btn8x6, btn8x7, btn8x8, btn8x9, btn8x10, btn8x11, btn8x12;

    @FXML
    private Button btn9x0, btn9x1, btn9x2, btn9x3, btn9x4, btn9x5, btn9x6, btn9x7, btn9x8, btn9x9, btn9x10, btn9x11, btn9x12;

    @FXML
    private Button btn10x0, btn10x1, btn10x2, btn10x3, btn10x4, btn10x5, btn10x6, btn10x7, btn10x8, btn10x9, btn10x10, btn10x11, btn10x12;

    @FXML
    private Button btn11x0, btn11x1, btn11x2, btn11x3, btn11x4, btn11x5, btn11x6, btn11x7, btn11x8, btn11x9, btn11x10, btn11x11, btn11x12;

    @FXML
    private Button btn12x0, btn12x1, btn12x2, btn12x3, btn12x4, btn12x5, btn12x6, btn12x7, btn12x8, btn12x9, btn12x10, btn12x11, btn12x12;

    @FXML
    private Button btn13x0, btn13x1, btn13x2, btn13x3, btn13x4, btn13x5, btn13x6, btn13x7, btn13x8, btn13x9, btn13x10, btn13x11, btn13x12;

    @FXML
    private Button btn14x0, btn14x1, btn14x2, btn14x3, btn14x4, btn14x5, btn14x6, btn14x7, btn14x8, btn14x9, btn14x10, btn14x11, btn14x12;

    @FXML
    private Button btn15x0, btn15x1, btn15x2, btn15x3, btn15x4, btn15x5, btn15x6, btn15x7, btn15x8, btn15x9, btn15x10, btn15x11, btn15x12;

    @FXML
    private Button btn16x0, btn16x1, btn16x2, btn16x3, btn16x4, btn16x5, btn16x6, btn16x7, btn16x8, btn16x9, btn16x10, btn16x11, btn16x12;

    @FXML
    private Button btn17x0, btn17x1, btn17x2, btn17x3, btn17x4, btn17x5, btn17x6, btn17x7, btn17x8, btn17x9, btn17x10, btn17x11, btn17x12;

    @FXML
    private Button btn18x0, btn18x1, btn18x2, btn18x3, btn18x4, btn18x5, btn18x6, btn18x7, btn18x8, btn18x9, btn18x10, btn18x11, btn18x12;


    private Button[][] field = new Button[17][13];

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
        inptDelay.setText("80");

        field = new Button[][] {
                {btn0x0, btn1x0, btn2x0, btn3x0, btn4x0, btn5x0, btn6x0, btn7x0, btn8x0, btn9x0, btn10x0, btn11x0, btn12x0, btn13x0, btn14x0, btn15x0, btn16x0, btn17x0, btn18x0},
                {btn0x1, btn1x1, btn2x1, btn3x1, btn4x1, btn5x1, btn6x1, btn7x1, btn8x1, btn9x1, btn10x1, btn11x1, btn12x1, btn13x1, btn14x1, btn15x1, btn16x1, btn17x1, btn18x1},
                {btn0x2, btn1x2, btn2x2, btn3x2, btn4x2, btn5x2, btn6x2, btn7x2, btn8x2, btn9x2, btn10x2, btn11x2, btn12x2, btn13x2, btn14x2, btn15x2, btn16x2, btn17x2, btn18x2},
                {btn0x3, btn1x3, btn2x3, btn3x3, btn4x3, btn5x3, btn6x3, btn7x3, btn8x3, btn9x3, btn10x3, btn11x3, btn12x3, btn13x3, btn14x3, btn15x3, btn16x3, btn17x3, btn18x3},
                {btn0x4, btn1x4, btn2x4, btn3x4, btn4x4, btn5x4, btn6x4, btn7x4, btn8x4, btn9x4, btn10x4, btn11x4, btn12x4, btn13x4, btn14x4, btn15x4, btn16x4, btn17x4, btn18x4},
                {btn0x5, btn1x5, btn2x5, btn3x5, btn4x5, btn5x5, btn6x5, btn7x5, btn8x5, btn9x5, btn10x5, btn11x5, btn12x5, btn13x5, btn14x5, btn15x5, btn16x5, btn17x5, btn18x5},
                {btn0x6, btn1x6, btn2x6, btn3x6, btn4x6, btn5x6, btn6x6, btn7x6, btn8x6, btn9x6, btn10x6, btn11x6, btn12x6, btn13x6, btn14x6, btn15x6, btn16x6, btn17x6, btn18x6},
                {btn0x7, btn1x7, btn2x7, btn3x7, btn4x7, btn5x7, btn6x7, btn7x7, btn8x7, btn9x7, btn10x7, btn11x7, btn12x7, btn13x7, btn14x7, btn15x7, btn16x7, btn17x7, btn18x7},
                {btn0x8, btn1x8, btn2x8, btn3x8, btn4x8, btn5x8, btn6x8, btn7x8, btn8x8, btn9x8, btn10x8, btn11x8, btn12x8, btn13x8, btn14x8, btn15x8, btn16x8, btn17x8, btn18x8},
                {btn0x9, btn1x9, btn2x9, btn3x9, btn4x9, btn5x9, btn6x9, btn7x9, btn8x9, btn9x9, btn10x9, btn11x9, btn12x9, btn13x9, btn14x9, btn15x9, btn16x9, btn17x9, btn18x9},
                {btn0x10, btn1x10, btn2x10, btn3x10, btn4x10, btn5x10, btn6x10, btn7x10, btn8x10, btn9x10, btn10x10, btn11x10, btn12x10, btn13x10, btn14x10, btn15x10, btn16x10, btn17x10, btn18x10},
                {btn0x11, btn1x11, btn2x11, btn3x11, btn4x11, btn5x11, btn6x11, btn7x11, btn8x11, btn9x11, btn10x11, btn11x11, btn12x11, btn13x11, btn14x11, btn15x11, btn16x11, btn17x11, btn18x11},
                {btn0x12, btn1x12, btn2x12, btn3x12, btn4x12, btn5x12, btn6x12, btn7x12, btn8x12, btn9x12, btn10x12, btn11x12, btn12x12, btn13x12, btn14x12, btn15x12, btn16x12, btn17x12, btn18x12},
        };

        for (int x = 0; x < field.length; x++) {
            for (int y = 0; y < field[x].length; y++) {
                field[x][y].setText("");
                field[x][y].setStyle("-fx-background-radius: 20px");
                field[x][y].setStyle("-fx-border: none");
            }
        }

        this.gui = new JavaFXGUI(field, controls, vbxConfiguration, lblAlgorithm, lblSeed);
        this.game = new MazeLogic(field.length, field[0].length, gui);

        this.gui.setAlgorithm("None");
        this.gui.setSeed(0);
    }

    @FXML
    private void handleBtnRecursiveMaze(ActionEvent actionEvent) throws InterruptedException {
        if (this.game.count(MazeLogic.Cell.NODE) > 0) {
            this.game.generateMazeEmpty();
        }

        this.game.generateMazeRecursively(Integer.parseInt(inptDelay.getText()));
    }

    @FXML
    private void handleBtnResetMaze(ActionEvent actionEvent) {
        this.game.generateMazeEmpty();
    }

    @FXML
    private void handleBtnKruskalMaze(ActionEvent actionEvent) {
        if (this.game.count(MazeLogic.Cell.NODE) > 0) {
            this.game.generateMazeEmpty();
        }
        this.game.generateMazeKruskal(Integer.parseInt(inptDelay.getText()));
    }

    @FXML
    private void handleBtnRandomBranchesMaze(ActionEvent actionEvent) {
        if (this.game.count(MazeLogic.Cell.NODE) > 0) {
            this.game.generateMazeEmpty();
        }
        this.game.generateMazeRandomBranches(Integer.parseInt(inptDelay.getText()));
    }

    @FXML
    private void handleBtnBinaryTreeMaze(ActionEvent actionEvent) {
        if (this.game.count(MazeLogic.Cell.NODE) > 0) {
            this.game.generateMazeEmpty();
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
}