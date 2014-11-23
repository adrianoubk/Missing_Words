package com.me.missingwords.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.me.missingwords.MissingWords.Language;

/**
 * 
 * Diccionario del juego.
 *
 */

public class Dictionary {	
	private HashMap<String, String> dic;
	
	public Dictionary(Language language) throws IOException {
		
		dic = new HashMap<String, String>();	
		FileHandle file = null;
		
		/* Cargamos el fichero del diccionario a usar */
		
		switch (language) {
			case german: file = Gdx.files.external("MissingWordsData/GameData/german-spanish.txt"); break;
			case english: file = Gdx.files.external("MissingWordsData/GameData/english-spanish.txt"); break;
		}
		
		BufferedReader br = new BufferedReader(file.reader());
		String line;
		String[] word;
		
		while((line = br.readLine()) != null) {	
			word = line.split(",");
			
			dic.put(word[0], word[1]);
		}
		
		/*for (Entry<String, String> e: dic.entrySet()) {
			System.out.println(e.getKey() + "," + e.getValue());
		}*/
		
		br.close();
	}
	
	/* -------------- Getters and Setters -------------- */
	
	public Map<String, String> getDictionary() {
		return dic;
	}
}
