package com.stresslesslibrary.bookservice.servicesImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTable;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableStyleInfo;

import com.stresslesslibrary.bookservice.entities.Book;
import com.stresslesslibrary.bookservice.entities.Branch;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

public class ExcelService {
	
	
	 	private List < Book > bookList;
	 	private List < Branch > branchList;
	    private XSSFWorkbook workbook;
	    private XSSFSheet sheet;
	    private XSSFSheet sheet1;

	    public ExcelService(List <Book> bookList, List< Branch> branchList) {
	        this.bookList = bookList;
	        this.branchList= branchList;
	        workbook = new XSSFWorkbook();
	    }
	
 
    public void writeHeader() {
        sheet = workbook.createSheet("Books");
        sheet1=workbook.createSheet("Dewey Thousands");
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        
      //Create header for Books sheet
       List<String> header = new ArrayList<>();
        	Stream.of("ISBN","Title","Subtitle","Language","Description",
        			"PageCount","PrintKind","PublishedDate","Publisher",
        			"Authors","PdfAvailble","epubAvailble","isReference","DeweyThousand").forEach(item -> {
        				header.add(item);
        			});
       
        
        XSSFRow row = sheet.createRow(0);
            
        for(int i=0;i<header.size();i++) {
        	createCell(row, i,header.get(i),style);
        }
        
        AreaReference reference = new AreaReference(new CellReference(0, 0), new CellReference(1000, header.size()-1),SpreadsheetVersion.EXCEL2007);

        XSSFTable table = sheet.createTable(reference);
        table.setDisplayName("Books");
        CTTable cttable = table.getCTTable();

        CTTableStyleInfo style1 = cttable.addNewTableStyleInfo();
        style1.setName("TableStyleMedium2");
        style1.setShowColumnStripes(false);
        style1.setShowRowStripes(true);

       
        cttable.setRef(reference.formatAsString());
        
        XSSFRow row1 = sheet1.createRow(0);	
        List<String> header1 = new ArrayList<>();
    	
        //Create header for Dewey Thousand classification
        Stream.of("Code","Label").forEach(item -> {
    				header1.add(item);
    			});
       
        for(int i=0;i<header1.size();i++) {
         	createCell(row1, i,header1.get(i),style);
         }
        
        AreaReference reference1 = new AreaReference(new CellReference(0, 0), new CellReference(1000, header1.size()-1),SpreadsheetVersion.EXCEL2007);

        XSSFTable table1 = sheet1.createTable(reference1);
        table1.setDisplayName("DeweyThousand");
        table1.setStyleName("TableStyleMedium2");
       

     
       
    }
    
    
    public void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
    
        if (valueOfCell instanceof Integer ) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String || valueOfCell==null) {
            cell.setCellValue((String) valueOfCell);
        } else {
            cell.setCellValue((Boolean) valueOfCell);
        }
        cell.setCellStyle(style);
    }
  
    
    public void write() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        //Excell Cell text wrap
        style.setWrapText(true);
        
        //Excell vertical alignment
        style.setVerticalAlignment(VerticalAlignment.TOP);
        XSSFFont font = workbook.createFont();
        //Excell font size
        font.setFontHeight(14);
        style.setFont(font);
        
        for (Book book: bookList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, book.getIsbn(), style);
            createCell(row, columnCount++, book.getTitle(), style);
            createCell(row, columnCount++, book.getSubtitle(), style);
            createCell(row, columnCount++, book.getLanguage().name(), style);
            createCell(row, columnCount++, book.getDescription(), style);
            createCell(row, columnCount++, book.getPageCount(), style);
            createCell(row, columnCount++, book.getKind().name(), style);
            createCell(row, columnCount++, book.getPublishedDate(), style);
            createCell(row, columnCount++, book.getPublisher(), style);
            createCell(row, columnCount++, book.getAuthors().stream().map(x->x.getName()).collect(Collectors.toList()), style);
            createCell(row, columnCount++, book.getPdfAvailble(), style);
            createCell(row, columnCount++, book.getEpubAvailble(), style);
            createCell(row, columnCount++, book.getIsReference(), style);
            createCell(row, columnCount++, book.getBranch(), style);
            
            
        }
        
        rowCount=1;
        for (Branch branch: branchList) {
            Row row = sheet1.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, branch.getId(), style);
            createCell(row, columnCount++, branch.getName(), style);
            
        }
    }
    
    
    public void generateExcelFile(HttpServletResponse response) throws IOException {
        writeHeader();
        write();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
  
}
