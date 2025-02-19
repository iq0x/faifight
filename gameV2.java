import java.util.Scanner;
import java.util.Random;
import java.io.*;

public class Main
{
	public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
	
	public static Scanner scanner = new Scanner(System.in);
	
	public static ActionFigur myenemy;
	public static ActionFigur enemy;
	public static Weapon enemyWeapon;
	public static Armor enemyArmor;
	public static Level levelStage;
	public static Weather levelWeather;
	public static Magic playerMagic;
	
	public static void main(String[] args)
    {
		clrscr();
		titel();
		
		kbhit();
		clrscr();
		
		ActionFigur[] character = new ActionFigur[17];
		loadCharacters(character);


		System.out.print("choose your character:\n");
		ActionFigur player = getCharacter(character);
		ActionFigur enemy = getEnemy(character);
		
		Weapon[] weapon = new Weapon[16];
		loadWeapons(weapon);
		
		clrscr();
		System.out.print("choose your weapon:\n");
		Weapon playerWeapon = getWeapon(weapon);
		Weapon enemyWeapon = getEnemyWeapon(weapon);
		
		Armor[] armor = new Armor[13];
		loadArmor(armor);
		
		clrscr();
		System.out.print("choose your armor:\n");
		Armor playerArmor = getArmor(armor);
		Armor enemyArmor = getEnemyArmor(armor);
		
		Level[] stage 	= new Level[3];
		loadStage(stage);
		Level levelStage = getStage(stage);
		
		Magic[] magic 	= new Magic[10];
		loadMagic(magic);
		Magic playerMagic = getMagic(magic);
		
		clrscr();
		System.out.println("your character is: " + ANSI_GREEN +  player.getCharacterName() + ANSI_RESET);	
		System.out.println("your weapon is: " + ANSI_GREEN + playerWeapon.getWeaponName() + ANSI_RESET);
		System.out.println("your armor is: " + ANSI_GREEN + playerArmor.getArmorName() + ANSI_RESET);
		System.out.println("\n");
		
		for (int tab1 = 0; tab1 <= 30; tab1++)
			{	
				System.out.print("═");
				if (tab1 == 12)
					System.out.print(" vs ");
				try{Thread.sleep(20);}catch(InterruptedException e){System.out.println(e);}
			}
			
		System.out.println("\n");
		
		System.out.println("enemy is: " + ANSI_RED + enemy.getCharacterName() + ANSI_RESET);
		System.out.println("enemys weapon is: " + ANSI_RED + enemyWeapon.getWeaponName() + ANSI_RESET);
		System.out.println("enemys armor is: " + ANSI_RED + enemyArmor.getArmorName() + ANSI_RESET);
		System.out.println("\n\n");	
		
		kbhit();
		clrscr();
			
		
		if (fight(player, enemy, playerWeapon, enemyWeapon, playerArmor, enemyArmor, levelStage, playerMagic) != player) 
			GAMEOVER();
		else
			GAMEWIN();
    } 
    
    
    
 //------------------------------------------------------------------------------------------------------------------------------------------
	
	
	public static void loadMagic(Magic[] magic)
	{
		magic[0] = new Magic(1, "fireball", 10, 25);
		magic[1] = new Magic(2, "fireball", 10, 25);
		magic[2] = new Magic(3, "fireball", 10, 25);
		magic[3] = new Magic(4, "fireball", 10, 25);
		magic[4] = new Magic(5, "fireball", 10, 25);
		magic[5] = new Magic(6, "fireball", 10, 25);
		magic[6] = new Magic(7, "fireball", 10, 25);
		magic[7] = new Magic(8, "fireball", 10, 25);
		magic[8] = new Magic(9, "fireball", 10, 25);
		magic[9] = new Magic(10, "fireball", 10, 25);
	}
	
	
	public static void loadWeather(Weather[] weather)
	{
		weather[0] = new Weather(1, "normal", "normal");
		weather[1] = new Weather(2, "snow", "frosty");
		weather[2] = new Weather(3, "rain", "muddy");
	}
	
	public static void loadStage(Level[] stage)
	{
		stage[0] = new Level(1, "forrest", "wood");
		stage[1] = new Level(2, "venus", "space");
		stage[2] = new Level(3, "island", "water");
	}
	
	public static void loadArmor(Armor[] armor)
	{
		armor[0] = new Armor(1, "nothing", 3, 4);
		armor[1] = new Armor(2, "leather harnish", 6, 4);
		armor[2] = new Armor(3, "metal harnish", 6, 5);
		armor[3] = new Armor(4, "titan harnish", 9, 6);
		armor[4] = new Armor(5, "alu harnish", 8, 6);
		armor[5] = new Armor(6, "orc harnish", 9, 6);
		armor[6] = new Armor(7, "elf harnish", 8, 6);
		armor[7] = new Armor(8, "plasma harnish", 5, 6);
		armor[8] = new Armor(9, "wood harnish", 7, 6);
		armor[9] = new Armor(10, "gold harnish", 8, 6);
		armor[10] = new Armor(11, "silver harnish", 5, 6);
		armor[11] = new Armor(12, "giga harnish", 8, 6);
		armor[12] = new Armor(13, "ultra harnish", 4, 6);
	}
	
	public static void loadWeapons(Weapon[] weapon)
	{
		weapon[0] = new Weapon(1, "axe", 5, 2);
		weapon[1] = new Weapon(2, "sword", 6, 3);
		weapon[2] = new Weapon(3, "plasmagun", 14, 7);
		weapon[3] = new Weapon(4, "handgun", 8, 6);
		weapon[4] = new Weapon(5, "rocketlouncher", 3, 2);
		weapon[5] = new Weapon(6, "lasergun", 6, 3);
		weapon[6] = new Weapon(7, "fusiongun", 14, 7);
		weapon[7] = new Weapon(8, "genkidama", 8, 6);
		weapon[8] = new Weapon(9, "blaster", 4, 2);
		weapon[9] = new Weapon(10, "laserblaster", 6, 3);
		weapon[10] = new Weapon(11, "ultrablaster", 14, 7);
		weapon[11] = new Weapon(12, "stinger", 8, 6);
		weapon[12] = new Weapon(13, "faser", 3, 2);
		weapon[13] = new Weapon(14, "lasersword", 6, 3);
		weapon[14] = new Weapon(15, "megawumme", 14, 7);
		weapon[15] = new Weapon(16, "supralouncher", 8, 6);
	}
	
	public static void loadCharacters(ActionFigur[] character)
	{
		character[0] 	= new ActionFigur(1, "helmut", "soldiera", 10, 3, 200, 50);
        character[1]	= new ActionFigur(2, "sergio", "heilerin", 5, 2, 200, 50);
        character[2] 	= new ActionFigur(3, "soeren", "gumbaaaa", 6, 1, 200, 50);
		character[3] 	= new ActionFigur(4, "mikeee", "titanaaa", 9, 3, 200, 50); 
		character[4] 	= new ActionFigur(5, "oliverA", "vikinger", 7, 2, 200, 50);
		character[5] 	= new ActionFigur(6, "andree", "vikinger", 7, 1, 200, 50);
		character[6] 	= new ActionFigur(7, "oliverB", "pirataaa", 6, 2, 200, 50);
		character[7] 	= new ActionFigur(8, "emreeee", "pirataaa", 5, 3, 200, 50);
		character[8] 	= new ActionFigur(9, "sascha", "pirataaa", 6, 2, 200, 50);
		character[9] 	= new ActionFigur(10, "stefee", "pirataaa", 5, 2, 200, 50);
		character[10] 	= new ActionFigur(11, "stefee", "pirataaa", 5, 2, 200, 50);
		character[11] 	= new ActionFigur(12, "Florian", "pirataaa", 6, 1, 200, 50);
		character[12] 	= new ActionFigur(13, "Salman", "pirataaa", 6, 3, 200, 50);
		character[13] 	= new ActionFigur(14, "Ashanti", "pirataaa", 6, 2, 200, 50); 
		character[14] 	= new ActionFigur(15, "jannnnA", "pirataaa", 6, 2, 200, 50);
		character[15] 	= new ActionFigur(16, "jannnnB", "pirataaa", 5, 1, 200, 50);
		character[16] 	= new ActionFigur(17, "christian", "pirataaa", 6, 1, 200, 50); 
	}
 
    public static ActionFigur fight(ActionFigur player, ActionFigur enemy, Weapon playerWeapon, Weapon enemyWeapon, Armor playerArmor, Armor enemyArmor, Level levelStage, Magic playerMagic)
    {
		int round = 0;
		ActionFigur winner = player;
		Random rand = new Random();
		int RoundEND = 10;
		int playerWINS = 0;
		int enemyWINS = 0;
		int animation = 0;
		int playerLife = player.getCharacterLife();
		int enemyLife = enemy.getCharacterLife();
		
		int playerMana = player.getCharacterMana();
		int enemyMana = enemy.getCharacterMana();
		int whoIS = 0;
		while (playerLife > 0)
		{
			Weather[] weather = new Weather[3];
			loadWeather(weather);
		
			Weather levelWeather = getWeather(weather);		
			System.out.println("════════════════════════════════════════════════");
			System.out.println(" FIGHT\tRound:" + round);
			System.out.println("════════════════════════════════════════════════");
			System.out.println(" world:"+ levelStage.getLevelName() + "\t\tweather:"+ levelWeather.getWeatherName());
			System.out.println("════════════════════════════════════════════════");
			
	
			 
			if (levelWeather.getWeatherName() == "rain")
			{
				if (animation == 0)
				{
					rainForrestA();
					animation++;
				}
				else if (animation == 1)
				{
					rainForrestB();
					animation--;
				}
			}
			
			if (levelWeather.getWeatherName() == "snow")
			{
				if (animation == 0)
				{
					snowForrestA();
					animation++;
				}
				else if (animation == 1)
				{
					snowForrestB();
					animation--;
				}
			}
			
			if (levelWeather.getWeatherName() == "normal")
			{
				if (animation == 0)
				{
					forrestA();
					animation++;
				}
				else if (animation == 1)
				{
					forrestB();
					animation--;
				}
			}
			
	
			
			System.out.println("════════════════════════════════════════════════");
			System.out.println("\t " + player.getCharacterName()
			+ANSI_YELLOW + "(" + playerLife + ")" + ANSI_RESET 
			+ANSI_BLUE + "(" + playerMana + ")" + ANSI_RESET +    
			
			" vs " 
			
			+ enemy.getCharacterName()
			+ANSI_YELLOW + "("+enemyLife+")"+ANSI_RESET+ ANSI_BLUE +"("+ enemyMana +")" +ANSI_RESET );
			System.out.println("════════════════════════════════════════════════");
				
		
			int attackBonus = rand.nextInt(5);
			String superBonus = " ";
			//int defenseBonus = rand.nextInt(3);
			
			int TOTALDEFENSEPLAYER = player.getCharacterDefense() + playerArmor.getArmorDefense();
			int TOTALDEFENSEENEMY = enemyArmor.getArmorDefense() + enemy.getCharacterDefense();
			int TOTALATTACKPLAYER = attackBonus + playerWeapon.getWeaponAttack() + player.getCharacterAttack();
			int MAGICATTACKPLAYER = playerMagic.getMagicDamage();
			int TOTALATTACKENEMY = enemyWeapon.getWeaponAttack() + enemy.getCharacterAttack();
			
//--------------------------------------------------------------------------		
			
			System.out.println(ANSI_GREEN + "1. Attack:\t" + playerWeapon.getWeaponName() + ANSI_RESET);
			System.out.println(ANSI_YELLOW +"2. Magic:\t" + playerMagic.getMagicName() + ANSI_RESET);
			System.out.println(ANSI_BLUE +"3. Item:\t" + "no Item" + ANSI_RESET);
			
//--------------------- fight round player ---------------------------------
				
				
			if (whoIS == 0)
			{		
				whoIS++;
				

				int chooseFight = scanner.nextInt();
				
				if (chooseFight == 1)
				{
					round++;
					enemyLife = enemyLife - TOTALATTACKPLAYER - attackBonus + TOTALDEFENSEENEMY;
				}
				
				
					if (chooseFight == 2)
					{
						if (playerMana >= playerMagic.getMagicCost())
						{
							round++;
							enemyLife = enemyLife - MAGICATTACKPLAYER;
							playerMana = playerMana - playerMagic.getMagicCost();
						}
						else
						{
							System.out.println("no mana!");
							kbhit();
						}
					}
					
					if (chooseFight == 3)
					{
						System.out.println("Items no available!");
						kbhit();
					}
			
			
	
			
			}
		
			
//--------------------- fight round enemy ---------------------------------	
			else if (whoIS == 1)
			{	
				whoIS--;
				kbhit();
				round++;
				playerLife = playerLife - TOTALATTACKENEMY - attackBonus + TOTALDEFENSEPLAYER;
			}
			clrscr();
			if (playerLife <= 0)
			{
				winner = enemy;
				break;
			}
			if (enemyLife <= 0)
			{
				winner = player;
				break;
			}
		}
		
		
		return winner;
	}
	
	
	
	
	
    public static Weather getWeather(Weather[] weather)
    {
		Random rand = new Random();
		int weatherNumber = rand.nextInt(3)+0;
		levelWeather = weather[weatherNumber];
	
		return levelWeather;
    } 
    
    
    
    public static Magic getMagic(Magic[] magic)
    {
		playerMagic = magic[0];
	
		return playerMagic;
    } 
    
    
    
     public static Level getStage(Level[] stage)
    {
		levelStage = stage[0];
	
		return levelStage;
    } 
    
    public static ActionFigur getEnemy(ActionFigur[] character)
    {
		Random rand = new Random();
		int enemyNumber = rand.nextInt(16);
		
		myenemy = character[enemyNumber];
	
		return myenemy;
    } 
    
    public static Weapon getEnemyWeapon(Weapon[] weapon)
    {
		
		Random rand = new Random();
		
		int enemyWeaponNumber = rand.nextInt(16);
		
		enemyWeapon = weapon[enemyWeaponNumber];
	
		return enemyWeapon;
	}
	
	public static Armor getEnemyArmor(Armor[] armor)
    {
		
		Random rand = new Random();
		
		int enemyArmorNumber = rand.nextInt(13);
		
		enemyArmor = armor[enemyArmorNumber];
	
		return enemyArmor;
	}
	
    public static ActionFigur getCharacter(ActionFigur[] character)
    {
		int i = 0;
		System.out.println("ID\tname\tspecies\tattack\tdefense");
        System.out.println("════════════════════════════════════════════════");
		while (i < character.length)
		{
			System.out.println(character[i].getCharacterID()
			 +" "+ character[i].getCharacterName()
			 +"\t"+ character[i].getCharacterSpecies()
			 +"\t"+ character[i].getCharacterAttack()
			 +"\t"+ character[i].getCharacterDefense());
			i++;
		}
		System.out.println("════════════════════════════════════════════════");
		
		int chooseCharacter = scanner.nextInt();
		
		ActionFigur player = character[chooseCharacter-1];
		
		return player;
	}
	
	
	public static Weapon getWeapon(Weapon[] weapon)
    {
		int j = 0;
		System.out.println("ID\tname\tattack");
        System.out.println("════════════════════════════════════════════════");
		while (j < weapon.length)
		{
			System.out.println(weapon[j].getWeaponID() +" "+ weapon[j].getWeaponName() +"\t"+ weapon[j].getWeaponAttack());
			j++;
		}
		System.out.println("════════════════════════════════════════════════");
		
		int chooseWeapon = scanner.nextInt();
		
		Weapon playerWeapon = weapon[chooseWeapon-1];
		
		return playerWeapon;
	}
	
	public static Armor getArmor(Armor[] armor)
    {
		int k = 0;
		System.out.println("ID\tname\tdefense");
        System.out.println("════════════════════════════════════════════════");
		while (k < armor.length)
		{
			System.out.println(armor[k].getArmorID() +" "+ armor[k].getArmorName() +"\t"+ armor[k].getArmorDefense());
			k++;
		}
		System.out.println("════════════════════════════════════════════════");
		
		int chooseArmor = scanner.nextInt();
		
		Armor playerArmor = armor[chooseArmor-1];
		
		return playerArmor;
	}
	
	public static void clrscr()
    {
		System.out.print("\033[H\033[2J"); 
		System.out.flush();  
	}
	
	static void kbhit()
    {
		System.out.println("\n\t\t\tpress ENTER");
		try 
		{
			System.in.read();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	static void forrestA()
	{
		System.out.println("     ░▒░                               ░▒░ ");
		System.out.println("   ░▒▒▒▒▒░                           ░▒▒▒▒▒░     ");
		System.out.println("  ░▒▒▒▒▒▒▒░        ░▒░              ░▒▒▒▒▒▒▒░     ");
		System.out.println("  ░▒▒▒▒▒▒▒░      ░▒▒▒▒▒░            ░▒▒▒▒▒▒▒░    "  );
		System.out.println("   ░▒▒▒▒▒░ ╔═╗  ░▒▒▒▒▒▒▒░            ░▒▒▒▒▒░     ");
		System.out.println("     ░▓░   ╚╦╝  ░▒▒▒▒▒▒▒░       ╔═╗    ░▓░    ");
		System.out.println("      ▓    ═╬═   ░▒▒▒▒▒░        ╚╦╝     ▓  ");
		System.out.println("      ▓    ╔╩╗     ░▓░          ═╬═     ▓  ");
		System.out.println("      ▓             ▓           ╔╩╗     ▓   ");
	}
	
	static void forrestB()
	{
		System.out.println("     ░▒░                               ░▒░ ");
		System.out.println("   ░▒▒▒▒▒░                           ░▒▒▒▒▒░     ");
		System.out.println("  ░▒▒▒▒▒▒▒░        ░▒░              ░▒▒▒▒▒▒▒░     ");
		System.out.println("  ░▒▒▒▒▒▒▒░      ░▒▒▒▒▒░            ░▒▒▒▒▒▒▒░    "  );
		System.out.println("   ░▒▒▒▒▒░  ╔═╗ ░▒▒▒▒▒▒▒░            ░▒▒▒▒▒░     ");
		System.out.println("     ░▓░    ╚╦╝ ░▒▒▒▒▒▒▒░      ╔═╗     ░▓░    ");
		System.out.println("      ▓     ═╬═  ░▒▒▒▒▒░       ╚╦╝      ▓  ");
		System.out.println("      ▓     ╔╩╗    ░▓░         ═╬═      ▓  ");
		System.out.println("      ▓             ▓          ╔╩╗      ▓   ");
	}
	
	static void snowForrestA()
	{
		System.out.println("     ░▒░      ❄                ❄       ░▒░ ");
		System.out.println("   ░▒▒▒▒▒░                           ░▒▒▒▒▒░     ");
		System.out.println("  ░▒▒▒▒▒▒▒░        ░▒░              ░▒▒▒▒▒▒▒░     ");
		System.out.println("  ░▒▒▒▒▒▒▒░      ░▒▒▒▒▒░     ❄      ░▒▒▒▒▒▒▒░    "  );
		System.out.println("   ░▒▒▒▒▒░ ╔═╗  ░▒▒▒▒▒▒▒░            ░▒▒▒▒▒░     ");
		System.out.println("     ░▓░   ╚╦╝  ░▒▒▒▒▒▒▒░       ╔═╗    ░▓░    ");
		System.out.println("      ▓    ═╬═   ░▒▒▒▒▒░        ╚╦╝     ▓  ");
		System.out.println("   ❄  ▓    ╔╩╗     ░▓░    ❄     ═╬═   ❄ ▓  ");
		System.out.println("      ▓             ▓           ╔╩╗     ▓   ");
	}
	
	static void snowForrestB()
	{
		System.out.println("     ░▒░             ❄                 ░▒░ ");
		System.out.println("   ░▒▒▒▒▒░                           ░▒▒▒▒▒░   ❄   ");
		System.out.println("  ░▒▒▒▒▒▒▒░        ░▒░          ❄   ░▒▒▒▒▒▒▒░     ");
		System.out.println("  ░▒▒▒▒▒▒▒░  ❄   ░▒▒▒▒▒░            ░▒▒▒▒▒▒▒░    "  );
		System.out.println("   ░▒▒▒▒▒░  ╔═╗ ░▒▒▒▒▒▒▒░            ░▒▒▒▒▒░     ");
		System.out.println("  ❄  ░▓░    ╚╦╝ ░▒▒▒▒▒▒▒░   ❄  ╔═╗     ░▓░    ");
		System.out.println("      ▓     ═╬═  ░▒▒▒▒▒░       ╚╦╝      ▓  ");
		System.out.println("      ▓     ╔╩╗    ░▓░         ═╬═      ▓    ❄ ");
		System.out.println("      ▓  ❄          ▓          ╔╩╗      ▓   ");
	}
	
	
	static void rainForrestA()
	{
		System.out.println("     ░▒░     '     '     '     '       ░▒░ ");
		System.out.println("   ░▒▒▒▒▒░                           ░▒▒▒▒▒░     ");
		System.out.println("  ░▒▒▒▒▒▒▒░        ░▒░              ░▒▒▒▒▒▒▒░     ");
		System.out.println("  ░▒▒▒▒▒▒▒░      ░▒▒▒▒▒░     '      ░▒▒▒▒▒▒▒░    "  );
		System.out.println("   ░▒▒▒▒▒░ ╔═╗  ░▒▒▒▒▒▒▒░            ░▒▒▒▒▒░     ");
		System.out.println("     ░▓░   ╚╦╝  ░▒▒▒▒▒▒▒░       ╔═╗    ░▓░    ");
		System.out.println("      ▓    ═╬═   ░▒▒▒▒▒░        ╚╦╝     ▓  ");
		System.out.println("   '  ▓    ╔╩╗  '  ░▓░    '     ═╬═   ' ▓  ");
		System.out.println("      ▓             ▓           ╔╩╗     ▓   ");
	}
	
	static void rainForrestB()
	{
		System.out.println("     ░▒░       '      '          '     ░▒░ ");
		System.out.println("   ░▒▒▒▒▒░                           ░▒▒▒▒▒░   '   ");
		System.out.println("  ░▒▒▒▒▒▒▒░        ░▒░     '    '   ░▒▒▒▒▒▒▒░     ");
		System.out.println("  ░▒▒▒▒▒▒▒░  '   ░▒▒▒▒▒░            ░▒▒▒▒▒▒▒░    "  );
		System.out.println("   ░▒▒▒▒▒░  ╔═╗ ░▒▒▒▒▒▒▒░            ░▒▒▒▒▒░     ");
		System.out.println("  '  ░▓░    ╚╦╝ ░▒▒▒▒▒▒▒░   '  ╔═╗     ░▓░    ");
		System.out.println("      ▓     ═╬═  ░▒▒▒▒▒░       ╚╦╝      ▓  ");
		System.out.println("      ▓     ╔╩╗    ░▓░   '     ═╬═   '  ▓    ' ");
		System.out.println("      ▓  '          ▓          ╔╩╗      ▓   ");
	}
	   
	   
	static void titel()
	{
		System.out.println(ANSI_GREEN +"\n\n\t\t\tWelcome to Fight Class\n\n" + ANSI_RESET);
		System.out.println(ANSI_PURPLE +"╔═══════╗   ╔═══════╗   ╔═══════╗   ╔═══════╗   ╔═══════╗   ╔═══════╗"+ANSI_RESET);
		System.out.println(ANSI_CYAN +"║ X   X ║   ║ O   X ║   ║ X   O ║   ║ O   X ║   ║ C   O ║   ║ -   X ║"+ANSI_RESET);
		System.out.println(ANSI_YELLOW +"╚═╗   ╔═╝   ╚═╗   ╔═╝   ╚═╗   ╔═╝   ╚═╗   ╔═╝   ╚═╗   ╔═╝   ╚═╗   ╔═╝"+ANSI_RESET);
		System.out.println(ANSI_BLUE +"  ║+++║       ║---║       ║┕━┙║       ║┍━┑║       ║═══║       ║+++║"+ANSI_RESET);
		System.out.println(ANSI_RED +"  ╚═══╝       ╚═══╝       ╚═══╝       ╚═══╝       ╚═══╝       ╚═══╝"+ANSI_RESET);

		System.out.println(ANSI_GREEN +"█████████████████████████████████████████████████████████████████████"+ ANSI_RESET);
		System.out.println(ANSI_BLUE +"██"+ ANSI_RESET+ANSI_RED+"    ╔═══  ║ ╔═══╗ ║   ║ ══╦══    ╔═══  ║    ╔═══╗ ╔═══  ╔═══     "+ANSI_RESET+ANSI_GREEN +"██"+ ANSI_RESET);
		System.out.println(ANSI_BLUE +"██"+ ANSI_RESET+ANSI_RED+"    ╠═══  ║ ║  ═╗ ╠═══╣   ║      ║     ║    ╠═══╣ ╚══╗  ╚══╗     "+ANSI_RESET+ANSI_GREEN +"██"+ ANSI_RESET);
		System.out.println(ANSI_BLUE +"██"+ ANSI_RESET+ANSI_RED+"    ║     ║ ╚═══╝ ║   ║   ║      ╚════ ╚═══ ║   ║ ═══╝  ═══╝ v0.2"+ANSI_RESET+ANSI_GREEN +"██"+ ANSI_RESET);
		System.out.println(ANSI_BLUE +"█████████████████████████████████████████████████████████████████████"+ ANSI_RESET);
	}
	
	static void GAMEWIN()
	{
		System.out.println("                 WIN");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("          WIN");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("                                 WIN");
		System.out.println("");
		System.out.println("");
		System.out.println("═════════════════════════════════════════════════");
		try{Thread.sleep(500);}catch(InterruptedException e){System.out.println(e);}
		
		clrscr();
		
		System.out.println("         WIN");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("                  WIN");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("                                    WIN");
		System.out.println("");
		System.out.println("");
		System.out.println("        ╱ ╲ ");	
		System.out.println("═════════════════════════════════════════════════");
		try{Thread.sleep(500);}catch(InterruptedException e){System.out.println(e);}
		
		clrscr();
		System.out.println("");
		System.out.println("                                   WIN");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("                            WIN");
		System.out.println("");
		System.out.println("             WIN");
		System.out.println("");
		System.out.println("");
		System.out.println("        ╱ ╲ ");
		System.out.println("       ╱   ╲ ");	
		System.out.println("═════════════════════════════════════════════════");
		try{Thread.sleep(500);}catch(InterruptedException e){System.out.println(e);}
		
		clrscr();
		System.out.println("");
		System.out.println("");
		System.out.println("           WIN");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("                              WIN");
		System.out.println("");
		System.out.println("");
		System.out.println("        ╱ ╲ ");
		System.out.println("       ╱   ╲ ");
		System.out.println("      │     │");
		System.out.println("═════════════════════════════════════════════════");
		try{Thread.sleep(500);}catch(InterruptedException e){System.out.println(e);}
		
		clrscr();
		System.out.println("");
		System.out.println("                WIN");
		System.out.println("");
		System.out.println("");
		System.out.println("                                 WIN");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("        ╱ ╲ ");
		System.out.println("       ╱   ╲ ");
		System.out.println("      │     │                    WIN");
		System.out.println("      │ ┍┑  │");
		System.out.println("═════════════════════════════════════════════════");
		try{Thread.sleep(500);}catch(InterruptedException e){System.out.println(e);}
		
		clrscr();
		System.out.println("   WIN        ");
		System.out.println("                               WIN");
		System.out.println("           ");
		System.out.println("           ");
		System.out.println("           WIN");
		System.out.println("           ");
		System.out.println("           ");
		System.out.println("        ╱ ╲ ");
		System.out.println("       ╱   ╲                WIN");
		System.out.println("      │     │");
		System.out.println("      │ ┍┑  │");
		System.out.println("     ╱│ ┕┙  │╲");
		System.out.println("═════════════════════════════════════════════════");
		try{Thread.sleep(500);}catch(InterruptedException e){System.out.println(e);}
		
		clrscr();
		System.out.println("");
		System.out.println("WIN");
		System.out.println("");
		System.out.println("                               WIN");
		System.out.println("");
		System.out.println("");
		System.out.println("        ╱ ╲ ");
		System.out.println("       ╱   ╲ ");
		System.out.println("      │     │          WIN");
		System.out.println("      │ ┍┑  │");
		System.out.println("     ╱│ ┕┙  │╲");
		System.out.println("      │ ┍┑  │");
		System.out.println("═════════════════════════════════════════════════");
		try{Thread.sleep(500);}catch(InterruptedException e){System.out.println(e);}
		
		clrscr();
		System.out.println("");
		System.out.println("          WIN");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("        ╱ ╲                    WIN");
		System.out.println("       ╱   ╲ ");
		System.out.println("      │     │");
		System.out.println("      │ ┍┑  │");
		System.out.println("     ╱│ ┕┙  │╲");
		System.out.println("      │ ┍┑  │");
		System.out.println("      │ ┕┙  │");
		System.out.println("═════════════════════════════════════════════════");
		try{Thread.sleep(500);}catch(InterruptedException e){System.out.println(e);}
		
		clrscr();
		System.out.println("                                  WIN");
		System.out.println("");
		System.out.println("             WIN");
		System.out.println("");
		System.out.println("        ╱ ╲ ");
		System.out.println("       ╱   ╲ ");
		System.out.println("      │     │");
		System.out.println("      │ ┍┑  │               WIN");
		System.out.println("     ╱│ ┕┙  │╲");
		System.out.println("      │ ┍┑  │");
		System.out.println("      │ ┕┙  │");
		System.out.println("      │     │");
		System.out.println("═════════════════════════════════════════════════");
		try{Thread.sleep(500);}catch(InterruptedException e){System.out.println(e);}
		
		clrscr();
		System.out.println("            WIN");
		System.out.println("                               WIN");
		System.out.println("");
		System.out.println("        ╱ ╲ ");
		System.out.println("       ╱   ╲ ");
		System.out.println("      │     │           WIN");
		System.out.println("      │ ┍┑  │");
		System.out.println("     ╱│ ┕┙  │╲");
		System.out.println("      │ ┍┑  │");
		System.out.println("      │ ┕┙  │");
		System.out.println("      │     │");
		System.out.println("      ┕━━━━━┙                   WIN");
		System.out.println("═════════════════════════════════════════════════");
		try{Thread.sleep(500);}catch(InterruptedException e){System.out.println(e);}
			
		clrscr();
		System.out.println("");
		System.out.println("        ╱ ╲ WIN");
		System.out.println("       ╱   ╲ ");
		System.out.println("      ╱     ╲ ");		
		System.out.println("      │     │                   WIN");
		System.out.println("      │ ┍┑  │");
		System.out.println("     ╱│ ┕┙  │╲");
		System.out.println("      │ ┍┑  │");
		System.out.println("      │ ┕┙  │      WIN");
		System.out.println("      │     │");
		System.out.println("      ┕━━━━━┙");
		System.out.println("        ▚  ▚ ▚");
		System.out.println("═════════════════════════════════════════════════");
		try{Thread.sleep(500);}catch(InterruptedException e){System.out.println(e);}
		
		clrscr();

		System.out.println("        ╱ ╲                WIN ");
		System.out.println("       ╱   ╲ ");
		System.out.println("      ╱     ╲ ");		
		System.out.println("      │     │");
		System.out.println("      │ ┍┑  │");
		System.out.println("     ╱│ ┕┙  │╲");
		System.out.println("      │ ┍┑  │        WIN");
		System.out.println("      │ ┕┙  │");
		System.out.println("      │     │");
		System.out.println("      ┕━━━━━┙");
		System.out.println("       ▞ ▚▞ ▚                   WIN");
		System.out.println("           ");
		System.out.println("═════════════════════════════════════════════════");
		try{Thread.sleep(500);}catch(InterruptedException e){System.out.println(e);}
		
		clrscr();
		System.out.println("       ╱   ╲         WIN");
		System.out.println("      ╱     ╲ ");		
		System.out.println("      │     │");
		System.out.println("      │ ┍┑  │");
		System.out.println("     ╱│ ┕┙  │╲                   WIN");
		System.out.println("      │ ┍┑  │");
		System.out.println("      │ ┕┙  │");
		System.out.println("      │     │");
		System.out.println("      ┕━━━━━┙");
		System.out.println("        ▚  ▚ ▚        WIN");
		System.out.println("           ");
		System.out.println("           ");
		System.out.println("═════════════════════════════════════════════════");
		try{Thread.sleep(500);}catch(InterruptedException e){System.out.println(e);}
		
		clrscr();
		System.out.println("      ╱     ╲                WIN");		
		System.out.println("      │     │");
		System.out.println("      │ ┍┑  │");
		System.out.println("     ╱│ ┕┙  │╲");
		System.out.println("      │ ┍┑  │");
		System.out.println("      │ ┕┙  │          WIN");
		System.out.println("      │     │");
		System.out.println("      ┕━━━━━┙");
		System.out.println("       ▞ ▚▞ ▚");
		System.out.println("           ");
		System.out.println("       WIN     ");
		System.out.println("           ");
		System.out.println("═════════════════════════════════════════════════");
		try{Thread.sleep(500);}catch(InterruptedException e){System.out.println(e);}
		
		clrscr();	
		System.out.println("      │     │");
		System.out.println("      │ ┍┑  │    WIN         ");
		System.out.println("     ╱│ ┕┙  │╲");
		System.out.println("      │ ┍┑  │");
		System.out.println("      │ ┕┙  │");
		System.out.println("      │     │");
		System.out.println("      ┕━━━━━┙                         WIN");
		System.out.println("        ▚ ▚ ▚");
		System.out.println("           ");
		System.out.println("           ");
		System.out.println("   WIN              WIN");
		System.out.println("           ");
		System.out.println("═════════════════════════════════════════════════");
		try{Thread.sleep(500);}catch(InterruptedException e){System.out.println(e);}
		
		clrscr();	
		System.out.println("      │ ┍┑  │                 WIN");
		System.out.println("     ╱│ ┕┙  │╲");
		System.out.println("      │ ┍┑  │");
		System.out.println("      │ ┕┙  │");
		System.out.println("      │     │");
		System.out.println("      ┕━━━━━┙        WIN");
		System.out.println("       ▞ ▚▞ ▚");
		System.out.println("           ");
		System.out.println("         WIN  ");
		System.out.println("           ");
		System.out.println("           ");
		System.out.println("                                  WIN  ");
		System.out.println("═════════════════════════════════════════════════");
		try{Thread.sleep(500);}catch(InterruptedException e){System.out.println(e);}
		
		clrscr();	
		System.out.println("     ╱│ ┕┙  │╲");
		System.out.println("      │ ┍┑  │");
		System.out.println("      │ ┕┙  │                     ");
		System.out.println("      │     │");
		System.out.println("      ┕━━━━━┙");
		System.out.println("        ▚  ▚ ▚");
		System.out.println("           ");
		System.out.println("           ");
		System.out.println("           ");
		System.out.println("                    WIN  ");
		System.out.println("           ");
		System.out.println("           ");
		System.out.println("═════════════════════════════════════════════════");
		try{Thread.sleep(500);}catch(InterruptedException e){System.out.println(e);}
		
		clrscr();	
		System.out.println("      │ ┍┑  │");
		System.out.println("      │ ┕┙  │      WIN");
		System.out.println("      │     │");
		System.out.println("      ┕━━━━━┙");
		System.out.println("       ▞ ▚▞ ▚");
		System.out.println("           ");
		System.out.println("                          WIN ");
		System.out.println("           ");
		System.out.println("           ");
		System.out.println("    WIN       ");
		System.out.println("           ");
		System.out.println("           ");
		System.out.println("═════════════════════════════════════════════════");
		try{Thread.sleep(500);}catch(InterruptedException e){System.out.println(e);}
		
		clrscr();	
		System.out.println("      │ ┕┙  │");
		System.out.println("      │     │");
		System.out.println("      ┕━━━━━┙");
		System.out.println("        ▚  ▚ ▚");
		System.out.println("           ");
		System.out.println("     WIN      ");
		System.out.println("           ");
		System.out.println("                               WIN ");
		System.out.println("           ");
		System.out.println("           ");
		System.out.println("  WIN         ");
		System.out.println("           ");
		System.out.println("═════════════════════════════════════════════════");
		try{Thread.sleep(500);}catch(InterruptedException e){System.out.println(e);}
		
		clrscr();	
		System.out.println("      │     │                   WIN");
		System.out.println("      ┕━━━━━┙");
		System.out.println("       ▞ ▚▞ ▚");
		System.out.println("           ");
		System.out.println("           ");
		System.out.println("       WIN    ");
		System.out.println("           ");
		System.out.println("           ");
		System.out.println("           ");
		System.out.println("                         WIN    ");
		System.out.println("           ");
		System.out.println("           ");
		System.out.println("═════════════════════════════════════════════════");
		try{Thread.sleep(500);}catch(InterruptedException e){System.out.println(e);}
		
		clrscr();	
		System.out.println("      ┕━━━━━┙");
		System.out.println("        ▚  ▚ ▚");
		System.out.println("           ");
		System.out.println("           ");
		System.out.println("                   WIN     ");
		System.out.println("           ");
		System.out.println("           ");
		System.out.println("           ");
		System.out.println("           ");
		System.out.println("    WIN       ");
		System.out.println("           ");
		System.out.println("           ");
		System.out.println("═════════════════════════════════════════════════");
		try{Thread.sleep(500);}catch(InterruptedException e){System.out.println(e);}
		
		clrscr();	
		System.out.println("       ▞ ▚▞ ▚                    WIN");
		System.out.println("           ");
		System.out.println("           ");
		System.out.println("           ");
		System.out.println("     WIN      ");
		System.out.println("           ");
		System.out.println("           ");
		System.out.println("           ");
		System.out.println("           ");
		System.out.println("                           WIN     ");
		System.out.println("           ");
		System.out.println("           ");
		System.out.println("═════════════════════════════════════════════════");
		try{Thread.sleep(500);}catch(InterruptedException e){System.out.println(e);}
		
				clrscr();	

		System.out.println("           ");
		System.out.println("    WIN       ");
		System.out.println("                          WIN   ");
		System.out.println("           ");
		System.out.println("           ");
		System.out.println("                    WIN     ");
		System.out.println("           ");
		System.out.println("           ");
		System.out.println("    WIN       ");
		System.out.println("           ");
		System.out.println("           ");
		System.out.println("                                  WIN   ");
		System.out.println("═════════════════════════════════════════════════");
		try{Thread.sleep(500);}catch(InterruptedException e){System.out.println(e);}
	}
	
	static void GAMEOVER()
	{
		clrscr();
			
		int i = 0;
		int wait = 400; 
		while ( i < 1 )
		{
			i++;
			
			System.out.println("┣━━━━>                  ┏━━━━━━━━━━━━┓    <━━━━━┫");
			System.out.println("┣━━━━━>    ╔═══════╗    ┃   help!    ┃     <━━━━┫");
			System.out.println("┣━━━━>     ║ O  o  ║    ┃   me!!     ┃    <━━━━━┫");
			System.out.println("┣━━━━━>    ║       ║    ┗━━━━━━━━━━━━┛     <━━━━┫");
			System.out.println("┣━━━━>     ║━━━━   ║                      <━━━━━┫");
			System.out.println("┣━━━━━>    ╚═══╦═══╝                       <━━━━┫");
			System.out.println("┣━━━━>     ╔═══╩═══╗                      <━━━━━┫");
			System.out.println("┣━━━━━> ║  ║       ║  ║                    <━━━━┫");
			System.out.println("┣━━━━> ═╬══╣       ╠══╬═                  <━━━━━┫");
			System.out.println("┣━━━━━> ║  ║       ║  ║                    <━━━━┫");
			System.out.println("┣━━━━>     ╚═══╦═══╝                      <━━━━━┫");
			System.out.println("┣━━━━━>     ╔══╩══╗                        <━━━━┫");
			System.out.println("┣━━━━>      ║     ║                       <━━━━━┫");
			System.out.println("┣━━━━━>   ══╝     ╚══                      <━━━━┫");
			System.out.println("═════════════════════════════════════════════════");
			
		
			try{Thread.sleep(wait);}catch(InterruptedException e){System.out.println(e);}
			clrscr();
			
			
			System.out.println("┣━━━━━>                 ┏━━━━━━━━━━━━┓   <━━━━┫");
			System.out.println("┣━━━━>     ╔═══════╗    ┃   help!    ┃  <━━━━━┫");
			System.out.println("┣━━━━━>    ║ o  O  ║    ┃   me!!     ┃   <━━━━┫");
			System.out.println("┣━━━━>     ║       ║    ┗━━━━━━━━━━━━┛  <━━━━━┫");
			System.out.println("┣━━━━━>    ║━━━━   ║                     <━━━━┫");
			System.out.println("┣━━━━>     ╚═══╦═══╝                    <━━━━━┫");
			System.out.println("┣━━━━━>    ╔═══╩═══╗                     <━━━━┫");
			System.out.println("┣━━━━>  ║  ║       ║  ║                 <━━━━━┫");
			System.out.println("┣━━━━━ ═╬══╣       ╠══╬═                 <━━━━┫");
			System.out.println("┣━━━━>  ║  ║       ║  ║                 <━━━━━┫");
			System.out.println("┣━━━━━>    ╚═══╦═══╝                     <━━━━┫");
			System.out.println("┣━━━━>      ╔══╩══╗                     <━━━━━┫");
			System.out.println("┣━━━━━>     ║     ║                      <━━━━┫");
			System.out.println("┣━━━━>    ══╝     ╚══                   <━━━━━┫");
			System.out.println("═════════════════════════════════════════════════");
			
			try{Thread.sleep(wait);}catch(InterruptedException e){System.out.println(e);}
			
			clrscr();
			
			
			System.out.println("┣━━━━>                               <━━━━━┫");
			System.out.println("┣━━━━━>    ╔═══════╗                  <━━━━┫");
			System.out.println("┣━━━━>     ║ O  o  ║                 <━━━━━┫");
			System.out.println("┣━━━━━>    ║       ║                  <━━━━┫");
			System.out.println("┣━━━━>     ║━━━━   ║                 <━━━━━┫");
			System.out.println("┣━━━━━>    ╚═══╦═══╝                  <━━━━┫");
			System.out.println("┣━━━━>     ╔═══╩═══╗                 <━━━━━┫");
			System.out.println("┣━━━━━> ║  ║       ║  ║               <━━━━┫");
			System.out.println("┣━━━━> ═╬══╣       ╠══╬═             <━━━━━┫");
			System.out.println("┣━━━━━> ║  ║       ║  ║               <━━━━┫");
			System.out.println("┣━━━━>     ╚═══╦═══╝                 <━━━━━┫");
			System.out.println("┣━━━━━>     ╔══╩══╗                   <━━━━┫");
			System.out.println("┣━━━━>      ║     ║                  <━━━━━┫");
			System.out.println("┣━━━━━>   ══╝     ╚══                 <━━━━┫");
			System.out.println("═════════════════════════════════════════════════");
			
			try{Thread.sleep(wait);}catch(InterruptedException e){System.out.println(e);}
			
			clrscr(); 
			
			System.out.println("┣━━━━━>                            <━━━━┫");
			System.out.println("┣━━━━>     ╔═══════╗              <━━━━━┫");
			System.out.println("┣━━━━━>    ║ o  O  ║               <━━━━┫");
			System.out.println("┣━━━━>     ║       ║              <━━━━━┫");
			System.out.println("┣━━━━━>    ║━━━━   ║               <━━━━┫");
			System.out.println("┣━━━━>     ╚═══╦═══╝              <━━━━━┫");
			System.out.println("┣━━━━━>    ╔═══╩═══╗               <━━━━┫");
			System.out.println("┣━━━━>  ║  ║       ║  ║           <━━━━━┫");
			System.out.println("┣━━━━━>═╬══╣       ╠══╬═           <━━━━┫");
			System.out.println("┣━━━━>  ║  ║       ║  ║           <━━━━━┫");
			System.out.println("┣━━━━━>    ╚═══╦═══╝               <━━━━┫");
			System.out.println("┣━━━━>      ╔══╩══╗               <━━━━━┫");
			System.out.println("┣━━━━━>     ║     ║                <━━━━┫");
			System.out.println("┣━━━━>    ══╝     ╚══             <━━━━━┫");
			System.out.println("═════════════════════════════════════════════════");
			
			try{Thread.sleep(wait);}catch(InterruptedException e){System.out.println(e);}
			
			clrscr();
			
			
			System.out.println("┣━━━━>                         <━━━━━┫");
			System.out.println("┣━━━━━>    ╔═══════╗            <━━━━┫");
			System.out.println("┣━━━━>     ║ O  o  ║           <━━━━━┫");
			System.out.println("┣━━━━━>    ║       ║            <━━━━┫");
			System.out.println("┣━━━━>     ║━━━━   ║           <━━━━━┫");
			System.out.println("┣━━━━━>    ╚═══╦═══╝            <━━━━┫");
			System.out.println("┣━━━━>     ╔═══╩═══╗           <━━━━━┫");
			System.out.println("┣━━━━━> ║  ║       ║  ║         <━━━━┫");
			System.out.println("┣━━━━> ═╬══╣       ╠══╬═       <━━━━━┫");
			System.out.println("┣━━━━━> ║  ║       ║  ║         <━━━━┫");
			System.out.println("┣━━━━>     ╚═══╦═══╝           <━━━━━┫");
			System.out.println("┣━━━━━>     ╔══╩══╗             <━━━━┫");
			System.out.println("┣━━━━>      ║     ║            <━━━━━┫");
			System.out.println("┣━━━━━>   ══╝     ╚══           <━━━━┫");
			System.out.println("═════════════════════════════════════════════════");
			
			try{Thread.sleep(wait);}catch(InterruptedException e){System.out.println(e);}
			
			clrscr();
			
			System.out.println("┣━━━━━>                      <━━━━┫");
			System.out.println("┣━━━━>     ╔═══════╗        <━━━━━┫");
			System.out.println("┣━━━━━>    ║ o  O  ║         <━━━━┫");
			System.out.println("┣━━━━>     ║       ║        <━━━━━┫");
			System.out.println("┣━━━━━>    ║━━━━   ║         <━━━━┫");
			System.out.println("┣━━━━>     ╚═══╦═══╝        <━━━━━┫");
			System.out.println("┣━━━━━>    ╔═══╩═══╗         <━━━━┫");
			System.out.println("┣━━━━>  ║  ║       ║  ║     <━━━━━┫");
			System.out.println("┣━━━━━>═╬══╣       ╠══╬═     <━━━━┫");
			System.out.println("┣━━━━>  ║  ║       ║  ║     <━━━━━┫");
			System.out.println("┣━━━━━>    ╚═══╦═══╝         <━━━━┫");
			System.out.println("┣━━━━>      ╔══╩══╗         <━━━━━┫");
			System.out.println("┣━━━━━>     ║     ║          <━━━━┫");
			System.out.println("┣━━━━>    ══╝     ╚══       <━━━━━┫");
			System.out.println("═════════════════════════════════════════════════");			
			try{Thread.sleep(wait);}catch(InterruptedException e){System.out.println(e);}
			
			clrscr();
			
			System.out.println("┣━━━━>                   <━━━━━┫");
			System.out.println("┣━━━━━>    ╔═══════╗      <━━━━┫");
			System.out.println("┣━━━━>     ║ O  o  ║     <━━━━━┫");
			System.out.println("┣━━━━━>    ║       ║      <━━━━┫");
			System.out.println("┣━━━━>     ║━━━━   ║     <━━━━━┫");
			System.out.println("┣━━━━━>    ╚═══╦═══╝      <━━━━┫");
			System.out.println("┣━━━━>     ╔═══╩═══╗     <━━━━━┫");
			System.out.println("┣━━━━━> ║  ║       ║  ║   <━━━━┫");
			System.out.println("┣━━━━> ═╬══╣       ╠══╬═ <━━━━━┫");
			System.out.println("┣━━━━━> ║  ║       ║  ║   <━━━━┫");
			System.out.println("┣━━━━>     ╚═══╦═══╝     <━━━━━┫");
			System.out.println("┣━━━━━>     ╔══╩══╗       <━━━━┫");
			System.out.println("┣━━━━>      ║     ║      <━━━━━┫");
			System.out.println("┣━━━━━>   ══╝     ╚══     <━━━━┫");
			System.out.println("═════════════════════════════════════════════════");			
			try{Thread.sleep(wait);}catch(InterruptedException e){System.out.println(e);}
			
			clrscr();
			
			System.out.println(" ");
			System.out.println("           ╔══╗ ╔══╗ ╔═╦═╗╔══     ");
			System.out.println("           ║    ║  ║ ║ ║ ║║    ");
			System.out.println("           ║ ═╗ ╠══╣ ║ ║ ║╠══ ");
			System.out.println("           ╚══╝ ║  ║ ║   ║╚══  ");
			System.out.println("           ╔══╗      ╔══ ╔══╗   ");
			System.out.println("           ║  ║ ║  ║ ║   ║  ║   ");
			System.out.println("           ║  ║ ║  ║ ╠══ ╠══╝   "       );
			System.out.println("           ╚══╝  ╲╱  ╚══ ║ ╲  ");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("═════════════════════════════════════════════════");			
			try{Thread.sleep(1500);}catch(InterruptedException e){System.out.println(e);}
			
			clrscr();
		}
	}
}

//------------------------------------------------------------------------------------------------------------------------

public class ActionFigur
{
	private int characterID;
	private String characterName;
    private String characterSpecies;
	private int characterAttack;
	private int characterDefense;
	private int characterLife;
	private int characterMana;

	

	
    public ActionFigur(int characterID, String characterName, String characterSpecies, int characterAttack, int characterDefense, int characterLife, int characterMana)
    {
		this.characterID = characterID;
		this.characterName = characterName;
        this.characterSpecies = characterSpecies;
        this.characterAttack = characterAttack;
        this.characterDefense = characterDefense;
        this.characterLife = characterLife;
        this.characterMana = characterMana;
 
    }
    
    public int getCharacterID()
    {
        return characterID;
    }
    
       public String getCharacterName()
    {
        return characterName;
    }
    
	public String getCharacterSpecies()
    {
        return characterSpecies;
    }
    
    public int getCharacterAttack()
    {
        return characterAttack;
    }
    
    public int getCharacterDefense()
    {
        return characterDefense;
    } 
    
    public int getCharacterLife()
    {
        return characterLife;
    }   
    
    public int getCharacterMana()
    {
        return characterMana;
    }
    
}



class Level 
{  
	private int levelID;
	private String levelName;
    private String world;

    public Level(int levelID, String levelName, String World)
    {
		this.levelID = levelID;
		this.levelName = levelName;
		this.world = world;
    }
    
    
    public int getlevelID()
    {
        return levelID;
    }
    
    public String getLevelName()
    {
        return levelName;
    }
    
	public String getWorld()
    {
        return world;
    }
}

class Weapon 
{  
	private int weaponID;
	private String weaponName;
    private int weaponAttack;
    private int weaponNegativ;

    public Weapon(int weaponID, String weaponName, int weaponAttack, int weaponNegativ)
    {
		this.weaponID = weaponID;
		this.weaponName = weaponName;
		this.weaponAttack = weaponAttack;
		this.weaponNegativ = weaponNegativ;
    }
    
    
    public int getWeaponID()
    {
        return weaponID;
    }
    
    public String getWeaponName()
    {
        return weaponName;
    }
    
	public int getWeaponAttack()
    {
        return weaponAttack;
    }
    
    public int getWeaponNegativ()
    {
        return weaponNegativ;
    }
}

class Armor 
{  
	private int armorID;
	private String armorName;
    private int armorDefense;
    private int armorNegativ;

    public Armor(int armorID, String armorName, int armorDefense, int armorNegativ)
    {
		this.armorID = armorID;
		this.armorName = armorName;
		this.armorDefense = armorDefense;
		this.armorNegativ = armorNegativ;
    }
    
    
    public int getArmorID()
    {
        return armorID;
    }
    
    public String getArmorName()
    {
        return armorName;
    }
    
	public int getArmorDefense()
    {
        return armorDefense;
    }
    
    public int getArmorNegativ()
    {
        return armorNegativ;
    }
}

class Weather 
{  
	private int weatherID;
	private String weatherName;
    private String weatherNegativ;

    public Weather(int weatherID, String weatherName, String weatherNegativ)
    {
		this.weatherID = weatherID;
		this.weatherName = weatherName;
		this.weatherNegativ = weatherNegativ;
    }
    
    
    public int getWeatherID()
    {
        return weatherID;
    }
    
    public String getWeatherName()
    {
        return weatherName;
    }
    
    
    public String getWeatherNegativ()
    {
        return weatherNegativ;
    }
}

class Magic 
{  
	private int magicID;
	private String magicName;
    private int magicCost;
    private int magicDamage;

    public Magic(int magicID, String magicName, int magicCost, int magicDamage)
    {
		this.magicID = magicID;
		this.magicName = magicName;
		this.magicCost = magicCost;
		this.magicDamage = magicDamage;
    }
    
    
    public int getMagicID()
    {
        return magicID;
    }
    
    public String getMagicName()
    {
        return magicName;
    }
    
    
    public int getMagicCost()
    {
        return magicCost;
    }
    
     public int getMagicDamage()
    {
        return magicDamage;
    }
}
