package feng.qiye.pojo.corp;

import java.io.Serializable;
/**
 * 企业组织部门类
 * @author sunlight
 *
 */
@SuppressWarnings("serial")
public class QiYeDepartment implements Serializable{
	private int id;
	private String name;
	private int parentId;

	public QiYeDepartment() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public QiYeDepartment(int id, String name, int parentId) {
		super();
		this.id = id;
		this.name = name;
		this.parentId = parentId;
	}

}
