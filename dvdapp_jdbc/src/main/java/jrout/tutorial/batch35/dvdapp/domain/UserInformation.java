package jrout.tutorial.batch35.dvdapp.domain;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class UserInformation {
    private String email;
    private String email2;
    private String occupation;
    private String password;
    private String remember;
    private int customer_id;
    private String store_id;
    private String first_name;
    private String last_name;
    private String  address_id;
    private String active_bool;
    private String create_date;
    private String last_update;
    private String active;
}
