package kafee;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class Icecekler {

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

	public void listeOlusturma() throws IOException {
		LinkedList<Object> list = new LinkedList<Object>();
		File file = new File("Icecekler.txt");
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
		System.out.println("-----------------\n" + "    ICECEKLER \n" + "-----------------");
		int id = 1;
		for (Object a : list) {
			System.out.println(id + ") " + a);
			id++;
		}
	}

	public void ekle() throws IOException {

		listeOlusturma();

		System.out.println("--Eklemek istedi�iniz �r�n�n--");
		System.out.print("Adi:");
		String icecekAdi = scan.next();
		System.out.print("Fiyati:");
		double icecekFiyati = scan.nextDouble();
		System.out.print("Maliyeti:");
		double icecekMaliyeti = scan.nextDouble();
		System.out.print("Kalorisi:");
		double icecekKalorisi = scan.nextDouble();
		System.out.println("Urun eklendi.");
		dosyaYazma("Icecekler.txt",
				icecekAdi + " " + icecekFiyati + " TL " + icecekMaliyeti + " TL " + icecekKalorisi + " kalori");

		LocalDateTime tarih = LocalDateTime.now();
		DateTimeFormatter gorunum = DateTimeFormatter.ofPattern("dd/mm/yyyy hh:mm");

		dosyaYazma("log.txt", tarih.format(gorunum) + " tarihinde " + icecekAdi + " eklendi.");
		dosyaYazma("MenuLog.txt",
				tarih.format(gorunum) + " tarihinde \"" + icecekAdi + "\" eklendi.");
	}

	public void sil(String dosyaIsmi, int id) throws IOException {
		LinkedList<Object> list = new LinkedList<Object>();

		File file = new File(dosyaIsmi);
		if (!file.exists()) {
			file.createNewFile();
		}

		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			list.add(line);
		}
		bufferedReader.close();

		System.out.println("-----------------\n" + "    ICECEKLER \n" + "-----------------");
		int idd = 1;
		for (Object a : list) {
			System.out.println(idd + ") " + a);
			idd++;
		}

		list.remove(id - 1);

		FileWriter fWriter = new FileWriter(file, false);
		BufferedWriter bWriter = new BufferedWriter(fWriter);
		for (Object liste : list) {
			bWriter.write(liste + "\n");
		}
		bWriter.close();

		System.out.println("-----------------\n" + "    ICECEKLER \n" + "-----------------");
		// System.out.println(" ADI\t\tFIYATI\tKALORI\n"
		// + " ---\t\t-------\t------");
		idd = 1;
		for (Object a : list) {
			System.out.println(idd + ") " + a);
			idd++;
		}
		LocalDateTime tarih = LocalDateTime.now();
		DateTimeFormatter gorunum = DateTimeFormatter.ofPattern("dd/mm/yyyy hh:mm");
		dosyaYazma("MenuLog.txt",
				tarih.format(gorunum) + " tarihinde " + " silindi.");
		dosyaYazma("log.txt", tarih.format(gorunum) + " tarihinde " + " �r�n silindi.");
	}

	public void guncelle() throws IOException {
		LinkedList<Object> list = new LinkedList<>();

		try {
			System.out.println("-----------------\n" + "    ICECEKLER \n" + "-----------------");
			dosyaOkuma("Icecekler.txt");
			FileReader fileReader = new FileReader("Icecekler.txt");
			String line;
			BufferedReader bReader = new BufferedReader(fileReader);
			while ((line = bReader.readLine()) != null) {
				list.add(line);
			}
			bReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.print("Guncellemek istediginiz urunun id'sini giriniz:");
		int urunId = scan.nextInt();
		System.out.println("--Guncellediginiz urunun--");
		System.out.print("Adi:");
		String ad = scan.next();
		System.out.print("Fiyati:");
		double fiyat = scan.nextDouble();
		System.out.print("Maliyeti:");
		double maliyet = scan.nextDouble();
		System.out.print("Kalorisi:");
		double kalori = scan.nextDouble();

		String[] eskiData = new String[1];
		eskiData[0] = list.get(urunId - 1).toString();
		list.set(urunId - 1, ad + " " + fiyat + " TL " + maliyet + " TL " + kalori + " kalori");

		System.out.println("-----------------\n" + "    ICECEKLER \n" + "-----------------");
		int id = 1;
		for (Object a : list) {
			System.out.println(id + ") " + a);
			id++;
		}

		SimpleDateFormat sekil = new SimpleDateFormat();
		Date tarih = new Date();
		dosyaYazma("log.txt", sekil.format(tarih) + " tarihinde " + eskiData[0] + " --> " + list.get(urunId - 1)
				+ " seklinde guncellendi.");
		dosyaYazma("MenuLog.txt", sekil.format(tarih) + " tarihinde "
				+ eskiData[0] + " --> " + list.get(urunId - 1) + " seklinde g�ncellendi.");

		File file2 = new File("Icecekler.txt");
		if (file2.exists()) {
			file2.delete();
			if (!file2.exists()) {
				try {
					file2.createNewFile();
					File file3 = new File("Icecekler.txt");
					FileWriter fileWriter = new FileWriter(file3, true);
					BufferedWriter bWriter = new BufferedWriter(fileWriter);
					for (int i = 0; i < list.size(); i++) {
						bWriter.write(list.get(i).toString());
						bWriter.newLine();
					}
					bWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void iceceklerKontrol() throws IOException {
		Menu menu = new Menu();

		System.out.println("1: EKLE\n2: SIL\n3: GUNCELLE\n0: GERI");
		System.out.print("--Yapmak istediginiz islemi seciniz:");
		int islem = scan.nextInt();
		switch (islem) {
		case 1:
			ekle();
			System.out.print("Geri gitmek icin 0 giriniz:");
			int secim = scan.nextInt();
			if (secim == 0) {
				iceceklerKontrol();
			}
			break;
		case 2:
			dosyaOkuma("Icecekler.txt");
			System.out.print("-Silmek istediginiz urunun id'sini giriniz:");
			int id = scan.nextInt();
			sil("Icecekler.txt", id);
			System.out.print("Geri gitmek icin 0 giriniz:");
			secim = scan.nextInt();
			if (secim == 0) {
				iceceklerKontrol();
			}
			break;
		case 3:
			guncelle();
			System.out.print("Geri gitmek icin 0 giriniz:");
			secim = scan.nextInt();
			if (secim == 0) {
				iceceklerKontrol();
			}
			break;
		case 0:
			menu.menuKontrol();
			break;
		default:
			System.out.println("Hatali secim yaptiniz!");
			break;
		}
	}
}
