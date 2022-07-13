package ricoh.workflow.eForm.servlet;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.h2.util.TempFileDeleter;

import ricoh.i2e20.library.datastore.exceptions.SystemUnavailableException;
import ricoh.i2e20.library.model.BOSActivityInstance;
import ricoh.i2e20.library.model.BOSProcessDefinition;
import ricoh.i2e20.library.model.BOSProcessInstance;
import ricoh.i2e20.library.model.BOSVariable;
import ricoh.i2e20.library.model.BOSVariableUpdate;
import ricoh.i2e20.library.model.exceptions.BOSActivityInstanceNotFound;
import ricoh.i2e20.library.model.exceptions.BOSProcessInstanceNotFound;
import ricoh.i2e20.library.model.exceptions.BOSVariableNotFound;
import ricoh.i2e20.library.model.exceptions.BOSVariableUpdateNotFound;
import ricoh.workflow.eForm.model.CancelContractForm;

/**
 * Servlet implementation class ProcessExport
 */
public class ProcessExport extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TempFileDeleter tempFileDeleter;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessExport() {
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
		// TODO Auto-generated method stub

		String startDate = (String) request.getParameter("startDate");
		String endDate = (String) request.getParameter("endDate");
		try {
			PrintWriter rWriter = response.getWriter();

			// rWriter.println(request.getAttributeNames().toString());

			response.setHeader("Conten-Type", "text/html");
			request.getRequestDispatcher("WEB-INF/jsp/export_form.jsp")
					.include(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			java.util.Date now = new java.util.Date();
			System.out.println("ServletException Error on "
					+ now.toLocaleString() + "\r\n");
			System.out.println(e.getMessage());
			e.printStackTrace();
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
