package com.TeamNovus.AutoMessage.Models;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.text.format.TextFormat;
import org.spongepowered.api.text.serializer.TextSerializers;

import com.google.common.collect.ImmutableList;

public class MessageList {

    private boolean enabled = true;
    private int interval = 45;
    private boolean random = false;
    private List<Message> messages = new LinkedList<Message>();

    private transient int currentIndex = 0;

    public MessageList() {
        messages.add(new Message("First message in the list!"));
        messages.add(new Message("&aSecond message in the list with formatters!"));
        messages.add(new Message("&bThird message in the list with formatters and a \nnew line!"));
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public boolean isRandom() {
        return random;
    }

    public void setRandom(boolean random) {
        this.random = random;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }

    public Message getMessage(Integer index) {
        try {
            return this.messages.get(index.intValue());
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public void addMessage(Integer index, Message message) {
        try {
            this.messages.add(index.intValue(), message);
        } catch (IndexOutOfBoundsException e) {
            this.messages.add(message);
        }
    }

    public boolean editMessage(Integer index, Message message) {
        try {
            return this.messages.set(index.intValue(), message) != null;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public boolean removeMessage(Integer index) {
        try {
            return this.messages.remove(index.intValue()) != null;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public boolean hasMessages() {
        return messages.size() > 0;
    }

    public void setCurrentIndex(int index) {
        this.currentIndex = index;

        if (currentIndex >= messages.size() || currentIndex < 0) {
            this.currentIndex = 0;
        }
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void broadcast(int index) {
    	String mess = this.getMessage(index).getMessage();
        Sponge.getServer().getBroadcastChannel().send(Text.of(deserializeText(mess)));
    }
    
    private static  Text mergeToText(String[] strs, String[] urls, boolean startWithUrl) {
        Text.Builder builder = Text.builder();
        mergeToText(builder, strs, urls, startWithUrl, 0, 0);
        return builder.build();
    }

    private static void mergeToText(Text.Builder builder, String[] strs, String[] urls, boolean startWithUrl, int startStrs, int startUrls) {
        if (startStrs > strs.length && startUrls > urls.length)
            return;

        TextFormat lastTextFormat = TextFormat.NONE;

        // Text formatting doesn't propagate from url to the next non-url string
        // Text formatting propagates from non-url string to the next url
        if (startWithUrl){
            if (startUrls < urls.length)
                try {
                    Text t = deserializeURLToText(urls[startUrls], lastTextFormat);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            if (startStrs < strs.length)
                builder.append(deserializeText(strs[startStrs]));
        } else {
            if (startStrs < strs.length) {
                Text t = deserializeText(strs[startStrs]);
                builder.append(t);
                lastTextFormat = lastTextFormat(t);
            }
            if (startUrls < urls.length)
                try {
                    builder.append(deserializeURLToText(urls[startUrls], lastTextFormat));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
        }

        mergeToText(builder, strs, urls, startWithUrl, startStrs + 1, startUrls + 1);
    }
    
    private static Text deserializeURLToText(String url, TextFormat format) throws MalformedURLException {
        return Text.builder(url).onClick(TextActions.openUrl(new URL(url))).format(format).build();
    }
    
    public static Text deserializeText(String str, boolean parseUrl){
        if (!parseUrl)
            return TextSerializers.FORMATTING_CODE.deserialize(str);

        final String URL_REGEX = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&\\/=]*)";
        Pattern urlPattern = Pattern.compile(URL_REGEX);
        Matcher m = urlPattern.matcher(str);
        List<String> urls = new ArrayList<String>();    // List of the urls in the string.

        while(m.find())
            urls.add(m.group());

        if (urls.isEmpty())     // URL not found
            return deserializeText(str);

        String[] nonUrlStr = str.split(URL_REGEX);      // Slitting the message for remove urls.

        boolean startWithUrl = str.startsWith(urls.get(0));     // true if the message start with an url

        // Links are merged with the rest of the message in the original position
        return mergeToText(nonUrlStr, urls.toArray(new String[urls.size()]), startWithUrl);
    }
    
    public static Text deserializeText(String str){
        return deserializeText(str, false);
    }
    
    private static TextFormat lastTextFormat(Text t){
        if (t.getChildren().isEmpty())
            return t.getFormat();

        ImmutableList<Text> children = t.getChildren();
        return lastTextFormat(children.get(children.size() - 1));
    }
}
