package com.chatbot.chatBot;

import java.io.File;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
// import org.alicebot.ab.History;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.MagicStrings;
import org.alicebot.ab.utils.IOUtils;
 
public class chatBot {
     static final boolean TRACE_MODE = false;
    //static String botName = "super";
 
    public static void main(String[] args) {
        try {
        	String textline="";
            String resourcespath = getResourcesPath();
            MagicBooleans.trace_mode=TRACE_MODE;
            System.out.println(resourcespath);
            MagicBooleans.trace_mode = TRACE_MODE;
            Bot bot = new Bot("super", resourcespath);
            Chat chatSession = new Chat(bot);
            bot.brain.nodeStats();
          //  String textline = "";
 
            while(true) {
                System.out.print("Human : ");
                textline = IOUtils.readInputTextLine();
                if ((textline == null) || (textline.length() < 1))
                    textline = MagicStrings.null_input;
                if (textline.equals("q")) {
                    System.exit(0);
                } else if (textline.equals("wq")) {
                    bot.writeQuit();
                    System.exit(0);
                } else {
                    String request = textline;
                 //   if (MagicBooleans.trace_mode)
                   // "system.out.println("STATE=" + request + ":THAT=" + (("History") chatSession.thatHistory.get(0)).get(0) + ":TOPIC=" + chatSession.predicates.get("topic"));
                    String response = chatSession.multisentenceRespond(request);
                    while (response.contains("&amp;lt;"))
                        response = response.replace("&amp;lt;", "<");
                    while (response.contains("&amp;gt;"))
                        response = response.replace("&amp;gt;", ">");
                    System.out.println("Robot : " + response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    public static String getResourcesPath() {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        path = path.substring(0, path.length() - 2);
        System.out.println(path);
        String resourcesPath = path + File.separator + "src" + File.separator + "main" + File.separator + "resources";
        return resourcesPath;
    }
}