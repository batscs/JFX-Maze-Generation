package logic;

import javafx.scene.paint.Color;

/**
 * Interface, welches die Logik des Spiels Memory benutzt, um mit der Oberfläche zu kommunizieren. 
 *
 * @author github.com/batscs
 */
public interface GUIConnector {

    void setCell(int x, int y, Color color);

    void lockControls(boolean disable);

    void setAlgorithm(String name);

    void setSeed(long seed);

    int getSizeX();

    int getSizeY();

}
