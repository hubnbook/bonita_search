<%@page import="java.text.DateFormat, java.text.SimpleDateFormat"%>
<%@page import="java.util.Date, java.util.Map, java.util.Set"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="ricoh.i2e20.library.model.*"%>
<%@page import="ricoh.workflow.eForm.model.*"%>
<%@ page language="java"%>
<%
	// contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Export</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>Service Order No.</td>
			<td>Rental Contract No.</td>
			<td>L&H Contract No.</td>
			<td>Serial No.</td>
			<td>Model</td>
			<td>Customer Code</td>
			<td>Customer Name</td>
			<td>Customer Address</td>
			<td>Customer Contact</td>
			<td>Customer Section</td>
			<td>Customer Telephone</td>
			<td>Cancel Detail</td>
			<td>Request Close Meter</td>
			<td>Close Meter Date</td>
			<td>Request Effect Cancel</td>
			<td>Effect Cancel Date</td>
			<td>Request Return Machine</td>
			<td>Return Machine Date</td>
			<td>Request By</td>
			<td>Request Extension</td>
			<td>Request Department</td>
			<td>Request Section</td>
			<td>Request Position</td>
			<td>Request Manager</td>
			<td>Request Telephone</td>
			<td>Request Date</td>
		</tr>
		<%
			SimpleDateFormat dateFormatForm = new SimpleDateFormat("dd/MM/yyyy"); // yyyy-MM-dd
			Map data = null;
			data = (Map) request.getAttribute("cancelContractFormResult");
			Set<String> mKey = data.keySet();
			if (data != null) {
				for (String k : mKey) {
					Map<String, Object> subData = (Map<String, Object>) data
							.get(k.toString());
					CancelContractForm a = (CancelContractForm) subData
							.get("cancelContractForm");
					// 			System.out.println(a.toString()+"\r\n");
					/*a.getServiceContractNo()*/
					//a.getRentalContractNo()
					String serviceOrderNo = "";
					String rentalContractNo = "";
					String lhContractNo = "";
					String serialNo = "";
					String model = "";
					String customerName = "";
					String customerCode = "";
					String customerAddress = "";
					String customerContact = "";
					String customerSection = "";
					String customerTelephone = "";
					String cancelContractDetail = "";
					String requestCloseMeter = "";
					String closeMeterDate = "";
					String requestEffectCancel = "";
					String effectCancelDate = "";
					String requestReturnMachine = "";
					String returnMachineDate = "";
					String requestByName = "";
					String requestExtension = "";
					String requestDepartment = "";
					String requestSection = "";
					String requestPosition = "";
					String requestManager = "";
					String requestTelephone = "";
					String requestDate = "";
					if (a != null) {
						serviceOrderNo = (a.getServiceContractNo() != null)? a.getServiceContractNo():"";
						rentalContractNo = (a.getRentalContractNo()!= null)? a.getRentalContractNo():"";
						lhContractNo = (a.getLnHContract()!=null)? a.getLnHContract():"";
						serialNo = (a.getMachineId() != null)? a.getMachineId():"";
						model = (a.getMachineModel() != null)? a.getMachineModel():"";
						customerName = (a.getCustomerName()!=null)? a.getCustomerName():"";
						customerCode = (a.getCustomerCode()!=null)? a.getCustomerCode():"";
						customerAddress = (a.getCustomerAddress()!=null)? a.getCustomerAddress():"";
						customerContact = (a.getCustomerContact() != null)? a.getCustomerContact():"";
						customerSection = (a.getCustomerSection() != null)? a.getCustomerSection():"";
						customerTelephone = (a.getCustomerTelephone() != null)? a.getCustomerTelephone():"";
						requestCloseMeter = (a.getDoCloseMeter() != null)? a.getDoCloseMeter().toString():"";
						closeMeterDate = (a.getCloseMeterDate() != null)? dateFormatForm.format(a.getCloseMeterDate()):"";
						requestEffectCancel = (a.getDoEffectCancel() != null)? a.getDoEffectCancel().toString():""; 
						effectCancelDate = (a.getEffectCancelDate() != null)? dateFormatForm.format(a.getEffectCancelDate()):"";
						requestReturnMachine = (a.getDoReturnMachine() != null)? a.getDoReturnMachine().toString():"";
						returnMachineDate = (a.getReturnMachineDate() != null)? dateFormatForm.format(a.getReturnMachineDate()):"";
						requestByName = (a.getRequestName() != null)? a.getRequestName():"";
						requestDepartment = (a.getRequestDepartment() != null)? a.getRequestDepartment():"";
						requestExtension = (a.getRequestExtensionNo() != null)? a.getRequestExtensionNo():"";
						requestManager = (a.getRequestManager() != null)? a.getRequestExtensionNo():"";
						requestPosition = (a.getRequestMemo() != "")? a.getRequestPosition():"";
						requestSection = (a.getRequestSection() != "")? a.getRequestSection():"";
// 						requestTelephone = (a.get)
						requestDate = (a.getRequestDate() != null)? dateFormatForm.format(a.getRequestDate()):"";
					}
		%>
		<tr>
			<td height="30"><%=serviceOrderNo%></td>
			<td><%=rentalContractNo%></td>
			<td><%=lhContractNo%></td>
			<td width="200"><%=serialNo %></td>
			<td width="300"><%=model %></td>
			<td><%=customerCode%></td>			
			<td><%=customerName%></td>
			<td><%=customerAddress%></td>
			<td><%=customerContact%></td>
			<td><%=customerSection %></td>
			<td><%=customerTelephone %></td>
			<td><%=cancelContractDetail%></td>
			<td><%=requestCloseMeter %></td>
			<td><%=closeMeterDate %></td>
			<td><%=requestEffectCancel %></td>
			<td><%=effectCancelDate %></td>
			<td><%=requestReturnMachine %></td>
			<td><%=returnMachineDate %></td>
			<td><%=requestByName %></td>
			<td><%=requestExtension %></td>
			<td><%=requestDepartment %></td>
			<td><%=requestSection %></td>
			<td><%=requestPosition %></td>
			<td><%=requestManager %></td>
			<td><%=requestTelephone %></td>
			<td><%=requestDate %></td>
		</tr>
		<%
				}
			} else {
		%>
		<tr>
			<td>No Data</td>
		</tr>
		<%
			}
		%>
	</table>	
</body>
</html>