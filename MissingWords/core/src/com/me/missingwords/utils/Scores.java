package com.me.missingwords.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class Scores {
	private HashMap<String, Integer> scores;
	
	public Scores() throws IOException {
		scores = new HashMap<String, Integer>();
		FileHandle file = Gdx.files.internal("scores.txt");
		BufferedReader br = new BufferedReader(file.reader());
		String line;
		String[] score;
		while ((line = br.readLine()) != null) {
			score = line.split(" ");
			scores.put(score[0], Integer.parseInt(score[1]));
		}
		
		/*for (Entry<String, Integer> e: scores.entrySet()) {
			System.out.println(e.getKey() + "," + e.getValue());
		}*/
		
		br.close();
	}

	public Map<String, Integer> getScores() {
		return scores;
	}
	
	public String randomKey() {
		Object[] keys;
		Object randomKey;
		
		keys = scores.keySet().toArray();
		randomKey = keys[new Random().nextInt(keys.length)];
		
		return (String) randomKey;
	}
}
