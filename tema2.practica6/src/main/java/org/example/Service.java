package org.example;

import java.sql.*;

public class Service {
    public void studentNameAndHouseName(String url, String user, String passw){
        try(Connection connection = DriverManager.getConnection(url, user, passw)){
            String sql = "SELECT e.nombre || '' || e.apellido AS Nombre_Estudiante, casa.nombre_casa AS Nombre_Casa FROM estudiante e JOIN casa ON e.id_casa = casa.id_casa";
            PreparedStatement query = connection.prepareStatement(sql);
            ResultSet result = query.executeQuery();
        } catch (SQLException ex) {
            System.out.println("ERROR: studentsGroupByHouses()");
        }
    }
}
