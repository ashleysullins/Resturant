import java.util.List;
import org.sql2o.*;

public class Information {
  private int id;
  private String name;
  private String address;
  private int phone;
  private String type;
  private int stars;
  private int price;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public int getPhone() {
    return phone;
  }

  public String getType() {
    return type;
  }

  public int getStars() {
    return stars;
  }

  public int getPrice() {
    return price;
  }

  public Information(String name, String address, Integer phone, String type, Integer stars, Integer price) {
    this.name = name;
    this.address = address;
    this.phone = phone;
    this.type = type;
    this.stars = stars;
    this.price = price;
  }

  public static List<Information> all() {
    String sql = "SELECT id, name, address, phone, type, stars, price FROM information";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Information.class);
    }
  }

  @Override
  public boolean equals(Object otherInformation){
    if (!(otherInformation instanceof Information)) {
      return false;
    } else {
      Information newInformation = (Information) otherInformation;
      return this.getName().equals(newInformation.getName());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO Information(name, address, phone, type, stars, price) VALUES (:name, :address, :phone, :type, :stars, :price)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("address", this.address)
        .addParameter("phone", this.phone)
        .addParameter("type", this.type)
        .addParameter("stars", this.stars)
        .addParameter("price", this.price)
        .executeUpdate()
        .getKey();
    }
  }

  public static Information find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM Information where id=:id";
      Information Information = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Information.class);
      return Information;
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM Information WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

 }
