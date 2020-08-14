/**
 * 
 */
package tiles;

import math3D.vectors.Vector2f;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Ian Fannon
 *
 */
public class Tile {

	public static final int TILE_SIZE = 64;
	public static final Tile[] tiles = new Tile[16];

	public static final Tile grass0 = new GrassTile0(0);
	public static final Tile grass1 = new GrassTile1(1);
	public static final Tile grass2 = new GrassTile2(2);
	public static final Tile brick0 = new BrickTile0(3);
	public static final Tile brick1 = new BrickTile1(4);
	public static final Tile brick2 = new BrickTile2(5);
	public static final Tile path0 = new PathTile0(6);
	public static final Tile path1 = new PathTile1(7);
	public static final Tile path2 = new PathTile2(8);
	public static final Tile dirt = new DirtTile(9);
	public static final Tile sand = new SandTile(10);
	public static final Tile flower = new FlowerTile(11);
	public static final Tile water = new WaterTile(12);
	public static final Tile mud = new MudTile(13);
	public static final Tile stone0 = new StoneTile0(14);
	public static final Tile stone1 = new StoneTile1(15);

	protected final BufferedImage texture;
	protected final int id;
	protected boolean solid = false;
	protected Vector2f position;

	public Tile(BufferedImage texture, int id, boolean solid) {
		this.texture = texture;
		this.id = id;
		this.solid = solid;
		position = new Vector2f();
		tiles[id] = this;
	}

	public void render(Graphics2D g2d, int x, int y) {
		setPosition(new Vector2f(x, y));
		g2d.drawImage(texture, x, y, Tile.TILE_SIZE, Tile.TILE_SIZE, null);
	}

	public boolean isSolid() {
		return solid;
	}

	public void setSolid(boolean solid) {
		this.solid = solid;
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public int getId() {
		return id;
	}

	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}
}
