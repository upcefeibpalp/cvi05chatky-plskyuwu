package cz.fei.upce.cv05.evidence.chatek;

import java.util.Scanner;

public class EvidenceChatekApp {

    static final int KONEC_PROGRAMU = 0;
    static final int VYPIS_CHATEK = 1;
    static final int VYPIS_KONKRETNI_CHATKU = 2;
    static final int PRIDANI_NAVSTEVNIKU = 3;
    static final int ODEBRANI_NAVSTEVNIKU = 4;
    static final int CELKOVA_OBSAZENOST = 5;
    static final int VYPIS_PRAZDNE_CHATKY = 6;

    static final int VELIKOST_KEMPU = 5;
    static final int MAX_VELIKOST_CHATKY = 10;

    static int[] chatky = new int[VELIKOST_KEMPU];

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int operace;

        do {
            System.out.println("""
                    \nMENU:
                                        
                    1 - vypsani vsech chatek
                    2 - vypsani konkretni chatky
                    3 - Pridani navstevniku
                    4 - Odebrani navstevniku
                    5 - Celkova obsazenost kempu
                    6 - Vypis prazdne chatky
                    0 - Konec programu
                    """);

            System.out.print("Zadej volbu: ");
            operace = scanner.nextInt();

            switch (operace) {
                case VYPIS_CHATEK -> vypisVsechChatek();
                case VYPIS_KONKRETNI_CHATKU -> vypisJedneChatky();
                case PRIDANI_NAVSTEVNIKU -> pridaniNavstevnikuDoChatky();
                case ODEBRANI_NAVSTEVNIKU -> odebraniNavstevnikuZChatky();
                case CELKOVA_OBSAZENOST -> vypisCelkoveObsazenosti();
                case VYPIS_PRAZDNE_CHATKY -> vypisPrazdnychChatek();
                case KONEC_PROGRAMU -> System.out.println("Konec programu");
                default -> System.err.println("Neplatna volba");
            }
        } while (operace != 0);
    }

    private static int zadaniIndexuChatky() {
        System.out.print("Zadej index chatky: ");
        return scanner.nextInt();
    }

    private static boolean kontrolaIndexuChatky(int indexChatky) {
        return !(indexChatky < 0 || indexChatky >= chatky.length);
    }

    private static void pridaniNavstevnikuDoChatky() {
        int indexChatky = zadaniIndexuChatky();

        if (kontrolaIndexuChatky(indexChatky)) {
            System.out.print("Zadej pocet navstevniku: ");
            int pocetNavstevniku = scanner.nextInt();

            if (pocetNavstevniku < 0 || pocetNavstevniku > MAX_VELIKOST_CHATKY
                || (chatky[indexChatky] + pocetNavstevniku) > MAX_VELIKOST_CHATKY) {
                System.err.println("Neplatna hodnota pro pocet navstevniku");
            } else {
                chatky[indexChatky] = chatky[indexChatky] + pocetNavstevniku ;
            }
        } else {
            System.err.println("Neplatny index chatky!");
        }
    }

    private static void odebraniNavstevnikuZChatky() {
        int indexChatky = zadaniIndexuChatky();

        if (kontrolaIndexuChatky(indexChatky)) {
            System.out.print("Zadej pocet navstevniku: ");
            int pocetNavstevniku = scanner.nextInt();

            if (pocetNavstevniku < 0 || pocetNavstevniku > MAX_VELIKOST_CHATKY) {
                System.err.println("Neplatna hodnota pro pocet navstevniku");
            } else {
                chatky[indexChatky] = chatky[indexChatky] - pocetNavstevniku;
            }
        } else {
            System.err.println("Neplatna hodnota pro pocet navstevniku");
        }
    }

    private static void vypisCelkoveObsazenosti() {
        int pocetNavstevniku = 0;
        for (int chatka: chatky) {
            pocetNavstevniku += chatka;
        }
        System.out.printf("V kempu je ubytovanych %d navstevniku%n", pocetNavstevniku);
    }

    private static void vypisJedneChatky() {
        int indexChatky = zadaniIndexuChatky();

        if (kontrolaIndexuChatky(indexChatky)) {
            System.out.printf("Chatka [%d] = %d%n", indexChatky, chatky[indexChatky]);
        } else {
            System.err.println("Tato chatka neexistuje!");
        }
    }

    private static void vypisVsechChatek() {
        for (int i = 0; i < chatky.length; i++) {
            System.out.printf("Chatka [%d] = %d%n", i, chatky[i]);
        }
    }

    private static void vypisPrazdnychChatek() {
        for (int i = 0; i < chatky.length; i++) {
            if (chatky[i] == 0) {
                System.out.printf("Chatka [%d] je prazdna%n", i);
            }
        }
    }
}
