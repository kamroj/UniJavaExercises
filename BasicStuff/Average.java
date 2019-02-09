import java.util.Scanner;

public class Average
{
    public static void main(String[] args)
    {
        MainMethod();
    }

    public static void MainMethod()
    {
        Scanner scanner = new Scanner(System.in);
        final int maxMarksSize = 20;
        int marksCount;
        float[] marks;

        System.out.println("Podaj ilość ocen: ");
        marksCount = scanner.nextInt();

        if(marksCount > maxMarksSize)
        {
            System.out.println("Liczba ocen nie może przekraczać " + maxMarksSize +"!");
            MainMethod();
        }
        else
        {
            System.out.println("Tworzę tablice o długości: " + marksCount);
            marks = new float[marksCount];
            SetArray(marks);
            System.out.println("Twoja średnia ocen wynosi: " + average(marks));
            FindMaxNumber(marks);
        }
    }

    public static double average(float[] marks)
    {
        float sum = 0;

        for(int i = 0; i < marks.length; i++)
        {
            sum += marks[i];
        }

        return sum/marks.length;
    }

    public static void SetArray(float[] marks)
    {
        for(int i = 0; i < marks.length; i++)
        {
            System.out.println("Podaj " + ((int)i+1) +" ocenę");
            marks[i] = SetArrayElement();
        }
    }

    public static float SetArrayElement()
    {
        Scanner scanner = new Scanner(System.in);
        float mark = 0;
        boolean isValid = true;

        while(isValid)
        {
            mark = scanner.nextFloat();

            if (mark > 5 || mark < 2)
            {
                System.out.println("Ocena powinna być z zakresu 2.0 do 5.0!");
            }
            else
            {
                System.out.println("Zanotowałem ocenę: " + mark);
                break;
            }
        }
        return mark;
    }

    public static void FindMaxNumber(float[] marks)
    {
        if (marks == null)
            return;

        float maxNumber= marks[0];;

        for (int i = 0; i<marks.length; i++)
        {
            if(maxNumber < marks[i])
                maxNumber = marks[i];
        }
        System.out.println("Największa liczba ze zbioru to: " + maxNumber);
    }
}