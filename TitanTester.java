import org.apache.commons.configuration.BaseConfiguration;
import com.thinkaurelius.titan.core.TitanFactory;
import com.thinkaurelius.titan.core.TitanGraph;
import com.thinkaurelius.titan.core.TitanProperty;
import com.thinkaurelius.titan.core.TitanVertex;
import com.thinkaurelius.titan.example.GraphOfTheGodsFactory;
import com.tinkerpop.blueprints.Vertex;

/*
 * @author Randy PatanasakPinyo (raterko@iastate.edu)
 * This code is for Titan (BerkeleyDB) version 0.5.4
 * */

public class TitanTester 
{

	public static void main(String[] args)
	{
		System.out.println("Titan: Create & Insert");
		
		TitanGraph tg = null;
		try
		{
			PrateekGraph g = new PrateekGraph();
			
			String str = "\\Users\\Preeti\\Downloads\\titan-1.0.0-hadoop1\\conf\\titan-berkeleydb-es.properties";
			tg = TitanFactory.open(str); //Replace with directory of your Titan DB
//			tg = TitanFactory.open("I:\\titan-0.5.4-hadoop2\\conf\\titan-berkeleydb-es.properties"); //Replace with directory of your Titan DB
		
			g.load(tg); //Load PrateekGraph
			
			
			//Verify that PrateekGraph is loaded successfully
			Iterable<Vertex> vertices = tg.getVertices();
			for(Vertex v : vertices)
			{
				TitanVertex u = (TitanVertex) v;
				if(u.getVertexLabel().toString().equals("student"))
				{
					System.out.println(u.toString() + "  " + u.getVertexLabel().toString() + " " + u.getPropertyCount());
					Iterable<TitanProperty> prop = u.getProperties();
					for(TitanProperty tp : prop)
					{
						System.out.println(tp.getPropertyKey() + ":" + tp.getValue());
					}
				}
			}
			System.out.println("SUCCESS");
		}
		catch(Exception e)
		{
			System.out.println("ERROR:");
			e.printStackTrace();
		}
		finally
		{
			tg.shutdown();
			System.out.println("Titan: Shutdown");
		}
	}

}
