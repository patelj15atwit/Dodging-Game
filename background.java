
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

		 	AnimationTimer clock;
		    Pane pane = new Pane();
		    List debri = new ArrayList();
		    double MM;
		    Rectangle rect;
		    double debrisSpeed;
		    double debrisCount;
		    Label contact;
		    int contactnum;

		public static void main(String[] args) {
			launch(args);

		}

		@Override
		public void start(Stage primaryStage) throws Exception {
			contact = new Label("contact made: 0");
			contact.setLayoutX(10);
	        contact.setLayoutY(10);
			contactnum =0;
			debrisSpeed = 1;
			debrisCount = 600;
			

			Timeline timeline = new Timeline();
			new Timeline(new KeyFrame(Duration.millis(debrisCount), event -> {
				debrisSpeed += debrisCount/1000;
				debri.add(circle());
				pane.getChildren().add((Node)debri.get(debri.size()-1));		
			}));
			
			timeline.play();
			timeline.setCycleCount(1000);
			
			clock = new AnimationTimer() {
				 
	            @Override
	            public void handle(long arg0) {
	                working();
	            }

			};
			clock.start();
			rect = rectangle();
			pane.getChildren().addAll(rect,contact);
			Scene scene = new Scene(pane, 400, 600);
			scene.setOnMouseMoved(e ->{
				MM = e.getX();
			});
			primaryStage.setScene(scene);
			primaryStage.show();
			}
		
		public Circle circle() {
			Circle debri=new Circle();
			debri.setFill(Color.GREEN);
			debri.setRadius(8);
			debri.setLayoutX(rand(0,480));
			debri.setLayoutY(1);
	        
	        return debri;
		}
		 public int rand(int min, int max) {
		        return (int)(Math.random() * max + min);
		 }

		public Rectangle rectangle() {
			Rectangle rect = new Rectangle();
			rect.setLayoutX(200);
			rect.setLayoutY(500);
			rect.setHeight(60);
			rect.setFill(Color.ALICEBLUE);
			return rect;
			
		}
		private void working() {
			 rect.setLayoutX(MM);
		       
		       
		        for(int i = 0; i < debri.size(); i++) {
		            ((Circle) debri.get(i)).setLayoutY(((Circle) debri.get(i)).getLayoutY() + debrisSpeed + ((Circle) debri.get(i)).getLayoutY() / 150 );
		            
		            if((((Circle) debri.get(i)).getLayoutX() > rect.getLayoutX() && ((Circle) debri.get(i)).getLayoutX() < rect.getLayoutX() + 70) && ((Circle) debri.get(i)).getLayoutY() >= 500  ) {
		                pane.getChildren().remove(((Circle) debri.get(i)));
		                debri.remove(i);
		            }
		               
		            
		            else if(((Circle) debri.get(i)).getLayoutY() >= 590) {
		                pane.getChildren().remove(((Circle) debri.get(i)));
		                debri.remove(i);
		                contactnum += 1;
		                contact.setText("no contact: " + String.valueOf(contactnum));
		            }
			
		}

	}}

