<%@page import="java.text.DateFormat, java.text.SimpleDateFormat"%>
<%@page import="java.util.Date, java.util.ArrayList" %><%@page
	import="java.io.PrintWriter"%><%@page
	import="ricoh.i2e20.library.model.*"%><%@ page language="java"
	contentType="text/html; charset=TIS-620" pageEncoding="TIS-620"%><%@ page
	import="java.sql.*,java.util.*"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=TIS-620">
<title>Search Cancel Contract</title>
<link rel="stylesheet" type="text/css" href="css/test.css">
<link rel="stylesheet" type="text/css"
	href="Scripts/Bootstrap/css/bootstrap.css">
<script type="text/javascript" src="Scripts/Bootstrap/js/bootstrap.js"></script>
<script type="text/javascript">

function goPage(pageNo){
//	alert(pageNo);
	document.getElementById("pageReq").value = pageNo;
	document.getElementById("searchform").submit();
}

</script>
</head>
<body>
	<%
		String testDate = request.getParameter("testDate");	
	
		Integer numDataAll = 0;
		if (request.getAttribute("numDataAll") != null) {
			numDataAll = (Integer) request.getAttribute("numDataAll");
		}
		Integer totalPage = 1;
		if (request.getAttribute("totalPage") != null) {
			totalPage = (Integer) request.getAttribute("totalPage");
		}
		Integer numDataOnShow = 0;
		if (request.getAttribute("numDataOnShow") != null) {
			numDataOnShow = (Integer) request.getAttribute("numDataOnShow");
		}
		Integer rangePageShow = 15;
		Double half = Math.ceil(rangePageShow / 2);
		Integer halfOfRangeShow = half.intValue();
		Integer beginPage = 1;
		Integer endPageShow = totalPage;
		Integer pageRef = 1;

		if (request.getParameter("page") != null) {
			pageRef = Integer.valueOf(request.getParameter("page"));
		}
		if (rangePageShow < totalPage) {
			endPageShow = rangePageShow;
			if (pageRef > halfOfRangeShow.intValue()) {
				if (pageRef < totalPage) {
					if (pageRef < (totalPage - 1)) {
						beginPage = pageRef
								- (rangePageShow - halfOfRangeShow);
						endPageShow = beginPage + rangePageShow - 1;
					} else {
						beginPage = pageRef - (rangePageShow - 1);
						endPageShow = totalPage - 1;
					}
				} else {
					beginPage = pageRef - (rangePageShow);
					endPageShow = beginPage + rangePageShow - 1;
				}
			}
		}
		if (beginPage <= 1) {
			beginPage = 2;
		}
		Integer limitPageShow = beginPage + rangePageShow;
		if ((limitPageShow - 1) > totalPage) {
			limitPageShow = totalPage;
		}
		if (limitPageShow >= totalPage) {
			limitPageShow = totalPage;
		}
		
		String queryString = "";

		String searchText = "";
		if(request.getParameter("searchText") == null){
			searchText = request.getParameter("searchText");
		}
		if (searchText == null) {
			searchText = "";
		}else{
			queryString += "&searchText=" +searchText;
		}
		String processDef = request.getParameter("processDef");
		if (processDef == null) {
			processDef = "";
		}else{
			queryString += "&processDef=" +processDef;			
		}
		
		ArrayList process = new ArrayList();	
		if(request.getAttribute("processDefList") != null){
			process = (ArrayList) request.getAttribute("processDefList");
		}
		
		java.util.Date now = new java.util.Date();
		Integer monthReq = null;
		Integer yearReq = null;
		if(null == request.getParameter("monthReq")){
//			monthReq = now.getMonth() + 1;
			monthReq = 0;
		}else{
			if(!request.getParameter("monthReq").equals("")){
				monthReq = Integer.parseInt(request.getParameter("monthReq"));
			}else{
				monthReq = 0;
			}
			
		}
		if(null == request.getParameter("yearReq")){
//			yearReq = now.getYear() + 1900;
			yearReq = 0;
			monthReq = 0;
		}else{
			if(request.getParameter("yearReq") != ""){
				yearReq = Integer.parseInt(request.getParameter("yearReq"));
			}else{
				yearReq = 0;
			}			
		}
		
		String startUpdateDate = request.getParameter("startUpdateDate");
		if(request.getParameter("startUpdateDate") == null){
			startUpdateDate = "is null";
		}

	%>
	Date Request: <%=startUpdateDate%>
	<div class="container">
		<jsp:useBean id="date" class="java.util.Date" />
		Date :<%=date%><br>
		<h1>Search Cancel Contract Information</h1>
		<div class="col-md-8">
			<form action="" method="POST" id="searchform" role="form" onsubmit="">
				<input type="hidden" name="page" id="pageReq" value="<%=pageRef%>">
				<div class="form-group">
					<label for="email">Search:</label> <input type="text"
						class="form-control" name="searchText" id="searchText"
						value="<%=searchText%>">
				</div>
				<div class="form-group">
					<label>Contract Form</label> <select name="processDef"
						class="form-control">
						<option value="">== Select ==</option>
						<%
							if (process.size() > 0) {
								for (int i = 0; i < process.size(); i++) {
									BOSProcessDefinition a = (BOSProcessDefinition) process
											.get(i);
						%>
						<option value="<%=a.getProcessUUID()%>"
							<%if (processDef.equals(a.getProcessUUID())) {%> selected <%}%>><%=a.getLabel() + "" + a.getVersion()%></option>
						<%
								}
							}
						%>
					</select>
				</div>
				<div class="col-md-3">
				<div class="form-group">
				
					<label>Search Text in Month :</label>
					<select class="form-control" name="monthReq" id="monthReq">
						<option value="">All</option>
					<%
						for(int i = 1;i <=12;i++){
					%>
						<option value="<%=i%>" <% if(monthReq.equals(i)){%>selected<% } %>><%=i %></option>
					<% 
						}
					%>
					</select>
				</div>
				</div>
				<div class="col-md-3">
				<div class="form-group">
					<label>Search Text in Year :</label>
					<select class="form-control" name="yearReq" id="yearReq">
						<option value="">All</option>
					<%
						int currYear = now.getYear() + 1900;
						for(int i = currYear;i > 2012;i--){
					%>
						<option value="<%=i%>" <% if(yearReq.equals(i)){%>selected<% } %>><%=i %></option>
					<% 
						}
					%>
					</select>					
				</div>
				</div>
				<div class="col-md-4">
					<br>
					<br>
					<br>
					<br>
					<br>
				</div>
				<div class="col-md-6">				
				<div class="form-group">
					<input type="submit" value="Search"> <input type="button"
						value="Export" onclick="window.open('./Export','_blank')">
				</div>
				</div>
			</form>
		</div>

		<div class="col-md-12">
			<h2>Contract List</h2>
			<h4>
				Amount of Data :
				<%=numDataAll.intValue()%></h4>
			<ul class="pagination">
				<li><a>Page</a></li>
				<%
					if (pageRef != 1) {
				%><li><a
					href="javascript:goPage(1);">1</a>
					<%
						} else {
					%>
				<li class="active"><a href="#">1</a> <%
 	}
		 	if (2 < (beginPage)) {
		 	}
		 	for (int i = beginPage; i < (endPageShow + 1); i++) {
		 		if (pageRef != i) {
		 			if (i != totalPage) {
 %>
				<li><a
					href="javascript:;" onclick="goPage(<%=i%>);">
						<%=i%>
				</a></li>
				<%
					}
				} else {
				%>
				<li class="active"><a href="#"><%=i%></a></li>
				<%
				}
			}
				if (totalPage > (rangePageShow + 1)) {
				%>
				<li><a href="#">...</a> <%
 				}
				 	if (totalPage > 1) {
				 		if (pageRef != totalPage) {
 %>
				<li><a
					href="javascript:;" onclick="goPage(<%=totalPage%>)"><%=totalPage%></a></li>
				<%
						}
					}
				%>
				<!--
				<li><a href="#">1</a></li>
				<li class="active"><a href="#">2</a></li>
				<li><a href="#">3</a></li>
				<li><a href="#">4</a></li>
				<li><a href="#">5</a></li>
-->
			</ul>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Case ID</th>
						<th>Detail</th>
						<th>Last Update</th>
					</tr>
				</thead>
				<tbody>
					<%
					ArrayList instance = null;
					if( request.getAttribute("instanceList") != null){ 
						instance = (ArrayList) request
								.getAttribute("instanceList");
					}
						if (instance != null) {
							for (int i = 0; i < instance.size(); i++) {
								BOSActivityInstance a = (BOSActivityInstance) instance
										.get(i);
								String url = "http://" + request.getLocalAddr()
										+ ":9090/bonita/console/homepage?locale=default";
								url += "&theme=" + a.getProcessUUID();
								url += "#form=" + a.getActivityDefinitionUUID() + "$entry";
								url += "&task=" + a.getActivityInstanceUUID();
								url += "&mode=app";

								SimpleDateFormat dateFormatForm = new SimpleDateFormat("dd/MM/yyyy");
								
								Date lastUpd = new Date();
								lastUpd.setTime(a.getLastUpdate());
// 								String d = DateFormat.getDateInstance().format(lastUpd);
								String d = dateFormatForm.format(lastUpd);
					%>
					<tr>
						<td><a href="<%=url%>" target="_blank"><%=a.getInstanceUUID()%></a></td>
						<td><%=a.getActivityLabel() + " "
							+ a.getDynDescription()%></td>
						<td><%=d%></td>
					</tr>
					<%
							}
						}
					%>
				</tbody>
			</table>
		</div>
	</div>
	<%
		
	%>
</body>
</html>