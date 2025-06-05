import controller.MenuController;
import model.GameModel;
import view.TetrisFrame;

public class TetrisGame {
    public static void main(String[] args) {
        TetrisFrame view = new TetrisFrame();
        MenuController controller = new MenuController(view);

        view.init(controller);
    }
}
