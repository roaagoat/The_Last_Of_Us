package Application;

import javafx.application.Application; 
import engine.Game;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;
import model.characters.Zombie;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;
import javafx.scene.text.Font.*;
import java.nio.file.Paths;
import java.util.List;
import model.characters.Direction;
import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;

public class Main extends Application {
	
	public static Hero temph;
	public static GridPane map1;
	public static Stage window;
	
	public static void main(String[] args) {
		launch(args);
	}
	
public void start(Stage primaryStage) throws Exception {
	
	Game.loadHeroes("Heroes.csv");
	
    window = primaryStage;
	window.setTitle("Game innit");
	
	Label title = new Label("THE LAST OF US \n");
	Font font2 = Font.font("Gothic Press",FontWeight.BOLD, 50);
	title.setFont(font2);
	title.setWrapText(true);
	title.setAlignment(Pos.TOP_CENTER);
	
	Label select = new Label(" Select a Player & Start Game");
	Font font3 = Font.font("Gothic Press",FontWeight.BOLD, 30);
	select.setFont(font3);
	
	BorderPane hi = new BorderPane();
   
	Button startgamebtn = new Button("START GAME");
	startgamebtn.setVisible(true);
	startgamebtn.setDisable(true);
	
	HBox startgamehbox = new HBox();
	startgamehbox.getChildren().add(startgamebtn);
	hi.setRight(startgamehbox);
	
    GridPane chars = new GridPane();
    
    Button joe = new Button("Joel Miller");
    joe.setOnAction(e -> {
    	temph = Game.availableHeroes.get(0);
    	startgamebtn.setVisible(true);
    	startgamebtn.setDisable(false);
    });
    
    Button ellie = new Button("Ellie Williams");
    ellie.setOnAction(e -> {
    	temph = Game.availableHeroes.get(1);
    	startgamebtn.setVisible(true);
    	startgamebtn.setDisable(false);
       });
   // Label joeTyoe = new Label("Fighter: FIGHTER");
 
    Button tess = new Button("Tess");
    tess.setOnAction(e -> {
    	temph = Game.availableHeroes.get(2);
    	startgamebtn.setVisible(true);
    	startgamebtn.setDisable(false);
    });
   
    Button riley = new Button("Riley Abel");
    riley.setOnAction(e -> {
    	temph = Game.availableHeroes.get(3);
    	startgamebtn.setVisible(true);
    	startgamebtn.setDisable(false);
    });
    
    Button tommy = new Button("Tommy Miller");
    tommy.setOnAction(e -> {
    	temph = Game.availableHeroes.get(4);
    	startgamebtn.setVisible(true);
    	startgamebtn.setDisable(false);
    });
    
    Button bill = new Button("Bill");
    bill.setOnAction(e -> {
    	temph = Game.availableHeroes.get(5);
    	startgamebtn.setVisible(true);
    	startgamebtn.setDisable(false);
    });
    
    Button david = new Button("David");
    david.setOnAction(e -> {
    	temph = Game.availableHeroes.get(6);
    	startgamebtn.setVisible(true);
    	startgamebtn.setDisable(false);
    });
    
    Button henry = new Button("Henry Burell");
    henry.setOnAction(e -> {
    	temph = Game.availableHeroes.get(7);
    	startgamebtn.setVisible(true);
    	startgamebtn.setDisable(false);
    });
    
    joe.setWrapText(true);
    ellie.setWrapText(true);
    riley.setWrapText(true);
    tess.setWrapText(true);
    david.setWrapText(true);
    tommy.setWrapText(true);
    bill.setWrapText(true);
    henry.setWrapText(true);
    
    Font font = Font.font("New Times Roman", FontWeight.BOLD, 15);
    joe.setFont(font);
    ellie.setFont(font);
    tess.setFont(font);
    riley.setFont(font);
    tommy.setFont(font);
    bill.setFont(font);
    david.setFont(font);
    henry.setFont(font);
    
    joe.setTextAlignment(TextAlignment.CENTER);    
    ellie.setTextAlignment(TextAlignment.CENTER);   
    riley.setTextAlignment(TextAlignment.CENTER);   
    tess.setTextAlignment(TextAlignment.CENTER);   
    david.setTextAlignment(TextAlignment.CENTER);   
    tommy.setTextAlignment(TextAlignment.CENTER);   
    bill.setTextAlignment(TextAlignment.CENTER);   
    henry.setTextAlignment(TextAlignment.CENTER);   

 
    henry.setPrefSize(350, 350);
    david.setPrefSize(350, 350);
    bill.setPrefSize(350, 350);
    riley.setPrefSize(350, 350);
    tommy.setPrefSize(350, 350);
    joe.setPrefSize(350, 350);
    ellie.setPrefSize(350, 350);
    tess.setPrefSize(350, 350);
    
    
    joe.setStyle("-fx-background-color: #cbb39c; "); 
    ellie.setStyle("-fx-background-color: #54473b; "); 
    henry.setStyle("-fx-background-color: #54473b; "); 
    david.setStyle("-fx-background-color: #cbb39c; "); 
    riley.setStyle("-fx-background-color:#626554; "); 
    tommy.setStyle("-fx-background-color: #626554; "); 
    tess.setStyle("-fx-background-color: #626554; "); 
    bill.setStyle("-fx-background-color: #54473b; "); 
   // ellie.setStyle("-fx-text: #626554");
    //bill.setStyle("-fx-text: #626554");
    //henry.setStyle("-fx-text: #626554");
    
    Tooltip joemama = new Tooltip();
    joemama.setText("TYPE: FIGHTER \n" + "MAX HP: 140 \n" + "MAX ACTIONS: 5 \n"+ "ATTACK DAMAGE: 30");
    joe.setTooltip(joemama);
    
    Tooltip ellietp = new Tooltip();
    ellietp.setText("TYPE: MEDIC \n" + "MAX HP: 110 \n" + "MAX ACTIONS: 6 \n"+ "ATTACK DAMAGE: 15");
    ellie.setTooltip(ellietp);
    
    Tooltip davidtp = new Tooltip();
    davidtp.setText("TYPE: FIGHTER \n" + "MAX HP: 150 \n" + "MAX ACTIONS: 4 \n"+ "ATTACK DAMAGE: 35");
    david.setTooltip(davidtp);
    
    Tooltip henrytp = new Tooltip();
    henrytp.setText("TYPE: MEDIC \n" + "MAX HP: 105 \n" + "MAX ACTIONS: 6 \n"+ "ATTACK DAMAGE: 15");
    henry.setTooltip(henrytp);
    
    Tooltip rileytp = new Tooltip();
    rileytp.setText("TYPE: EXPLORER \n" + "MAX HP: 90 \n" + "MAX ACTIONS: 5 \n"+ "ATTACK DAMAGE: 25");
    riley.setTooltip(rileytp);
    
    Tooltip tommytp = new Tooltip();
    tommytp.setText("TYPE: EXPLORER \n" +"MAX HP: 95 \n" + "MAX ACTIONS: 5 \n"+ "ATTACK DAMAGE: 25");
    tommy.setTooltip(tommytp);
    
    
    Tooltip test = new Tooltip();
    test.setText("Type: EXPLORER \n" + "MAX HP: 80 \n" + "MAX ACTIONS: 6 \n"+ "ATTACK DAMAGE: 20");
    tess.setTooltip(test);
    
    Tooltip billtdiff = new Tooltip();
    billtdiff.setText("Type: MEDIC \n" + "MAX HP: 100 \n" + "MAX ACTIONS: 7 \n"+ "ATTACK DAMAGE: 10");
    bill.setTooltip(billtdiff);
    Label label1 = new Label("Note: Hover over characters for details");
    VBox hb = new VBox();
    hb.getChildren().addAll(label1);
    hi.setLeft(hb);
    
    //#29754c
    DropShadow shadow = new DropShadow();
    joe.setEffect(shadow);
    ellie.setEffect(shadow);
    henry.setEffect(shadow);
    bill.setEffect(shadow);
    tommy.setEffect(shadow);
    tess.setEffect(shadow);
    david.setEffect(shadow);
    riley.setEffect(shadow);
    
    chars.add(joe, 0, 0);
    chars.add(ellie, 1, 0);
    chars.add(tess, 2, 0);
    chars.add(riley, 3, 0);
    chars.add(tommy, 0, 1);
    chars.add(bill, 1, 1); 
    chars.add(david, 2, 1);
    chars.add(henry, 3, 1);
    chars.getColumnConstraints().add(new ColumnConstraints(100));
    chars.getColumnConstraints().add(new ColumnConstraints(100)); 
    chars.getColumnConstraints().add(new ColumnConstraints(100));
    chars.getColumnConstraints().add(new ColumnConstraints(100));
    chars.getRowConstraints().add(new RowConstraints(100));
    chars.getRowConstraints().add(new RowConstraints(100));
    chars.setHgap(10);
    chars.setVgap(10);
    chars.setPadding(new Insets(10,10,10,10));
    HBox hboxtop = new HBox();
    hboxtop.getChildren().add(title);
    HBox hboxtop2 = new HBox();
    hboxtop2.getChildren().add(select);
    chars.setAlignment(Pos.CENTER);
    hi.setTop(hboxtop2);
    hboxtop.setAlignment(Pos.CENTER);
    hboxtop2.setAlignment(Pos.BASELINE_CENTER);
    hi.setBottom(hboxtop);
    hi.setRight(startgamehbox);
    hi.setCenter(chars);
    hi.setRight(startgamebtn);	

	BorderPane marina = new BorderPane();
	GridPane move = new GridPane();
	Button moveright = new Button("RIGHT");
	Button moveleft = new Button("LEFT");
	Button moveup = new Button("UP");
	Button movedown = new Button("DOWN");

	move.add(movedown, 1, 1);
	move.add(moveright, 2, 1);
	move.add(moveleft, 0, 1);
	move.add(moveup, 1, 0);
	
	movedown.setPrefSize(60, 60);
	moveup.setPrefSize(60, 60);
	moveright.setPrefSize(60, 60);
	moveleft.setPrefSize(60, 60);
    move.setAlignment(Pos.BASELINE_RIGHT);
    
   Alert movemebt = new Alert(AlertType.ERROR);
  
   moveup.setOnAction(e -> {
  	try {
  	temph.move(Direction.UP);
  	zeft(map1);
	Label displayzeft = new Label(displaytemph());
    VBox disp = new VBox(displayzeft);
    disp.setSpacing(10);
    marina.setLeft(disp);
  	}
  	catch(MovementException roaa) {
  		displayalert("oops", roaa.getMessage());
  		
  	} catch (NotEnoughActionsException e1) {
  		displayalert("no", e1.getMessage());	
		}	
});
  
   
  moveleft.setOnAction(e -> {
  	try {
  	temph.move(Direction.LEFT);
  	zeft(map1);
	Label displayzeft = new Label(displaytemph());
    VBox disp = new VBox(displayzeft);
    disp.setSpacing(10);
	   marina.setLeft(disp);
  	}
  	catch(MovementException roaa) {
  		displayalert("oops", roaa.getMessage());
  		
  	} catch (NotEnoughActionsException e1) {
  		displayalert("no", e1.getMessage());	
		}
});
  
  moveright.setOnAction(e -> {
  	try {
  	temph.move(Direction.RIGHT);
  	zeft(map1);
	Label displayzeft = new Label(displaytemph());
    VBox disp = new VBox(displayzeft);
    disp.setSpacing(10);
	   marina.setLeft(disp);
  	}
  	catch(MovementException roaa) {
  		displayalert("oops", roaa.getMessage());
  		
  	} catch (NotEnoughActionsException e1) {
  		displayalert("no", e1.getMessage());	
		}
});
    
    movedown.setOnAction(e -> {
    	try {
    	temph.move(Direction.DOWN);
    	zeft(map1);
       Label displayzeft = new Label(displaytemph());
       VBox disp = new VBox(displayzeft);
       disp.setSpacing(10);
   	   marina.setLeft(disp);
    	}
    	catch(MovementException roaa) {
    		displayalert("oops", roaa.getMessage());
    		
    	} catch (NotEnoughActionsException e1) {
    		displayalert("no", e1.getMessage());
	}
  
  });
    
  
    
	Button attack = new Button("ATTACK!");
	attack.setOnAction(e -> {
		try {
			temph.attack();
			Label displayzeft = new Label(displaytemph());
		   	Label displayzom = new Label(displayzombie());
		   	VBox disp = new VBox(displayzeft, displayzom);
		   	disp.setSpacing(10);
		     marina.setLeft(disp);
		     zeft(map1);
			
	 } catch (NotEnoughActionsException hello) {
			   displayalert("oops", hello.getMessage());
		   }
	  catch (InvalidTargetException help) {
		  displayalert("oops", help.getMessage());
	  }

	} );
	
	Button cure = new Button("CURE!");
	cure.setOnAction(e -> {
		try {
			temph.cure();
			Label displayzeft = new Label(displaytemph());
		   	Label displayzom = new Label(displayzombie());
		   	VBox disp = new VBox(displayzeft, displayzom);
		   	disp.setSpacing(10);
		   	   marina.setLeft(disp);
			
	 } catch (NotEnoughActionsException hello) {
			   displayalert("oops", hello.getMessage());
		   }
	  catch (InvalidTargetException help) {
		  displayalert("oops", help.getMessage());
	  } catch (NoAvailableResourcesException e1) {
		  displayalert("oops", e1.getMessage());	
	}	
	} );
	
	Button usespecial = new Button("USE SPECIAL!");
	usespecial.setOnAction(e -> {
		try {
			temph.useSpecial();
			Label displayzeft = new Label(displaytemph());
		   	Label displayzom = new Label(displayzombie());
		   	VBox disp = new VBox(displayzeft, displayzom);
		   	disp.setSpacing(10);
		   	   zeft(map1);
			
	 } catch (NoAvailableResourcesException hello) {
			   displayalert("oops", hello.getMessage());
		   }
	  catch (InvalidTargetException help) {
		  displayalert("oops", help.getMessage()); 
	  }
	});
	
  GridPane map = new GridPane();
   map1=map;
  
  
   marina.setCenter(map1);
   marina.setBottom(move);
   
	Scene mapscene = new Scene(marina,1200,600);
	startgamebtn.setOnAction(
			e -> {
		    	Game.startGame(temph);
		    	  window.setScene(mapscene);
		    	  zeft(map1);
		    	  Label displaychar = new Label("Name:" + temph.getName() + "\n" +
			  				"Current HP:" + temph.getCurrentHp() + "\n" +
			  				"Attack Damage:" +  temph.getAttackDmg() + "\n" + 
			  				"Actions Left:" + temph.getActionsAvailable() + "\n" +
			  				"Vaccine Inventory:" + temph.getVaccineInventory()+ "vaccines \n"+
			  				"Supply Inventory:" + temph.getSupplyInventory() + "supplies "
			  		);
			    	  VBox disp = new VBox(displaychar);
			    	  disp.setSpacing(10);
			    	  marina.setLeft(disp);
			    	  zeft(map1);
			    });
	
	Button endTurn = new Button("END TURN :(");
	endTurn.setOnAction(e -> {
		try {
			Game.endTurn();
			zeft(map1);
			
			
	 } catch (NotEnoughActionsException hello) {
			   displayalert("oops", hello.getMessage());
		   }
	  catch (InvalidTargetException help) {
		  displayalert("oops", help.getMessage());
	  }
		});
    VBox buttons = new VBox(attack, cure,usespecial, endTurn);
    marina.setRight(buttons);
    
   Scene chooseChar = new Scene(hi,1200,600);
   window.setScene(chooseChar);
    window.show();

}
public static void zeft(GridPane map) { 
	for(int i = 0; i<15; i++) {
		for(int j =0 ; j<15; j++) {
		//Button[][] cells = new Button[15][15];
			Button cell = new Button();
		    cell.setPrefSize(70, 70);
			map.add(cell, j,Game.map.length-1-i);
			final int iset= i;
			final int jset = j;
				cell.setOnAction(e -> 	{
		             if(Game.map[iset][jset] instanceof CharacterCell) {
							if(((CharacterCell) Game.map[iset][jset]).getCharacter() instanceof Hero) {
								temph = (Hero) ((CharacterCell) Game.map[iset][jset]).getCharacter();
							}else if(((CharacterCell) Game.map[iset][jset]).getCharacter()!=null) {
									temph.setTarget(((CharacterCell) Game.map[iset][jset]).getCharacter());
							}}
							});
				
				

			cell.setStyle("-fx-background-color: #4d2e0f; ");
			map.setVgap(5);
		    map.setHgap(5);	
		    if(Game.map[iset][jset] instanceof TrapCell) {
		    	
		    }
			if(Game.map[i][j].isVisible() == true) {
				if (Game.map[i][j] instanceof CollectibleCell
						&& ((CollectibleCell) Game.map[i][j]).getCollectible() instanceof Vaccine) {
					cell.setStyle("-fx-background-color: blue;");
				}
				else if (Game.map[i][j] instanceof CollectibleCell
						&& ((CollectibleCell) Game.map[i][j]).getCollectible() instanceof Supply) {
					cell.setStyle("-fx-background-color: yellow;");
				}
				else if (Game.map[i][j] instanceof CharacterCell
						&& ((CharacterCell) Game.map[i][j]).getCharacter() instanceof Hero) {
					cell.setStyle("-fx-background-color: red;");
				}
				else if (Game.map[i][j] instanceof CharacterCell
						&& ((CharacterCell) Game.map[i][j]).getCharacter() instanceof Zombie) {
					cell.setStyle("-fx-background-color: green;");
				}
				else if(Game.map[i][j] instanceof TrapCell) {
					cell.setStyle("-fx-background-color: white;");
				}
	
				else
					cell.setStyle("-fx-background-color: white;");
	     	}
		}
	}		

	if(Game.checkGameOver()) {
		if(Game.checkWin()) {
			winAlert("win","winner winner chicken dinner");
			BorderPane yarab = new BorderPane();
		    Scene winnie = new Scene(yarab);
		    window.setScene(winnie);
		    window.show();
		}else 
			loseAlert("loser", "LOSER!!!!!");
		    BorderPane yarabn5las = new BorderPane();
	        Scene lost = new Scene(yarabn5las);
	        window.setScene(lost);
		    window.show();
		}
		}
	
	
public void displayalert(String title, String message) {
	Alert alert = new Alert(AlertType.ERROR);
	alert.setTitle(title);
	alert.setHeaderText(null);
	alert.setContentText(message);
	alert.showAndWait();
	}

public static void winAlert(String title, String text) {
	Alert alertwin = new Alert(AlertType.INFORMATION);
	alertwin.setTitle(title);
	alertwin.setContentText(text);
	alertwin.showAndWait();
}
public static void loseAlert(String title2, String text2) {
	Alert alertlose = new Alert(AlertType.INFORMATION);
	alertlose.setTitle(title2);
	alertlose.setContentText(text2);
	alertlose.showAndWait();
}
public static void trapCellAlert(String title, String text) {
	Alert alertwin = new Alert(AlertType.INFORMATION);
	alertwin.setTitle(title);
	alertwin.setContentText(text);
	alertwin.showAndWait();
}
public static String displaytemph() {
	
	String dis = "Name:" + temph.getName() + "\n" +
			"Current HP:" + temph.getCurrentHp() + "\n" +
			"Attack Damage:" +  temph.getAttackDmg() + "\n" + 
			"Actions Left:" + temph.getActionsAvailable() + "\n" +
			"Vaccine Inventory:" + temph.getVaccineInventory().size() + " vaccines \n"+
			"Supply Inventory:" + temph.getSupplyInventory().size()  + " supplies ";
	
	return dis;
}
public static String displayzombie() {
	String diz = "Name:" + temph.getTarget().getName() + "\n" +
                  "Current HP:" + temph.getTarget().getCurrentHp() + "\n" +
			"Attack Damage:" + temph.getTarget().getAttackDmg();
	return diz;
}

}
