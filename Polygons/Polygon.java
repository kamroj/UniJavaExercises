import java.util.List;

public abstract class Polygon
{
    public List<Double> sides;

    public Polygon(List<Double> sides)
    {
        this.sides = sides;
    }

    public double parimeter()
    {        
        return sides.stream().mapToDouble(i -> i.doubleValue()).sum();
    }

    public abstract double surface();
}
