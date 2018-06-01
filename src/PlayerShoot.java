import java.util.ArrayList;
import java.util.List;

public class PlayerShoot {
	public List<BulletPlayer> bulletsPlayer;
	private int countBullet = 0;

	public PlayerShoot() {
		this.bulletsPlayer = new ArrayList<>();
	}

	public void run(Player player) {
		if (countBullet == 4) {
			BulletPlayer bulletPlayer = new BulletPlayer();
			bulletPlayer.position.set(player.position);
			bulletPlayer.velocity.set(player.velocity.add(player.velocity.normalize().multiply(2)));

			this.bulletsPlayer.add(bulletPlayer);
			countBullet = 0;
		} else {
			countBullet += 1;
		}
		
		bulletsPlayer.removeIf(bullet -> bullet.position.x <0 || bullet.position.x >1024 
				|| bullet.position.y<0 ||bullet.position.y >1024);
	}
}
