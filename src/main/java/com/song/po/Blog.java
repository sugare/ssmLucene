package com.song.po;

public class Blog {

	private Integer id;

    private String title;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
    
    @Override
	public String toString() {
		return "Blog [id=" + id + ", title=" + title + ", content=" + content + "]";
	}

    
}