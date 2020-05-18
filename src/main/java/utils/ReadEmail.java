package utils;
import java.util.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.*;
import javax.mail.internet.*;
import javax.xml.crypto.dsig.XMLObject;

import static credentials.Properties.MAIL_FOLDER_PATH;

public class ReadEmail {

    public static Message getMessage(File emlFile) throws Exception{
        Properties props = System.getProperties();
        Session mailSession = Session.getDefaultInstance(props, null);
        InputStream source = new FileInputStream(emlFile);
        Message message = new MimeMessage(mailSession, source);
        return message;
    }

    public static List<String> getUrlsFromMessage(String subject, String linkText) throws Exception{
        File file = getLastModified(MAIL_FOLDER_PATH);
        Message message = getMessage(file);
        List<String> allMatches = new ArrayList<String>();

        if(message.getSubject().equals(subject)){
            String html = message.getContent().toString();

            Matcher matcher = Pattern.compile("<\\s*a[^>]*>(\\s*" + linkText + "\\s*)<\\s*\\/\\s*a>").matcher(html);
            while (matcher.find()) {
                String aTag = matcher.toMatchResult().group();
                allMatches.add(aTag.substring(aTag.indexOf("http"), aTag.indexOf("\" style")));
            }
        }
        return allMatches;
    }


    public static File getLastModified(String directoryFilePath)
    {
        File directory = new File(directoryFilePath);
        File[] files = directory.listFiles(File::isFile);
        long lastModifiedTime = Long.MIN_VALUE;
        File chosenFile = null;

        if (files != null)
        {
            for (File file : files)
            {
                if (file.lastModified() > lastModifiedTime)
                {
                    chosenFile = file;
                    lastModifiedTime = file.lastModified();
                }
            }
        }

        return chosenFile;
    }
}