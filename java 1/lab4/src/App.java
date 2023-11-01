import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

class lab04 
{
  public static void main(String[] args) 
  {
    Game game = new Game();
    game.dice = new Dice();
    game.players = new ArrayList < Player > 
    (
      Arrays.asList
      (
        new Player("Anakin Skywalker"),
        new Player("Obi-Wan Kenobi")
      )
    );

    game.run();
  }
}


class Dice 
{
    Scanner myObj = new Scanner(System.in);
    private int side;
    Dice()
    {
        while(side>20 || side<1)
        {
        System.out.println("ilosc scian kostki: ");
        side=myObj.nextInt();
        }
    }
  public int roll() 
  {
    return new Random().nextInt(side) + 1;
  }
}

class Player 
{
  protected String name;
  public int health=50;

  public Player(String name) 
  {
    this.name = name;
  }
}

class Game 
{
    Scanner myObj = new Scanner(System.in);
  public HashMap < Integer, Integer > fields = new HashMap < Integer, Integer > ();
  public ArrayList < Player > players = new ArrayList < Player > ();
  private int distance;
  protected Dice dice;


  public void run() 
  {
    int index1 = 0;
    for (Player player: this.players) 
    {
      this.fields.put(index1, 0);
      index1++;
    }

    System.out.println("czy chcesz zmienic ilosc pol(1/0): ");
    int zmiana=myObj.nextInt();
    if(zmiana==0)
    distance=100;
    else
    {
    System.out.println("podaj ilosc pol: ");    
    distance=myObj.nextInt();
    }
    Player end = null;
    
    

    while (end == null)  
    {
      int index = 0;
      for (Player player: this.players) 
      {
        int result = this.dice.roll();
        int position = this.fields.get(index) + result;
        this.fields.put(index, position);

        if (position >= distance) 
        {
          position = distance;
        }

        System.out.println(player.name + " rolled " + result + ". Now is on position " + position);

        index++;


        if (position%7 == 0) 
        {
          int dmg = this.dice.roll();
          player.health-=dmg;
          System.out.println(player.name + " rolled " + dmg + ". Now has " + player.health + " health");
          
        }

        if (player.health <= 0) 
        {
          System.out.println(player.name + " lost!");
          end = player;
          break;
        }
        
        if (position >= distance) 
        {
          System.out.println(player.name + " won!");
          end = player;
          break;
        }

        
      }
    }
  }
}

