/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.appCadDelta.teste;

import br.com.appCadDelta.JPAConttroller.PecasJpaController;
import br.com.appCadDelta.entity.Peca;
import java.io.File;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author maiquelknechtel
 */
public class ImportPecas {

    public static void main(String[] args) {
        try {
            //           FileInputStream fileInputStream =
            //                   new FileInputStream("/Users/maiquelknechtel/Documents/delta/Aparelhos.xlsx");
            //           HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
            File f = new File("c:\\Users\\Gilberto Lessa\\Desktop\\progamação\\delta\\OSPECAS.xlsx");

            Workbook wb = WorkbookFactory.create(f);
            Sheet sheet = wb.getSheetAt(0);

            int rowsCount = sheet.getLastRowNum();
            for (int i = 1; i <= rowsCount; i++) {
                Row row = sheet.getRow(i);

                Cell cellContAparelho = row.getCell(0);
                Cell cellDescricao = row.getCell(1);

                Cell cellValor = row.getCell(3);

                Peca op = new Peca();

                if (cellContAparelho != null) {
                    op.setIdAparelho((int) Double.parseDouble(cellContAparelho.toString()));
                }

                if (cellDescricao != null) {
                    op.setDescricao(cellDescricao.toString());
                }

                if (cellValor != null) {
                    op.setValor(Double.parseDouble(cellValor.toString()));
                }

                PecasJpaController pecaJPA = new PecasJpaController();

                pecaJPA.create(op);
                System.out.println("i = " + i);

            }
            System.out.println("FIM");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

}
