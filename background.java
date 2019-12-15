
	import java.util.ArrayList;
	import java.util.List;
	 
	import javafx.animation.AnimationTimer;
	import javafx.animation.KeyFrame;
	import javafx.animation.Timeline;
	import javafx.application.Application;
	import javafx.scene.Node;
	import javafx.scene.Scene;
	import javafx.scene.control.Label;
	import javafx.scene.layout.Pane;
	import javafx.scene.paint.Color;
	import javafx.scene.shape.Circle;
	import javafx.scene.shape.Rectangle;
	import javafx.stage.Stage;
	import javafx.util.Duration;

	public class background extends Application{

		/* animationtimer is used for the animation timer
		 * MM is used to bind mouse movements with the movement of the rectangle object
		 * (i was trying to make the moveable object into a triangle by using TriangleMesh but it only works as a 3d object
		 * debriS is used to describe the movement speed
		 * debrisF is used for amount of debri falling
		 * i tried to include code for start window in another class but was messing up the codea
		 */
		AnimationTimer clock;
	    Pane pane = new Pane();
	    List debri = new ArrayList();
	    double MM;
	    Rectangle rect;
	    double debriS;
	    double debriF;
	    Label contactM;
	    int contact;
	 
	    // main method used to launch the code
	    public static void main() {
	        launch();
	     
	    }
	 
	    @Override
	    public void start(Stage primaryStage) throws Exception {
	    	// the following code is used to output how many times the debri touched the rectangle object
	        contactM = new Label("contact made : 0");
	        contactM.setLayoutX(10);
	        contactM.setLayoutY(10);
	        contact = 0;
	       
	        // 2 is the speed and 100 is the amount of debri falling
	        debriS = 3;
	        debriF = 100;
	       
	        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(debriF), event -> {
	           
	            debriS += debriF / 3000;
	            debri.add(circle());
	            pane.getChildren().add(((Node)debri.get(debri.size() -1)));
	        }));
	       
	        timeline.setCycleCount(1000);
	        timeline.play();
	      
	        clock = new AnimationTimer() {
	 
	            @Override
	            public void handle(long arg0) {
	                gameUpdate();    
	            }
	           
	        };
	        clock.start(); 
	       
	        rect = rectangle(); 
	   
	        pane.getChildren().addAll(rect, contactM);
	       // the borders are 400 X 600
	        Scene scene = new Scene(pane, 400, 600);
	        
	        //the following code is used as a eventhandler
	        scene.setOnMouseMoved(e -> {
	            MM = e.getX();
	        });
	       
	        primaryStage.setScene(scene);
	        primaryStage.show();
	       
	    }
	   
	    //this method is used to code the debri size and color 
	    public Circle circle() {
	        Circle circle = new Circle();
	        circle.setLayoutX(rand(0, 400));
	        circle.setLayoutY(1);
	        circle.setRadius(6);
	        circle.setFill(Color.BLACK);
	        return circle;
	    }
	   
	    //this method is used to code moveable objects size and color
	    public Rectangle rectangle() {
	        Rectangle rectangle = new Rectangle();
	        rectangle.setLayoutX(200);
	        rectangle.setLayoutY(550);
	        rectangle.setHeight(20);
	        rectangle.setWidth(70);
	        rectangle.setFill(Color.RED);
	        return rectangle;
	       
	    }
	   
	    public int rand(int min, int max) {
	        return (int)(Math.random() * max + min);
	    }
	    public void gameUpdate(){
	       // for moving the rectangle
	        rect.setLayoutX(MM);
	       
	       // i tried to use the for loop to make the code stop if the debri hits object
	        for(int i = 0; i < debri.size(); i++) {
	            ((Circle) debri.get(i)).setLayoutY(((Circle) debri.get(i)).getLayoutY() + debriS + ((Circle) debri.get(i)).getLayoutY() / 150 );
	            
	            if((((Circle) debri.get(i)).getLayoutX() > rect.getLayoutX() && ((Circle) debri.get(i)).getLayoutX() < rect.getLayoutX() + 70) && ((Circle) debri.get(i)).getLayoutY() >= 550  ) {
	                pane.getChildren().remove(((Circle) debri.get(i)));
	                debri.remove(i);
	                System.exit(0);
	            }
	               
	            
	            else if(((Circle) debri.get(i)).getLayoutY() >= 590) {
	                pane.getChildren().remove(((Circle) debri.get(i)));
	                debri.remove(i);
	                contact += 1;
	                contactM.setText("no contact: " + String.valueOf(contact));
	            }
	        } 
	    }}

	/* this class is made to open a start menu first and then playt the game once button is pressed but this class didnt work
	 * i copyied my code in this class and removed the start window method.
	public class start extends Application{

		@Override
	    public void start(Stage primaryStage) {
	        Button btn = new Button();
	        btn.setText("Start");
	        btn.setOnAction(new EventHandler<ActionEvent>() {
	        
	            @Override
	            public void handle(ActionEvent event) {
	            	System.out.print("opening game");
	            	backgorund.main();
	            	
	            }
	        });
	        
	        StackPane root = new StackPane();
	        root.getChildren().add(btn);

	 Scene scene = new Scene(root, 300, 250);

	        primaryStage.setTitle("Dodging game");
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    }
	 public static void main(String[] args) {
	        launch(args);

		}
	}
	*/
