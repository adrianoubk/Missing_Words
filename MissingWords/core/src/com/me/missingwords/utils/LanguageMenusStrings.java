package com.me.missingwords.utils;

public interface LanguageMenusStrings {
	/* ---------- MEN� PRINCIPAL ---------- */
	/* INGL�S */
	public String playerCPU_en = "Player VS CPU";
	public String singlePlayer_en = "Single-Player";
	public String stats_en = "Stats";
	public String settings_en = "Settings";
	public String instructions_en = "Instructions";
	public String exit_en = "Exit";
	
	/* ALEM�N */
	public String playerCPU_de = "Spieler VS CPU";
	public String singlePlayer_de = "Single-Player";
	public String stats_de = "Statistiken";
	public String settings_de = "Spieleinstellungen";
	public String instructions_de = "Spielanleitung";
	public String exit_de = "Exit";
	
	/* ---------- ESTAD�STICAS ---------- */
	/* INGL�S */
	public String statsTitle_en = "Stats";
	public String general_en = "- General:";
	public String hits_en = "% Hits: ";
	public String gamesWon_en = "Games won: ";
	public String gamesLost_en = "Game lost: ";
	public String largestWord_en = "Largest Word: ";
	public String bestWord_en = "Best Word: ";
	public String cluesUsed_en = "Clues used: ";
	public String categories_en = "- Categories:";
	
	/* ALEM�N */
	public String statsTitle_de = "Statistiken";
	public String general_de = "- General:";
	public String hits_de = "% Treffer: ";
	public String gamesWon_de = "Gewonnene Spiele: ";
	public String gamesLost_de = "Verlorene Spiele: ";
	public String largestWord_de = "L�ngstes Wort: ";
	public String bestWord_de = "Bestes Wort: ";
	public String cluesUsed_de = "Verwendete Hilfen: ";
	public String categories_de = "- Themen:";
	
	/* ---------- ESTAD�STICAS POR CATEGOR�A ---------- */
	/* INGL�S */
	public String progress_en = "Progress:\n  ";
	
	/* ALEM�N */
	public String progress_de = "Fortschritte:\n    ";
	
	/* ---------- AJUSTES ---------- */
	/* INGL�S */
	public String settingsTitle_en = "Settings";
	public String soundOn_en = " Sound On";
	public String soundOff_en = " Sound Off";
	public String language_en = "Language";
	
	/* ALEM�N */
	public String settingsTitle_de = "Spieleinstellungen";
	public String soundOn_de = " mit Ton";
	public String soundOff_de = " ohne Ton";
	public String language_de = "Sprachen";
	
	/* ---------- INSTRUCCIONES ---------- */
	/* INGL�S */
	
	public String instructionsTitle_en = "Instructions";
	public String instructionsLabel_en = "Goal: Win the mini-game by making words. For each\n"
				+ "word you form you get rolls to move your character.\n"
				+ "Don't forget that you have to use at least one of your\n"
				+ "rolls before using the option \"wait\". Avoid the holes!\n"
				+ "Clues: You can only use 2 clues per turn. Min. 1 roll.\n";
	public String translation_en = "-> translation (-2 rolls).";
	public String firstLetter_en = "-> first letter (-1 roll).";
	public String length_en = "-> length of the word (-1 roll).";
	
	/* ALEM�N */
	public String instructionsTitle_de = "Spielanleitung";
	public String instructionsLabel_de = "Ziel: Bilde mit den Buchstaben W�rter! F�r jedes\n"
			    + "Wort darfst du w�rfeln und deine Spielerfigur\n"
			    + "fortbewegen. Du musst mindestens 1 X w�rfeln. Erst\n"
			    + "dann darfst du \"warten\" w�hlen. Achtung mit den\n"
			    + "L�chern! Hilfen: Du darfst nur 2 Hilfen pro Wort.";
	public String translation_de = "-> �bersetzung (-2 w�rfeln)";
	public String firstLetter_de = "-> Erster Buchstabe (-1 w�rfeln)";
	public String length_de = "-> Wortl�nge (-1 w�rfeln)";
	
	/* ---------- SELECI�N DE CATEGOR�A ---------- */
	/* INGL�S */
	public String categorySelection_en = "Category Selection";
	
	/* ALEM�N */
	public String categorySelection_de = "Themenauswahl";
	
	/* ---------- CATEGOR�AS ---------- */
	/* INGL�S */
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
	
	/* ALEM�N */
	public String days_de = "Wochentage";
	public String months_de = "Monate";
	public String wQuestions_de = "W-Fragen";
	public String colours_de = "Farben";
	public String size_de = "Gr�sse";
	public String classroom_de = "Klassenraum";
	public String bodyParts_de = "K�rperteile";
	public String feelings_de = "Wie geht's?";
	public String university_de = "Uni (Pl�tze)";
	public String city_de = "Stadt (Pl�tze)";
	public String freetime_de = "Freizeit";
	
	
	public void updateLanguageStrings();
}
