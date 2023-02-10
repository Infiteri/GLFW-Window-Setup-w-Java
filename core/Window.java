package core;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;

public class Window {
    public static Window window;

    // Data
    private long glfwWindow;

    // Get window setup
    public static Window GetWindow() {
        if (Window.window == null) {
            Window.window = new Window();
        }
        return Window.window;
    }

    public String title;
    public int width, height;

    private Window() {
        this.title = "Game Engine";
        this.width = 1024;
        this.height = 576;
    }

    public void Run() {
        Init();
        Update();
    }

    /**
     * 
     */
    private void Init() {
        GLFWErrorCallback.createPrint(System.err).set();

        // Inits
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW.");
        }

        // Configuration
        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);
        GLFW.glfwWindowHint(GLFW.GLFW_MAXIMIZED, GLFW.GLFW_TRUE);

        // Create the window
        glfwWindow = GLFW.glfwCreateWindow(this.width, this.height, this.title, MemoryUtil.NULL, MemoryUtil.NULL);
        if (glfwWindow == MemoryUtil.NULL) {
            throw new IllegalStateException("Unable to initialize GLFW Window");
        }

        // OpenGL Context
        GLFW.glfwMakeContextCurrent(glfwWindow);

        // V-Sync
        GLFW.glfwSwapInterval(1);

        // Show the window
        GLFW.glfwShowWindow(glfwWindow);

        // GL Capabilities
        GL.createCapabilities();
    }

    private void Update() {
        while (!GLFW.glfwWindowShouldClose(glfwWindow)) {
            // Poll events
            GLFW.glfwPollEvents();

            GL11.glClearColor(0, 0, 0, 1);
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

            GLFW.glfwSwapBuffers(glfwWindow);
        }
    }
}
