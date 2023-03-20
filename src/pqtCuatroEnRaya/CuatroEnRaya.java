package pqtCuatroEnRaya;

import java.awt.Color;
import pqtUtilidades.StdDraw;

public class CuatroEnRaya {
	static Circulo[][] tablero; // Matriz de 6 filas y 7 columnas de círculos que contendrá las fichas
	static Rectangulo zonaDeJuego; // Rectángulo azul que representa el tablero que contiene las fichas
	static final double TAM_CASILLA = 25; // Tomamos cuadrículas de 25x25 como referencia para la posición de cada ficha
	static int turno = 1; // Jugador 1 ó 2. Comienza el 1.
	static String advertencia = null;
	
	// Dibuja el tablero según su contenido (huecos y fichas)
	private static void dibujarTablero() {
		zonaDeJuego.dibujarRectangulo();
		Circulo hueco = new Circulo(0, 0, TAM_CASILLA * 0.8 / 2); // Círculo para dibujar los huecos
		hueco.setColor(Color.WHITE);
		Circulo c = null; // Referencia a un Circulo que se dibujará (hueco o ficha amarilla/roja)
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[0].length; j++) { // Transformamos la i(fila) y la j(columna) en coordenadas del centro de un círculo
				double x = zonaDeJuego.getSuperiorIzquierda().getPositionX() + j * TAM_CASILLA + TAM_CASILLA / 2;
				double y = zonaDeJuego.getSuperiorIzquierda().getPositionY() - i * TAM_CASILLA + TAM_CASILLA / 2
						- TAM_CASILLA;
				if (tablero[i][j] == null) { // No se ha introducido ficha en esa posición
					c = hueco; // Se le asigna un hueco (círculo blanco)
				} else { // Se ha introducido ficha en esa posición
					c = tablero[i][j]; // Se le asigna una ficha
				}
				// Se le asigna el centro y se dibuja el hueco o la ficha
				c.getCentro().setPositionX(x);
				c.getCentro().setPositionY(y);
				c.dibujarCirculo();
			}
		}
	}

	private static Integer detectarColumnaRaton() {
		double mouseX = StdDraw.mouseX(); // Posición del ratón en X
		double mouseY = StdDraw.mouseY(); // Posición del ratón en Y
		if (mouseX <= zonaDeJuego.getSuperiorIzquierda().getPositionX()
				|| mouseX >= zonaDeJuego.getInferiorDerecha().getPositionX()
				|| mouseY <= zonaDeJuego.getInferiorDerecha().getPositionY()
				|| mouseY >= zonaDeJuego.getSuperiorIzquierda().getPositionY()) {
			return null; // Ratón fuera del tablero
		}
		double distanciaBordeIzquierdo = (mouseX - zonaDeJuego.getSuperiorIzquierda().getPositionX()) / TAM_CASILLA; // Valor 0 y 6,99
		return (int) distanciaBordeIzquierdo; // Convertimos a int para truncar decimales
	}

	private static void resaltarColumna(Integer columnaRaton) {
		Color colorResaltado = null;
		// Asignamos un color a cada turno
		if (turno == 1)
			colorResaltado = new Color(255, 255, 0, 70);
		else
			colorResaltado = new Color(255, 0, 0, 70);
		// Construimos rectángulo que rodee la columna. Tenrdá el ancho de una columna (TAM_CASILLA)
		Punto superiorIzquierda = new Punto(
				zonaDeJuego.getSuperiorIzquierda().getPositionX() + columnaRaton * TAM_CASILLA, // Coordenada X de cada esquina superior izquierda de cada columna
				zonaDeJuego.getSuperiorIzquierda().getPositionY(), colorResaltado); // Coordenada Y de cada esquina superior izquierda de cada columna
		Punto inferiorDerecha = new Punto(
				zonaDeJuego.getSuperiorIzquierda().getPositionX() + columnaRaton * TAM_CASILLA + TAM_CASILLA, // Coordenada X de cada esquina inferior derecha de cada columna
				zonaDeJuego.getInferiorDerecha().getPositionY()); // Coordenada Y de cada esquina inferior derecha de cada columna
		// Creamos y dibujamos el rectángulo
		Rectangulo resaltado = new Rectangulo(superiorIzquierda, inferiorDerecha);
		resaltado.dibujarRectangulo();
	}

	private static void introducirFicha(int jugador, int columnaRaton) {
		if (jugador < 1 || jugador > 2)
			throw new IllegalArgumentException("El jugador debe ser 1 ó 2");
		if (columnaRaton < 0 || columnaRaton > 6)
			throw new IllegalArgumentException("La columna debe estar entre 0 y 6");
		if (tablero[0][columnaRaton] != null)
			throw new ExceptionColumnaLlena("La columna " + columnaRaton + " está llena");
		// Creamos ficha amarilla o roja
		Circulo ficha = new Circulo(0, 0, TAM_CASILLA * 0.8 / 2);
		if (jugador == 1)
			ficha.setColor(Color.YELLOW);
		else
			ficha.setColor(Color.RED);
		// Recorremos las filas de abajo hacia arriba, en el primer hueco(null) colocamos la ficha
		for (int i = tablero.length - 1; i >= 0; i--) {
			if (tablero[i][columnaRaton] == null) {
				tablero[i][columnaRaton] = ficha;
				return;
			}
		}
	}

	// Devuelve verdadero si hay 4 en raya en cualquier dirección
	private static boolean hayVictoria() {
		// Análisis horizontales
		for (int i = 0; i < tablero.length; i++) {
			int contador = 0;
			for (int j = 1; j < tablero[0].length; j++) {
				if (tablero[i][j] != null && tablero[i][j - 1] != null) { // Comprobamos que haya ficha a su izquierda (j-1)
					if (tablero[i][j].getCentro().getColor().equals(tablero[i][j - 1].getCentro().getColor())) { // Comparamos que sea del mismo color
						contador++; // Aumentamos el contador de coincidencias
						if (contador == 3) { // 4 en raya
							return true;
						}
					} else { // Tirene color diferente
						contador = 0;
					}
				} else { // Hay un hueco
					contador = 0;
				}
			}
		}

		// Análisis verticales
		for (int j = 0; j < tablero[0].length; j++) {
			int contador = 0;
			for (int i = 1; i < tablero.length; i++) {
				if (tablero[i][j] != null && tablero[i - 1][j] != null) { // Comprobamos que haya ficha arriba (i-1)
					if (tablero[i][j].getCentro().getColor().equals(tablero[i - 1][j].getCentro().getColor())) { // Comparamos que sea del mismo color
						contador++; // Aumentamos el contador de coincidencias
						if (contador == 3) { // 4 en raya
							return true;
						}
					} else { // Tirene color diferente
						contador = 0;
					}
				} else { // Hay un hueco
					contador = 0;
				}
			}
		}

		// Análisis diagonales \ que nacen en la parte superior
		int fila = 0;
		for (int columna = 0; columna < tablero[0].length; columna++) {
			int contador = 0;
			for (int i = fila + 1, j = columna + 1; i < tablero.length && j < tablero[0].length; i++, j++) {
				if (tablero[i][j] != null && tablero[i - 1][j - 1] != null) { // Comprobamos que haya ficha arriba (i-1)
					if (tablero[i][j].getCentro().getColor().equals(tablero[i - 1][j - 1].getCentro().getColor())) { // Comparamos que sea del mismo color
						contador++; // Aumentamos el contador de coincidencias
						if (contador == 3) { // 4 en raya
							return true;
						}
					} else { // Tirene color diferente
						contador = 0;
					}
				} else { // Hay un hueco
					contador = 0;
				}
			}
		}

		// Análisis diagonales \ que nacen en el lateral izquierdo
		int columna = 0;
		for ( fila = 0; fila < tablero.length; fila++) {
			int contador = 0;
			for (int i = fila + 1, j = columna + 1; i < tablero.length && j < tablero[0].length; i++, j++) {
				if (tablero[i][j] != null && tablero[i - 1][j - 1] != null) { // Comprobamos que haya ficha arriba (i-1)
					if (tablero[i][j].getCentro().getColor().equals(tablero[i - 1][j - 1].getCentro().getColor())) { // Comparamos que sea del mismo color
						contador++; // Aumentamos el contador de coincidencias
						if (contador == 3) { // 4 en raya
							return true;
						}
					} else { // Tirene color diferente
						contador = 0;
					}
				} else { // Hay un hueco
					contador = 0;
				}
			}
		}
		
		// Análisis diagonales / que nacen en la parte superior
		fila = 0;
		for (columna = 0; columna < tablero[0].length; columna++) {
			int contador = 0;
			for (int i = fila + 1, j = columna - 1; i < tablero.length && j >= 0; i++, j--) {
				if (tablero[i][j] != null && tablero[i - 1][j + 1] != null) { // Comprobamos que haya ficha arriba (i-1)
					if (tablero[i][j].getCentro().getColor().equals(tablero[i - 1][j + 1].getCentro().getColor())) { // Comparamos que sea del mismo color
						contador++; // Aumentamos el contador de coincidencias
						if (contador == 3) { // 4 en raya
							return true;
						}
					} else { // Tirene color diferente
						contador = 0;
					}
				} else { // Hay un hueco
					contador = 0;
				}
			}
		}
		
		// Análisis diagonales / que nacen en la parte superior
		columna = tablero[0].length - 1;
		for (fila = 0; fila < tablero.length; fila++) {
			int contador = 0;
			for (int i = fila + 1, j = columna - 1; i < tablero.length && j >= 0; i++, j--) {
				if (tablero[i][j] != null && tablero[i - 1][j + 1] != null) { // Comprobamos que haya ficha arriba (i-1)
					if (tablero[i][j].getCentro().getColor().equals(tablero[i - 1][j + 1].getCentro().getColor())) { // Comparamos que sea del mismo color
						contador++; // Aumentamos el contador de coincidencias
						if (contador == 3) { // 4 en raya
							return true;
						}
					} else { // Tirene color diferente
						contador = 0;
					}
				} else { // Hay un hueco
					contador = 0;
				}
			}
		}
		return false;
	}
	
	// Devuelve verdadero si el tablero está lleno
	private static boolean tableroLleno() {
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[0].length; j++) {
				if (tablero[i][j] == null) {
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {

		long tiempoInicio = System.currentTimeMillis(); // Tomamos el tiempo en el momento en el que se inicia el programa para posteriormente calcular el tiempo de ejecución

		tablero = new Circulo[6][7]; // Creamos el tablero
		Punto esquinaSuperiorIzquierda = new Punto(-88, 70, Color.BLUE); // Vértice superior izquierdo de la zona de juego
		zonaDeJuego = new Rectangulo( // Creamos la zona de juego (rectángulo azul)
				esquinaSuperiorIzquierda, // Esquina superior izquierda
				new Punto(esquinaSuperiorIzquierda.getPositionX() + tablero[0].length * TAM_CASILLA, // Coordenada X esquina inferior derecha
						  esquinaSuperiorIzquierda.getPositionY() - tablero.length * TAM_CASILLA)); // Coordenada Y esquina inferior derecha

		// Establecemos margen de coordenadas
		StdDraw.setXscale(-100, 100);
		StdDraw.setYscale(-100, 100);

		StdDraw.enableDoubleBuffering();
		boolean recienPulsado = false;
		boolean finPartida = false;

		// Bucle de animación
		while (!finPartida) {
			// Dibujamos el tablero según su contenido
			dibujarTablero();
			
			// Informamos de a quien le toca jugar
			StdDraw.setPenColor(Color.BLACK);
			StdDraw.text(0, 90, "Turno jugador " + turno);
			
			// Si la columna está llena mostramos el mensaje de error por pantalla
			if (advertencia != null) {
				StdDraw.setPenColor(Color.RED);
				StdDraw.text(0, 80, advertencia); 
			}
			
			// Detectamos la columna que corresponde a la posición del ratón (null si no
			// está sobre ninguna)
			Integer columnaRaton = detectarColumnaRaton();
			if (columnaRaton != null) { // Ratón dentro de zona de juego
				resaltarColumna(columnaRaton); // Resaltamos la columna que corresponde a la posición del ratón
				if (StdDraw.isMousePressed()) { // Evento: click del ratón
					if (!recienPulsado) { // Ratón pulsado
						try {
							introducirFicha(turno, columnaRaton); // Introducimos la ficha
							advertencia = null; // Volvemos a poner la advertencia a null para que deje de mostrarse
								if (hayVictoria()) { // Evaluamos si hay combinación ganadora
									finPartida = true; // Si la hay salimos del bucle
								} else { // Cambio de jugador
									if (turno == 1) {
										turno = 2;
									} else {
										turno = 1;
									}
								}
								if (tableroLleno()) {
									finPartida = true;
								}
						} catch (ExceptionColumnaLlena e) { 
							System.out.println(e.getMessage());
							advertencia = e.getMessage();	
						}
					}
					recienPulsado = true;
				} else
					recienPulsado = false;
			}

			// Fotograma: mostramos, pausamos y limpiamos la interfaz
			StdDraw.show();
			StdDraw.pause(10);
			StdDraw.clear();
		}

		long tiempoFin = System.currentTimeMillis(); // Cogemos el tiempo al final de la partida para calcular el tiempo de ejecución
		long tiempoEjecucion = tiempoFin - tiempoInicio; // Tiempo de ejecución
		long segundos = tiempoEjecucion / 1000; // Pasamos a segundos
		long minutos = 0; // Inicializamos minutos a cero
		if (segundos > 60) { // Si los segundos son mayores de 60 segundos pasamos a minutos
			minutos = segundos / 60; // Calculamos minutos
			segundos = segundos % 60; // Calculamos segundos
		}

		// Pantalla fin
		StdDraw.clear();
		StdDraw.setPenColor(Color.BLACK);
		if (hayVictoria()) {
			StdDraw.text(0, 90, "Ganador: jugador " + turno);
		}
		if (tableroLleno()) {
			StdDraw.text(0, 90, "EMPATE");
		}
		StdDraw.text(0, 80, "La partida ha durado " + minutos + ":" + segundos);
		dibujarTablero();
		StdDraw.show();
		StdDraw.pause(5000);
		System.exit(0); 
	}
}
