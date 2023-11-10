package asia.lhweb.springboot.bean;

/**
 * @author :罗汉
 * @date : 2023/9/27
 */

public class student {
    private Integer id;
    private String name;

    public student() {
    }

    public student(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
