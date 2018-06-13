package models;

import java.util.Observable;

public class PlayerFound extends Observable {
    private boolean playerFound = false;

    public boolean isPlayerFound() {
        return playerFound;
    }

    public void setPlayerFound(boolean playerFound) {
        this.playerFound = playerFound;
        setChanged();
        notifyObservers();
    }




}
