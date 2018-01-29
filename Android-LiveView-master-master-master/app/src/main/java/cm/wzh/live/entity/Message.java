package cm.wzh.live.entity;

/**
 * Created by WZH on 2016/12/25.
 */

public class Message {
    public String name ;
    public String message ;
    public int level ;
    public String img ;

    public void setName(String name) {
        this.name = name;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public int getLevel() {
        return level;
    }

    public String getImg() {
        return img;
    }
}
