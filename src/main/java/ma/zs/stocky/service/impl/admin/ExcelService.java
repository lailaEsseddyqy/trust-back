package ma.zs.stocky.service.impl.admin;

import ma.zs.stocky.bean.core.projet.Projet;
import ma.zs.stocky.dao.facade.core.projet.ProjetDao;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Service
public class ExcelService {
    private @Autowired ProjetDao dao;
    public void saveExcelData(MultipartFile file) throws IOException {
        /*List<SecurityProperties.User> projetList = new ArrayList<>();
        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.rowIterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row.getRowNum() == 0) {
                    //skip header row
                    continue;
                }
                Projet projet=new Projet();
                projet.setNom(row.getCell(0).getStringCellValue());
                /*user.setLastName(row.getCell(1).getStringCellValue());
                user.setSex(row.getCell(2).getStringCellValue());
                user.setAge((int) row.getCell(3).getNumericCellValue());
                user.setCounty(row.getCell(4).getStringCellValue());
                user.setAddress(row.getCell(5).getStringCellValue());
                projetList.add(projet);
            }
        }
        dao.saveAll(projetList);*/
    }
}


