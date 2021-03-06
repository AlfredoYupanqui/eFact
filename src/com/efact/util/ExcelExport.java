package com.efact.util;

import java.util.List;

import com.efact.bean.*;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

// https://www.callicoder.com/java-write-excel-file-apache-poi/

public class ExcelExport {

	
	public static InputStream salesRecordExport(List<ReportSalesRecord> list) throws Exception {
		
		String[] columns = {
				"Fuente", "Emision", "Tipo moneda", "F. Emision", "F. Vencimiento", "Programa",
				"Grupo-Cupo", "Contrato", "Tipo Comprobante", "Serie", "Numero Comprobante", 
				"Tipo DOI", "Numero DOI", "Información del Cliente", "Valor facturado", "Base imponible",
				"Exonerada", "importe inafecta", "ISC", "IGV %", "Otros", "Importe total", "TC Venta",
				"Ref Fecha", "Ref Tipo", "Ref Serie", "Ref Numero", "Ref TC", "Total afectas", 
				"Total no afectas", "Total igv", "Total", "Documento", "ID"
		};
		
        Workbook workbook = new XSSFWorkbook();
        CreationHelper createHelper = workbook.getCreationHelper();

        // Create a Sheet
        Sheet sheet = workbook.createSheet("Reporte");

        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        //headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.BLUE.getIndex());

        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheet.createRow(0);

        // Create cells
        for(int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        // Create Cell Style for formatting Date
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
        
        // Create Other rows and cells with employees data
        int rowNum = 1;
        for(ReportSalesRecord object: list) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0).setCellValue(object.getFuente());
            row.createCell(1).setCellValue(object.getTipoemision());
            row.createCell(2).setCellValue(object.getRvb_tmoneda());
            row.createCell(3).setCellValue(object.getRvb_femision());
            row.createCell(4).setCellValue(object.getRvb_fvencimiento());
            row.createCell(5).setCellValue(object.getRvb_programa());
            row.createCell(6).setCellValue(object.getGrupo_cupo());
            row.createCell(7).setCellValue(object.getRvb_contrato());
            row.createCell(8).setCellValue(object.getComprobante());
            row.createCell(9).setCellValue(object.getRvb_serie());
            row.createCell(10).setCellValue(object.getRvb_numero());
            row.createCell(11).setCellValue(object.getRvb_tipo());
            row.createCell(12).setCellValue(object.getRvb_documento());
            row.createCell(13).setCellValue(object.getRvb_datos());
            row.createCell(14).setCellValue(object.getRvb_valorfacturado());
            row.createCell(15).setCellValue(object.getRvb_baseimponible());
            row.createCell(16).setCellValue(object.getExonerada());
            row.createCell(17).setCellValue(object.getRvb_impinafecta());
            row.createCell(18).setCellValue(object.getIsc());
            row.createCell(19).setCellValue(object.getRvb_igv());
            row.createCell(20).setCellValue(object.getOtros());
            row.createCell(21).setCellValue(object.getRvb_imptotal());
            row.createCell(22).setCellValue(object.getTcd_venta());
            row.createCell(23).setCellValue(object.getRvb_femisiondev());
            row.createCell(24).setCellValue(object.getRvb_tipodev());
            row.createCell(25).setCellValue(object.getRvb_seriedev());
            row.createCell(26).setCellValue(object.getRvb_numerodev());
            row.createCell(27).setCellValue(object.getRvb_tipocambiodev());
            row.createCell(28).setCellValue(object.getTotalafectas_sol());
            row.createCell(29).setCellValue(object.getTotalnoafectas_sol());
            row.createCell(30).setCellValue(object.getTotaligv_sol());
            row.createCell(31).setCellValue(object.getTotaltotal_sol());
            row.createCell(32).setCellValue(object.getRvb_tdocumento());
            row.createCell(33).setCellValue(object.getRvb_id());
        }

		// Resize all columns to fit the content size
        for(int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }
        
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		workbook.write(baos);
		
		return new ByteArrayInputStream(baos.toByteArray());
    }

	public static InputStream salesRecordSummary(List<ReportSalesSummary> list) throws Exception {													

		String[] columns = {
				"Anio", "Tipo", "Serie", "Programa", "Ene", "Feb",
				"Mar", "Abr", "May", "Jun", "Jul", "Ago",
				"Set", "Oct", "Nov", "Dic"
		};
		
        Workbook workbook = new XSSFWorkbook();
        CreationHelper createHelper = workbook.getCreationHelper();

        Sheet sheet = workbook.createSheet("Reporte");

        Font headerFont = workbook.createFont();
        
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.BLUE.getIndex());

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        Row headerRow = sheet.createRow(0);

        for(int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
        
        int rowNum = 1;
        for(ReportSalesSummary object: list) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0).setCellValue(object.getYear());
            row.createCell(1).setCellValue(object.getTipo());
            row.createCell(2).setCellValue(object.getSerie());
            row.createCell(3).setCellValue(object.getPrograma());
            row.createCell(4).setCellValue(object.getEne());
            row.createCell(5).setCellValue(object.getFeb());
            row.createCell(6).setCellValue(object.getMar());
            row.createCell(7).setCellValue(object.getAbr());
            row.createCell(8).setCellValue(object.getMay());
            row.createCell(9).setCellValue(object.getJun());
            row.createCell(10).setCellValue(object.getJul());
            row.createCell(11).setCellValue(object.getAgo());
            row.createCell(12).setCellValue(object.getSet());
            row.createCell(13).setCellValue(object.getOct());
            row.createCell(14).setCellValue(object.getNov());
            row.createCell(15).setCellValue(object.getDic());
        }

        for(int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }
        
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		workbook.write(baos);
		
		return new ByteArrayInputStream(baos.toByteArray());
    }

	public static InputStream noteDebitExport(List<NoteDebit> list) throws Exception {													

		String[] columns = {
				"Contrato", "Grupo", "Cupo", "Nombre asociado", "Cuota", "Mes grupo",
				"Descripcion", "No Afecto", "Afecto", "IGV", "Sub total", "Penalidad"
		};
		
        Workbook workbook = new XSSFWorkbook();
        CreationHelper createHelper = workbook.getCreationHelper();

        Sheet sheet = workbook.createSheet("Reporte");

        Font headerFont = workbook.createFont();
        
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.BLUE.getIndex());

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        Row headerRow = sheet.createRow(0);

        for(int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
        
        int rowNum = 1;
        for(NoteDebit object: list) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0).setCellValue(object.getContrato());
            row.createCell(1).setCellValue(object.getGrupo());
            row.createCell(2).setCellValue(object.getCupo());
            row.createCell(3).setCellValue(object.getNombreAsociado());
            row.createCell(4).setCellValue(object.getCuota());
            row.createCell(5).setCellValue(object.getMesGrupo());
            row.createCell(6).setCellValue(object.getDescripcion());
            row.createCell(7).setCellValue(object.getNoAfecto());
            row.createCell(8).setCellValue(object.getAfecto());
            row.createCell(9).setCellValue(object.getIgv());
            row.createCell(10).setCellValue(object.getSubTotal());
            row.createCell(11).setCellValue(object.getPenalidad());
        }

        
        for(int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }
        
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		workbook.write(baos);
		
		return new ByteArrayInputStream(baos.toByteArray());
    }

	
	public static InputStream accruedIssueExport(List<AccruedIssue> list) throws Exception {													
							   
		String[] columns = {
				"Recaudo", "Codigo Recaudo", "Codigo Asociado", "Nombre Asociado", "Cuota", "Posicion", "Descripcion",
				"Afecto", "No Afecto", "IGV", "Total"
		};
		
        Workbook workbook = new XSSFWorkbook();
        CreationHelper createHelper = workbook.getCreationHelper();

        Sheet sheet = workbook.createSheet("Reporte");

        Font headerFont = workbook.createFont();
        
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.BLUE.getIndex());

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        Row headerRow = sheet.createRow(0);

        for(int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
        
        int rowNum = 1;
        for(AccruedIssue object: list) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0).setCellValue(object.getRecaudoId());
            row.createCell(1).setCellValue(object.getCodRecaudo());
            row.createCell(2).setCellValue(object.getCodigoAsociado());
            row.createCell(3).setCellValue(object.getDatosAsociados());
            row.createCell(4).setCellValue(object.getNumeroCuota());
            row.createCell(5).setCellValue(object.getPosicion());
            row.createCell(6).setCellValue(object.getDescripcion());
            row.createCell(7).setCellValue(object.getAfecto());
            row.createCell(8).setCellValue(object.getNoAfecto());
            row.createCell(9).setCellValue(object.getIgv());
            row.createCell(10).setCellValue(object.getTotal());
        }              
        
        
        for(int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }
        
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		workbook.write(baos);
		
		return new ByteArrayInputStream(baos.toByteArray());
    }

}
