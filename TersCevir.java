package arrayBitecek;
import java.util.Scanner;
import java.util.Arrays;

public class TersCevir {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // İstatistik değişkenleri
        int totalWordsSoFar = 0;
        int totalLettersSoFar = 0;

        while (true) {
            System.out.println(); 
            System.out.print("Please enter an input string: ");
            String metin = scanner.nextLine();

            // ÇIKIŞ KONTROLÜ
            if (metin.equalsIgnoreCase("exit") || metin.equalsIgnoreCase("quit")) {
                System.out.println("Program terminating... Goodbye!");
                break; 
            }
            // İSTATİSTİK KONTROLÜ
            else if (metin.equalsIgnoreCase("stat")) {
                System.out.println("The number of words: " + totalWordsSoFar);
                System.out.println("The number of alphabetic letters: " + totalLettersSoFar);
                continue; 
            }
            // NORMAL İŞLEMLER
            else {
                // Kelime sayısını ekle (Senin if bloğun)
                if (!metin.trim().isEmpty()) {
                    String[] words = metin.trim().split("\\s+");
                    totalWordsSoFar += words.length;
                }

                // Harf sayısını ekle
                for (int i = 0; i < metin.length(); i++) {
                    if (Character.isLetter(metin.charAt(i))) {
                        totalLettersSoFar++;
                    }
                }

                // MENÜ
                System.out.println("1. Change Case");
                System.out.println("2. Count vowels and consonants");
                System.out.println("3. Capitalize the first letter");
                System.out.println("4. Encrypt or Decrypt"); // YENİ SEÇENEK
                System.out.print("Seçiminiz: ");
                
                int secim = scanner.nextInt();
                scanner.nextLine(); // ÖNEMLİ: Sayıdan sonra enter'ı temizlemek için

                System.out.println("--- SONUÇ ---");

                if (secim == 1) {
                    changeCase(metin);
                } else if (secim == 2) {
                    countVowelsConsonants(metin);
                } else if (secim == 3) {
                    capitalizeFirst(metin);
                } 
                // YENİ EKLENEN KISIM (TRY CATCH OLMADAN)
                else if (secim == 4) {
                    System.out.print("Enter an offset value (-25 to -1 or 1 to 25): ");
                    int offset = scanner.nextInt();
                    scanner.nextLine(); // Enter temizliği
                    
                    String encrypted = encryptOrDecrypt(metin, offset);
                    System.out.println("Result: " + encrypted);
                } 
                else {
                    System.out.println("Hatalı seçim!");
                }
            }
        }
    }

    // --- YENİ ŞİFRELEME METODU ---
    public static String encryptOrDecrypt(String str, int offset) {
        // Offset kontrolü (0 olamaz, -25 ile 25 arasında olmalı)
        if (offset == 0 || offset < -25 || offset > 25) {
            return "Error: Offset must be between -25 and 25 (excluding 0).";
        }

        str = str.toUpperCase(); // Önce hepsini büyüt
        String sonuc = "";

        for (int i = 0; i < str.length(); i++) {
            char harf = str.charAt(i);

            if (harf >= 'A' && harf <= 'Z') {
                // Harfin sırasını bul (A=0, B=1...)
                int originalPos = harf - 'A';
                
                // Kaydır
                int newPos = (originalPos + offset) % 26;

                // Negatif sayı çıkarsa (örn -1) onu pozitife çevir (25 yap)
                if (newPos < 0) {
                    newPos += 26;
                }

                // Tekrar harfe çevir
                char newChar = (char) ('A' + newPos);
                sonuc += newChar;
            } else {
                // Harf değilse olduğu gibi ekle
                sonuc += harf;
            }
        }
        return sonuc;
    }

    // --- ESKİ METOTLARIN AYNEN BURADA ---
    
    public static void changeCase(String str) {
        String yeniMetin = "";
        for (int i = 0; i < str.length(); i++) {
            char harf = str.charAt(i); 
            if (Character.isUpperCase(harf)) {
                yeniMetin += Character.toLowerCase(harf);
            } else if (Character.isLowerCase(harf)) {
                yeniMetin += Character.toUpperCase(harf);
            } else {
                yeniMetin += harf;
            }
        }
        System.out.println(yeniMetin);
    }

    public static void countVowelsConsonants(String str) {
        int sesli = 0;
        int sessiz = 0;
        String kucukMetin = str.toLowerCase(); 
        for (int i = 0; i < kucukMetin.length(); i++) {
            char harf = kucukMetin.charAt(i);
            if (harf >= 'a' && harf <= 'z') {
                if (harf == 'a' || harf == 'e' || harf == 'i' || harf == 'o' || harf == 'u') {
                    sesli++;
                } else {
                    sessiz++;
                }
            }
        }
        System.out.println("Vowels (Sesli): " + sesli);
        System.out.println("Consonants (Sessiz): " + sessiz);
    }

    public static void capitalizeFirst(String str) {
        if (str.length() == 0) {
            System.out.println(""); 
            return;
        }
        String sonuc = "";
        char ilkHarf = str.charAt(0);
        sonuc += Character.toUpperCase(ilkHarf);
        for (int i = 1; i < str.length(); i++) {
            char harf = str.charAt(i);
            sonuc += Character.toLowerCase(harf);
        }
        System.out.println(sonuc);
    }
}
