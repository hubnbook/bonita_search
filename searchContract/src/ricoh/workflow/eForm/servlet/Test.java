package ricoh.workflow.eForm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeFactory;

import ricoh.i2e20.library.datastore.exceptions.SystemUnavailableException;
import ricoh.i2e20.library.model.BOSActivityInstance;
import ricoh.i2e20.library.model.BOSPVMExecution;
import ricoh.i2e20.library.model.BOSProcessInstance;
import ricoh.i2e20.library.model.BOSVariable;
import ricoh.i2e20.library.model.exceptions.BOSActivityInstanceNotFound;
import ricoh.i2e20.library.model.exceptions.BOSProcessInstanceNotFound;
import ricoh.i2e20.library.model.exceptions.BOSVariableNotFound;
import ricoh.workflow.eForm.model.CancelContractForm;

/**
 * Servlet implementation class Test
 */
public class Test extends HttpServlet {
	private static final long serialVersionUID = 12345L;

	/**
	 * Default constructor.
	 */
	public Test() {
		super();
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
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub

		Map formResultList = null;

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		pw.println("<html>");
		pw.println("<head><title></title></head>");
		pw.println("<body>");
		pw.println("Test");

		BOSProcessInstance instance = new BOSProcessInstance();
		BOSPVMExecution execute = new BOSPVMExecution();
		/*
		 * Long databaseId = (long) 1; instance.setDatabaseId(databaseId);
		 * 
		 * pw.println(databaseId.toString());
		 */
		pw.println("<table border=\"1\">");
		pw.println("<tr>");
		pw.println("<td>DBID</td>");
		pw.println("<td>INST_UUID</td>");
		pw.println("<td>PROC_UUID</td>");
		pw.println("</tr>");
		try {
			ArrayList a = (ArrayList) BOSProcessInstance
					.listByBOSProcessInstancePeriodLastUpdateWithLimitSort(
							instance, null, null, null, null, null);

			BOSActivityInstance activityInstance = new BOSActivityInstance();
			String sortCondition = null;
			Integer limitBegin = null;
			Date endLastUpdate = null;
			Integer numDataOnShow = null;
			Date startLastUpdate = null;

			ArrayList actInst = (ArrayList) BOSActivityInstance
					.listByBOSActivityInstancePeriodLastUpdateWithLimitSort(
							activityInstance, startLastUpdate, endLastUpdate,
							limitBegin, numDataOnShow, sortCondition);

			for (int i = 0; i < a.size(); i++) {
				BOSProcessInstance instanceResult = (BOSProcessInstance) a
						.get(i);
				pw.println("<tr>");
				pw.println("<td>" + instanceResult.getDatabaseId() + "</td>");
				pw.println("<td>" + instanceResult.getInstanceUUID() + "</td>");
				pw.println("<td>" + instanceResult.getProcessUUID() + "</td>");
				pw.println("</tr>");
			}

			BOSVariable variable = new BOSVariable();
			ArrayList varList = (ArrayList) BOSVariable
					.listByBOSVariableWithLimitSort(variable, limitBegin,
							numDataOnShow, sortCondition);

			CancelContractForm form = new CancelContractForm();

			// System.out.println("Act List : "+actInst.toString()+"\r\n");
			// System.out.println("Var List : "+varList.toString()+"\r\n");
			if (actInst != null && varList != null) {

				formResultList = CancelContractForm
						.listContractFromBOSActivityInstanceList(actInst,
								varList);

				// System.out.println("Output Cancel Contract Form: "
				// + CancelContractForm
				// .listContractFromBOSActivityInstanceList(
				// actInst, varList).toString());
			}

		} catch (SystemUnavailableException e) {

			e.printStackTrace();
		} catch (BOSVariableNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BOSActivityInstanceNotFound e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			pw.println("<tr>");
			pw.println("<td colspan=\"3\">Not Found</td>");
			pw.println("</tr>");
		} catch (BOSProcessInstanceNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		pw.println("</table>");

		// System.out.println(formResultList.keySet());

		Set<String> k = formResultList.keySet();
		int i = 0;
		for (String key : k) {
			Map<String, Object> map = (Map<String, Object>) formResultList
					.get(key);
			 System.out.println(map.keySet());
			BOSActivityInstance actInstance = (BOSActivityInstance) map
					.get("act_inst");
			CancelContractForm form = null;
			form = (CancelContractForm) map
					.get("cancelContractForm");
			pw.println(i + ". UUID: " + actInstance.getInstanceUUID() + ", ");
			if (form != null) {
				System.out.println(form.toString() + "\r\n");
				pw.println("SO: " + form.getServiceContractNo() + "<br>");
			}else{
				pw.println("SO: <br>");
			}
			i++;
		}

		// for(String key: formResultList.keySet()){
		//
		// }

		pw.println("</body>");
		pw.println("</html>");
	}

}
