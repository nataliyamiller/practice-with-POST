import java.util.HashMap;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("username", request.session().attribute("username"));

      model.put("template", "templates/welcome.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/rectangle", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int length = Integer.parseInt(request.queryParams("length"));
      int width = Integer.parseInt(request.queryParams("width"));

      Rectangle myRectangle = new Rectangle(length, width);
      model.put("myRectangle", myRectangle);

      model.put("template", "templates/rectangle.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/welcome", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      String inputtedUsername = request.queryParams("username");
      request.session().attribute("username", inputtedUsername);
      model.put("username", inputtedUsername);

      model.put("template", "templates/welcome.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }
}
