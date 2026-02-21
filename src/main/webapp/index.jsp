<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Rush Quest</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>

<section class="hero">
    <div class="overlay">
        <h1 class="title">RUSH QUEST</h1>

        <p class="subtitle">
            A text-based adventure where every decision shapes your destiny.
            Enter the world of danger, mystery, and unexpected outcomes.
        </p>

        <a href="${pageContext.request.contextPath}/game" class="start-btn">
            Start Game
        </a>
    </div>
</section>

</body>
</html>