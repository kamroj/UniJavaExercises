import java.util.List;

public class Triangle extends Polygon
{
	public Triangle(double a, double b, double c)
    {
        super(addSidesToList(a, b, c));        
    }
    
    private static List<Double> addSidesToList(double a, double b, double c)
    {    	
    	return List.of(a, b, c);
    }

    public double surface() 
    {
    	var a = sides.get(0);
    	var b = sides.get(1);
    	var c = sides.get(2);
    	
    	var p = (a + b + c)/2;
    	
    	return java.lang.Math.sqrt(p * (p-a) * (p-b) * (p-c));
    }
}
