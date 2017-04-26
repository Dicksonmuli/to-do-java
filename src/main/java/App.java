import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
	public static void main(String[] args) {
		staticFileLocation("/public");
		String layout = "templates/layout.vtl";
		// root route
		get("/", (request, response) -> {
			Map<String, Object> model = new HashMap<String, Object>();
			// model.put("tasks", request.session().attribute("tasks"));
			model.put("template", "templates/index.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());
		// new tast
		get("tasks/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/task-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
		// task routes
		get("/tasks", (request, response) -> {
		 Map<String, Object> model = new HashMap<String, Object>();
		 model.put("tasks", Task.all());
		 model.put("template", "templates/tasks.vtl");
		 return new ModelAndView(model, layout);
	 }, new VelocityTemplateEngine());

		post("/tasks", (request, response) -> {
		 Map<String, Object> model = new HashMap<String, Object>();
		//  (using sessions but we dont need it anymore since we are storing tasks )
		//  ArrayList<Task> tasks = request.session().attribute("tasks");
		//  if(tasks == null) {
		// 	 tasks = new ArrayList<Task>();
		// 	 request.session().attribute("tasks", tasks);
		//  }

		 String description = request.queryParams("description");
		 Task newTask = new Task(description);
		//  tasks.add(newTask);

		 model.put("template", "templates/success.vtl");
		 return new ModelAndView(model, layout);
	 }, new VelocityTemplateEngine());

	//  dynamic route for every task
	get("/tasks/:id", (request, response) -> {
  HashMap<String, Object> model = new HashMap<String, Object>();
  Task task = Task.find(Integer.parseInt(request.params(":id")));
  model.put("task", task);
  model.put("template", "templates/task.vtl");
  return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());
	}
}
