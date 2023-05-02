package kafee;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Scanner;

public class Kasa {
	LocalDateTime tarih = LocalDateTime.now();
	DateTimeFormatter gorunum = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
	Scanner scan = new Scanner(System.in);

	public void dosyaYazma(String dosyaIsmi, String dosyaYazilacakMetin) throws IOException {
		File file = new File(dosyaIsmi);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fWriter = new FileWriter(file, true);
		BufferedWriter bWriter = new BufferedWriter(fWriter);
		bWriter.append(dosyaYazilacakMetin + "\n");
		bWriter.close();
	}

	public void dosyaOkuma(String dosyaIsmi) throws IOException {
		File file = new File(dosyaIsmi);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileReader fileReader = new FileReader(dosyaIsmi);
		String line;
		BufferedReader bReader = new BufferedReader(fileReader);
		int n = 1;
		while ((line = bReader.readLine()) != null) {
			System.out.println(n + ") " + line);
			n++;
		}
		bReader.close();
	}

	public double toplamMaliyet() throws IOException {

		FileReader fileReader1 = new FileReader("Maliyet.txt");
		BufferedReader bReader1 = new BufferedReader(fileReader1);
		FileReader fileReader = new FileReader("Maliyet.txt");
		String line;
		BufferedReader bReader = new BufferedReader(fileReader);
		if (bReader1.readLine() == null) {
			System.out.println("Bu b�l�m bo�");
		}
		bReader1.close();
		double toplamMaliyet = 0;
		while ((line = bReader.readLine()) != null) {
			toplamMaliyet += Double.parseDouble(line);
		}
		bReader.close();
		return toplamMaliyet;

	}

	public double toplamGelir() throws IOException {
		FileReader fileReader1 = new FileReader("Satis.txt");
		BufferedReader bReader1 = new BufferedReader(fileReader1);
		FileReader fileReader = new FileReader("Satis.txt");
		String line;
		BufferedReader bReader = new BufferedReader(fileReader);
		if (bReader1.readLine() == null) {
			System.out.println("Bu b�l�m bo�");
		}
		bReader1.close();
		double toplamGelir = 0;
		while ((line = bReader.readLine()) != null) {
			toplamGelir += Double.parseDouble(line);
		}
		bReader.close();
		return toplamGelir;
	}

	public double toplamGider() throws IOException {
		FileReader fileReader1 = new FileReader("Gider.txt");
		BufferedReader bReader1 = new BufferedReader(fileReader1);
		FileReader fileReader = new FileReader("Gider.txt");
		String line;
		BufferedReader bReader = new BufferedReader(fileReader);
		if (bReader1.readLine() == null) {
			System.out.println("Bu b�l�m bo�");
		}
		bReader1.close();
		double toplamGider = 0;
		while ((line = bReader.readLine()) != null) {
			toplamGider += Double.parseDouble(line);
		}
		bReader.close();
		return toplamGider;

	}

	public void giderEkle() throws IOException {
		System.out.print("Gider ismi girin:");
		String giderIsmi = scan.next();
		System.out.print("Gider fiyat� girin:");
		Double giderFiyati = scan.nextDouble();
		String donustur = String.valueOf(giderFiyati);
		dosyaYazma("Gider.txt", donustur);
		dosyaYazma("log.txt", tarih.format(gorunum) + " tarihinde " + giderIsmi + " isimli gider " + donustur
				+ " fiyat� ile eklendi.");
	}

	public String rapor(double gelir, double gider) {
		double oran;
		String text;
		Double toplam = gelir + gider;
		Double fark = gelir - gider;
		if (gelir > gider) {
			oran = 100.0 * fark / toplam;
			text = "+%" + oran;

		} else if (fark == 0) {
			oran = 0;
			text = "%" + oran;

		} else {
			fark = gider - gelir;
			oran = 100.0 * fark / toplam;
			text = "-%" + oran;
		}

		return text;
	}

	public void kasaKontrol() throws IOException {

		Menu menu = new Menu();

		System.out.println("1: MAL�YET\n2: GEL�R\n3: G�DER\n4: G�DER EKLE\n5: RAPOR\n0: GER�");
		System.out.print("--Yapmak istedi�iniz i�lemi se�iniz:");

		int islem = scan.nextInt();
		switch (islem) {
		case 1:
			System.out.println("Toplam Maliyet= " + toplamMaliyet());
			System.out.print("Geri gitmek i�in 0 giriniz:");
			int secim = scan.nextInt();
			if (secim == 0) {
				kasaKontrol();
			} else {
				System.out.println("Hatal� giri� yapt�n�z!");
			}
			break;
		case 2:
			System.out.println("Toplam Gelir= " + (toplamGelir()));

			System.out.print("Geri gitmek i�in 0 giriniz:");
			secim = scan.nextInt();
			if (secim == 0) {
				kasaKontrol();
			} else {
				System.out.println("Hatal� giri� yapt�n�z!");
			}
			break;
		case 3:
			System.out.println("Toplam Gider= " + (toplamGider() + toplamMaliyet()));
			System.out.print("Geri gitmek i�in 0 giriniz:");
			secim = scan.nextInt();
			if (secim == 0) {
				kasaKontrol();
			} else {
				System.out.println("Hatal� giri� yapt�n�z!");
			}
			break;
		case 4:
			giderEkle();
			System.out.print("Geri gitmek i�in 0 giriniz:");
			secim = scan.nextInt();
			if (secim == 0) {
				kasaKontrol();
			} else {
				System.out.println("Hatal� giri� yapt�n�z!");
			}
			break;
		case 5:
			System.out.println("Toplam Gelirler= " + toplamGelir());
			System.out.println("Toplam Giderler= " + (toplamMaliyet() + toplamGider()));
			System.out.print("Detayl� g�rmek i�in 1'e bas�n: ");
			secim = scan.nextInt();
			if (secim == 1) {
				System.out.println("Detay= " +rapor(toplamGelir(), (toplamMaliyet() + toplamGider())));
			}
			System.out.print("Geri gitmek i�in 0 giriniz:");
			secim = scan.nextInt();
			if (secim == 0) {
				kasaKontrol();
			} else {
				System.out.println("Hatal� giri� yapt�n�z!");
			}
			break;
		case 0:
			menu.Kontrol();
			break;
		default:
			System.out.println("Hatal� se�im yapt�n�z!");
			break;
		}
	}
}