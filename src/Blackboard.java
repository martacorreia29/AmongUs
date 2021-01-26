import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Blackboard {

	private Map<String, Position> alivePlayers;
	private Map<String, Position> deadPlayers;
	private Map<String, Position> tasks;
	private Map<String, Position> emergencies;
	private List<String> imposters;
	private int numOfImposters;
	private int numOfCrewmates;
	private int numOfPlayers;
	private static final int LINES = 14;
	private static final int COLUMNS = 31;
	private boolean emergencyCalling;
	private int imposterVision = 4;
	private int crewmateVision = 2;
	private int imposterKillDistance = 1;

	private TypeOfPosition[] map;
	
	private static Blackboard blackboard;
	
	private Blackboard() {
		this.alivePlayers = new HashMap<>();
		this.deadPlayers = new HashMap<>();
		this.tasks = new HashMap<>();
		this.emergencies = new HashMap<>();
		this.imposters = new ArrayList<>();
		this.emergencyCalling = false;
	}
	
	public static Blackboard getInstance() {
		if(blackboard == null) blackboard = new Blackboard();
		return blackboard;
	}
	
	// PLAYERS
	public Position getPlayerPosition(String key) {
		if(deadPlayers.containsKey(key)) return deadPlayers.get(key);
		return alivePlayers.get(key);
	}
	
	public void setPlayerPosition(String key, int x, int y) {
		if(deadPlayers.containsKey(key)) {
			if(deadPlayers.containsKey(key)) deadPlayers.replace(key , new Position(x, y));
			else deadPlayers.put(key, new Position(x, y));
		} else {
			if(alivePlayers.containsKey(key)) alivePlayers.replace(key , new Position(x, y));
			else alivePlayers.put(key, new Position(x, y));
		}		
	}
	
	public Map<String, Position> getAlivePlayersPositions(){
		Map<String, Position> newMap = new HashMap<>();
		newMap.putAll(alivePlayers);
		return newMap;
	}

	public List<String> getAllAlivePlayers() {
		List<String> result = new ArrayList<String>();
		for(String agent: alivePlayers.keySet()) {
			result.add(agent);
		}
		
		return result;
	}
	
	// MAP	
	public TypeOfPosition[] getMap() {
		return map;
	}

	public void setMap(TypeOfPosition[] map) {
		this.map = map;		
	}
	
	// TASKS
	public Position getTaskPosition(String key) {
		return tasks.get(key);
	}
	
	public void setTaskPosition(String key, Position value) {
		tasks.put(key, value);
	}
	
	// EMERGENCIES
	public Position getEmergencyPosition(String key) {
		return emergencies.get(key);
	}
	
	public void setEmergencyPosition(String key, Position value) {
		emergencies.put(key, value);
	}
	
	// VARIABLES
	public void setNum(int numOfPlayers, int numOfImposters) {
		this.numOfPlayers = numOfPlayers;
		this.numOfCrewmates = numOfPlayers - numOfImposters;
		this.numOfImposters = numOfImposters;
	}
	
	public int getNumOfPlayers() {
		return numOfPlayers;
	}
	
	public int getNumOfCrewmates() {
		return numOfPlayers;
	}
	
	public int getNumOfImposters() {
		return numOfPlayers;
	}
	
	public int getCollums() {
		return COLUMNS;
	}
	
	public int getLines() {
		return LINES;
	}
	
	// IMPOSTER
	public void setImposters(String color) {
		imposters.add(color);
	}
	
	public List<String> getImposters() {
		return imposters;
	}
	
	// EMERGENCIES
	public void setEmergencyCalling(boolean value) {
		this.emergencyCalling = value;
	}
	
	public boolean getEmergencyCalling() {
		return this.emergencyCalling;
	}
	
	// VISIONS
	public int getImposterVision() {
		return imposterVision;
	}
	
	public int getCrewmateVision() {
		return crewmateVision;
	}
	
	// DEAD
	public Map<String, Position> getDeadPlayerss(String key) {
		return deadPlayers;
	}
	
	public void setPlayerAsDead(String key, int x, int y) {
		deadPlayers.put(key, new Position (x, y));
		alivePlayers.remove(key);
	}
	
	public int getDistanceKill() {
		return imposterKillDistance;
	}
}
