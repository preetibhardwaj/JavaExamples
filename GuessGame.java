public class GuessGame{

Player p1=new Player();
Player p2=new Player();
Player p3=new Player();

public void startGame(){
int target=(int) (Math.random()*10);
System.out.println(target);
int p1_guess=p1.guess();
System.out.println(p1_guess);
int p2_guess=p2.guess();
int p3_guess=p3.guess();

if(target==p1_guess){
System.out.println("P1 wins");
}
}

}