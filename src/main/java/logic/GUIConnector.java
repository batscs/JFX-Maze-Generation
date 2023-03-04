package logic;

/**
 * Interface, welches die Logik des Spiels Memory benutzt, um mit der Oberfläche zu kommunizieren. 
 *
 * @author Maximilian Cwik
 */
public interface GUIConnector {

    void setCell(int x, int y, String color);

    void lockControls(boolean disable);

    void setAlgorithm(String name);

    void setSeed(long seed);

}
