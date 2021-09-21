<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <meta name="description" content="">
   <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
   <meta name="generator" content="Hugo 0.83.1">
   <title>Yun's Apartment Infomation Site</title>

  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/sidebars/">
  <link rel="stylesheet" href="../css/bootstrap-select.css">
   <!-- Bootstrap core CSS -->
  <link href="../css/bootstrap.min.css" rel="stylesheet">

   <style>
     .bd-placeholder-img {
       font-size: 1.125rem;
       text-anchor: middle;
       -webkit-user-select: none;
       -moz-user-select: none;
       user-select: none;
     }

     @media (min-width: 768px) {
       .bd-placeholder-img-lg {
         font-size: 3.5rem;
       }
     }
     a:link, a:visited {
     text-decoration: none;
     color:black;
     }
   </style>

   <!-- Custom styles for this template -->
   <link href="../css/sidebars.css" rel="stylesheet">
</head>
<body>

<div class="container">
  <form role="form">
    <div class="form-group row">
      <div class="col-lg-5">
        <select class="selectpicker form-control" id="number" data-container="body" data-live-search="true" title="아파트명을 입력하세요" data-hide-disabled="true"></select>
      </div>
    </div>
  </form>
 </div>
</body>
<!-- bootstrap core js -->
  <script src="../js/bootstrap.bundle.min.js"></script>
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.bundle.min.js"></script>  
	<script src="../js/bootstrap-select.js"></script>  
<!-- 커스텀 js -->
	<script src="../js/yunsapt.js"></script>
  

</html>