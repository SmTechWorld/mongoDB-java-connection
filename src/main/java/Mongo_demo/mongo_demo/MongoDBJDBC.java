package Mongo_demo.mongo_demo;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.Cursor;

public class MongoDBJDBC {


	public static void main( String args[] ) {

		try{
			//Connecting With Server Please add the external jar file first
			MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
			System.out.println("server connection successfully done");

			//Connecting with database
			DB dbs =mongoClient.getDB("mongo-crud-java-example");
			System.out.println("Connection Done");
			System.out.println("Database Name "+dbs.getName());
			//boolean auth = db.authenticate(myUserName, myPassword);
			//System.out.println("Authentication: "+auth);

			//Create Collection
			DBCollection coll = dbs.getCollection("employees");
			System.out.println("Collection created successfully");

			//Insert Document in Collection
			BasicDBObject doc1 = new BasicDBObject("name", "Shree").
					append("age", 24).
					append("designation", "Software Enginner").
					append("location", "Pune");
			coll.insert(doc1);
			System.out.println("Document Insereted..");

			BasicDBObject doc2 = new BasicDBObject("name", "Souvik").
					append("age", 24).
					append("designation", "Software Enginner").
					append("location", "Pune");
			coll.insert(doc2);
			System.out.println("Document Insereted..");

			BasicDBObject doc3 = new BasicDBObject("name", "Saurabh").
					append("age", 25).
					append("designation", "Software Enginner").
					append("location", "Pune");
			coll.insert(doc3);
			System.out.println("Document Insereted..");


			//Read or Find Document From Database

			Cursor cursor=coll.find();
			//BasicDBObject doc = new BasicDBObject("name", "Souvik");
			//Cursor cursor=coll.find(doc);
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
				//System.out.println(cursor.next().get("name"));
			}
			//Update Document in Collection
			BasicDBObject updatedDocument = new BasicDBObject();
			//New Value
			updatedDocument.append("$set", new BasicDBObject().append("location","Hyderabad" ));
			//Old Document
			BasicDBObject oldDocument = new BasicDBObject().append("name", "Shree");
			coll.update(oldDocument, updatedDocument,false,false);
			System.out.println("Document Updated..");

			cursor=coll.find();
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
				//System.out.println(cursor.next().get("name"));
			}


			//Delete Document

			//coll.remove(); //For All Documents

			BasicDBObject del = new BasicDBObject("name", "Souvik");
			coll.remove(del);
			cursor=coll.find();
			System.out.println("After Deletion");
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
				//System.out.println(cursor.next().get("name"));
			}

			//Drop Collection
			coll.drop();
			System.out.println("Collection Dropped");

		}catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
	}

}
