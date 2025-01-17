package service;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import daointerface.RecordDao;
import daointerface.impl.RecordDaoImpl;
import model.Records;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RecordService {

    private final RecordDao recordDao;


    public RecordService() {
        //programming to the interface
        this.recordDao = new RecordDaoImpl();
    }

    public void getAllRecords(){
        List<Records> records = recordDao.getAllRecords();
        System.out.println("All Records");

        for(Records record : records){
            System.out.println("Catalog Number: " + record.getCatalogNum() + "Name: " + record.getRecordName());
        }
    }

    public void save(){
        Scanner scanner = new Scanner(System.in);
        ArrayList<Records> recordList = new ArrayList<>();

        System.out.println("Enter the number of records to add: ");
        int numberOfRecords = Integer.parseInt(scanner.nextLine());

        for(int i = 0; i < numberOfRecords; i++){
            Records record = new Records();

            System.out.println("Enter Catalog Number for Record " + (i + 1) + ":");
            record.setCatalogNum(Integer.parseInt(scanner.nextLine()));

            System.out.println("Enter Record name for Record " + (i +1) + ":");
            record.setRecordName(scanner.nextLine());
            recordList.add(record);
        }

        recordDao.saveRecord(recordList);
        System.out.println("Records save successfully.");

    }

    public void updateRecord(){
        Scanner scanner = new Scanner(System.in);
        Records updateRecord = new Records();

        System.out.println("Enter the Catalog Number of the record to update: ");
        int catalogNumber = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter the new Record name.");
        updateRecord.setRecordName(scanner.nextLine());

        updateRecord.setCatalogNum(catalogNumber);

        recordDao.updateRecord(updateRecord, catalogNumber);
        System.out.println("Record updated successfully.");
    }

    public void deleteRecord(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the Record Id of the record to delete. ");
        int recordId = Integer.parseInt(scanner.nextLine());

        recordDao.deleteRecord(recordId);
        System.out.println("Record deleted successfully.");

    }


}
