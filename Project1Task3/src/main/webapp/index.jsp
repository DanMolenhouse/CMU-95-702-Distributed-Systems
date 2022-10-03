<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
<!-- Basic HTML setup -->
<head>
    <title>Project1 Task3: Survey</title>
</head>
<h1><b>Distributed Systems Class Clicker</b></h1>
<body>
<h4><b>Created by Dan Molenhouse</b></h4>

<!-- This prints the response to the last answer, if it exists -->
<%if(request.getAttribute("lastAnswer")!=null){%>
<h4><%=request.getAttribute("lastAnswer")%></h4>
<%}%>

<body>
<!-- Below is just the radio buttons for the four options, and a submit button -->
    <form action="clickerServlet" method="get">
        <fieldset>
            Please select answer to the next question:
            <br>
            <input type="radio" name="answer" value="a" checked>a
            <br>
            <input type="radio" name="answer" value="b">b
            <br>
            <input type="radio" name="answer" value="c">c
            <br>
            <input type="radio" name="answer" value="d">d
            <br>
            <input type="submit" value="submit">
        </fieldset>
    </form>

</body>
</html>