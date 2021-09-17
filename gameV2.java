import java.util.Scanner;
import java.util.Random;
import java.io.*;

public class Main
{
	public static Scanner scanner = new Scanner(System.in);
	
	public static ActionFigur mychoice;
	public static ActionFigur myenemy;
	public static ActionFigur enemy;
	
	public static void main(String[] args)
    {
		clrscr();
		ActionFigur[] character = new ActionFigur[17];
		
        character[0] 	= new ActionFigur(1, "helmut", "soldiera", 10, 3, 0);
        character[1]	= new ActionFigur(2, "sergio", "heilerin", 4, 5, 0);
        character[2] 	= new ActionFigur(3, "soeren", "gumbaaaa", 2, 4, 0);
		character[3] 	= new ActionFigur(4, "mikeee", "titanaaa", 9, 12, 0); 
		character[4] 	= new ActionFigur(5, "oliverA", "vikinger", 7, 9, 0);
		character[5] 	= new ActionFigur(6, "andree", "vikinger", 7, 4, 0);
		character[6] 	= new ActionFigur(7, "oliverB", "pirataaa", 4, 6, 0);
		character[7] 	= new ActionFigur(8, "emreeee", "pirataaa", 4, 6, 0);
		character[8] 	= new ActionFigur(9, "sascha", "pirataaa", 4, 6, 0);
		character[9] 	= new ActionFigur(10, "stefee", "pirataaa", 4, 6, 0);
		character[10] 	= new ActionFigur(11, "stefee", "pirataaa", 4, 6, 0);
		character[11] 	= new ActionFigur(12, "Florian", "pirataaa", 4, 6, 0);
		character[12] 	= new ActionFigur(13, "Salman", "pirataaa", 4, 6, 0);
		character[13] 	= new ActionFigur(14, "Ashanti", "pirataaa", 4, 6, 0); 
		character[14] 	= new ActionFigur(15, "jannnnA", "pirataaa", 4, 6, 0);
		character[15] 	= new ActionFigur(16, "jannnnB", "pirataaa", 4, 6, 0);
		character[16] 	= new ActionFigur(17, "christian", "pirataaa", 4, 6, 0); 
		
		Level[] stage 	= new Level[17];
		
		stage[0] = new Level(1, "forrest", "wood");
		stage[1] = new Level(2, "venus", "space");
		stage[2] = new Level(3, "island", "water");
		
		System.out.print("choose your character:\n");
		ActionFigur player = getCharacter(character);
		
		ActionFigur enemy = getEnemy(character);
		
		
		
		Weapon[] weapon = new Weapon[4];
		
		weapon[0] = new Weapon(1, "axe", 3);
		weapon[1] = new Weapon(2, "sword", 6);
		weapon[2] = new Weapon(3, "plasmagun", 14);
		weapon[3] = new Weapon(4, "handgun", 8);
		
		Weapon playerWeapon = getWeapon(weapon);
		
		
		clrscr();
		
		System.out.print("your charakter is: " + player.getCharacterName() + "\n");
		System.out.print("your weapon is: " + playerWeapon.getWeaponName() + "\n");
		
		
		System.out.print("your enemy is: " + enemy.getCharacterName() + "\n");
		
		System.out.print(player.getCharacterName() + " vs " + enemy.getCharacterName() + "\n\n");
		
		System.out.println("========================================");
		System.out.println("=                 FIGHT                =");
		System.out.println("========================================");
		System.out.print("your winner is: " + fight(player, enemy).getCharacterName() + "\n");
		
		
    } 
    
    public static ActionFigur fight(ActionFigur player, ActionFigur enemy)
    {
		int round = 0;
	
		ActionFigur winner;
		
		winner = (player.getCharacterAttack() > enemy.getCharacterDefense()) ? player : enemy;
		
		return winner;
	}
    
    public static ActionFigur getEnemy(ActionFigur[] character)
    {
		Random rand = new Random();
		int enemyNumber = rand.nextInt(16);
		
		myenemy = character[enemyNumber];
	
		return myenemy;
    } 
    
    
    public static ActionFigur getCharacter(ActionFigur[] character)
    {
		int i = 0;
		System.out.println("ID\tname\tspecies\tattack\tdefense");
        System.out.println("===========================================");
		while (i < character.length)
		{
			System.out.println(character[i].getCharacterID() +" "+ character[i].getCharacterName() +"\t"+ character[i].getCharacterSpecies() +"\t"+ character[i].getCharacterAttack() +"\t"+ character[i].getCharacterDefense());
			i++;
		}
		System.out.println("===========================================");
		
		int chooseCharacter = scanner.nextInt();
		
		ActionFigur player = character[chooseCharacter-1];
		
		return player;
	}
	
	
	  public static Weapon getWeapon(Weapon[] weapon)
    {
		int j = 0;
		System.out.println("ID\tname\tspecies\tattack\tdefense");
        System.out.println("===========================================");
		while (j < weapon.length)
		{
			System.out.println(weapon[j].getWeaponID() +" "+ weapon[j].getWeaponName() +"\t"+ weapon[j].getWeaponAttack());
			j++;
		}
		System.out.println("===========================================");
		
		int chooseWeapon = scanner.nextInt();
		
		Weapon playerWeapon = weapon[chooseWeapon-1];
		
		return playerWeapon;
	}
	
	
	 public static void clrscr()
    {
		System.out.print("\033[H\033[2J"); 
		System.out.flush();  
	}
	
	static void kbhit()
       {
		   	System.out.println("\npress any button to play again!!!");
			try 
			{
				System.in.read();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
	   }
}



public class ActionFigur
{
	private int characterID;
	private String characterName;
    private String characterSpecies;
	private int characterAttack;
	private int characterDefense;
	private int win;
	

	
    public ActionFigur(int characterID, String characterName, String characterSpecies, int characterAttack, int characterDefense, int win)
    {
		this.characterID = characterID;
		this.characterName = characterName;
        this.characterSpecies = characterSpecies;
        this.characterAttack = characterAttack;
        this.characterDefense = characterDefense;
        this.win = win;
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
    
    public String getlevelName()
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

    public Weapon(int weaponID, String weaponName, int weaponAttack)
    {
		this.weaponID = weaponID;
		this.weaponName = weaponName;
		this.weaponAttack = weaponAttack;
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
}
