import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;

public class Ball {
	Image img = new Image("ball.png");
	
	double x;
	double y;
	double dx;
	double dy;
	
	double width = img.getWidth();
	double height = img.getHeight();
	
	public Ball() {
		reset();
	}
	
	private void reset() {
		x = 0;
		y = 0;
		dx = 10;
		dy = 8;
	}
	
	public void update() {
		if (x + width + dx > Main.SCREEN_W) {
			dx *= -1;
		}
		if (y + height + dy > Main.SCREEN_H) {
			Main.showAlert("Du suger!");
			reset();
		}
		if (x + dx < 0) {
			dx *= -1;
		}
		if (y + dy < 0) {
			dy *= -1;
		}
		
		x += dx;
		y += dy;
	}
	
	public void bounceOnPaddle() {
		dy *= -1;
	}
	
	public void draw(GraphicsContext gc) {
		gc.drawImage(img, x, y);
	}
	
	public Rectangle2D getRect() {
		return new Rectangle2D(x, y, width, height);
	}
}
