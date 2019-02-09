import java.util.*;

public class Library {

    public static void main(String [] args)
    {
        var booksCounter = createBooksCounter(BOOKS);
        var whiteRavens = findWhiteRavens(booksCounter);
        printWhiteRavens(whiteRavens);

        //zadanie z *
        var uniqueTitles = getUniqueTitles(BOOKS);
        printUniqueTitles(uniqueTitles);

        //sorted via Array
        var uniqueSortedTitles = getSortedUniqueTitles(uniqueTitles);
        printSortedUniqueTitles(uniqueSortedTitles);

        //sorting using TreeSet
        sortingByTreeSet(uniqueTitles);
    }

    private static String[] BOOKS = {"Priests Of Dawn",
            "Chicken Abroad",
            "Lord With Sins",
            "Chicken Abroad",
            "Fish And Horses",
            "Mistress Of Devotion",
            "Bandit Of The Void",
            "Lord With Sins",
            "Guardians And Gangsters",
            "Lions Of Dread",
            "Chicken Abroad",
            "Future Of Fire",
            "Priests Of Dawn",
            "Fish And Horses",
            "Chicken Abroad",
            "Fish And Horses",
            "Guardians And Gangsters",
            "Inception Of Heaven",
            "Lord With Sins",
            "Future Of Fire",
            "Driving Into The Depths",
            "Starting The Demons",
            "Maid With Blue Eyes",
            "Mistress Of Devotion",
            "Lovers In The Forest",
            "Fish And Horses",
            "Maid With Blue Eyes",
            "Destruction Of The Faceless Ones",
            "Guardians And Gangsters",
            "Chicken Abroad"};

    public static HashMap<String, Integer> createBooksCounter(String [] booksTitles)
    {
        var booksCounter = new HashMap<String, Integer>();
        for (String title : booksTitles)
        {
            if(booksCounter.containsKey(title))
            {
                var oldValue = booksCounter.get(title);
                booksCounter.put(title, ++oldValue);
            }
            else
            {
                booksCounter.put(title, 1);
            }
        }
        return booksCounter;
    }

    //Zwraca liczbe wystąpień danego tytułu poprzez countBook(booksCounter, BOOKS[index].
    public static void countBook(HashMap<String, Integer> booksCounter, String bookTitle)
    {
        System.out.println(bookTitle + ": " + booksCounter.get(bookTitle));
    }

    public static ArrayList<String> findWhiteRavens(HashMap<String, Integer> booksCounter)
    {
        var whiteRavens = new ArrayList<String>();

        for(String title : booksCounter.keySet())
        {
            if(booksCounter.get(title) == 1)
            {
                whiteRavens.add(title);
            }
        }
        return whiteRavens;
    }

    public static void printWhiteRavens(ArrayList<String> whiteRavens)
    {
        var index = 1;

        System.out.println("***LIST OF WHITE RAVENS BOOKS***");
        for(var item : whiteRavens)
        {
            System.out.println(index + ". " + item);
            ++index;
        }
    }

    public static HashSet<String> getUniqueTitles (String[] BOOKS)
    {
        var uniqueTitles = new HashSet<String>();
        for(String title : BOOKS)
        {
            uniqueTitles.add(title);
        }
        return uniqueTitles;
    }

    public static void printUniqueTitles (HashSet<String> uniqueTitles)
    {
        var index = 1;

        System.out.println("\n***LIST OF UNIQUE TITLES***");
        for(String title : uniqueTitles)
        {
            System.out.println(index + ". " + title);
            ++index;
        }
    }

    //1-szy sposób sortowania HashSet
    public static ArrayList<String> getSortedUniqueTitles(HashSet<String> uniqueTitles)
    {
        var sortedList = new ArrayList(uniqueTitles);
        Collections.sort(sortedList);
        return sortedList;
    }

    public static void printSortedUniqueTitles (ArrayList<String> sortedTitleList)
    {
        var index = 1;

        System.out.println("\n***LIST OF SORTED UNIQUE TITLES VIA ARRAY LIST***");
        for(String title : sortedTitleList)
        {
            System.out.println(index + ". " + title);
            ++index;
        }
    }

    //2-gi sposób sortowania HashSet
    public static void sortingByTreeSet(HashSet<String> uniqueTitles)
    {
        var treeSet = new TreeSet<String>();
        treeSet.addAll(uniqueTitles);

        System.out.println("\n***LIST OF SORTED UNIQUE TITLES VIA TREESET***");
        System.out.println(treeSet + " --> TreeSet");
    }
}