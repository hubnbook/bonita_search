package ricoh.workflow.eForm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;

import javax.lang.model.element.Element;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ricoh.i2e20.library.datastore.exceptions.SystemUnavailableException;
import ricoh.i2e20.library.model.BOSActivityInstance;
import ricoh.i2e20.library.model.BOSProcessDefinition;
import ricoh.i2e20.library.model.BOSVariable;
import ricoh.i2e20.library.model.BOSVariableUpdate;
import ricoh.i2e20.library.model.exceptions.BOSActivityInstanceNotFound;
import ricoh.i2e20.library.model.exceptions.BOSProcessDefinitionNotFound;
import ricoh.i2e20.library.model.exceptions.BOSVariableNotFound;

/**
 * Servlet implementation class ProcessSearch
 */
public class ProcessSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessSearch() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) {

		// response.setHeader("Content-Type", "text/html; charset=UTF-8");
		// try {
		response.setContentType("text/csv; charset=TIS-620");
		// response.setCharacterEncoding("UTF-8");
		// } catch (UnsupportedEncodingException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }

		BOSProcessDefinition bosProcessDefinition = new BOSProcessDefinition();

		Integer pageReq = 1;

		Integer limitBegin = 0;
		Integer numDataOnShow = 30;
		String sortCondition = " DBID_ DESC";

		Integer page = null;

		if (request.getParameter("page") != null) {
			page = Integer.valueOf(request.getParameter("page"));
		}
		if (request.getParameter("numDataOnShow") != null) {
			numDataOnShow = Integer.valueOf(request
					.getParameter("numDataOnShow"));
		}

		if (page != null && page > 0) {
			if (page == 1) {
				limitBegin = 0;
			} else {
				limitBegin = page - 1;
			}
			limitBegin = limitBegin * numDataOnShow;
		}

		// bosProcessDefinition.setState("ENABLED");

		String searchText = request.getParameter("searchText");
		String processDef = request.getParameter("processDef");
		String monthOnRequest = request.getParameter("monthReq");
		String yearOnRequest = request.getParameter("yearReq");

		String procSortCondition = "";// " LABEL_ ASC";

		ArrayList result = new ArrayList();

		String message = "OK_2.2";

		Date startUpdateDate = null;
		Date endUpdateDate = null;
		
		//
		try {
			PrintWriter out = response.getWriter();
			try {
				// out.println("<h1>" + message + "</h1>");

				ArrayList<BOSProcessDefinition> processDefList = (ArrayList<BOSProcessDefinition>) BOSProcessDefinition
						.listByBOSProcessDefinitionWithLimitSort(
								bosProcessDefinition, null, null,
								procSortCondition);

				// BOSProcessDefinitionBean processDefBean = new
				// BOSProcessDefinitionBean();
				// processDefBean.setProcessDefinitionLsit(processDefList);

				if (yearOnRequest != null && yearOnRequest != "") {
					
					
					
					Integer startDay = 1;
					Integer endDay = 28;
					Integer monthReq = 1;
					Integer startMonthReq = 1;
					Integer endMonthReq = 12;
					Integer yearReq = Integer.parseInt(yearOnRequest);
					GregorianCalendar calendar = null;
					if (monthOnRequest != null && monthOnRequest != "") {
						startMonthReq = Integer.parseInt(monthOnRequest) - 1;
						endMonthReq = startMonthReq;
						calendar = new GregorianCalendar(yearReq, endMonthReq,
								1);
					} else {
						calendar = new GregorianCalendar(yearReq, endMonthReq,
								1);
					}

					endDay = calendar.getActualMaximum(calendar.DAY_OF_MONTH);

					yearReq = yearReq - 1900;

					if (monthOnRequest != null) {

					} else {
						endDay = 31;
					}
					startUpdateDate = new Date(yearReq, startMonthReq, startDay);
					// startUpdateDate.setHours(0);
					// startUpdateDate.setMinutes(0);
					endUpdateDate = new Date(yearReq, endMonthReq, endDay);
					// endUpdateDate.setHours(12);
					// endUpdateDate.setMinutes(0);
					 System.out.println(startUpdateDate.toLocaleString()
					 + " <- Start : End -> "
					 + endUpdateDate.toLocaleString() + "\r\n");
//					out.write(startUpdateDate.toLocaleString()
//							+ " <- Start : End -> "
//							+ endUpdateDate.toLocaleString() + "\r\n");
					request.setAttribute("startUpdateDate",
							startUpdateDate.toLocaleString());
					request.setAttribute("endUpdateDate",
							endUpdateDate.toLocaleString());
				}

				try {
					Long[] instanceIdList = null;
					BOSVariable variableRefer = new BOSVariable();
					if (searchText != null && !searchText.isEmpty()) {
						searchText = "";
						// variableRefer.setStringValue(searchText);
						// variableRefer.setTextValue(searchText);
						out.println("CharacterEncoding: "
								+ response.getCharacterEncoding());
						out.println("Search TEXT: " + searchText);
						System.out.println("Search TEXT: " + searchText);

						// String variableSortCondition = " bnv.DBID_ DESC";

						// String[] keyNameList = {};
						String[] keyNameList = { "serviceContractNo",
								"rentContractNo", "lhContractNo", "machineID",
								"machineModel", "customerCode", "customerName",
								"customerAddress", "customerContact" };

						startUpdateDate = null;
						endUpdateDate = null;
						// variable = null;
						ArrayList variableList = new ArrayList();
						variableList = (ArrayList) BOSVariable
								.listByBOSVarableKeyNameListPeriodUpdateInstanceIdListWithLimitSort(
										variableRefer, keyNameList,
										startUpdateDate, endUpdateDate, null,
										0, 10000, null);

						// variableList =
						// (ArrayList)BOSVariable.listByBOSVariableWithLimitSort(variableRefer,
						// null, null, null);

						//
						BOSVariableUpdate variableUpdateReq = new BOSVariableUpdate();
						//
						// instanceIdList = new Long[variableList.size()];
						System.out.println("Num of Variable ID List : " + 0
								+ "\r\n");
						for (int i = 0; i < variableList.size(); i++) {
							BOSVariable a = (BOSVariable) variableList.get(i);
							// variableIDList[i] = a.getDatabaseId();
							// if (!Arrays.asList(instanceIdList).contains(
							// (long) a.getInstanceId())) {

							instanceIdList[i] = a.getInstanceId();
							// }
							//
							// //
							System.out.println("instanceIdList ID List : "
									+ instanceIdList[i] + "\r\n");
							//
						}
						// for (int j = 0; j < instanceIdList.length; j++) {
						// write.println("Activity Instance ID List : "
						// + instanceIdList[j] + "<br>");
						// }
					}
					BOSActivityInstance activityInstanceReq = new BOSActivityInstance();

					// Date startLastUpdate = null;
					// Date endLastUpdate = null;
					activityInstanceReq.setActivityState("READY");
					if (processDef != null && !processDef.isEmpty()) {
						activityInstanceReq.setProcessUUID(processDef);
					}
					// activityInstanceReq = null;
					// Object in variable of function, it can not be null
					Long[] databaseIDList = null;
					result = (ArrayList) BOSActivityInstance
							.listByBOSActivityInstanceDatabaseIDListInstanceDatabaseIDListPeriodLastUpdateWithLimitSort(
									activityInstanceReq, null, null,
									startUpdateDate, endUpdateDate, limitBegin,
									numDataOnShow, sortCondition);
					request.setAttribute("instanceList", result);

					Integer numResultAll = BOSActivityInstance
							.countByBOSActivityInstanceDatabaseIDListInstanceDatabaseIdListPeriodLastUpdate(
									activityInstanceReq, null, null,
									startUpdateDate, endUpdateDate);

					request.setAttribute("numDataAll", numResultAll);

					Double calTotalPage = 0.0;
					if (numResultAll.doubleValue() > 0) {
						calTotalPage = (double) (numResultAll.doubleValue() / numDataOnShow
								.doubleValue());
					}
					Double totalPage = Math.ceil(calTotalPage.doubleValue());
					// System.out.println(" Total Page: " +
					// totalPage.doubleValue() + "\r\n");
					request.setAttribute("totalPage", totalPage.intValue());

				} catch (BOSActivityInstanceNotFound e) {
					// } catch (BOSActivityInstanceNotFound e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					request.setAttribute("numDataAll", 0);
					request.setAttribute("totalPage", 0);
					java.util.Date now = new java.util.Date();
					System.out.println("BOSActivityInstanceNotFound Error on "
							+ now.toLocaleString() + "\r\n");
				} catch (BOSVariableNotFound e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					java.util.Date now = new java.util.Date();
					System.out.println("BOSVariableNotFound Error on "
							+ now.toLocaleString() + "\r\n");
					// } catch (BOSVariableNotFound e) {
					// // TODO Auto-generated catch block
					//
					// java.util.Date now = new java.util.Date();
					// out.println("BOSVariableNotFound Error on "
					// + now.toLocaleString());
					// System.out.println("BOSVariableNotFound Error on "
					// + now.toLocaleString() + "\r\n");
					// e.printStackTrace();
				}

				request.setAttribute("processDefList", processDefList);
				request.setAttribute("numDataOnShow", numDataOnShow);
				
				request.setAttribute("testDate", "rrrrrr");

				RequestDispatcher rd = request
						.getRequestDispatcher("WEB-INF/jsp/search.jsp");
				rd.forward(request, response);

			} catch (BOSProcessDefinitionNotFound e) {

				java.util.Date now = new java.util.Date();
				System.out.println("BOSProcessDefinitionNotFound Error on "
						+ now.toLocaleString() + "\r\n");
				System.out.println(e.getMessage());
				out.println("BOSProcessDefinitionNotFound Error on "
						+ now.toLocaleString() + "</br>");
				// e.printStackTrace();
			} catch (ServletException e) {
				//
				java.util.Date now = new java.util.Date();
				System.err.println("ServletException Error on "
						+ now.toLocaleString() + "\r\n");
				System.err.println(e.getMessage());
				// out.println("ServletException Error on " +
				// now.toLocaleString()
				// + "</br>");
				e.printStackTrace();

			} catch (SystemUnavailableException e) {
				java.util.Date now = new java.util.Date();
				System.err.println("SystemUnavailableException Error on "
						+ now.toLocaleString() + "\r\n");
				System.err.println(e.getMessage());
				out.println("SystemUnavailableException Error on "
						+ now.toLocaleString() + "</br>");
				// e.printStackTrace();
				// PrintWriter out;

			}
		} catch (IOException e) {

			java.util.Date now = new java.util.Date();
			System.err.println("IOException Error on " + now.toLocaleString()
					+ "\r\n");
			System.err.println(e.getMessage());
			// e.printStackTrace();
		}
	}
	/*
	 * private void test() { BOSVariable variable = new BOSVariable(); String[]
	 * keyNameList = { "serviceContractNo", "rentContractNo", "lhContractNo",
	 * "machineID", "machineModel", "customerCode", "customerName",
	 * "customerAddress", "customerContact" };
	 * 
	 * // String[] keyNameList = null; //
	 * variable.setKeyName("serviceContractNo"); Integer startDay = 1; Integer
	 * endDay = 28; Integer monthReq = 3; monthReq = monthReq - 1; Integer
	 * yearReq = 2016; yearReq = yearReq - 1900; GregorianCalendar calendar =
	 * new GregorianCalendar(yearReq, monthReq, 1); endDay =
	 * calendar.getActualMaximum(calendar.DAY_OF_MONTH); Date startUpdateDate =
	 * new Date(yearReq, monthReq, startDay); Date endUpdateDate = new
	 * Date(yearReq, monthReq, endDay); // startUpdateDate.setHours(0); //
	 * startUpdateDate.setMinutes(0); try { ArrayList vOut = (ArrayList)
	 * BOSVariable
	 * .listByBOSVarableKeyNameListPeriodUpdateInstanceIdListWithLimitSort(
	 * variable, keyNameList, null, null, null, 0, 500000, null); //
	 * BOSVariable.listByBOSVariableWithLimitSort(variable, // 0, 1000, null);
	 * 
	 * System.out.println("num Variable :  " + vOut.size() + "\r\n"); } catch
	 * (BOSVariableNotFound e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); System.out.println(e.getMessage()); } catch
	 * (SystemUnavailableException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); System.out.println(e.getMessage()); } }
	 */
}
