package com.my.model.nodes;

import com.my.model.GameState;

import java.util.List;

public interface GameNode {
    String getStory();

    List<String> getOptions();

    String nextNodeKey(String decision);

    GameState toGameState();
}
