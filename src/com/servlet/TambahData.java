package com.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.codec.binary.Base64;

import com.model.Golongan;
import com.model.Pegawai;
import com.socket.ToServer;

import sun.misc.IOUtils;


/**
 * Servlet implementation class TambahData
 */
@WebServlet("/TambahData")
@MultipartConfig
public class TambahData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TambahData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		// pesan yang dikirim ke server
		Pegawai pegawai = new Pegawai();
		pegawai.setNama(request.getParameter("nama"));
		pegawai.setJenis_kelamin(request.getParameter("jenkel"));
		pegawai.setAlamat(request.getParameter("alamat"));
		Golongan gol = new Golongan();
		out.print("id : "+request.getParameter("golongan"));
		gol.setIdgolongan(Integer.parseInt(request.getParameter("golongan")));
		pegawai.setGolongan(gol);
		
		// mengambil gambar
		Part foto = request.getPart("fileFoto");
		String disposition = foto.getHeader("Content-Disposition");
		String pathFile = disposition.replaceFirst("(?i)^.*filename=\"([^\"]+)\".*$", "$1");
		File myFile = new File(pathFile);
		byte[] base64 = null;
		
		// convert base64
		InputStream isGambar = foto.getInputStream();
        base64 = IOUtils.readFully(isGambar, -1, true);
		pegawai.setFoto("data:" + foto.getContentType() + ";base64," + Base64.encodeBase64String(base64));

		
		// mengirim pesan ke server
		int hasil = new ToServer().send("ins", pegawai);
		response.sendRedirect("Index");
	}

}
