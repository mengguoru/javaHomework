package DA;

public class DBInfo {
	private String time;
	private String title;
	private String editor;
	private String detail;
	private int id;
	
	public DBInfo(String time, String title, String editor, String detail, int id) {
		super();
		this.time = time;
		this.title = title;
		this.editor = editor;
		this.detail = detail;
		this.id = id;
	}

	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}
}
