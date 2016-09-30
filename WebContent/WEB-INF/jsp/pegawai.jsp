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
	<form:form method="POST" action="/ClientServlet2/pegawai/tambah" enctype="multipart/form-data">
		<table>
			<tr>
				<td><form:label path="nama">Nama</form:label></td>
				<td><form:input path="nama"/></td>
			</tr>
			<tr>
				<td><form:label path="alamat">Alamat</form:label></td>
				<td><form:textarea path="alamat"/></td>
			</tr>
			<tr>
				<td>Foto</td>
				<td><input type="file" name="file"></td>
			</tr>
			<tr>
				<td>Golongan</td>
				<td>
					<form:select path="golongan.idgolongan">
						<form:options items="${daftarGolongan}"/>
					</form:select>
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
				<td></td>
				<td><input type="submit" value="kirim"> </td>
			</tr>
			
		</table>
	</form:form>
	<table border=1>
		<thead>
			<tr>
				<th>Nama</th>
				<th>Jenis Kelamin</th>
				<th>Alamat</th>
				<th>Foto</th>
				<th>Gaji</th>
				<th>Aksi</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="daftarPegawai" value="${daftarPegawai}"/>
			<c:forEach items="${daftarPegawai}" var="pegawai">
				<tr>
					<td><c:out value="${pegawai.nama}" /></td>
					<td><c:out value="${pegawai.jenis_kelamin}" /></td>
					<td><c:out value="${pegawai.alamat}" /></td>
					<td><img style="width:60px;" alt="" src="${pegawai.foto}"></td>
					<td><c:out value="${pegawai.golongan.gaji}" /></td>
					<td>
						<a href="pegawai/edit?id=${pegawai.idpegawai}">Edit</a>
						<a href="pegawai/hapus?id=${pegawai.idpegawai}">Delete</a>					
					</td>					
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>