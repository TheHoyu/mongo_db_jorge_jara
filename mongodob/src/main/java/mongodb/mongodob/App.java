package mongodb.mongodob;


import com.mongodb.ConnectionString;
import com.mongodb.client.*;
import org.bson.Document;


public class App 
{
    public static void main( String[] args )
    {
        String connectionString="mongodb+srv://GM:1234@cluster0.tbpzes5.mongodb.net/?retryWrites=true&w=majority";
        
        try(MongoClient mongoClient= MongoClients.create(new ConnectionString(connectionString))){
        	//obtener bbdd 
        	MongoDatabase database = mongoClient.getDatabase("base_mongo");
        	//sacar coleccion
        	MongoCollection<Document> collection = database.getCollection("mis_productos");
        	
        	//Insertar nuevo documento
        	Document document = new Document("nombre","Ejemplo")
    							.append("edad",30)
    							.append("ciudad","Ejemplo");
        	collection.insertOne(document);
        	
        	//Consultar e imprimir todos los documentos de la colección.
        	MongoCursor<Document> cursor = collection.find().iterator();
        					
        	try {
        		while(cursor.hasNext()) {
        			System.out.println(cursor.next().toJson());
        		}
        		
        		
        	}finally {
        		cursor.close();
        	}
        	
        }

        
        
        
        
    }
}
