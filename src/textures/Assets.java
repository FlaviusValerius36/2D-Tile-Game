package textures;

import sounds.AudioPlayer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {

    private static final int TILE_SIZE = 64;
    public static AudioPlayer startMusic, playMusic;
    // UI Objects
    public static BufferedImage menuScreen, inventoryScreen, startBtn, optionsBtn, celticDragon;
    // Tiles
    public static BufferedImage tile_brick0, tile_brick1, tile_brick2, tile_dirt, tile_grass0, tile_grass1, tile_grass2, tile_water;
    public static BufferedImage tile_flowers, tile_mud, tile_path0, tile_path1, tile_path2, tile_sand, tile_stone0, tile_stone1;
    // Players
    public static BufferedImage[] playerIdle, playerWalking;
    public static BufferedImage[] vladWalking_up, vladWalking_down, vladWalking_left, vladWalking_right;
    public static BufferedImage dwarfSheet, linkSheet;
    // Cursors
    public static BufferedImage[] circleCursor;
    // Objects
    public static BufferedImage barrel0, barrel1, boulder, campFire0, campFire1, deadTree0, deadTree1, rock, sign, stump0, tree0, tree1;
    public static BufferedImage rockObj1, rockObj2, rockObj3, treeObj, rockSheet, itemSheet;
    public static BufferedImage heart;
    public static Image player;

    public static void initTiles() {
        tile_brick0 = new Texture("resources/textures/tiles/brick0.png").getTexture();
        tile_brick1 = new Texture("resources/textures/tiles/brick1.png").getTexture();
        tile_brick2 = new Texture("resources/textures/tiles/brick2.png").getTexture();
        tile_dirt = new Texture("resources/textures/tiles/dirt.png").getTexture();
        tile_grass0 = new Texture("resources/textures/tiles/grass0.png").getTexture();
        tile_grass1 = new Texture("resources/textures/tiles/grass1.png").getTexture();
        tile_grass2 = new Texture("resources/textures/tiles/grass2.png").getTexture();
        tile_flowers = new Texture("resources/textures/tiles/grassFlowers.png").getTexture();
        tile_mud = new Texture("resources/textures/tiles/mud.png").getTexture();
        tile_path0 = new Texture("resources/textures/tiles/path0.png").getTexture();
        tile_path1 = new Texture("resources/textures/tiles/path1.png").getTexture();
        tile_path2 = new Texture("resources/textures/tiles/path2.png").getTexture();
        tile_sand = new Texture("resources/textures/tiles/sand.png").getTexture();
        tile_stone0 = new Texture("resources/textures/tiles/stone0.png").getTexture();
        tile_stone1 = new Texture("resources/textures/tiles/stone1.png").getTexture();
        tile_water = new Texture("resources/textures/tiles/waterTile.png").getTexture();
    }

    public static void initEntities() {
        playerIdle = new BufferedImage[]{
                new Texture("resources/textures/player/idle/_0.png").getTexture(),
                new Texture("resources/textures/player/idle/_1.png").getTexture(),
                new Texture("resources/textures/player/idle/_2.png").getTexture(),
                new Texture("resources/textures/player/idle/_3.png").getTexture()
        };
        playerWalking = new BufferedImage[]{
                new Texture("resources/textures/player/walking/_0.png").getTexture(),
                new Texture("resources/textures/player/walking/_1.png").getTexture(),
                new Texture("resources/textures/player/walking/_2.png").getTexture(),
                new Texture("resources/textures/player/walking/_3.png").getTexture()
        };
        dwarfSheet = new Texture("resources/textures/player/dwarfSpriteSheet.png").getTexture();
        vladWalking_right = new BufferedImage[]{
                // Walking East
                new Texture("resources/textures/player/vlad/walking e0000.bmp").getTexture(),
                new Texture("resources/textures/player/vlad/walking e0001.bmp").getTexture(),
                new Texture("resources/textures/player/vlad/walking e0002.bmp").getTexture(),
                new Texture("resources/textures/player/vlad/walking e0003.bmp").getTexture(),
                new Texture("resources/textures/player/vlad/walking e0004.bmp").getTexture(),
                new Texture("resources/textures/player/vlad/walking e0005.bmp").getTexture(),
                new Texture("resources/textures/player/vlad/walking e0006.bmp").getTexture(),
                new Texture("resources/textures/player/vlad/walking e0007.bmp").getTexture()};
                // Walking West
        vladWalking_left = new BufferedImage[]{
                new Texture("resources/textures/player/vlad/walking w0000.bmp").getTexture(),
                new Texture("resources/textures/player/vlad/walking w0001.bmp").getTexture(),
                new Texture("resources/textures/player/vlad/walking w0002.bmp").getTexture(),
                new Texture("resources/textures/player/vlad/walking w0003.bmp").getTexture(),
                new Texture("resources/textures/player/vlad/walking w0004.bmp").getTexture(),
                new Texture("resources/textures/player/vlad/walking w0005.bmp").getTexture(),
                new Texture("resources/textures/player/vlad/walking w0006.bmp").getTexture(),
                new Texture("resources/textures/player/vlad/walking w0007.bmp").getTexture()};
                // Walking South
        vladWalking_down = new BufferedImage[]{
                new Texture("resources/textures/player/vlad/walking s0000.bmp").getTexture(),
                new Texture("resources/textures/player/vlad/walking s0001.bmp").getTexture(),
                new Texture("resources/textures/player/vlad/walking s0002.bmp").getTexture(),
                new Texture("resources/textures/player/vlad/walking s0003.bmp").getTexture(),
                new Texture("resources/textures/player/vlad/walking s0004.bmp").getTexture(),
                new Texture("resources/textures/player/vlad/walking s0005.bmp").getTexture(),
                new Texture("resources/textures/player/vlad/walking s0006.bmp").getTexture(),
                new Texture("resources/textures/player/vlad/walking s0007.bmp").getTexture()};
                // Walking North
        vladWalking_up = new BufferedImage[]{
                new Texture("resources/textures/player/vlad/walking n0000.bmp").getTexture(),
                new Texture("resources/textures/player/vlad/walking n0001.bmp").getTexture(),
                new Texture("resources/textures/player/vlad/walking n0002.bmp").getTexture(),
                new Texture("resources/textures/player/vlad/walking n0003.bmp").getTexture(),
                new Texture("resources/textures/player/vlad/walking n0004.bmp").getTexture(),
                new Texture("resources/textures/player/vlad/walking n0005.bmp").getTexture(),
                new Texture("resources/textures/player/vlad/walking n0006.bmp").getTexture(),
                new Texture("resources/textures/player/vlad/walking n0007.bmp").getTexture()};
        linkSheet = new Texture("resources/textures/player/linkFormatted.png").getTexture();
        player = new Texture("resources/textures/player/dinoSheet.png").getTexture().getSubimage(24, 110, 30, 54).
                getScaledInstance(90, 162, BufferedImage.SCALE_SMOOTH);
    }

    public static void initUIObjects() {
        menuScreen = new Texture("resources/textures/menu.png").getTexture();
        inventoryScreen = new Texture("resources/textures/inventoryScreen.png").getTexture();
        startBtn = new Texture("resources/textures/playBtn.png").getTexture();
        optionsBtn = new Texture("resources/textures/optionsBtn.png").getTexture();
        celticDragon = new Texture("resources/textures/Red_Woods_Dragon_3.png").getTexture();
    }

    public static void initObjects() {
        barrel0 = new Texture("resources/textures/objects/barrel0.png").getTexture();
        barrel1 = new Texture("resources/textures/objects/barrel1.png").getTexture();
        boulder = new Texture("resources/textures/objects/boulder.png").getTexture();
        campFire0 = new Texture("resources/textures/objects/campFire0.png").getTexture();
        campFire1 = new Texture("resources/textures/objects/campFire0.png").getTexture();
        deadTree0 = new Texture("resources/textures/objects/deadTree0.png").getTexture();
        deadTree1 = new Texture("resources/textures/objects/deadTree1.png").getTexture();
        rock = new Texture("resources/textures/objects/rock.png").getTexture();
        sign = new Texture("resources/textures/objects/sign.png").getTexture();
        stump0 = new Texture("resources/textures/objects/stump0.png").getTexture();
        tree0 = new Texture("resources/textures/objects/tree0.png").getTexture();
        tree1 = new Texture("resources/textures/objects/tree1.png").getTexture();
        rockObj1 = new Texture("resources/textures/objects/rock0.png").getTexture();
        rockObj2 = new Texture("resources/textures/objects/rock1.png").getTexture();
        rockObj3 = new Texture("resources/textures/objects/rock2.png").getTexture();
        treeObj = new Texture("resources/textures/objects/green trees.png").getTexture();
    }

    public static void initItems() {
        int tileSize = 25;
        itemSheet = new Texture("resources/textures/itemSheet.png").getTexture();
        heart = itemSheet.getSubimage(tileSize * 8, 0, tileSize, tileSize);
    }

    public static void initCursor() {
        circleCursor = new BufferedImage[]{
                new Texture("resources/cursor/an_0.png").getTexture(),
                new Texture("resources/cursor/an_1.png").getTexture(),
                new Texture("resources/cursor/an_2.png").getTexture(),
                new Texture("resources/cursor/an_3.png").getTexture()
        };
    }

    public static void initAudio() {
        startMusic = new AudioPlayer("resources/sounds/01_-_Dragon_Warrior_-_NES_-_Overture_March.wav");
        playMusic = new AudioPlayer("resources/sounds/02_-_Dragon_Warrior_Chateau_Ladutorm.wav");
    }
}
