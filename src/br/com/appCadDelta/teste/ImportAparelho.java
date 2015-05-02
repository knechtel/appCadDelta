/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.appCadDelta.teste;

/**
 *
 * @author maiquelknechtel
 */
import br.com.appCadDelta.JPAConttroller.AparelhoJpaController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.appCadDelta.entity.Aparelho;

public class ImportAparelho {

    public static void main(String[] args) throws InvalidFormatException {
        try {
            //           FileInputStream fileInputStream =
            //                   new FileInputStream("/Users/maiquelknechtel/Documents/delta/Aparelhos.xlsx");
            //           HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
            File f = new File("/Users/maiquelknechtel/Documents/deltaExcelFiles/Aparelhos.xlsx");

            Workbook wb = WorkbookFactory.create(f);
            Sheet sheet = wb.getSheetAt(0);

            int rowsCount = sheet.getLastRowNum();
            for (int i = 1; i <= rowsCount; i++) {
                Row row = sheet.getRow(i);
                int colCounts = row.getLastCellNum();

                Cell cellContOs = row.getCell(0);
                Cell cellDescricao = row.getCell(1);
                Cell cellContador = row.getCell(2);
                Cell cellDataEntrada = row.getCell(3);
                Cell cellDataSaida = row.getCell(4);
                Cell cellEntradaRetorno = row.getCell(5);

                Cell cellsaidaRetorno = row.getCell(6);

                Cell cellMarca = row.getCell(7);
                Cell cellModelo = row.getCell(8);
                Cell cellNSerie = row.getCell(9);
                Cell cellDefeitoApresentado = row.getCell(9);
                Cell cellDefeitoConstado = row.getCell(10);
                Cell cellRecebedor = row.getCell(12);
                Cell cellEntregador = row.getCell(13);
                Cell cellTotalPecas = row.getCell(14);
                Cell cellMaoDeObra = row.getCell(15);
                Cell cellDesconto = row.getCell(16);
                Cell cellOrcamento = row.getCell(17);
                Cell cellAutorizado = row.getCell(18);
                Cell cellGarantia = row.getCell(19);
                Cell cellPago = row.getCell(18);
                Cell cellSConserto = row.getCell(19);
                Cell cellTecResponsavel = row.getCell(20);
               
                Cell cellPronto = row.getCell(18);
                Cell cellAspecto = row.getCell(18);
                Cell cellsConserto = row.getCell(21);
                Aparelho a = new Aparelho();
                System.out.println("i = " + i + " contOs = " + cellContOs);

                //contOs
                if (cellContOs != null) {
                    a.setContOs((int) Double.parseDouble(cellContOs.toString()));
                } else {
                    a.setContOs(null);

                }

                //descricao
                if (cellDescricao != null) {
                    a.setDescricao(cellDescricao.toString());
                } else {
                    a.setDescricao(null);

                }

                //cellContador
                if (cellContador != null) {
                    a.setContador((int) Double.parseDouble(cellContador.toString()));
                } else {
                    a.setContador(null);

                }

                //cellDataEntrada
                if (cellDataEntrada != null) {
                    a.setDataEntrada(cellDataEntrada.getDateCellValue());
                } else {
                    a.setDataEntrada(null);

                }

                //cellDataSaida
                if (cellDataSaida != null) {
                    a.setDataSaida(cellDataSaida.getDateCellValue());
                } else {
                    a.setDataSaida(null);

                }

                //cellEntradaRetorno
                if (cellEntradaRetorno != null) {
                    a.setDataEntradaRetorno(cellEntradaRetorno.getDateCellValue());
                } else {
                    a.setDataEntradaRetorno(null);
                }

                //cellSaidaRetorno
                if (cellsaidaRetorno != null) {
                    a.setSaidaRetorno(cellsaidaRetorno.getDateCellValue());

                } else {
                    a.setSaidaRetorno(null);

                }

                //cellMarca
                if (cellMarca != null) {
                    a.setMarca(cellMarca.toString());

                } else {
                    a.setMarca(null);

                }

                //cellModelo
                if (cellModelo != null) {
                    a.setModelo(cellModelo.toString());

                } else {
                    a.setModelo(null);
                }

                if (cellNSerie != null) {
                    a.setSerial(cellNSerie.toString());

                } else {
                    a.setSerial(null);

                }

                if (cellDefeitoApresentado != null) {
                    a.setDefeitoApresentado(cellDefeitoApresentado.toString());
                } else {
                    a.setDefeitoApresentado(null);
                }

                if (cellDefeitoConstado != null) {
                    a.setDefeitoCostado(cellDefeitoConstado.toString());
                } else {
                    a.setDefeitoCostado(null);
                }

//                //UsuarioRecebedor
                if (cellRecebedor != null) {
                    a.setUsuarioRecebedor(cellRecebedor.toString());

                } else {
                    a.setUsuarioRecebedor(null);
                }

                //cellEntregador
                if (cellEntregador != null) {
                    a.setUsuarioEntregador(cellEntregador.toString());

                } else {
                    a.setUsuarioEntregador(null);

                }

                if (cellTotalPecas != null) {
                    Double d = Double.parseDouble(cellTotalPecas.toString());
                    a.setTotalPeca(d);
                } else {
                    a.setTotalPeca(null);
                }
                //cellTotalPeca
                if (cellMaoDeObra != null) {
                    Double d = Double.parseDouble(cellMaoDeObra.toString());
                    a.setMaoDeObra(d);

                } else {
                    a.setMaoDeObra(null);

                }

                if (cellDesconto != null) {
                    Double d = Double.parseDouble(cellDesconto.toString());
                    a.setDesconto(d);

                } else {
                    a.setDesconto(null);
//
                }
//
                if (cellOrcamento != null) {
                    Double d = Double.parseDouble(cellOrcamento.toString());
                    a.setOrcamento(d);
                } else {
                    a.setOrcamento(null);
                }

                if (cellAutorizado != null) {

                    if (cellAutorizado.toString().equalsIgnoreCase("sim")) {
                        a.setAutorizado(1);
                    } else {
                        a.setAutorizado(0);
                    }

                } else {
                    a.setAutorizado(null);

                }

                if (cellPago != null) {

                    a.setPago(cellPago.toString());

                } else {
                    a.setPago(null);

                }

                if (cellSConserto != null) {
                    a.setSemConserto(cellSConserto.toString());

                } else {
                    a.setSemConserto(null);
                }

                AparelhoJpaController aJpa = new AparelhoJpaController();
                aJpa.create(a);

            }

            System.out.println("FIM");
        } catch (IOException ex) {
            Logger.getLogger(ImportAparelho.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
