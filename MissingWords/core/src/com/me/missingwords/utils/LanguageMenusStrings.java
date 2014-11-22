package com.me.missingwords.utils;

public interface LanguageMenusStrings {
	/* ---------- MENÚ PRINCIPAL ---------- */
	/* INGLÉS */
	public String playerCPU_en = "Player VS CPU";
	public String singlePlayer_en = "Single-Player";
	public String stats_en = "Stats";
	public String settings_en = "Settings";
	public String instructions_en = "Instructions";
	public String exit_en = "Exit";
	
	/* ALEMÁN */
	public String playerCPU_de = "Spieler VS CPU";
	public String singlePlayer_de = "Single-Player";
	public String stats_de = "Statistiken";
	public String settings_de = "Spieleinstellungen";
	public String instructions_de = "Spielanleitung";
	public String exit_de = "Exit";
	
	/* ---------- ESTADÍSTICAS ---------- */
	/* INGLÉS */
	public String statsTitle_en = "Stats";
	public String general_en = "- General:";
	public String hits_en = "% Hits: ";
	public String gamesWon_en = "Games won: ";
	public String gamesLost_en = "Game lost: ";
	public String largestWord_en = "Largest Word: ";
	public String bestWord_en = "Best Word: ";
	public String cluesUsed_en = "Clues used: ";
	public String categories_en = "- Categories:";
	
	/* ALEMÁN */
	public String statsTitle_de = "Statistiken";
	public String general_de = "- General:";
	public String hits_de = "% Treffer: ";
	public String gamesWon_de = "Gewonnene Spiele: ";
	public String gamesLost_de = "Verlorene Spiele: ";
	public String largestWord_de = "Längstes Wort: ";
	public String bestWord_de = "Bestes Wort: ";
	public String cluesUsed_de = "Verwendete Hilfen: ";
	public String categories_de = "- Themen:";
	
	/* ---------- ESTADÍSTICAS POR CATEGORÍA ---------- */
	/* INGLÉS */
	public String progress_en = "Progress:\n  ";
	
	/* ALEMÁN */
	public String progress_de = "Fortschritte:\n    ";
	
	/* ---------- AJUSTES ---------- */
	/* INGLÉS */
	public String settingsTitle_en = "Settings";
	public String soundOn_en = " Sound On";
	public String soundOff_en = " Sound Off";
	public String language_en = "Language";
	
	/* ALEMÁN */
	public String settingsTitle_de = "Spieleinstellungen";
	public String soundOn_de = " mit Ton";
	public String soundOff_de = " ohne Ton";
	public String language_de = "Sprachen";
	
	/* ---------- INSTRUCCIONES ---------- */
	/* INGLÉS */
	
	public String instructionsTitle_en = "Instructions";
	public String instructionsLabel_en = "Goal: Win the mini-game by making words. For each\n"
				+ "word you form you get rolls to move your character.\n"
				+ "Don't forget that you have to use at least one of your\n"
				+ "rolls before using the option \"wait\". Avoid the holes!\n"
				+ "Clues: You can only use 2 clues per turn. Min. 1 roll.\n";
	public String translation_en = "-> translation (-2 rolls).";
	public String firstLetter_en = "-> first letter (-1 roll).";
	public String length_en = "-> length of the word (-1 roll).";
	
	/* ALEMÁN */
	public String instructionsTitle_de = "Spielanleitung";
	public String instructionsLabel_de = "Ziel: Bilde mit den Buchstaben Wörter! Für jedes\n"
			    + "Wort darfst du würfeln und deine Spielerfigur\n"
			    + "fortbewegen. Du musst mindestens 1 X würfeln. Erst\n"
			    + "dann darfst du \"warten\" wählen. Achtung mit den\n"
			    + "Löchern! Hilfen: Du darfst nur 2 Hilfen pro Wort.";
	public String translation_de = "-> Übersetzung (-2 würfeln)";
	public String firstLetter_de = "-> Erster Buchstabe (-1 würfeln)";
	public String length_de = "-> Wortlänge (-1 würfeln)";
	
	/* ---------- SELECIÓN DE CATEGORÍA ---------- */
	/* INGLÉS */
	public String categorySelection_en = "Category Selection";
	
	/* ALEMÁN */
	public String categorySelection_de = "Themenauswahl";
	
	/* ---------- CATEGORÍAS ---------- */
	/* INGLÉS */
	public String days_en = "Days";
	public String months_en = "Months";
	public String wQuestions_en = "W-Questions";
	public String colours_en = "Colours";
	public String size_en = "Size";
	public String classroom_en = "Classroom";
	public String bodyParts_en = "Body-parts";
	public String feelings_en = "Feelings";
	public String university_en = "University";
	public String city_en = "City";
	public String freetime_en = "Freetime";
	
	/* ALEMÁN */
	public String days_de = "Wochentage";
	public String months_de = "Monate";
	public String wQuestions_de = "W-Fragen";
	public String colours_de = "Farben";
	public String size_de = "Grösse";
	public String classroom_de = "Klassenraum";
	public String bodyParts_de = "Körperteile";
	public String feelings_de = "Wie geht's?";
	public String university_de = "Uni (Plätze)";
	public String city_de = "Stadt (Plätze)";
	public String freetime_de = "Freizeit";
	
	
	public void updateLanguageStrings();
}
