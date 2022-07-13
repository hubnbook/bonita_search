package ricoh.workflow.eForm.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ricoh.i2e20.library.datastore.exceptions.SystemUnavailableException;
import ricoh.i2e20.library.model.BOSActivityDefinition;
import ricoh.i2e20.library.model.BOSActivityInstance;
import ricoh.i2e20.library.model.BOSProcessDefinition;
import ricoh.i2e20.library.model.BOSVariable;
import ricoh.i2e20.library.model.exceptions.BOSActivityDefinitionNotFound;
import ricoh.i2e20.library.model.exceptions.BOSActivityInstanceNotFound;
import ricoh.i2e20.library.model.exceptions.BOSProcessDefinitionNotFound;
import ricoh.i2e20.library.model.exceptions.BOSVariableNotFound;
import ricoh.workflow.eForm.model.CancelContractForm;

/**
 * Servlet implementation class ProcessGenExportFile
 */
public class ProcessGenExportFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessGenExportFile() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub

		String startDate = (String) request.getParameter("startDate");
		String endDate = (String) request.getParameter("endDate");
		try { // Try For PrintWriter

			// rWriter.println(request.getAttributeNames().toString());

			if (startDate != null && endDate != "") {

				java.util.Date date = new java.util.Date();
				SimpleDateFormat format = new SimpleDateFormat(
						"yyyyMMdd-hhmmss");

				// response.setContentType("application/octet-stream");
				response.setContentType("text/csv");
				response.setCharacterEncoding("TIS-620");

				PrintWriter rWriter = response.getWriter();

				response.setHeader(
						"Content-Disposition",
						"attachment; filename=e-form_report_"
								+ format.format(date) + ".csv");

				// rWriter.println(startDate + " " + endDate);

				Integer limitBegin = null;
				Integer numDataOnShow = null;
				String sortCondition = null;
				String[] keyNameList = null;
				BOSVariable variable = null;
				ArrayList result = null;
				Map formResultList = null;

				Date startLastUpdate = null;
				Date endLastUpdate = null;
				try {

					BOSActivityInstance activityInstanceReq = new BOSActivityInstance();

					activityInstanceReq.setActivityState("READY");

					if (request.getParameter("startDate") != null
							|| request.getParameter("endDate") != null) {
						String startDateTemp = request
								.getParameter("startDate");
						String endDateTemp = request.getParameter("endDate");
						String[] sD = startDateTemp.split("-");
						String[] eD = endDateTemp.split("-");

						Integer startYear = Integer.parseInt(sD[2]) - 1900;
						Integer startMonth = Integer.parseInt(sD[1]) - 1;
						java.util.Date tmp = new java.util.Date();
						tmp.setDate(Integer.parseInt(sD[0]));
						tmp.setMonth(startMonth);
						tmp.setYear(startYear);

						startLastUpdate = new Date(tmp.getTime());

						Integer endYear = Integer.parseInt(eD[2]) - 1900;
						Integer endMonth = Integer.parseInt(eD[1]) - 1;
						tmp = new java.util.Date();
						tmp.setDate(Integer.parseInt(eD[0]));
						tmp.setMonth(endMonth);
						tmp.setYear(endYear);

						endLastUpdate = new Date(tmp.getTime());

						// System.out.println(startLastUpdate.toLocaleString()
						// + " " + endLastUpdate.toLocaleString());
					}

					ArrayList actInst = new ArrayList();
					// actInst = (ArrayList) BOSActivityInstance
					// .listByBOSActivityInstancePeriodLastUpdateWithLimitSort(
					// activityInstanceReq, startLastUpdate,
					// endLastUpdate, limitBegin, numDataOnShow,
					// null);

					actInst = (ArrayList) BOSActivityInstance
							.listJoinActivityDefinition(activityInstanceReq,
									null, null, startLastUpdate, endLastUpdate,
									null, null, null);

					ArrayList<Long> instanceIdListTmp = new ArrayList<Long>();
					for (int i = 0; i < actInst.size(); i++) {
						BOSActivityInstance a = (BOSActivityInstance) actInst
								.get(i);

						// rWriter.println("INST: " + i + ", "
						// + a.getInstanceDatabaseID());
						if (instanceIdListTmp.contains(a
								.getInstanceDatabaseID())) {
							// rWriter.println("IS EXIST");
						} else {
							instanceIdListTmp.add(a.getInstanceDatabaseID());
							// rWriter.println("IS NOT EXIST");
						}
						// System.out.println("INSTANCE DBID: " +
						// a.getInstanceDatabaseID());
						// System.out.println("PROCESS DBID: " +
						// a.getProcessDatabseID());
					}
					Long[] instanceIDListReq = new Long[instanceIdListTmp
							.size()];
					for (int k = 0; k < instanceIdListTmp.size(); k++) {
						instanceIDListReq[k] = instanceIdListTmp.get(k);
					}
					instanceIdListTmp = null;

					variable = new BOSVariable();
					ArrayList varList = null;

					String[] keyName = { "requestNumber", "request_Name",
							"requestorName", "serviceContractNo",
							"rentContractNo", "lhContractNo", "customerCode",
							"customerName", "machineID", "machineModel",
							"customerAddress", "customerTel",
							"customerContact", "select_cancel_detail",
							"returnMachineDate", "doReturnMachine",
							"closeMeterDate", "doCloseMeter",
							"effectCancelDate", "doActionCancel",
							"request_Memo", "fieldServiceSupervisor",
							"fieldServiceSupervisorMemo", "request_Date" };

					varList = (ArrayList) BOSVariable
							.listByBOSVarableKeyNameListPeriodUpdateInstanceIdListWithLimitSort(
									variable, keyName, null, null,
									instanceIDListReq, null, null, null);

					HashMap<Long, HashMap<String, String>> varMap = new HashMap<Long, HashMap<String, String>>();

					for (int i = 0; i < varList.size(); i++) {
						BOSVariable v = (BOSVariable) varList.get(i);
						String txt = v.getStringValue() + v.getTextValue();
						HashMap h = new HashMap<String, String>();

						HashMap<String, String> dExist = varMap.get(v
								.getInstanceId());
						if (dExist != null) {
							String sValue = "";
							if (v.getStringValue() != "null") {
								sValue += v.getStringValue();
							} else if (!v.getDateValue().equals(null)) {
								sValue += v.getDateValue().toString();
							} else {
								sValue += v.getTextValue();
							}
							dExist.put(v.getKeyName(), sValue);
							varMap.put(v.getInstanceId(), dExist);
						} else {
							String sValue = "";
							if (v.getStringValue() != "null") {
								sValue += v.getStringValue();
							} else if (!v.getDateValue().equals(null)) {
								sValue += v.getDateValue().toString();
							} else {
								sValue += v.getTextValue();
							}
							h.put(v.getKeyName(), sValue);
							varMap.put(v.getInstanceId(), h);
						}
					}

					BOSProcessDefinition processDefinition = new BOSProcessDefinition();
					ArrayList processDefListTmp = new ArrayList();
					processDefListTmp = (ArrayList) BOSProcessDefinition
							.listByBOSProcessDefinitionWithLimitSort(
									processDefinition, null, null, null);

					HashMap<Long, BOSProcessDefinition> proccesDefList = new HashMap<Long, BOSProcessDefinition>();

					for (int i = 0; i < processDefListTmp.size(); i++) {
						BOSProcessDefinition p = (BOSProcessDefinition) processDefListTmp
								.get(i);
						proccesDefList.put(p.getDatabaseId(), p);
						// System.out.println("DBID: " + p.getDatabaseId() +
						// ", " + p.getLabel());

					}
					processDefListTmp = null;

					CancelContractForm form = new CancelContractForm();

					// System.out.println("Act List : "+actInst.toString()+"\r\n");
					// System.out.println("Var List : "+varList.toString()+"\r\n");

					Long[] databaseIdList = null;
					//

					File tmpFile = File.createTempFile("e-Form-", ".tmp");

					FileWriter fw = new FileWriter(tmpFile);

					try {

						// System.out.println(tmpFile.getAbsolutePath());
						// tmpFile.setExecutable(true);
						// tmpFile.setReadable(true);
						// tmpFile.setWritable(true);

						fw.write("Request-No, Request-Type, Request-On-Step, Service-Contract, Rental-Contract, LH-Contract, User-Code, Request-Name, Customer-Code, Customer-Name, "
								+ "Customer-Address, Contact-Name, Contact-Tel, Cancel-Detail, Serial-No, M/C Model, M/C Return, Return M/C Date, "
								+ "Close Meter, Close Meter Date, Effect Cancel, Effect Cancel Date,"
								+ "Field Service Sup. Name, Field Service Sup. Memo,"
								+ "Request Memo, Request Date\r\n");
						// fw.flush();

						for (int i = 0; i < actInst.size(); i++) {
							BOSActivityInstance a = (BOSActivityInstance) actInst
									.get(i);
							HashMap<String, String> m = varMap.get(a
									.getInstanceDatabaseID());

							//
							if (m != null) {
								String requestNo = m.get("requestNumber");
								if (requestNo == "null") {
									requestNo = "";
								}

								Long pID = a.getProcessDatabseID();

								BOSProcessDefinition bosPD = proccesDefList
										.get(pID);

								String processName = pID.toString() + "";
								if (bosPD != null) {
									processName = bosPD.getLabel();
								}
								String activityLabel = (a.getActivityLabel() == null) ? ""
										: a.getActivityLabel();
								String serviceNo = (m.get("serviceContractNo") == null) ? ""
										: m.get("serviceContractNo");
								String rentContractNo = (m
										.get("rentContractNo") == null) ? ""
										: m.get("rentContractNo");
								String lhContractNo = (m.get("lhContractNo") == null) ? ""
										: m.get("lhContractNo");
								String userId = (a.getUserID() == null) ? ""
										: a.getUserID();
								String requestName = (m.get("requestorName") == null) ? ""
										: m.get("requestorName");
								if (requestName == "") {
									requestName = (m.get("request_Name") == null) ? ""
											: m.get("request_Name");
								}
								String customerCode = (m.get("customerCode") == null) ? ""
										: m.get("customerCode");
								String customerName = (m.get("customerName") == null) ? ""
										: m.get("customerName");
								String customerAddress = (m
										.get("customerAddress") == null) ? ""
										: m.get("customerAddress");
								String customerContact = (m
										.get("customerContact") == null) ? ""
										: m.get("customerContact");
								String customerTelephone = (m
										.get("customerTel") == null) ? "" : m
										.get("customerTel");
								String cancelDetail = (m
										.get("select_cancel_detail") == null) ? ""
										: m.get("select_cancel_detail");
								String machineID = (m.get("machineID") == null) ? ""
										: m.get("machineID");
								String machineModel = (m.get("machineModel") == null) ? ""
										: m.get("machineModel");
								String returnMachineDate = (m
										.get("returnMachineDate") == null) ? ""
										: m.get("returnMachineDate");
								returnMachineDate = (returnMachineDate == "null") ? ""
										: returnMachineDate;
								String doReturnMachine = (m
										.get("doReturnMachine") == null) ? ""
										: m.get("doReturnMachine");
								String closeMeterDate = (m
										.get("closeMeterDate") == null) ? ""
										: m.get("closeMeterDate");
								closeMeterDate = (closeMeterDate == "null") ? ""
										: closeMeterDate;
								String doCloseMeter = (m.get("doCloseMeter") == null) ? "No"
										: m.get("doCloseMeter");
								String fieldServiceSup = (m
										.get("fieldServiceSupervisor") == null) ? ""
										: m.get("fieldServiceSupervisor");
								String fieldServiceSupMemo = (m
										.get("fieldServiceSupervisorMemo") == null) ? ""
										: m.get("fieldServiceSupervisorMemo");
								String requestDate = (m.get("request_Date") == null) ? ""
										: m.get("request_Date");
								String requestMemo = (m.get("request_Memo") == null) ? ""
										: m.get("request_Memo");
								String effectCancelDate = (m
										.get("effectCancelDate") == null) ? ""
										: m.get("effectCancelDate");
								effectCancelDate = (effectCancelDate == "null") ? ""
										: effectCancelDate;
								String doActionCancel = (m
										.get("doActionCancel") == null) ? ""
										: m.get("doActionCancel");

								String textLine = "";
								textLine += "\"" + requestNo + "\"";
								textLine += ",\"" + processName + "\"";
								textLine += ",\"" + activityLabel + "\"";
								textLine += "," + "\"" + serviceNo + "\"";
								textLine += "," + "\"" + lhContractNo + "\"";
								textLine += "," + "\"" + rentContractNo + "\"";
								textLine += "," + "\"" + userId + "\"";
								textLine += "," + "\"" + requestName + "\"";
								textLine += "," + "\"" + customerCode + "\"";
								textLine += "," + "\"" + customerName + "\"";
								textLine += "," + "\"" + customerAddress + "\"";
								textLine += "," + "\"" + customerContact + "\"";
								textLine += "," + "\"" + customerTelephone
										+ "\"";
								textLine += "," + "\"" + cancelDetail + "\"";
								textLine += "," + "\"" + machineID + "\"";
								textLine += "," + "\"" + machineModel + "\"";
								textLine += "," + "\"" + doReturnMachine + "\"";
								textLine += "," + "\"" + returnMachineDate
										+ "\"";
								textLine += "," + "\"" + doCloseMeter + "\"";
								textLine += "," + "\"" + closeMeterDate + "\"";
								textLine += "," + "\"" + doActionCancel + "\"";
								textLine += "," + "\"" + effectCancelDate
										+ "\"";
								textLine += "," + "\"" + fieldServiceSup + "\"";
								textLine += "," + "\"" + fieldServiceSupMemo
										+ "\"";
								textLine += "," + "\"" + requestMemo + "\"";
								textLine += "," + "\"" + requestDate + "\"";

								// textLine += "," + m.get("request_Name");
								// textLine += "," + m.get("customerCode");
								// fw.write(textLine);
								// fw.write(textLine);
								// rWriter.write(textLine);
								// String s = new
								// String(textLine.getBytes("UTF-8"),"TIS-620");

								byte[] textLineByte = textLine
										.getBytes("TIS-620");

								String textOutput = new String(textLineByte,
										"TIS-620");

								fw.write(textOutput + "\r\n");
								fw.flush();
							}
						}
						// fw.write(response.getContentType() + "\r\n");
						// fw.flush();
						fw.close();
						// fw.write("");
						//
						// fw.write("TEST: " + startLastUpdate.toString()
						// + endLastUpdate.toString());
						// fw.flush();
						// fw.close();

						FileReader fr = new FileReader(tmpFile);
						//
						BufferedReader br = new BufferedReader(fr);
						String s;
						while ((s = br.readLine()) != null) {
							rWriter.println(s);
							rWriter.flush();
						}
						fr.close();

						// request.setAttribute("cancelContractFormResult",
						// formResultList);

						// rWriter.println("TEST : " +
						// startLastUpdate.toString()
						// + endLastUpdate.toString() + "");
						// request.getRequestDispatcher("WEB-INF/jsp/export_html.jsp")
						// .include(request, response);
						// rWriter.close();
					} catch (IOException eIn) {

					} finally {
						fw.close();
						tmpFile.deleteOnExit();
					}
				} catch (BOSActivityInstanceNotFound e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					// rWriter.println("Not found Data on "
					// + startLastUpdate.toString() + " "
					// + endLastUpdate.toString());
					// rWriter.println("Not found Data on "
					// + startLastUpdate.getTime() + " "
					// + endLastUpdate.getTime());

					// request.setAttribute("numDataAll", 0);
					// request.setAttribute("totalPage", 0);

					// response.setHeader("Conten-Type", "text/html");
					// request.getRequestDispatcher("WEB-INF/jsp/export_form.jsp")
					// .include(request, response);
				} catch (BOSVariableNotFound e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					// rWriter.println("<br>BOSVariableNotFound<br>");
					e.printStackTrace();
					java.util.Date now = new java.util.Date();
					System.err.println("Error on time: " + now.toLocaleString()
							+ "\r\n");
					System.err.println(e.getMessage());
				} catch (SystemUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					java.util.Date now = new java.util.Date();
					System.err.println("SystemUnavailableException Error on "
							+ now.toLocaleString() + "\r\n");
					System.err.println(e.getMessage());
					// } catch (BOSProcessInstanceNotFound e) {
					// // TODO Auto-generated catch block
					// java.util.Date now = new java.util.Date();
					// System.out.println("BOSProcessInstanceNotFound Error on "
					// + now.toLocaleString() + "\r\n");
					// System.out.println(e.getMessage());
					// e.printStackTrace();
					// } catch (BOSVariableUpdateNotFound e) {
					// // TODO Auto-generated catch block
					// e.printStackTrace();
				} catch (BOSProcessDefinitionNotFound e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.destroy();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			java.util.Date now = new java.util.Date();
			System.out.println("IOException Error on " + now.toLocaleString()
					+ "\r\n");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
