import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import com.thinkaurelius.titan.core.*;
import com.thinkaurelius.titan.core.schema.TitanManagement;
import com.tinkerpop.blueprints.Vertex;

public class PrateekGraph
{
	public static final String INDEX_NAME = "search";
	
	public static TitanGraph configure(final String directory)
	{
        TitanFactory.Builder config = TitanFactory.build();
        
        
        //  config.set("index.search.backend","/Users/prateek/Downloads/titan-0.5.4-hadoop2/data/es");
   //     System.out.println(config);
        //Users/prateek/Downloads/titan-0.5.4-hadoop2/data
        
      
        
        
        config.set("storage.backend", "berkeleyje");
        config.set("storage.directory", directory);
        config.set("index."+INDEX_NAME+".backend","elasticsearch");
        config.set("index." + INDEX_NAME + ".directory", directory + File.separator + "es");
        config.set("index."+INDEX_NAME+".elasticsearch.local-mode",true);
        config.set("index."+INDEX_NAME+".elasticsearch.client-only",false);

        TitanGraph graph = config.open();
       
        return graph;
    }
	
	public static void load(final TitanGraph graph)
	{
		
		//Create Schema
		
		///Define Property Key & Indexing
		graph.rollback();
	
		TitanManagement mgmt = graph.getManagementSystem();	
		
        final PropertyKey screen_name = mgmt.makePropertyKey("screen_name").dataType(String.class).make();
        final PropertyKey retweet_count = mgmt.makePropertyKey("retweet_count").dataType(String.class).make();
     
        mgmt.buildIndex("screen_name", Vertex.class).addKey(screen_name).buildCompositeIndex();
        mgmt.buildIndex("retweet_count", Vertex.class).addKey(screen_name).addKey(retweet_count).buildCompositeIndex();
        
     
		ReadExcel.init();
        
        //Define Vertex for User
        mgmt.makeVertexLabel("User").make();
        //Define Vertex for Tweet
        mgmt.makeVertexLabel("Tweet").make();
        
        //Define Vertex for mentioned_user
        mgmt.makeVertexLabel("Mentioned_User").make();
        
        // Define Vertex for HashTag
        mgmt.makeVertexLabel("HashTag").make();
        
        //Define Vertex for Url_Used
        mgmt.makeVertexLabel("tid").make();
        
        /*---------------------------------------*/
        
        //Define Edge
        mgmt.makeEdgeLabel("posted").make();   
        
      //Define Edge
        mgmt.makeEdgeLabel("mentioned").make();   
      
      //Define Edge
        mgmt.makeEdgeLabel("tagged").make();   
        
      //Define Edge
        mgmt.makeEdgeLabel("url").make();   
        
        
        
        mgmt.commit();
        
        TitanTransaction tx = graph.newTransaction();
         
        ArrayList<ArrayList<String>> userdata = ReadExcel.userdata;
		ArrayList<ArrayList<String>> tweetdata1 = ReadExcel.tweetdata1;
		ArrayList<ArrayList<String>> mentioneddata = ReadExcel.mentioned_user;
		ArrayList<ArrayList<String>> url_used_data = ReadExcel.url_used;
		ArrayList<ArrayList<String>> tagged_data = ReadExcel.hashtag;
	
		// Collection of Vertex 
		
		ArrayList<Vertex> vertexUserList = new ArrayList<Vertex>();
		HashMap<String,ArrayList<Vertex>> posting_vertexEdgeList = new HashMap<String,ArrayList<Vertex>>();
		HashMap<String,ArrayList<Vertex>> tweetid_vertexEdgeList = new HashMap<String,ArrayList<Vertex>>();
        
		ArrayList<Vertex> vertexMentionedList = new ArrayList<Vertex>();
		ArrayList<Vertex> vertexUrlList = new ArrayList<Vertex>();
		ArrayList<Vertex> vertexTaggedList = new ArrayList<Vertex>();
		
		// Create User Vertex
		for(ArrayList<String> user : userdata){
			   Vertex v1 = tx.addVertexWithLabel("User");

			   v1.setProperty("followers",user.get(0));
			   v1.setProperty("following", user.get(1));
			   v1.setProperty("category", user.get(2));
			   v1.setProperty("location", user.get(3));
			   v1.setProperty("name", user.get(4));
			   v1.setProperty("screen_name", user.get(5));
			   v1.setProperty("sub_category", user.get(6));
			 
			 vertexUserList.add(v1);
			
			  System.out.println("User :"+v1);
		
		}
		
		
		
		// Create Tweet Vertex
		
		for(ArrayList<String> tweet : tweetdata1){
			
			Vertex v2 = tx.addVertexWithLabel("Tweet");
			v2.setProperty("tid",tweet.get(0));
			v2.setProperty("text",tweet.get(1));
			v2.setProperty("retweet_count",tweet.get(2));
			v2.setProperty("retweeted",tweet.get(3));
			v2.setProperty("date",tweet.get(4));
			v2.setProperty("posting_user",tweet.get(5));
			
			String key1 =  tweet.get(5);
			String key2 =  tweet.get(0);
			
			 System.out.println("Tweet :"+v2);
			 
			 // HashMap for tid in Edges
			 if(tweetid_vertexEdgeList.containsKey(key2)){
				 ArrayList<Vertex> al= tweetid_vertexEdgeList.get(key2);
				 al.add(v2);
			 }
			 else{
				 ArrayList<Vertex> al = new ArrayList<Vertex>();
	    		 al.add(v2);
	    		 tweetid_vertexEdgeList.put(key2,al);
			 }
			
			 // HashMap for screen_name in Edges
			if(posting_vertexEdgeList.containsKey(key1)){
				ArrayList<Vertex> al= posting_vertexEdgeList.get(key1);
    			al.add(v2);
    		}
    		else{
    			ArrayList<Vertex> al = new ArrayList<Vertex>();
    			al.add(v2);
    			posting_vertexEdgeList.put(key1, al);
    		}	
		
		}
		
		
		// Create Mentioned_User vertex

		for(ArrayList<String> mdata : mentioneddata){
			   Vertex v1 = tx.addVertexWithLabel("Mentioned_User");			   
			   v1.setProperty("tid",mdata.get(0));
	//		   v1.setProperty("screen_name", mdata.get(1) == null ? " " : mdata.get(1));
			   vertexMentionedList.add(v1);
			   System.out.println("Mentioned_User :"+v1);
		}
		
		// Create Url vertex
		
		for(ArrayList<String> urlData : url_used_data){
			   Vertex v1 = tx.addVertexWithLabel("Url_Used");
			   v1.setProperty("tid",urlData.get(0));
	//		   v1.setProperty("url", urlData.get(1) == null ? " " : urlData.get(1));
			   vertexUrlList.add(v1);	
			   System.out.println("Url :"+v1);
		}
		
		// Create Tagged vertex
		
		for(ArrayList<String> tagged : tagged_data){
			   Vertex v1 = tx.addVertexWithLabel("HashTag");
			   v1.setProperty("tid",tagged.get(0));
	//		   v1.setProperty("hashtagname", tagged.get(1) == null ? " " : tagged.get(1));
			   vertexTaggedList.add(v1); 
			   System.out.println("Tagged :"+v1);
		}
		
		
		
		
	try{
		// Add Edges between User and Tweet -- ref by screen_name
		for(Vertex v1 : vertexUserList){
			String key = v1.getProperty("screen_name");
			System.out.println(key);
			ArrayList<Vertex> al  = posting_vertexEdgeList.get(key);
			
			if(al != null){
			for(Vertex v2 : al){
				v1.addEdge("posted", v2);
				System.out.println("edge created for Tweet");
			}
			 
		}
	}
		
		
		// Add Edge between Tweet and Mentioned User -- ref by tid
		
		for(Vertex v1 : vertexMentionedList){
	//		System.out.println(v1);
			String key = v1.getProperty("tid");
			ArrayList<Vertex> al  = tweetid_vertexEdgeList.get(key);
			if(al != null){
			for(Vertex v2 : al){
				v1.addEdge("mentioned", v2);
				System.out.println("edge created for Mentioned");
			}
			 
		}
	}
		
		// Add Edge between tweet and hashtag
		
		for(Vertex v1 : vertexTaggedList){
	//		System.out.println(v1);
			String key = v1.getProperty("tid");
			ArrayList<Vertex> al  = tweetid_vertexEdgeList.get(key);
			if(al != null){
			for(Vertex v2 : al){
				v1.addEdge("tagged", v2);
				System.out.println("edge created for tagged");
			}
			 
		}
	}
		
		// Add Edge between tweet and Url_Used
		
		
		for(Vertex v1 : vertexUrlList){
	//		System.out.println(v1);
			String key = v1.getProperty("tid");
			ArrayList<Vertex> al  = tweetid_vertexEdgeList.get(key);
			if(al != null){
			for(Vertex v2 : al){
			//	v2.getEdges(in, arg1)
				v1.addEdge("url", v2);
				System.out.println("edge created for url");
			}
			 
		}
	}
		  
		  System.out.println("wait graph to commit to database");
		
	}
	catch(Exception e){
		System.out.println(e);
		System.out.println(e.getStackTrace());
	}
	
	 	tx.commit();
	  graph.commit();
//	  graph.shutdown();
    
	}
	
	public static void read(String str,TitanGraph graph){
		
		TitanGraph tg = graph;
		try
		{
			
	//	tg = TitanFactory.open(str);
		Iterable<Vertex> vertices = tg.getVertices();
		
	//	System.out.println("vertices :"+vertices);
		
		for(Vertex v : vertices)
		{
			TitanVertex u = (TitanVertex) v;
	//		System.out.println(u.getLabel());
			if(u.getVertexLabel().toString().equals("User"))
			{
				System.out.println(u.toString() + "  " + u.getVertexLabel().toString() + " " + u.getPropertyCount());
				Iterable<TitanProperty> prop = u.getProperties();
				for(TitanProperty tp : prop)
				{
					System.out.println(tp.getPropertyKey() + ":" + tp.getValue());
				}
			}
			
		/*	if(u.getVertexLabel().toString().equals("Tweet"))
			{
				System.out.println(u.toString() + "  " + u.getVertexLabel().toString() + " " + u.getPropertyCount());
				Iterable<TitanProperty> prop = u.getProperties();
				for(TitanProperty tp : prop)
				{
					System.out.println(tp.getPropertyKey() + ":" + tp.getValue());
				}
			} */
		}
		System.out.println("SUCCESS");
		}catch(Exception e)
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
	
	public static void main(String args[])
    {
		String str = "/Users/Preeti/Downloads/titan-1.0.0-hadoop1/conf/titan-berkeleydb-es.properties";
		System.out.println("---Database configuring----");
		// config.set("storage.directory", "/Users/Preeti/Downloads/titan-0.5.4-hadoop2/data");
		TitanGraph g = PrateekGraph.configure("/Users/Preeti/Downloads/titan-1.0.0-hadoop1/data");
		System.out.println("---- Start Database to load----");
		PrateekGraph.load(g);
		System.out.println("---call read vertices---");
	//	PrateekGraph.read(str,g);
        g.shutdown();
    } 
}
