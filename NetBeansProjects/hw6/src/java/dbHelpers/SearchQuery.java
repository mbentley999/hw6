package dbHelpers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Videogames;


public class SearchQuery {
    
    private Connection conn;
    private ResultSet results;
    
    public SearchQuery(){
        Properties props = new Properties();
        InputStream instr = getClass().getResourceAsStream("dbConnection.properties");
        try {
            props.load(instr);
        } catch (IOException ex) {
            Logger.getLogger(SearchQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            instr.close();
        } catch (IOException ex) {
            Logger.getLogger(SearchQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String driver = props.getProperty("driver.name");
        String url = props.getProperty("server.name");
        String username = props.getProperty("user.name");
        String passwd = props.getProperty("user.password");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SearchQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn = DriverManager.getConnection(url, username, passwd);
        } catch (SQLException ex) {
            Logger.getLogger(SearchQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } // end of method
    
    public void doSearch(String dog){
        try {
            String query = "Select * from video_games WHERE UPPER(v_name) LIKE ? OR UPPER(game_type) LIKE ? OR rating LIKE ? or years_old LIKE ? ORDER BY v_id ASC";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, "%" + dog.toUpperCase() + "%");
            ps.setString(2, "%" + dog.toUpperCase() + "%");
            ps.setString(3, "%" + dog + "%");
            ps.setString(4, "%" + dog + "%");
            this.results = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getHtmlTable(){
        
        String table = "";
        
        table += "<table>";
        
        table += "<tr>";
            table += "<th>Videogame ID</th>";
            table += "<th>Name</th>";
            table += "<th>Years Old</th>";
            table += "<th>Gametype</th>";
            table += "<th>Personal Rating</th>";
            table += "<th>Modifications</th>";
        table += "</tr>";
        
        try {
            if(!this.results.isBeforeFirst()){
                table += "<tr>";
                    table += "<td colspan='6'> Sorry, no records matched your search</td>";
                table += "<tr>";
            }
            else{
            while(this.results.next()){
                
                Videogames videogame = new Videogames();
                String dconfirm = " onclick=\"return confirm('Confirm deletion?')\"";
                
                videogame.setV_id(this.results.getInt("v_id"));
                videogame.setV_name(this.results.getString("v_name"));
                videogame.setYears_old(this.results.getInt("years_old"));
                videogame.setGame_type(this.results.getString("game_type"));
                videogame.setRating(this.results.getInt("rating"));
                
                table += "<tr>";
                    table += "<td>";
                        table += videogame.getV_id();
                    table += "</td>";
                    table += "<td>";
                        table += videogame.getV_name();
                    table += "</td>";    
                    table += "<td>";
                        table += videogame.getYears_old();
                    table += "</td>";    
                    table += "<td>";
                        table += videogame.getGame_type();
                    table += "</td>";    
                    table += "<td>";
                        table += videogame.getRating();
                    table += "</td>";
                    table += "<td>";
                        table += "<a href=update?v_id=" + videogame.getV_id() + ">Update</a>";
                        table += " ";
                        table += "<a href=delete?v_id=" + videogame.getV_id() + dconfirm + ">Delete </a>";
                    table += "</td>";
                table += "</tr>";
                
                
            } // end of loop
        }} catch (SQLException ex) {
            Logger.getLogger(SearchQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        table += "</table>";
        
        return table;
    } // end of method
}
