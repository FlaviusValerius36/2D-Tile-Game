package entities;

import tiles.Tile;
import utils.Handler;

public abstract class Creature extends BaseEntity implements Attributes {

    protected float health;
    protected float speed;
    protected float jump;
    protected float run;
    protected float defensiveStrength;
    protected float attackPower;
    protected int state;
    protected long stateTime;
    protected boolean collided;

    public Creature(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        this.health = DEFAULT_HEALTH;
        this.speed = DEFAULT_SPEED;
        this.run = RUNNING_SPEED;
        this.state = STATE_NORMAL;
    }

    @Override
    public void setStats(float health, float defense, float attack) {
        this.health = health;
        this.defensiveStrength = defensiveStrength;
        this.attackPower = attackPower;
    }

    public void updatePosition() {
        if (velX > 0) { // Moving right
            int tx = (int) (x + velX + bounds.x + bounds.width) / Tile.TILE_SIZE;

            if (!hasCollisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_SIZE)
                    && !hasCollisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_SIZE)) {
                x += velX;
            } else {
                x = tx * Tile.TILE_SIZE - bounds.x - bounds.width - 1;
            }
        } else if (velX < 0) { // Moving left
            int tx = (int) (x + velX + bounds.x) / Tile.TILE_SIZE;

            if (!hasCollisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_SIZE)
                    && !hasCollisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_SIZE)) {
                x += velX;
            } else {
                x = tx * Tile.TILE_SIZE + Tile.TILE_SIZE - bounds.x;
            }
        }

        if (velY < 0) { // Moving up
            int ty = (int) (y + velY + bounds.y) / Tile.TILE_SIZE;

            if (!hasCollisionWithTile((int) (x + bounds.x) / Tile.TILE_SIZE, ty)
                    && !hasCollisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_SIZE, ty)) {
                y += velY;
            } else {
                y = ty * Tile.TILE_SIZE + Tile.TILE_SIZE - bounds.y;
            }
        } else if (velY > 0) { // Moving down
            int ty = (int) (y + velY + bounds.y + bounds.height) / Tile.TILE_SIZE;

            if (!hasCollisionWithTile((int) (x + bounds.x) / Tile.TILE_SIZE, ty)
                    && !hasCollisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_SIZE, ty)) {
                y += velY;
            } else {
                y = ty * Tile.TILE_SIZE - bounds.y - bounds.height - 1;
            }
        }
    }

    /**
     Wakes up the creature when the Creature first appears
     on screen. Normally, the creature starts moving left.
     */
    public void wakeUp() {
        if (state == STATE_NORMAL && velX == 0) {
            velX = -MAX_SPEED;
        }
    }

    protected boolean hasCollisionWithTile(int x, int y) {
        return handler.getWorld().getTile(x, y).isSolid();
    }

    /**
     Gets the state of this Creature. The state is either
     STATE_NORMAL, STATE_DYING, or STATE_DEAD.
     */
    public int getState() {
        return state;
    }

    /**
     Sets the state of this Creature to STATE_NORMAL,
     STATE_DYING, or STATE_DEAD.
     */
    public void setState(int state) {
        if (this.state != state) {
            this.state = state;
            stateTime = 0;
            if (state == STATE_DYING) {
                velX = 0;
                velY = 0;
            }
        }
    }

    public void updateDeadState(long elapsedTime) {
        // update to "dead" state
        stateTime += elapsedTime;
        if (state == STATE_DYING && stateTime >= DIE_TIME) {
            setState(STATE_DEAD);
        }
    }

    /**
     Called before update() if the creature collided with a
     tile horizontally.
     */
    public void collideHorizontal() {
        setVelX(-getVelX());
    }


    /**
     Called before update() if the creature collided with a
     tile vertically.
     */
    public void collideVertical() {
        setVelY(0);
    }


    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getJump() {
        return jump;
    }

    public void setJump(float jump) {
        this.jump = jump;
    }

    public float getRun() {
        return run;
    }

    public void setRun(float run) {
        this.run = run;
    }

    public float getDefensiveStrength() {
        return defensiveStrength;
    }

    public void setDefensiveStrength(float defensiveStrength) {
        this.defensiveStrength = defensiveStrength;
    }

    public float getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(float attackPower) {
        this.attackPower = attackPower;
    }

    public boolean isCollided() {
        return collided;
    }

    public void setCollided(boolean collided) {
        this.collided = collided;
    }
}
