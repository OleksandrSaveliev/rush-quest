package com.my.model.nodes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonGameNode implements GameNode {

    private String story;
    private List<String> options;
    private Map<String, String> transitions;

    @Override
    public String getStory() {
        return story;
    }

    @Override
    public List<String> getOptions() {
        return options;
    }

    @Override
    public String nextNodeKey(String decision) {
        return transitions.getOrDefault(decision, "lose");
    }

}
