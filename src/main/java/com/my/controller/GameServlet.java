package com.my.controller;

import com.my.model.GameState;
import com.my.model.nodes.GameNode;
import com.my.repository.GameNodeJsonRepository;
import com.my.repository.GameNodeRepository;
import com.my.service.GameService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/game")
public class GameServlet extends HttpServlet {

    private final GameNodeRepository nodeRepository = new GameNodeJsonRepository();
    private final GameService gameService = new GameService(nodeRepository);

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        GameState state = gameService.startGame();
        request.getSession().setAttribute("state", state);

        forwardToJsp(request, response, state);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        GameState currentState = (GameState) session.getAttribute("state");
        String decision = request.getParameter("decision");

        if (currentState == null || currentState.isGameOver()) {
            currentState = gameService.startGame();
        } else {
            currentState = gameService.next(currentState, decision);
        }

        session.setAttribute("state", currentState);

        forwardToJsp(request, response, currentState);
    }

    private void forwardToJsp(HttpServletRequest request,
                              HttpServletResponse response,
                              GameState state)
            throws ServletException, IOException {

        GameNode node = state.getCurrentNode();

        request.setAttribute("story", node.getStory());
        request.setAttribute("status", state.getStatus().name());
        request.setAttribute("options", node.getOptions());
        request.setAttribute("isGameOver", state.isGameOver());

        request.getRequestDispatcher("/game.jsp")
                .forward(request, response);
    }
}