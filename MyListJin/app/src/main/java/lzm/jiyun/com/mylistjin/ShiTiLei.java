package lzm.jiyun.com.mylistjin;

import java.util.List;

/**
 * Created by lenovo on 2018/1/29.
 */

public class ShiTiLei {
    /**
     * state : true
     * more : false
     * time : 1512370594
     * data : [{"contentid":11619,"modelid":4,"title":"【青声系语】青年，与新时代共进共成长","thumb":"http://upload.univs.cn/2017/1201/thumb_640_314_1512093662450.jpg","description":"国防生和网红外教心中的\u201c十九大\u201d有什么不同？在大学生眼中过去#我们这五年#又有什么变化？创业青年和学术新星有什么新计划？点开视频查看答案！","comments":0,"sorttime":1512370239},{"contentid":11516,"modelid":1,"title":"来一打这样的科大男团","thumb":"http://upload.univs.cn/2017/1126/thumb_640_314_1511675972339.jpg","description":"可能选择国防生的那一刻起，就注定他们要经历一段不一样的人生了，我们给予我们最美好的祝福，希望他们为学校争光！\n","comments":0,"sorttime":1511752647},{"contentid":11357,"modelid":1,"title":"重庆大学举行秋季学期开学典礼暨学习贯彻党的十九大精神报告会","thumb":"http://upload.univs.cn/2017/1114/1510638210291.jpg","description":"2017年11月9日下午2时30分，重庆大学团校 2017 年秋季学期开学典礼暨学习贯彻党的十九大精神报告会在重庆大学虎溪校区理科楼 LA101 报告厅举行。 ","comments":0,"sorttime":1510638658}]
     */

    private boolean state;
    private boolean more;
    private int time;
    private List<DataBean> data;

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean isMore() {
        return more;
    }

    public void setMore(boolean more) {
        this.more = more;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * contentid : 11619
         * modelid : 4
         * title : 【青声系语】青年，与新时代共进共成长
         * thumb : http://upload.univs.cn/2017/1201/thumb_640_314_1512093662450.jpg
         * description : 国防生和网红外教心中的“十九大”有什么不同？在大学生眼中过去#我们这五年#又有什么变化？创业青年和学术新星有什么新计划？点开视频查看答案！
         * comments : 0
         * sorttime : 1512370239
         */

        private int contentid;
        private int modelid;
        private String title;
        private String thumb;
        private String description;
        private int comments;
        private int sorttime;

        public int getContentid() {
            return contentid;
        }

        public void setContentid(int contentid) {
            this.contentid = contentid;
        }

        public int getModelid() {
            return modelid;
        }

        public void setModelid(int modelid) {
            this.modelid = modelid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getComments() {
            return comments;
        }

        public void setComments(int comments) {
            this.comments = comments;
        }

        public int getSorttime() {
            return sorttime;
        }

        public void setSorttime(int sorttime) {
            this.sorttime = sorttime;
        }
    }
}
