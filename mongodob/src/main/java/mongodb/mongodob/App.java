package mongodb.mongodob;


import com.mongodb.ConnectionString;
import com.mongodb.client.*;
import org.bson.Document;


public class App 
{
    public static void main( String[] args )
    {
        String connectionString="mongodb+srv://GM:1234@cluster0.tbpzes5.mongodb.net/?retryWrites=true&w=majority";
          //  String connectionString = "mongodb://localhost:27017";
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
////////////////////////////////////////////////////////////////////
            System.out.println("Documentos antes de la modificación:");
            printAllDocuments(collection);

        // Modificar un documento
            modificarDocumento(collection, "mis_productos", "edad", 35);

            System.out.println("Documentos después de la modificación:");
            printAllDocuments(collection);


           borrarDocumento(collection, "Ejemplo");
            
            System.out.println("Documentos después de la eliminación:");
            printAllDocuments(collection);


            
            
        }

        
       private static void insertarDocumento(MongoCollection<Document> collection, String nombre, int edad, String ciudad) {
        Document document = new Document("nombre", nombre)
                .append("edad", edad)
                .append("ciudad", ciudad);
        collection.insertOne(document);
    }

        private static void modificarDocumento(MongoCollection<Document> collection, String nombre, String campo, Object valor) {
        collection.updateOne(new Document("nombre", nombre),
                new Document("$set", new Document(campo, valor)));
    }

    private static void borrarDocumento(MongoCollection<Document> collection, String nombre) {
        collection.deleteOne(new Document("nombre", nombre));
    }

    private static void printAllDocuments(MongoCollection<Document> collection) {
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }
    }
        
    }
}
