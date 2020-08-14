package tiles;

import entities.Player;
import math3D.vectors.Vector2i;
import utils.Handler;
import utils.Util;

import java.awt.*;

public class World {

    private int[][] map;
    private int entity_id;
    private Dimension size;
    private Vector2i spawnPosition;
    private Player player;
    private Handler handler;

    public World(Handler handler, String filename) {
        this.handler = handler;
        loadMap("resources/levels/" + filename);
    }

    public void loadMap(String file) {
        String[] tokens = Util.loadFileAsString(file).split("\\s+");
        setSize(Util.parseInt(tokens[0]), Util.parseInt(tokens[1]));
        this.setEntity_id(Util.parseInt(tokens[2]));
        spawnPosition = new Vector2i(Util.parseInt(tokens[3]), Util.parseInt(tokens[4]));
        player = new Player(handler, spawnPosition.x, spawnPosition.y);
        map = new int[size.width][size.height];

        for (int y = 0; y < size.height; y++) {
            for (int x = 0; x < size.width; x++) {
                try {
                    setMap(x, y, Util.parseInt(tokens[(x + y * size.width) + 5]));
                } catch(Exception e) {
                    Util.error(e, e.getLocalizedMessage());
                    Util.exit(-1);
                }
            }
        }
    }

    public void render(Graphics2D g2d) {
        int xStart = (int) Math.max(0, handler.getCamera().getxOffset() / Tile.TILE_SIZE);
        int xEnd = (int) Math.min(size.width, (handler.getCamera().getxOffset() + handler.getWidth()) / Tile.TILE_SIZE + 1);
        int yStart = (int) Math.max(0, handler.getCamera().getyOffset() / Tile.TILE_SIZE);
        int yEnd = (int) Math.min(size.height, (handler.getCamera().getyOffset() + handler.getHeight()) / Tile.TILE_SIZE + 1);

        for(int y = yStart; y < yEnd; y++){
            for(int x = xStart; x < xEnd; x++){
                getTile(x, y).render(g2d, (int) (x * Tile.TILE_SIZE - handler.getCamera().getxOffset()),
                        (int) (y * Tile.TILE_SIZE - handler.getCamera().getyOffset()));
            }
        }
        player.render(g2d);
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= size.width || y >= size.height) {
            return Tile.grass1;
        }
        Tile tile = Tile.tiles[map[x][y]];

        if (tile == null) {
            return Tile.dirt;
        } else {
            return tile;
        }
    }

    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public int getMapIndex(int indexX, int indexY) {
        return map[indexX][indexY];
    }

    public int getMapIndex(int index) {
        return map[index][index];
    }

    public void setMap(int[] indexX, int[] indexY) {
        for (int y = 0; y < size.height; y++) {
            for (int x = 0; x < size.width; x++) {
                if(indexX.length < size.width || indexY.length < size.height) {
                    map[x][y] = map[indexX[x]][indexY[y]];
                }
            }
        }
    }

    public void setMap(int indexX, int indexY, int value) throws Exception {
        if(indexX <= size.width || indexY <= size.height) {
            if(value < 16) {
                map[indexX][indexY] = value;
            } else {
                throw new Exception("Value is greater then the tile id's!");
            }
        } else {
            throw new Exception("Index-x or Index-y is greater then the width or height of the map!");
        }
    }

    public int getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(int entity_id) {
        this.entity_id = entity_id;
    }

    public int getWidth() {
        return size.width;
    }

    public void setWidth(int width) {
        this.size.width = width;
    }

    public int getHeight() {
        return size.height;
    }

    public void setHeight(int height) {
        this.size.height = height;
    }

    public Vector2i getSpawnPosition() {
        return spawnPosition;
    }

    public void setSpawnPosition(Vector2i spawnPosition) {
        this.spawnPosition = spawnPosition;
    }

    public void setSpawnPosition(int x, int y) {
        setSpawnX(x);
        setSpawnY(y);
    }

    public void setSpawnPosition(Point point) {
        setSpawnX(point.x);
        setSpawnY(point.y);
    }

    public int getSpawnX() {
        return spawnPosition.x;
    }

    public void setSpawnX(int spawnX) {
        this.spawnPosition.x = spawnX;
    }

    public int getSpawnY() {
        return spawnPosition.y;
    }

    public void setSpawnY(int spawnY) {
        this.spawnPosition.y = spawnY;
    }

    public Dimension getSize() {
        return size;
    }

    public void setSize(Dimension size) {
        this.size = size;
    }

    public void setSize(int width, int height) {
        size = new Dimension(width, height);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Handler getHandler() {
        return handler;
    }
}
