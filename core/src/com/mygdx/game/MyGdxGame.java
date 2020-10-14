//MyGdxGame.java
//Nafis Molla & Emerson Wells
//this is our version of the classic game Tron light cycles our game has a two player and a single player mode that has a built in AI
//and also a simple sleek designed menu screen

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
package com.mygdx.game;

import java.awt.*;
import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;


public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	BitmapFont font;
	ShapeRenderer sr;
	ShapeRenderer shapes;
	Texture tronLogo, instructionsP, background, other, yellowL, yellowR, yellowU, yellowD, greenR, greenL, greenU, greenD,greenwon,yellowwon,greyback;
	//////////////////////////////////////////////////////////////////////////
	//all final constants
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	public static final int UP = 3;
	public static final int DOWN = 4;
	public static final int CENTER = 5;
	public boolean menu = true;
	public boolean oneplayer = false;
	public boolean twoplayer = false;
	public boolean instructions = false;
	int keysBox = UP;
	public static final int MULTIPLAYER = 101;
	public static final int SINGLE = 102;
	//////////////////////////////////////////////////////////////////////
	public ArrayList<Integer> trailY = new ArrayList<Integer>();//yellow bike trail points
	public ArrayList<Integer> trailG = new ArrayList<Integer>();//green bike trail points
	/////////////////////////////////////////////////////////////////////////
	Bike yellow = new Bike(900, 300, LEFT,900,300); //bike objects
	Bike green = new Bike(150, 300, RIGHT,0,363);
	/////////////////////////////////////////////////////////////////////
	@Override
	public void create () {
		font = new BitmapFont();
		font.getData().setScale(2f);
		Gdx.graphics.setWindowedMode(1024,768);
		batch = new SpriteBatch();
		sr = new ShapeRenderer();
		shapes = new ShapeRenderer();
		///////////////////////////////////////////////////////
		//loading all textures
		yellowL = new Texture("yellowbikeL.png");
		yellowR = new Texture("yellowbikeR.png");
		yellowU = new Texture("yellowbikeU.png");
		yellowD = new Texture("yellowbikeD.png");
		greenL = new Texture("greenbikeL.png");
		greenR = new Texture("greenbikeR.png");
		greenU = new Texture("greenbikeU.png");
		greenD = new Texture("greenbikeD.png");
		tronLogo = new Texture("tronlogo.png");
		instructionsP = new Texture("Instructions.png");
		background = new Texture("background.jpg");
		other = new Texture("betterlogo.png");
		greenwon = new Texture("greenwon.png");
		yellowwon = new Texture("yellowwon.png");
		greyback = new Texture("grey.jpg");

	}

	public void add(String col){ //adds bike points into corresponding arraylists
		if(col == "YELLOW"){
			trailY.add(yellow.getpinX());
			trailY.add(yellow.getpinY());
			trailY.add(yellow.getXb());
			trailY.add(yellow.getYb());

		}
		if(col == "GREEN"){
			trailG.add(green.getpinX());
			trailG.add(green.getpinY());
			trailG.add(green.getXb());
			trailG.add(green.getYb());
		}
	}

	public void keys(boolean flag){ //getting key inputs

		if(flag) {
			if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
				if (yellow.getDir() != DOWN) {
					yellow.changeDir(UP); //changing bike direction
				}
				add("YELLOW");//adds current bike cordinates
				yellow.pindrop();//drops pin for trail
			}
			if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
				if (yellow.getDir() != RIGHT) {
					yellow.changeDir(LEFT);
				}
				add("YELLOW");
				yellow.pindrop();
			}
			if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
				if (yellow.getDir() != UP) {
					yellow.changeDir(DOWN);
				}
				add("YELLOW");
				yellow.pindrop();
			}
			if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
				if (yellow.getDir() != LEFT) {
					yellow.changeDir(RIGHT);
				}
				add("YELLOW");
				yellow.pindrop();
			}
		}

		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			if(green.getDir() != DOWN){
				green.changeDir(UP);
			}
			add("GREEN");
			green.pindrop();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			if(green.getDir() != RIGHT){
				green.changeDir(LEFT);
			}
			add("GREEN");
			green.pindrop();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			if(green.getDir() != UP){
				green.changeDir(DOWN);
			}
			add("GREEN");
			green.pindrop();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			if(green.getDir() != LEFT){
				green.changeDir(RIGHT);
			}
			add("GREEN");
			green.pindrop();
		}
	}


	public void draw(){ //drawing bikes
		//drawing bikes with offsets so bike is centered with the trail

		//yellow
		if(yellow.getDir() == UP){
			batch.draw(yellowU, yellow.getBlitx()-17, yellow.getBlity()-66);
		}
		if(yellow.getDir() == DOWN){
			batch.draw(yellowD, yellow.getBlitx()-17, yellow.getBlity()+1);
		}
		if(yellow.getDir() == LEFT){
			batch.draw(yellowL, yellow.getBlitx()+1, yellow.getBlity()-17);
		}
		if(yellow.getDir() == RIGHT){
			batch.draw(yellowR, yellow.getBlitx()-66, yellow.getBlity()-17);
		}


		//green
		if(green.getDir() == UP){
			batch.draw(greenU, green.getXb()-17, green.getYb()-66);
		}
		if(green.getDir() == DOWN){
			batch.draw(greenD, green.getXb()-17, green.getYb()+1);
		}
		if(green.getDir() == LEFT){
			batch.draw(greenL, green.getXb()+1, green.getYb()-17);
		}
		if(green.getDir() == RIGHT){
			batch.draw(greenR, green.getXb()-66, green.getYb()-17);
		}
	}

	public void srDrawY(){//drawing yellow trail
		for(int i = 0; i<trailY.size();i+=4){ //points are in sets of four first pair are trail coordinates and second pair are bike coordinates
			sr.line(trailY.get(i), trailY.get(i+1), trailY.get(i+2), trailY.get(i+3));
		}
	}
	public void srDrawG(){//drawing green trail
		for(int i = 0; i<trailG.size();i+=4){
			sr.line(trailG.get(i), trailG.get(i+1), trailG.get(i+2), trailG.get(i+3));
		}
	}

	public boolean collisionY(){//cheeking yellow collision
		if(yellow.getXb() < 50 || yellow.getXb() > 970 || yellow.getYb() < 50 || yellow.getYb() > 670){
			return true;
		}
		//if bike crash into the other trail

		for(int i = 0; i < trailG.size(); i += 4){
			if(trailG.get(i+2) == yellow.getXb() && trailG.get(i+3) == yellow.getYb()) {
				return true;
			}
		}

		//if bike crash into their own trails,

		for(int i = 0; i < trailY.size(); i += 4){
			if(trailY.get(i+2) == yellow.getXb() && trailY.get(i+3) == yellow.getYb()){
				return true;
			}
		}
		return false;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////

	public boolean collisionG(){//cheeking green bike collision
		if(green.getXb() < 50 || green.getXb() > 970 || green.getYb() < 50 || green.getYb() > 670){
			return true;
		}
		//if they crash into the other trail
		for(int i = 0; i < trailY.size(); i += 4){ //yellow crashes into green
			if(trailY.get(i+2) == green.getXb() && trailY.get(i+3) == green.getYb()){
				return true;
			}
		}
		//if bike crash into their own trail
		for(int i = 0; i < trailG.size(); i += 4){
			if(trailG.get(i+2) == green.getXb() && trailG.get(i+3) == green.getYb()){
				return true;
			}
		}
		return false;
	}
//////////////////////////////////////////////////////////////////////////////////////////////

	public int rand(int low,int high) { //random number generator
		return (int) (Math.random() * (high - low + 1) + (low)); //inclusive of high
	}

////////////////////////////////////////////////////////////////////////////////////////////
	public void grid(){//drawing grid
		shapes.begin(ShapeRenderer.ShapeType.Line);
		shapes.setColor(0.141f, 0.972f, 0.976f, 1);
		for(int k=0;k<940;k+=20){
			shapes.line(k+50, 630, k+50, 50);  //vertical lines
		}

		for(int n=0; n<600;n+=20){
			shapes.line(50, n+50, 970, n+50); //horizontal lines
		}
		shapes.end();
	}
	/////////////////////////////////////////////////////////////////////////
	public void gray(){//draws gray back ground
		batch.begin();
		batch.draw(greyback,0,0);
		batch.end();
	}
	/////////////////////////////////////////////////
	public void logo(){//draws tron logo
		batch.begin();
		batch.draw(other, 300, 620);
		batch.end();
	}

	//////////////////////////////////////////////////////
	public void lines(){ //draws trail lines
		sr.begin(ShapeType.Line);
		sr.setColor(Color.YELLOW);
		srDrawY();
		sr.setColor(Color.GREEN);
		srDrawG();
		sr.end();
	}
	///////////////////////////////////////////////////
	public void ending(){//draws ending screens
		if (!green.isAlive()) {
			batch.begin();
			batch.draw(yellowwon,0,0);
			batch.end();
		}
		if (!yellow.isAlive()) {
			batch.begin();
			batch.draw(greenwon,0,0);
			batch.end();
			yellow.died();
		}
	}


//////////////////////////////////////////////////////////////////////////////////////////////
	public void twoplayr(){//two player gamemode function

		if (yellow.isAlive() && green.isAlive()) {

			add("YELLOW");//adding yellow bikes points to trail arraylist
			add("GREEN");//adding green bikes points to trail arraylist
			Gdx.gl.glClearColor(0.439f, 0.501f, 0.564f, 0.5f);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			Gdx.gl.glLineWidth(5);//setting thickness of the line

			gray();
			keys(true);
			grid();//draws grid
			logo();//draws logo
			lines();//draws trails

			batch.begin();
			draw(); //drawing bikes
			batch.end();

			green.move();
			yellow.move();

			if(collisionY()){//cheeks collision
				yellow.died();
			}
			if(collisionG()){//cheeks collision
				green.died();
			}
		}

		ending();//displays end screens

	}


	public void singleplayer(){//single player mode


		if (yellow.isAlive() && green.isAlive()) {

			add("YELLOW");
			add("GREEN");
			Gdx.gl.glClearColor(0.439f, 0.501f, 0.564f, 0.5f);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			Gdx.gl.glLineWidth(5);//setting thickness of the line

			gray();
			keys(false);
			grid();
			logo();
			lines();

			batch.begin();
			draw();
			batch.end();

			green.move();
			yellow.AImove(trailG);//AI movement

			if(collisionY()){
				yellow.died();
			}
			if(collisionG()){
				green.died();
			}
		}
		ending();

	}


	public void tronLogo(){//function that draws the tron logo on top of the screen
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(background, 0, 0);//draws background
		batch.draw(tronLogo, 200, 500);//draws logo
		batch.end();
	}

	public void instructions(){//function that displays instructions
		batch.begin();
		batch.draw(instructionsP,0 ,0);//drawing instructions
		batch.end();
		sr.begin(ShapeType.Line);
		sr.setColor(Color.GREEN);
		sr.rect(150,80,200,60);//draws a back button to return to main menu
		sr.end();
		batch.begin();
		font.draw(batch, "Back", 190, 120);//drawing font on back button
		batch.end();
		if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){//checking if the back button is selected
			instructions = false;
			menu = true;
		}
	}

	public void menuBoxes(){//function to draw the buttons on the menu
		sr.begin(ShapeType.Line);
		if(keysBox == DOWN){//button for two players
			sr.setColor(Color.GREEN);
			sr.rect(400,400,200,60);
			batch.begin();
			font.draw(batch, "One Player", 420, 340);//drawing font on one player button
			batch.end();
			if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){//checking if the two player button is selected
				instructions = false;
				menu = false;
				twoplayer = true;//runs the two player game
			}
		}
		else{
			sr.setColor(Color.RED);//if not selected box turns red
			sr.rect(400,400,200,60);
			batch.begin();
			font.draw(batch, "One Player", 420, 340);
			batch.end();
		}
		if(keysBox == UP){//button for one player
			sr.setColor(Color.GREEN);
			sr.rect(400,300,200,60);
			batch.begin();
			font.draw(batch, "Two Player", 420, 440);
			batch.end();
			if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
				instructions = false;
				menu = false;
				oneplayer = true;//if selected run two player code
			}
		}
		else{
			sr.setColor(Color.RED);
			sr.rect(400, 300, 200, 60);
			batch.begin();
			font.draw(batch, "Two Player", 420, 440);
			batch.end();
		}
		if(keysBox == CENTER){//button for instructions
			sr.setColor(Color.GREEN);
			sr.rect(400,200,200,60);
			batch.begin();
			font.draw(batch, "Instructions", 420, 240);
			batch.end();
			if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
				instructions = true;//if selected display instructions page
				menu = false;
			}
		}
		else{
			sr.setColor(Color.RED);
			sr.rect(400, 200, 200, 60);
			batch.begin();
			font.draw(batch, "Instructions", 420, 240);
			batch.end();
		}
		sr.end();
	}
	public void keysMenu(){//checking the keys for the selected menu option
		if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && keysBox == DOWN ) {//checking what box should be highlighted
			keysBox = DOWN;//variable to keep track of the box tht is highlighted
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && keysBox == UP) {
			keysBox = DOWN;

		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && keysBox == CENTER) {
			keysBox = UP;
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && keysBox == UP) {
			keysBox = CENTER;
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && keysBox == DOWN) {
			keysBox = UP;
		}
	}

	@Override
	public void render() {
		if (menu == true){//when the menu is displayed
			tronLogo();
			keysMenu();
			menuBoxes();
		}
		else if(instructions == true){//when instructions displayed
			instructions();
		}
		else if(oneplayer == true){//if single player is selected
			singleplayer();//run single player game
		}
		else if(twoplayer == true){//if two player is selected
			twoplayr();//run two player game
		}
	}
	@Override
	public void dispose(){
		batch.dispose();
		yellowL.dispose();
		greenR.dispose();
	}
}
//////////////////////////////////////////////////////////
class Bike{//class to keep track of the bikes
	private int vx;//bike x and y coordinates
	private int vy;
	private int bdir;//direction that the bike is travelling
	int trailpinX;//the x and y coordinate of the pin
	int trailpinY;//the pin is what is dropped at the location that the bike changed direction
	private boolean alive = true;//bikes current health status
	private int blitx; //bike blit x
	private int blity;//bike blit y

	private Rectangle bound = new Rectangle(vx, vy, 66, 36);

	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	public static final int UP = 3;
	public static final int DOWN = 4;


	public Bike(int startX, int startY, int dir,int bx,int by) {//constructor for bike objects

		vx = startX;
		vy = startY;
		trailpinX = startX;
		trailpinY = startY;
		bdir = dir;
		blitx = bx;
		blity = by;

	}

	public void AImove(ArrayList<Integer> trail){

		boolean flag = true;
		int turn; // local turn variable

		if (bdir == LEFT){ //left

			for(int i = 0; i < trail.size(); i += 4){ //loop runs through every point in the user bike trail

				if((trail.get(i+2)+12 == vx && trail.get(i+3) == vy)|| vx<=12){ //cheeks trail collision and wall collision
					turn = rand(3,4); //random turn (generates ether up or down)
					bdir = turn;
					pindrop();//drops pin because bike turns
				}
			}
			if(flag) {
				blitx -= 3;
				vx -= 3;
			}
		}

		////////////////////////////////////////////////////////////////////////////////
		if (bdir == RIGHT){//right

			for(int i = 0; i < trail.size(); i += 4){

				if((trail.get(i+2)+72 == vx && trail.get(i+3) == vy) || vx>=899){//cheeking restrictions
					turn = rand(3,4);
					bdir = turn;
					pindrop();
				}
			}
			if(flag) {
				blitx += 3;
				vx += 3;
			}
		}

		/////////////////////////////////////////////////////////////////////
		if (bdir == UP){ //up

			for(int i = 0; i < trail.size(); i += 4){ //cheeking restrictions

				if((trail.get(i+2) == vx && trail.get(i+3)-8 == vy)||vy > 598){
					turn = rand(1,2);
					bdir = turn;
					pindrop();
				}
			}
			if(flag) {
				blity += 3;
				vy += 3;
			}
		}

		if (bdir == DOWN){//down

			for(int i = 0; i < trail.size(); i += 4){

				if((trail.get(i+2) == vx && trail.get(i+3)+6 == vy)|| vy<=57 ){ //cheeking restrictions
					turn = rand(1,2);
					bdir = turn;
					pindrop();
				}
			}

			if(flag) {
				blity -= 3;
				vy -= 3;
			}
		}

	}


	public int getDir(){//returns the direction of the bike
		return bdir;
	}

	public int getBlitx(){ //returns bike x
		return blitx;
	}

	public int getBlity() { //returns bike x
		return blity;
	}

	public int getXb(){ //returns bike x
		return vx;
	}
	public int getYb(){ //returns bike x
		return vy;
	}
	public void changeDir(int newDir){ //changes direction
		bdir = newDir;
	}

	public int getpinX(){ //gets trail pin x
		return trailpinX;
	}
	public int getpinY(){ //gets trail pin y
		return trailpinY;
	}
	public void pindrop(){ //drop pin every time bike turns
		trailpinX = vx;
		trailpinY = vy;
	}
	public void move(){ //this function lets the bike move in a direction constantly
		if (bdir == 1){
			blitx-=3;
			vx -= 3;
		}
		if (bdir == 2){
			blitx+=3;
			vx += 3;
		}
		if (bdir == 3){
			blity+=3;
			vy += 3;
		}
		if (bdir == 4){
			blity-=3;
			vy -= 3;
		}
	}

	public int rand(int low,int high) { //random number generator
		return (int) (Math.random() * (high - low + 1) + (low)); //inclusive of high
	}



	public boolean isAlive(){ //cheeks to see if bike is alive
		if(alive){
			return true;
		}
		else{
			return false;
		}
	}

	public void died(){//cheeks to see if bike is alive
		alive = false;
	}


}

