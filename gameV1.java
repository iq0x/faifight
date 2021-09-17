
import java.util.Scanner;
import java.util.Random;
import java.io.*;

public class ActionFigur
{
	private int ID;
	private String name;
    private String species;
	private int attack;
	private int defense;
	private int win;
	
	public static Scanner scanner = new Scanner(System.in);
	public static ActionFigur mychoice;
	public static ActionFigur myenemy;
	public static ActionFigur enemy;
	
    public ActionFigur(int ID, String name, String species, int attack, int defense, int win)
    {
		this.ID = ID;
		this.name = name;
        this.species = species;
        this.attack = attack;
        this.defense = defense;
        this.win = win;
    }
    
    public static ActionFigur getCharacter(ActionFigur fighter, ActionFigur heilerin, ActionFigur boss, ActionFigur support)
    {
        
        System.out.println("ID\tname\tspecies\tattack\tdefense");
        System.out.println("========================================");
        System.out.println(fighter.ID +"\t"+ fighter.name +"\t"+ fighter.species +"\t"+ fighter.attack +"\t"+ fighter.defense);
        System.out.println(heilerin.ID +"\t"+ heilerin.name +"\t"+ heilerin.species +"\t"+ heilerin.attack +"\t"+ heilerin.defense);
        System.out.println(boss.ID +"\t"+ boss.name +"\t"+ boss.species +"\t"+ boss.attack +"\t"+ boss.defense);
        System.out.println(support.ID +"\t"+ support.name +"\t"+ support.species +"\t"+ support.attack +"\t"+ support.defense);
        System.out.println("========================================");
		int chooseCharacter = scanner.nextInt();
		
		if (chooseCharacter == 1) mychoice = fighter;
		else if (chooseCharacter == 2) mychoice = heilerin;
		else if (chooseCharacter == 3) mychoice = boss;
		else if (chooseCharacter == 4) mychoice = support;
		else mychoice = fighter;
		
		return mychoice;

    } 
    
    
    public static ActionFigur getEnemy(ActionFigur fighter, ActionFigur heilerin, ActionFigur boss, ActionFigur support)
    {
        
		Random rand = new Random();
		int enemyNumber = rand.nextInt(3);
		
		if (enemyNumber == 0) myenemy = fighter;
		else if (enemyNumber == 1) myenemy = heilerin;
		else if (enemyNumber == 2) myenemy = boss;
		else if (enemyNumber == 3) myenemy = support;
		else myenemy = fighter;
	
		return myenemy;

    } 
    
    public static String fight(ActionFigur player, ActionFigur enemy)
    {
		int round = 0;
	
		ActionFigur winner;
		
		winner = (player.attack > enemy.attack) ? player : enemy;
		
		return winner.name;
	}

//----------------------------- main ------------------------------------

	public static void main(String[] args)
    {
        ActionFigur fighter 	= new ActionFigur(1, "helmut", "soldier", 10, 3, 0);
        ActionFigur heilerin 	= new ActionFigur(2, "sergio", "troll", 4, 5, 0);
        ActionFigur support 	= new ActionFigur(3, "soeren", "gumba", 2, 4, 0);
		ActionFigur boss 		= new ActionFigur(4, "mike", "titan", 9, 12, 0); 
       
		while (true)
		{
			clrscr();
			
			System.out.println("Choose your character: \n");
		
			ActionFigur player = getCharacter(fighter, heilerin, boss, support);
			ActionFigur enemy = getEnemy(fighter, heilerin, boss, support);
			
			System.out.print("\nYou choose: " + player.name +"\n\n");
		
			System.out.print(player.name + " vs " + enemy.name + "\n\n");
			
			System.out.println("========================================");
			System.out.println("=                 FIGHT                =");
			System.out.println("========================================");
			System.out.println("The Winner is: " + fight(player, enemy));
			kbhit();
		}
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
