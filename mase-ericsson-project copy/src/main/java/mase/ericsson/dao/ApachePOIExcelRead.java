package mase.ericsson.dao;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;

import mase.ericsson.entities.*;

//import org.apache.log4j.BasicConfigurator;
import org.apache.logging.log4j.*;
//import org.apache.log4j.Logger;
//import org.apache.log4j.FileAppender;
import org.apache.logging.log4j.core.LoggerContext;

public class ApachePOIExcelRead {

	private Workbook workbook;
	private Row currentRow;
	private Cell currentCell;

	private static final int HEADER_ROW = 0;
	private static final String NULL_STR = "(null)";
	
	private Iterator<Row> rowIterator;
	private Iterator<Cell> cellIterator;

	private List<Object> baseDataUnvalidated = new ArrayList<>();
	private List<Object> referenceDataUnvalidated = new ArrayList<>();
	
	private List<Object> baseDataToPersist;
	private List<Object> referenceDataToPersist;
	
	private DataFormatter formatter;
	DataValidator validator;

	private static NetworkEventsDAO neDao = new NetworkEventsDAO();

/* 	Log4j functionality commented out for Sprint 1 - cannot redirect to file	
 * 	************************************		
 * 	private static Logger logger = Logger.getLogger(ApachePOIExcelRead.class.getName());
 * 	private static Logger logger = LogManager.getLogger(ApachePOIExcelRead.class.getName());
 * 	private static Logger logger = LogManager.getLogger("invalidRecords");
 */
	public boolean readExcel(String fileName) {
		
		boolean canReadExcelSuccessfully = true;
		
/*		Log4j functionality commented out for Sprint 1 - cannot redirect to file	
 * 		************************************	
 *		LoggerContext context = (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
 *		File file = new File("/mase-ericsson-project/target/classes/resources/log4j2.xml");
 *		context.setConfigLocation(file.toURI());
 *		logger.info("This is my first log4j's statement");
 */		
		try {
			FileInputStream excelFile = new FileInputStream(new File(fileName));
			workbook = new HSSFWorkbook(excelFile);

			readSheetEventCause(workbook.getSheet("Event-Cause Table"));
			readSheetFailureClass(workbook.getSheet("Failure Class Table"));
			readSheetMccMnc(workbook.getSheet("MCC - MNC Table"));
			readSheetUETable(workbook.getSheet("UE Table"));
			readSheetBaseData(workbook.getSheet("Base Data"));
			workbook.close();

			validator = new DataValidator();

			referenceDataToPersist = validator.validateReferenceData(referenceDataUnvalidated);
			boolean insertRefSuccess = neDao.addData(referenceDataToPersist);

/*			Log4j functionality commented out for Sprint 1 - cannot redirect to file	
 * 			************************************	
 *			logger.info("Added Reference Data");
 */		
			baseDataToPersist = validator.validateBaseData(baseDataUnvalidated);
			boolean insertBaseSuccess = neDao.addData(baseDataToPersist);

/*			Log4j functionality commented out for Sprint 1 - cannot redirect to file	
 * 			************************************	
 * 			logger.info("Added Base Data");
 */
			if (insertRefSuccess && insertBaseSuccess) {
				System.out.println("Number of records inserted to networkEventsDB is:: Reference Data - " + referenceDataToPersist.size() + " ; BaseData - " + baseDataToPersist.size() );
			} else {
				System.out.println("Some error");
			}			
		} catch (FileNotFoundException e) {
			canReadExcelSuccessfully = false;
			e.printStackTrace();
			System.out.println("FilePath passed in: " + fileName);
		} catch (IOException e) {
			canReadExcelSuccessfully = false;
			e.printStackTrace();
		} catch (Exception e) {
			canReadExcelSuccessfully = false;
			e.printStackTrace();
		}

		return canReadExcelSuccessfully;   
	}

	public void readSheetEventCause(Sheet eventCauseSheet) {

		rowIterator = eventCauseSheet.rowIterator();
		currentRow = rowIterator.next();
		cellIterator = currentRow.cellIterator();
		formatter = new DataFormatter();
		
		EventCause ec;
		
		while (rowIterator.hasNext()) {
			currentRow = rowIterator.next();
			ec = new EventCause();
			
			if (currentRow.getRowNum() > HEADER_ROW) {
				cellIterator = currentRow.cellIterator();

				while (cellIterator.hasNext()) {
					currentCell = cellIterator.next();

					switch (currentCell.getColumnIndex()) {
					case 0:
						if (formatter.formatCellValue(currentCell).equals(NULL_STR)) {
							ec.setCauseCode(null);
						} else {
							ec.setCauseCode(Integer.parseInt(formatter.formatCellValue(currentCell)));
						}
						break;
					case 1:
						if (formatter.formatCellValue(currentCell).equals(NULL_STR)) {
							ec.setEventId(null);
						} else {
							ec.setEventId(Integer.parseInt(formatter.formatCellValue(currentCell)));
						}
						break;
					case 2:
						ec.setDescription(formatter.formatCellValue(currentCell));
						break;
					default:
						break;
					}
				}
				referenceDataUnvalidated.add(ec);
			}
		}
	}

	public void readSheetFailureClass(Sheet failureClassSheet) {

		rowIterator = failureClassSheet.rowIterator();
		currentRow = rowIterator.next();
		cellIterator = currentRow.cellIterator();
		
		FailureClass fc;
		
		while (rowIterator.hasNext()) {

			currentRow = rowIterator.next();
			fc = new FailureClass();
			if (currentRow.getRowNum() > HEADER_ROW) {
				cellIterator = currentRow.cellIterator();

				while (cellIterator.hasNext()) {

					currentCell = cellIterator.next();
					
					switch (currentCell.getColumnIndex()) {
					case 0:
						if (formatter.formatCellValue(currentCell).equals(NULL_STR)) {
							fc.setFailureClass(null);
						} else {
							fc.setFailureClass(Integer.parseInt(formatter.formatCellValue(currentCell)));
						}
						break;
					case 1:
						fc.setDescription(formatter.formatCellValue(currentCell));
						break;
					default:
						break;
					}
				}
				referenceDataUnvalidated.add(fc);
			}
		}
	}
	
	public void readSheetMccMnc(Sheet mccMncSheet) {

		rowIterator = mccMncSheet.rowIterator();
		currentRow = rowIterator.next();
		cellIterator = currentRow.cellIterator();
		formatter = new DataFormatter();
		
		MccMnc mccMnc;

		while (rowIterator.hasNext()) {
			currentRow = rowIterator.next();
			mccMnc = new MccMnc();

			if (currentRow.getRowNum() > HEADER_ROW) {
				cellIterator = currentRow.cellIterator();

				while (cellIterator.hasNext()) {
					currentCell = cellIterator.next();

					switch (currentCell.getColumnIndex()) {
					case 0:
						if (formatter.formatCellValue(currentCell).equals(NULL_STR)) {
							mccMnc.setMcc(null);
						} else {
							mccMnc.setMcc(Integer.parseInt(formatter.formatCellValue(currentCell)));
						}
						break;
					case 1:
						if (formatter.formatCellValue(currentCell).equals(NULL_STR)) {
							mccMnc.setMnc(null);
						} else {
							mccMnc.setMnc(Integer.parseInt(formatter.formatCellValue(currentCell)));
						}
						break;
					case 2:
						mccMnc.setCountry(formatter.formatCellValue(currentCell));
						break;
					case 3:
						mccMnc.setOperator(formatter.formatCellValue(currentCell));
						break;
					default:
						break;
					}

				}
				referenceDataUnvalidated.add(mccMnc);

			}
		}
	}

	public void readSheetUETable(Sheet UETableSheet) {

		rowIterator = UETableSheet.rowIterator();
		currentRow = rowIterator.next();
		cellIterator = currentRow.cellIterator();

		UserEquipment ue;
		
		while (rowIterator.hasNext()) {

			currentRow = rowIterator.next();
			ue = new UserEquipment();

			if (currentRow.getRowNum() > HEADER_ROW) {
				cellIterator = currentRow.cellIterator();

				while (cellIterator.hasNext()) {
					currentCell = cellIterator.next();

					switch (currentCell.getColumnIndex()) {
					case 0:
						if (formatter.formatCellValue(currentCell).equals(NULL_STR)) {
							ue.setTac(null);
						} else {
							ue.setTac(Integer.parseInt(formatter.formatCellValue(currentCell)));
						}
						break;
					case 1:
						ue.setMarketingName(formatter.formatCellValue(currentCell));
						break;
					case 2:
						ue.setManufacturer(formatter.formatCellValue(currentCell));
						break;
					case 3:
						ue.setAccessCapability(formatter.formatCellValue(currentCell));
						break;
					case 4:
						ue.setModel(formatter.formatCellValue(currentCell));
						break;
					case 5:
						ue.setVendorName(formatter.formatCellValue(currentCell));
						break;
					case 6:
						ue.setUeType(formatter.formatCellValue(currentCell));
						break;
					case 7:
						ue.setOs(formatter.formatCellValue(currentCell));
						break;
					case 8:
						ue.setInputMode(formatter.formatCellValue(currentCell));
						break;
					default:
						break;
					}

				}
				referenceDataUnvalidated.add(ue);
			}
		}
	}

	private void readSheetBaseData(Sheet baseDataSheet) {
		rowIterator = baseDataSheet.rowIterator();
		currentRow = rowIterator.next();
		cellIterator = currentRow.cellIterator();
		formatter = new DataFormatter();
		
		BaseData bd;
		
		while (rowIterator.hasNext()) {
			currentRow = rowIterator.next();
			bd = new BaseData();

			if (currentRow.getRowNum() > HEADER_ROW) { 
				cellIterator = currentRow.cellIterator();

				while (cellIterator.hasNext()) {
					currentCell = cellIterator.next();

					switch (currentCell.getColumnIndex()) {
					case 0:
						bd.setEventDate(currentCell.getDateCellValue());
						break;
					case 1:
						if (formatter.formatCellValue(currentCell).equals(NULL_STR)) {
							bd.setEventId(null);
						} else {
							bd.setEventId(Integer.parseInt(formatter.formatCellValue(currentCell)));
						}
						break;
					case 2:
						if (formatter.formatCellValue(currentCell).equals(NULL_STR)) {
							bd.setFailureClass(null);
						} else {
							bd.setFailureClass(Integer.parseInt(formatter.formatCellValue(currentCell)));
						}
						break;
					case 3:
						if (formatter.formatCellValue(currentCell).equals(NULL_STR)) {
							bd.setUeType(null);
						} else {
							bd.setUeType(Integer.parseInt(formatter.formatCellValue(currentCell)));
						}
						break;
					case 4:
						if (formatter.formatCellValue(currentCell).equals(NULL_STR)) {
							bd.setMarket(null);
						} else {
							bd.setMarket(Integer.parseInt(formatter.formatCellValue(currentCell)));
						}
						break;
					case 5:
						if (formatter.formatCellValue(currentCell).equals(NULL_STR)) {
							bd.setOperator(null);
						} else {
							bd.setOperator(Integer.parseInt(formatter.formatCellValue(currentCell)));
						}
						break;
					case 6:
						if (formatter.formatCellValue(currentCell).equals(NULL_STR)) {
							bd.setCellId(null);
						} else {
							bd.setCellId(Integer.parseInt(formatter.formatCellValue(currentCell)));
						}
						break;
					case 7:
						if (formatter.formatCellValue(currentCell).equals(NULL_STR)) {
							bd.setDuration(null);
						} else {
							bd.setDuration(Integer.parseInt(formatter.formatCellValue(currentCell)));
						}
						break;
					case 8:
						if (formatter.formatCellValue(currentCell).equals(NULL_STR)) {
							bd.setCauseCode(null);
						} else {
							bd.setCauseCode(Integer.parseInt(formatter.formatCellValue(currentCell)));
						}
						break;
					case 9:
						bd.setNeVersion(formatter.formatCellValue(currentCell));
						break;
					case 10:
						if (formatter.formatCellValue(currentCell).equals(NULL_STR)) {
							bd.setImsi(null);
						} else {
							bd.setImsi(Long.parseLong(formatter.formatCellValue(currentCell)));
						}
						break;
					case 11:
						if (formatter.formatCellValue(currentCell).equals(NULL_STR)) {
							bd.setHier3Id(null);
						} else {
							bd.setHier3Id(Long.parseLong(formatter.formatCellValue(currentCell)));
						}
						break;
					case 12:
						if (formatter.formatCellValue(currentCell).equals(NULL_STR)) {
							bd.setHier32Id(null);
						} else {
							bd.setHier32Id(Long.parseLong(formatter.formatCellValue(currentCell)));
						}
						break;
					case 13:
						if (formatter.formatCellValue(currentCell).equals(NULL_STR)) {
							bd.setHier321Id(null);
						} else {
							bd.setHier321Id(Long.parseLong(formatter.formatCellValue(currentCell)));
						}
						break;
					default:
						break;
					}
				}
				baseDataUnvalidated.add(bd);
			}
		}
	}
}