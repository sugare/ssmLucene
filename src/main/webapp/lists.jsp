<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/bootstrap-3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/bootstrap-3.3.7/css/bootstrap-theme.min.css">
<script src="${pageContext.request.contextPath }/static/jquery-2.1.4/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/static/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<title>SUCCESS</title>
</head>
<body>


<div class="container" style="margin: 50px;">
<div class="row">
  <div class="col-md-2"></div>
  <div class="col-md-8" style="text-align:center">
  	<div style="box-shadow:0px 2px 5px;height:100px;padding: 40px 0 0 0">
		<form class="form-inline" action="${pageContext.request.contextPath }/insertSubmit" method="post">
		  <div class="form-group">
		    <label for="title">标题</label>
		    <input type="text" name="title" class="form-control" id="title" placeholder="设计模式">
		  </div>
		  &nbsp;&nbsp;
		  <div class="form-group">
		    <label for="content">内容</label>
		    <input type="text" name="content" class="form-control" id="content" placeholder="学习">
		  </div>
		  &nbsp;&nbsp;
		  <button type="submit" class="btn btn-default">添加数据</button>
		</form>
  	</div>
  	<div style="box-shadow:0px 2px 5px">
  	
		
		<table class="table table-striped table-bordered">
			<tr>
				<td width="100px">序号</td>
				<td width="250px">标题</td>
				<td>内容</td>
			</tr>

			
			<c:forEach items="${blogs }" var="blog" varStatus="b">
				<tr>
					<td>${b.index + 1}</td>
					<td>${blog.title }</td>
					<td>${blog.content }</td>
				</tr>
			</c:forEach>
		
		</table>
	
		
	</div>
  </div>
</div>
</div>
</body>

</html>