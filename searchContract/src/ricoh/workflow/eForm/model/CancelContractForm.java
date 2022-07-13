package ricoh.workflow.eForm.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import ricoh.i2e20.library.model.BOSActivityInstance;
import ricoh.i2e20.library.model.BOSVariable;

public class CancelContractForm {

	private Long databaseId;
	private final static String databaseIdDBField = "DBID_";
	private Boolean isServiceContract;
	private final static String isServiceContractDBField = "serviceContract";
	private String serviceContractNo;
	private final static String serviceContractNoDBField = "serviceContractNo";
	private Boolean isRentalContract;
	private final static String isRentalContractDBField = "rentalContract";
	private String rentalContractNo;
	private final static String rentalContractNoDBField = "rentalContractNo";
	private Boolean isLnHContract;
	private final static String isLnHContractDBField = "lhContract";
	private String lnHContract;
	private final static String lnHContractDBField = "lhContractNo";
	private String machineId;
	private final static String machineIdDBField = "machineID";
	private String machineModel;
	private final static String machineModelDBField = "machineModel";
	private String customerCode;
	private final static String customerCodeDBField = "customer_Code";
	private String customerName;
	private final static String customerNameDBField = "customer_Name";
	private String customerAddress;
	private final static String customerAddressDBField = "customer_Address";
	private String customerContact;
	private final static String customerContactDBField = "customer_Contact";
	private String customerSection;
	private final static String customerSectionDBField = "customer_Section";
	private String customerTelephone;
	private final static String customerTelephoneDBField = "customerTel";
	private String cancelContractDetail;
	private final static String cancelContractDetailDBField = "cancelRentalContactDetail";
	private String doCloseMeter;
	private final static String doCloseMeterDBField = "doCloseMeter";
	private Date closeMeterDate;
	private final static String closeMeterDateDBField = "closeMeterDate";
	private String doEffectCancel;
	private final static String doEffectCancelDBField = "doActionCancel";
	private Date effectCancelDate;
	private final static String effectCancelDateDBField = "effectCancelDate";
	private String doReturnMachine;
	private final static String doReturnMachineDBField = "doReturnMachine";
	private Date returnMachineDate;
	private final static String returnMachineDateDBField = "returnMachineDate";
	private String requestName;
	private final static String requestNameDBField = "request_Name";
	private String requestExtensionNo;
	private final static String requestExtensionNoDBField = "request_Ext";
	private String requestDepartment;
	private final static String requestDepartmentDBField = "request_Department";
	private String requestSection;
	private final static String requestSectionDBField = "request_Section";
	private String requestPosition;
	private final static String requestPositionDBField = "request_Position";
	private String requestManager;
	private final static String requestManagerDBField = "request_Manager";
	private Date requestDate;
	private final static String requestDateDBField = "request_Date";
	private String requestMemo;
	private final static String requestMemoDBField = "request_Memo";
	private Long instanceId;

	public CancelContractForm() {
		// TODO Auto-generated constructor stub
	}

	public Long getDatabaseId() {
		return databaseId;
	}

	public void setDatabaseId(Long databaseId) {
		this.databaseId = databaseId;
	}

	public Boolean getIsServiceContract() {
		return isServiceContract;
	}

	public void setIsServiceContract(Boolean isServiceContract) {
		this.isServiceContract = isServiceContract;
	}

	public String getServiceContractNo() {
		return serviceContractNo;
	}

	public void setServiceContractNo(String serviceContractNo) {
		this.serviceContractNo = serviceContractNo;
	}

	public Boolean getIsRentalContract() {
		return isRentalContract;
	}

	public void setIsRentalContract(Boolean isRentalContract) {
		this.isRentalContract = isRentalContract;
	}

	public String getRentalContractNo() {
		return rentalContractNo;
	}

	public void setRentalContractNo(String rentalContractNo) {
		this.rentalContractNo = rentalContractNo;
	}

	public Boolean getIsLnHContract() {
		return isLnHContract;
	}

	public void setIsLnHContract(Boolean isLnHContract) {
		this.isLnHContract = isLnHContract;
	}

	public String getLnHContract() {
		return lnHContract;
	}

	public void setLnHContract(String lnHContract) {
		this.lnHContract = lnHContract;
	}

	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	public String getMachineModel() {
		return machineModel;
	}

	public void setMachineModel(String machineModel) {
		this.machineModel = machineModel;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerContact() {
		return customerContact;
	}

	public void setCustomerContact(String customerContact) {
		this.customerContact = customerContact;
	}

	public String getCustomerSection() {
		return customerSection;
	}

	public void setCustomerSection(String customerSection) {
		this.customerSection = customerSection;
	}

	public String getCustomerTelephone() {
		return customerTelephone;
	}

	public void setCustomerTelephone(String customerTelephone) {
		this.customerTelephone = customerTelephone;
	}

	public String getCancelContractDetail() {
		return cancelContractDetail;
	}

	public void setCancelContractDetail(String cancelContractDetail) {
		this.cancelContractDetail = cancelContractDetail;
	}

	public String getDoCloseMeter() {
		return doCloseMeter;
	}

	public void setDoCloseMeter(String doCloseMeter) {
		this.doCloseMeter = doCloseMeter;
	}

	public Date getCloseMeterDate() {
		return closeMeterDate;
	}

	public void setCloseMeterDate(Date closeMeterDate) {
		this.closeMeterDate = closeMeterDate;
	}

	public String getDoEffectCancel() {
		return doEffectCancel;
	}

	public void setDoEffectCancel(String doEffectCancel) {
		this.doEffectCancel = doEffectCancel;
	}

	public Date getEffectCancelDate() {
		return effectCancelDate;
	}

	public void setEffectCancelDate(Date effectCancelDate) {
		this.effectCancelDate = effectCancelDate;
	}

	public String getDoReturnMachine() {
		return doReturnMachine;
	}

	public void setDoReturnMachine(String doReturnMachine) {
		this.doReturnMachine = doReturnMachine;
	}

	public Date getReturnMachineDate() {
		return returnMachineDate;
	}

	public void setReturnMachineDate(Date returnMachineDate) {
		this.returnMachineDate = returnMachineDate;
	}

	public String getRequestName() {
		return requestName;
	}

	public void setRequestName(String requestName) {
		this.requestName = requestName;
	}

	public String getRequestExtensionNo() {
		return requestExtensionNo;
	}

	public void setRequestExtensionNo(String requestExtensionNo) {
		this.requestExtensionNo = requestExtensionNo;
	}

	public String getRequestDepartment() {
		return requestDepartment;
	}

	public void setRequestDepartment(String requestDepartment) {
		this.requestDepartment = requestDepartment;
	}

	public String getRequestSection() {
		return requestSection;
	}

	public void setRequestSection(String requestSection) {
		this.requestSection = requestSection;
	}

	public String getRequestPosition() {
		return requestPosition;
	}

	public void setRequestPosition(String requestPosition) {
		this.requestPosition = requestPosition;
	}

	public String getRequestManager() {
		return requestManager;
	}

	public void setRequestManager(String requestManager) {
		this.requestManager = requestManager;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public String getRequestMemo() {
		return requestMemo;
	}

	public void setRequestMemo(String requestMemo) {
		this.requestMemo = requestMemo;
	}

	public Long getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(Long instanceId) {
		this.instanceId = instanceId;
	}

	public static Map listContractFromBOSActivityInstanceList(
			ArrayList activityInstance, ArrayList variable) {
		Map result = null;

		Map<String, Object> source = new HashMap<String, Object>();

		for (int i = 0; i < activityInstance.size(); i++) {
			BOSActivityInstance a = (BOSActivityInstance) activityInstance
					.get(i);
			Map<String, Object> sourceSub = new HashMap<String, Object>();
			sourceSub.put("act_inst", a);
			sourceSub.put("cancelContractForm", null);
			source.put(a.getInstanceDatabaseID().toString(), sourceSub);
		}

		// System.out.println("Act List : "+ source.toString()+"\r\n");

		for (int i = 0; i < variable.size(); i++) {
			// System.out.println("Variable" + i + "\r\n");
			BOSVariable a = (BOSVariable) variable.get(i);
			if (a.getInstanceId() != null) {
				HashMap<String, CancelContractForm> map = (HashMap<String, CancelContractForm>) source
						.get(a.getInstanceId().toString());
				if (map != null) {
					// System.out.println("MAP NOT NULL\r\n");
					CancelContractForm form = (CancelContractForm) map
							.get("cancelContractForm");
					// System.out
					// .println("Show Sub Map : " + map.toString() + "\r\n");
					if (form == null) {
						form = new CancelContractForm();
						form.mapBOSVariable2BOSActivityInstance(a);
						map.put("cancelContractForm", form);
					} else {
						form.mapBOSVariable2BOSActivityInstance(a);
						map.remove("cancelContractForm");
						map.put("cancelContractForm", form);
					}
					source.put(a.getInstanceId().toString(), map);
				}
			}
		}
		// System.out
		// .println("Act List after Map : " + source.toString() + "\r\n");

		result = source;

		return result;
	}

	void mapBOSVariable2BOSActivityInstance(BOSVariable variable) {

		Date dDate = null;
		String varRef = variable.getKeyName();
		if (varRef.equals(cancelContractDetailDBField)) {
			this.setCancelContractDetail(variable.getStringValue());
		} else if (varRef.equals(customerAddressDBField)) {
			this.setCustomerAddress(variable.getStringValue());
		} else if (varRef.equals(customerCodeDBField)) {
			this.setCustomerAddress(variable.getStringValue());
		} else if (varRef.equals(customerContactDBField)) {
			this.setCustomerContact(variable.getStringValue());
		} else if (varRef.equals(customerNameDBField)) {
			this.setCustomerName(variable.getStringValue());
		} else if (varRef.equals(customerSectionDBField)) {
			this.setCustomerSection(variable.getStringValue());
		} else if (varRef.equals(customerTelephoneDBField)) {
			this.setCustomerTelephone(variable.getStringValue());
		} else if (varRef.equals(doCloseMeterDBField)) {
			this.setDoCloseMeter(variable.getStringValue());
		} else if (varRef.equals(closeMeterDateDBField)) {
			dDate = this.convertDateStringValue2DateObject(variable
					.getStringValue());
			this.setCloseMeterDate(dDate);
		} else if (varRef.equals(doEffectCancelDBField)) {
			this.setDoEffectCancel(variable.getStringValue());
		} else if (varRef.equals(effectCancelDateDBField)) {
			// dDate = new Date(variable.getStringValue());
			dDate = this.convertDateStringValue2DateObject(variable
					.getStringValue());
			this.setEffectCancelDate(dDate);
		} else if (varRef.equals(doReturnMachineDBField)) {
			this.setDoReturnMachine(variable.getStringValue());
		} else if (varRef.equals(returnMachineDateDBField)) {
			// dDate = new Date(variable.getStringValue());
			dDate = this.convertDateStringValue2DateObject(variable
					.getStringValue());
			this.setReturnMachineDate(dDate);
		} else if (varRef.equals(requestDateDBField)) {
			// dDate = new Date(variable.getStringValue());
			dDate = this.convertDateStringValue2DateObject(variable
					.getStringValue());
			this.setRequestDate(dDate);
		} else if (varRef.equals(requestDepartmentDBField)) {
			this.setRequestDepartment(requestDepartment);
		} else if (varRef.equals(requestExtensionNoDBField)) {
			this.setRequestExtensionNo(requestExtensionNo);
		} else if (varRef.equals(requestManagerDBField)) {
			this.setRequestManager(requestManager);
		} else if (varRef.equals(requestMemoDBField)) {
			this.setRequestMemo(variable.getStringValue());
		} else if (varRef.equals(requestNameDBField)) {
			this.setRequestName(variable.getStringValue());
		} else if (varRef.equals(requestPositionDBField)) {
			this.setRequestPosition(variable.getStringValue());
		} else if (varRef.equals(requestSectionDBField)) {
			this.setRequestSection(variable.getStringValue());
		} else if (varRef.equals(serviceContractNoDBField)) {
			this.setServiceContractNo(variable.getStringValue());
		} else if (varRef.equals(isServiceContractDBField)) {
			isServiceContract = false;
			String value = variable.getStringValue();
			value = value.toLowerCase();
			if (value.equals("t") || value.equals("yes")) {
				isServiceContract = true;
			}
			this.setIsServiceContract(isServiceContract);
		} else if (varRef.equals(isLnHContractDBField)) {
			isLnHContract = false;
			String value = variable.getStringValue();
			value = value.toLowerCase();
			if (value.equals("t") || value.equals("yes")) {
				isLnHContract = true;
			}
			this.setIsLnHContract(isLnHContract);
		} else if (varRef.equals(lnHContractDBField)) {
			this.setLnHContract(variable.getStringValue());
		} else if (varRef.equals(isRentalContractDBField)) {
			isRentalContract = false;
			String value = variable.getStringValue();
			value = value.toLowerCase();
			if (value.equals("t") || value.equals("yes")) {
				isRentalContract = true;
			}
			this.setIsRentalContract(isRentalContract);
		} else if (varRef.equals(rentalContractNoDBField)) {
			this.setLnHContract(variable.getStringValue());
		} else if (varRef.equals(machineIdDBField)) {
			this.setMachineId(variable.getStringValue());
		} else if (varRef.equals(machineModelDBField)) {
			this.setMachineModel(variable.getStringValue());
		}
		this.setDatabaseId(variable.getActivityID());
		this.setInstanceId(variable.getInstanceId());

	}

	Date convertDateStringValue2DateObject(String dateString) {

		Date result = null;

		if (dateString != null) {
			result = new Date();
			String[] tmp1 = dateString.split(" ");
			String[] tmp2 = tmp1[0].split("-");

			result.setDate(Integer.parseInt(tmp2[2]));
			result.setMonth(Integer.parseInt(tmp2[1]) - 1); // 0 - 11
			result.setYear(Integer.parseInt(tmp2[0]) - 1900); // Current Year - 1900
//			System.out.println(Integer.parseInt(tmp2[0]) + "-"+ tmp2[1] + "-"+tmp2[2] + "\r\n");
//			System.out.println(result.toGMTString() + "\r\n");
//			System.out.println(result.toLocaleString() + "\r\n");
		}

		return result;
	}
}
