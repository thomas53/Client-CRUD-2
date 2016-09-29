<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
</head>
<body>
	<center>
		<form action="TambahData" method="POST" enctype="multipart/form-data">
			<table style="text-align:left">
				<tr>
					<td>Nama</td>
					<td>:</td>
					<td><input type="text" name="nama"/></td>
				</tr>
				<tr>
					<td>Jenis Kelamin</td>
					<td>:</td>
					<td>
						<input type="radio" id="l" checked=true name="jenkel" value="Laki-Laki"><label for="l">Laki-Laki</label>
						<input type="radio" id="p" name="jenkel" value="Perempuan"><label for="p">Perempuan</label>
					</td>
				</tr>
				<tr>
					<td>foto</td>
					<td>:</td>
					<td><input type="file" name="fileFoto"/></td>
				</tr>
				<tr>
					<td>Golongan</td>
					<td>:</td>
					<td>
						<select name="golongan">
							<option selected disabled>Pilih golongan</option>
							<c:set var="daftarGolongan" value="${daftarGolongan}"/>
							<c:forEach items="${daftarGolongan}" var="golongan">
							<option value="${golongan.idgolongan}">${golongan.nama_golongan}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>Alamat</td>
				<td>:</td>
				<td><textarea rows="5" cols="30" name="alamat"></textarea><br></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td><input type="submit" value="kirim"></td>
			</tr>
		</table>		
	</form>
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
					<a href="EditData?id=${pegawai.idpegawai}">Edit</a>
					<a href="HapusData?id=${pegawai.idpegawai}">Delete</a>					
				</td>					
			</tr>
		</c:forEach>
	</tbody>
</table>
</center>
</body>
</html>