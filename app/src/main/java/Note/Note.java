package Note;

import java.util.Date;

public class Note {
    private String title;
    private String content;
    private Date modifiedDate;
    private Date createdDate;

    public Note() {}

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
        createdDate = null;
        modifiedDate = null;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

}
