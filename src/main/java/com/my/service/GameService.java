package com.my.service;

import com.my.model.GameState;
import com.my.model.GameStatus;
import com.my.model.nodes.GameNode;
import com.my.repository.GameNodeJsonRepository;
import com.my.repository.GameNodeRepository;

public class GameService {

    private final GameNodeRepository repository;

    public GameService(GameNodeRepository repository) {
        this.repository = repository;
    }

    public GameState startGame() {
        GameNode startNode = repository.getNodeByKey("forestStart");
        return new GameState(startNode);
    }

    public GameState next(GameState currentState, String decision) {
        GameNode currentNode = currentState.getCurrentNode();

        if (!currentState.getOptions().isEmpty()) {
            String nextKey = currentNode.nextNodeKey(decision);
            GameNode nextNode = repository.getNodeByKey(nextKey);
            currentState.setCurrentNode(nextNode);

            setGameStatus(currentState, nextKey);
        }

        return currentState;
    }

    private void setGameStatus(GameState current, String nextKey) {
        switch (nextKey.toLowerCase()) {
            case "win" -> current.setStatus(GameStatus.WON);
            case "lose" -> current.setStatus(GameStatus.LOST);
            default -> current.setStatus(GameStatus.IN_PROGRESS);
        }
    }
}