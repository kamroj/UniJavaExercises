import java.util.HashMap;
import java.util.LinkedList;

public class LyricsCounter {

    private static final String LYRICS = "Dwunastu braci, wierzac w sny, zbadalo mur od marzen strony,\n" +
            "A poza murem plakal glos, dziewczecy glos zaprzepaszczony.\n" +
            "I pokochali glosu dzwiek i chetny domysl o Dziewczynie,\n" +
            "I zgadywali ksztalty ust po tym, jak spiew od zalu ginie...\n" +
            "Mowili o niej: \"lka, wiec jest!\" - I nic innego nie mowili,\n" +
            "I przezegnali caly swiat - i swiat zadumal sie w tej chwili...\n" +
            "Porwali mloty w twarda dlon i jeli w mury tluc z loskotem!\n" +
            "I nie wiedziala slepa noc, kto jest czlowiekiem, a kto mlotem?\n" +
            "\"O, predzej skruszmy zimny glaz, nim smierc Dziewczyne rdza powlecze!\" -\n" +
            "Tak, walac w mur, dwunasty brat do jedenastu innych rzecze.\n" +
            "Ale daremny byl ich trud, daremny ramion sprzeg i usil!\n" +
            "Oddali ciala swe na strwon owemu snowi, co ich kusil!\n" +
            "lamia sie piersi, trzeszczy kosc, prochnieja dlonie, twarze bledna...\n" +
            "I wszyscy w jednym zmarli dniu i noc wieczysta mieli jedna!\n" +
            "Lecz cienie zmarlych - Boze moj! - nie wypuscily mlotow z dloni!\n" +
            "I tylko inny plynie czas - i tylko mlot inaczej dzwoni...\n" +
            "I dzwoni w przod! I dzwoni wspak! I wzwyz za kazdym grzmi nawrotem!\n" +
            "I nie wiedziala slepa noc, kto tu jest cieniem, a kto mlotem?\n" +
            "\"O, predzej skruszmy zimny glaz, nim smierc Dziewczyne rdza powlecze!\" -\n" +
            "Tak, walac w mur, dwunasty cien do jedenastu innych rzecze.\n" +
            "Lecz cieniom zbraklo nagle sil, a cien sie mrokom nie opiera!\n" +
            "I powymarly jeszcze raz, bo nigdy dosc sie nie umiera...\n" +
            "I nigdy dosc, i nigdy tak, jak pragnie tego ow, co kona!...\n" +
            "I znikla tresc - i zginal slad - i powiesc o nich juz skonczona!\n" +
            "Lecz dzielne mloty - Boze moj - mdlej nie poddaly sie zalobie!\n" +
            "I same przez sie bily w mur, huczaly spizem same w sobie!\n" +
            "Huczaly w mrok, huczaly w blask i ociekaly ludzkim potem!\n" +
            "I nie wiedziala slepa noc, czym bywa mlot, gdy nie jest mlotem?\n" +
            "\"O, predzej skruszmy zimny glaz, nim smierc Dziewczyne rdza powlecze!\" -\n" +
            "Tak, walac w mur, dwunasty mlot do jedenastu innych rzecze.\n" +
            "I runal mur, tysiacem ech wstrzasajac wzgorza i doliny!\n" +
            "Lecz poza murem - nic i nic! Ni zywej duszy, ni Dziewczyny!\n" +
            "Niczyich oczu ani ust! I niczyjego w kwiatach losu!\n" +
            "Bo to byl glos i tylko - glos, i nic nie bylo oprocz glosu!\n" +
            "Nic - tylko placz i zal i mrok i niewiadomosc i zatrata!\n" +
            "Takiz to swiat! Niedobry swiat! Czemuz innego nie ma swiata?\n" +
            "Wobec klamliwych jawnie snow, wobec zmarnialych w nicosc cudow,\n" +
            "Potezne mloty legly w rzad, na znak spelnionych godnie trudow.\n" +
            "I byla zgroza naglych cisz. I byla proznia w calym niebie!\n" +
            "A ty z tej prozni czemu drwisz, kiedy ta proznia nie drwi z ciebie?";

    private static String[] getWordsInLyrics() {
        return LYRICS.split("\\W+");
    }

    public static void main(String[] args) {
        String [] allWords = getWordsInLyrics();

        var wordBegins = getMostRecentWordFromAllWords(allWords);

        printMostCommonWord(wordBegins);

        wordBegins = getMostRecentWordLongerThanTwoLetters(allWords, 4);

        printMostCommonWord(wordBegins);
    }

    public static HashMap<String, LinkedList<String>> getMostRecentWordFromAllWords (String[] allWords)
    {
        HashMap<String, LinkedList<String>> wordBegins = new HashMap<>();

        if(allWords==null || allWords.length == 0)
        {
            System.out.println("Error: String array is null or empty!");
            return null;
        }

        for (String word : allWords)
        {
            if(wordBegins.keySet().contains(word.substring(0,1)))
            {
                wordBegins.get(word.substring(0,1)).add(word);
            }
            else
            {
                wordBegins.put(word.substring(0,1), new LinkedList<String>());
            }
        }
        return wordBegins;
    }

    public static HashMap<String, LinkedList<String>> getMostRecentWordLongerThanTwoLetters (String[] allWords, int minimumWordSize)
    {
        System.out.println("\nSettings: minimum word size is set to: " + minimumWordSize);

        if(allWords==null || allWords.length == 0)
        {
            System.out.println("Error: String array is null or empty!");
            return null;
        }

        HashMap<String, LinkedList<String>> wordBegins = new HashMap<>();

        for (String word : allWords)
        {
            if (word.length()<minimumWordSize)
            {
                continue;
            }
            if(wordBegins.keySet().contains(word.substring(0,1)))
            {
                wordBegins.get(word.substring(0,1)).add(word);
            }
            else
            {
                wordBegins.put(word.substring(0,1), new LinkedList<String>());
            }
        }
        return wordBegins;
    }

    public static void printMostCommonWord(HashMap<String, LinkedList<String>> wordBegins)
    {
        String letter = "";
        int highestValue = 0;

        for (String word : wordBegins.keySet())
        {
            if(wordBegins.get(word.substring(0)).size() > highestValue)
            {
                highestValue = wordBegins.get(word).size();
                letter = word.substring(0);
            }
        }

        System.out.println("The most common letter in text is: " + letter);
        System.out.println("Word repeads in text " + highestValue + " times!\n");

        var index = 1;
        for (int i = 0; i < wordBegins.get(letter).size() ; i++)
        {
            System.out.println(index + ". " + wordBegins.get(letter).get(i));
            ++index;
        }
    }

}