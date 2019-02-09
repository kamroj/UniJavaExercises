public class Display
{
    public static void main(String [] args)
    {
        Polygon triangle = new Triangle(3, 4, 5);
        Polygon rectangle = new Rectangle(3,5);
        Polygon squere = new Square(10);

        //triangle
        System.out.println("\nTriangle parimeter: " + triangle.parimeter());
        System.out.println("Triangle surface: " + triangle.surface());

        //rectangle
        System.out.println("\nRectangle parimeter: " + rectangle.parimeter());
        System.out.println("Rectangle surface: " + rectangle.surface());

        //squere
        System.out.println("\nSquere parimeter: " + squere.parimeter());
        System.out.println("Squere surface: " + squere.surface());
    }
}