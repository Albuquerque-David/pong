package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball 
{
	
	public boolean right = false;
	public boolean left = false;
	public double x,y;
	public int width, height;
	
	public double dx, dy;
	public double speed = 1.5;
	
	public Ball(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.width = 4;
		this.height = 4;
		
		int angle = new Random().nextInt(75) + 45;
		
		dx = Math.cos(Math.toRadians(angle));
		dy = Math.sin(Math.toRadians(angle));
	}
	
	public void tick()
	{
		//Paredes
		if(x+(dx*speed) + width >= Game.WIDTH)
		{
			dx*=-1;
		}
		else if(x+(dx*speed) < 0)
		{
			dx*=-1;
		}
		
		if(y >= Game.HEIGHT)
		{
			//PONTO INIMIGO
			System.out.println("Ponto do inimigo!");
			new Game();
		}
		else if(y <= 0)
		{
			//PONTO DO JOGADOR
			System.out.println("Ponto do jogador!");
			new Game();
		}
		
		Rectangle bounds = new Rectangle( (int)(x+(dx *speed)), (int)(y+(dy*speed)), width, height);
		
		Rectangle boundsPlayer = new Rectangle(Game.player.x, Game.player.y, Game.player.width, Game.player.height);
		Rectangle boundsEnemy = new Rectangle((int)Game.enemy.x, (int)Game.enemy.y, Game.enemy.width, Game.enemy.height);
		
		if(bounds.intersects(boundsPlayer))
		{
			int angle = new Random().nextInt(75) + 45;
			dx = Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
			if(dy > 0)
				dy*=-1;
		}
		else if(bounds.intersects(boundsEnemy))
		{
			int angle = new Random().nextInt(75) + 45;
			dx = Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
			if(dy < 0)
				dy*=-1;
		}
		
		x+=dx*speed;
		y+=dy*speed;
	}
	
	public void render(Graphics g)
	{
		g.setColor(Color.GREEN);
		g.fillRect((int) x, (int) y, width, height);
	}
}
