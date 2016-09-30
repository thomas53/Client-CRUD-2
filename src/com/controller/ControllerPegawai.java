package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.model.Golongan;
import com.model.Pegawai;
import com.model.coba;
import com.socket.ToServer;

@Controller
public class ControllerPegawai {
	
	@RequestMapping(value="/pegawai", method = RequestMethod.GET)
	public ModelAndView pegawai(ModelMap model) {
		List<Pegawai> daftarPegawai = new ArrayList<Pegawai>();
		daftarPegawai = new ToServer().ambilPegawai("get");
		
		List<Golongan> daftarGolongan = new ArrayList<Golongan>();
		daftarGolongan = new ToServer().ambilGolongan();
		
		Map<String,String> mapGolongan = new LinkedHashMap<String,String>();
		for (Golongan golongan : daftarGolongan) {
			mapGolongan.put(golongan.getIdgolongan()+"", golongan.getNama_golongan());
		}
		model.addAttribute("daftarPegawai", daftarPegawai);
		model.addAttribute("daftarGolongan", mapGolongan);
		
		return new ModelAndView("pegawai", "command", new Pegawai());
	}
	
	@RequestMapping(value="/pegawai/tambah", method = RequestMethod.POST)
	public String tambahPegawai(@ModelAttribute("SpringWeb")Pegawai cob, 
				@RequestParam("file")MultipartFile foto, ModelMap model) {
		
		String fotoBase64="";
		try {
			fotoBase64 = "data:"+foto.getContentType()+";base64,"
				+ StringUtils.newStringUtf8(Base64.encodeBase64(foto.getBytes(), false));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cob.setFoto(fotoBase64);
		int simpan = new ToServer().send("ins", cob);
		return "redirect:/pegawai";
	}
	
	@RequestMapping(value="/pegawai/hapus",method = RequestMethod.GET)
	public String hapusPegawai(@RequestParam("id")int idpegawai, ModelMap model){
		Pegawai peg = new Pegawai();
		peg.setIdpegawai(idpegawai);
		int hapus = new ToServer().send("del", peg);
		return "redirect:/pegawai";
	}
	
	@RequestMapping(value="/pegawai/edit",method = RequestMethod.GET)
	public ModelAndView editPegawai(@RequestParam("id")int idpegawai,ModelMap model){
		Pegawai peg = new ToServer().ambilPegawaiById(idpegawai);
		
		model.addAttribute("nama", peg.getNama());
		model.addAttribute("alamat",peg.getAlamat());
		model.addAttribute("jenis_kelamin", peg.getJenis_kelamin());
		model.addAttribute("golongan",peg.getGolongan().getNama_golongan());
		model.addAttribute("foto", peg.getFoto());
		
		return new ModelAndView("edit-pegawai", "command", new Pegawai());
	}
	
	@RequestMapping(value="/uploadFile", method = RequestMethod.POST)
	public @ResponseBody 
	String uploadHandler(@RequestParam("nama")String name, 
			@RequestParam("file")MultipartFile file){
		if (!file.isEmpty()) {
			String res="";
			try {
				res = "data:"+file.getContentType()+";base64,"
					+ StringUtils.newStringUtf8(Base64.encodeBase64(file.getBytes(), false));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "file not empty : <img src='"+ res +"'/>" ;
		} else {
			return "file empty";
		}
	}
}
