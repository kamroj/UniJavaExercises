import java.util.List;

public class Rectangle extends Polygon
{
    public Rectangle(double a, double b)
    {
        super(addSidesToList(a, b));        
    }
    
    private static List<Double> addSidesToList(double a, double b)
    {    	
    	return List.of(a, b, a, b);
    }

    public double surface() 
    {
    	return sides.get(0) * sides.get(1);
    }   
}
