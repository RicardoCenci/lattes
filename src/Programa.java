import java.util.Stack;

public class Programa {
    private static Stack<SubMenu> menuStack = new Stack<>();
    private static boolean isRunning = false;
    public static void start(){
        isRunning = true;
    }

    public static boolean isRunning(){
        return isRunning;
    }
    public static void setLastMenu(SubMenu menu){
        Programa.menuStack.push(menu);
    }

    private static SubMenu popIfNotEmpty(){
        if(!menuStack.isEmpty()){
            return menuStack.pop();
        }
        return null;
    }
    public static SubMenu popLastMenu(){
        return popIfNotEmpty();
    }
    public static SubMenu popLastMenu(int n){
        SubMenu menu = null;
        for(int i = 0; i < n; i++){
            menu = popIfNotEmpty();
        }
        return menu;
    }
    public static SubMenu getLastMenu(){
        if(menuStack.isEmpty()){
            return null;
        };

        return menuStack.pop();
    }
    public static SubMenu peekLastMenu(){
        if(menuStack.isEmpty()){
            return null;
        };

        return menuStack.peek();
    }
    public static void exit(){
        Programa.isRunning = false;
    }
}
