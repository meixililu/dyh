package com.dyh.bean;

public class NewsBean implements BaseType{
	
	private String id = "1";
	
	private String title = "第一届办公室主任工作交流会召开";
	
	private String content = "面对针纺织行业国内外市场竞争仍旧激烈的现状，我会以“深入行业服务，了解企业发展现状，解决相应困难，提供扶持与帮助”为目标，于8月23日在山西太原召开2013年中国针织工业协会会员单位办公室主任工作交流会。会上，瞿静秘书长向与会代表分析了今年上半年针织行业的整体经济运行情况及欧美日主要国际市场的现状及趋势。会议除向与会代表简单介绍了中国针织工业协会的年度大事件及未来重点开拓的项目外，也通过现场讨论环节，听取了与会代表从一线企业带来的发展现状介绍、面临困难及对协会工作的建议。\\n\\n办公室主任工作交流会是中国针织工业协会寻找服务创新模式的新尝试。通过第一届办公室主任工作交流会上与会代表的充分交流与沟通，必将为协会与会员企业间的深入合作与推动行业发展奠定坚实的基础。";
	
	private String icon = "";
	
	private String time = "2012年10月3日15时23分";
	
	private String source;
	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

}
