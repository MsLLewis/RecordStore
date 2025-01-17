package daointerface.impl;

import daointerface.RecordDao;
import model.Records;
import utility.ConnectionDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecordDaoImpl extends ConnectionDAO implements RecordDao {


    @Override
    public List<Records> getAllRecords(){
            try{
                Connection connection = ConnectionDAO.getConnection();
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM records");

                List recordList = new ArrayList();

                while(rs.next()){
                    Records r = new Records();
                    r.setCatalogNum(rs.getInt("catalogNum"));
                    r.setRecordName(rs.getString("recordName"));
                    recordList.add(r);
                }
                return recordList;

            }catch (SQLException e){
                e.printStackTrace();
                System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            }
        return  null;
    }

    @Override
    public void saveRecord(List<Records> recordsList) {
        try{
            Connection connection = ConnectionDAO.getConnection();
            for(Records r: recordsList){
                String sqlQuery = "INSERT INTO records (catalogNum, recordName) Values (?,?)";
                PreparedStatement prepStmt = connection.prepareStatement(sqlQuery);
                prepStmt.setInt(1, r.getCatalogNum());
                prepStmt.setString(2, r.getRecordName());
                int affectedRows = prepStmt.executeUpdate();
                System.out.println(affectedRows + " row(s) affected .");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean deleteRecord(int id) {
        try{
            Connection connection = ConnectionDAO.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM records WHERE id=?");

            ps.setInt(1, id);
            int i = ps.executeUpdate();
            if(i == 1){
                return true;
            }
        }catch (SQLException e){
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateRecord(Records record, int id) {
        try{
            Connection connection = ConnectionDAO.getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE records SET catalogNum=?, recordName=? WHERE id=?");
            ps.setInt(1, record.getCatalogNum());
            ps.setString(2, record.getRecordName());
            ps.setInt(3, id);
            int i = ps.executeUpdate();
            if(i ==1){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return false;
    }


}
