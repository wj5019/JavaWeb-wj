package cn.tedu.javaweb.pojo;

//����ģ�ͣ�ר�������û�����ķ�װ
public class User {
//user����ֶ�������mysql���ݿ��е�t_user��
	//�������ֶΣ���Ӧ��t_user���е�����ֶ�
	private String phone;//�û����������绰����
	private String uname;
	private String upwd;
	private String email;
	private int role;//��ɫ��0�������ߣ�1������Ա��
	//��ӡ�û��������Ϣ��ʹ��toString()����
	@Override
	public String toString() {
		return "User [phone=" + phone + ", uname=" + uname + ", upwd=" + upwd + ", email=" + email + ", role=" + role
				+ "]";
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpwd() {
		return upwd;
	}
	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
		

		
	
}
