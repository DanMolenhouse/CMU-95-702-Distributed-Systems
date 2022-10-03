<%@ page import="java.util.Objects" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
<!-- Basic HTML setup -->
<head>
    <title>Project1 Task3: Survey</title>
</head>
<h1><b>Distributed Systems Class Clicker</b></h1>
<h4><b>Created by Dan Molenhouse</b></h4>
<br>

<!-- Prints the results based on what results exist -->
<body>
<h4><%=request.getAttribute("results")%></h4><br>

<!-- If statements below only execute if that particular variable exists -->
<%if(request.getAttribute("resA")!=null){%>
<h4><%=request.getAttribute("resA")%></h4>
<%}%>
<%if(request.getAttribute("resB")!=null){%>
<h4><%=request.getAttribute("resB")%></h4>
<%}%>
<%if(request.getAttribute("resC")!=null){%>
<h4><%=request.getAttribute("resC")%></h4>
<%}%>
<%if(request.getAttribute("resD")!=null){%>
<h4><%=request.getAttribute("resD")%></h4>
<%}%>
</body>
</html>