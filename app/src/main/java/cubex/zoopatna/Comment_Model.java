package cubex.zoopatna;

/**
 * Created by srikanthk on 11/8/2016.
 */
public class Comment_Model {

  public String name, comment, email, phone_num;

    public Comment_Model(String name, String email, String phone_num, String comment){

        this.name=name;
        this.comment=comment;
        this.email=email;
        this.phone_num=phone_num;

    }

    public Comment_Model() {

    }
}
