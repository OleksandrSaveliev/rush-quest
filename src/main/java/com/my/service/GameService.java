package com.my.service;

import com.my.model.GameState;
import com.my.model.GameStatus;
import com.my.model.nodes.GameNode;
import com.my.repository.GameNodeJsonRepository;
import com.my.repository.GameNodeRepository;

public class GameService {

    private final GameNodeRepository repository = new GameNodeJsonRepository();

    public GameState startGame() {
        GameNode startNode = repository.getNodeByKey("forestStart");
        return new GameState(startNode);
    }

    public GameState next(GameState current, String decision) {
        GameNode currentNode = current.getCurrentNode();

        // Only move if there are options
        if (!current.getOptions().isEmpty()) {
            String nextKey = currentNode.nextNodeKey(decision);
            GameNode nextNode = repository.getNodeByKey(nextKey);
            current.setCurrentNode(nextNode);

            // Update status based on next node key
            switch (nextKey.toLowerCase()) {
                case "win" -> current.setStatus(GameStatus.WON);
                case "lose" -> current.setStatus(GameStatus.LOST);
                default -> current.setStatus(GameStatus.IN_PROGRESS);
            }
        }

        return current;
    }
}