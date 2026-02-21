package com.my.model;

import com.my.model.nodes.GameNode;

public class GameState {

    private GameNode currentNode;
    private GameStatus status;

    public GameState(GameNode currentNode) {
        this.currentNode = currentNode;
        this.status = GameStatus.IN_PROGRESS; // default
    }

    public GameNode getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(GameNode currentNode) {
        this.currentNode = currentNode;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public boolean isGameOver() {
        return status != GameStatus.IN_PROGRESS;
    }

    public String getStory() {
        return currentNode.getStory();
    }

    public java.util.List<String> getOptions() {
        return currentNode.getOptions();
    }
}