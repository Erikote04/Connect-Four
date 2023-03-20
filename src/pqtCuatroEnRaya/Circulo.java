package pqtCuatroEnRaya;

import java.awt.Color;
import java.util.Objects;

import pqtUtilidades.StdDraw;

public class Circulo {
	private Punto centro;
	private double radio;

	public Circulo(Punto centro, double radio) {
		super();
		setCentro(centro);
		setRadio(radio);
	}

	public Circulo(double x, double y, double radio) {
		super();
		centro = new Punto(x, y);
		setRadio(radio);
	}

	public Circulo() { // circulo por defecto //
		super();
		centro = new Punto(0, 0);
		setRadio(10);
	}

	public Punto getCentro() {
		return centro;
	}

	public void setCentro(Punto centro) {
		if (centro == null) {
			throw new IllegalArgumentException("El centro no puede ser nulo");
		}
		this.centro = centro;
	}

	public double getRadio() {
		return radio;
	}

	public void setRadio(double radio) {
		if (radio <= 0)
			throw new IllegalArgumentException("El radio debe ser mayor que cero");
		this.radio = radio;
	}

	public void setColor(Color color) {
		centro.setColor(color);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Circulo other = (Circulo) obj;
		return Objects.equals(centro, other.centro)
				&& Double.doubleToLongBits(radio) == Double.doubleToLongBits(other.radio);
	}

	public String toString() {
		return "Circulo [centro=" + centro + ", radio=" + radio + "]";
	}

	public void dibujarCirculo() {
		StdDraw.setPenColor(centro.getColor());
		StdDraw.filledCircle(centro.getPositionX(), centro.getPositionY(), radio);
	}

	public void moverCirculo() {
		centro.moverPunto();
	}

	public boolean seSuperpone(Circulo c) {
		return this.centro.distanciaPunto(c.getCentro()) < this.radio + c.getRadio();
	}

	public boolean seSuperpone(Rectangulo r) {
		if ((this.centro.getPositionX() + this.radio > r.getSuperiorIzquierda().getPositionX()) // superponiendose por la derecha
				|| (this.centro.getPositionX() - this.radio < r.getInferiorDerecha().getPositionX()) // superponiendose por la izquierda
				|| (this.centro.getPositionY() + this.radio > r.getInferiorDerecha().getPositionY()) // superponiendose por abajo
				|| (this.centro.getPositionY() - this.radio < r.getSuperiorIzquierda().getPositionY()) // superponiendose por arriba
				|| (this.centro.distanciaPunto(r.getSuperiorIzquierda()) < this.radio) // superponiendose por esquina sup. izq.
				|| (this.centro.distanciaPunto(r.superiorDerecha()) < this.radio) // superponiendose por esquina sup. der.
				|| (this.centro.distanciaPunto(r.inferiorIzquierda()) < this.radio) // superponiendose por esquina inf. izq.
				|| (this.centro.distanciaPunto(r.getInferiorDerecha()) < this.radio)) { // superponiendose por esquina inf. der.
			return true;
		}
		return false;
	}

	public boolean dentroDeRectangulo(Rectangulo r) {
		if ((this.centro.getPositionX() + this.radio < r.getInferiorDerecha().getPositionX())
				&& (this.centro.getPositionX() - this.radio > r.getSuperiorIzquierda().getPositionX())
				&& (this.centro.getPositionY() + this.radio < r.getSuperiorIzquierda().getPositionY())
				&& (this.centro.getPositionY() - this.radio > r.getInferiorDerecha().getPositionY())) {
			return true;
		}
		return false;
	}
}
