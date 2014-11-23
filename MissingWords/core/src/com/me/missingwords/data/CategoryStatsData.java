package com.me.missingwords.data;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.me.missingwords.MissingWords.Category;
import com.me.missingwords.MissingWords.Language;

/**
 * 
 * Almacena las estadísticas de cada categoría.
 *
 */

public class CategoryStatsData {
	private LinkedHashMap<String, Boolean> categoryWords;
	private FileHandle f;
	
	public CategoryStatsData() {
		categoryWords = new LinkedHashMap<String, Boolean>();
		
		f = Gdx.files.external("MissingWordsData/CategoryData");
		FileHandle[] files = f.list();
		
		/* Comprobamos si se han creado los ficheros para las categorías */
		if (files.length == 0)
			try {
				createFiles();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		try {
			checkNewVocabulary();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void checkNewVocabulary() throws IOException {
		LinkedHashMap<String, Boolean> days, months, colours, wquestions, size, classroom,
		   							   bodyparts, feelings, university, city, freetime;
		
		ArrayList<String> delete_days, delete_months, delete_colours, delete_wquestions,
						  delete_size, delete_classroom, delete_bodyparts, delete_feelings,
						  delete_university, delete_city, delete_freetime;
		
		delete_days = new ArrayList<String>();
		delete_months = new ArrayList<String>();
		delete_colours = new ArrayList<String>();
		delete_wquestions = new ArrayList<String>();
		delete_size = new ArrayList<String>();
		delete_classroom = new ArrayList<String>();
		delete_bodyparts = new ArrayList<String>();
		delete_feelings = new ArrayList<String>();
		delete_university = new ArrayList<String>();
		delete_city = new ArrayList<String>();
		delete_freetime = new ArrayList<String>();
		
		days = new LinkedHashMap<String, Boolean>();
		months = new LinkedHashMap<String, Boolean>();
		colours = new LinkedHashMap<String, Boolean>();
		wquestions = new LinkedHashMap<String, Boolean>();
		size = new LinkedHashMap<String, Boolean>();
		classroom = new LinkedHashMap<String, Boolean>();
		bodyparts = new LinkedHashMap<String, Boolean>();
		feelings = new LinkedHashMap<String, Boolean>();
		university = new LinkedHashMap<String, Boolean>();
		city = new LinkedHashMap<String, Boolean>();
		freetime = new LinkedHashMap<String, Boolean>();
		
		read("days", "english");
		days = categoryWords;
		
		read("months", "english");
		months = categoryWords;
		
		read("colours", "english");
		colours = categoryWords;
		
		read("wquestions", "english");
		wquestions = categoryWords;
		
		read("size", "english");
		size = categoryWords;
		
		read("classroom", "english");
		classroom = categoryWords;
		
		read("bodyparts", "english");
		bodyparts = categoryWords;
		
		read("feelings", "english");
		feelings = categoryWords;
		
		read("university", "english");
		university = categoryWords;
		
		read("city", "english");
		city = categoryWords;
		
		read("freetime", "english");
		freetime = categoryWords;
		
		FileHandle f = Gdx.files.external("MissingWordsData/GameData/vocabulary-english.txt");
		BufferedReader br = new BufferedReader(f.reader());
		String line;
		String[] word;
		
		while ((line = br.readLine()) != null) {	
			word = line.split(",");
			
			if (word[1].equals(Category.days.toString())) {
				delete_days.add(word[0]);
				if (!days.containsKey(word[0]))
					days.put(word[0], false);
			}
			else if (word[1].equals(Category.months.toString())) {
				delete_months.add(word[0]);
				if (!months.containsKey(word[0]))
					months.put(word[0], false);
			}
			else if (word[1].equals(Category.colours.toString())) {
				delete_colours.add(word[0]);
				if (!colours.containsKey(word[0]))
					colours.put(word[0], false);
			}
			else if (word[1].equals(Category.wquestions.toString())) {
				delete_wquestions.add(word[0]);
				if (!wquestions.containsKey(word[0]))
					wquestions.put(word[0], false);
			}
			else if (word[1].equals(Category.size.toString())) {
				delete_size.add(word[0]);
				if (!size.containsKey(word[0]))
					size.put(word[0], false);
			}
			else if (word[1].equals(Category.classroom.toString())) {
				delete_classroom.add(word[0]);
				if (!classroom.containsKey(word[0]))
					classroom.put(word[0], false);
			}
			else if (word[1].equals(Category.bodyparts.toString())) {
				delete_bodyparts.add(word[0]);
				if (!bodyparts.containsKey(word[0]))
					bodyparts.put(word[0], false);
			}
			else if (word[1].equals(Category.feelings.toString())) {
				delete_feelings.add(word[0]);
				if (!feelings.containsKey(word[0]))
					feelings.put(word[0], false);
			}
			else if (word[1].equals(Category.university.toString())) {
				delete_university.add(word[0]);
				if (!university.containsKey(word[0]))
					university.put(word[0], false);
			}
			else if (word[1].equals(Category.city.toString())) {
				delete_city.add(word[0]);
				if (!city.containsKey(word[0]))
					city.put(word[0], false);
			}
			else if (word[1].equals(Category.freetime.toString())) {
				delete_freetime.add(word[0]);
				if (!freetime.containsKey(word[0]))
					freetime.put(word[0], false);
			}	
		}
		
		br.close();
		
		if (days.size() != delete_days.size())
			deleteWords(days, delete_days);
		
		if (months.size() != delete_months.size())
			deleteWords(months, delete_months);
		
		if (colours.size() != delete_colours.size())
			deleteWords(colours, delete_colours);
		
		if (wquestions.size() != delete_wquestions.size())
			deleteWords(wquestions, delete_wquestions);
		
		if (size.size() != delete_size.size())
			deleteWords(size, delete_size);
		
		if (classroom.size() != delete_classroom.size())
			deleteWords(classroom, delete_classroom);
		
		if (bodyparts.size() != delete_bodyparts.size())
			deleteWords(bodyparts, delete_bodyparts);
		
		if (feelings.size() != delete_feelings.size())
			deleteWords(feelings, delete_feelings);
		
		if (university.size() != delete_university.size())
			deleteWords(university, delete_university);
		
		if (city.size() != delete_city.size())
			deleteWords(city, delete_city);
		
		if (freetime.size() != delete_freetime.size())
			deleteWords(freetime, delete_freetime);
		
		write(days, "days", "english");
		write(months, "months", "english");
		write(colours, "colours", "english");
		write(wquestions, "wquestions", "english");
		write(size, "size", "english");
		write(classroom, "classroom", "english");
		write(bodyparts, "bodyparts", "english");
		write(feelings, "feelings", "english");
		write(university, "university", "english");
		write(city, "city", "english");
		write(freetime, "freetime", "english");
		
		read("days", "german");
		days = categoryWords;
		
		read("months", "german");
		months = categoryWords;
		
		read("colours", "german");
		colours = categoryWords;
		
		read("wquestions", "german");
		wquestions = categoryWords;
		
		read("size", "german");
		size = categoryWords;
		
		read("classroom", "german");
		classroom = categoryWords;
		
		read("bodyparts", "german");
		bodyparts = categoryWords;
		
		read("feelings", "german");
		feelings = categoryWords;
		
		read("university", "german");
		university = categoryWords;
		
		read("city", "german");
		city = categoryWords;
		
		read("freetime", "german");
		freetime = categoryWords;
		
		delete_days.clear();
		delete_months.clear();
		delete_colours.clear();
		delete_wquestions.clear();
		delete_size.clear();
		delete_classroom.clear();
		delete_bodyparts.clear();
		delete_feelings.clear();
		delete_university.clear();
		delete_city.clear();
		delete_freetime.clear();
		
		f = Gdx.files.external("MissingWordsData/GameData/vocabulary-german.txt");
		br = new BufferedReader(f.reader());
		
		while ((line = br.readLine()) != null) {	
			word = line.split(",");
			
			if (word[1].equals(Category.days.toString())) {
				delete_days.add(word[0]);
				if (!days.containsKey(word[0]))
					days.put(word[0], false);
			}
			else if (word[1].equals(Category.months.toString())) {
				delete_months.add(word[0]);
				if (!months.containsKey(word[0]))
					months.put(word[0], false);
			}
			else if (word[1].equals(Category.colours.toString())) {
				delete_colours.add(word[0]);
				if (!colours.containsKey(word[0]))
					colours.put(word[0], false);
			}
			else if (word[1].equals(Category.wquestions.toString())) {
				delete_wquestions.add(word[0]);
				if (!wquestions.containsKey(word[0]))
					wquestions.put(word[0], false);
			}
			else if (word[1].equals(Category.size.toString())) {
				delete_size.add(word[0]);
				if (!size.containsKey(word[0]))
					size.put(word[0], false);
			}
			else if (word[1].equals(Category.classroom.toString())) {
				delete_classroom.add(word[0]);
				if (!classroom.containsKey(word[0]))
					classroom.put(word[0], false);
			}
			else if (word[1].equals(Category.bodyparts.toString())) {
				delete_bodyparts.add(word[0]);
				if (!bodyparts.containsKey(word[0]))
					bodyparts.put(word[0], false);
			}
			else if (word[1].equals(Category.feelings.toString())) {
				delete_feelings.add(word[0]);
				if (!feelings.containsKey(word[0]))
					feelings.put(word[0], false);
			}
			else if (word[1].equals(Category.university.toString())) {
				delete_university.add(word[0]);
				if (!university.containsKey(word[0]))
					university.put(word[0], false);
			}
			else if (word[1].equals(Category.city.toString())) {
				delete_city.add(word[0]);
				if (!city.containsKey(word[0]))
					city.put(word[0], false);
			}
			else if (word[1].equals(Category.freetime.toString())) {
				delete_freetime.add(word[0]);
				if (!freetime.containsKey(word[0]))
					freetime.put(word[0], false);
			}	
		}
		
		br.close();
		
		if (days.size() != delete_days.size())
			deleteWords(days, delete_days);
		
		if (months.size() != delete_months.size())
			deleteWords(months, delete_months);
		
		if (colours.size() != delete_colours.size())
			deleteWords(colours, delete_colours);
		
		if (wquestions.size() != delete_wquestions.size())
			deleteWords(wquestions, delete_wquestions);
		
		if (size.size() != delete_size.size())
			deleteWords(size, delete_size);
		
		if (classroom.size() != delete_classroom.size())
			deleteWords(classroom, delete_classroom);
		
		if (bodyparts.size() != delete_bodyparts.size())
			deleteWords(bodyparts, delete_bodyparts);
		
		if (feelings.size() != delete_feelings.size())
			deleteWords(feelings, delete_feelings);
		
		if (university.size() != delete_university.size())
			deleteWords(university, delete_university);
		
		if (city.size() != delete_city.size())
			deleteWords(city, delete_city);
		
		if (freetime.size() != delete_freetime.size())
			deleteWords(freetime, delete_freetime);
		
		write(days, "days", "german");
		write(months, "months", "german");
		write(colours, "colours", "german");
		write(wquestions, "wquestions", "german");
		write(size, "size", "german");
		write(classroom, "classroom", "german");
		write(bodyparts, "bodyparts", "german");
		write(feelings, "feelings", "german");
		write(university, "university", "german");
		write(city, "city", "german");
		write(freetime, "freetime", "german");
	}
	
	private void deleteWords(LinkedHashMap<String, Boolean> category, ArrayList<String> category_delete) {
		ArrayList<String> aux = new ArrayList<String>();
		
		for (Entry<String, Boolean> e : category.entrySet()) {
			if (!category_delete.contains(e.getKey()))
				aux.add(e.getKey());
		}
		
		Iterator<String> it = aux.iterator();
		while (it.hasNext()) 
			category.remove(it.next());
	}
	
	/* createFiles(): crea los ficheros que almacenan nuestro progreso en las categorías
	 * del juego. Sólo se llama una vez si los ficheros no está creados.
	 */
	private void createFiles() throws IOException {
		LinkedHashMap<String, Boolean> days, months, colours, wquestions, size, classroom,
									   bodyparts, feelings, university, city, freetime;
		
		days = new LinkedHashMap<String, Boolean>();
		months = new LinkedHashMap<String, Boolean>();
		colours = new LinkedHashMap<String, Boolean>();
		wquestions = new LinkedHashMap<String, Boolean>();
		size = new LinkedHashMap<String, Boolean>();
		classroom = new LinkedHashMap<String, Boolean>();
		bodyparts = new LinkedHashMap<String, Boolean>();
		feelings = new LinkedHashMap<String, Boolean>();
		university = new LinkedHashMap<String, Boolean>();
		city = new LinkedHashMap<String, Boolean>();
		freetime = new LinkedHashMap<String, Boolean>();
		
		/* Creamos los ficheros en inglés */
		f = Gdx.files.internal("utils/vocabulary-english.txt");
		
		BufferedReader br = new BufferedReader(f.reader());
		String line;
		String[] word;
		
		while((line = br.readLine()) != null) {	
			word = line.split(",");
			
			if (word[1].equals(Category.days.toString()))
				days.put(word[0], false);
			else if (word[1].equals(Category.months.toString()))
				months.put(word[0], false);
			else if (word[1].equals(Category.colours.toString()))
				colours.put(word[0], false);
			else if (word[1].equals(Category.wquestions.toString()))
				wquestions.put(word[0], false);
			else if (word[1].equals(Category.size.toString()))
				size.put(word[0], false);
			else if (word[1].equals(Category.classroom.toString()))
				classroom.put(word[0], false);
			else if (word[1].equals(Category.bodyparts.toString()))
				bodyparts.put(word[0], false);
			else if (word[1].equals(Category.feelings.toString()))
				feelings.put(word[0], false);
			else if (word[1].equals(Category.university.toString()))
				university.put(word[0], false);
			else if (word[1].equals(Category.city.toString()))
				city.put(word[0], false);
			else if (word[1].equals(Category.freetime.toString()))
				freetime.put(word[0], false);
		}
		
		br.close();
		
		write(days, Category.days.toString(), Language.english.toString());
		write(months, Category.months.toString(), Language.english.toString());
		write(colours, Category.colours.toString(), Language.english.toString());
		write(wquestions, Category.wquestions.toString(), Language.english.toString());
		write(size, Category.size.toString(), Language.english.toString());
		write(classroom, Category.classroom.toString(), Language.english.toString());
		write(bodyparts, Category.bodyparts.toString(), Language.english.toString());
		write(feelings, Category.feelings.toString(), Language.english.toString());
		write(university, Category.university.toString(), Language.english.toString());
		write(city, Category.city.toString(), Language.english.toString());
		write(freetime, Category.freetime.toString(), Language.english.toString());
		
		days.clear();
		months.clear();
		colours.clear();
		wquestions.clear();
		size.clear();
		classroom.clear();
		bodyparts.clear();
		feelings.clear();
		university.clear();
		city.clear();
		freetime.clear();
		
		/* Creamos los ficheros en alemán */
		f = Gdx.files.internal("utils/vocabulary-german.txt");
		
		br = new BufferedReader(f.reader());
		
		while((line = br.readLine()) != null) {	
			word = line.split(",");
			
			if (word[1].equals(Category.days.toString()))
				days.put(word[0], false);
			else if (word[1].equals(Category.months.toString()))
				months.put(word[0], false);
			else if (word[1].equals(Category.colours.toString()))
				colours.put(word[0], false);
			else if (word[1].equals(Category.wquestions.toString()))
				wquestions.put(word[0], false);
			else if (word[1].equals(Category.size.toString()))
				size.put(word[0], false);
			else if (word[1].equals(Category.classroom.toString()))
				classroom.put(word[0], false);
			else if (word[1].equals(Category.bodyparts.toString()))
				bodyparts.put(word[0], false);
			else if (word[1].equals(Category.feelings.toString()))
				feelings.put(word[0], false);
			else if (word[1].equals(Category.university.toString()))
				university.put(word[0], false);
			else if (word[1].equals(Category.city.toString()))
				city.put(word[0], false);
			else if (word[1].equals(Category.freetime.toString()))
				freetime.put(word[0], false);
		}
		
		br.close();
		
		write(days, Category.days.toString(), Language.german.toString());
		write(months, Category.months.toString(), Language.german.toString());
		write(colours, Category.colours.toString(), Language.german.toString());
		write(wquestions, Category.wquestions.toString(), Language.german.toString());
		write(size, Category.size.toString(), Language.german.toString());
		write(classroom, Category.classroom.toString(), Language.german.toString());
		write(bodyparts, Category.bodyparts.toString(), Language.german.toString());
		write(feelings, Category.feelings.toString(), Language.german.toString());
		write(university, Category.university.toString(), Language.german.toString());
		write(city, Category.city.toString(), Language.german.toString());
		write(freetime, Category.freetime.toString(), Language.german.toString());
	}
	
	/* write(): guarda los datos de la categoría en el fichero correspondiente */
	public void write(LinkedHashMap<String, Boolean> object, String category, String language) {
		String path = "MissingWordsData/CategoryData/" + category + "-" + language + ".txt"; // ruta del fichero
		f = Gdx.files.external(path);
		ByteArrayOutputStream out = null; // Flujo de salida para escribir bytes
		ObjectOutputStream oos = null; // Nos permite escribir objetos en un flujo
		try {
			out = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(out);
			
			oos.writeObject(object);
			oos.flush(); // Nos aseguramos de que se han escrito bien los datos
			
			byte[] datos = out.toByteArray(); // Transformamos el objeto a array de bytes
			f.writeBytes(datos, false); // Lo escribimos en el fichero
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/* read(): lee datos de una categoría desde fichero */
	@SuppressWarnings("unchecked")
	public void read(String category, String language) {
		String path = "MissingWordsData/CategoryData/" + category + "-" + language + ".txt";
		f = Gdx.files.external(path);
		ByteArrayInputStream in = null;
		ObjectInputStream ois = null;
		
		try {
			in = new ByteArrayInputStream(f.readBytes());
			ois = new ObjectInputStream(in);
			
			/* Casting de Object a LinkedHashMap */
			categoryWords = (LinkedHashMap<String, Boolean>) ois.readObject();
			//System.out.println(categoryWords);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	/* addWord(): añade una palabra formada para incluirla en las estadísticas de categorías */
	public void addWord(String word) {
		if (categoryWords.containsKey(word) && categoryWords.get(word).equals(false))
			categoryWords.put(word, true);
	}

	/* -------------- Getters and Setters -------------- */
	
	public LinkedHashMap<String, Boolean> getCategoryWords() {
		return categoryWords;
	}
}
