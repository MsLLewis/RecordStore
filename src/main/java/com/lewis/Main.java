package com.lewis;

import service.RecordService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        RecordService recordService = new RecordService();
        recordService.save();

      // recordService.getAllRecords();

      //  recordService.updateRecord();

    //    recordService.deleteRecord();

    }
}