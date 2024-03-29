package logic;

import javafx.scene.paint.Color;
import jdk.jfr.Event;

import java.util.*;

public class MazeLogic {

    final int lengthX;
    final int lengthY;

    Cell[][] map;

    private final GUIConnector gui;

    public enum Cell {
        STONE, EMPTY, UNUSABLE, NODE, RECURSIVE, OUT_OF_MAP;
    }

    public MazeLogic(GUIConnector gui) {
        lengthX = gui.getSizeX();
        lengthY = gui.getSizeY();
        this.gui = gui;

        map = new Cell[lengthX][lengthY];

        // initialize map with empty
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                setCell(i, j, Cell.EMPTY);
            }
        }
    }

    public void generateMazeEmpty() {
        gui.setAlgorithm("None");
        gui.setSeed(0);

        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                setCell(x, y, Cell.EMPTY);
            }
        }
    }

    public void generateMazeRecursively(int delay) {

        gui.setAlgorithm("Recursively \nSize: " + lengthX + " x " + lengthY);

        Random rand = new Random();
        long seed = rand.nextLong();;
        rand.setSeed(seed);
        gui.setSeed(seed);

        new Thread(() -> {

            gui.lockControls(true);

            Stack stack = new Stack<int[]>();

            // Start Coordinates
            int x = 0;
            int y = 0;

            x = rand.nextInt(lengthX);
            y = rand.nextInt(lengthY);

            stack.push(new int[] {x, y});

            do {

                boolean fallback = false;
                boolean animate = false;

                if (getCell(x, y) == Cell.EMPTY) {
                    if (getNumberOfNeighbours(x, y, Cell.NODE) + getNumberOfNeighbours(x, y, Cell.RECURSIVE) <= 1) {
                        stack.push(new int[] {x, y});
                        setCell(x, y, Cell.RECURSIVE);
                        animate = true;
                    } else {
                        setCell(x, y, Cell.UNUSABLE);
                        fallback = true;
                    }
                }

                if (getCell(x, y) == Cell.RECURSIVE || getCell(x, y) == Cell.NODE) {
                    int[][] emptyNeighbours = zipNeighbours(x, y, Cell.EMPTY);
                    if (emptyNeighbours == null) {
                        fallback = true;
                    } else {
                        stack.push(new int[] {x, y}); // Alte Node darf nicht verloren gehen falls mehrere Nachbarn vorhanden
                        int idx = rand.nextInt(emptyNeighbours.length);
                        x = emptyNeighbours[idx][0];
                        y = emptyNeighbours[idx][1];
                    }
                } else {
                    fallback = true;
                }

                if (fallback) {

                    int[] recursive = (int[]) stack.pop();
                    x = recursive[0];
                    y = recursive[1];

                    if (getCell(x, y) == Cell.RECURSIVE && getNumberOfNeighbours(x, y, Cell.EMPTY) == 0) {
                        setCell(x, y, Cell.NODE);
                        animate = true;
                    }

                }

                // Wait for Animation
                if (animate && delay > 0) {
                    try {
                            Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

            } while (!stack.isEmpty());

            gui.lockControls(false);
            Thread.currentThread().interrupt();

        }).start();

    }

    public void generateMazeRandomBranches(int delay) {

        gui.setAlgorithm("Random Branches");

        Random rand = new Random();
        long seed = rand.nextLong();;
        rand.setSeed(seed);
        gui.setSeed(seed);

        new Thread(() -> {

            gui.lockControls(true);

            int x = lengthX / 2;
            int y = lengthY / 2;

            x = rand.nextInt(lengthX);
            y = rand.nextInt(lengthY);

            LinkedList<int[]> queue = new LinkedList<>();
            queue.push(new int[] {x, y});

            setCell(x, y, Cell.NODE);

            int stuck = 0;

            do {

                boolean animate = false;

                int[] current = queue.getFirst();
                x = current[0];
                y = current[1];

                if (getNumberOfNeighbours(x, y, Cell.NODE) == 1 && getCell(x, y) == Cell.EMPTY) {
                    setCell(x, y, Cell.NODE);
                    animate = true;
                } else {
                    queue.removeFirst();
                }

                if (getNumberOfNeighbours(x, y, Cell.NODE) > 1 && getCell(x,y) == Cell.EMPTY) {
                    setCell(x,y, Cell.UNUSABLE);
                }

                int[][] emptyNeighbours = zipNeighbours(x, y, Cell.EMPTY);

                if (emptyNeighbours != null) {
                    int idx = rand.nextInt(emptyNeighbours.length);
                    queue.addLast(new int[]{emptyNeighbours[idx][0], emptyNeighbours[idx][1]});
                }

                try {
                    if (animate) {
                        Thread.sleep(delay);
                        stuck = 0;
                    } else {
                        //System.out.println(stuck);
                        stuck++;
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


            } while (!queue.isEmpty() && stuck < lengthX * lengthY * 30);

            gui.lockControls(false);
            Thread.currentThread().interrupt();

        }).start();

    }

    private int makeEvenNumberNextSmallest(int num) {
        return num % 2 == 0 ? num : num -1 ;
    }

    public void generateMazeBinaryTree(int delay) {

        gui.setAlgorithm("Binary Tree");

        Random rand = new Random();
        long seed = rand.nextLong();;
        rand.setSeed(seed);
        gui.setSeed(seed);

        new Thread(() -> {

            gui.lockControls(true);

            boolean animate;

            for (int y = 0; y < lengthY; y += 2) {
                for (int x = 0; x < lengthX; x += 2) {

                    animate = false;

                    if (getCell(x, y) == Cell.EMPTY) {
                        setCell(x,y, Cell.NODE);

                        int choice = rand.nextInt(2);
                        int neighbourX = x + choice;
                        int neighbourY = y + 1 - choice;

                        setCell(neighbourX, neighbourY, Cell.NODE);

                        animate = true;
                    }

                    try {
                        if (animate) {
                            Thread.sleep(delay);
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            }

            // x-Achse Border (unten)
            for (int x = 0; x < lengthX; x++) {
                setCell(x, makeEvenNumberNextSmallest(lengthY - 1), Cell.NODE);
                setCell(x, makeEvenNumberNextSmallest(lengthY - 1) + 1, Cell.EMPTY);

                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            // y-Achse Border (links)
            for (int y = 0; y < lengthY; y++) {
                setCell(makeEvenNumberNextSmallest(lengthX - 1), y, Cell.NODE);
                setCell(makeEvenNumberNextSmallest(lengthX - 1) + 1, y, Cell.EMPTY);

                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            gui.lockControls(false);
            Thread.currentThread().interrupt();

        }).start();

    }

    public void generateMazeKruskal(int delay) {

        new Thread(() -> {

            gui.lockControls(true);

            Random rand = new Random();
            boolean animate = false;

            while (countNeighbours(Cell.EMPTY) > 0) {
                animate = false;

                int x = rand.nextInt(lengthX);
                int y = rand.nextInt(lengthY);

                if (getNumberOfNeighbours(x, y, Cell.NODE) <= 2 && getCell(x, y) == Cell.EMPTY) {
                    setCell(x,y, Cell.NODE);
                    animate = true;
                } else {
                    setCell(x, y, Cell.UNUSABLE);
                }

                try {
                    if (animate) {
                        Thread.sleep(delay);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


            }

            gui.lockControls(false);
            Thread.currentThread().interrupt();

        }).start();

    }

    private void setCell(int x, int y, Cell content) {
        if (x < lengthX && y < lengthY && x >= 0 && y >= 0) {
            map[x][y] = content;
            gui.setCell(x, y, cellToColor(content));
        }
    }

    public void setStone(int x, int y) {
        setCell(x, y, Cell.STONE);
    }

    public int countNeighbours(Cell content) {
        int result = 0;
        for (int x = 0; x < lengthX; x++) {
            for (int y = 0; y < lengthY; y++) {
                if (getCell(x, y) == content) {
                    result++;
                }
            }
        }
        return result;
    }

    private Cell getCell(int x, int y) {
        if (x < lengthX && x >= 0 && y < lengthY && y >= 0) {
            return map[x][y];
        } else {
            return Cell.OUT_OF_MAP;
        }
    }

    private boolean notConstructingStairs(int x, int y, int oldX, int oldY) {
        boolean noStairs = true;

        int dirX = x - oldX;
        int dirY = y - oldY;

        return noStairs;
    }

    private int[][] zipNeighbours(int x, int y, Cell content) {
        int numNeighbours = getNumberOfNeighbours(x, y, content);
        int idx = 0;
        int[][] result = new int[numNeighbours][2];

        if (numNeighbours == 0) {
            return null;
        }

        if (getCell(x-1, y) == content) {
            result[idx][0] = x-1;
            result[idx][1] = y;
            idx++;
        }

        if (getCell(x+1, y) == content) {
            result[idx][0] = x+1;
            result[idx][1] = y;
            //System.out.println("b");
            idx++;
        }

        if (getCell(x, y-1) == content) {
            result[idx][0] = x;
            result[idx][1] = y-1;
            //System.out.println("a");
            idx++;
        }

        if (getCell(x, y+1) == content) {
            result[idx][0] = x;
            result[idx][1] = y+1;
            //System.out.println("d");
            idx++;
        }

        return result;
    }

    private int getNumberOfNeighbours(int x, int y, Cell content) {
        int sum = 0;

        if (x > 0) {
            if (getCell(x-1, y) == content) {
                sum++;
            }
        }

        if (x < lengthX - 1) {
            if (getCell(x+1, y) == content) {
                sum++;
            }
        }

        if (y > 0) {
            if (getCell(x, y-1) == content) {
                sum++;
            }
        }

        if (y <= lengthY - 2) {
            if (getCell(x, y+1) == content) {
                sum++;
            }
        }

        return sum;
    }

    private int getNumberOfNeighboursBig(int x, int y, Cell content) {
        int sum = 0;

        System.out.println(x + " | " + y);

        // Oben Rechts
        if (getCell(x+1, y+1) == content) {
            sum++;
            System.out.println("a");
        }

        // Rechts
        /*
        if (getCell(x+1, y) == content) {
            sum++;
        }
         */

        // Unten Rechts
        if (getCell(x+1, y-1) == content) {
            sum++;
            System.out.println("b");
        }

        // Unten
        /*
        if (getCell(x, y-1) == content) {
            sum++;
        }
         */

        // Unten Links
        if (getCell(x-1, y-1) == content) {
            sum++;
            System.out.println("c");
        }

        // Links
        /*
        if (getCell(x-1, y) == content) {
            sum++;
        }
         */

        // Oben Links
        if (getCell(x-1, y+1) == content) {
            sum++;
            System.out.println("d");
        }

        // Oben
        /*
        if (getCell(x, y+1) == content) {
            sum++;
        }
         */

        return sum;
    }

    private String cellToChar(Cell content) {
        String result = "?";

        if (content == Cell.EMPTY) {
            result = ConsoleColors.BLACK + "██ " + ConsoleColors.RESET;
        } else if (content == Cell.RECURSIVE) {
            result = ConsoleColors.WHITE + "██ " + ConsoleColors.RESET;
        } else if (content == Cell.NODE) {
            result = ConsoleColors.GREEN + "██ " + ConsoleColors.RESET;
        } else if (content == Cell.UNUSABLE) {
            result = ConsoleColors.BLACK + "██ " + ConsoleColors.RESET;
        }

        return result;
    }

    private Color cellToColor(Cell content) {
        Color result = Color.BLACK;

        if (content == Cell.EMPTY) {
            result = Color.BLACK;
        } else if (content == Cell.RECURSIVE) {
            result = new Color((float) 192 / 255, (float)  57 / 255, (float) 43 / 255, 1); // Red
        } else if (content == Cell.NODE) {
            result = new Color((float) 39 / 255, (float) 174 / 255, (float) 96 / 255, 1); // Green
        } else if (content == Cell.UNUSABLE) {
            result = Color.BLACK;
        } else if (content == Cell.STONE) {
            result = new Color((float) 32 / 255, (float) 32 / 255, (float) 32 / 255, 1); // Gray (Stone)
        }

        return result;
    }

    public void printMap() {

        StringBuilder output = new StringBuilder("");
        
        for (int x  = 0; x < map[0].length; x++) {
            for (int y = 0; y < map.length; y++) {
                output.append(cellToChar(map[y][x]));
            }
            output.append("\n");

        }

        System.out.println(output);
    }

}
