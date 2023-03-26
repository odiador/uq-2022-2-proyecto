package config;

import java.awt.Color;
import java.awt.Font;

import custom.Componente;

public class Constantes {

	public static final String[] strings = { "Crispeta Familiar", "Crispeta grande", "Crispeta mediana",
			"Crispeta pequeña", "Gaseosa grande", "Hamburguesa", "Nachos", "Perro", "Sándwich", "Stellar Cinema" };
	public static final Color defaultTextColor = new Color(200, 200, 200);
	public static final Color defaultBgColor = Componente.defaultColor;
	public static final Color defaultLineColor = defaultTextColor;
	public static final Font defaultFont = new Font("Coolvetica Rg", Font.PLAIN, 20);
	public static final int maxImgConfiSize = 250;
	public static final int maxImgConfiRnd = 50;
	public static final int minImgConfiSize = 100;
	public static final int minImgConfiRnd = 30;

}
