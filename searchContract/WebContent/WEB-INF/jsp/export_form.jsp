<%@page import="java.text.DateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@page import="java.util.Date"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Export Cancel Contract</title>
<script type="text/javascript" src="Scripts/jquery/jquery-1.12.0.js"></script>
<link rel="stylesheet" type="text/css" href="Scripts/Bootstrap/css/bootstrap.css">
<script type="text/javascript" src="Scripts/Bootstrap/js/bootstrap.js"></script>
<link rel="stylesheet" type="text/css" href="Scripts/datepicker/css/datepicker.css">
<script type="text/javascript" src="Scripts/datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript">
</script>
<% 

%>
</head>
<body>

	<div class="container">
		<h2>Export Cancel Contract Report</h2>
		<div class="col-md-6">
			<form action="./GenExport" method="POST" role="form">
        <div class="well">
        	<table>
        		<tr>
        			<td>
        	<label>Start Date</label>
        	<div class="input-group">
				  <div class="input-append date" id="dpStart" data-date="" data-date-format="dd-mm-yyyy">
				<!-- 	<input class="span2" size="16" type="text" value="12-02-2012" readonly> -->
					<input class="span2 form-control" size="16" id="startDate" name="startDate" type="text" value="12-02-2012" readonly>
					<span class="add-on"><i class="icon-calendar"><span class="glyphicon glyphicon-calendar"></span></i></span>
				  </div>
			  </div>
			  		</td>
			  		<td width="60">&nbsp;</td>
			  		<td>
        	<label>End Date</label>
        	<div class="input-group">
				  <div class="input-append date" id="dpEnd" data-date="" data-date-format="dd-mm-yyyy">
				<!-- 	<input class="span2" size="16" type="text" value="12-02-2012" readonly> -->
					<input class="span2 form-control" size="16" id="endDate" name="endDate" type="text" value="12-02-2012" readonly>
					<span class="add-on"><i class="icon-calendar"><span class="glyphicon glyphicon-calendar"></span></i></span>
				  </div>
			  </div>
			  		</td>
			  	</tr>
			  </table>
				<div class="form-group">
					<input class="btn btn-default" type="submit" value="Search">
				</div>			  			  
          </div>				

			</form>
		</div>
	</div>
	<script type="text/javascript">

		if (top.location != location) {
			top.location.href = document.location.href;
		}
		$(function() {

			try {
// 				$('#dp1').datepicker({
// 					format : 'mm-dd-yyyy'
// 				});

// 				$('#dp2').datepicker();
 				$('#dpStart').datepicker();
 				$('#dpEnd').datepicker();
			
// 				$('#dpYears').datepicker();
// 				$('#dpEnd').datepicker();
				/*
				var startDate = new Date(2012, 1, 20);
				var endDate = new Date(2012, 1, 25);
				$('#dp4')
						.datepicker()
						.on(
								'changeDate',
								function(ev) {
									if (ev.date.valueOf() > endDate.valueOf()) {
										$('#alert')
												.show()
												.find('strong')
												.text(
														'The start date can not be greater then the end date');
									} else {
										$('#alert').hide();
										startDate = new Date(ev.date);
										$('#startDate')
												.text($('#dp4').data('date'));
									}
									$('#dp4').datepicker('hide');
								});
				$('#dp5')
						.datepicker()
						.on(
								'changeDate',
								function(ev) {
									if (ev.date.valueOf() < startDate.valueOf()) {
										$('#alert')
												.show()
												.find('strong')
												.text(
														'The end date can not be less then the start date');
									} else {
										$('#alert').hide();
										endDate = new Date(ev.date);
										$('#endDate').text($('#dp5').data('date'));
									}
									$('#dp5').datepicker('hide');
								});
				 */
				// disabling dates
				var nowTemp = new Date();
				var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(),
						nowTemp.getDate(), 0, 0, 0, 0);
				var currDate =  nowTemp.getDate() + "-" + (nowTemp.getMonth()+1)  +"-"+
				nowTemp.getFullYear();
				$("#startDate").val(currDate);
				$("#endDate").val(currDate);

				// 				var checkin = $('#dpd1')
				// 						.datepicker(
				// 								{
				// 									onRender : function(date) {
				// 										return date.valueOf() < now.valueOf() ? 'disabled'
				// 												: '';
				// 									}
				// 								}).on('changeDate', function(ev) {
				// 							if (ev.date.valueOf() > checkout.date.valueOf()) {
				// 								var newDate = new Date(ev.date)
				// 								newDate.setDate(newDate.getDate() + 1);
				// 								checkout.setValue(newDate);
				// 							}
				// 							checkin.hide();
				// 							$('#dpd2')[0].focus();
				// 						}).data('datepicker');

				// 				var checkout = $('#dpd2')
				// 						.datepicker(
				// 								{
				// 									onRender : function(date) {
				// 										return date.valueOf() <= checkin.date
				// 												.valueOf() ? 'disabled' : '';
				// 									}
				// 								}).on('changeDate', function(ev) {
				// 							checkout.hide();
				// 						}).data('datepicker');
			} catch (err) {
				alert(err.name + ", " + err.message);
			}
		});
	</script>

</body>
</html>