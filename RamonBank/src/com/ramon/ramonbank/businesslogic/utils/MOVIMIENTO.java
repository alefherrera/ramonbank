package com.ramon.ramonbank.businesslogic.utils;

public class MOVIMIENTO {

	public enum TIPO {
		EXTRACCION(1, "Extraccion"), DEPOSITO(2, "Deposito");

		private int _key;
		private String _nombre;

		public String nombre() {
			return this._nombre;
		}

		public int id() {
			return this._key;
		}

		TIPO(int _key, String _nombre) {
			this._key = _key;
			this._nombre = _nombre;
		}

		public static TIPO get_enum(int _key) {
			switch (_key) {
			case 1:
				return EXTRACCION;
			case 2:
				return DEPOSITO;
			default:
				break;
			}
			return EXTRACCION;
		}
	}

	public enum ORIGEN {
		CAJA(1, "Extraccion"), TRANSFERENCIA(2, "Deposito"), SERVICIO(3,
				"Servicio"), PRESTAMO(4, "Prestamo"), PLAZO_FIJO(5,
				"Plazo Fijo");

		private int _key;
		private String _nombre;

		public String nombre() {
			return this._nombre;
		}

		public int id() {
			return this._key;
		}

		ORIGEN(int _key, String _nombre) {
			this._key = _key;
			this._nombre = _nombre;
		}

		public static ORIGEN get_enum(int _key) {
			switch (_key) {
			case 1:
				return CAJA;
			case 2:
				return TRANSFERENCIA;
			case 3:
				return SERVICIO;
			case 4:
				return PRESTAMO;
			case 5:
				return PLAZO_FIJO;
			default:
				break;
			}
			return CAJA;
		}
	}
}