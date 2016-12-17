import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;

public class Paddle implements EventHandler<MouseEvent> {
	Image img = new Image("paddle.png");
	int x;
	double width = img.getWidth();
	double height = img.getHeight();
	
	@Override
	public void handle(MouseEvent e) {
		x = (int) (e.getSceneX() - (width / 2));
	}
	
	public void draw(GraphicsContext gc) {
		gc.drawImage(img, x, 600 - 32);
	}
	
	public Rectangle2D getRect() {
		return new Rectangle2D(x, 568, width, height);
	}
}
