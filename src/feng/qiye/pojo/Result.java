package feng.qiye.pojo;

public class Result<T> {
	private T object;
	private String errcode;
	private String errmsg;

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	@Override
	public String toString() {
		return "Result [object=" + object + ", errcode=" + errcode
				+ ", errmsg=" + errmsg + "]";
	}

	
}
