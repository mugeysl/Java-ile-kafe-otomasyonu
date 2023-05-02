package kafee;

import java.io.*;
import java.util.Scanner;

public class Menu {

	public void Kontrol() throws IOException {
		Scanner scan = new Scanner(System.in);

		Personel personel = new Personel();
		Musteri musteri = new Musteri();
		Kasa kasa = new Kasa();

		System.out.print("1: MENÜ\n2: PERSONEL\n3: MÜÞTERÝ\n4: KASA\n--Ýþlem yapmak istediðiniz bölümü seçiniz:");
		int islem = scan.nextInt();
		switch (islem) {
		case 1:
			menuKontrol();
			break;
		case 2:
			personel.personelKontrol();
			break;
		case 3:
			musteri.musteriKontrol();
			break;
		case 4:
			kasa.kasaKontrol();
			break;
		default:
			System.out.println("Hatalý iþlem giriþi yaptýnýz tekrar deneyiniz.");
			break;
		}
	}

	public void menuKontrol() throws IOException {
		Scanner scan = new Scanner(System.in);

		Icecekler icecekler = new Icecekler();
		Tatlilar tatlilar = new Tatlilar();
		AraSicaklar araSicaklar = new AraSicaklar();
		AnaYemekler anaYemekler = new AnaYemekler();

		System.out.println("------------\n" + "    MENU \n" + "------------");
		System.out.println("1: ICECEKLER\n2: TATLILAR\n3: ARA SICAKLAR\n4: ANA YEMEKLER\n0: GERÝ");
		System.out.print("--Yapmak istediginiz islemi seciniz:");

		while (true) {
			int islem = scan.nextInt();
			switch (islem) {
			case 1:
				icecekler.iceceklerKontrol();
				break;
			case 2:
				tatlilar.tatlilarKontrol();
				break;
			case 3:
				araSicaklar.araSicaklarKontrol();
				break;
			case 4:
				anaYemekler.anaYemeklerKontrol();
				break;
			case 0:
				Kontrol();
				break;
			default:
				System.out.println("Hatalý iþlem yaptýnýz!");
				break;
			}
		}
	}
}