package com.tepach.bean;

import java.util.HashMap;

public class Usuario {
	private HashMap<String, String> usuarios = new HashMap<>();
	
	public Usuario(){
		usuarios.put("CesarCarrillo", "Carrillo");
		usuarios.put("Barrios", "Barrios");	
		usuarios.put("AnthonyTepach", "Tepach");
		usuarios.put("tepach", "tepach");
	}

	public HashMap<String, String> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(HashMap<String, String> usuarios) {
		this.usuarios = usuarios;
	}
}
