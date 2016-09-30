<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form:form>
		<table>
			<tr>
				<td><form:label path="nama">Nama</form:label> </td>
				<td><form:input path="nama" value="${nama}"/></td>
			</tr>
			<tr>
				<td><form:label path="alamat">Alamat</form:label> </td>
				<td><form:input path="alamat" value="${alamat}"/></td>
			</tr>
			<tr>
				<td><form:label path="foto">Foto</form:label> </td>
				<td><form:input path="foto" value="${foto}"/></td>
			</tr>
			<tr>
				<td><form:label path="jenis_kelamin">Jenis Kelamin</form:label> </td>
				<td><form:input path="jenis_kelamin" value="${jenis_kelamin}"/></td>
			</tr>
			<tr>
				<td><form:label path="golongan.idgolongan">Golongan</form:label> </td>
				<td><form:input path="golongan.nama_golongan" value="${golongan}"/></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="update"></td>
			</tr>
		</table>
		<br>
	</form:form>
</body>
</html>