import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Card {
    int randomkozir;
    int mybeatindex;
    int min;
    int first;
    int myhodindex;
    int size;
    int randomindex;
    int convertnum;
    int kolodasize;
    String mybeatmast;
    String computermin;
    String kozir;
    String minkozir;
    String computercardnum;
    String generalmast;
    String minconvert;
    String computercard;
    String computercardmast;
    String myhod;
    String card;
    String symbols;
    String masti;
    String turn = "Мой ход";
    String myhodmast;
    String myhodnum;
    String mybeat;
    String computerthrowmast;
    ArrayList<String> myhand = new ArrayList<>();
    ArrayList<String> koloda = new ArrayList<>();
    ArrayList<String> computerhand = new ArrayList<>();
    ArrayList<Integer> computerhandmast = new ArrayList<>();
    ArrayList<Integer> computerhandkozir = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public void setupgame() {
        System.out.println("Добро пожаловать в игру \"Дурак\" на одну масть.");
        System.out.println("Колода дается на выбор: 36 карт или 52 карты.");
        System.out.println(ANSI_RED + "Правила такие же как в классической игре \"Дурак\".");
        System.out.println("Сражаться вы будете против компьютера.");
        System.out.println(ANSI_BLUE + "Кто первый будет ходить будет определенно случайно.");
        System.out.println("Удачи!" + ANSI_RESET);
        System.out.println();
        System.out.println(ANSI_CYAN + "/////////////////////////////////////////////////////////////////////////////////////////////////////////////" + ANSI_RESET);
        System.out.println();
        firstmove();
        setkolodasize();
        setofkoloda();
        setofkozir();
    }
    public void firstmove() {                                //Кто будет первым ходить? Выбираем случайно.
        first = (int) (Math.random() * 2);
        if (first == 0) {
            turn = "Ход противника";
        } else {
            turn = "Мой ход";
        }
    }
    public void setkolodasize(){
        System.out.println(ANSI_GREEN + "Введите сколько карт вы хотите видеть в колоде(36 или 52):" + ANSI_RESET);
        try {
            kolodasize = scanner.nextInt();
        } catch (InputMismatchException | IndexOutOfBoundsException e) {
            excendgame();
        }
        if(kolodasize >= 52)
            kolodasize = 52;
        else kolodasize = 36;
        System.out.println("Вы выбрали колоду из: " + kolodasize + " карт.");
    }
    public void getkozir(){
        System.out.println(ANSI_YELLOW + "Козырь: " + kozir + ANSI_RESET);
    }
    public void startgame() {
        kolodasize();
        if (turn.equals("Ход противника")) {
            computerthrowaCard();
            ibeat();
            if (!koloda.isEmpty() & !computerhand.isEmpty()) System.out.println("Берем карты из колоды.");
            computertakeaCard();
            itakeaCard();
            getkozir();
            take();
        } else {
            ithrowaCard();
            if (!koloda.isEmpty()) System.out.println("Берем карты из колоды.");
            itakeaCard();
            computertakeaCard();
            getkozir();
            take();
        }
    }
    public void setofkoloda() {
        if(kolodasize == 52) {
            for (int j = 0; j < 4; j++) {
                switch (j) {
                    case 0:
                        masti = "♠";
                        break;
                    case 1:
                        masti = "♣";
                        break;
                    case 2:
                        masti = "♦";
                        break;
                    case 3:
                        masti = "♥";
                        break;
                }
                for (int x = 2; x < 15; x++) {
                    switch (x) {
                        case 11:
                            symbols = "J";
                            koloda.add(symbols + masti);
                            break;
                        case 12:
                            symbols = "Q";
                            koloda.add(symbols + masti);
                            break;
                        case 13:
                            symbols = "K";
                            koloda.add(symbols + masti);
                            break;
                        case 14:
                            symbols = "T";
                            koloda.add(symbols + masti);
                            break;
                        default:
                            koloda.add(x + masti);
                    }
                }
            }
        } else {
            for (int j = 0; j < 4; j++) {
                switch (j) {
                    case 0:
                        masti = "♠";
                        break;
                    case 1:
                        masti = "♣";
                        break;
                    case 2:
                        masti = "♦";
                        break;
                    case 3:
                        masti = "♥";
                        break;
                }
                for (int x = 6; x < 15; x++) {
                    switch (x) {
                        case 11:
                            symbols = "J";
                            koloda.add(symbols + masti);
                            break;
                        case 12:
                            symbols = "Q";
                            koloda.add(symbols + masti);
                            break;
                        case 13:
                            symbols = "K";
                            koloda.add(symbols + masti);
                            break;
                        case 14:
                            symbols = "T";
                            koloda.add(symbols + masti);
                            break;
                        default:
                            koloda.add(x + masti);
                    }
                }
            }
        }
        System.out.println("Общая колода выглядит вот так: " + koloda);
    }
    public void setofkozir(){
        randomkozir = (int) (Math.random() * 4);
        switch (randomkozir) {
            case 0:
                kozir = "♠";
                break;
            case 1:
                kozir = "♣";
                break;
            case 2:
                kozir = "♦";
                break;
            case 3:
                kozir = "♥";
                break;
        }
        getkozir();
    }
    public void hand(int size) {                        //Устанавливаем размер руки каждого игрока.
        if (size == 0) excendgame();
        this.size = size;
        for (int x = 0; x < size; x++) {
            randomindex = (int) (Math.random() * koloda.size());
            card = koloda.get(randomindex);
            myhand.add(card);
            koloda.remove(randomindex);
            randomindex = (int) (Math.random() * koloda.size());
            card = koloda.get(randomindex);
            computerhand.add(card);
            koloda.remove(randomindex);
        }
        take();
    }
    public void kolodasize() {
        System.out.println("В колоде осталось: " + koloda.size() + " карт(ы).");
    }
    public void ithrowaCard() {
        System.out.println(ANSI_CYAN + "Ваш ход. Введите индекс(начиная с 0) карты, которую хотите бросить противнику: " + ANSI_RESET);
        try {
            myhod = myhand.get(scanner.nextInt());
            myhodindex = myhand.indexOf(myhod);
        } catch (InputMismatchException | IndexOutOfBoundsException e) {
            excendgame();
        }
        if (myhod.length() < 3) {
            myhodmast = myhod.substring(1);
            myhodnum = myhod.substring(0, 1);
        } else {
            myhodmast = myhod.substring(2);
            myhodnum = myhod.substring(0, 2);
        }
        myhand.remove(myhodindex);
        System.out.println("Вы ходите картой: " + myhod + ".");
        convertnumber(myhodnum);
        computerbeat(convertnum, myhodmast);
    }
    public void computerthrowaCard() {                      //Компьютер кидает карту
        computermin = Collections.min(computerhand);
        computerhand.remove(computermin);
        if (computermin.length() < 3) {
            computerthrowmast = computermin.substring(1);
        } else {
            computerthrowmast = computermin.substring(2);
        }
        System.out.println(ANSI_GREEN + "Ход противника. Противник бросил: " + computermin + ANSI_RESET);
    }
    public void itakeaCard() {                                   //Я беру карту
        if (!koloda.isEmpty() && myhand.size() < size) {
            randomindex = (int) (Math.random() * koloda.size());
            card = koloda.get(randomindex);
            myhand.add(card);
            System.out.println("Вы взяли карту: " + card);
            koloda.remove(card);
        }
        if (myhand.size() > size & !koloda.isEmpty()) {
            System.out.println("У вас слишком много карт, вы не берете карту на этом ходу.");
        }
    }
    public void computertakeaCard() {                            //Компьютер берет карту.
        if (!koloda.isEmpty() & computerhand.size() < size) {
            randomindex = (int) (Math.random() * koloda.size());
            card = koloda.get(randomindex);
            computerhand.add(card);
            koloda.remove(card);
            System.out.println("Компьютер взял карту.");
        }
        if (computerhand.size() > size & !koloda.isEmpty()) {
            System.out.println("У компьютера слишком много карт. Поэтому на этом ходу карту из колоды он брать не будет.");
        }
    }
    public void ibeat() {
        System.out.println("Введите индекс(начиная с 0) карты которой хотите покрыться(Если вам нечем крыться, введите число меньшее 0 или большее размера вашей руки): ");
        try {
            mybeatindex = scanner.nextInt();
            mybeat = myhand.get(mybeatindex);
        }catch (InputMismatchException | IndexOutOfBoundsException e) {
            excendgame();
        }
        if (mybeat.length() < 3) {
            mybeatmast = mybeat.substring(1);
        } else {
            mybeatmast = mybeat.substring(2);
        }
        if(mybeatmast.equals(computerthrowmast) | mybeatmast.equals(kozir)) {
            myhand.remove(mybeat);
            System.out.println("Вы покрыли картой: " + mybeat + " карту соперника: " + computermin + ".");
            turn = "Мой ход";
        } else{
            System.out.println("Вы бросили карту другой масти, " + mybeat + " вам придется взять карту соперника: " + computermin);
            myhand.add(computermin);
            turn = "Ход противника";
        }

    }
    public void computerbeat(int peopleconvertnum, String myhodmast) {
        computerhandmast.clear();
        computerhandkozir.clear();
        for (int x = 0; x < computerhand.size(); x++) {
            computercard = computerhand.get(x);
            if (computercard.length() < 3) {
                computercardmast = computercard.substring(1);
                computercardnum = computercard.substring(0,1);
            } else {
                computercardmast = computercard.substring(2);
                computercardnum = computercard.substring(0,2);
            }
            convertnumber(computercardnum);
            if(kozir.equals(computercardmast) & convertnum > peopleconvertnum){
                computerhandkozir.add(convertnum);
            }
            if (computercardmast.equals(myhodmast) & convertnum > peopleconvertnum){
                computerhandmast.add(convertnum);
                generalmast = computercardmast;
            }
        }
        if(computerhandmast.size() > 0 & computerhandkozir.size() == 0) {                       //Новый список с элементами, которыми компьютер может покрыться
            min = Collections.min(computerhandmast);
            backconvert(min);
            System.out.println("Противник кроет вашу карту своей: " + minconvert + ".");
            computerhand.remove(minconvert);
            turn = "Ход противника";
        } else if(computerhandmast.size() == 0 & computerhandkozir.size() > 0) {
            min = Collections.min(computerhandkozir);
            backconvert(min);
            System.out.println("Противник кроет вашу карту своей: " + minkozir + ".");
            computerhand.remove(minkozir);
            turn = "Ход противника";
        } else if(computerhandmast.size() > 0 & computerhandkozir.size() > 0){
            min = Collections.min(computerhandmast);
            backconvert(min);
            System.out.println("Противник кроет вашу карту своей: " + minconvert + ".");
            computerhand.remove(minconvert);
            turn = "Ход противника";
        } else {
            System.out.println("Компьютеру нечем крыться и он берет вашу карту.");
            computerhand.add(myhod);
            turn = "Мой ход";
        }
    }
    public void backconvert(int min){
        switch (min) {
            case 11:
                minconvert = "J" + generalmast;
                minkozir = "J" + kozir;
                break;
            case 12:
                minconvert = "Q" + generalmast;
                minkozir = "Q" + kozir;
                break;
            case 13:
                minconvert = "K" + generalmast;
                minkozir = "K" + kozir;
                break;
            case 14:
                minconvert = "T" + generalmast;
                minkozir = "T" + kozir;
                break;
            default:
                minconvert = min + generalmast;
                minkozir = min + kozir;
                break;
        }
    }
    public void convertnumber(String num){
        switch (num){
            case "J":
                convertnum = 11;
                break;
            case "Q":
                convertnum = 12;
                break;
            case "K":
                convertnum = 13;
                break;
            case "T":
                convertnum = 14;
                break;
            default:
                convertnum = Integer.parseInt(num);
        }
    }
    public void take(){
        System.out.println(ANSI_PURPLE + "Ваша рука: " + myhand + ANSI_RESET);
        Collections.sort(computerhand);
        //System.out.println("Рука противника: " + computerhand);
    }
    public void endgame(){                          //Конец игры.
        kolodasize();
        if(myhand.isEmpty())
            System.out.println(ANSI_YELLOW + "У вас не осталось карт. Поздравляю, вы победили!" + ANSI_RESET);
        else System.out.println(ANSI_RED + "У противника не осталось карт. К сожалению, вы проиграли." + ANSI_RESET);
    }
    public void excendgame(){
        System.out.println(ANSI_RED + "Вероятно, вы ввели букву вместо цифры, или указали количество карт большее 16 или меньшее 1. В следующий раз следуйте правилам!" + ANSI_RESET);
        System.out.println(ANSI_RED + "Игра окончена. Попробуйте снова." + ANSI_RESET);
        System.exit(0);
    }
}