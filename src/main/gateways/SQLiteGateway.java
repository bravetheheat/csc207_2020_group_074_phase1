//package main.gateways;
//
//import main.entities.User;
//import main.gateways.beans.UserBean;
//
//import java.sql.*;
//import java.util.List;
//
//public class SQLiteGateway implements Gateway{
//
//    private Connection conn;
//
//    public SQLiteGateway() {
//        this.connect();
//    }
//
//    private void connect() {
//        try {
//                String url = "jdbc:sqlite::memory:";
//                this.conn = DriverManager.getConnection(url);
//
//        } catch (SQLException e) {
//            System.out.println("Failed to connect to DB");
//        }
//    }
//
//    private void initialize() {
//        this.createUserTable();
//        this.createMessageTable();
//    }
//
//    private void createUserTable() {
//        String sql = "CREATE TABLE IF NOT EXISTS users(\n"
//                +" id string PRIMARY KEY, \n"
//                +" username string NOT NULL\n"
//                +" password string NOT NULL\n"
//                +" role string NOT NULL\n"
//                +");";
//        try {
//            Statement stmt = conn.createStatement();
//            stmt.execute(sql);
//        } catch(SQLException e){
//            System.out.println(e.getMessage());
//        }
//    }
//
//    private void createMessageTable() {
//        String sql = "CREATE TABLE IF NOT EXISTS message(\n"
//                +" id string PRIMARY KEY, \n"
//                +" text string NOT NULL\n"
//                +" time string NOT NULL\n"
//                +" sender string NOT NULL\n"
//                +");";
//        try {
//            Statement stmt = conn.createStatement();
//            stmt.execute(sql);
//        } catch(SQLException e){
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public void saveUsers(List<User> users) {
//
//
//    }
//
//    private void saveUserBeans(List<UserBean> userBeans) {
//        String sql = "INSERT INTO users(id, username, password, role) VALUES(?,?,?,?)";
//        try {
//            PreparedStatement pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, )
//
//        } catch (SQLException e){
//            System.out.println(e.getMessage());
//        }
//
//    }
//
//}
