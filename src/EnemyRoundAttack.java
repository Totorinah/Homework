import java.awt.Graphics;

public class EnemyRoundAttack {
	 public Vector2D position;
	    public Vector2D velocity;
	    private ImageRenderer renderer;
	    private EnemyShoot enemyShoot;
	    public EnemyRoundAttack(){
	        this.position = new Vector2D();
	        this.velocity = new Vector2D();
	        this.renderer = new ImageRenderer("resources/images/circle.png",20,20);
	        this.enemyShoot = new EnemyShoot();
	    }


	    public void run(){
	        this.position.addUp(this.velocity);
	        this.enemyShoot.run(this);
	        this.enemyShoot.bulletEnemies.forEach(bulletEnemy -> bulletEnemy.run());

	    }

	    public void render(Graphics graphics){
	        this.renderer.render(graphics,this.position);
	        this.enemyShoot.bulletEnemies.forEach(bulletEnemy -> bulletEnemy.render(graphics));
	    }
}
