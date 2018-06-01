import java.util.ArrayList;
import java.util.List;

public class EnemyShoot {
	public List<BulletEnemy> bulletEnemies;
    private int countBullet = 0;

	public EnemyShoot() {
		this.bulletEnemies = new ArrayList<>();
	}

	public void run(EnemyRoundAttack enemy) {
		double angleBullet = 0;
		if (countBullet == 100) {
			for (int i = 0; i < 12; i++) {
				BulletEnemy bulletEnemy = new BulletEnemy();
				angleBullet += 30;
				bulletEnemy.velocity = new Vector2D(5, 0).rotate(angleBullet);
				bulletEnemy.position.set(enemy.position);
				this.bulletEnemies.add(bulletEnemy);
			}

			countBullet = 0;
			angleBullet = 0;

		} else {
			countBullet += 1;
		}
		
		bulletEnemies.removeIf(bullet -> bullet.position.x <0 || bullet.position.x >1024 
								|| bullet.position.y<0 ||bullet.position.y >1024);
		
	}

}
