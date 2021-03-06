import java.awt.Graphics;

public class BulletPlayer {
	  public Vector2D position;
	    public Vector2D velocity;
	    private ImageRenderer renderer;

	    public BulletPlayer(){
	        this.position = new Vector2D();
	        this.velocity = new Vector2D();
	        this.renderer = new ImageRenderer("resources/images/circle.png",4,4);
	    }

	    public void run(){
	        this.position.addUp(this.velocity);
	    }

	    public void render(Graphics graphics){
	        this.renderer.render(graphics,this.position );
	    }
}
