package tiles;

import textures.Assets;

public class PathTile2 extends Tile {


    public PathTile2(int id) {
        super(Assets.tile_path2, id, false);
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
