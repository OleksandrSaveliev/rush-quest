package com.my.service;

import com.my.model.GameState;
import com.my.model.GameStatus;
import com.my.model.nodes.GameNode;
import com.my.repository.GameNodeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameServiceTest {

    private GameNodeRepository repository;
    private GameService gameService;

    @BeforeEach
    void setUp() {
        repository = mock(GameNodeRepository.class);
        gameService = new GameService(repository);
    }

    @Test
    void startGame_shouldReturnInitialState() {
        GameNode startNode = mock(GameNode.class);
        when(repository.getNodeByKey("forestStart")).thenReturn(startNode);

        GameState state = gameService.startGame();

        assertEquals(startNode, state.getCurrentNode());
        assertEquals(GameStatus.IN_PROGRESS, state.getStatus());
    }

    @Test
    void next_shouldMoveToNextNode() {
        GameNode currentNode = mock(GameNode.class);
        GameNode nextNode = mock(GameNode.class);

        GameState state = new GameState(currentNode);

        when(state.getOptions()).thenReturn(List.of("go"));
        when(currentNode.nextNodeKey("go")).thenReturn("nextNode");
        when(repository.getNodeByKey("nextNode")).thenReturn(nextNode);

        GameState updated = gameService.next(state, "go");

        assertEquals(nextNode, updated.getCurrentNode());
        assertEquals(GameStatus.IN_PROGRESS, updated.getStatus());
    }

    @Test
    void next_shouldSetStatusWon() {
        GameNode currentNode = mock(GameNode.class);
        GameNode winNode = mock(GameNode.class);

        GameState state = new GameState(currentNode);

        when(state.getOptions()).thenReturn(List.of("go"));
        when(currentNode.nextNodeKey("go")).thenReturn("win");
        when(repository.getNodeByKey("win")).thenReturn(winNode);

        GameState updated = gameService.next(state, "go");

        assertEquals(GameStatus.WON, updated.getStatus());
    }

    @Test
    void next_shouldSetStatusLost() {
        GameNode currentNode = mock(GameNode.class);
        GameNode loseNode = mock(GameNode.class);

        GameState state = new GameState(currentNode);

        when(state.getOptions()).thenReturn(List.of("go"));
        when(currentNode.nextNodeKey("go")).thenReturn("lose");
        when(repository.getNodeByKey("lose")).thenReturn(loseNode);

        GameState updated = gameService.next(state, "go");

        assertEquals(GameStatus.LOST, updated.getStatus());
    }
}