package model.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.model.Border;
import model.model.Composition;
import model.model.Compositor;
import model.model.Area;
import model.model.Block;
import model.model.Row;
import model.model.SimpleComposition;
import model.model.SimpleCompositor;

public class Labyrinth extends JPanel {

	private static final long serialVersionUID = -5372667045344272675L;

	private Composition content;
	private KeyListener keyListener;
	private List<Area> rows;
	private JButton reset;
	private List<Border> borders;
	private int maxWidth = 0;
	private int maxHeight = 0;
	private int[][] pozicijaZastavice = new int[1][2];
	private int[][] pozicijaIgraca = new int[1][2];
	private JPanel renderingArea;
	private int stepsFromPreviousGames = 0;
	private boolean playing;

	public Labyrinth(Compositor compositor) {
		loadBordersfromFile("resources/borders3.txt");
		initModel(compositor);
		remberFinishFlagAndWalkerCoordinates();
		fillRows();
		addWalkerAndFinishFlagIntoRow();
		initEditor();
		keyListener = new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (playing) {
					rows = ((SimpleComposition) content).move(e.getKeyCode());
					renderingArea.removeAll();
					fillRenderingArea();
					validate();
					repaint();
				}
			}

		};
		addKeyListener(keyListener);
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reset();

			}
		});
	}

	private void reset() {
		stepsFromPreviousGames = ((SimpleComposition) content).getSteps();
		resetLabelsAndColors();
		addWalkerAndFinishFlagIntoRow();
		renderingArea.removeAll();
		fillRenderingArea();
		revalidate();
		repaint();
		playing = true;
	}

	private void remberFinishFlagAndWalkerCoordinates() {
		pozicijaZastavice[0][0] = borders.get(borders.size() - 1).getI();
		pozicijaZastavice[0][1] = borders.get(borders.size() - 1).getJ();
		pozicijaIgraca[0][0] = borders.get(0).getI();
		pozicijaIgraca[0][1] = borders.get(0).getJ();
	}

	private void fillRows() {
		int brojac = 0;
		for (int i = 0; i < maxHeight; i++) {
			for (int j = 0; j < maxWidth; j++) {
				if (brojac < borders.size() && borders.get(brojac).getI() == i && borders.get(brojac).getJ() == j) {
					rows = content.insert(Area.create("", borders.get(brojac)));
					brojac++;
				} else {
					rows = content.insert(Area.create("rupa", new Border(i, j, "rupa")));
				}
			}
		}
	}

	private void addWalkerAndFinishFlagIntoRow() {
		((Block) (((Row) rows.get(pozicijaIgraca[0][0])).getChildren().get(pozicijaIgraca[0][1]))).setLabel("walker");
		((Block) (((Row) rows.get(pozicijaZastavice[0][0])).getChildren().get(pozicijaZastavice[0][1])))
				.setLabel("finish");
	}

	private void setBorderForPanel(JPanel panel, String border) {
		switch (border) {
		case "bottom":
			panel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
			break;
		case "left":
			panel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.black));
			break;
		case "both":
			panel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0, Color.black));
			break;
		}
	}

	private void addIconsToPanel(JPanel panel, String type) {
		if (type.equals("walker")) {
			panel.add(new JLabel(new ImageIcon("resources/walker.png")));
		}
		if (type.equals("rupa")) {
			panel.setBackground(Color.black);
		}
		if (type.equals("finish")) {
			panel.add(new JLabel(new ImageIcon("resources/finish.png")));
		}
	}

	private boolean checkIfWalkerOnFinish(Area value) {
		if ((getPositionOfArea(value) == pozicijaZastavice[0][0] * maxWidth + pozicijaZastavice[0][1])
				&& (((Block) value).getLabel().equals("walker"))) {
			playing = false;
			return true;
		}
		return false;
	}

	private void fillRenderingArea() {
		boolean finish = false;
		for (Area row : rows) {
			for (Area value : ((Row) row).getChildren()) {
				JPanel test = new JPanel(new BorderLayout());
				test.setBackground(((Block) value).getBoja());
				setBorderForPanel(test, ((Block) value).getBorder().getType());
				addIconsToPanel(test, ((Block) value).getLabel());
				renderingArea.add(test);
				if (checkIfWalkerOnFinish(value)) {
					finish = true;
				}
			}
		}
		if (finish) {
			((JPanel) renderingArea.getComponent(pozicijaZastavice[0][0] * maxWidth + pozicijaZastavice[0][1]))
					.add(new JLabel(new ImageIcon("resources/walker.png")));
			renderingArea.revalidate();
			if (JOptionPane.showConfirmDialog(Labyrinth.this,
					"Zavrsili ste igru u " + (((SimpleComposition) content).getSteps() - stepsFromPreviousGames)
							+ " koraka!\nZelite li da igrate ispocetka?",
					"Kraj igre", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
				System.out.println("BORJ KOMPONENATA2 " + renderingArea.getComponentCount());
				reset();
			}
		}

	}

	private void resetLabelsAndColors() {
		for (Area row : rows) {
			for (Area value : ((Row) row).getChildren()) {
				if (((Block) value).getLabel().equals("walker") || ((Block) value).getLabel().equals("finish")) {
					((Block) value).setLabel("");
				}
				if (((Block) value).getBoja().equals(Color.red)) {
					((Block) value).setBoja(Color.white);
					;
				}
			}
		}
	}

	private void initEditor() {
		setLayout(new BorderLayout());
		setFocusable(true);
		playing = true;
		renderingArea = new JPanel(new GridLayout(maxHeight, maxWidth, 0, 0));
		fillRenderingArea();
		reset = new JButton("Pocni ispocetka");
		reset.setFocusable(false);
		add(renderingArea, BorderLayout.CENTER);
		add(reset, BorderLayout.SOUTH);
		validate();
		repaint();
	}

	private void initModel(Compositor compositor) {
		content = new SimpleComposition(compositor, maxWidth, maxHeight);
		((SimpleCompositor) compositor).setROW_LENGTH(maxWidth);
	}

	private int getPositionOfArea(Area a) {
		return ((Block) a).getBorder().getI() * maxWidth + ((Block) a).getBorder().getJ();
	}

	private void loadBordersfromFile(String fileName) {
		borders = new LinkedList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName));) {
			String line = reader.readLine();
			while (line != null) {
				String[] tokens = line.split(",");
				int i = Integer.parseInt(tokens[0].trim());
				if (i + 1 > maxHeight)
					maxHeight = i + 1;
				int j = Integer.parseInt(tokens[1].trim());
				if (j + 1 > maxWidth)
					maxWidth = j + 1;
				borders.add(new Border(i, j, tokens[2].trim()));
				line = reader.readLine();
			}

		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	public int getMaxWidth() {
		return maxWidth;
	}

	public int getMaxHeight() {
		return maxHeight;
	}

}
