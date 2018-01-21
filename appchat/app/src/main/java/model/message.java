package model;

/**
 * Created by anh khoa on 12/21/2017.
 */

public class message {
    private String messageContent;
    private String messageUser;
    private long messageTime;

    public message(String msgContent, String displayName, String date) {
    }

    public message(String messageContent, String messageUser, long messageTime) {
        this.messageContent = messageContent;
        this.messageUser = messageUser;
        this.messageTime = messageTime;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getMessageUser() {
        return messageUser;
    }

    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }
}
