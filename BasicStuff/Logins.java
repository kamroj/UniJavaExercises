public class Logins
{
    public static void main(String[] args)
    {
        String[] studentNames = {"Budynek Piotr",
                                 "Chalupa Krystyna",
                                 "Wiezowiec Jan",
                                 "Szkieletor Andrzej",
                                 "Domek Marianna" };

        EmailGenerator(studentNames);
    }

    public static void EmailGenerator(String[] emails)
    {
        String name;
        for (int i = 0; i < emails.length; i++)
        {
            name = emails[i].toLowerCase();
            String[] nameParts = name.split(" ");
            nameParts[0] = nameParts[0].substring(0,2);
            nameParts[1] = nameParts[1].substring(0,3);
            System.out.println(nameParts[1]+nameParts[0]+"@agh.edu.pl");
        }
    }
}
