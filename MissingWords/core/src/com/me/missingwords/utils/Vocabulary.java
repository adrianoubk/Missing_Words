package com.me.missingwords.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.me.missingwords.MissingWords.Category;

public class Vocabulary {
	private HashMap<String, Category> vocabulary;
	
	public Vocabulary() throws IOException {
		vocabulary = new HashMap<String, Category>();
		FileHandle file = Gdx.files.internal("vocabulary.csv");
		BufferedReader br = new BufferedReader(file.reader());
		String line;
		String[] word;
		br.readLine(); // Saltar primera linea por el cursor
			while((line = br.readLine()) != null) {
				word = line.split(",");
				if (word[1].equals(Category.kalender.toString())) 
					vocabulary.put(word[0], Category.kalender);
				else if (word[1].equals(Category.lander.toString())) 
					vocabulary.put(word[0], Category.lander);
			}
			
		/*for (Entry<String, Category> e: vocabulary.entrySet()) {
			System.out.println(e.getKey() + "," + e.getValue());
		}*/
		
		br.close();
	}

	public Map<String, Category> getVocabulary() {
		return vocabulary;
	}
	
	public String randomKey() {
		Object[] keys;
		Object randomKey;
		
		keys = vocabulary.keySet().toArray();
		randomKey = keys[new Random().nextInt(keys.length)];
		
		return (String) randomKey;
	}
}
