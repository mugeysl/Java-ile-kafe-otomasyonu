package kafee;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class Tatlilar {

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
		File file = new File("Tatlilar.txt");
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

		System.out.println("----------------\n" + "    TATLILAR \n" + "----------------");
		int id = 1;
		for (Object a : list) {
			System.out.println(id + ") " + a);
			id++;
		}
	}

	public void ekle() throws IOException {

		listeOlusturma();

		System.out.println("--Eklemek istedi�iniz �r�n�n--");
		System.out.print("Ad�:");
		String tatliAdi = scan.next();
		System.out.print("Fiyat�:");
		double tatliFiyati = scan.nextDouble();
		System.out.print("Maliyeti:");
		double tatliMaliyeti = scan.nextDouble();
		System.out.print("Kalorisi:");
		double tatliKalorisi = scan.nextDouble();
		System.out.println("�r�n eklendi.");

		dosyaYazma("Tatlilar.txt",
				tatliAdi + " " + tatliFiyati + " TL " + tatliMaliyeti + " TL " + tatliKalorisi + " kalori");

		LocalDateTime tarih = LocalDateTime.now();
		DateTimeFormatter gorunum = DateTimeFormatter.ofPattern("dd/mm/yyyy hh:mm");

		dosyaYazma("MenuLog.txt",
				tarih.format(gorunum) + " tarihinde \"" + tatliAdi + "\" eklendi.");
		dosyaYazma("log.txt", tarih.format(gorunum) + " tarihinde \"" + tatliAdi + "\" eklendi.");

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

		System.out.println("----------------\n" + "    TATLILAR \n" + "----------------");
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

		System.out.println("----------------\n" + "    TATLILAR \n" + "----------------");
		idd = 1;
		for (Object a : list) {
			System.out.println(idd + ") " + a);
			idd++;
		}
		LocalDateTime tarih = LocalDateTime.now();
		DateTimeFormatter gorunum = DateTimeFormatter.ofPattern("dd/mm/yyyy hh:mm");

		dosyaYazma("log.txt", tarih.format(gorunum) + " tarihinde " + " silindi.");
		dosyaYazma("MenuLog.txt",
				tarih.format(gorunum) + " tarihinde " + " silindi.");
	}

	public void guncelle() throws IOException {
		LinkedList<Object> list = new LinkedList<>();

		try {
			System.out.println("----------------\n" + "    TATLILAR \n" + "----------------");
			dosyaOkuma("Tatlilar.txt");
			FileReader fileReader = new FileReader("Tatlilar.txt");
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

		System.out.print("G�ncellemek istedi�iniz �r�n�n id'sini giriniz:");
		int urunId = scan.nextInt();
		System.out.println("--G�ncelledi�iniz �r�n�n--");
		System.out.print("Ad�:");
		String ad = scan.next();
		System.out.print("Fiyat�:");
		double fiyat = scan.nextDouble();
		System.out.print("Maliyeti:");
		double maliyet = scan.nextDouble();
		System.out.print("Kalorisi:");
		double kalori = scan.nextDouble();

		String[] eskiData = new String[1];
		eskiData[0] = list.get(urunId - 1).toString();
		list.set(urunId - 1, ad + " " + fiyat + " TL " + kalori + " kalori");

		System.out.println("----------------\n" + "    TATLILAR \n" + "----------------");
		int id = 1;
		for (Object a : list) {
			System.out.println(id + ") " + a);
			id++;
		}
		SimpleDateFormat sekil = new SimpleDateFormat();
		Date tarih = new Date();
		dosyaYazma("log.txt", sekil.format(tarih) + " tarihinde " + eskiData[0] + " --> " + list.get(urunId - 1)
				+ " seklinde g�ncellendi.");
		dosyaYazma("MenuLog.txt", sekil.format(tarih) + " tarihinde "
				+ eskiData[0] + " --> " + list.get(urunId - 1) + " seklinde g�ncellendi.");

		File file2 = new File("Tatlilar.txt");
		if (file2.exists()) {
			file2.delete();
			if (!file2.exists()) {
				try {
					file2.createNewFile();
					File file3 = new File("Tatlilar.txt");
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

	public void tatlilarKontrol() throws IOException {
		Menu menu = new Menu();

		System.out.println("1: EKLE\n2: S�L\n3: G�NCELLE\n0: GER�");
		System.out.print("--Yapmak istedi�iniz i�lemi se�iniz:");
		int islem = scan.nextInt();
		switch (islem) {
		case 1:
			ekle();
			System.out.println("Geri gitmek i�in 0 giriniz:");
			int secim = scan.nextInt();
			if (secim == 0) {
				tatlilarKontrol();
			}
			break;
		case 2:
			dosyaOkuma("Tatlilar.txt");
			System.out.print("-Silmek istedi�iniz �r�n�n id'sini giriniz:");
			int id = scan.nextInt();
			sil("Tatlilar.txt", id);
			System.out.print("Geri gitmek i�in 0 giriniz:");
			secim = scan.nextInt();
			if (secim == 0) {
				tatlilarKontrol();
			}
			break;
		case 3:
			guncelle();
			System.out.print("Geri gitmek i�in 0 giriniz:");
			secim = scan.nextInt();
			if (secim == 0) {
				tatlilarKontrol();
			}
			break;
		case 0:
			menu.menuKontrol();
			break;
		default:
			System.out.println("Hatal� se�im yapt�n�z!");
			break;
		}
	}
}