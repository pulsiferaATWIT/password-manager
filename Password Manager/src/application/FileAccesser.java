package application;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileAccesser {
	static ArrayList<String> passwords = new ArrayList<>();
	static ArrayList<String> usernames = new ArrayList<>();
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner fileIn = new Scanner(new File("passwords.txt"));

		while (fileIn.hasNextLine()) {
			String s = fileIn.nextLine();
			for (int i = 0; i < s.length(); i++) {
				if ((int) s.charAt(i) == 58) {
					usernames.add(s.substring(0, i));
					passwords.add(s.substring(i + 2));
				}
			}
		}
		
		fileIn.close();
		
		for (int i = 0; i < passwords.size(); i++) {
			passwords.set(i, decrypt(passwords.get(i)));
			usernames.set(i, decrypt(usernames.get(i)));
		}
		
//		for (String s : usernames) {
//			System.out.println(s);
//		}
//		
//		for (String s : passwords) {
//			System.out.println(s);
//		}
		
		System.out.println(searchByUsername("username2"));
		
		PrintWriter fileOut = new PrintWriter(new File("passwords.txt"));
		
		for (int i = 0; i < passwords.size(); i++) {
			fileOut.printf("%s: %s%n", encrypt(usernames.get(i)), encrypt(passwords.get(i)));
		}
		
		fileOut.close();
	}
	
	static String encrypt(String s) {
		String encrypted = "";
		
		for (int i = 0; i < s.length(); i++) {
			int c = s.charAt(i);
			
			if (c >= 33 && c <= 94) {
				encrypted += (char) (c + 31);
			} else if (c >= 96 && c <= 126) {
				encrypted += (char) (c - 63);
			} else if (c == 95) {
				encrypted += (char) (c - 62);
			} else {
				encrypted += (char) c;
			}
		}
		
		return encrypted;
	}
	
	static String decrypt(String s) {
		String decrypted = "";
		
		for (int i = 0; i < s.length(); i++) {
			int c = s.charAt(i);
			
			if (c == 33) {
				decrypted += (char) (c + 62);
			} else if (c >= 34 && c <= 63) {
				decrypted += (char) (c + 63);
			} else if (c >= 64 && c <= 126) {
				decrypted += (char) (c - 31);
			} else {
				decrypted += (char) c;
			}
		}
		
		return decrypted;
	}
	
	static String searchByUsername(String s) {
		for (int i = 0; i < usernames.size(); i++) {
			if (compareStrings(usernames.get(i), s)) {
				return String.format("%s: %s%n", usernames.get(i), passwords.get(i));
			}
		}
		
		return "No such account";
	}
	
	static boolean compareStrings(String s1, String s2) {
		if (s1.length() != s2.length())
			return false;
		
		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) != s2.charAt(i))
				return false;
		}
		
		return true;
	}
}
