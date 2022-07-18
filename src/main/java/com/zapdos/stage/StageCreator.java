package com.zapdos.stage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.zapdos.enemies.Enemy;
import com.zapdos.enemies.Enemy_Reverbot;
import com.zapdos.environmentobjects.Button;
import com.zapdos.environmentobjects.Door;
import com.zapdos.environmentobjects.MovablePlatforms;
import com.zapdos.environmentobjects.Spike;
import com.zapdos.main.MainApplication;
import com.zapdos.main.Sprite;

public class StageCreator {
	private static double SCALE = MainApplication.SCALE;
	private BaseStage2 bStage;
	
	
	public StageCreator(BaseStage2 bStage) {
		this.bStage = bStage;
	}
	
	
	@SuppressWarnings("unchecked")
	private void addJSONObjects(ArrayList<Sprite> envObjList, JSONObject envObject, String objType) {

		if (envObjList.size() > 0) {

			JSONArray spriteArr = new JSONArray();

			for (Sprite p: envObjList) {
				JSONObject envObjData = new JSONObject();
				envObjData.put("fileName", p.getFileName());
				envObjData.put("yCoord", p.getY() + bStage.getViewY());
				envObjData.put("xCoord", p.getX() + bStage.getViewX());
				spriteArr.add(envObjData);	
			}

			if (spriteArr.size() > 0) {
				envObject.put(objType, spriteArr);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private void addJSONMovablePlatforms(ArrayList<MovablePlatforms> envObjList, JSONObject envObject, String objType) {

		if (envObjList.size() > 0) {

			JSONArray spriteArr = new JSONArray();

			for (MovablePlatforms mvp: envObjList) {
				JSONObject envObjData = new JSONObject();
				envObjData.put("fileName", mvp.getFileName());
				envObjData.put("yCoord", mvp.getY() + bStage.getViewY());
				envObjData.put("xCoord", mvp.getX() + bStage.getViewX());
				envObjData.put("letter", String.valueOf(mvp.getLetter()));
				envObjData.put("isMovingVert", mvp.getIsMovingVert());
				envObjData.put("isDownOrRight", mvp.getIsDownOrRight());
				spriteArr.add(envObjData);	
			}

			if (spriteArr.size() > 0) {
				envObject.put(objType, spriteArr);
			}
		}
	}

	
	@SuppressWarnings("unchecked")
	private void addJSONSpikes(ArrayList<Spike> envObjList, JSONObject envObject, String objType) {

		if (envObjList.size() > 0) {

			JSONArray spriteArr = new JSONArray();

			for (Spike spike: envObjList) {
				JSONObject envObjData = new JSONObject();
				envObjData.put("fileName", spike.getFileName());
				envObjData.put("yCoord", spike.getY() + bStage.getViewY());
				envObjData.put("xCoord", spike.getX() + bStage.getViewX());
				spriteArr.add(envObjData);	
			}

			if (spriteArr.size() > 0) {
				envObject.put(objType, spriteArr);
			}
		}
	}

	
	@SuppressWarnings("unchecked")
	private void addJSONDoors(ArrayList<Door> envObjList, JSONObject envObject, String objType) {

		if (envObjList.size() > 0) {

			JSONArray spriteArr = new JSONArray();

			for (Door door: envObjList) {
				JSONObject envObjData = new JSONObject();
				envObjData.put("isOpen", door.getIsOpen());
				envObjData.put("yCoord", door.getY() + bStage.getViewY());
				envObjData.put("xCoord", door.getX() + bStage.getViewX());
				spriteArr.add(envObjData);	
			}

			if (spriteArr.size() > 0) {
				envObject.put(objType, spriteArr);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void addJSONEnemy(ArrayList<Enemy> envObjList, JSONObject envObject, String objType) {

		if (envObjList.size() > 0) {

			JSONArray enemyArr = new JSONArray();

			for (Enemy p: envObjList) {
				JSONObject envObjData = new JSONObject();
				envObjData.put("fileName", p.getFileName());
				envObjData.put("yCoord", p.getY() + bStage.getViewY());
				envObjData.put("xCoord", p.getX() + bStage.getViewX());
				enemyArr.add(envObjData);	
			}

			if (enemyArr.size() > 0) {
				envObject.put(objType, enemyArr);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void addJSONButtons(ArrayList<Button> envObjList, JSONObject envObject, String objType) {

		if (envObjList.size() > 0) {

			JSONArray buttonArr = new JSONArray();

			for (Button p: envObjList) {
				JSONObject buttonData = new JSONObject();
				buttonData.put("fileName", p.getName());
				buttonData.put("yCoord", p.getY() + bStage.getViewY());
				buttonData.put("xCoord", p.getX() + bStage.getViewX());
				buttonArr.add(buttonData);	
			}

			if (buttonArr.size() > 0) {
				envObject.put(objType, buttonArr);
			}
		}
	}


	@SuppressWarnings("unchecked")
	public void saveStage() {

		JSONObject envObject = new JSONObject();

		// Save Current Background
		JSONArray backgroundJSONArr = new JSONArray();
		JSONObject backgroundData = new JSONObject();
		backgroundData.put("fileName", bStage.getBackground());
		backgroundJSONArr.add(backgroundData);
		envObject.put("Background", backgroundJSONArr);
		
		// Save Current Music
		JSONArray musicJSONArr = new JSONArray();
		JSONObject musicData = new JSONObject();
		
		if (bStage.getMusicPlayer().getCurrentTrack() != null) {
			musicData.put("fileName", bStage.getMusicPlayer().getCurrentTrack());
			musicJSONArr.add(musicData);
			envObject.put("Music", musicJSONArr);
		}
		
		addJSONButtons(bStage.getButtons(), envObject, "Buttons");
		addJSONDoors(bStage.getDoors(), envObject, "Doors");
		addJSONSpikes(bStage.getSpikes(), envObject, "Spikes");
		addJSONObjects(bStage.getPlatforms(), envObject, "Platforms");
		addJSONEnemy(bStage.getEnemies(), envObject, "Enemies");
		addJSONMovablePlatforms(bStage.getMovablePlatforms(), envObject, "MovablePlatforms");
		
    
		try {
			FileWriter file = new FileWriter("src/main/resources/stages/stage7.json");
			file.write(envObject.toString());
			file.close();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
		System.out.println("JSON file created: " + envObject);
	}


	@SuppressWarnings("unchecked")
	public void loadStage(int stageNum) {
		String[] envList = {"Platforms", "MovablePlatforms", "Enemies", "Background", "Spikes", "Music", "Buttons", "Doors", "Keys"};
		Iterator<JSONObject> envIter;
		Iterator<?> envObjIter;
		@SuppressWarnings("rawtypes")
		Map.Entry evObjDict;
		Object stageData;
		JSONObject stageObj;
		JSONArray envArray;
		String fileName = "";
		String key = "";
		char letter = 'a';
		boolean isMovingVert = false;
		boolean isDoorOpen = false;
		double isDownOrRight = 1.0;
		double envX = 0.0;
		double envY = 0.0;

		try {
			stageData = new JSONParser().parse(new FileReader("src/main/resources/stages/stage" + stageNum + ".json"));
			stageObj = (JSONObject) stageData;

			for(String env: envList) {
				envArray = (JSONArray) stageObj.get(env);

				// iterating platforms
				if(envArray != null) {
					envIter = envArray.iterator();					


					while (envIter.hasNext()) {
						envObjIter = ((Map<?, ?>) envIter.next()).entrySet().iterator();

						while (envObjIter.hasNext()) {

							evObjDict = (Entry<String, ?>) envObjIter.next();
							key = evObjDict.getKey().toString();

							if (key.equals("fileName")) {
								fileName = (String) evObjDict.getValue();
							}

							else if (key.equals("yCoord")) {
								envY = (double) evObjDict.getValue();
							}

							else if (key.equals("xCoord")) {
								envX = (double) evObjDict.getValue();
							}
							
							else if (key.equals("isOpen")) {
								isDoorOpen = (Boolean) evObjDict.getValue();
							}
							
							else if (key.equals("letter")) {
								String temp =  (String)evObjDict.getValue();
								letter = temp.charAt(0);
							}
							
							else if (key.equals("isMovingVert")) {
								isMovingVert = (Boolean) evObjDict.getValue();
							}
							
							else if (key.equals("isDownOrRight")) {
								isDownOrRight = (Double) evObjDict.getValue();
							}
							
						}

						if (env == "Platforms") {
							bStage.getPlatforms().add(new Sprite(fileName, envX, envY, SCALE));
						}
						
						if (env == "MovablePlatforms") {
							bStage.getMovablePlatforms().add(new MovablePlatforms(fileName, envX, envY, SCALE, letter, isMovingVert, isDownOrRight));
						}

						else if(env == "Background") {
							bStage.getBackgroundSprite().setImage(fileName);
						}
						
						else if(env == "Music") {
							bStage.getMusicPlayer().playThisTrack(fileName);
						}
						
						else if(env == "Enemies") {
							bStage.getEnemies().add(new Enemy_Reverbot("T_ReverbotMoveLeft.gif", envX, envY, bStage.getPlayer()));
						}
						
						else if(env == "Buttons") {
							bStage.getButtons().add(new Button(envX, envY, fileName));
						}
						
						else if(env == "Doors") {
							if (isDoorOpen) bStage.getDoors().add(new Door(envX, envY, SCALE, true));
							else bStage.getDoors().add(new Door(envX, envY, SCALE, false));
						}
						
						else if(env == "Spikes") {
							bStage.getSpikes().add(new Spike(bStage, fileName, envX, envY, SCALE));
						}
					}

				}
			}

		} catch (IOException | ParseException e1) {
			e1.printStackTrace();
		} 	
	}

}
