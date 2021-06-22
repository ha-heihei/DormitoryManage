package com.lh.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;
import java.util.Map;

/**
 * @author LiHao
 * @create 20201124 10:32
 */
public class ExcelUtils {

    public static HSSFWorkbook downLoadExcel(String title,List<String> colsName, List<Map<String,Object>> data){
        HSSFWorkbook workbook=new HSSFWorkbook();
        HSSFSheet sheet=workbook.createSheet(title);
        HSSFRow header=sheet.createRow(0);
        for(int i=0;i<colsName.size();i++){
            HSSFCell cell=header.createCell(i);
            cell.setCellValue(colsName.get(i));
        }

        for(int j=1;j<=data.size();j++){
            HSSFRow row=sheet.createRow(j);
            int col=0;
            for(Map.Entry item:data.get(j-1).entrySet()){
                HSSFCell cell=row.createCell(col);
                cell.setCellValue(item.getValue().toString());
                col++;
            }
        }

        return workbook;
    }


}
