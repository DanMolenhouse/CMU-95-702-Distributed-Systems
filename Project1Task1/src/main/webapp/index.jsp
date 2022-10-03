<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
<head>
    <title>Project1 Task1: Compute Hashes</title>
</head>

<!-- Below is a simple text input / radio button selector for user to enter desired hash type-->
<body>
    <form action="ComputeHashes" method="get">
        <fieldset>
            <legend>Project 1 Task 1:</legend>

            <!-- Text box -->
            Please enter string to be hashed:
            <input type="text" name="hashWord" required/><br>

            <!-- Radio buttons for hash types -->
            Please select hash function (MD5 or SHA-256):
            <input type="radio" name="hashType" value="MD5" checked>
            MD5
            <input type="radio" name="hashType" value="SHA-256">
            SHA-256<br>

            <!-- submit button sends get request -->
            <input type="submit" value="submit">
        </fieldset>
    </form>



</body>
</html>