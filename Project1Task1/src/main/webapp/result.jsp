<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
<head>
    <title>Project1 Task1: Hashed String Results</title>
</head>

<body>
    <fieldset>
        <!-- Below prints the results of the user-inputted hash words -->
        <h1>Here is <%= request.getParameter("hashWord")%> hashed using <%= request.getParameter("hashType")%> </h1><br>
        <p> Hash Value (base64): <%=request.getAttribute("hash64")%></p>
        <p> Hash Value (Hexadecimal):<%=request.getAttribute("hashHex")%></p><br>
    </fieldset>
    <br>

    <!-- Below is the prompt for another user-inputted hash request-->
<form action="ComputeHashes" method="get">
    <fieldset>
        <legend>Hash another string:</legend>

        <!-- Text box for user to enter word -->
        Please enter text to be hashed:
        <input type="text" name="hashWord" required/><br>

        <!-- radio buttons for hash selection -->
        Please choose hash function:
        <input type="radio" name="hashType" value="MD5" checked>
        MD5
        <input type="radio" name="hashType" value="SHA-256">
        SHA-256<br>
        <input type="submit" value="submit">
    </fieldset>
</form>
</body>
</html>

