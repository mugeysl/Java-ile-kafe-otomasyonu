package kafee;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Scanner;

public class Personel {

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

	public void listeOlusturma() throws IOException {
		LinkedList<Object> list = new LinkedList<Object>();
		File file = new File("Personeller.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		FileReader fileReader = new FileReader(file);
		BufferedReader bReader = new BufferedReader(fileReader);
		String line;
		while ((line = bReader.readLine()) != null) {
			list.add(line);
		}
		bReader.close();
		System.out.println("--------------------\n" + "    PERSONELLER\n" + "--------------------");
		int id = 1;
		for (Object a : list) {
			System.out.println(id + ") " + a);
			id++;
		}
	}

	public void iseGiris() throws IOException {
		listeOlusturma();
		System.out.print("Ad Soyad giriniz: ");
		String adSoyad = scan.next();

		System.out.print("Maas giriniz: ");
		int maas = scan.nextInt();

		dosyaYazma("Gider.txt", String.valueOf(maas));
		dosyaYazma("Personeller.txt",
				adSoyad + " isimli personel " + maas + " TL maasla ise girdi.");

		dosyaOkuma("Personeller.txt");

		LocalDateTime tarih = LocalDateTime.now();
		DateTimeFormatter gorunum = DateTimeFormatter.ofPattern("dd/mm/yyyy hh:mm");

		dosyaYazma("log.txt",
				tarih.format(gorunum) + " tarihinde " + adSoyad + " " + maas + " isimli personel eklendi.");
		dosyaYazma("PersonelLog.txt",
				tarih.format(gorunum) + " tarihinde " + adSoyad + " " + maas + " isimli personel eklendi.");
	}

	public void istenCikis() throws IOException {
		LinkedList<Object> list = new LinkedList<>();
		try {
			BufferedReader bReader = new BufferedReader(
					new FileReader("Personeller.txt"));
			String line;
			try {
				while ((line = bReader.readLine()) != null) {
					list.add(line);
				}
				bReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("--------------------\n" + "    PERSONELLER\n" + "--------------------");
		int id = 1;
		for (Object a : list) {
			System.out.println((id) + ") " + a);
			id++;
		}

		System.out.print("-Silmek istediginiz personel id'sini giriniz:");
		int personelId = scan.nextInt();
		list.remove(personelId - 1);

		File file = new File("Personeller.txt");
		FileWriter fWriter = new FileWriter(file, false);
		BufferedWriter bWriter = new BufferedWriter(fWriter);
		for (Object liste : list) {
			bWriter.write(liste + "\n");
		}
		bWriter.close();
		System.out.println("--------------------\n" + "    PERSONELLER\n" + "--------------------");
		int i = 1;
		for (Object b : list) {
			System.out.println((i) + ") " + b);
			i++;
		}

		LocalDateTime tarih = LocalDateTime.now();
		DateTimeFormatter gorunum = DateTimeFormatter.ofPattern("dd/mm/yyyy hh:mm");

		dosyaYazma("log.txt", tarih.format(gorunum) + " tarihinde" + " personel silindi.");
		dosyaYazma("PersonelLog.txt",
				tarih.format(gorunum) + " tarihinde" + " personel silindi.");
	}

	public void mesaiSaatleri() {
		LinkedList<Object> personeller = new LinkedList<>();

		try {
			BufferedReader bReader = new BufferedReader(
					new FileReader("Personeller.txt"));
			String line;
			try {
				while ((line = bReader.readLine()) != null) {
					personeller.add(line);
				}
				bReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String[] personel = new String[personeller.size()];
		String[] ad = new String[1];

		String[] bolme = new String[8];
		for (int i = 0; i < personeller.size(); i++) {
			String replace1 = personeller.get(i).toString().replace(" ", "-");
			bolme = replace1.split("-");
			personel[i] = bolme[0];
		}

		System.out.println("--------------------\n" + "    PERSONELLER\n" + "--------------------");

		if (personeller.size() != 0) {
			int idd = 1;
			for (String a : personel) {
				System.out.println(idd + ") " + a);
				idd++;
			}
			System.out.print("Mesai eklemek istediginiz personel id'sini giriniz: ");
			int id = scan.nextInt();

			String[] bolme2 = new String[8];
			for (int i = 0; i < personeller.size(); i++) {
				String replace = personeller.get(id - 1).toString().replace(" ", "-");
				bolme2 = replace.split("-");
				ad[0] = bolme2[0];
			}

			try {
				BufferedWriter bWriter = new BufferedWriter(
						new FileWriter("MesaiSaatleri.txt", true));
				System.out.print("Mesai tarihi giriniz (gg-aa-yyyy): ");
				String date = scan.next();
				System.out.print("Kac saat mesai alacagini giriniz: ");
				int saat = scan.nextInt();
				bWriter.write(ad[0] + " -----> " + date + " ==> " + saat + " saat ek mesai ald�.");
				bWriter.newLine();
				bWriter.close();
				System.out.println(ad[0] + " isimli personel " + date + " tarihine " + saat + " saat ek mesai ald�.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			System.out.println("Mevcut personeliniz bulunmamaktadir.");
		}
	}

	public void haftalikMesaiSaatleri() throws IOException {
		dosyaOkuma("MesaiSaatleri.txt");
		dosyaYazma("HaftalikMesaiSaatleri.txt", "");
	}

	public void personelOdeme() throws IOException {
		LinkedList<Object> personeller = new LinkedList<>();

		try {
			BufferedReader bReader = new BufferedReader(
					new FileReader("Personeller.txt"));
			String line;
			try {
				while ((line = bReader.readLine()) != null) {
					personeller.add(line);
				}
				bReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String[] personel = new String[personeller.size()];

		String[] splist1 = new String[8];
		for (int i = 0; i < personeller.size(); i++) {
			String replace1 = personeller.get(i).toString().replace(" ", "-");
			splist1 = replace1.split("-");
			personel[i] = splist1[0];
		}

		System.out.println("--------------------\n" + "    PERSONELLER\n" + "--------------------");

		if (personeller.size() != 0) {
			int idd = 1;
			for (String a : personel) {
				System.out.println(idd + ") " + a);
				idd++;
			}
			System.out.print("Odemesini yapmak istediginiz kullanicinin id'sini giriniz:");
			int personelId = scan.nextInt();
			System.out.print("Odemesi yapilacak ayi giriniz (Ornek: Ocak): ");
			String ay = scan.next();
			System.out.print("Odenecek tutari giriniz:");
			double odenecekTutar = scan.nextDouble();
			System.out.println("Odeme yapildi.");

			try {
				BufferedWriter bWriter = new BufferedWriter(
						new FileWriter("PersonelOdemeler.txt", true));
				bWriter.write(personel[personelId - 1] + " isimli personele  " + ay
						+ " ayinin odemesi yapildi. TUTAR--> " + odenecekTutar + " TL");
				bWriter.newLine();
				bWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			LocalDateTime tarih = LocalDateTime.now();
			DateTimeFormatter gorunum = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");

			dosyaYazma("log.txt", tarih.format(gorunum) + (personel[personelId - 1]) + " isimli personele  " + ay
					+ " ayinin odemesi yapildi. TUTAR--> " + odenecekTutar + " TL");

		} else {
			System.out.println("Mevcut personeliniz bulunmamaktadir.");
		}
	}

	public void personelKontrol() throws IOException {

		Menu menu = new Menu();

		System.out.println(
				"1: ISE GIRIS\n2: ISTEN CIKIS\n3: MESAI SAATLERI\n4: HFTALIK MESAI SAATLERI\n5: PERSONEL ODEME\n0 : GERI");
		System.out.print("--Yapmak istediginiz islemi seciniz:");
		int islem = scan.nextInt();
		switch (islem) {
		case 1:
			iseGiris();
			System.out.print("Geri gitmek icin 0 giriniz:");
			int secim = scan.nextInt();
			if (secim == 0) {
				personelKontrol();
			} else {
				System.out.println("Hatali giris yaptiniz!");
			}
			break;
		case 2:
			istenCikis();
			System.out.print("Geri gitmek icin 0 giriniz:");
			secim = scan.nextInt();
			if (secim == 0) {
				personelKontrol();
			} else {
				System.out.println("Hatali giris yaptiniz!");
			}
			break;
		case 3:
			mesaiSaatleri();
			System.out.print("Geri gitmek icin 0 giriniz:");
			secim = scan.nextInt();
			if (secim == 0) {
				personelKontrol();
			} else {
				System.out.println("Hatali giris yaptiniz!");
			}
			break;
		case 4:
			haftalikMesaiSaatleri();
			System.out.print("Geri gitmek icin 0 giriniz:");
			secim = scan.nextInt();
			if (secim == 0) {
				personelKontrol();
			} else {
				System.out.println("Hatali giris yaptiniz!");
			}
			break;
		case 5:
			personelOdeme();
			System.out.print("Geri gitmek icin 0 giriniz:");
			secim = scan.nextInt();
			if (secim == 0) {
				personelKontrol();
			} else {
				System.out.println("Hatali giris yaptiniz!");
			}
			break;
		case 0:
			menu.Kontrol();
			break;
		default:
			System.out.println("Hatali islem yaptiniz!");
			break;
		}
	}
}
