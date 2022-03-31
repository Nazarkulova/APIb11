package UTILS;

public class PayloadUtil {
    public static String getPetPayload(int id,String PetName,String PetStatus){
        String petPayload ="{\n" +
                "  \"id\": "+id+",\n" +
                "  \"category\": {\n" +
                "    \"id\": 22,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \""+PetName+"\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \""+PetStatus+"\"\n" +
                "}";
        return petPayload;
    }

    public static String getSlackMessagePayload(String message){
        String payload ="{\n" +
                "    \"channel\": \"C0397J4JY3T\",\n" +
                "    \"text\": \"Atyra: "+message+"\"\n" +
                "}";
        return payload;

    }
}
