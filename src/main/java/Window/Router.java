package Window;

public class Router {
    private static Router ourInstance = new Router();

    public static Router getInstance() {
        return ourInstance;
    }

    private Router() {
    }
}
