<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
<!-- Basic HTML header -->
<head>
    <title>Project1 Task2: Dog Finder</title>
</head>
<h1><b>Dog Finder</b></h1>

<!-- Basic HTML text -->
<body>
<h4><b>Created by Dan Molenhouse</b></h4>

<!-- Below is the drop down menu and submit button for user input to select a dog breed -->
<form action="dogFinderServlet" method="get">
    <fieldset><legend><b>Dog Breeds:</b></legend>
    <label for="dogBreed">Choose a dog breed:</label>
    <select name="dogBreed" id="dogBreed">
        <option value="Borzoi">Borzoi</option>
        <option value="Boxer">Boxer</option>
        <option value="Chihuahua">Chihuahua</option>
        <option value="Collie">Collie</option>
        <option value="Dachshund">Dachshund</option>
        <option value="Dalmatian">Dalmatian</option>
        <option value="Maltese">Maltese</option>
        <option value="Otterhound">Otterhound</option>
        <option value="Poodle">Poodle</option>
        <option value="Rottweiler">Rottweiler</option>
        <option value="Saluki">Saluki</option>
        <option value="Whippet">Whippet</option>
    </select>
        <input type="submit" value="submit">
    </fieldset>
</form>

</body>
</html>