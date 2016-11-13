package com.example.uremote.resources;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by raul on 13/11/16.
 */

@Path("/schema")
public class ControlSchemaResource {


    private final Bucket bucket;

    public ControlSchemaResource(Bucket bucket) {
        this.bucket =bucket;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSchema(@QueryParam("app") String schema) {
        try {
            if (schema != null) {
                return bucket.get(schema).content().toString();
            } else {
                return bucket.get("qwop").content().toString();
            }
        }catch (Exception e){
            return "some error :\\";
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public JsonDocument getSchema(JsonObject schema) {
        try {
            JsonDocument jsonDoc = JsonDocument.create("docId", schema);
            return bucket.upsert(jsonDoc);
        }catch (Exception e){
            return null;
        }
    }
}

