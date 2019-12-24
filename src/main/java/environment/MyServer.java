package environment;

import java.util.Map;

import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

/**
 * Created by Alexander Pushkarev on 24.12.19.
 */
public class MyServer {


    public void start() {
        UserApplication controller = new UserApplication();

        staticFiles.location("/public");
        get("/index", (req, res) -> {
            Map<String, Object> model = controller.getUsersList();
            return new FreeMarkerEngine().render(new ModelAndView(model, "index.ftl"));
        });
        post("/index", (req, res) -> {
            MultiMap<String> params = new MultiMap<>();
            UrlEncoded.decodeTo(req.body(), params, "UTF-8");

            Map<String, Object> model = controller.addUser(
                            params.getString("username")
            );
            return new FreeMarkerEngine().render(new ModelAndView(model, "index.ftl"));
        });
    }

}
