package newsManagement;

public class NewsInfo {
    private int id;			   //新闻id
    private String title;      //标题
    private String content;    //内容
    private int type;          //新闻状态

    public NewsInfo(){

    }
    public NewsInfo(int id,String title,String content,int type){
        this.id=id;
        this.title=title;
        this.content=content;
        this.type=type;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title=title;
    }

    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content=content;
    }

    public int getType(){
        return type;
    }
    public void setType(int type){
        this.type=type;
    }
}
