package com.my.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.my.model.nodes.GameNode;
import com.my.model.nodes.JsonGameNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class GameNodeJsonRepository implements GameNodeRepository{

    private final Map<String, GameNode> nodes = new HashMap<>();

    public GameNodeJsonRepository() {
        loadNodes();
    }

    private void loadNodes() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = getClass().getClassLoader().getResourceAsStream("story.json");

            Map<String, JsonGameNode> storyMap = mapper.readValue(is, new TypeReference<>() {});

            nodes.putAll(storyMap);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load story.json", e);
        }
    }

    @Override
    public GameNode getNodeByKey(String key) {
        GameNode node = nodes.get(key);
        if (node == null) {
            throw new IllegalArgumentException("Unknown node: " + key);
        }
        return node;
    }
}