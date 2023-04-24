
//import java.sql.*;
//
//public class UserDatabase {
//
//  private static final String URL = "jdbc:mysql://localhost:3306/user_db";
//  private static final String USER = "root";
//  private static final String PASSWORD = "senha";
//
//  public static void main(String[] args) {
//    try {
//      Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
//
//      createTable(conn);
//
//      createUser(conn, "joao", "senha123");
//
//      updateUser(conn, "joao", "novasenha");
//
//      deleteUser(conn, "joao");
//
//      conn.close();
//    } catch (SQLException e) {
//      System.out.println("Ocorreu um erro: " + e.getMessage());
//    }
//  }
//
//  private static void createTable(Connection conn) throws SQLException {
//    String sql =
//      "CREATE TABLE IF NOT EXISTS users (" +
//      "id INT AUTO_INCREMENT PRIMARY KEY," +
//      "username VARCHAR(255) NOT NULL," +
//      "password VARCHAR(255) NOT NULL" +
//      ")";
//    Statement stmt = conn.createStatement();
//    stmt.execute(sql);
//    stmt.close();
//  }
//
//  private static void createUser(
//    Connection conn,
//    String username,
//    String password
//  ) throws SQLException {
//    String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
//    PreparedStatement stmt = conn.prepareStatement(sql);
//    stmt.setString(1, username);
//    stmt.setString(2, password);
//    stmt.executeUpdate();
//    stmt.close();
//  }
//
//  private static void updateUser(
//    Connection conn,
//    String username,
//    String newPassword
//  ) throws SQLException {
//    String sql = "UPDATE users SET password = ? WHERE username = ?";
//    PreparedStatement stmt = conn.prepareStatement(sql);
//    stmt.setString(1, newPassword);
//    stmt.setString(2, username);
//    stmt.executeUpdate();
//    stmt.close();
//  }
//
//  private static void deleteUser(Connection conn, String username)
//    throws SQLException {
//    String sql = "DELETE FROM users WHERE username = ?";
//    PreparedStatement stmt = conn.prepareStatement(sql);
//    stmt.setString(1, username);
//    stmt.executeUpdate();
//    stmt.close();
//  }
//}

// import java.sql.*;

// public class UserDatabase {

//   private static final String URL = "jdbc:mysql://localhost:3306/user_db";
//   private static final String USER = "root";
//   private static final String PASSWORD = "senha";

//   public static void main(String[] args) {
//     try {
//       Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

//       createTable(conn);

//       createUser(conn, "joao", "senha123");

//       updateUser(conn, "joao", "novasenha");

//       deleteUser(conn, "joao");

//       conn.close();
//     } catch (SQLException e) {
//       System.out.println("Ocorreu um erro: " + e.getMessage());
//     }
//   }

//   private static void createTable(Connection conn) throws SQLException {
//     String sql =
//       "CREATE TABLE IF NOT EXISTS users (" +
//       "id INT AUTO_INCREMENT PRIMARY KEY," +
//       "username VARCHAR(255) NOT NULL," +
//       "password VARCHAR(255) NOT NULL" +
//       ")";
//     Statement stmt = conn.createStatement();
//     stmt.execute(sql);
//     stmt.close();
//   }

//   private static void createUser(
//     Connection conn,
//     String username,
//     String password
//   ) throws SQLException {
//     String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
//     PreparedStatement stmt = conn.prepareStatement(sql);
//     stmt.setString(1, username);
//     stmt.setString(2, password);
//     stmt.executeUpdate();
//     stmt.close();
//   }

//   private static void updateUser(
//     Connection conn,
//     String username,
//     String newPassword
//   ) throws SQLException {
//     String sql = "UPDATE users SET password = ? WHERE username = ?";
//     PreparedStatement stmt = conn.prepareStatement(sql);
//     stmt.setString(1, newPassword);
//     stmt.setString(2, username);
//     stmt.executeUpdate();
//     stmt.close();
//   }

//   private static void deleteUser(Connection conn, String username)
//     throws SQLException {
//     String sql = "DELETE FROM users WHERE username = ?";
//     PreparedStatement stmt = conn.prepareStatement(sql);
//     stmt.setString(1, username);
//     stmt.executeUpdate();
//     stmt.close();
//   }
// }
