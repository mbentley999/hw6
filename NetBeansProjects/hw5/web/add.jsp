<%-- 
    Document   : add
    Created on : Oct 10, 2017, 1:36:08 PM
    Author     : mattbentley
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Videogame</title>
        <link rel = 'stylesheet' type = 'text/css' href = 'hw5.css'/>
    </head>
    <body>
        <h1>Add A New Videogame</h1>
        
        <form name="addForm" action="addFriend" method="get">
            <label>Videogame Name:</label>
            <input type="text" name="name" value="" /><br>
            
            <label>Years Old:</label>
            <input type="text" name="yearsold" value="" /><br>
            
            <label>Gametype:</label>
            <input type="text" name="gametype" value="" /><br>
            
            <label>Rating:</label>
            <input type="text" name="rating" value="" /><br>
            
            <input type="reset" value="Clear" id="Clear">
            <input type="submit" name="submit" value="Submit">
            
        </form>
    </body>
</html>
