package wuziqi;

public enum Chessman {
	//ö������
	BLACK("��"),WHITE("��");
	private String chessman;
	//˽�й�������
	private Chessman(String chessman)
	{
		this.chessman=chessman;
		
	}
	public String getChessman()
	{
		return this.chessman;
	}
}
