import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameCanvas extends JPanel {
	BufferedImage backBuffered;
	Graphics graphics;

	Background background;
	List<Star> stars;
	List<Enemy> enemies;
	EnemyRoundAttack enemyRoundAttack;
	public Player player;
	private Random random = new Random();
	private int countStar = 0;
	private int countEnemy = 0;

	public GameCanvas() {
		this.setSize(1024, 600);
		this.setupBackBuffered();
		this.setupCharacter();

		this.setVisible(true);
	}

	private void setupBackBuffered() {
		this.backBuffered = new BufferedImage(1024, 600, BufferedImage.TYPE_4BYTE_ABGR);
		this.graphics = this.backBuffered.getGraphics();
	}

	private void setupCharacter() {
		this.background = new Background();
		this.background.color = Color.BLACK;
		this.setEnemyRoundAttack();
		this.setupEnemies();
		this.setupPlayer();
		this.setupStar();
	}

	private void setupEnemies() {
		this.enemies = new ArrayList<>();
	}

	private void setEnemyRoundAttack() {
		this.enemyRoundAttack = new EnemyRoundAttack();
		this.enemyRoundAttack.position.set(random.nextInt(1024), random.nextInt(600));
		this.enemyRoundAttack.velocity.set(4, 0);
	}

	private void setupPlayer() {
		this.player = new Player();
		this.player.position.set(500, 300);
		this.player.velocity.set(4, 0);
	}

	private void setupStar() {
		this.stars = new ArrayList<>();

	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(this.backBuffered, 0, 0, null);
	}

	public void renderAll() {
		this.background.render(this.graphics);

		this.stars.forEach(star -> star.render(graphics));

		this.enemies.forEach(enemy -> enemy.render(graphics));

		this.enemyRoundAttack.render(graphics);

		this.player.render(this.graphics);

		this.repaint();
	}

	public void runAll() {
		this.createStar();
        this.stars.forEach(star -> star.run());
        this.createEnemy();
        this.enemies.forEach(enemy -> {
            Vector2D velocity = player.position.subtract(enemy.position).normalize()
                    .multiply(2);
            enemy.velocity.set(velocity);
        });
        Vector2D velocity = player.position.subtract(enemyRoundAttack.position).normalize()
                .multiply(2);
        this.enemyRoundAttack.velocity.set(velocity);
        this.enemyRoundAttack.run();
        this.enemies.forEach(enemy -> enemy.run());
        this.player.run();
	}

	private void createStar() {
		if (this.countStar == 30) {
			Star star = new Star();
			star.image = this.loadImage("resources/images/star.png");
			star.position.set(1024, this.random.nextInt(600));
			star.velocity.set(-(this.random.nextInt(3) + 1), 0);
			star.width = 5;
			star.height = 5;
			this.stars.add(star);
			this.countStar = 0;
		} else {
			this.countStar += 1;
		}

	}

	private void createEnemy() {
		if (this.countEnemy == 200) {
			Enemy enemy = new Enemy();
			enemy.position.set(this.random.nextInt(1024), this.random.nextInt(600));
			this.enemies.add(enemy);
			this.countEnemy = 0;
		} else {
			this.countEnemy += 1;
		}
	}

	private BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
