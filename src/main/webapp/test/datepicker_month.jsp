<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css" rel="stylesheet"/>
<script>
$('#day').datepicker({
    format: "dd/mm/yyyy",
    autoclose: true, 
   startView: "days", 
   minViewMode: "days"     
}).on('change', function (ev) {
   $(this).datepicker('hide');
});
$('#month').datepicker({
    format: "mm/yyyy",
    autoclose: true, 
   startView: "months", 
   minViewMode: "months"
}).on('change', function (ev) {
   $(this).datepicker('hide');
});
$('#year').datepicker({
    format: "yyyy",
    autoclose: true, 
   startView: "years", 
   minViewMode: "years"
}).on('change', function (ev) {
   $(this).datepicker('hide');
});
</script>

Day Picker: <input id="day"> <br/>
Month Picker: <input id="month"> <br/>
Year Picker: <input id="year"> <br/>
COMBINED Day OR Month OR Year Picker Picker: <input id="combined" placeholder="Can get MM/DD/YYYY, MM/YYYY, or YYYY" style="width:250px"> <br/>
 