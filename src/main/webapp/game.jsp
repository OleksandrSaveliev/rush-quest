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

    <div class="story-box">
        <p>
            You stand at the edge of a dark forest.
            The wind whispers through the trees.
            A narrow path splits into three directions.
            Your adventure begins now...
        </p>
    </div>

    <form action="${pageContext.request.contextPath}/game" method="post" class="choices-form">

        <h2>What will you do?</h2>

        <label class="choice">
            <input type="radio" name="decision" value="left" required>
            Take the left path toward the river
        </label>

        <label class="choice">
            <input type="radio" name="decision" value="right">
            Follow the right trail into the shadows
        </label>

        <label class="choice">
            <input type="radio" name="decision" value="forward">
            Move forward into the forest
        </label>

        <button type="submit" class="action-btn">
            Continue
        </button>

    </form>

</div>

</body>
</html>
