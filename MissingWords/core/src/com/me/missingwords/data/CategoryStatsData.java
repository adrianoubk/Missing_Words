package com.me.missingwords.data;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedHashMap;

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
		
		f = Gdx.files.external("categoryData");
		FileHandle[] files = f.list();
		
		/* Comprobamos si se han creado los ficheros para las categorías */
		if (files.length == 0)
			try {
				createFiles();
			} catch (IOException e) {
				e.printStackTrace();
			}
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
		String path = "categoryData/" + category + "-" + language + ".txt"; // ruta del fichero
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
		String path = "categoryData/" + category + "-" + language + ".txt";
		f = Gdx.files.external(path);
		ByteArrayInputStream in = null;
		ObjectInputStream ois = null;
		
		try {
			in = new ByteArrayInputStream(f.readBytes());
			ois = new ObjectInputStream(in);
			
			/* Casting de Object a LinkedHashMap */
			categoryWords = (LinkedHashMap<String, Boolean>) ois.readObject();
			System.out.println(categoryWords);
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
