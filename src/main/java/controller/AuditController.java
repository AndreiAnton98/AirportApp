package controller;

import dao.AuditDao;
import dao.FlightDao;
import model.Audit;
import model.User;

import java.sql.Connection;
import java.util.List;

public class AuditController {
    private AuditDao auditDao;
    private static AuditController instance;

    private AuditController(){
        Connection connection = DatabaseConnection.getInstance();
        auditDao = new AuditDao(connection);
    }

    public static AuditController getInstance(){
        if(instance == null){
            instance = new AuditController();
        }
        return instance;
    }

    public boolean addAudit(Audit audit, User user){
        return auditDao.createAudit(audit, user);
    }

    public List<Audit> getAudit(User user){
        return auditDao.getAudit(user);
    }
}
