package basic.system;

import basic.component.InputComponent;
import basic.component.MoveComponent;
import basic.component.MoveComponent.ORIENTATION;
import basic.component.manager.ComponentType;
import basic.entity.Entity;
import basic.world.World;

public class MoveSystem implements System {
	private Entity player;
	private InputComponent playerInput;
	private MoveComponent playerMove;
	
	public MoveSystem(World world) {
		player = world.getEntityByName("player");
		playerInput = (InputComponent) world.getComponentByEntity(ComponentType.input, player);
		playerMove = (MoveComponent) world.getComponentByEntity(ComponentType.move, player);
	}

	@Override
	public void instantiation() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(playerInput.isKeyPress()) {
			switch(playerInput.getCurKeyCode()) {
			case W://up
				playerMove.setDirection(ORIENTATION.NORTH);
				break;
			case S://down
				playerMove.setDirection(ORIENTATION.SOUTH);
				break;
			case A://left
				playerMove.setDirection(ORIENTATION.WEST);
				break;
			case D://right
				playerMove.setDirection(ORIENTATION.EAST);
				break;
			}
		}
	}

}
