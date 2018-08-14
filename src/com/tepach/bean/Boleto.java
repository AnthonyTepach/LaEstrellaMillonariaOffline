package com.tepach.bean;

import java.util.ArrayList;

public class Boleto {
	private ArrayList<String> folio;
	private ArrayList<String> kit;
	private ArrayList<String> cantidad;
	private ArrayList<String> escaneo;
	private ArrayList<String> repetido;
	
	public Boleto() {
		this.folio=new ArrayList<>();
		this.kit=new ArrayList<>();
		this.cantidad=new ArrayList<>();
		this.escaneo=new ArrayList<>();
		this.repetido=new ArrayList<>();
	}

	public ArrayList<String> getFolio() {
		return folio;
	}

	public void setFolio(ArrayList<String> folio) {
		this.folio = folio;
	}

	public ArrayList<String> getKit() {
		return kit;
	}

	public void setKit(ArrayList<String> kit) {
		this.kit = kit;
	}

	public ArrayList<String> getCantidad() {
		return cantidad;
	}

	public void setCantidad(ArrayList<String> cantidad) {
		this.cantidad = cantidad;
	}

	public ArrayList<String> getEscaneo() {
		return escaneo;
	}

	public void setEscaneo(ArrayList<String> escaneo) {
		this.escaneo = escaneo;
	}

	public ArrayList<String> getRepetido() {
		return repetido;
	}

	public void setRepetido(ArrayList<String> repetido) {
		this.repetido = repetido;
	}
}
