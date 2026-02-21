<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Rush Quest - Adventure</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/game.css">
</head>
<body>

<div class="game-container">

    <h1 class="game-title">Rush Quest</h1>

    <!-- Dynamic Story -->
    <div class="story-box">
        <p>
            <c:choose>
                <c:when test="${not empty story}">
                    ${story}
                </c:when>
                <c:otherwise>
                    Welcome to Rush Quest! Your adventure begins now.
                </c:otherwise>
            </c:choose>
        </p>
    </div>

    <!-- Check if game is over -->
    <c:choose>
        <c:when test="${status == 'WON'}">
            <h2 class="game-result">🎉 You Won! Congratulations!</h2>
            <a href="${pageContext.request.contextPath}/game" class="action-btn">Play Again</a>
        </c:when>

        <c:when test="${status == 'LOST'}">
            <h2 class="game-result">💀 You Lost! Try again.</h2>
            <a href="${pageContext.request.contextPath}/game" class="action-btn">Restart</a>
        </c:when>

        <c:otherwise>
            <!-- Game in progress: show choices -->
            <form action="${pageContext.request.contextPath}/game" method="post" class="choices-form">

                <h2>What will you do?</h2>

                <c:forEach var="option" items="${options}">
                    <label class="choice">
                        <input type="radio" name="decision" value="${option}" required>
                        ${option}
                    </label>
                </c:forEach>

                <button type="submit" class="action-btn">
                    Continue
                </button>
            </form>
        </c:otherwise>
    </c:choose>

</div>

</body>
</html>