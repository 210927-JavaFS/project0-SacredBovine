package com.revature.daos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UserDAO {
/*
	private static Logger log = LoggerFactory.getLogger(UserDAO.class);

	public void writeUser(User user) {
		File users = new File("src\\main\\resources\\users.txt");
		
		try {
			if(users.createNewFile()) {
				log.info("Created new users file");
			}else {
				log.info("users file already exists");
			}
		}catch(IOException e) {
			log.error("Something went wrong trying to access users file: "+e.getMessage());
		}
		
		try(FileWriter writer = new FileWriter("\\src\\main\\resources\\users.txt", true)){
			StringBuilder builder = new StringBuilder(user.getName());
			builder.append(","+user.getName());
			builder.append(","+player.getWeapon().getBaseDamage());
			builder.append(","+player.getWeapon().getCritMultiplier());
			builder.append(","+player.getWeapon().getElement());
			builder.append(","+player.getMaxHealth());
			builder.append(","+player.getCurrentHealth());
			builder.append(","+player.getPotions());
			builder.append(","+player.getGold()+"\n");
			String playerString = new String(builder);
			writer.write(playerString);
		}catch(IOException e) {
			log.error("Could not write to file: "+e.getMessage());
		}
		
		
	}

	public ArrayList<User> findAllUsers() {
		ArrayList<User> allUsers = new ArrayList<>();
		try(Scanner scan = new Scanner(new File("src//main//resources//users.txt"))) {
			while(scan.hasNextLine()) {
				String userIn = scan.nextLine();
				String[] userParts = userIn.split(",");
				allUsers.add(new User(UserParts[0], new Weapon(playerParts[1],
						Integer.valueOf(playerParts[2]), Integer.valueOf(playerParts[3]), 
						Element.valueOf(playerParts[4])), Integer.valueOf(playerParts[5]), Integer.valueOf(playerParts[6]),
						Integer.valueOf(playerParts[7]), Integer.valueOf(playerParts[8])));
			}
		}catch(IOException e) {
			log.error("Something went wrong retieving Users: "+e.getMessage());
		}
		return allUsers;
	}*/
	
}
