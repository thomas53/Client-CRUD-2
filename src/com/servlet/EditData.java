package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import com.model.Golongan;
import com.model.Pegawai;
import com.socket.ToServer;

/**
 * Servlet implementation class EditData
 */
@WebServlet("/EditData")
public class EditData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Pegawai pegawai = new ToServer().ambilPegawaiById(id);
		List<Golongan> golongan = new ArrayList<Golongan>();
		golongan = new ToServer().ambilGolongan();
		
		request.setAttribute("daftarGolongan", golongan);
		
		
		request.setAttribute("idpegawai", pegawai.getIdpegawai());
		request.setAttribute("nama", pegawai.getNama());
		request.setAttribute("jenkel", pegawai.getJenis_kelamin());
		request.setAttribute("alamat", pegawai.getAlamat());
		request.setAttribute("golongan", pegawai.getGolongan().getIdgolongan());
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/update-form.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("batal")==null){
			// pesan yang dikirim ke server
			Pegawai pegawai = new Pegawai();
			pegawai.setIdpegawai(Integer.parseInt(request.getParameter("idpegawai")));
			pegawai.setNama(request.getParameter("nama"));
			pegawai.setJenis_kelamin(request.getParameter("jenkel"));
			pegawai.setAlamat(request.getParameter("alamat"));
			Golongan golongan = new Golongan();
			golongan.setIdgolongan(Integer.parseInt(request.getParameter("golongan")));
			pegawai.setGolongan(golongan);
			// mengirim pesan ke server
			int hasil = new ToServer().send("upd",pegawai);
			
			response.sendRedirect("Index");
		}else{
			response.sendRedirect("index.jsp");
		}
	}

}
