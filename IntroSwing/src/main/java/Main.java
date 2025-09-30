import org.example.controller.FileExplorerController;
import org.example.model.FileExplorerModel;
import org.example.view.FileExplorerView;

public class Main {
    public static void main(String[] args) {
        FileExplorerView view = new FileExplorerView();
        FileExplorerModel model = new FileExplorerModel();
        new FileExplorerController(view, model);
        view.show();
    }
}
