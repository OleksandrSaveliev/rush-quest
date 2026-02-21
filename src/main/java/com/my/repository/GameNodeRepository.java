package com.my.repository;

import com.my.model.nodes.GameNode;

public interface GameNodeRepository {
     GameNode getNodeByKey(String key);
}
