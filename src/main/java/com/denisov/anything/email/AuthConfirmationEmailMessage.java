package com.denisov.anything.email;

public class AuthConfirmationEmailMessage {



    private String constructEmail(String confirmationCode){
        String email = "<html>\n" +
                "           <head>\n" +
                "               <link href=\"http://fonts.cdnfonts.com/css/peace-sans\" rel=\"stylesheet\">\n" +
                "               <link href=\"http://fonts.cdnfonts.com/css/jura\" rel=\"stylesheet\">\n" +
                "           </head>\n" +
                "           <body>\n" +
                "               <div style=\"position: relative; margin: auto; width: 100%; height: 100%;\">\n" +
                "                   <div style=\"width:670px; height: 384px; background-color:#F3AE30; margin:auto; margin-top:50px; \">\n" +
                "                    <div style=\"height:20px\"></div>\n" +
                "                       <img src=\"fridge2.png\" alt=\"Anything\" width=\"150px\" style=\"display: block; margin-left: 260px; margin-top: 50px;\">\n" +
                "                       <h2 style=\"font-family: 'Peace Sans', sans-serif; text-align: center; font-size: 40px; margin-top: 20px\">"+ confirmationCode +"</h2>\n" +
                "                       <h2 style=\"font-family: 'jura', sans-serif; font-size:10px; text-align: center; margin-top:-20px \">Expires in 5 minutes</h2>\n" +
                "                    </div>\n" +
                "                   </div>\n" +
                "           </body>\n" +
                "      </html>";
        return email;
    }
}
