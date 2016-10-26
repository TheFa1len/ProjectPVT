package Model;

public class Post extends Entity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7275529067115324393L;
	String post;

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}
	@Override
	public String toString(){
		return post;
	}
}
