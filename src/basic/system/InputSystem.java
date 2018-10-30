package basic.system;

import java.util.ArrayList;

import basic.component.InputComponent;
import basic.component.manager.ComponentType;
import basic.entity.Entity;
import basic.event.KeyBoardEventListener;
import basic.world.World;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class InputSystem implements System{
	private Entity player;
	private InputComponent inputcomponent;
	private ArrayList<KeyBoardEventListener> listenerList = new ArrayList<KeyBoardEventListener>();
	
	private EventHandler<MouseEvent> mouseEventHandler = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			// TODO Auto-generated method stub
			if(event.getEventType() == MouseEvent.MOUSE_PRESSED && !inputcomponent.isKeyPress()) {
				inputcomponent.setMouseX(event.getX());
				inputcomponent.setMouseY(event.getY());
				inputcomponent.setMousePress(true);
				
//				java.lang.System.out.print(event.getX() + "  "+ event.getY());
			} 
			else if(event.getEventType() == MouseEvent.MOUSE_RELEASED) {
				inputcomponent.setMousePress(false);
			}
		}
	};
	
	private EventHandler<KeyEvent> keyEventHandler = new EventHandler<KeyEvent>() {
		@Override
		public void handle(KeyEvent event) {
			// TODO Auto-generated method stub
			if(event.getEventType() == KeyEvent.KEY_PRESSED) {
				inputcomponent.setCurKeyCode(event.getCode());
				inputcomponent.setKeyPress(true);
				onPressKeyEvent();
			}
			else if(event.getEventType() == KeyEvent.KEY_RELEASED) {
				inputcomponent.setKeyPress(false);
			}
		}
	};
	
	public InputSystem(World world, Stage primaryStage) {
		
		player = world.getEntityByName("player");
		inputcomponent = (InputComponent) world.getComponentByEntity(ComponentType.input, player);
		
		
		//mouse event
		primaryStage.getScene().setOnMousePressed(mouseEventHandler);
		primaryStage.getScene().setOnMouseReleased(mouseEventHandler);
		
		//key event
		primaryStage.getScene().setOnKeyPressed(keyEventHandler);
		primaryStage.getScene().setOnKeyReleased(keyEventHandler);
	}
	
	@Override
	public void instantiation() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}

	public void addListener(KeyBoardEventListener listener) {
		listenerList.add(listener);
	}
	
	private void onPressKeyEvent() {
		for(KeyBoardEventListener listener : listenerList) {
			listener.pressTheKey();
		}
	}
}

