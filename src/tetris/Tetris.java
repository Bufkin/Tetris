package tetris;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Tetris extends JComponent {

	private static final long serialVersionUID = 279494108361487144L;

	static ArrayList<BufferedImage> tiles;
	static BufferedImage tilesIMG;
	static BufferedImage backgroundIMG;
	static BufferedImage frameIMG;

	static int x;
	static int y;
	static int size;

	public static void main(String[] args) throws IOException {

		tilesIMG = ImageIO.read(new File("./img/tiles.png"));
		backgroundIMG = ImageIO.read(new File("./img/background.png"));
		frameIMG = ImageIO.read(new File("./img/frame.png"));

		Tetris.size = 18;
		Tetris.x = 27 + (4 * Tetris.size);
		Tetris.y = 30;
		loadTextures();

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException ex) {
					ex.printStackTrace();
				}

				JFrame frame = new JFrame("Testing");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				Random r = new Random();

				switch (r.nextInt((6 - 0) + 1) + 0) {
					// O-Block
					case 0:
						frame.add(new Shapes('O', Tetris.x, Tetris.y, Tetris.size));
						break;

					// I-Block
					case 1:
						frame.add(new Shapes('I', Tetris.x, Tetris.y, Tetris.size));
						break;

					// J-Block
					case 2:
						frame.add(new Shapes('J', Tetris.x, Tetris.y, Tetris.size));
						break;

					// L-Block
					case 3:
						frame.add(new Shapes('L', Tetris.x, Tetris.y, Tetris.size));
						break;

					// S-Block
					case 4:
						frame.add(new Shapes('S', Tetris.x, Tetris.y, Tetris.size));
						break;

					// T-Block
					case 5:
						frame.add(new Shapes('T', Tetris.x, Tetris.y, Tetris.size));
						break;

					// Z-Block
					case 6:
						frame.add(new Shapes('Z', Tetris.x, Tetris.y, Tetris.size));
						break;
					default:
						System.out.println("Unexpected value!");
						break;
				}

				frame.setSize(new Dimension(320, 480));
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

	public static void loadTextures() {
		int xTemp = 0;

		Tetris.tiles = new ArrayList<>();

		for (int x = 1; x <= 8; x++) {
			Tetris.tiles.add(Tetris.tilesIMG.getSubimage(xTemp, 0, Tetris.size, Tetris.size));
			xTemp += Tetris.size;
		}
	}
}
