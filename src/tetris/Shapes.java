package tetris;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Timer;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class Shapes extends JPanel {

	private static final long serialVersionUID = 8210398347281559014L;

	private int x;
	private int y;
	private int size;
	BufferedImage image;
	BufferedImage txtr;
	private char shape;
	Random r = new Random();
	Timer timerObj = new Timer(true);

	private static final int TIMER_DELAY = 170;

	public Shapes(char shape, int x, int y, int size) {
		this.shape = shape;
		this.x = x;
		this.y = y;
		this.size = size;
		this.image = Tetris.tiles.get(this.r.nextInt((7 - 0) + 1) + 0);

		this.bindKeyStrokeTo(WHEN_IN_FOCUSED_WINDOW, "down", KeyStroke.getKeyStroke(KeyEvent.VK_S, 0),
				new AbstractAction() {

					private static final long serialVersionUID = -1883407745108685456L;

					@Override
					public void actionPerformed(ActionEvent e) {

						if (Shapes.this.y <= 342) {
							Shapes.this.y += Tetris.size;
						}

						if ((Shapes.this.y + Tetris.size) > Shapes.this.getHeight()) {
							Shapes.this.y = Shapes.this.getHeight() - Tetris.size;
						}
						Shapes.this.repaint();
					}
				});
		this.bindKeyStrokeTo(WHEN_IN_FOCUSED_WINDOW, "left", KeyStroke.getKeyStroke(KeyEvent.VK_A, 0),
				new AbstractAction() {

					private static final long serialVersionUID = -1883407745108685456L;

					@Override
					public void actionPerformed(ActionEvent e) {
						Shapes.this.x -= Tetris.size;
						if ((Shapes.this.x + Tetris.size) > Shapes.this.getWidth()) {
							Shapes.this.x = Shapes.this.getHeight() - Tetris.size;
						}
						Shapes.this.repaint();
					}
				});
		this.bindKeyStrokeTo(WHEN_IN_FOCUSED_WINDOW, "right", KeyStroke.getKeyStroke(KeyEvent.VK_D, 0),
				new AbstractAction() {

					private static final long serialVersionUID = -1883407745108685456L;

					@Override
					public void actionPerformed(ActionEvent e) {
						Shapes.this.x += Tetris.size;
						if ((Shapes.this.x + Tetris.size) > Shapes.this.getWidth()) {
							Shapes.this.x = Shapes.this.getHeight() - Tetris.size;
						}
						Shapes.this.repaint();
					}
				});
		this.bindKeyStrokeTo(WHEN_IN_FOCUSED_WINDOW, "rotate", KeyStroke.getKeyStroke(KeyEvent.VK_R, 0),
				new AbstractAction() {

					private static final long serialVersionUID = -1883407745108685456L;

					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("rotate");
						Shapes.this.repaint();
					}
				});

		new javax.swing.Timer(TIMER_DELAY, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Shapes.this.updateShape();
			}
		}).start();

	}

	public void bindKeyStrokeTo(int condition, String name, KeyStroke keyStroke, Action action) {
		InputMap im = this.getInputMap(condition);
		ActionMap am = this.getActionMap();

		im.put(keyStroke, name);
		am.put(name, action);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(Tetris.size, Tetris.size);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Zeichnen des Hintergrunds
		g.drawImage(Tetris.backgroundIMG, 0, 0, this);

		switch (this.shape) {
			case 'O':

				int[] xPoints = {
						0, 2 * Shapes.this.size, 2 * Shapes.this.size, 0
				};
				int[] yPoints = {
						0, 0, 2 * Shapes.this.size, 2 * Shapes.this.size
				};

				Polygon p = new Polygon(xPoints, yPoints, 4);
				Shape shape = p.getBounds();
				Shapes.this.txtr = Shapes.this.getTexturedImage((Graphics2D) g, Shapes.this.image, shape, Shapes.this.x,
						Shapes.this.y);

				g.drawImage(Shapes.this.txtr, Shapes.this.x, Shapes.this.y, Shapes.this);

				g.drawImage(this.image, this.x, this.y, Color.WHITE, this);
				g.drawImage(this.image, this.x + this.size, this.y, Color.WHITE, this);
				g.drawImage(this.image, this.x, this.y + this.size, Color.WHITE, this);
				g.drawImage(this.image, this.x + this.size, this.y + this.size, Color.WHITE,
						this);

				break;

			case 'I':
				g.drawImage(this.image, this.x, this.y, Color.WHITE, this);
				g.drawImage(this.image, this.x, this.y + this.size, Color.WHITE, this);
				g.drawImage(this.image, this.x, this.y + (this.size * 2), Color.WHITE, this);
				g.drawImage(this.image, this.x, this.y + (this.size * 3), Color.WHITE, this);
				break;

			case 'J':
				g.drawImage(this.image, this.x, this.y, Color.WHITE, this);
				g.drawImage(this.image, this.x, this.y + this.size, Color.WHITE, this);
				g.drawImage(this.image, this.x, this.y + (this.size * 2), Color.WHITE, this);
				g.drawImage(this.image, this.x - this.size, this.y + (this.size * 2), Color.WHITE, this);
				break;

			case 'L':
				g.drawImage(this.image, this.x, this.y, Color.WHITE, this);
				g.drawImage(this.image, this.x, this.y + this.size, Color.WHITE, this);
				g.drawImage(this.image, this.x, this.y + (this.size * 2), Color.WHITE, this);
				g.drawImage(this.image, this.x + this.size, this.y + (this.size * 2), Color.WHITE, this);
				break;

			case 'S':
				g.drawImage(this.image, this.x, this.y + this.size, Color.WHITE, this);
				g.drawImage(this.image, this.x + this.size, this.y + this.size, Color.WHITE, this);
				g.drawImage(this.image, this.x + this.size, this.y, Color.WHITE, this);
				g.drawImage(this.image, this.x + (this.size * 2), this.y, Color.WHITE, this);
				break;

			case 'T':
				g.drawImage(this.image, this.x, this.y, Color.WHITE, this);
				g.drawImage(this.image, this.x, this.y + this.size, Color.WHITE, this);
				g.drawImage(this.image, this.x, this.y + (this.size * 2), Color.WHITE, this);
				g.drawImage(this.image, this.x + this.size, this.y + this.size, Color.WHITE, this);
				break;

			case 'Z':
				g.drawImage(this.image, this.x, this.y, Color.WHITE, this);
				g.drawImage(this.image, this.x + this.size, this.y, Color.WHITE, this);
				g.drawImage(this.image, this.x + this.size, this.y + this.size, Color.WHITE, this);
				g.drawImage(this.image, this.x + (this.size * 2), this.y + this.size, Color.WHITE, this);
				break;

			default:
				throw new IllegalArgumentException("Unexpected value: " + this.shape);
		}

		g.drawImage(Tetris.frameIMG, 0, 0, this);
	}

	private BufferedImage getTexturedImage(Graphics2D g, BufferedImage src, Shape shp, int x, int y) {
		Rectangle r = shp.getBounds();

		BufferedImage tmp = new BufferedImage(r.width + 2, r.height + 2, BufferedImage.TYPE_INT_ARGB);
		g = tmp.createGraphics();

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);

		AffineTransform centerTransform = AffineTransform.getTranslateInstance(-r.x + 1, -r.y + 1);
		g.setTransform(centerTransform);
		g.setClip(shp);
		g.drawImage(src, x, y, null);
		g.setClip(null);
		g.setColor(Color.RED);
		g.setStroke(new BasicStroke(1f));
		g.draw(shp);
		g.dispose();

		return tmp;
	}

	private void updateShape() {

		// 20 Kästchen hoch
		if (this.y >= 342) {
			// Ende
		} else {
			this.y += Tetris.size;
		}
		Shapes.this.repaint();
	}
}
