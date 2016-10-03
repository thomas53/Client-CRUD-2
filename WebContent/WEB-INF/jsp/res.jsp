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
				<td><form:label path="nama">Nama</form:label></td>
				<td><form:input path="nama" value="${peg.nama }"/><br></td>
			</tr>
			<tr>
				<td><form:label path="alamat">Alamat</form:label></td>
				<td><form:input path="alamat" value="${peg.alamat }"/><br></td>
			</tr>
			<tr>
				<td><form:label path="foto">Foto</form:label></td>
				<td>
					<img style="width:60px;" alt="foto profil" src="${peg.foto}"><br>
					<input type="file" name="file">
				</td>
			</tr>
			<tr>
				<td><form:label path="jenis_kelamin">Jenis Kelamin</form:label></td>
				<td>
					<form:radiobutton path="jenis_kelamin" value="Laki-Laki"/>Laki-Laki
					<form:radiobutton path="jenis_kelamin" value="Perempuan"/>Perempuan
				</td>
			</tr>
			<tr>
				<td>Golongan</td>
				<td>
					<form:select path="golongan.idgolongan">
						<form:options items="${listGol}"/>
					</form:select>
				</td>
			</tr>
		</table>		
	</form:form>
</body>
</html>