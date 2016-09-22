package com.example;


import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;
import de.greenrobot.daogenerator.ToOne;

public class Generator {
//
//
//    {
//        "id":1,
//            "name": "Jon Doe",
//            "address": {
//                "city":"NY",
//                "personId":1
//         },
//
//        "booksOwn":[
//        {
//            "bookId":1,
//                "bookName":"A brief history of time",
//                "personId":1
//        },
//        {
//            "bookId":2,
//                "bookName":"Harry Potter and the Half-Blood Prince",
//                "personId":1
//        }
//        ]
//    }


    private static final String PROJECT_DIR = System.getProperty("user.dir").replace("\\", "/");
    private static final String OUT_DIR = PROJECT_DIR + "/app/src/main/java";


    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(1, "SampleDaoModel");//change this no when you want to reconstruct the db, like you have changedthe schema or  want a refresh on DB
        create_incidentDao(schema);
        new DaoGenerator().generateAll(schema, OUT_DIR);
    }

    public static Schema create_incidentDao(Schema schema) {

        //person schema
        Entity person = schema.addEntity("Person");
        Property.PropertyBuilder personId = person.addLongProperty("id").primaryKey().autoincrement();
        person.addStringProperty("name");

        //address schema
        Entity address = schema.addEntity("Address");
        Property.PropertyBuilder personIdinAddress = address.addLongProperty("personId").primaryKey();
        address.addStringProperty("city");

        //setting one to one relationship between person and address
        ToOne toOnePersonAddress = person.addToOne(address,personId.getProperty());
        toOnePersonAddress.setName("address");

        //book schema
        Entity book = schema.addEntity("Book");
        book.addLongProperty("bookId").primaryKey().autoincrement();
        Property.PropertyBuilder personIdinBook = book.addLongProperty("personId");
        book.addStringProperty("bookName");

        //setting one to one relationship between person and books
        ToMany toManyPersonBooks = person.addToMany(personId.getProperty(),book,personIdinBook.getProperty());
        toManyPersonBooks.setName("booksOwn");

        return schema;
    }

}
