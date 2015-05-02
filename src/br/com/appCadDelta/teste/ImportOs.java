/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.appCadDelta.teste;

import br.com.appCadDelta.JPAConttroller.ClienteJpaController;
import br.com.appCadDelta.JPAConttroller.OrdemServicoJpaController;
import br.com.appCadDelta.entity.Cliente;
import br.com.appCadDelta.entity.Ordemservico;
import java.io.File;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**o
 *
 * @author maiquelknechtel
 */
public class ImportOs {

    public static void main(String[] args) {
            try {
                //           FileInputStream fileInputStream =
                //                   new FileInputStream("/Users/maiquelknechtel/Documents/delta/Aparelhos.xlsx");
                //           HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
                File f = new File("/Users/maiquelknechtel/Documents/deltaExcelFiles/OrdensDeServico.xlsx");

                Workbook wb = WorkbookFactory.create(f);
                Sheet sheet = wb.getSheetAt(0);

                int rowsCount = sheet.getLastRowNum();
                for (int i = 1; i <= rowsCount; i++) {
                    Row row = sheet.getRow(i);

                    Cell cellContOs = row.getCell(1);
                    Cell cellNomeCliente = row.getCell(2);
                    Cell cellIdentidade = row.getCell(3);
                    Cell cellCpf = row.getCell(4);
                    Cell cellEndereco = row.getCell(5);
                    Cell cellTelefone = row.getCell(9);
                    Cell cellCelular = row.getCell(10);
                    Cell cellTotalOrcamento = row.getCell(12);

                    Ordemservico os = new Ordemservico();

                    if (cellContOs != null) {
                        os.setContOs((int) Double.parseDouble(cellContOs.toString()));
                    }
                    Cliente c = new Cliente();

                    if (cellNomeCliente != null) {
                        c.setNome(cellNomeCliente.toString());
                    }

                    if (cellIdentidade != null) {
                        c.setRg(cellIdentidade.toString());
                    }

                    if (cellCpf != null) {
                        c.setCpf(cellCpf.toString());
                    }

                    if (cellEndereco != null) {
                        c.setEndereco(cellEndereco.toString());
                    }

                    if(cellTelefone!=null){
                        c.setTelefone(cellTelefone.toString());
                    }

                    if(cellCelular!=null){
                        c.setCelular(cellCelular.toString());
                    }

                    if(cellTotalOrcamento!=null){
                        os.setTotalOrcamento(Double.parseDouble(cellTotalOrcamento.toString()));
                    }
                    ClienteJpaController clienteJpa = new ClienteJpaController();
                    clienteJpa.create(c);
                    os.setClienteId(c);

                    OrdemServicoJpaController osJPA = new OrdemServicoJpaController();
                    osJPA.create(os);

                    System.out.println("i = " +i);

                }
                System.out.println("FIM");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
    }
}
