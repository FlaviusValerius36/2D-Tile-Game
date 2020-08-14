package states;

import utils.GameLogic;
import utils.Handler;

public abstract class State implements GameLogic {

    protected final int id;
    protected final StateManager state;
    protected final Handler handler;
    protected boolean active = false;

    public State(Handler handler, StateManager state, final int id) {
        this.id = id;
        this.state = state;
        this.handler = handler;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public StateManager getState() {
        return state;
    }

    public Handler getHandler() {
        return handler;
    }

    public int getId() {
        return id;
    }
}
