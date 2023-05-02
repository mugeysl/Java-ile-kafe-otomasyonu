package kafee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Scanner;

public class Musteri {
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

	public void dosyaSilme(String dosyaIsmi) {
		File file = new File(dosyaIsmi);
		if (file.exists()) {
			file.delete();
		}
	}

	public String iceceklerOkuma(int index) throws IOException {
		LinkedList<Object> list = new LinkedList<>();
		FileReader fileReader = new FileReader("Icecekler.txt");
		String line;
		BufferedReader bReader = new BufferedReader(fileReader);

		while ((line = bReader.readLine()) != null) {
			list.add(line);
		}
		bReader.close();
		return list.get(index - 1).toString();
	}

	public String tatlilarOkuma(int index) throws IOException {
		LinkedList<Object> list = new LinkedList<>();
		FileReader fileReader = new FileReader("Tatlilar.txt");
		String line;
		BufferedReader bReader = new BufferedReader(fileReader);

		while ((line = bReader.readLine()) != null) {
			list.add(line);
		}
		bReader.close();
		return list.get(index - 1).toString();
	}

	public String araSicaklarOkuma(int index) throws IOException {
		LinkedList<Object> list = new LinkedList<>();
		FileReader fileReader = new FileReader("AraSicaklar.txt");
		String line;
		BufferedReader bReader = new BufferedReader(fileReader);

		while ((line = bReader.readLine()) != null) {
			list.add(line);
		}
		bReader.close();
		return list.get(index - 1).toString();
	}

	public String anaYemeklerOkuma(int index) throws IOException {
		LinkedList<Object> list = new LinkedList<>();
		FileReader fileReader = new FileReader("AnaYemekler.txt");
		String line;
		BufferedReader bReader = new BufferedReader(fileReader);
		int sayac = 0;
		while ((line = bReader.readLine()) != null) {
			list.add(line);
		}
		bReader.close();
		return list.get(index - 1).toString();
	}

	public void masaEkleme() throws IOException {

		System.out.println("--Eklemek istedi�iniz masa--");
		System.out.print("Ad�:");
		String masaAdi = scan.next();
		dosyaYazma("Masalar.txt", masaAdi);

		LocalDateTime tarih = LocalDateTime.now();
		DateTimeFormatter gorunum = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");

		dosyaYazma("log.txt", tarih.format(gorunum) + " tarihinde \"" + masaAdi + "\" eklendi.");
	}

	public void masaGosterme() throws IOException {
		dosyaOkuma("Masalar.txt");
	}

	public String masaBul(int index) throws IOException {
		LinkedList<Object> list = new LinkedList<>();
		FileReader fileReader = new FileReader("Masalar.txt");
		String line;
		BufferedReader bReader = new BufferedReader(fileReader);

		while ((line = bReader.readLine()) != null) {
			list.add(line);
		}
		bReader.close();
		return list.get(index - 1).toString();
	}

	public void siparis() throws IOException {
		LinkedList<Object> list = new LinkedList<>();

		dosyaOkuma("Masalar.txt");
		System.out.print("-Sipari� girilecek masa numaras�: ");
		int masaNumarasi = scan.nextInt();
		int siparis;
		while (true) {
			System.out.println("1: ICECEKLER\n2: TATLILAR\n3: ARA SICAKLAR\n4: ANA YEMEKLER");
			System.out.print("-Se�iminiz:");

			int secim = scan.nextInt();
			switch (secim) {
			case 1:
				dosyaOkuma("Icecekler.txt");
				System.out.print("Sipari� numaran�z: ");
				siparis = scan.nextInt();
				list.add(iceceklerOkuma(siparis));
				break;
			case 2:
				dosyaOkuma("Tatlilar.txt");
				System.out.print("Sipari� numaran�z: ");
				siparis = scan.nextInt();
				list.add(tatlilarOkuma(siparis));
				break;
			case 3:
				dosyaOkuma("AraSicaklar.txt");
				System.out.print("Sipari� numaran�z: ");
				siparis = scan.nextInt();
				list.add(araSicaklarOkuma(siparis));
				break;
			case 4:
				dosyaOkuma("AnaYemekler.txt");
				System.out.print("Sipari� numaran�z: ");
				siparis = scan.nextInt();
				list.add(anaYemeklerOkuma(siparis));
				break;
			default:
				System.out.println("Hatal� se�im yapt�n�z!");
				break;
			}
			System.out.print("Sipari� almaya devam etmek istiyor musunuz?(e/h): ");
			String eh = scan.next();
			if (eh.equals("e")) {
				continue;
			} else {
				break;
			}
		}

		String masam = masaBul(masaNumarasi);
		File file = new File("Musteri\\".concat(masam) + ".txt");
		if (!file.exists()) {
			file.createNewFile();
			BufferedWriter bWriter = new BufferedWriter(
					new FileWriter("Musteri\\".concat(masam) + ".txt", true));
			for (int i = 0; i < list.size(); i++) {
				bWriter.write(list.get(i).toString());
				bWriter.newLine();
			}
			bWriter.close();
		} else {
			BufferedWriter bWriter = new BufferedWriter(
					new FileWriter("Musteri\\".concat(masam) + ".txt", true));
			for (int i = 0; i < list.size(); i++) {
				bWriter.write(list.get(i).toString());
				bWriter.newLine();
			}
			bWriter.close();
		}
		int i = 1;
		for (Object a : list) {
			System.out.println(i + ") " + a);
			i++;
		}
	}

	public void odeme() throws IOException {
		LinkedList<Object> list = new LinkedList<>();

		dosyaOkuma("Masalar.txt");
		System.out.print("�demesi yap�lcak masa numaras� giriniz: ");
		int masaNumarasi = scan.nextInt();

		String masam = masaBul(masaNumarasi);
		File file = new File("Musteri\\".concat(masam) + ".txt");
		if (!file.exists()) {
			System.out.println("Bu masada sipari� yok!!");
		} else {
			BufferedReader bReader = new BufferedReader(
					new FileReader("Musteri\\".concat(masam) + ".txt"));
			String line;
			while (((line = bReader.readLine()) != null)) {
				list.add(line);
			}
			bReader.close();
			double[] fiyat = new double[list.size()];
			double toplamFiyat = 0;
			for (int i = 0; i < list.size(); i++) {
				String replace = list.get(i).toString().replace(" ", "-");
				String[] bolme = replace.split("-", 0);
				fiyat[i] = Double.parseDouble(bolme[1]);
				toplamFiyat += fiyat[i];
			}
			double[] maliyet = new double[list.size()];
			double toplamMaliyet = 0;
			for (int i = 0; i < list.size(); i++) {
				String replace = list.get(i).toString().replace(" ", "-");
				String[] bolme = replace.split("-", 0);
				maliyet[i] = Double.parseDouble(bolme[3]);
				toplamMaliyet += maliyet[i];
			}
			double[] kalori = new double[list.size()];
			double toplamKalori = 0;
			for (int i = 0; i < list.size(); i++) {
				String replace = list.get(i).toString().replace(" ", "-");
				String[] bolme = replace.split("-", 0);
				kalori[i] = Double.parseDouble(bolme[5]);
				toplamKalori += kalori[i];
			}
			System.out.println("�denecek Tutar = " + toplamFiyat);
			System.out.println("Toplam Maliyet = " + toplamMaliyet);
			System.out.println("Toplam Kalori = " + toplamKalori);
			double kar = toplamFiyat - toplamMaliyet;
			System.out.println("Kar = " + (kar));

			dosyaYazma("Maliyet.txt", String.valueOf(toplamMaliyet));
			dosyaYazma("Satis.txt", String.valueOf(toplamFiyat));
			dosyaYazma("Kar.txt", String.valueOf(kar));

			System.out.print("�deme yapmak i�in 1 tu�una bas�n�n�z: ");
			int odemeTusu = scan.nextInt();
			if (odemeTusu == 1) {
				dosyaSilme("Musteri\\".concat(masam) + ".txt");
				System.out.println("�deme yap�ld�.");
			} else {
				System.out.println("�deme yap�lamad�.");
			}
		}
	}

	public void musteriKontrol() throws IOException {
		Menu menu = new Menu();
		System.out.println("1: MASALAR\n2: S�PAR��\n3: �DEME\n0: GER�");
		System.out.print("--Yapmak istedi�iniz i�lemi se�iniz:");
		int islem = scan.nextInt();
		switch (islem) {
		case 1:
			System.out.println("1: MASA EKLEME\n2: MASALARI G�STERME\n0: GER�");
			System.out.print("Se�iminiz:");
			int secim = scan.nextInt();
			if (secim == 1) {
				masaEkleme();
				System.out.print("Geri gitmek i�in 0 giriniz:");
				secim = scan.nextInt();
				if (secim == 0) {
					musteriKontrol();
				} else {
					System.out.println("Hatal� giri� yapt�n�z!");
				}
			} else if (secim == 2) {
				masaGosterme();
				System.out.print("Geri gitmek i�in 0 giriniz:");
				secim = scan.nextInt();
				if (secim == 0) {
					musteriKontrol();
				} else {
					System.out.println("Hatal� giri� yapt�n�z!");
				}
			} else if (secim == 0) {
				musteriKontrol();
				System.out.print("Geri gitmek i�in 0 giriniz:");
				secim = scan.nextInt();
				if (secim == 0) {
					musteriKontrol();
				} else {
					System.out.println("Hatal� giri� yapt�n�z!");
				}
			} else {
				System.out.println("Hatal� giri�!");
			}
			break;
		case 2:
			siparis();
			System.out.print("Geri gitmek i�in 0 giriniz:");
			secim = scan.nextInt();
			if (secim == 0) {
				musteriKontrol();
			} else {
				System.out.println("Hatal� giri� yapt�n�z!");
			}
			break;
		case 3:
			odeme();
			System.out.print("Geri gitmek i�in 0 giriniz:");
			secim = scan.nextInt();
			if (secim == 0) {
				musteriKontrol();
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