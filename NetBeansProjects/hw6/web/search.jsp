<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
        <link rel = 'stylesheet' type = 'text/css' href = 'hw6.css'/>
    </head>
    <body>
        <h1>Search A Videogame</h1>
        
        <div class="addpage">
        <form name="searchForm" action="search" method="get">
            
            <input type="text" name="searchVal" value=""/><br>
            <input type="submit" name="submit" value="Search"/>
        </div>    
        </form>
    </body>
</html>
