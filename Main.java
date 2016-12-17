import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert;

public class Main extends Application {
	public static final int SCREEN_W = 800;
	public static final int SCREEN_H = 600;
	
	double horizontalV = 10;
	double verticalV = 8;
	double xPos = 0;
	double yPos = 0;
	
	GraphicsContext gc;
	static AnimationTimer gameLoop;
	
	@Override
	public void start(Stage theStage) {
		theStage.setTitle("Kasseknuser");
		
		Group root = new Group();
		Scene theScene = new Scene(root);
		theStage.setScene(theScene);
		
		Canvas canvas = new Canvas(SCREEN_W, SCREEN_H);
		root.getChildren().add(canvas);
		
		gc = canvas.getGraphicsContext2D();
		
		Ball ball = new Ball();
		Paddle paddle = new Paddle();
		
		canvas.addEventHandler(MouseEvent.MOUSE_MOVED, paddle);
		
		final long START_NANO_TIME = System.nanoTime();
		
		gameLoop = new AnimationTimer() {
			@Override
			public void handle(long currentNanoTime) {
				gc.clearRect(0, 0, SCREEN_W, SCREEN_H);
				if (paddle.getRect().intersects(ball.getRect())) {
					ball.bounceOnPaddle();
				}
				
				ball.update();
				
				paddle.draw(gc);
				ball.draw(gc);
			}
		};
		
		gameLoop.start();
		theStage.show();
	}
	
	public static void showAlert(String text) {
		gameLoop.stop();
		Alert youLose = new Alert(Alert.AlertType.WARNING, text);
		youLose.show();
		gameLoop.start();
	}
}
