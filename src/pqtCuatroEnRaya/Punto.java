package pqtCuatroEnRaya;

import java.awt.Color;

import pqtUtilidades.StdDraw;

public class Punto { // Atributos que va a tener nuestro objeto 
	private double positionX; // posicion del punto en el eje X 
	private double positionY; // posicion del punto en el eje Y 
	private double movementX; // desplazamiento del punto en el eje X 
	private double movementY; // desplazamiento del punto en el eje Y 
	Color color; // Color del punto 
	
	// Constructores //
	// Constructor 1. Crea un punto con una posición y le asigna el color negro por defecto
	public Punto(double positionX, double positionY) { 
		this(positionX, positionY, Color.BLACK);
		/*
		 * super(); this.positionX = positionX; this.positionY = positionY; color =
		 * Color.BLACK;
		 */
	}
	
	// Constructor 2. Crea un punto con una posición y un color
	public Punto(double positionX, double positionY, Color color) {
		super();
		this.positionX = positionX;
		this.positionY = positionY;
		this.color = color;
	}
	
	// Getters y Setters //
	// Getters: rescatar el valor del atributo
	// Setters: asignar un valor al atributo
	
	public double getPositionX() {
		return positionX;
	}

	public Punto setPositionX(double positionX) {
		this.positionX = positionX;
		return this;
	}

	public double getPositionY() {
		return positionY;
	}

	public Punto setPositionY(double positionY) {
		this.positionY = positionY;
		return this;
	}

	public double getMovementX() {
		return movementX;
	}

	public void setMovementX(double movementX) {
		this.movementX = movementX;
	}

	public double getMovementY() {
		return movementY;
	}

	public void setMovementY(double movementY) {
		this.movementY = movementY;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	// Devuelve verdadero si dos objetos punto son iguales
	public boolean equals(Punto obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Punto other = (Punto) obj;
		return Double.doubleToLongBits(positionX) == Double.doubleToLongBits(other.positionX)
				&& Double.doubleToLongBits(positionY) == Double.doubleToLongBits(other.positionY);
	}
	
	// Formato en el que queremos mostrar la información de nuestro objeto
	public String toString() { 
		return "Punto [positionX=" + positionX + ", positionY=" + positionY + "]";
	}
	
	// Métodos propios //
	// Métodos static se invocan a partir de la clase
	
	// Devuelve la distancia entre dos puntos a partir de las coordenadas de estos dos
	public static double distanciaEntreCoordenadas(double x1, double y1, double x2, double y2) { 
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}
	
	// Devuelve la distancia entre dos puntos a partir de estos dos
	// Recupera las coordenadas de cada punto
	// Reutiliza el método ya creado anteriormente
	public static double distanciaEntrePuntos(Punto p1, Punto p2) { 
		return distanciaEntreCoordenadas(p1.positionX, p1.positionY, p2.positionX, p2.positionY);
	}
	
	// Devuelve la distancia entre dos puntos a partir del que invoca al método y el punto que se le pasa como parámetro
	// Recupera las coordenadas de cada punto
	// Como es un punto el que invocxa al método no hace falta referenciarlo dentro de este para conseguir sus atributos
	// Reutiliza el método ya creado anteriormente
	public double distanciaPunto(Punto p) { 
		return distanciaEntreCoordenadas(positionX, positionY, p.positionX, p.positionY);
	}
	
	// Dibuja el punto 
	public void dibujarPunto() { 
		StdDraw.setPenColor(color); // Le asigna el color que se le haya indicado, por defecto negro
		StdDraw.setPenRadius(0.03); // Grosor del punto
		StdDraw.point(positionX, positionY); // Coordenadas del punto
	}
	
	// Modifica la posición del punto
	public void moverPunto() {
		positionX = positionX + movementX; // A su coordenada original le suma un número n de posiciones en X
		positionY = positionY + movementY; // A su coordenada original le suma un número n de posiciones en Y
	}
	
	// Devuelve verdadero si el punto que invoca al método está dentro del círculo que se le pasa como parámetro
	public boolean estaDentroDeCirculo(Circulo c) {
		if (distanciaPunto(c.getCentro())<c.getRadio()) { // si la distancia entre el punto y el centro es menor que el radio
			return true; // verdadero
		}
		return false; // sino, falso
	}
}
