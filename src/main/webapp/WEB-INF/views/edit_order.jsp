<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.io.*,java.util.*" %>

<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  

  <title> Cassis </title>

  <!-- Bootstrap core CSS -->
  <link href="static/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom fonts for this template -->
  <link href="https://fonts.googleapis.com/css?family=Raleway:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Lora:400,400i,700,700i" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="static/css/business-casual.min.css" rel="stylesheet">

</head>

<body>
<section class="page-section about-heading">
    <div class="container" align="center" >
      <img class="img-fluid rounded about-heading-img mb-3 mb-lg-0" src="static/img/.jpg" alt="">
      <div class="about-heading-content">
        <div>
          <div class="col-xl-14 col-lg-13 mx-auto">
            <div class="bg-faded rounded p-5">
              <h2 class="section-heading mb-4">
                <span class="section-heading-upper">Edit</span>
                <span class="section-heading-lower">Order</span>
              </h2>	
              
              <form:form action="save" method="post" modelAttribute="editOrder">
            <table>
            <tr>
                    <td>ID: </td>
                    <td>${order.orderId}
                        <form:hidden path="orderId"/>
                    </td>
                </tr>        
                <tr>
                    <td>Customer Name: </td>
                    <td><form:input path="customerName"/></td>
                </tr>
                
                 <tr>
                    <td>Email: </td>
                    <td><form:input path="customerEmail"/></td>
                </tr>
                 
                 <tr>
                    <td>Phone: </td>
                    <td><form:input path="phone"/></td>
                </tr>
                
                 <tr>
                    <td>Product: </td>
                    <td><form:input path="product" /></td>
                    
                </tr>
                 <tr>
                    <td>Quantity: </td>
                    <td><form:input path="quantity" /></td>
                </tr>
               <tr>
                    <td>Pickup Date </td>
                    <td><form:input path="pickupDate"  type="date"/></td>
                </tr>
              
                 
                <tr>
                    <td>Additional Information: </td>
                    <td><form:input path="notes" type="text" size="50" cols="30" rows="3"/></td>
                </tr> 

                
                <tr>  
                    <td colspan="2">
                  <a href="orderTable" > <button type="submit" value="save">Save</button> </a>
                    <a href="orderTable" data-dismiss="modal" aria-hidden="true" data-toggle="modal">&nbsp;&nbsp;Cancel</a>
                    </td>
                </tr>                    
            </table>     
            
        </form:form>	 	
                
            </div>
          </div>
        </div>
     </div>
  </div>
</section>
   			