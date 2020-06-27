package dao;

import model.Audit;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuditDao {
    private PreparedStatement createAuditStatement;
    private PreparedStatement getAuditByUserIdStatement;
    private Connection connection;

    public AuditDao(Connection connection){
        try {
            createAuditStatement = connection.prepareStatement("INSERT INTO audit VALUES (null, ?, ?, ?)");
            getAuditByUserIdStatement = connection.prepareStatement("SELECT * FROM audit WHERE user_id = ? ORDER BY id DESC LIMIT 10");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean createAudit(Audit audit, User user){
        try {
            createAuditStatement.setString(1, String.valueOf(user.getId()));
            createAuditStatement.setString(2, audit.getAction());
            createAuditStatement.setString(3, String.valueOf(audit.getTimestamp()));
            return createAuditStatement.executeUpdate() != 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public List<Audit> getAudit(User user){
        List<Audit> list = new ArrayList<>();
        try {
            getAuditByUserIdStatement.setString(1, String.valueOf(user.getId()));
            ResultSet rs = getAuditByUserIdStatement.executeQuery();
            while(rs.next()){
                list.add(new Audit(rs.getInt("id"), rs.getInt("user_id"), rs.getString("action"), rs.getString("timestamp")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}
