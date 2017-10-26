package strategos;

import strategos.behaviour.BehaviourFactory;
import strategos.behaviour.BehaviourFactoryImpl;
import strategos.mapcreation.mapgeneration.TerrainGeneration;
import strategos.mapgenerator.Generator;
import strategos.model.GameState;
import strategos.model.GameStateFactory;
import strategos.model.StateCreator;
import strategos.networking.handlers.NetworkingHandlerImpl;
import strategos.ui.UI;

public class GameRunner {

	private GameRunner() {
		Generator generator = new TerrainGeneration();
		BehaviourFactory factory = new BehaviourFactoryImpl();

		GameStateFactory stateCreator = new StateCreator(factory, generator);

		GameState gameState = stateCreator.createNewState();

		new UI(gameState, new NetworkingHandlerImpl());
	}

	public static void main(String[] args) {
		new GameRunner();
	}
}
