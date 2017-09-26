package com.michealyang.auth.domain;

/**
 * @author michealyang
 * @version 1.0
 * @created 17/7/21
 * 开始眼保健操： →_→  ↑_↑  ←_←  ↓_↓
 */
public class User {
    private Long id;
    //unique
    private String name;
    private String passwd;
    private String nickname;
    private String telephone;
    private String email;
    private String salt;
    private Integer ctime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getCtime() {
        return ctime;
    }

    public void setCtime(Integer ctime) {
        this.ctime = ctime;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", passwd='").append(passwd).append('\'');
        sb.append(", nickname='").append(nickname).append('\'');
        sb.append(", telephone='").append(telephone).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", salt='").append(salt).append('\'');
        sb.append(", ctime=").append(ctime);
        sb.append('}');
        return sb.toString();
    }
}
