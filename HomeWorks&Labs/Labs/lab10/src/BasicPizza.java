
import java.util.ArrayList;
import java.util.List;

public class BasicPizza extends AbstractPizza {
   /**
    * BasicPizza
    *
    */
   private int          size;
   private List<String> extras;
   private double        price;

   public BasicPizza( int size, float price )
   {
      this.size = size;
      this.extras = new ArrayList<String>();
      this.price = price;
   }

   @Override
   public List<String> toppings()
   {
      return( extras );
   }

   @Override
   public double howMuch()
   {
      return( price );
   }

   @Override
   public int getSize()
   {
      return( size );
   }
}
