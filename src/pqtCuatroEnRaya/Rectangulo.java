package pqtCuatroEnRaya;

import java.awt.Color;

import pqtUtilidades.StdDraw;

public class Rectangulo {
	private Punto superiorIzquierda;
	private Punto inferiorDerecha;

	public Rectangulo(Punto superiorIzquierda, Punto inferiorDerecha) {
		if (inferiorDerecha == null) {
			throw new IllegalArgumentException("No puede introducir null");
		}
		this.inferiorDerecha = inferiorDerecha;
		setSuperiorIzquierda(superiorIzquierda);
		setInferiorDerecha(inferiorDerecha);
	}

	public Rectangulo(double supIzquierdaX, double supIzquierdaY, double infDerechaX, double infDerechaY) {
		this(new Punto(supIzquierdaX, supIzquierdaY), new Punto(infDerechaX, infDerechaY));
	}

	public Punto getSuperiorIzquierda() {
		return superiorIzquierda;
	}

	public void setSuperiorIzquierda(Punto superiorIzquierda) {
		if (superiorIzquierda == null) {
			throw new IllegalArgumentException("No puede introducir null");
		}
		if (superiorIzquierda.getPositionX() >= inferiorDerecha.getPositionX()
				|| superiorIzquierda.getPositionY() <= inferiorDerecha.getPositionY()) {
			throw new IllegalArgumentException("Puntos del rectángulo incorrectos");
		}
		this.superiorIzquierda = superiorIzquierda;
	}

	public Punto getInferiorDerecha() {
		return inferiorDerecha;
	}

	public void setInferiorDerecha(Punto inferiorDerecha) {
		if (inferiorDerecha == null) {
			throw new IllegalArgumentException("No puede introducir null");
		}
		if (superiorIzquierda.getPositionX() >= inferiorDerecha.getPositionX()
				|| superiorIzquierda.getPositionY() <= inferiorDerecha.getPositionY()) {
			throw new IllegalArgumentException("Puntos del rectángulo incorrectos");
		}
		this.inferiorDerecha = inferiorDerecha;
	}

	public String toString() {
		return "Rectangulo [superiorIzquierda=" + superiorIzquierda + ", inferiorDerecha=" + inferiorDerecha + "]";
	}

	public Punto centroIzquierda() {
		return new Punto(superiorIzquierda.getPositionX(), superiorIzquierda.getPositionY() - altura() / 2);
	}

	public Punto inferiorIzquierda() {
		return new Punto(superiorIzquierda.getPositionX(), inferiorDerecha.getPositionY());
	}

	public Punto centroInferior() {
		return new Punto(inferiorDerecha.getPositionX() - base() / 2, inferiorDerecha.getPositionY());
	}

	public Punto centroDerecha() {
		return new Punto(inferiorDerecha.getPositionX(), inferiorDerecha.getPositionY() + altura() / 2);
	}

	public Punto superiorDerecha() {
		return new Punto(inferiorDerecha.getPositionX(), superiorIzquierda.getPositionY());
	}

	public Punto centroSuperior() {
		return new Punto(superiorIzquierda.getPositionX() + base() / 2, superiorIzquierda.getPositionY());
	}

	public Punto centro() {
		return new Punto(superiorIzquierda.getPositionX() + base() / 2, inferiorDerecha.getPositionY() + altura() / 2);
	}

	public double base() {
		return inferiorDerecha.getPositionX() - superiorIzquierda.getPositionX();
	}

	public double altura() {
		return superiorIzquierda.getPositionY() - inferiorDerecha.getPositionY();
	}

	public double area() {
		return base() * altura(); 
	}

	public void dibujarRectangulo() {
		dibujarRectangulo(true);
	}

	public void dibujarRectangulo(boolean relleno) {
		StdDraw.setPenColor(superiorIzquierda.getColor());
		if (relleno) {
			StdDraw.filledRectangle(centro().getPositionX(), centro().getPositionY(), base() / 2, altura() / 2);
		} else {
			StdDraw.setPenRadius(0.005);
			StdDraw.rectangle(centro().getPositionX(), centro().getPositionY(), base() / 2, altura() / 2);
		}
	}

	public void cambiarColor(Color color) {
		superiorIzquierda.setColor(color);
	}	
	
	public void moverRectangulo(double movementX, double movementY) {
		superiorIzquierda.setMovementX(movementX);
		superiorIzquierda.setMovementY(movementY);
		inferiorDerecha.setMovementX(movementX);
		inferiorDerecha.setMovementY(movementY);
	}
	
	public void moverRectangulo() {
		superiorIzquierda.moverPunto();
		inferiorDerecha.moverPunto();
	}
}
