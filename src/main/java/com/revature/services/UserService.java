package com.revature.services;

import java.util.Scanner;

import com.revature.models.User;

public class UserService {


	private static Scanner scan = new Scanner(System.in);
	
	public User createNewUser() {
		User user = new User();
		String response;
		System.out.println("Please enter your first name: ");
		response = scan.nextLine();
		System.out.println("Please enter your last name: ");
		user.setName(response, scan.nextLine());
		System.out.println("Please enter your email address: ");
		response = scan.nextLine();
		user.seteMail(response);
		System.out.println("Please enter your mailing address: ");
		response = scan.nextLine();
		user.setAddress(response);
		System.out.println("Please enter your phone number: ");
		response = scan.nextLine();
		user.setPhone(response);
		user.setType(1);
		return user;
	}
	
	public User createNewUser(String eMail, String password) { // Junk for ease of menu entry during dev testing
		User user = new User();
		user.seteMail(eMail);
		user.setPass(password);
		user.setType(1);
		return user;
	}
	
	public void displayUserInfo(User acct) { // Not sure if will end up using, just was useful at one time
		System.out.println(acct.getName());
		System.out.println(acct.getAddress());
		System.out.println(acct.getPhone());
		System.out.println(acct.geteMail());
	}
	
	public User getUser(String eMail, String password) { // more junk different line
		User user = createNewUser(eMail, password);
		user.setType(1);
		return user;
	}
	
	public int getType(User user) {
		return user.getType();
	}
	
	
	/*
	void SetType(String input){  e.g. accountHolder->employee->admin, admin->employee->accountholder, admin->accountholder and should only be used in AdminController
	}
	*/
	
	void assignAccount( User user) { //This will be used by all employees to open approved accounts, account service exists in skeleton form
		
	}
	
	/*void JoinAccount( worry about this later)
	
	updating fields of User object passed in should be implemented. 
	
*/
}


/*
 * this is from old DAO
 * private static Logger log = LoggerFactory.getLogger(UserDAO.class);

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