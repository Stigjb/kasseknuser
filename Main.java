import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    final int SCREEN_W = 800;
    final int SCREEN_H = 600;
    
    double horizontalV = 10;
    double verticalV = 8;
    double xPos = 0;
    double yPos = 0;
    
    @Override
    public void start(Stage theStage) {
        theStage.setTitle("Sprettende prikk");
        
        Group root = new Group();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);
        
        Canvas canvas = new Canvas(SCREEN_W, SCREEN_H);
        root.getChildren().add(canvas);
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        Image ball = new Image("ball.png");
        
        
        final long START_NANO_TIME = System.nanoTime();
        
        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                gc.clearRect(0, 0, SCREEN_W, SCREEN_H);
                gc.drawImage(ball, xPos, yPos);
                
                if (xPos + 32 + horizontalV > SCREEN_W) {
                    horizontalV *= -1;
                }
                if (yPos + 32 + verticalV > SCREEN_H) {
                    verticalV *= -1;
                }
                if (xPos + horizontalV < 0) {
                    horizontalV *= -1;
                }
                if (yPos + verticalV < 0) {
                    verticalV *= -1;
                }
                
                xPos += horizontalV;
                yPos += verticalV;
            }
        }.start();
        
        theStage.show();
    }
}