package com.example.project;

import java.util.Random;

public class HashLinearProbing {
	private int hsize; // tamano de la tabla hash
	private Persona[] buckets; // array que representa la tabla hash
	private Integer AVAILABLE;
	private int size; // cantidad de elementos en la tabla hash

	public HashLinearProbing(int hsize) {
		this.buckets = new Persona[hsize];
		this.hsize = hsize;
		this.AVAILABLE = Integer.MIN_VALUE;
		this.size = 0;
	}

	public int hashing(String sKey) {

		// CONVERTIR A NÚMERO
		int num = Integer.valueOf(sKey);

		// SUMAR LOS DÍGITOS
		int key = 0;
		while (num > 0) {
			key += num % 10;
			num = num / 10;
		}

		// MÓDULO CON PRIMO 13
		int hash = key % 13;
		if (hash < 0) {
			hash += hsize;
		}
		return hash;
	}

	public void insertHash(Persona persona) {
		Persona wrappedPersona = persona;

		int hash = hashing(persona.DNI);

		if (isFull()) {
			System.out.println("Tabla hash esta llena!");
			return;
		}

		for (int i = 0; i < hsize; i++) {
			if (buckets[hash] == null) {
				buckets[hash] = wrappedPersona;
				size++;
				return;
			}

			if (hash + 1 < hsize) {
				hash++;
			} else {
				hash = 0;
			}
		}
	}

	public void deleteHash(String key) {
		String wrappedString = key;
		int hash = hashing(key);

		if (isEmpty()) {
			System.out.println("Tabla hash esta vacia!");
			return;
		}

		for (int i = 0; i < hsize; i++) {
			if (buckets[hash] != null && buckets[hash].DNI.equals(wrappedString)) {
				buckets[hash].setDNI(convAVAILABLE());
				;
				size--;
				return;
			}

			if (hash + 1 < hsize) {
				hash++;
			} else {
				hash = 0;
			}
		}
		System.out.println("Clave " + key + " no encontrada");
	}

	public void displayHashtable() {
		for (int i = 0; i < hsize; i++) {
			if (buckets[i] == null || buckets[i].DNI.equals(convAVAILABLE())) {
				System.out.println("Celda " + i + ": Vacia");
			} else {
				System.out.println("Celda " + i + ": " + buckets[i].toString());
			}
		}
	}

	public int findHash(String key) {
		String wrappedString = key;
		int hash = hashing(key);

		if (isEmpty()) {
			System.out.println("Tabla hash esta vacia!");
			return -1;
		}

		for (int i = 0; i < hsize; i++) {
			try {
				if (buckets[hash].DNI.equals(wrappedString)) {
					buckets[hash].setDNI(convAVAILABLE());
					return hash;
				}
			} catch (Exception E) {
			}

			if (hash + 1 < hsize) {
				hash++;
			} else {
				hash = 0;
			}
		}
		System.out.println("Clave " + key + " no encontrada!");
		return -1;
	}

	public boolean isFull() {
		return size == hsize;
	}

	public boolean isEmpty() {
		boolean response = true;
		for (int i = 0; i < hsize; i++) {
			if (buckets[i] != null) {
				response = false;
				break;
			}
		}
		return response;
	}

	/*public static void main(String[] args) {
		HashLinearProbing tb = new HashLinearProbing(10);

		Random rd = new Random();

		for (int i = 0; i < 5; i++) {
			tb.insertHash(rd.nextInt(100));
		}

		tb.displayHashtable();
	}*/

	public String convAVAILABLE() {
		return String.valueOf(AVAILABLE);
	}
}
