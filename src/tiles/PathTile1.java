package tiles;

import textures.Assets;

public class PathTile1 extends Tile {


    public PathTile1(int id) {
        super(Assets.tile_path1, id, false);
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
