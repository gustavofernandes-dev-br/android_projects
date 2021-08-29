package br.dev.gustavofernandes.testesasynceweb.retrofit.model;

public class Post {
    private long userID;
    private long id;
    private String title;
    private String body;

    public long getUserID() { return userID; }
    public void setUserID(long value) { this.userID = value; }

    public long getID() { return id; }
    public void setID(long value) { this.id = value; }

    public String getTitle() { return title; }
    public void setTitle(String value) { this.title = value; }

    public String getBody() { return body; }
    public void setBody(String value) { this.body = value; }
}
