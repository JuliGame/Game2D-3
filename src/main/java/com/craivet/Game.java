package com.craivet;

import javax.swing.*;
import java.awt.*;

import com.craivet.entity.Player;
import com.craivet.input.KeyHandler;
import com.craivet.object.SuperObject;
import com.craivet.tile.TileManager;

public class Game extends JPanel implements Runnable {

	// Screen settings
	final int originalTileSize = 16; // 16x16 tile
	final int scale = 3;

	public final int tileSize = originalTileSize * scale; // 48x48 tile
	// ¿Cuantos tiles se pueden mostrar en una sola pantalla horizontal y verticalmente?
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
	public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

	// World settings
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;

	Thread gameThread;
	KeyHandler keyHandler = new KeyHandler();
	TileManager tileManager = new TileManager(this);
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public Player player = new Player(this, keyHandler);
	public SuperObject[] objs = new SuperObject[10];

	final int fps = 60;

	public Game() {
		setPreferredSize(new Dimension(screenWidth, screenHeight));
		setBackground(Color.black);
		// Mejora el rendimiento de representacion (es algo parecido al metodo getBufferStrategy() de Canvas)
		setDoubleBuffered(true);
		addKeyListener(keyHandler);
		setFocusable(true);
	}

	@Override
	public void run() {

		setup();

		// Intervalo de tiempo entre cada frame aplicando la unidad de tiempo en nanosegundos y 60 fps
		double drawInterval = 1e9 / fps;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int frames = 0, ticks = 0;
		boolean shouldRender = false;

		while (gameThread != null) {

			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			timer += currentTime - lastTime;
			lastTime = currentTime;

			if (delta >= 1) {
				frames++;
				update();
				repaint();
				delta--;
				// shouldRender = true;
			}

			/* try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} */

			/* if (shouldRender) {
				repaint();
				frames++;
			} */

			if (timer >= 1e9) {
				System.out.println(frames + " fps");
				timer = 0;
				frames = 0;
			}

		}

	}

	public void setup() {
		aSetter.setObject();
	}

	public void update() {
		player.update();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		/* La clase Graphics2D extiende la clase Graphics para proporcionar un control mas sofisticado sobre la
		 * geometria, las transformaciones de coordenadas, la gestion del color y el diseño del texto. */
		Graphics2D g2 = (Graphics2D) g;

		tileManager.draw(g2);

		for (SuperObject obj : objs)
			if (obj != null) obj.draw(g2, this);

		player.draw(g2);

		g2.dispose(); // Desecha este contexto de graficos y libera cualquier recurso del sistema que este utilizando
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
}

