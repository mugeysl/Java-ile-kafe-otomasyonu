package kafee;

import java.io.*;
import java.util.Scanner;

public class Main {
	static String admin;
	static String password;
	static String kullaniciAdi = "admin";
	static String sifre = "123456";

	public static void kullaniciGirisi() {
		Scanner scan = new Scanner(System.in);
		System.out.print("Kullanýcý adýný giriniz:");
		admin = scan.next();
		System.out.print("Þifrenizi giriniz:");
		password = scan.next();
	}

	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		Menu menu = new Menu();

		kullaniciGirisi();
		if (kullaniciAdi.equals(admin) && sifre.equals(password)) {
			System.out.println("Sisteme baþarýyla girildi!");
			menu.Kontrol();

		} else {
			System.out.println("Kullanýcý adý veya þifre yanlýþ!\nKontrol ederek tekrar deneyiniz.");
			kullaniciGirisi();
		}
	}
}