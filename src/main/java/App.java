import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
	public static void main(String[] args) {
		staticFileLocation("/public");
		String layout = "template/layout.vtl";
		// root route
		get("/", (request, response) -> {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("template", "template/index.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());

		// task route
		post("/tasks", (request, response) -> {
		 Map<String, Object> model = new HashMap<String, Object>();

		 String description = request.queryParams("description");
		 Task newTask = new Task(description);
		 request.session().attribute("task", newTask);

		 model.put("template", "templates/success.vtl");
		 return new ModelAndView(model, layout);
	 }, new VelocityTemplateEngine());
	}
}
