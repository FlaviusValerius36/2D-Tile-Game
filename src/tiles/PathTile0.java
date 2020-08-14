package tiles;

import textures.Assets;

public class PathTile0 extends Tile {


    public PathTile0(int id) {
        super(Assets.tile_path0, id, false);
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
