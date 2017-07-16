import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import com.thinkaurelius.titan.core.*;
import com.thinkaurelius.titan.core.schema.TitanManagement;
//import com.tinkerpop.blueprints.Vertex;

public class CreateGraph
{
	public static final String INDEX_NAME = "search";
	
	public static TitanGraph configure(final String directory)
	{
        TitanFactory.Builder config = TitanFactory.build();
        
     
        
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
	
		/*
		 * 
		 * 
		 * name = mgmt.getPropertyKey('name')
age = mgmt.getPropertyKey('age')
mgmt.buildIndex('byNameComposite', Vertex.class).addKey(name).buildCompositeIndex()
mgmt.buildIndex('byNameAndAgeComposite', Vertex.class).addKey(name).addKey(age).buildCompositeIndex()
		 * 
		 */
		
		graph.tx().rollback() ;
		TitanManagement mgmt = graph.openManagement();
		
		final PropertyKey screen_name = mgmt.makePropertyKey("screen_name").dataType(String.class).make();
	    final PropertyKey retweet_count = mgmt.makePropertyKey("retweet_count").dataType(String.class).make();
	     
	 //   mgmt.buildIndex("screen_name", TitanVertex.class).addKey(screen_name).buildCompositeIndex();
	    mgmt.buildIndex("retweet_count", TitanVertex.class).addKey(screen_name).addKey(retweet_count).buildCompositeIndex();
	        
     
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
		
		ArrayList<TitanVertex> vertexUserList = new ArrayList<TitanVertex>();
		HashMap<String,ArrayList<TitanVertex>> posting_vertexEdgeList = new HashMap<String,ArrayList<TitanVertex>>();
		HashMap<String,ArrayList<TitanVertex>> tweetid_vertexEdgeList = new HashMap<String,ArrayList<TitanVertex>>();
        
		ArrayList<TitanVertex> vertexMentionedList = new ArrayList<TitanVertex>();
		ArrayList<TitanVertex> vertexUrlList = new ArrayList<TitanVertex>();
		ArrayList<TitanVertex> vertexTaggedList = new ArrayList<TitanVertex>();
		
		// Create User Vertex
		for(ArrayList<String> user : userdata){
			//tx.addVertex(arg0)
			TitanVertex v1 = tx.addVertex("User");

			
			   v1.property("followers",user.get(0));
			   v1.property("following", user.get(1));
			   v1.property("category", user.get(2));
			   v1.property("location", user.get(3));
			   v1.property("name", user.get(4));
			   v1.property("screen_name", user.get(5));
			   v1.property("sub_category", user.get(6));
			 
			 vertexUserList.add(v1);
			
			  System.out.println("User :"+v1);
		
		}
		
		
		
		// Create Tweet Vertex
		
		for(ArrayList<String> tweet : tweetdata1){
			
			TitanVertex v2 = tx.addVertex("Tweet");
			v2.property("tid",tweet.get(0));
			v2.property("text",tweet.get(1));
			v2.property("retweet_count",tweet.get(2));
			v2.property("retweeted",tweet.get(3));
			v2.property("date",tweet.get(4));
			v2.property("posting_user",tweet.get(5));
			
			String key1 =  tweet.get(5);
			String key2 =  tweet.get(0);
			
			 System.out.println("Tweet :"+v2);
			 
			 // HashMap for tid in Edges
			 if(tweetid_vertexEdgeList.containsKey(key2)){
				 ArrayList<TitanVertex> al= tweetid_vertexEdgeList.get(key2);
				 al.add(v2);
			 }
			 else{
				 ArrayList<TitanVertex> al = new ArrayList<TitanVertex>();
	    		 al.add(v2);
	    		 tweetid_vertexEdgeList.put(key2,al);
			 }
			
			 // HashMap for screen_name in Edges
			if(posting_vertexEdgeList.containsKey(key1)){
				ArrayList<TitanVertex> al= posting_vertexEdgeList.get(key1);
    			al.add(v2);
    		}
    		else{
    			ArrayList<TitanVertex> al = new ArrayList<TitanVertex>();
    			al.add(v2);
    			posting_vertexEdgeList.put(key1, al);
    		}	
		
		}
		
		
		// Create Mentioned_User vertex

		for(ArrayList<String> mdata : mentioneddata){
			TitanVertex v1 = tx.addVertex("Mentioned_User");			   
			   v1.property("tid",mdata.get(0));
	//		   v1.setProperty("screen_name", mdata.get(1) == null ? " " : mdata.get(1));
			   vertexMentionedList.add(v1);
			   System.out.println("Mentioned_User :"+v1);
		}
		
		// Create Url vertex
		
		for(ArrayList<String> urlData : url_used_data){
			TitanVertex v1 = tx.addVertex("Url_Used");
			   v1.property("tid",urlData.get(0));
	//		   v1.setProperty("url", urlData.get(1) == null ? " " : urlData.get(1));
			   vertexUrlList.add(v1);	
			   System.out.println("Url :"+v1);
		}
		
		// Create Tagged vertex
		
		for(ArrayList<String> tagged : tagged_data){
			TitanVertex v1 = tx.addVertex("HashTag");
			   v1.property("tid",tagged.get(0));
	//		   v1.setProperty("hashtagname", tagged.get(1) == null ? " " : tagged.get(1));
			   vertexTaggedList.add(v1); 
			   System.out.println("Tagged :"+v1);
		}
		
		
		
		
	try{
		// Add Edges between User and Tweet -- ref by screen_name
		for(TitanVertex v1 : vertexUserList){
		
			String key = v1.property("screen_name").value().toString();
			System.out.println(key);
			ArrayList<TitanVertex> al  = posting_vertexEdgeList.get(key);
			
			if(al != null){
			for(TitanVertex v2 : al){
				v1.addEdge("posted", v2);
				System.out.println("edge created for Tweet");
			}
			 
		}
	}
		
		
		// Add Edge between Tweet and Mentioned User -- ref by tid
		
		for(TitanVertex v1 : vertexMentionedList){
	
			String key = v1.property("tid").value().toString();
			ArrayList<TitanVertex> al  = tweetid_vertexEdgeList.get(key);
			if(al != null){
			for(TitanVertex v2 : al){
				v1.addEdge("mentioned", v2);
				System.out.println("edge created for Mentioned");
			}
			 
		}
	}
		
		// Add Edge between tweet and hashtag
		
		for(TitanVertex v1 : vertexTaggedList){
	//		System.out.println(v1);
			String key = v1.property("tid").value().toString();
			ArrayList<TitanVertex> al  = tweetid_vertexEdgeList.get(key);
			if(al != null){
			for(TitanVertex v2 : al){
				v1.addEdge("tagged", v2);
				System.out.println("edge created for tagged");
			}
			 
		}
	}
		
		// Add Edge between tweet and Url_Used
		
		
		for(TitanVertex v1 : vertexUrlList){
	//		System.out.println(v1);
			String key = v1.property("tid").value().toString();
			ArrayList<TitanVertex> al  = tweetid_vertexEdgeList.get(key);
			if(al != null){
			for(TitanVertex v2 : al){
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
//	  graph.commit();
//	  graph.shutdown();
    
	}
	
	/*public static void read(String str,TitanGraph graph){
		
		TitanGraph tg = graph;
		try
		{
			
	//	tg = TitanFactory.open(str);
		Iterable<TitanVertex> vertices = tg.getVertexLabel("User");
		
	//	System.out.println("vertices :"+vertices);
		
		for(TitanVertex v : vertices)
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
	/*	}
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
		
	} */
	
	public static void main(String args[])
    {
		String str = "/Users/Preeti/Downloads/titan-1.0.0-hadoop1/conf/titan-berkeleydb-es.properties";
		System.out.println("---Database configuring----");
		TitanGraph g = CreateGraph.configure("/Users/Preeti/Downloads/titan-1.0.0-hadoop1/db1");
		System.out.println("---- Start Database to load----");
		CreateGraph.load(g);
		System.out.println("---call read vertices---");
	//	PrateekGraph.read(str,g);
        g.close();
    } 
}
