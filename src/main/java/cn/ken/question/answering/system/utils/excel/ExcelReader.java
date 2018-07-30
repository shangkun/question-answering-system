package cn.ken.question.answering.system.utils.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * author: shangkun <br/>
 * date: 2018/7/16 <br/>
 * what: ExcelReader <br/>
 */
public class ExcelReader {

    @SuppressWarnings({ "resource", "unused" })
    public static ArrayList<ArrayList<String>> xlsxReader(String excelUrl, int... args) throws IOException {

        XSSFWorkbook xssfWorkbook = null;

        File excelFile = new File(excelUrl);
        InputStream is = new FileInputStream(excelFile);
        xssfWorkbook = new XSSFWorkbook(is);

        if(xssfWorkbook==null){
            System.out.println("未读取到内容,请检查路径！");
            return null;
        }

        ArrayList<ArrayList<String>> ans=new ArrayList<>();
        //遍历xlsx中的sheet
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            // 对于每个sheet，读取其中的每一行
            for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow == null) continue;
                ArrayList<String> curarr=new ArrayList<>();
                for(int columnNum = 0 ; columnNum<args.length ; columnNum++){
                    XSSFCell cell = xssfRow.getCell(args[columnNum]);

                    curarr.add( Trim_str( getValue(cell) ) );
                }
                ans.add(curarr);
            }
        }
        return ans;
    }

    @SuppressWarnings({ "resource", "unused" })
    public static ArrayList<ArrayList<String>> xlsxReaderBySheet(String excelUrl,int numSheet, int... args) throws IOException {

        XSSFWorkbook xssfWorkbook = null;

        File excelFile = new File(excelUrl);
        InputStream is = new FileInputStream(excelFile);
        xssfWorkbook = new XSSFWorkbook(is);

        if(xssfWorkbook==null){
            System.out.println("未读取到内容,请检查路径！");
            return null;
        }

        ArrayList<ArrayList<String>> ans=new ArrayList<>();

        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
        if (xssfSheet == null) {
            return null;
        }
        // 对于每个sheet，读取其中的每一行
        for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
            XSSFRow xssfRow = xssfSheet.getRow(rowNum);
            if (xssfRow == null) continue;
            ArrayList<String> curarr=new ArrayList<>();
            for(int columnNum = 0 ; columnNum<args.length ; columnNum++){
                XSSFCell cell = xssfRow.getCell(args[columnNum]);

                curarr.add( Trim_str( getValue(cell) ) );
            }
            ans.add(curarr);
        }
        return ans;
    }

    @SuppressWarnings({ "resource", "unused" })
    public static ArrayList<ArrayList<String>> xlsReader(String excel_url, int... args) throws IOException {

        HSSFWorkbook hssfWorkbook = null;

        File excelFile = new File(excel_url);
        InputStream is = new FileInputStream(excelFile);
        hssfWorkbook = new HSSFWorkbook(is);

        if(hssfWorkbook==null){
            System.out.println("未读取到内容,请检查路径！");
            return null;
        }

        ArrayList<ArrayList<String>> ans=new ArrayList<>();
        //遍历xlsx中的sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 对于每个sheet，读取其中的每一行
            for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow == null) continue;
                ArrayList<String> curarr=new ArrayList<>();
                for(int columnNum = 0 ; columnNum<args.length ; columnNum++){
                    HSSFCell cell = hssfRow.getCell(args[columnNum]);

                    curarr.add( Trim_str( getValue(cell) ) );
                }
                ans.add(curarr);
            }
        }
        return ans;
    }

    @SuppressWarnings("deprecation")
    private static String getValue(XSSFCell xssfRow) {
        if(xssfRow==null){
            return "---";
        }
        if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfRow.getBooleanCellValue());
        } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
            double cur=xssfRow.getNumericCellValue();
            long longVal = Math.round(cur);
            Object inputValue = null;
            if(Double.parseDouble(longVal + ".0") == cur)
                inputValue = longVal;
            else
                inputValue = cur;
            return String.valueOf(inputValue);
        } else if(xssfRow.getCellType() == xssfRow.CELL_TYPE_BLANK || xssfRow.getCellType() == xssfRow.CELL_TYPE_ERROR){
            return "---";
        }
        else {
            return String.valueOf(xssfRow.getStringCellValue());
        }
    }

    @SuppressWarnings("deprecation")
    private static String getValue(HSSFCell hssfCell) {
        if(hssfCell==null){
            return "---";
        }
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            double cur=hssfCell.getNumericCellValue();
            long longVal = Math.round(cur);
            Object inputValue = null;
            if(Double.parseDouble(longVal + ".0") == cur)
                inputValue = longVal;
            else
                inputValue = cur;
            return String.valueOf(inputValue);
        } else if(hssfCell.getCellType() == hssfCell.CELL_TYPE_BLANK || hssfCell.getCellType() == hssfCell.CELL_TYPE_ERROR){
            return "---";
        }
        else {
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }

    static private String Trim_str(String str){
        if(str==null)
            return null;
        return str.replaceAll("[\\s\\?]", "").replace("　", "");
    }
}
