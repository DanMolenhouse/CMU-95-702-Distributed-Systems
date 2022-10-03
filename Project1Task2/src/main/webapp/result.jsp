<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
<!-- Basic HTML header -->
<head>
    <title>Project1 Task1: Hashed String Results</title>
</head>
<body>
<!-- Below are simple HTML request/getparameter statements for each attribute -->
    <h1><b>Dog: <%=request.getParameter("dogBreed")%></b></h1>
    <h2><b>Friendly: <%=request.getAttribute("friend")%> Stars</b></h2>
    <h2><b>Intelligence: <%=request.getAttribute("intel")%> Stars</b></h2>
    <h2><b>Height: <%=request.getAttribute("height")%> </b></h2>
    <h2><b>Weight: <%=request.getAttribute("weight")%></b></h2>
    <h2><b>Lifespan: <%=request.getAttribute("lifespan")%></b></h2>
    <h4><b>Credit: https://dogtime.com/dog-breeds/profile</b></h4>
    <br>

    <!-- This section prints the image form the fetched URL -->
    <img src="<%= request.getAttribute("pictureURL")%>"><br><br>
    <h4><b>Credit: https://dog.ceo/dog-api/</b></h4>

    <!-- Continue button to return to index -->
    <form action="dogFinderServlet" method="get">
        <input type="submit" value="continue">
    </form>
</body>
</html>

