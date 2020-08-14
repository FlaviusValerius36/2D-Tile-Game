package entities;

import inputs.GameAction;

public interface Attributes {

    int STATE_NORMAL = 0;
    int STATE_IDLE = 1;
    int STATE_DYING = 2;
    int STATE_DEAD = 3;
    int STATE_JUMPING = 4;
    int DIE_TIME = 1000;
    int DEFAULT_ENTITY_WIDTH = 64;
    int DEFAULT_ENTITY_HEIGHT = 64;
    int UP = 3;
    int DOWN = 2;
    int LEFT = 1;
    int RIGHT = 0;
    int DEFAULT_HEALTH = 15;
    float DEFAULT_SPEED = 0.8f;
    float MAX_SPEED = 5.05f;
    float ACCELERATION = 2.255f;
    float DECELERATION = 1.15f;
    float RUNNING_SPEED = MAX_SPEED * DECELERATION;

    GameAction w_up = new GameAction("UP", GameAction.NORMAL);
    GameAction s_down = new GameAction("DOWN", GameAction.NORMAL);
    GameAction a_left = new GameAction("LEFT", GameAction.NORMAL);
    GameAction d_right = new GameAction("RIGHT", GameAction.NORMAL);
    GameAction dir_up = new GameAction("UP", GameAction.NORMAL);
    GameAction dir_down = new GameAction("DOWN", GameAction.NORMAL);
    GameAction dir_left = new GameAction("LEFT", GameAction.NORMAL);
    GameAction dir_right = new GameAction("RIGHT", GameAction.NORMAL);

    void setStats(float health, float defense, float attack);
}
