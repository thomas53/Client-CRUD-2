package com.socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.model.Golongan;
import com.model.Pegawai;

public class ToServer {
	
	private final String serverName = "127.0.0.1";
	private final int port = 8888;
	
	public int send(String act,Pegawai peg){
		
		int msg = 0;
		
		try {
			Socket client = new Socket(serverName,port);
			
			// set aksi
			DataOutputStream dataOut = new DataOutputStream(client.getOutputStream());
			dataOut.writeUTF(act);
			
			// set data
			ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
			Pegawai pegawai = peg;
			out.writeObject(pegawai);
			
			ObjectInputStream in = new ObjectInputStream(client.getInputStream());
			msg = (int) in.readObject();
			client.close();
		}catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return msg;
	}
	
	public List<Pegawai> ambilPegawai(String act){
		List<Pegawai> res = new ArrayList<Pegawai>();
		
		try {
			Socket client = new Socket(serverName,port);
			
			// set aksi
			DataOutputStream dataOut = new DataOutputStream(client.getOutputStream());
			dataOut.writeUTF(act);
			
			// get data
			ObjectInputStream in = new ObjectInputStream(client.getInputStream());
			res = (List<Pegawai>) in.readObject();
			
			client.close();
		}catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}
	
	public Pegawai ambilPegawaiById(int id){
		Pegawai res = new Pegawai();
				
		try {
			Socket client = new Socket(serverName,port);
			
			// set aksi
			DataOutputStream dataOut = new DataOutputStream(client.getOutputStream());
			dataOut.writeUTF("getId");
			
			// set data
			ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
			Pegawai pegawai = new Pegawai();
			pegawai.setIdpegawai(id);
			out.writeObject(pegawai);
			
			// get data
			ObjectInputStream in = new ObjectInputStream(client.getInputStream());
			res = (Pegawai) in.readObject();
			
			client.close();
		}catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public List<Golongan> ambilGolongan(){
		List<Golongan> res = new ArrayList<Golongan>();
		try {
			Socket client = new Socket(serverName,port);
			
			// set aksi
			DataOutputStream dataOut = new DataOutputStream(client.getOutputStream());
			dataOut.writeUTF("get_gol");
			
			// get data
			ObjectInputStream in = new ObjectInputStream(client.getInputStream());
			res = (List<Golongan>) in.readObject();
			
			client.close();
		}catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
}
