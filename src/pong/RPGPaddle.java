package pong;

import java.awt.event.KeyEvent;

public class RPGPaddle extends Paddle{
	String role = "";
	private int hp, hBonus, attack;
	private double speed;
	private int abilityRole;
	private int selectedAbility;
	private int exp, healthStat, speedStat, attackStat, lengthStat, maxHP;
	public RPGPaddle(int startX, int startY, int w, int h, int maxX, int maxY, boolean AI, String role) {
		super(startX, startY, w, h, maxX, maxY, AI);
		this.role = role;
		this.exp = 0;
		this.healthStat = 0;
		this.speedStat = 0;
		this.attackStat = 0;
		this.lengthStat = 0;
		this.maxHP = 0;
		if(this.role == "Knight") {
			this.hp = 5;
			this.speed = 1;
			this.abilityRole = 1;
			this.maxHP = this.hp;
		}
		
		else if(this.role == "Wizard") { 
			this.hp = 3;
			this.speed = 1;
			this.abilityRole = 2;
			this.maxHP = this.hp;
		}
		
		else if(this.role == "Rogue") {
			this.hp = 3;
			this.speed = 3;
			this.abilityRole = 3;
			this.maxHP = this.hp;
		}
		//Archer
		else {
			this.hp = 4;
			this.speed = 2;
			this.abilityRole = 4;
			this.maxHP = this.hp;
		}
	}
	
	public void increaseStat(boolean[] KeysPressed) {
		if(KeysPressed[KeyEvent.VK_1]) {
			this.healthStat += 1;
		}
		else if(KeysPressed[KeyEvent.VK_2]) {
			this.attackStat += 1;
		}
		else if(KeysPressed[KeyEvent.VK_2]) {
			this.speedStat += 1;
		}
		else if(KeysPressed[KeyEvent.VK_3]) {
			this.lengthStat += 1;
		}
		else if(KeysPressed[KeyEvent.VK_4]) {
			this.hp = this.maxHP;
		}
	}
}
