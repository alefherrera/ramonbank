package com.ramon.ramonbank.servicios.utils;

public class PLAZO_FIJO {
	public enum INTERESES {
		DIAS_0(1, 0, 0), DIAS_30(2, 30, 0.10), DIAS_60(3, 60, 0.12), DIAS_90(4,
				90, 0.13);

		private int _key;
		private int _dias;
		private double _interes;

		public int key() {
			return this._key;
		}

		public int dias() {
			return this._dias;
		}

		public double interes() {
			return this._interes;
		}

		public int id() {
			return this._key;
		}

		INTERESES(int _key, int _dias, double _interes) {
			this._key = _key;
			this._dias = _dias;
			this._interes = _interes;
		}

		public static INTERESES get_enum(int _key) {
			switch (_key) {
			case 1:
				return DIAS_30;
			case 2:
				return DIAS_60;
			case 3:
				return DIAS_90;
			default:
				break;
			}
			return DIAS_30;
		}

		public static INTERESES get_intereses(int _dias) {
			if (_dias < 30) {
				_dias = 0;
			} else if (_dias < 60) {
				_dias = 30;
			} else if (_dias < 90) {
				_dias = 60;
			} else {
				_dias = 90;
			}

			switch (_dias) {
			case 0:
				return DIAS_0;
			case 30:
				return DIAS_30;
			case 60:
				return DIAS_60;
			case 90:
				return DIAS_90;
			default:
				return DIAS_0;
			}
		}
	}
	
	public enum ACREDITACION{
		CAJA(1, "Caja"), CUENTA(2, "Cuenta");
		
		private int _key;
		private String _nombre;
		
		public int get_key() {
			return _key;
		}
		public void set_key(int _key) {
			this._key = _key;
		}
		public String get_nombre() {
			return _nombre;
		}
		public void set_nombre(String _nombre) {
			this._nombre = _nombre;
		}
		
		ACREDITACION(int _key, String _nombre){
			this._key = _key;
			this._nombre = _nombre;
		}
		public static ACREDITACION get_enum(int _key){
			switch (_key) {
			case 1:
				return CAJA;
			case 2:
				return CUENTA;
			default:
				return CAJA;
			}
		}
		

	}
	
	

}
