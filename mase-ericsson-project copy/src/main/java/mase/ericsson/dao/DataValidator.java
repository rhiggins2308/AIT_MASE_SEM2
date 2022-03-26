package mase.ericsson.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
//import org.apache.log4j.Logger;
import mase.ericsson.entities.BaseData;
import mase.ericsson.entities.EventCause;
import mase.ericsson.entities.EventCauseID;
import mase.ericsson.entities.FailureClass;
import mase.ericsson.entities.MccMnc;
import mase.ericsson.entities.MccMncID;
import mase.ericsson.entities.UserEquipment;

public class DataValidator {
	List<Object> recordsToPersist;
	
	List<EventCauseID> validEventCauseIdList;
	List<Integer> validFailureClassList;
	List<MccMncID> validMccMncIdList;
	List<Integer> validTacList;
	
	NetworkEventsDAO neDao = new NetworkEventsDAO();
	List<Object> invalidEventsNulls;
	List<Object> invalidEventsDupes;
	List<Object> validatedBaseData;
	List<Object> validatedReferenceData;
	
	File logFile;
	FileWriter writer;

/*	Log4j functionality commented out for Sprint 1 - cannot redirect to file	
 * 	************************************		
 *	static Logger logger = Logger.getLogger(DataValidator.class);
 */

	public DataValidator() {
		logFile = createLogFile();
	}
	
	private File createLogFile() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now);
		String filePath = "mase-ericsson-project/logs/" + date + "_errorLog.log";
		
		//String AbsoluteFilePath = "C:/Users/rhigg/eclipse-workspace/AIT_MASE_SEM2/team-possible/" + filePath;
		//String AbsoluteFilePath = "C:/Users/David/git/team-possible/" + filePath;
		String AbsoluteFilePath = "/Users/robbie/eclipse-workspace/AIT_MASE_SEM2/team-possible/" + filePath;
		return new File(AbsoluteFilePath);
	}	

	private void writeToFile(List<Object> objects, String errorCode) {
		try {
			FileWriter writer = new FileWriter(logFile, true);
			writer.write("\nTotal invalid records excluded for " + errorCode + ": " + objects.size());
			writer.write("\n****************************************************");
			
			for (Object obj : objects) {
				String logEntry = "\nInvalid " + obj.getClass().getSimpleName();
				
				if (obj instanceof BaseData) {
					logEntry = logEntry + " IMSI: " + ((BaseData) obj).getImsi();
				} else if (obj instanceof EventCause) {
					logEntry = logEntry + " causeCode: " + ((EventCause) obj).getCauseCode() + " EventId: " + ((EventCause) obj).getEventId();
				} else if (obj instanceof FailureClass) {
					logEntry = logEntry + " failureClass: " + ((FailureClass) obj).getFailureClass();
				} else if (obj instanceof MccMnc) {
					logEntry = logEntry + " mcc: " + ((MccMnc) obj).getMcc() + " mnc: " + ((MccMnc) obj).getMnc();
				} else if (obj instanceof UserEquipment) {
					logEntry = logEntry + " tac: " + ((UserEquipment) obj).getTac();
				}	
				writer.write(logEntry);
		    }
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<Object> removeNullRecords(List<Object> uncheckedList) {
		
		List<Object> checkedForNullsList = new ArrayList<>();
		invalidEventsNulls = new ArrayList<>();
		
		for(Object recordToCheck : uncheckedList) {
			if (recordToCheck instanceof BaseData) {
				if (((BaseData) recordToCheck).getFailureClass() != null && ((BaseData) recordToCheck).getEventId() >= 0 && ((BaseData) recordToCheck).getMarket() >= 0 && ((BaseData) recordToCheck).getOperator() >= 0 && ((BaseData) recordToCheck).getCauseCode() >= 0 && ((BaseData) recordToCheck).getImsi() >= 0 && ((BaseData) recordToCheck).getUeType() >= 0) {
					checkedForNullsList.add(recordToCheck);
				} else {
					invalidEventsNulls.add(recordToCheck);
				}
			} else if (recordToCheck instanceof EventCause) {
			 	if (((EventCause) recordToCheck).getCauseCode() >= 0 && ((EventCause) recordToCheck).getEventId() > 0 && !((EventCause) recordToCheck).getDescription().isEmpty() || !((EventCause) recordToCheck).getDescription().equals("(null)")) {
					checkedForNullsList.add(recordToCheck);
				} else {
					//logger.info("EventCause Reference Data containing null entries || CauseCode: " + ((EventCause) recordToCheck).getCauseCode() + " || EventID: " + ((EventCause) recordToCheck).getEventId());
					invalidEventsNulls.add(recordToCheck);
				}
			} else if (recordToCheck instanceof FailureClass) {
				if (((FailureClass) recordToCheck).getFailureClass() >= 0 && !((FailureClass) recordToCheck).getDescription().isEmpty() || !((FailureClass) recordToCheck).getDescription().equals("(null)")) {
					checkedForNullsList.add(recordToCheck);
				} else {
					invalidEventsNulls.add(recordToCheck);
				}
			} else if (recordToCheck instanceof UserEquipment) {
				if (((UserEquipment) recordToCheck).getTac() >= 0 && !((UserEquipment) recordToCheck).getMarketingName().isEmpty() && !((UserEquipment) recordToCheck).getManufacturer().isEmpty() && !((UserEquipment) recordToCheck).getAccessCapability().isEmpty() && !((UserEquipment) recordToCheck).getModel().isEmpty() && !((UserEquipment) recordToCheck).getVendorName().isEmpty()) {
					checkedForNullsList.add(recordToCheck);
				} else {
					invalidEventsNulls.add(recordToCheck);
				}
			} else if (recordToCheck instanceof MccMnc) {
				if (((MccMnc) recordToCheck).getMcc() >= 0 && ((MccMnc) recordToCheck).getMnc() >= 0 && !((MccMnc) recordToCheck).getCountry().isEmpty() && !((MccMnc) recordToCheck).getOperator().isEmpty()) {
					checkedForNullsList.add(recordToCheck);
				} else {
					invalidEventsNulls.add(recordToCheck);
				}
			}			
		}

		writeToFile(invalidEventsNulls, "NULL ENTRIES");
		return new ArrayList<>(checkedForNullsList);
	}
	
	private boolean checkVsReferenceList(Object recordToCheck, List<Integer> referenceList) {
		
		if (recordToCheck instanceof FailureClass) {
			for (Integer reference : referenceList) {
				if (((FailureClass) recordToCheck).getFailureClass().intValue() == reference.intValue()) {
					return true;
				}
			}
		} else if (recordToCheck instanceof UserEquipment) {
			for (Integer reference : referenceList) {
				if (((UserEquipment) recordToCheck).getTac().intValue() == reference.intValue()) {
					return true;
				}
			}
		} else if (recordToCheck instanceof BaseData) {
			for (Integer reference : referenceList) {
				if (((BaseData) recordToCheck).getUeType().intValue() == reference.intValue()) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	private List<Object> removeDuplicateReferenceData(List<Object> uncheckedList) {
		List<Object> checkedForDuplicatesList = new ArrayList<>();
		invalidEventsDupes = new ArrayList<>();
		
		for (Object recordToCheck : uncheckedList) {
			if (recordToCheck instanceof BaseData) {
				checkedForDuplicatesList.add(recordToCheck);
			} else if (recordToCheck instanceof FailureClass) {
				if (checkVsReferenceList(recordToCheck, validFailureClassList)) {
					invalidEventsDupes.add(recordToCheck);
				} else {
					checkedForDuplicatesList.add(recordToCheck);
				}
			} else if (recordToCheck instanceof UserEquipment) {
				if (checkVsReferenceList(recordToCheck, validTacList)) {
					invalidEventsDupes.add(recordToCheck);
				} else {
					checkedForDuplicatesList.add(recordToCheck);
				}
			} else if (recordToCheck instanceof EventCause) {
				EventCauseID checkEcid = new EventCauseID(((EventCause) recordToCheck).getCauseCode(), ((EventCause) recordToCheck).getEventId());
				boolean duplicate = false;
				
				for (EventCauseID eventCauseId : validEventCauseIdList) {
					if (eventCauseId.equals(checkEcid)) {
						duplicate = true;
						invalidEventsDupes.add(recordToCheck);
					}
				}
				
				if (!duplicate) {
					checkedForDuplicatesList.add(recordToCheck);
				}
				
			} else if (recordToCheck instanceof MccMnc) {
				MccMncID checkMccMncId = new MccMncID(((MccMnc) recordToCheck).getMcc(), ((MccMnc) recordToCheck).getMnc());
				boolean duplicate = false;
				for (MccMncID mccMncId : validMccMncIdList) {
					if (mccMncId.equals(checkMccMncId)) {
						duplicate = true;
						invalidEventsDupes.add(recordToCheck);
					}
				}
				
				if (!duplicate) {
					checkedForDuplicatesList.add(recordToCheck);
				}
			}
		}
		
		writeToFile(invalidEventsDupes, "DUPLICATE REFERENCE DATA");
		return new ArrayList<>(checkedForDuplicatesList);
	}
	
	private boolean checkBaseDataVsMasterLists(BaseData recordToCheck) {
		boolean valid = false;
		for (Integer reference : validFailureClassList) {
			if (recordToCheck.getFailureClass().intValue() == reference.intValue()) {
				valid = true;
				break;
			}
		}
		
		for (Integer reference : validTacList) {
			if (recordToCheck.getUeType().intValue() == reference.intValue()) {
				valid = true;
				break;
			}
		}
		
		for (EventCauseID ecId : validEventCauseIdList) {
			if (ecId.equals(new EventCauseID(recordToCheck.getCauseCode(), recordToCheck.getEventId()))) {
				valid = true;
				break;
			}
		}
		
		for (MccMncID mmId : validMccMncIdList) {
			if (mmId.equals(new MccMncID(recordToCheck.getMarket(), recordToCheck.getOperator()))) {
				valid = true;
				break;
			}
		}
						
		return valid;
	}

	private List<Object> removeInvalidBaseDataRecords(List<Object> uncheckedList){
		List<Object> checkedForInvalidBaseDataList = new ArrayList<>();
		List<Object> invalidBaseDataList = new ArrayList<>();
		
		for (Object recordToCheck : uncheckedList) {
			if (recordToCheck instanceof BaseData) {
				if (checkBaseDataVsMasterLists((BaseData) recordToCheck)) {
					checkedForInvalidBaseDataList.add(recordToCheck);
				} else {
					invalidBaseDataList.add(recordToCheck);
				}
			}
		}
		
		writeToFile(invalidBaseDataList, "BASE DATA CONTAINS INVALID REFERENCE(S)");
		return checkedForInvalidBaseDataList;
	}
	
	private void getReferenceData() {
		validFailureClassList = neDao.getValidFailureClassList();
		validTacList = neDao.getValidTacList();
		validEventCauseIdList = neDao.getValidEventCauseId();
		validMccMncIdList = neDao.getValidMccMncId();
	}
	
	public List<Object> validateReferenceData(List<Object> rawRefData) {
		
		getReferenceData();
		List<Object> checkedForNullsList = removeNullRecords(rawRefData);
		
		if (!checkedForNullsList.isEmpty()) {
			validatedReferenceData = removeDuplicateReferenceData(checkedForNullsList);
		}
		
		if (!validatedReferenceData.isEmpty()) {
			return new ArrayList<>(validatedReferenceData);
		} else {
			return new ArrayList<>();
		}	
	}	
	
	public List<Object> validateBaseData(List<Object> rawBaseData) {
		
		getReferenceData();
		invalidEventsNulls.clear();
		List<Object> checkedForNullsList = removeNullRecords(rawBaseData);
		
		if (!checkedForNullsList.isEmpty()) {
			validatedBaseData = removeInvalidBaseDataRecords(checkedForNullsList);
		}
		
		//logger.info("Invalid Records removed: " + invalidCount);		
		if (!validatedBaseData.isEmpty()) {
			return new ArrayList<>(validatedBaseData);
		} else {
			return new ArrayList<>();
		}		
	}
}