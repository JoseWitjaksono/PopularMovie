package id.sch.smktelkom_mlg.privateassignment.xirpl634.popularmovie.model;

/**
 * Created by Jose Witjaksono on 13/06/2017.
 */

public class Local {
	String title;
	String desc;

	public Local() {
	}

	public Local(String title, String desc) {
		this.title = title;
		this.desc = desc;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}