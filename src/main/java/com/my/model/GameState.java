package com.my.model;

import com.my.model.nodes.GameNode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class GameState {

    private GameNode currentNode;
    private GameStatus status;

    public GameState(GameNode currentNode) {
        this.currentNode = currentNode;
        this.status = GameStatus.IN_PROGRESS;
    }

    public boolean isGameOver() {
        return status != GameStatus.IN_PROGRESS;
    }

    public List<String> getOptions() {
        return currentNode.getOptions();
    }
}