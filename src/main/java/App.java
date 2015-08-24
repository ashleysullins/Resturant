import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import org.sql2o.*;

public class App {
  public static void main(String[] args) {
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String,Object>();
      model.put("template", "templates/home.vtl");
      model.put("information", Information.all());

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/information/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/information.vtl");
      Information name = Information.find(Integer.parseInt(request.params(":id")));

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/submit", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/home.vtl");
      String name = request.queryParams("newName");
      String address = request.queryParams("newAddress");
      String phoneString = request.queryParams("newPhone");
      Integer phone = Integer.parseInt(phoneString);
      String type = request.queryParams("newType");
      String starsString = request.queryParams("newStars");
      Integer stars = Integer.parseInt(starsString);
      String priceString = request.queryParams("newPrice");
      int price = Integer.parseInt(priceString);
      String deleteMeString = request.queryParams("delete");
      Boolean deleteme = Boolean.valueOf(deleteMeString);


      Information newInformation = new Information(name, address, phone, type, stars, price, deleteme);
      newInformation.save();
      model.put("information", Information.all());

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }

  //Algorithm goes here
}
