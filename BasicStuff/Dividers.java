import java.util.*;

public class Dividers
{
    public static void main(String[] args)
    {
        int number = readNumber();
        int dividers[] = findDividers(number);
        printDividers(dividers);
    }

    public static int readNumber()
    {
        System.out.print("Podaj liczbę: ");
        Scanner scan = new Scanner(System.in);
        return scan.nextInt();
    }

    public static boolean checkDivider(int a, int b)
    {
        int result = a%b;
        if(result == 0)
        {
            System.out.println("Liczba "+a+" dzieli się przez liczbę "+b);
            return true;
        }
        else
        {
            System.out.println("Liczba "+a+" nie dzieli się przez liczbę "+b);
            return false;
        }
    }

    public static int []findDividers(int number)
    {
        int index = 0;
        int dividersCount = 0;
        int tempNumber = number/2;

        for (int i = 1; i <= tempNumber; i++)
        {
            boolean isDivider = checkDivider(number, i);

            if (isDivider)
            {
                dividersCount++;
            }
        }

        int tempArray[] = new int [dividersCount];

        for (int i = 1; i <= tempNumber; i++)
        {
            boolean isDivider = checkDivider(number, i);
            if (isDivider)
            {
                tempArray[index]=i;
                index++;
            }
        }

        return  tempArray;
    }

    public static void printDividers(int [] dividers)
    {
        int i = 0;
        System.out.print("Dzielniki liczby : ");

        for(int number: dividers)
        {
            if(i++ == dividers.length -1)
            {
                System.out.print(number);
            }
            else
            {
                System.out.print(number + ", ");
            }
        }
    }

}